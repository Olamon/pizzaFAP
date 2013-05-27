/*
 * Pomocnicze funkcje - joina chyba nie ma we wbudowanych bibliotekach javy 
 */

package utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringUtils {
	// wstawia delimiter między elementy i zwraca jako jeden String
	public static String join(Collection<String> s, String delimiter) {
		StringBuilder builder = new StringBuilder();
		Iterator<String> iter = s.iterator();
		while (iter.hasNext()) {
			builder.append(iter.next());
			if (!iter.hasNext()) {
				break;                  
			}
			builder.append(delimiter);
		}
		return builder.toString();
	}
	
	public static String join(String[] s, String delimiter) {
		return join(Arrays.asList(s), delimiter);
	}
	
	public static String join(String s, String delimiter) {
		return join(Arrays.asList(s), delimiter);
	}
	
	// służy do konwersji wyniku zapytania SQL (ResultSet) do kolekcji stringów
	// w której wiersze są jednym stringiem, z kolumnami rozdzielonymi przecinkiem
	public static Vector<String> RowsToStrings(ResultSet rs) {
		Vector<String> result = new Vector<String>();
		
		try {
			int columnCount = rs.getMetaData().getColumnCount();
	        while (rs.next()) {
	        	Vector<String> thisRow = new Vector<String>();
	            for (int i = 1; i <= columnCount; i++)
	                thisRow.add(rs.getString(i));
	            
	            result.add(StringUtils.join(thisRow, ", "));
	        }
		}
		catch (SQLException ex) {
			Logger lgr = Logger.getLogger(StringUtils.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
            
            result.clear();
		}
		
		return result;
	}
}
