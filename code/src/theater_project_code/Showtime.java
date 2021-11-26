package theater_project_code;

public class Showtime {
	private Date date;
	private String movieName;
	private Seat[] seatsAvailable;
	private Seat[] seatsReserved;
	
	/*
	 * constructors
	 */
	
	public Showtime(Date date, String movieName, Seat[] seatsAvailable, Seat[] seatsReserved) {
		this.date = date;
		this.movieName = movieName;
		this.seatsAvailable = seatsAvailable;
		this.seatsReserved = seatsReserved;
	}
	
	public Showtime() {
		this.date = null;
		this.movieName = "";
		this.seatsAvailable = null;
		this.seatsReserved = null;
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
	public Seat[] get_seatsAvailable() {
		return seatsAvailable;
	}
	public void set_seatsAvailable(Seat[] seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public Seat[] get_seatsReserved() {
		return seatsReserved;
	}
	public void set_seatsReserved(Seat[] seatsReserved) {
		this.seatsReserved = seatsReserved;
	}
	
	@Override
	public String toString() {
		return this.movieName + " at " + this.date.toString();
	}
}
