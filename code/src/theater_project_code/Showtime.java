package theater_project_code;
import java.util.ArrayList;

public class Showtime {
	private Date date;
	private String movieName;
	private ArrayList<Theater> theaters;
	
	/*
	 * constructors
	 */
	
	public Showtime(Date date, String movieName, ArrayList<Theater> theaters) {
		this.date = date;
		this.movieName = movieName;
		this.theaters = theaters;
	}
	
	public Showtime() {
		this.date = null;
		this.movieName = "";
		this.theaters = new ArrayList<Theater>();
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
	public ArrayList<Theater> get_theaters() {
		return theaters;
	}
	public void set_theaters(ArrayList<Theater> theaters) {
		this.theaters = theaters;
	}
	public void addTheater(Theater theater) {
		this.theaters.add(theater);
	}
	
	@Override
	public String toString() {
		String str = "";
		str += this.movieName + ": In Theaters ";
		if(!theaters.isEmpty()) {
			for(Theater theater : theaters) {
				str += String.valueOf(theater.getTheaterNumber()) + ",";
			}
			//remove trailing comma
			str = str.substring(0, str.length() - 1);
		}
		str += " at " + this.date.toString();
		return str;
	}
	
	/*
	 *test harness code 
	 * 
	public static void main(String[] args) {
		Showtime show1 = new Showtime();
		show1.set_date(new Date());
		show1.set_movie("Endgame");
		//test printing
		System.out.println(show1.toString());
		//create some default theaters to store
		Theater theater1 = new Theater(1);
		Theater theater2 = new Theater(2);
		//add two theaters
		show1.addTheater(theater1);
		show1.addTheater(theater2);
		System.out.println("Showtime info:");
		System.out.println(show1.toString());
	}*/
}
