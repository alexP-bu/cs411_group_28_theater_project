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
	/*
	 * default blank command manager
	 */
	public CommandManager() {
		commandList = new HashSet<String>();
		accountManager = new AccountManager();
		theaterManager = new TheaterManager();
		showtimeManager = new ShowtimeManager(theaterManager);
	}
	/*
	 * initialize commandManager with a list of commands
	 */
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
	 * method to run commands
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
			}
			returning();
			break;
		case "logout":
			if (!accountManager.logoutAccount()) {
				System.out.println("No account logged in!");
			}
			returning();
			break;
		case "newAccount":
			if (accountManager.isLoggedIn()) {
				System.out.printf("Please logout of the current %s account before creating a new one.",
						accountManager.getLoggedInAccount().getUsername());
				returning();
				break;
			}
			if (accountManager.createAccount("customer")) {
				System.out.println("Customer account sucessfully created");
				System.out.println("Please login to access your account");
				System.out.println("...");
			} else {
				System.out.println("Error creating your account.");
			}
			returning();
			break;
		case "newAdmin":
			if (!accountManager.createAccount("administrator")) {
				System.out.println("Account creation failed.");
			}else {
				System.out.println("ADMIN ACCOUNT CREATED");
				System.out.printf("ACCESS GRANTED TO %s", accountManager.getLoggedInAccount().getUsername());
			}
			returning();
			break;
		case "newEmployee":
			if(accountManager.isLoggedIn()) {
				if(!accountManager.getLoggedInAccount().getType().equals("administrator")) {
					System.out.println("Invalid permissions! Please contact system administrator!");
				}else {
					if (accountManager.createAccount("employee")) {
						System.out.println("Finished creating employee account.");
						System.out.printf("Welcome to the Team: %s\n", accountManager.getLoggedInAccount().getUsername());
					}else {
						System.out.println("Account creation failed.");
					}
				}
			}else {
				System.out.println("Please log in!");
			}
			returning();
			break;
		case "deleteAccount":
			if (!accountManager.deleteAccount()) {
				System.out.println("Failed deleting account.");
			}
			returning();
			break;
		case "viewAccount":
			if (accountManager.isLoggedIn()) {
				System.out.println(accountManager.getLoggedInAccount().toString());
			} else {
				System.out.println("Please log in to view your account!");
			}
			returning();
			break;
		case "listAccounts":
			accountManager.printAccountList();
			returning();
			break;
		case "clearAccountsData":
			accountManager.clearAccountList();
			accountManager.clearAccountDatabase();
			System.out.println("Successfully finished clearing accounts and data.");
			returning();
			break;
		case "updateAccountsData":
			accountManager.exportAccounts(accountManager.getDatabaseFile());
			returning();
			break;
		case "listShowtimes":
			showtimeManager.listShowtimes();
			returning();
			break;
		case "newTheater":
			if(accountManager.isLoggedIn()) {
				if(accountManager.getLoggedInAccount().getType().equals("employee")) {
					theaterManager.createTheater();
				}else {
					System.out.println("Insufficient permissions! Please contact an employee.");
				}
			}else {
				System.out.println("Please log in!");
			}
			returning();
			break;
		case "deleteTheater":
			if(accountManager.isLoggedIn()) {
				if(accountManager.getLoggedInAccount().getType().equals("employee")) {
					if (theaterManager.deleteTheater()) {
						System.out.println("New theater list:");
						theaterManager.listTheaters();
					} else {
						System.out.println(
								"Failed to delete theater. No theaters in system to delete, or invalid theater entered.");
					}
				}else {
					System.out.println("Insufficient permissions! Please contact an employee.");
				}
			}else {
				System.out.println("Please log in!");
			}
			returning();
			break;
		case "listTheaters":
			theaterManager.listTheaters();
			returning();
			break;
		case "viewTheater":
			theaterManager.viewTheater();
			returning();
			break;
		case "newShowtime":
			if(accountManager.isLoggedIn()) {
				if(accountManager.getLoggedInAccount().getType().equals("employee")) {
					showtimeManager.createShowtime();
				} else {
					System.out.println("Insufficient permissions! Please contact an employee.");
				}
			}else {
				System.out.println("Please log in!");
			}
			returning();
			break;
		case "viewShowtime":
			showtimeManager.viewShowtime();
			returning();
			break;
		case "deleteShowtime":
			if(accountManager.isLoggedIn()) {
				if(accountManager.getLoggedInAccount().getType().equals("employee")) {
					if(!showtimeManager.deleteShowtime()) {
						System.out.println("Error deleting showtime!");
					}
				}else {
					System.out.println("Insufficient permissions! Please contact an employee.");
				}
			}else {
				System.out.println("Please log in!");
			}
			returning();
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
			returning();
			break;
		case "addBalance":
			if (accountManager.addBalance()) {
				System.out.println("Successfully added to balance!");
				System.out.printf("Balance is now: $%s\n", accountManager.getLoggedInAccount().getBalance());
			} else {
				System.out.println("Failed to add to Balance");
			}
			break;
		case "clearTheatersData":
			theaterManager.clearTheatersDatabaseFile();
			theaterManager.clearLocalTheaters();
			returning();
			break;
		case "updateTheatersData":
			theaterManager.importTheaters(theaterManager.getFile());
			returning();
			break;
		case "clearShowtimesData":
			showtimeManager.clearShowtimesDatabaseFile();
			showtimeManager.clearLocalShowtimes();
			returning();
			break;
		case "updateShowtimesData":
			showtimeManager.importShowtimes(showtimeManager.getFile());
			returning();
			break;
		}
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
	 * return to main menu
	 */
	public void returning() {
		System.out.println("Returning to main menu...");
		user_interface.wait(1);
		runCommand("main");
	}
}
