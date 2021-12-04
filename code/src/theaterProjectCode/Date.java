package theaterProjectCode;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Date implements Serializable{
	/**
	 * id
	 */
	private static final long serialVersionUID = -496635546555161762L;
	private transient Scanner reader = new Scanner(System.in);
	private int month;
	private int day;
	private int year;
	private int hours;
	private int minutes;
	
	/*
	 * constructors
	 */
	
	public Date(int month, int day, int year, int hours, int minutes) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.hours = hours;
		this.minutes = minutes;
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
	/*
	 * methods
	 */
	public Date createDate() {
		System.out.println("Enter year number when showtime is on: ");
		int year = getNumInput();
		System.out.println("Enter month number when showtime is on: ");
		int month = getNumInput();
		System.out.println("Enter date number when showtime is on: ");
		int day = getNumInput();
		System.out.println("Enter hour number when showtime is on (24 hour clock): ");
		int hour = getNumInput();
		System.out.println("Enter minutes when showtimes is on: ");
		int minute = getNumInput();
		return new Date(month, day, year, hour, minute);
	}
	/*
	 * get number from user
	 */
	private int getNumInput() {
		int input = 0;
		do {
			try {
				input = Integer.parseInt(reader.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println(e);
			}
			System.out.println("invalid input entered! try again");
		}while(true);
		
		return input;
	}
	
	@Override
	public String toString() {
		return this.hours + ":" + this.minutes + " on " + this.month + "/" + this.day + "/" + this.year;
	}
}
