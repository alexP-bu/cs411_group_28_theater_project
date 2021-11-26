package theater_project_code;

public class Showtime {
	private Date date;
	private String movieName;
	private Seat[] seats;
	
	/*
	 * constructors
	 */
	
	public Showtime(Date date, String movieName, Seat[] seats, Seat[] seatsReserved) {
		this.date = date;
		this.movieName = movieName;
		this.seats = seats;
	}
	
	public Showtime() {
		this.date = null;
		this.movieName = "";
		this.seats = null;
	}
	
	/*
	 * getters and setters
	 */
	public Date get_date() {
		return date;
	}
	public void set_date(Date date) {
		this.date = date;
	}
	public String get_movie() {
		return movieName;
	}
	public void set_movie(String movie) {
		this.movieName = movie;
	}
	public Seat[] get_seats() {
		return seats;
	}
	public void set_seats(Seat[] seats) {
		this.seats = seats;
	}
	
	@Override
	public String toString() {
		return this.movieName + " at " + this.date.toString();
	}
}
