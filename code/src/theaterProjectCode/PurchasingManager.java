package theaterProjectCode;

import java.util.Scanner;

public class PurchasingManager {
	
	private AccountManager accountManager;
	private TheaterManager theaterManager;
	private ShowtimeManager showtimeManager;
	private Scanner reader = new Scanner(System.in);
	
	/*
	 * 
	 */
	public PurchasingManager(AccountManager am, TheaterManager tm, ShowtimeManager sm) {
		this.setAccountManager(am);
		this.setTheaterManager(tm);
		this.setShowtimeManager(sm);
	}
	/*
	 * getters and setters
	 */
	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public TheaterManager getTheaterManager() {
		return theaterManager;
	}

	public void setTheaterManager(TheaterManager theaterManager) {
		this.theaterManager = theaterManager;
	}

	public ShowtimeManager getShowtimeManager() {
		return showtimeManager;
	}

	public void setShowtimeManager(ShowtimeManager showtimeManager) {
		this.showtimeManager = showtimeManager;
	}
	/*
	 * methods
	 */
	public boolean purchaseTicket() {
		if(accountManager.isLoggedIn()) {
			System.out.printf("You are logged in as user: %s \n", accountManager.getLoggedInAccount().getUsername());
			System.out.println("Would you like to purchase a ticket to this account? yes/no");
			String input = showtimeManager.getYesNo();
			if(input.equals("no")) {
				System.out.println("Logging out...");
				return false;
			}else {
				System.out.println("Please select a showtime to purchase your ticket for:");
				if(showtimeManager.isEmpty()) {
					System.out.println("There are no showtimes available! Exiting...");
					return false;
				}
				showtimeManager.listShowtimes();
				Showtime showtimeSelected = showtimeManager.getShowtime(showtimeManager.getValidShowtimeID());
				Date datePurchased = new Date();
				Account accountSelected = accountManager.getLoggedInAccount();
				System.out.println("Select a theater by ID to reserve a seat in:");
				if(showtimeSelected.getTheaters() == null || showtimeSelected.getTheaters().isEmpty() || (!showtimeSelected.emptySeatExists())) {
					System.out.println("No unreserved seats available! Exiting....");
					return false;
				}
				showtimeSelected.listUnreservedTheaters();
				String theaterSelected = reader.nextLine();
				do {
					if(showtimeSelected.isListed(theaterSelected) && (!showtimeSelected.getTheaterByID(theaterSelected).isFull())) {
						break;
					}
					System.out.println("Theater is full or invalid. Please enter a different theater from the above list.");
				}while(true);
				System.out.println("Select a seat to reserve in this theater");
				theaterManager.viewTheater(theaterSelected);
				//Seat seatSelected = 
				//Generate ticket and add to account
				//Ticket ticketOut = new Ticket(showtimeSelected,seatSelected,showtimeSelected.getPrice(),new Date(),theaterSelected);
				//accountSelected.addTicket(ticketOut);
			}
		}else {
			System.out.println("Would you like to log in to an account to purchase your ticket?");
			String input = showtimeManager.getYesNo();
			if(input.equals("yes")) {
				System.out.println("Please log in and make your purchase.");
				return false;
			}
		}
		return false;
	}
}
