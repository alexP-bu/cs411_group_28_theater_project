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
		this.theaters.put(theater.getTheaterID(), theater);
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
		if(this.is_theater_valid(ID)) {
			this.theaters.remove(ID);
			System.out.println("Successfully deleted theater from system.");
			return true;
		}
		return false;
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
			System.out.println("Successfully added seating chart configuration.");
		}
		this.addTheater(theater);
		System.out.println("Successfully added theater configuration to system.");
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
	 * test if theater ID is valid. If it is, return true. Else, return false.
	 */
	public boolean is_theater_valid(String ID) {
		while(!theaters.containsKey(ID)) {
			if(ID.equals("exit")) {
				break;
			}
			System.out.println("Please enter a valid theater ID or or type \"exit\". Available Theaters:");
			this.listTheaters();
			ID = reader.next();
		}
		
		if(ID.equals("exit")) {
			System.out.println("invalid theater ID entered...");
			return false;
		}else {
			System.out.println("valid theater ID entered...");
			return true;
		}
	}
}
