package theater_project_code;

public class Showtime {
	private Date date;
	private String movie_name;
	private Seat[] seats_available;
	private Seat[] seats_reserved;
	
	public Date get_date() {
		return date;
	}
	public void set_date(Date date) {
		this.date = date;
	}
	public String get_movie() {
		return movie_name;
	}
	public void set_movie(String movie) {
		this.movie_name = movie;
	}
	public Seat[] get_seats_available() {
		return seats_available;
	}
	public void set_seats_available(Seat[] seats_available) {
		this.seats_available = seats_available;
	}
	public Seat[] get_seats_reserved() {
		return seats_reserved;
	}
	public void set_seats_reserved(Seat[] seats_reserved) {
		this.seats_reserved = seats_reserved;
	}
}
