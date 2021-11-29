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
				i++;
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
				ID = theaterManager.get_valid_theater(ID);
				show.addTheater(theaterManager.getTheaters().get(ID));
			}
		}
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
		show.setDate(new Date(month,day,year,hour,minute));
		System.out.println("Enter rating of showtime, 1 to 5");
		int rating = getNumInput();
		show.setRating(rating);
		System.out.println("Enter popularity of show, from 1 to 100");
		int popularity = getNumInput();
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
		int selected = getValidShowTime(); 
		System.out.println(showtimes.get(selected - 1).toString());
	}
	/*
	 * get valid number input from user
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
	/*
	 * seat reservation code
	 */
	public boolean reserveSeat(Account account) {
		System.out.println("Enter showtime which you would like to reserve a seat at by selecting the index next to it: ");
		this.listShowtimes();
		int showtimeSelected = getValidShowTime();
		System.out.println("Enter row which you want to reserve a seat in (the rows are uppercase characters)");
		char row = reader.nextLine().charAt(0);
		System.out.println("Enter column which you want to reserve a seat in (number)");
		int col = getNumInput();
		if(this.showtimes.get(showtimeSelected).getTheaters() == null || this.showtimes.get(showtimeSelected).getTheaters().isEmpty()) {
			System.out.println("No theaters are showing this showtime! Please contact theater staff.");
			return false;
		}
		System.out.println("Available theaters: ");
		this.showtimes.get(showtimeSelected).listTheaters();
		System.out.println("Please enter theater ID from the list:");
		String theaterID = reader.nextLine();
		if(!(this.showtimes.get(showtimeSelected).isListed(theaterID))){
			System.out.println("Please enter a valid theater number!");
			theaterID = reader.nextLine();
		}
		if(account.getUsername().equals("guest")) {
			System.out.println("You are not currently logged in. Reservation will be made as guest. Confirm reservation?");
			if(getYesNo().equals("yes")) {
				if(theaterManager.reserveSeat(row, col, theaterID, account)) {
					System.out.println("Seat successfully reserved!");
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	/*
	 * get valid showtime by ID from user input
	 */
	public int getValidShowTime() {
		this.listShowtimes();
		System.out.println("Select showtime by index next to it.");
		int selected = getNumInput();
		while(selected > showtimes.size()) {
			System.out.println("Please enter a valid value!");
			selected = getNumInput();
		}
		return selected;
	}
	/*
	 * get yes/no from user input
	 */
	public String getYesNo() {
		System.out.println("yes/no");
		String input = reader.nextLine();
		do {
			System.out.println("Please enter valid response:");
			input = reader.nextLine();
			} while(input.equals("yes") || input.equals("no"));
		return input;
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