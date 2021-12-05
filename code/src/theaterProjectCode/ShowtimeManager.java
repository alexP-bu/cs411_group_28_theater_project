package theaterProjectCode;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowtimeManager {
	Scanner reader = new Scanner(System.in);
	private ArrayList<Showtime> showtimes;
	private TheaterManager theaterManager;
	private File showtimes_data = new File("showtimes.ser");
	/*
	 * constructors
	 */
	public ShowtimeManager(TheaterManager theaterManager) {
		this.showtimes = new ArrayList<Showtime>();
		this.theaterManager = theaterManager;
		try {
			if (!showtimes_data.exists()) {
				showtimes_data.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// import database file
		this.importShowtimes(showtimes_data);
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
				System.out.printf("%d: %s \n",i,showtime.toString());
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
	 * get showtime
	 */
	public Showtime getShowtime(int ID) {
		return showtimes.get(ID);
	}
	/*
	 * create new showtime. returns true on success, false on failure
	 */
	public void createShowtime() {
		ArrayList<Theater> showtimeTheaters = new ArrayList<Theater>();
		System.out.println("Enter showtime data:");
		System.out.println("Name: ");
		String name = reader.nextLine();
		System.out.println("Enter rating of showtime, 1 to 5");
		int rating = getNumInput();
		System.out.println("Enter popularity of show, from 1 to 100");
		int popularity = getNumInput();
		System.out.println("Enter category of show, in one word (use dashes for Sci-Fi etc)");
		String category = reader.nextLine();
		System.out.println("Enter price per ticket");
		double price = getPrice();
		System.out.println("Would you like to add which theaters the showtime will be in?");
		String yesno = getYesNo();
		if(yesno.equals("yes")) {
			if(theaterManager.getTheaters() == null || theaterManager.getTheaters().isEmpty()) {
				System.out.println("There are no registered theaters in the system. Please create a theater to add it.");
			}else {
				do {
				System.out.println("Add from these theaters:");
				theaterManager.listTheaters();
				System.out.println("Enter theater ID, or type \"exit\" to stop adding.");
				String ID = reader.nextLine();
				if(ID.equals("exit")) {
					break;
				}else {
					ID = theaterManager.get_valid_theater(ID);
					showtimeTheaters.add(theaterManager.getTheaters().get(ID));
				}
				} while(true);
			}
		}
		showtimes.add(new Showtime(new Date().createDate(),name, showtimeTheaters, popularity, category, price, rating));
		exportShowtimes(showtimes_data);
		System.out.println("Showtime successfully added!");
	}
	/*
	 * delete showtime
	 */
	public boolean deleteShowtime() {
		if(showtimes.isEmpty() || showtimes == null) {
			System.out.println("There are no showtimes to delete!");
			return false;
		}
		System.out.println("Select showtime from following list to delete: ");
		listShowtimes();
		int showtimeSelected = this.getValidShowtimeID();
		showtimes.remove(showtimeSelected);
		System.out.println("Showtime deleted successfully.");
		exportShowtimes(showtimes_data);
		return true;
	}
	/*
	 * view show time data
	 */
	public void viewShowtime() {
		if(showtimes.isEmpty() || showtimes == null) {
			System.out.println("There are no showtimes available!");
		}else {
			int selected = this.getValidShowtimeID(); 
			System.out.println(showtimes.get(selected).toString());
		}
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
	private double getPriceInput() {
		double input = 0;
		do {
			try {
				input = Double.parseDouble(reader.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println(e);
				}
				System.out.println("invalid input entered! try again FORMAT: 25.35");
			}while(true);
		return input;
	}
	/*
	 * get double input
	 */
	public double getPrice() {
		System.out.println("Enter dollars amount: ");
		return getPriceInput();
	}
	/*
	 * get valid show time by ID from user input
	 */
	public int getValidShowtimeID() {
		this.listShowtimes();
		System.out.println("Select showtime by index next to it.");
		int selected = getNumInput();
		while(selected > showtimes.size()) {
			System.out.println("Please enter a valid value!");
			selected = getNumInput();
		}
		return (selected - 1);
	}
	/*
	 * get yes/no from user input
	 */
	public String getYesNo() {
		System.out.println("yes/no");
		String input = reader.nextLine();
		do {
			if(input.equals("yes") || input.equals("no")) {
				break;
			}
			System.out.println("Please enter valid response:");
			input = reader.nextLine();
			} while(!(input.equals("yes") || (input.equals("no"))));
		return input;
	}
	/*
	 * check if there are no showtimes available
	 */
	public boolean isEmpty() {
		if(showtimes.isEmpty() || showtimes == null) {
			return true;
		}
		return false;
	}
	/*
	 * database methods for showtimes
	 */
	public void importShowtimes(File file) {
		try {
			ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file));
			while (true) {
				try {
					Showtime retreived = (Showtime) objIn.readObject();
					if(retreived != null) {
						showtimes.add(retreived);
					}else {
						break;
					}
				} catch (EOFException e) {
					objIn.close();
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Finished reading file.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Imported showtimes database file.");
	}

	/*
	 * export accounts from account list in current session to file
	 */
	public void exportShowtimes(File file) {
		clearShowtimesDatabaseFile();
		try {
			ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(file));
			try {
				for (Showtime showtime : showtimes) {
					objOut.writeObject(showtime);
				}
			} catch (Exception e) {
				objOut.close();
				System.out.println("Error exporting showtimes.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Database file updated.");
	}

	/*
	 * clear local theaters
	 */
	public void clearLocalShowtimes() {
		showtimes.clear();
		System.out.println("Finished clearing local showtimes data.");
	}

	/*
	 * clear database file
	 */
	public void clearShowtimesDatabaseFile() {
		try {
			FileOutputStream clear = new FileOutputStream(showtimes_data);
			clear.close();
			System.out.println("Showtimes database file cleared!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return showtimes_data;
	}
	/*
	 * test harness code
	 */
	public static void main(String[] args) {
		ShowtimeManager showtimes = new ShowtimeManager(new TheaterManager());
		Showtime show1 = new Showtime(new Date(), "Endgame", new ArrayList<Theater>(List.of(new Theater("1"), new Theater("2"), new Theater("3"))), 1 ,"R", 21.00,1);
		Showtime show2 = new Showtime(new Date(), "Infinity War", new ArrayList<Theater>(List.of(new Theater("4"), new Theater("5"))), 1 ,"R", 21.00,1);
		Showtime show3 = new Showtime(new Date(), "Breaking Bad", new ArrayList<Theater>(List.of(new Theater("6"))), 1 ,"R", 21.00,1);
		Showtime show4 = new Showtime(new Date(), "The Simpsons", new ArrayList<Theater>(List.of(new Theater("7"), new Theater("8"), new Theater("9"))), 1 ,"R", 21.00,1);
		showtimes.addShowtime(show1);
		showtimes.addShowtime(show2);
		showtimes.addShowtime(show3);
		showtimes.addShowtime(show4);
		showtimes.listShowtimes();
	}
}
