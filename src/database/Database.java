package database;

import gui.MainGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.sqlite.SQLiteConnection;

public class Database {

	private final static String TABLE_NAME = "User_Account";
	private final static String DB_LOCATION = "jdbc:sqlite:C:/Users/klicata/workspace/VideoGames/sql/VideoGameDatabase";
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs = null;

	/*
	 * close() closes all connection to the database
	 */
	public static void close(){
		//System.out.println("Closing");
		try{
			if(rs != null){
				rs.close();
			}
			if(statement != null){
				statement.close();
			}
			if(connection != null){
				connection.close();
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	/*
	 * getResultSetFromSQL(String sql) takes in a query string and
	 * 			returns the resultSet of that query
	 */
	public static ResultSet getResultSetFromSQL(String sql) {
		
		MainGUI.setHourGlass(true);
		
		try {
			Class.forName("org.sqlite.JDBC"); // Register JDBC driver
			connection = DriverManager.getConnection(DB_LOCATION);
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); 	
				
		} catch (SQLException se) {
			System.out.println("Unable to connect to database");
		} catch (Exception e) {
			System.out.println("Driver issues");
		}
		
		MainGUI.setHourGlass(false);
		
		return rs;
	}
	
	/*
	 * isValidSelectSql(String sql) checks for valid query string and
	 * 				returns boolean
	 */
	private static boolean isValidSelectSql(String sql) {
		boolean result = false;
		
		// Determine if this string is a valid Select Sql Statement
		if (sql != null) {
			if (sql.indexOf("SELECT") != -1 && sql.indexOf("FROM") != -1) {
				result = true;
			}
			if (sql.indexOf("INSERT INTO") != -1 && sql.indexOf("VALUES") != -1) {
				result = true;
			}
		}
		
		return result;
	}
	
	/*
	 * executeSQL(String sqlString) takes in a query string and returns
	 * 				the boolean value of the query
	 */
	public static boolean executeSQL(String sqlString) {
		boolean result = false;
		
		if(sqlString != null){
			try{
				Class.forName("org.sqlite.JDBC"); //load sqlite-JDBC driver
				connection = DriverManager.getConnection(DB_LOCATION);
				statement = connection.createStatement();
				result = statement.execute(sqlString);
				
			} catch (SQLException e) {
				//possibly db file not found
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally{
				try { statement.close(); } catch (SQLException e) { /*ignored*/}
				try { connection.close(); } catch (SQLException e) { /*ignored*/}
			}
		}
		
		return result;
	}
	
	/*
	 * getDBLocation() is a getter method 
	 */
	public static String getDBLocation(){
		return DB_LOCATION;
	}
}