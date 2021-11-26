package theater_project_code;

public class Date {
	private int month;
	private int day;
	private int year;
	private int hours;
	private int minutes;
	private int seconds;
	
	/*
	 * constructors
	 */
	
	public Date(int month, int day, int year, int hours, int minutes, int seconds) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public Date() {
		this.month = 0;
		this.day = 0;
		this.year = 0;
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}
	
	/*
	 * getters and setters
	 */
	
	public int get_month() {
		return month;
	}
	public void set_month(int month) {
		this.month = month;
	}
	public int get_day() {
		return day;
	}
	public void set_day(int day) {
		this.day = day;
	}
	public int get_year() {
		return year;
	}
	public void set_year(int year) {
		this.year = year;
	}
	public int get_hours() {
		return hours;
	}
	public void set_hours(int hours) {
		this.hours = hours;
	}
	public int get_minutes() {
		return minutes;
	}
	public void set_minutes(int minutes) {
		this.minutes = minutes;
	}
	public int get_seconds() {
		return seconds;
	}
	public void set_seconds(int seconds) {
		this.seconds = seconds;
	}
	
	@Override
	public String toString() {
		return this.month + "/" + this.day + "/" + this.year + ", " + this.hours + ":" + this.minutes + ":" + this.seconds;
	}
}
