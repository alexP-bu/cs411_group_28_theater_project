package theaterProjectCode;

import java.util.HashMap;
import java.util.Scanner;

public class TheaterManager {
	private HashMap<String,Theater> theaters;
	private int NUM_THEATERS;
	private Scanner reader = new Scanner(System.in);
	
	/*
	 * constructors
	 */
	public TheaterManager() {
		this.setTheaters(new HashMap<String,Theater>());
		this.setNUM_THEATERS(0);
	}
	/*
	 * getters/setters
	 */
	public HashMap<String,Theater> getTheaters() {
		return theaters;
	}

	public void setTheaters(HashMap<String,Theater> theaters) {
		this.theaters = theaters;
	}

	public int getNUM_THEATERS() {
		return NUM_THEATERS;
	}

	public void setNUM_THEATERS(int nUM_THEATERS) {
		NUM_THEATERS = nUM_THEATERS;
	}
	/*
	 * misc methods
	 */
	/*
	 * list theaters in local system
	 */
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
	 * add theater to local system
	 */
	public void addTheater(Theater theater) {
		String ID = theater.getTheaterID();
		this.theaters.put(ID, theater);
		this.setNUM_THEATERS(getNUM_THEATERS() + 1);
		System.out.println("Theater successfully added to local system!");
	}
	/*
	 * delete theater from local system 
	 */
	public boolean deleteTheater(Theater theater) {
		if(theaters.containsKey(theater.getTheaterID())) {
			this.theaters.remove(theater.getTheaterID());
			System.out.println("Successfully deleted theater from system.");
			return true;
		}else {
			System.out.println("Error deleting theater, theater not found!");
			return false;
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
		ID = get_valid_theater(ID);
		this.theaters.remove(ID);
		System.out.println("Successfully deleted theater from system.");
		return true;
	}
	/*
	 * create custom theater and add it to the showtime manager
	 */
	public void createTheater() {
		Theater theater = new Theater();
		System.out.println("Enter theater ID:");
		String ID = reader.nextLine();
		while(theaters.containsKey(theater.getTheaterID()) || (ID == null) || (ID.isBlank())) {
			System.out.println("Please enter a different number or ID.");
			ID = reader.nextLine();
		}
		theater.setTheaterID(ID);
		System.out.printf("Use the default seating chart for theater %s? \n", theater.getTheaterID());
		System.out.println("yes/no");
		if(reader.nextLine().equals("yes")) {
			theater.setSeatingChart(new SeatingChart());
		}else {
			System.out.println("Enter number of seating chart rows: ");
			int rows = getNumInput();
			System.out.println("Enter number of seating chart columns: ");
			int cols = getNumInput();
			theater.setSeatingChart(new SeatingChart(rows,cols));
			System.out.println("Successfully added seating chart configuration.");
		}
		this.addTheater(theater);
		System.out.println("Successfully added theater configuration to system.");
	}
	/*
	 * view theater information 
	 */
	public void viewTheater() {
		if(this.theaters.isEmpty() || (this.theaters == null)) {
			System.out.println("No theaters in local system, please add a theater");
		}else {
			System.out.println("Please enter theater ID:");
			String ID = reader.nextLine();
			ID = this.get_valid_theater(ID);
			System.out.println(this.theaters.get(ID).toString());
		}
	}
	/*
	 * test if theater ID is valid. Keep going until user returns valid ID.
	 */
	public String get_valid_theater(String ID) {
		while(!theaters.containsKey(ID)) {
			System.out.println("Please enter a valid theater ID. Available Theaters:");
			this.listTheaters();
			ID = reader.next();
		}
		System.out.println("valid theater ID entered...");
		return ID;
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
	 * test harness
	 */
	public static void main(String[] args) {
		TheaterManager theaterManager = new TheaterManager();
		Theater theater = new Theater();
		theater.setTheaterID("3");
		theater.setSeatingChart(new SeatingChart(3,4));
		System.out.println(theater.toString());
		theaterManager.theaters.put(theater.getTheaterID(), theater);
		System.out.println(theaterManager.getTheaters().toString());
		System.out.println(theaterManager.theaters.get(theater.getTheaterID()));
		theaterManager.viewTheater();
	}
}
