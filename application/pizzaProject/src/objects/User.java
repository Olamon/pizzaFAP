/*
 *  klasa odzwierciedlająca pojedynczego uzytkownika
 *  pole hasła jest prywatne, więc da się sprawdzić jego zgodność z wpisanym hasłem tylko przez Authenticate
 */

package objects;

import fapDB.Session;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class User {
    public String email;
    private String password;    //haslło jest zahaszowane przez jakieś MD5, przy logowaniu porównuje się hasz wejścia z tym polem

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //przykładowa implementacja autoryzacji użytkownika
    public boolean Authenticate(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bytesOfPass = pass.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bytesOfPass);
        return(digest.toString()==password);    //jeszcze nie sprawdziłem, czy to działa poprawnie
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
