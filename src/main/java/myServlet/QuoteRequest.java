package myServlet;

public class QuoteRequest {
	private String hike = null;
	private String duration = null;
	private String year = null;
	private String month = null;
	private String day = null;
	private String numHikers = null;
	
//	to be linked
	private String[] hikeNames;
	private int maxYear;
	private String outputMessage="";
	private boolean firstAccess = true;
	
	
	
	public String getHike() {
		return hike;
	}
	public void setHike(String input) {
		hike = input;
	}
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String input) {
		duration = input;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String input) {
		year = input;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String input) {
		month = input;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String input) {
		day = input;
	}
	
	public String getNumHikers() {
		return numHikers;
	}
	public void setNumHikers(String input) {
		numHikers = input;
	}
	
	public String[] getHikeNames() {
		return hikeNames;
	}
	public void setHikeNames(String[] input) {
		hikeNames = input;
	}
	
	public int getMaxYear() {
		return maxYear;
	}
	public void setMaxYear(int input) {
		maxYear = input;
	}
	
	public String getOutputMessage() {
		return outputMessage;
	}
	public void setOutputMessage(String input) {
		outputMessage = input;
	}
	
	public boolean isFirstAccess() {
		return firstAccess;
	}
	public void setFirstAccess(boolean input) {
		firstAccess = input;
	}
	
}
