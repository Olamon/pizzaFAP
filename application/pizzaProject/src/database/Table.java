/*
 * Wstępny wrapper na tabele
 */

package database;

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
		this.name = new String(name);
	}
	
	// jakiś select - można selekcić po nazwach tabel lub dać nulla lub pustą tabelę -
	// wtedy wybierze wszystko
	//TODO to trzeba ulepszyć
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
	
	//Bardzo kiepski insert - nie wiem jak sobie poradzi z nietrywialnymi typami danych
	//działanie podobne do postgresowego inserta
	//TODO to trzeba ulepszyć
	public void insert(String[] columnNames, String[][] values) throws SQLException {
		Statement st = connection.createStatement();
		
		String columns;
		if(columnNames == null || columnNames.length == 0)
			columns = "";
		else
			// wstawia ',' między sąsiednie elementy tabeli
			columns = "(" + StringUtils.join(columnNames, ",") + ")";
		
		//spłaszczamy tablicę tablic wartości (krotki) do jednego stringa
		String[] rows = new String[values.length];
        for(int i = 0; i < values.length; i++) {
        	rows[i] = new String("('" + StringUtils.join(values[i], "','") + "')");
        }
        String valuesString = new String(StringUtils.join(rows, ","));
        //System.out.println(valuesString);
		st.executeUpdate("INSERT INTO " + name + " " + columns + 
						 " VALUES " + valuesString);  
		//na koniec trzeba zapiasć zmiany w bazie
		connection.commit();
	}
	
	private Connection connection;
	private String name;
}
