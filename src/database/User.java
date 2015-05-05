package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class User {

	//Members
	private static final String TABLE_NAME = "User_Account";
	private static final String FIELD_USER_ID = "Uid";
	private static final String FIELD_FIRST_NAME = "FirstName";
	private static final String FIELD_LAST_NAME = "LastName";
	private static final String FIELD_DATE_OF_BIRTH = "DateBirth";
	private static final String FIELD_USER_EMAIL = "Email";
	private static final String FIELD_PASSWORD = "Password";
	
	private static User currentUser = null;
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs = null;
	
	private int userId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String userEmail;
	private String password;
	private int age;
	
	/*
	 * User(ResultSet rs) takes in a resultSet and sets values to
	 * 				member variables
	 */
	public User(ResultSet rs){
		try{
			this.userId = rs.getInt(FIELD_USER_ID);
			this.firstName = rs.getString(FIELD_FIRST_NAME);
			this.lastName = rs.getString(FIELD_LAST_NAME);
			this.dateOfBirth = rs.getString(FIELD_DATE_OF_BIRTH);
			this.userEmail = rs.getString(FIELD_USER_EMAIL);
			this.password = rs.getString(FIELD_PASSWORD);
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	/*
	 * getters/setters for membber variables
	 */
	public static String getTableName(){
		return TABLE_NAME;
	}
	
	public static String getFieldUserEmail(){
		return FIELD_USER_EMAIL;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		User.currentUser = currentUser;
	}
	
	/*
	 * newUser(String fn, String ln, String dob, String email, String pwd) takes in 
	 * 				values from user then creates a new user with those credentials
	 * 				and returns the user object
	 */
	public static User newUser(String fn, String ln, String dob, String email, String pwd){
		User userResult = null;
		
		String query = "INSERT INTO " + TABLE_NAME + "(" + FIELD_FIRST_NAME + ", "
				+ FIELD_LAST_NAME + ", " + FIELD_DATE_OF_BIRTH + ", " 
				+ FIELD_USER_EMAIL + ", " + FIELD_PASSWORD + ") "
				+ "VALUES(\"" + fn + "\", \"" + ln + "\", \"" + dob + "\", \"" 
				+ email + "\", \"" + pwd + "\")";
		
		try{
			//Class.forName("org.sqlite.JDBC"); //load driver
			connection = DriverManager.getConnection(Database.getDBLocation());
			statement = connection.createStatement();
			statement.executeUpdate(query);
			String sqlString = "SELECT * FROM " + TABLE_NAME 
					+ " WHERE " + FIELD_USER_EMAIL + " = " + "\"" + email + "\"";
			rs = statement.executeQuery(sqlString);

			if (rs != null){
				try{
					if (rs.next()){
						userResult = new User(rs);
						setCurrentUser(userResult);
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException se) {
			//does not return resultSet
			System.err.println(se.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}/* finally {
			Database.close();
		}*/

		return userResult;
	}
	
	/*
	 * authenticateUser(String email, String password) takes in the user's
	 * 				input and checks credentials to allow user to log in
	 * 				then sets current user to their account
	 */
	public static User authenticateUser(String email, String password){
		User result = null;
		String sqlString = "SELECT * FROM " + TABLE_NAME + " "
				+ "WHERE " + FIELD_USER_EMAIL + " = \"" + email + "\" AND "
				+ FIELD_PASSWORD + " = \"" + password + "\"";
		ResultSet rs = Database.getResultSetFromSQL(sqlString);
		if (rs != null){
			try{
				if (rs.next()){
					result = new User(rs);
					setCurrentUser(result);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Database.close();
		
		return result;
	}
}
