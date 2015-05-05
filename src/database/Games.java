package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Games { 
	private static String TABLE_NAME = "Games";
	private static String FIELD_GID = "Gid";
	private static String FIELD_TITLE = "Title";
	private static String FIELD_RATE_STAR = "Rate_Star";
	private static String FIELD_GENRE = "Genre";
	private static String FIELD_RATING_AGE = "Rating_Age";
	
	private static String TABLE_NAME_ACCOUNT = "Account_Games";
	private static String FIELD_UID = "Uid";
	private static String FIELD_STATUS = "Status";
	
	private String gameID;
	private String gameTitle;
	private String gameRateStar;
	private String gameGenre;
	private String gameRatingAge;
	
	private String gameStatus;
	
	//empty constructor
	public Games(){	
	}
	
	/*
	 * Games(String title, String rate, String genre, String rating) is a
	 * 		constructor accepting 4 parameters setting a game's attributes
	 */
	public Games(String title, String rate, String genre, String rating){
		this.gameTitle = title;
		this.gameRateStar = rate;
		this.gameGenre = genre;
		this.gameRatingAge = rating;
	}
	
	/*
	 * Games(ResultSet rs) constructor accepting 1 parameter as a ResultSet
	 * 		from the database setting a game's attributes
	 */
	public Games(ResultSet rs){
		try{
			this.gameID = rs.getString("Gid");
			this.gameTitle = rs.getString("Title");
			this.gameRateStar = rs.getString("Rate_Star");
			this.gameGenre = rs.getString("Genre");
			this.gameRatingAge = rs.getString("Rating_Age");
		} catch (SQLException e){
			System.err.println(e.getMessage());
		}
	}
	
	/*
	 * getters/setters
	 */
	public String getGameID() {
		return gameID;
	}
	public String getGameTitle() {
		return gameTitle;
	}
	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}
	public String getGameRateStar() {
		return gameRateStar;
	}
	public void setGameRateStar(String gameRateStar) {
		this.gameRateStar = gameRateStar;
	}
	public String getGameGenre() {
		return gameGenre;
	}
	public void setGameGenre(String gameGenre) {
		this.gameGenre = gameGenre;
	}
	public String getGameRatingAge() {
		return gameRatingAge;
	}
	public void setGameRatingAge(String gameRatingAge) {
		this.gameRatingAge = gameRatingAge;
	}
	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	/*
	 * getAllGamesFromDatabase() collects all the games in the database
	 */
	public static ArrayList<Games> getAllGamesFromDatabase(){
		ArrayList<Games> resultList = new ArrayList<Games>();
		
		ResultSet rs = Database.getResultSetFromSQL("SELECT * FROM " + TABLE_NAME);
		if (rs != null){
			try{
				while (rs.next()){
					Games game = new Games(rs);
					resultList.add(game);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultList;
	}
	
	/*
	 * getFilteredGamesFromDatabase(Games filter) takes in attributes of a game
	 * 		and searches for that particular game or games that match the given
	 * 		attributes and ruturn the list of games
	 */
	public static ArrayList<Games> getFilteredGamesFromDatabase(Games filter){
		ArrayList<Games> resultList = new ArrayList<Games>();
		
		String sql = "SELECT * FROM " + TABLE_NAME + " " + filter.getWhereClause()
				+ " ORDER BY " + FIELD_TITLE + ", " + FIELD_RATE_STAR + ", "
				+ FIELD_GENRE + ", " + FIELD_RATING_AGE;
		ResultSet rs = Database.getResultSetFromSQL(sql);
		
		if (rs != null){
			try{
				while (rs.next()){
					Games game = new Games(rs);
					resultList.add(game);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Database.close();
		
		return resultList;
	}
	
	/*
	 * getWhereClause() allows to build the query from the information from the
	 * 		search window and return the collective string
	 */
	public String getWhereClause(){
		StringBuilder whereClause = new StringBuilder();
		String result = new String("");
		
		if (gameTitle != null || gameRateStar != null || gameGenre != null
				|| gameRatingAge != null){
			whereClause = addCriteriaToStringBuilder(whereClause, "Title", gameTitle);
			whereClause = addCriteriaToStringBuilder(whereClause, "Rate_Star", gameRateStar);
			whereClause = addCriteriaToStringBuilder(whereClause, "Genre", gameGenre);
			whereClause = addCriteriaToStringBuilder(whereClause, "Rating_Age", gameRatingAge);
			
			result = "WHERE " + whereClause.toString();
		}
		
		return result;
	}
	
	/*
	 * addCriteriaToStringBuilder(StringBuilder builder, String fieldName, String value)
	 * 		takes in parameters of game info one at a time and appends the correct
	 * 		syntax needed and return the StringBuilder
	 */
	private StringBuilder addCriteriaToStringBuilder(StringBuilder builder,
			String fieldName, String value){
		
		if (value != null){
			if (builder.length() > 0){
				builder.append(" AND ");
			}
			
			builder.append(fieldName + " LIKE \"" + value + "%\"");
		}
		
		return builder;
	}
	
	/*
	 * getUserGamesFromDatabase(String status, int userID) takes in the status and userID
	 * 		to find all games currently in that status and return the list
	 */
	public static ArrayList<Games> getUserGamesFromDatabase(String status, int userID){
		ArrayList<Games> resultList = new ArrayList<Games>();
		
		String sql = "SELECT * FROM " + TABLE_NAME_ACCOUNT 
				+ " WHERE " + FIELD_UID + " = " + userID 
				+ " AND " + FIELD_STATUS + " = " + "\"" + status + "\""
				+ " ORDER BY " + FIELD_UID + ", " + FIELD_STATUS;

		ResultSet rs = Database.getResultSetFromSQL(sql);
		
		if (rs != null){
			try{
				while (rs.next()){
					int gameId = rs.getInt(FIELD_GID);
					Games games = getGamesFromDatabaseWithID(gameId);
					resultList.add(games);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Database.close();
		
		return resultList;
	}

	/*
	 * getGamesFromDatabaseWithID(int id) takes in a game ID and returns
	 * 		the game that corresponds with that ID
	 */
	public static Games getGamesFromDatabaseWithID(int id){
		Games resultGame = new Games();
		
		String sql = "SELECT * FROM " + TABLE_NAME + " " 
					+ " WHERE " + FIELD_GID + " = " + id;
		ResultSet rs = Database.getResultSetFromSQL(sql);
		
		if (rs != null){
			try{
				while (rs.next()){
					resultGame = new Games(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Database.close();
		
		return resultGame;
	}
	
	/*
	 * deleteGameFromList(int gid, int uid) takes in the primary key elements
	 * 		to find and remove the game from the user's list completely
	 */
	public void deleteGameFromList(int gid, int uid){
		Database.executeSQL("DELETE FROM " + TABLE_NAME_ACCOUNT 
				+ " WHERE " + FIELD_GID + " = "	+ gid 
				+ " AND " + FIELD_UID + " = " + uid + ";");
	}
	
	/*
	 * addGameFromList(int gid, int uid, String status) takes in the game ID and the
	 *  	user ID along with the status the user wants the game to go to and adds
	 *  	that information to the Game_Account table
	 */
	public void addGameFromList(int gid, int uid, String status){
		String checkExistingGameInList = "SELECT COUNT(*) FROM " + TABLE_NAME_ACCOUNT +
				" WHERE " + FIELD_GID + " = \"" + gid + "\" AND " +
				FIELD_UID + " = \"" + uid + "\"";
		
		ResultSet gameInList = Database.getResultSetFromSQL(checkExistingGameInList);
		
		try{
			if(gameInList.getInt(1) < 1){
				Database.close();
				Database.executeSQL("INSERT INTO " + TABLE_NAME_ACCOUNT 
					+ "(" + FIELD_GID + ", " + FIELD_UID + ", " + FIELD_STATUS
					+ ") VALUES (" + gid + ", "+ uid + ", \"" + status + "\");");
			}
			else {
				System.out.println("Game is already in your list");
			}
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
}
