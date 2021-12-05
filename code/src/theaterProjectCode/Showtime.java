package theaterProjectCode;
import java.io.Serializable;
import java.util.ArrayList;

public class Showtime implements Serializable{
	/**
	 * id
	 */
	private static final long serialVersionUID = 8986433822983659960L;
	private String name;
	private Date date;
	private ArrayList<Theater> theaters;
	private int rating;
	private int popularity;
	private String category;
	private double price;
	
	/*
	 * constructors
	 */
	
	public Showtime(Date date, String name, ArrayList<Theater> theaters, int popularity, String category, double price, int rating) {
		this.date = date;
		this.name = name;
		this.theaters = theaters;
		this.popularity = popularity;
		this.category = category;
		this.price = price;
		this.rating = rating;
	}
	
	public Showtime() {
		this.date = null;
		this.name = "";
		this.theaters = new ArrayList<Theater>();
	}
	
	/*
	 * getters and setters
	 */
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Theater> getTheaters() {
		return theaters;
	}
	public void setTheaters(ArrayList<Theater> theaters) {
		this.theaters = theaters;
	}
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	/*
	 * method to check if theater is listed in theater list
	 */
	public boolean isListed(String ID) {
		for(Theater theater : theaters) {
			if (theater.getTheaterID().equals(ID)) {
				return true;
			}
		}
		return false;
	}
	/*
	 * method to list theaters in show time
	 */
	public void listTheaters() {
		for (Theater theater : theaters) {
			System.out.println(theater.getTheaterID());
		}
	}
	/*
	 * list theaters which have space in them
	 */
	public void listUnreservedTheaters() {
		for(Theater theater: theaters) {
			if(!theater.isFull()) {
				System.out.println(theater.getTheaterID());
			}
		}
	}
	/*
	 * check if there are any unreserved theaters
	 */
	public boolean emptySeatExists() {
		for(Theater theater: theaters) {
			if(!theater.isFull()) {
				return true;
			}
		}
		return false;
	}
	/*
	 * method to add a theater to show time
	 */
	public void addTheater(Theater theater) {
		this.theaters.add(theater);
	}
	/*
	 * get theater by ID, returns a null theater if it doesnt exist
	 */
	public Theater getTheaterByID(String ID) {
		if(this.theaters.isEmpty() || (this.theaters == null)) {
			System.out.println("No theaters in local system, please add a theater");
			return new Theater();
		}else {
			for(Theater theater : theaters) {
				if(theater.getTheaterID().equals(ID)) {
					return theater;
				}
			}
		}
		return new Theater();
	}
	
	@Override
	public String toString() {
		String str = "";
		str += this.name + "; In Theaters ";
		if(!theaters.isEmpty()) {
			for(Theater theater : theaters) {
				str += theater.getTheaterID() + ",";
			}
			//remove trailing comma
			str = str.substring(0, str.length() - 1);
		}
		str += " at " + this.date.toString() + "\n";
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