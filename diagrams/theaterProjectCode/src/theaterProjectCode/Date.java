package theaterProjectCode;
import java.time.LocalDateTime;

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
	/*
	 * default constructor sets date to current date 
	 */
	public Date() {
		LocalDateTime date = LocalDateTime.now();
		this.month = date.getMonthValue();
		this.day = date.getDayOfMonth();
		this.year = date.getYear();
		this.hours = date.getHour();
		this.minutes = date.getMinute();
		this.seconds = date.getSecond();
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
		return this.hours + ":" + this.minutes + " on " + this.month + "/" + this.day + "/" + this.year;
	}
}
