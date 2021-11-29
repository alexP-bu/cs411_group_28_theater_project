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
		for(String command : commands) {
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
	 * print list valid commands 
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
	
	public void runCommand(String command) {
		switch (command) {
			case "login": 
				if(accountManager.isLoggedIn()) {
					System.out.println("Logging out of current account..");
				}
				accountManager.logoutAccount();
				if(accountManager.loginAccount()) {
					break;
				}else {
					System.out.println("Login Failed. Please try again.");
					break;
				}
			case "logout": 
				if(accountManager.logoutAccount()) {
					break;
				}
			case "newAccount":
				if (accountManager.createAccount("customer")) {
					break;
				}else {
					System.out.println("Error creating your account. Please contact an employee.");
					break;
				}
			case "newAdmin":
				if(accountManager.createAccount("administrator")) {
					break;
				}else {
					System.out.println("Account creation failed.");
					break;
				}
			case "newEmployee":
				if(accountManager.createAccount("employee")) {
					System.out.println("Finished creating employee account.");
				}
				break;
			case "deleteAccount":
				if(accountManager.deleteAccount()) {
					break;
				}else {
					System.out.println("Failed deleting account.");
				}
				break;
			case "viewAccount":
				if(accountManager.isLoggedIn()) {
					System.out.println(accountManager.getLoggedInAccount().toString());
				}else {
					System.out.println("Please log in to view your account!");
				}
				break;
			case "listAccounts":
				accountManager.printAccountList();
				break;
			case "clearAccountsData":
				accountManager.clearAccountList();
				accountManager.clearDatabase();
				System.out.println("Successfully finished clearing accounts and data.");
				break;
			case "updateAccountDatabase":
				try {
					accountManager.exportAccounts(accountManager.getDatabaseFile(),accountManager.getLocalAccountList());
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
				if(theaterManager.deleteTheater()) {
					System.out.println("New theater list:");
					theaterManager.listTheaters();
				}else {
					System.out.println("Failed to delete theater. No theaters in system to delete, or invalid theater entered.");
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
		}
	}
}