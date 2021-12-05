package theaterProjectCode;

import java.util.HashSet;

public class CommandManager {

	private HashSet<String> commandList;
	private AccountManager accountManager;
	private ShowtimeManager showtimeManager;
	private TheaterManager theaterManager;

	/*
	 * constructors
	 */
	public CommandManager() {
		commandList = new HashSet<String>();
		accountManager = new AccountManager();
		theaterManager = new TheaterManager();
		showtimeManager = new ShowtimeManager(theaterManager);
	}

	public CommandManager(String[] commands) {
		commandList = new HashSet<String>();
		for (String command : commands) {
			commandList.add(command);
		}
		accountManager = new AccountManager();
		theaterManager = new TheaterManager();
		showtimeManager = new ShowtimeManager(theaterManager);
	}

	/*
	 * validates if a command is valid for this system
	 */
	public boolean validateCommand(String keyword) {
		if (commandList.contains(keyword)) {
			return true;
		}
		return false;
	}

	/*
	 * print list of valid commands
	 */
	public void listCommands() {
		System.out.println(commandList.toString());
	}

	/*
	 * delete command from system
	 */
	public void deleteCommand(String keyword) {
		commandList.remove(keyword);
	}

	/*
	 * add a command to system
	 */
	public void addCommand(String keyword) {
		commandList.add(keyword);
	}

	public void returning() {
		System.out.println("Returning to main menu...");
		user_interface.wait(1);
		runCommand("main");
	}

	/*
	 * run main run case
	 */
	public void runCommand(String command) {
		switch (command) {
		case "login":
			if (accountManager.isLoggedIn()) {
				System.out.println("Logging out of current account..");
				accountManager.logoutAccount();
			}
			if (accountManager.loginAccount()) {
				System.out.printf("Welcome back, %s\n", accountManager.getLoggedInAccount().getUsername());
				System.out.print(accountManager.getLoggedInAccount().toString());
			} else {
				System.out.println("Invalid account username/password.");
				returning();
			}
			break;
		case "logout":
			if (!accountManager.logoutAccount()) {
				System.out.println("Would you like to login?");
				user_interface.wait(1);
				System.out.println("Or would you like to return to main menu?");
				break;
			}
			break;
		case "newAccount":
			if (accountManager.isLoggedIn()) {
				System.out.printf("Please logout of the current %s account before creating a new one.",
						accountManager.getLoggedInAccount().getUsername());
				break;
			}
			if (accountManager.createAccount("customer")) {
				System.out.println("Customer account sucessfully created");
				System.out.println("Please login to access your account");
				System.out.println("...");
			} else {
				System.out.println("Error creating your account. Please contact an employee.");
				returning();
			}
			break;
		case "newAdmin":
			if (accountManager.createAccount("administrator")) {
				break;
			} else {
				System.out.println("Account creation failed.");
				break;
			}
		case "newEmployee":
			if (accountManager.createAccount("employee")) {
				System.out.println("Finished creating employee account.");
			}
			break;
		case "deleteAccount":
			if (accountManager.deleteAccount()) {
				break;
			} else {
				System.out.println("Failed deleting account.");
				returning();
			}
			break;
		case "viewAccount":
			if (accountManager.isLoggedIn()) {
				System.out.println(accountManager.getLoggedInAccount().toString());
			} else {
				System.out.println("Please log in to view your account!");
			}
			break;
		case "listAccounts":
			accountManager.printAccountList();
			break;
		case "clearAccountsData":
			accountManager.clearAccountList();
			accountManager.clearAccountDatabase();
			System.out.println("Successfully finished clearing accounts and data.");
			break;
		case "updateAccountsData":
			try {
				accountManager.exportAccounts(accountManager.getDatabaseFile());
			} catch (Exception e) {
				System.out.println("Error exporting database file");
				e.printStackTrace();
				break;
			}
			System.out.println("Successfully finished updating database file.");
			break;
		case "listShowtimes":
			showtimeManager.listShowtimes();
			break;
		case "newTheater":
			theaterManager.createTheater();
			break;
		case "deleteTheater":
			if (theaterManager.deleteTheater()) {
				System.out.println("New theater list:");
				theaterManager.listTheaters();
			} else {
				System.out.println(
						"Failed to delete theater. No theaters in system to delete, or invalid theater entered.");
			}
			break;
		case "listTheaters":
			theaterManager.listTheaters();
			break;
		case "viewTheater":
			theaterManager.viewTheater();
			break;
		case "newShowtime":
			showtimeManager.createShowtime();
			break;
		case "viewShowtime":
			showtimeManager.viewShowtime();
			break;
		case "deleteShowtime":
			showtimeManager.deleteShowtime();
			break;
		case "main":
			user_interface.printInstructions();
			System.out.println("Enter \"exit\" to exit the program");
			break;
		case "purchaseTicket":
			PurchasingManager purchasingManager = new PurchasingManager(accountManager, theaterManager,
					showtimeManager);
			if (purchasingManager.purchaseTicket()) {
				System.out.println("Successfully purchased ticket");
			} else {
				System.out.println("Ticket purchase failed.");
			}
			break;
		case "clearTheatersData":
			theaterManager.clearTheatersDatabaseFile();
			break;
		case "updateTheatersData":
			try {
				theaterManager.importTheaters(theaterManager.getFile());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "clearShowtimesData":
			showtimeManager.clearShowtimesDatabaseFile();
			break;
		case "updateShowtimesData":
			try {
			showtimeManager.importShowtimes(showtimeManager.getFile());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
}
