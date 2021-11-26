package theater_project_code;
import java.util.Scanner;
import java.util.HashSet;

public class CommandManager {
	
	private HashSet<String> commandList;
	private AccountManager accountManager;
	
	/*
	 * constructors
	 */
	
	public CommandManager() {
		commandList = new HashSet<String>();
		accountManager = new AccountManager();
	}
	
	public CommandManager(String[] commands) {
		commandList = new HashSet<String>();
		for(String command : commands) {
			commandList.add(command);
		}
		accountManager = new AccountManager();
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
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String username;
		String password;
		switch (command) {
			case "login": 
				if(accountManager.isLoggedIn()) {
					System.out.println("Logging out of current account..");
				}
				accountManager.logoutAccount();
				System.out.println("Please enter your username: ");
				username = reader.nextLine();
				System.out.println("Please enter your password: ");
				password = reader.nextLine();
				if(accountManager.loginAccount(username, password)) {
					System.out.printf("Welcome back, %s\n",username);
					System.out.print(accountManager.getAccount().toString());
					break;
				}else {
					System.out.println("Login Failed. Invalid username/password.");
					break;
				}
			case "logout": {
				if(accountManager.logoutAccount()) {
					System.out.println("Logout successful.");
					break;
				}
			}
			case "new":
				System.out.println("Please enter a username for your new account: ");
				username = reader.nextLine();
				System.out.println("Please enter a password for your new account: ");
				password = reader.nextLine();
				if (accountManager.createCustomerAccount(username, password)) {
					System.out.println("Account successfully created! You are now logged in.");
					break;
				}else {
					System.out.println("Error creating your account. Please contact an employee.");
					break;
				}
			case "delete":
				System.out.println("Please enter a username for account to delete: ");
				username = reader.nextLine();
				if(accountManager.deleteAccount(username)) {
					System.out.println("Account successfully deleted.");
					break;
				}else {
					System.out.println("Error deleting account. Please contact an employee.");
				}
			case "listAccounts":
				accountManager.printAccountList();
				break;
			case "newAdmin":
				System.out.println("Please enter a username for your new account: ");
				username = reader.nextLine();
				System.out.println("Please enter a password for your new account: ");
				password = reader.nextLine();
				if(accountManager.createAdminAccount(username, password)) {
					System.out.println("Successfully created admin account.");
					break;
				}else {
					System.out.println("Account creation failed.");
					break;
				}
			case "clearData":
				System.out.println("Clearing account database and local files...");
				accountManager.clearAccountList();
				accountManager.clearDatabase();
				System.out.println("Successfully cleared accounts and data.");
				break;
		}
	}
}
