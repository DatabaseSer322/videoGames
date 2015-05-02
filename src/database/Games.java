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
	
	public Games(){
		
	}
	
	public Games(String title, String rate, String genre, String rating){
		this.gameTitle = title;
		this.gameRateStar = rate;
		this.gameGenre = genre;
		this.gameRatingAge = rating;
	}
	
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
	
	
	public static ArrayList<Games> getAllGamesFromDatabase()
	{
		ArrayList<Games> resultList = new ArrayList<Games>();
		
		ResultSet rs = Database.getResultSetFromSQL("SELECT * FROM " + TABLE_NAME);
		if (rs != null)
		{
			try
			{
				while (rs.next())
				{
					Games game = new Games(rs);
					resultList.add(game);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		return resultList;
	}
	
	public static ArrayList<Games> getFilteredGamesFromDatabase(Games filter)
	{
		ArrayList<Games> resultList = new ArrayList<Games>();
		
		String sql = "SELECT * FROM " + TABLE_NAME + " " + filter.getWhereClause()
				+ " ORDER BY " + FIELD_TITLE + ", " + FIELD_RATE_STAR + ", "
				+ FIELD_GENRE + ", " + FIELD_RATING_AGE;
		ResultSet rs = Database.getResultSetFromSQL(sql);
		
		if (rs != null)
		{
			try
			{
				while (rs.next())
				{
					Games game = new Games(rs);
					resultList.add(game);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		Database.close();
		
		return resultList;
	}
	
	public String getWhereClause()
	{
		StringBuilder whereClause = new StringBuilder();
		String result = new String("");
		
		if (gameTitle != null || gameRateStar != null || gameGenre != null
				|| gameRatingAge != null)
		{
			whereClause = addCriteriaToStringBuilder(whereClause, "Title", gameTitle);
			whereClause = addCriteriaToStringBuilder(whereClause, "Rate_Star", gameRateStar);
			whereClause = addCriteriaToStringBuilder(whereClause, "Genre", gameGenre);
			whereClause = addCriteriaToStringBuilder(whereClause, "Rating_Age", gameRatingAge);
			
			result = "WHERE " + whereClause.toString();
		}
		
		return result;
	}
	
	private StringBuilder addCriteriaToStringBuilder(StringBuilder builder,
			String fieldName, String value)
	{
		
		if (value != null)
		{
			if (builder.length() > 0)
			{
				builder.append(" AND ");
			}
			
			builder.append(fieldName + " LIKE \"" + value + "%\"");
		}
		
		return builder;
	}
	
	public static ArrayList<Games> getUserGamesFromDatabase(String status, int userID)
	{
		ArrayList<Games> resultList = new ArrayList<Games>();
		
		String sql = "SELECT * FROM " + TABLE_NAME_ACCOUNT 
				+ " WHERE " + FIELD_UID + " = " + userID 
				+ " AND " + FIELD_STATUS + " = " + "\"" + status + "\""
				+ " ORDER BY " + FIELD_UID + ", " + FIELD_STATUS;

		ResultSet rs = Database.getResultSetFromSQL(sql);
		
		if (rs != null)
		{
			try
			{
				while (rs.next())
				{
					int gameId = rs.getInt(FIELD_GID);
					Games games = getGamesFromDatabaseWithID(gameId);
					resultList.add(games);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		Database.close();
		
		return resultList;
	}

	public static Games getGamesFromDatabaseWithID(int id)
	{
		Games resultGame = new Games();
		
		String sql = "SELECT * FROM " + TABLE_NAME + " " 
					+ " WHERE " + FIELD_GID + " = " + id;
		ResultSet rs = Database.getResultSetFromSQL(sql);
		
		if (rs != null)
		{
			try
			{
				while (rs.next())
				{
					resultGame = new Games(rs);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		Database.close();
		
		return resultGame;
	}
	
	public void deleteGameFromList(int gid, int uid){
		Database.executeSQL("DELETE FROM " + TABLE_NAME_ACCOUNT 
				+ " WHERE " + FIELD_GID + " = "	+ gid 
				+ " AND " + FIELD_UID + " = " + uid + ";");
	}
	
	public void addGameFromList(int gid, int uid, String status){
		Database.executeSQL("INSERT INTO " + TABLE_NAME_ACCOUNT 
				+ "(" + FIELD_GID + ", " + FIELD_UID + ", " + FIELD_STATUS
				+ ") VALUES (" + gid + ", "+ uid + ", \"" + status + "\");");
	}
}
