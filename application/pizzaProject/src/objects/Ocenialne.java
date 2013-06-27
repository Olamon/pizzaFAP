package objects;

/*
 * Zmieniłem tę klasę na klasę będącą pojemnikiem na wartości pojedyńczej krotki
 * z danej tabeli, a metody dotyczące bazy przeniosłem do paczki "database"
 */

//klasa jest abstrakcyjna, bo "ocenialne" to cecha obiektu, a nie obiekt sam w sobie
public abstract class Ocenialne {
    public int id;
    public int iloscOcen;
    public float sredniaOcen;
    
    public Ocenialne(int id, int iloscOcen, float sredniaOcen) {
    	this.id = id;
    	this.iloscOcen = iloscOcen;
    	this.sredniaOcen = sredniaOcen;
    }

    /*
    public List<Ocena> GetAllReviews() throws SQLException {
        ResultSet rs = Session.current().selectQuery(null, "ocena", "oc_id = " + id);
        if (rs != null)
            return Ocena.GetAll(rs);
        return null;
    }
    */
}
