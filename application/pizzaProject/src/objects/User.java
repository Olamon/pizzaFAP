/*
 *  klasa odzwierciedlająca pojedynczego uzytkownika
 *  pole hasła jest prywatne, więc da się sprawdzić jego zgodność z wpisanym hasłem tylko przez Authenticate
 */

package objects;

import fapDB.Session;
import utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class User {
    public String email;
    private String password;    
    //hasło jest zahaszowane przez jakieś MD5, przy logowaniu porównuje się hasz wejścia z tym polem
    //TODO md5 to zły wybór - służy raczej do obliczania skrótów, niż do szyfrowania

    //"konstruktor" dla zewnętrzego kodu - hashuje podane hasło
    //nazwa nie najlepsza, ale nie wymyśliłem lepszej
    public static User CreateUser(String email, String password) throws 
    											UnsupportedEncodingException, 
    											NoSuchAlgorithmException {
    	byte[] bytesOfPass = password.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bytesOfPass);
        String hash = StringUtils.byteArrayToHexString(digest);
        return new User(email, hash);
    }
    //konstruktor dla do tworzenia modeli na podstawie wpisów w bazie - wewnętrzny
    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    //przykładowa implementacja autoryzacji użytkownika
    
    //zmieniłem metodę, bo przy logowaniu miałem problem:
    //- wiemy z jakim loginem i hasłem użytkownik próbuje się zalogować
    //- więc to co jest potrzebne przy logowaniu to funkcja, która sprawdzi czy
    //  istnieje krotka o takim emailu i haśle
    //- żeby to zrobić nie wystarczy porównywać podawanego przez uzytkownika
    //  hasła z jakimś emailem i hasłem, no bo właśnie nie wiemy z którym,
    //  musimy najpierw wyszukać
    public boolean Authenticate() throws SQLException {
    	String conditions = "email = '" + email + "' AND " + "pass = '" + password + "'";
        ResultSet rs = Session.current().selectQuery(null, "uzytkownik", conditions);
        return rs.next(); //true jeśli rs niepusty false wpp
    }

    //zwraca listę wszystkich ocen użytkownika
    public List<Ocena> GetUserReviews() throws SQLException
    {
        ResultSet rs = Session.current().selectQuery(null, "ocena", "email = " + email);
        if (rs != null)
            return Ocena.GetAll(rs);
        else return null;
    }

    //zwraca listę wszystkich uzytkownikow w bazie danych
    public static List<User> GetAll() throws SQLException
    {
        return GetAll(Session.current().getTable("uzytkownik").select(null));
    }

    // przekształca dany ResultSet na listę obiektów typu User
    public static List<User> GetAll(ResultSet rs) throws SQLException {
        List<User> list = new Vector<User>();
        while (rs.next())
            list.add(new User(rs.getString("email"), rs.getString("pass")));
        return list;
    }
}
