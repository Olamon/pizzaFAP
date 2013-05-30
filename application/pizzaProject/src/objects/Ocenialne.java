package objects;

import fapDB.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Ocenialne {

    public int id;

    public List<Ocena> GetAllReviews() throws SQLException {
        ResultSet rs = Session.current().selectQuery(null, "ocena", "oc_id = " + id);
        if (rs != null)
            return Ocena.GetAll(rs);
        return null;
    }
}
