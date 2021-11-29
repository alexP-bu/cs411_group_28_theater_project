package theaterProjectCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowtimeManager {
	Scanner reader = new Scanner(System.in);
	private ArrayList<Showtime> showtimes;
	private TheaterManager theaterManager;
	/*
	 * constructors
	 */
	public ShowtimeManager(TheaterManager theaterManager) {
		this.showtimes = new ArrayList<Showtime>();
		this.theaterManager = theaterManager;
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
	 * create new showtime. returns true on success, false on failure
	 */
	public void createShowtime() {
		Showtime show = new Showtime();
		System.out.println("Enter showtime name:");
		show.setName(reader.nextLine());
		System.out.println("Would you like to add which theaters the showtime will be in?");
		System.out.println("yes/no");
		if(reader.nextLine().equals("yes")) {
			if(theaterManager.getTheaters() == null || theaterManager.getTheaters().isEmpty()) {
				System.out.println("There are no registered theaters in the system. Please create a theater to add it.");
			}else {
				System.out.println("Available theaters:");
				theaterManager.listTheaters();
				System.out.println("Enter theater ID");
				String ID = reader.nextLine();
				if(theaterManager.is_theater_valid(ID)) {
					show.addTheater(theaterManager.getTheaters().get(ID));
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
	 * test harness code
	 */
	public static void main(String[] args) {
		ShowtimeManager showtimes = new ShowtimeManager(new TheaterManager());
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
