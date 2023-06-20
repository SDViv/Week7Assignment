/**
 * 
 */
package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sound.sampled.Port;

import projects.exception.DbException;

/**
 * @author vguer
 *
 */
@SuppressWarnings("unused")
public class DbConnection {
	private static final String SCHEMA = "projects";
	private static final String USER = "projects";
	private static final String PASSWORD = "projects";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;

	public static Connection getConnection() {
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", HOST, PORT, SCHEMA, USER, PASSWORD);
System.out.println("Connecting with url="+url);
try {
Connection conn= DriverManager.getConnection(url);
System.out.println("Successgully obtrained connection!");
return conn;
}catch(SQLException e){
throw new DbException(e);
}
	}
}
