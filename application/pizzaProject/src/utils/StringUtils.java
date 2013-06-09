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
	
	//do przedstawiania hashy md5 w postaci stringa
	public static String byteArrayToHexString(byte[] a) {
	   StringBuilder sb = new StringBuilder();
	   for(byte b: a)
	      sb.append(String.format("%02x", b&0xff));
	   return sb.toString();
	}

    //jeśli się da, przedstawia łańcuch jako integer, a jeśli nie, zwraca alt
    public static int asInteger(String s, int alt)
    {
        try {
            Integer.valueOf(s);
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return alt;
        }
    }

    //jeśli się da, przedstawia łańcuch jako float, a jeśli nie, zwraca alt
    public static float asFloat(String s, float alt)
    {
        try {
            Float.valueOf(s);
            return Float.valueOf(s);
        } catch (NumberFormatException e) {
            return alt;
        }
    }
}
