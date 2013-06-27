/*
 * Klasa będąca implementacją czynności wykonywanych przez rolę "login" w bazie.
 * Tutaj powinny znajdować się wszystkie zapytania, jakie użytkownik o tym
 * poziomie uprawnień może wykonać za pośrednictwem programu.
 */

package database;

import database.Session;
import objects.Account;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class LoginRole {
	public LoginRole() throws SQLException {
		Session.setRole(Constants.PATH, 
						Constants.LOGINS.get(Constants.ROLE.login), 
						Constants.PASSWORDS.get(Constants.ROLE.login));
	}
	
	public ResultSet Account_authenticate(String login, String password) throws SQLException {
		PreparedStatement st = Session.instance.connection.prepareStatement(
			"SELECT * FROM uzytkownik WHERE email = ? AND pass = ?");
		st.setString(1, login);
		st.setString(2, password);
		return st.executeQuery();
	}
	public ResultSet Account_exists(String login) throws SQLException {
		PreparedStatement st = Session.instance.connection.prepareStatement(
			"SELECT * FROM uzytkownik WHERE email = ?");
		st.setString(1, login);
		return st.executeQuery();
	}
	public int Account_insert(Account account) throws SQLException {
		PreparedStatement st = Session.instance.connection.prepareStatement(
			"INSERT INTO uzytkownik VALUES (?,?,?::typ_konta)");
		st.setString(1, account.email);
		st.setString(2, account.password);
		st.setString(3, account.typ_konta);
		return st.executeUpdate();
	}
}