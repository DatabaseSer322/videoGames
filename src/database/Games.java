package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Games { //extends Object implements java.io.Serializable {
	private static String TABLE_NAME = "Games";
	private static String FIELD_GID = "Gid";
	private static String FIELD_TITLE = "Title";
	private static String FIELD_RATE_STAR = "Rate_Star";
	private static String FIELD_GENRE = "Genre";
	private static String FIELD_RATING_AGE = "Rating_Age";
	
	private String gameID;
	private String gameTitle;
	private String gameRateStar;
	private String gameGenre;
	private String gameRatingAge;
	
	public Games(String title, String rate, String genre, String rating){
		this.gameTitle = title;
		this.gameRateStar = rate;
		this.gameGenre = genre;
		this.gameRatingAge = rating;
	}
	
	public Games(ResultSet rs){
		try{
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
}