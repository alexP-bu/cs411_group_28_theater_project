package theaterProjectCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ShowtimeManager {
	Scanner reader = new Scanner(System.in);
	private ArrayList<Showtime> showtimes;
	private HashMap<String,Theater> theaters;
	private int NUM_THEATERS;
	/*
	 * constructors
	 */
	public ShowtimeManager() {
		this.showtimes = new ArrayList<Showtime>();
		this.theaters = new HashMap<String,Theater>();
		this.setNUM_THEATERS(0);
	}
	/*
	 * getters/setters
	 */
	public int getNUM_THEATERS() {
		return NUM_THEATERS;
	}
	public void setNUM_THEATERS(int nUM_THEATERS) {
		NUM_THEATERS = nUM_THEATERS;
	}
	
	/*
	 * list show times
	 */
	public void listShowtimes() {
		if((showtimes == null) || (showtimes.isEmpty())) {
			System.out.println("There are no showtimes available!");
		}else {
			System.out.println("Listing showtimes:");
			int i = 1;
			for(Showtime showtime : showtimes) {
				System.out.printf("%d: %s",i,showtime.toString());
			}
			System.out.println("Finished listing showtimes.");
		}
	}
	
	public void listTheaters() {
		if((theaters == null) || (theaters.isEmpty())) {
			System.out.println("There are no theaters registered in the system.");
		}else {
			System.out.println("Listing theaters by ID: ");
			for(String ID : theaters.keySet()) {
				System.out.println(ID);
			}
		}
	}
	/*
	 * add show time to local system
	 * returns true on successful add, false otherwise
	 */
	public boolean addShowtime(Showtime show) {
		if(show != null) {
			showtimes.add(show);
		}
		return false;
	}
	/*
	 * add theater to local system
	 */
	public void addTheater(Theater theater) {
		theaters.put(theater.getTheaterID(), theater);
		setNUM_THEATERS(getNUM_THEATERS() + 1);
		System.out.println("Theater successfully added to local system!");
	}
	/*
	 * delete theater from local system 
	 */
	public void deleteTheater(Theater theater) {
		if(theaters.containsKey(theater.getTheaterID())) {
			this.theaters.remove(theater.getTheaterID());
			System.out.println("Successfully deleted theater from system.");
		}else {
			System.out.println("Error deleting theater, theater not found!");
		}
	}
	/*
	 * delete theater by ID
	 * if successful, returns true. false otherwise
	 */
	public boolean deleteTheater() {
		if(theaters.isEmpty() || theaters == null) {
			return false;
		}
		System.out.println("Enter ID of theater to delete:");
		String ID = reader.nextLine();
		if(this.is_theater_valid(ID)) {
			Theater theater = theaters.get(ID);
			if(theater != null) {
				this.deleteTheater(theater);
				return true;
			}
		}
		return false;
	}
	/*
	 * view theater information 
	 */
	public void viewTheater() {
		if(theaters.isEmpty() || theaters == null) {
			System.out.println("No theaters in local system, please add a theater");
		}else {
			System.out.println("Please enter theater ID:");
			String ID = reader.nextLine();
			if(this.is_theater_valid(ID)) {
				System.out.println(theaters.get(ID).toString());
			}
		}
	}
	
	/*
	 * create custom theater and add it to the showtime manager
	 */
	public void createTheater() {
		Theater theater = new Theater();
		System.out.println("Enter theater ID:");
		String ID = reader.nextLine();
		while(theaters.containsKey(theater.getTheaterID())) {
			System.out.println("Theater already exists! Please enter a different number or ID.");
			ID = reader.nextLine();
		}
		theater.setTheaterID(ID);
		System.out.printf("Use the default seating chart for theater %s? \n", theater.getTheaterID());
		System.out.println("yes/no");
		if(reader.nextLine().equals("yes")) {
			theater.setSeatingChart(new SeatingChart());
		}else {
			System.out.println("Enter number of seating chart rows: ");
			while (!reader.hasNextInt()) {
				System.out.println("Please enter a number!");
				reader.next();
			}
			int rows = reader.nextInt();
			System.out.println("Enter number of seating chart columns: ");
			while(!reader.hasNextInt()) {
				System.out.println("Please enter a number!");
				reader.next();
			}
			int cols = reader.nextInt();
			theater.setSeatingChart(new SeatingChart(rows,cols));
		}
		addTheater(theater);
		System.out.println("Successfully added theater configuration to system.");
	}
	/*
	 * create new showtime. returns true on success, false on failure
	 */
	public void createShowtime() {
		Showtime show = new Showtime();
		System.out.println("Enter showtime name:");
		show.setName(reader.nextLine());
		System.out.println("Would you like to add which theaters the showtime will be in?");
		System.out.println("yes/no");
		if(reader.nextLine().equals("yes")) {
			if(theaters == null || theaters.isEmpty()) {
				System.out.println("There are no registered theaters in the system. Please create a theater to add it.");
			}else {
				System.out.println("Available theaters:");
				this.listTheaters();
				System.out.println("Enter theater ID");
				String ID = reader.nextLine();
				if(is_theater_valid(ID)) {
					show.addTheater(theaters.get(ID));
				}
			}
		}
		System.out.println("Enter year number when showtime is on: ");
		int year = reader.nextInt();
		System.out.println("Enter month number when showtime is on: ");
		int month = reader.nextInt();
		System.out.println("Enter date number when showtime is on: ");
		int day = reader.nextInt();
		System.out.println("Enter hour number when showtime is on (24 hour clock): ");
		int hour = reader.nextInt();
		System.out.println("Enter minutes when showtimes is on: ");
		int minute = reader.nextInt();
		show.setDate(new Date(month,day,year,hour,minute));
		System.out.println("Enter rating of showtime, 1 to 5");
		int rating = reader.nextInt();
		show.setRating(rating);
		System.out.println("Enter popularity of show, from 1 to 100");
		int popularity = reader.nextInt();
		show.setPopularity(popularity);
		System.out.println("Enter category of show, in one word (use dashes for Sci-Fi etc)");
		String category = reader.nextLine();
		show.setCategory(category);
		showtimes.add(show);
	}
	/*
	 * view showtime data
	 */
	public void viewShowtime() {
		this.listShowtimes();
		System.out.println("Select showtime by index next to it.");
		int selected = reader.nextInt();
		System.out.println(showtimes.get(selected - 1).toString());
	}
	/*
	 * test if theater ID is valid. If it is, return true. Else, return false.
	 */
	private boolean is_theater_valid(String ID) {
		while(!theaters.containsKey(ID)) {
			if(ID.equals("exit")) {
				break;
			}
			System.out.println("Please enter a valid theater ID or or type \"exit\". Available Theaters:");
			this.listTheaters();
			ID = reader.next();
		}
		
		if(ID.equals("exit")) {
			return false;
		}else {
			return true;
		}
	}
	/*
	 * test harness code
	 */
	public static void main(String[] args) {
		ShowtimeManager showtimes = new ShowtimeManager();
		Showtime show1 = new Showtime(new Date(), "Endgame", new ArrayList<Theater>(List.of(new Theater("1"), new Theater("2"), new Theater("3"))));
		Showtime show2 = new Showtime(new Date(), "Infinity War", new ArrayList<Theater>(List.of(new Theater("4"), new Theater("5"))));
		Showtime show3 = new Showtime(new Date(), "Breaking Bad", new ArrayList<Theater>(List.of(new Theater("6"))));
		Showtime show4 = new Showtime(new Date(), "The Simpsons", new ArrayList<Theater>(List.of(new Theater("7"), new Theater("8"), new Theater("9"))));
		showtimes.addShowtime(show1);
		showtimes.addShowtime(show2);
		showtimes.addShowtime(show3);
		showtimes.addShowtime(show4);
		showtimes.listShowtimes();
	}
}
