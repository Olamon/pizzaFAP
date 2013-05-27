/*
 * Wstępny wrapper na tabele
 */

package fapDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

import utils.StringUtils;

// instancje należy tworzyć przez Session.getTable();
public class Table {
	public Table(Connection connection, String name) throws IllegalArgumentException, SQLException {
		// następne 4 linijki sprawdzają czy tabela o nazwie name istnieje w bazie
		DatabaseMetaData md = connection.getMetaData();
		ResultSet rs = md.getTables(null, null, name, null);
		if (rs.next() == false) {
		  throw new IllegalArgumentException();
		}
		
		this.connection = connection;
		this.name = name;
	}
	
	// jakiś select - można selekcić po nazwach tabel lub dać nulla lub pustą tabelę -
	// wtedy wybierze wszystko
	public ResultSet select(String[] columnNames) throws SQLException {
		Statement st = connection.createStatement();
		
		String columns;
		if(columnNames == null || columnNames.length == 0)
			columns = "*";
		else
			// wstawia ',' między sąsiednie elementy tabeli
			columns = StringUtils.join(columnNames, ",");
        
		ResultSet rs = st.executeQuery("SELECT " + columns + " FROM " + name);  
        return rs;
	}
	
	private Connection connection;
	private String name;
}
