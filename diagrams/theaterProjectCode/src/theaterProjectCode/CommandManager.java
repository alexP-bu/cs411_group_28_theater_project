package theaterProjectCode;
import java.util.HashSet;

public class CommandManager {
	
	private HashSet<String> commandList;
	private AccountManager accountManager;
	private ShowtimeManager showtimeManager;
	
	/*
	 * constructors
	 */
	
	public CommandManager() {
		commandList = new HashSet<String>();
		accountManager = new AccountManager();
		showtimeManager = new ShowtimeManager();
	}
	
	public CommandManager(String[] commands) {
		commandList = new HashSet<String>();
		for(String command : commands) {
			commandList.add(command);
		}
		accountManager = new AccountManager();
		showtimeManager = new ShowtimeManager();
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
					System.out.println("Login Failed. Internal error.");
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
					
				}
			case "deleteAccount":
				if(accountManager.deleteAccount()) {
					break;
				}else {
					System.out.println("Error deleting account. Please contact an employee.");
				}
			case "viewAccount":
				if(accountManager.isLoggedIn()) {
					System.out.println(accountManager.getLoggedInAccount().toString());
				}else {
					System.out.println("Please log in to view your account!");
				}
			case "listAccounts":
				accountManager.printAccountList();
				break;
			case "clearAccountsData":
				System.out.println("Clearing account database and local files...");
				accountManager.clearAccountList();
				accountManager.clearDatabase();
				System.out.println("Successfully cleared accounts and data.");
				break;
			case "updateAccountDatabase":
				System.out.println("Updating account database...");
				try {
					accountManager.exportAccounts(accountManager.getDatabaseFile(),accountManager.getLocalAccountList());
				} catch (Exception e) {
					System.out.println("Error exporting database file");
					e.printStackTrace();
					break;
				}
				System.out.println("Successfully updated database file.");
				break;
			case "listShowtimes":
				showtimeManager.listShowtimes();
				break;
		}
	}
}
