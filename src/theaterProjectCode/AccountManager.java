package theaterProjectCode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;

public class AccountManager {
	
	private HashMap<String, Account> accountList;
	private Account loggedIn;
	private File accountsFile;
	private Scanner reader = new Scanner(System.in); 
	
	/*
	 * initializing constructor for the account manager
	 * get list of user names and passwords from database file and import them into list
	 * 
	 */
	public AccountManager() {
		this.accountsFile = new File("accounts.ser");
		if(!accountsFile.exists()) {
			try {
				accountsFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.loggedIn = null;
		//read accounts from file and import them into accounts list
		try {
			this.accountList = new HashMap<String,Account>(importAccounts(accountsFile));
		} catch (ClassNotFoundException e) {
			System.out.println("error importing accounts file.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Finished importing accounts file. There may have been an error during processing.");
		}
	}	
	/*
	 * login account using user name and password.
	 * return true on successful login, throws exception if an error occurred.
	 */	
	public boolean loginAccount() throws IllegalArgumentException {
		String username = this.getUsernameInput();
		String password = this.getPasswordInput();
		//attempt an account login
		if(accountList.containsKey(username)) {
			if(accountList.get(username).getPassword().equals(password)) {
				if(loggedIn == null) {
					//if no other account logged in, login account
					loggedIn = accountList.get(username);
					System.out.println("Account successfully logged in!");
					System.out.printf("Welcome back, %s\n",this.getLoggedInAccount().getUsername());
					System.out.print(this.getLoggedInAccount().toString());
					return true;
				} else {
					//another account is logged in already
					System.out.println("Please log out before logging in a new account!");
					return false;
				}
			}else {
				System.out.println("Invalid account username/password.");
				return false;
			}
		}
		return false;
	}
	/*
	 * logout any account. returns true on successful logout
	 */	
	public boolean logoutAccount() {
		loggedIn = null;
		System.out.println("Logout successful.");
		return true;
	}
	/*
	 * check if account is logged in. returns true if it is, false if not
	 */
	public boolean isLoggedIn() {
		if(loggedIn == null) {
			return false;
		}else {
			return true;
		}
	}	
	/*
	 * method to add new account
	 * returns true on successful account creation
	 * throws exception on account creation failure
	 * 
	 */
	public boolean createAccount(String type) {
		String username = this.getUsernameInput();
		String password = this.getPasswordInput();
		if(accountList.containsKey(username)) {
			System.out.println("Username already exists, please try again!");
			return false;
		}
		Account account = new Account(username, password, type);
		//add key to local account list
		accountList.put(username, account);
		//clear current account database file and export new one
		clearDatabase();
		try {
			exportAccounts(accountsFile, accountList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Account successfully created! Please log in.");
		return true;
	}
	/*
	 * method to create administrator account
	 */
	public boolean createAdminAccount() {
		if(createAccount("administrator")) {
			clearDatabase();
			try {
				exportAccounts(accountsFile, accountList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	/*
	 * method to return logged in account
	 */
	public Account getLoggedInAccount() {
		if (loggedIn != null) {
			return loggedIn;
		}else {
			return null;
		}
	}
	/*
	 * method to delete account by user name
	 * account must be logged in to delete itself
	 * administrator account must be logged in order to delete it
	 * returns true on successful deletion, false otherwise
	 */
	public boolean deleteAccount() throws IllegalArgumentException {
		String username = this.getUsernameInput();
		if(loggedIn != null) {
			if(loggedIn.getType().equals("administrator") || loggedIn.getUsername().equals(username)) {
				System.out.printf("Are you sure you want to delete %s's account?\n", username);
				System.out.println("Please re-enter account username to confirm: ");
				if (!username.equals(this.getUsernameInput())) {
					System.out.println("Account failed to delete!");
					return false;
				}
				//delete account from accountList
				accountList.remove(username);
				//export new database file with deleted account
				try {
					exportAccounts(accountsFile, accountList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.printf("%s's account has been successfully deleted.\n", username);
				return true;
			}else {
				System.out.println("Insufficient permissions to delete account!");
			}
		}else {
			System.out.println("Please log in to delete account!");
		}
		return false;
	}
	/*
	 * print local accounts
	 */
	public void printAccountList() {
		if(accountList == null || accountList.isEmpty()) {
			System.out.println("There are no registered users on this system.");
		} else {
			System.out.println("Accounts List: ");
			for(Map.Entry<String, Account> entry : accountList.entrySet()) {
				System.out.println(entry.getKey());
			}
			System.out.println("Accounts list done printing.");
		}
	}
	/*
	 * method to read accounts from accounts list using FileInputStreams
	 */	
	public static HashMap<String,Account> importAccounts(File file) throws IOException, ClassNotFoundException {
		HashMap<String,Account> output = new HashMap<String,Account>();
		if (file.length() == 0) {
			return output;
		}
		ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
		while(true) {
			try {
				Account get = (Account) reader.readObject();
				output.put(get.getUsername(),get);
			}catch (EOFException e) {
				break;
			}
		}
		reader.close();
		return output;
	}
	/*
	 * method to export accounts from account list in current session to file
	 */
	public void exportAccounts(File file, HashMap<String,Account> accountList) throws IOException {
		try {
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
			for(Map.Entry<String, Account> entry : accountList.entrySet()) {
				Account account = entry.getValue();
				writer.writeObject(account);
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			throw e;
		}
		System.out.println("Database file updated.");
	}
	/*
	 * method to database clear file
	 */
	public void clearDatabase() {
		try {
			new FileOutputStream(accountsFile).close();
		}catch (Exception e) {
			System.out.println("file not found!");
			e.printStackTrace();
		}
		System.out.println("Database file cleared.");
	}
	/*
	 * method to clear local account list
	 */
	public void clearAccountList() {
		if(accountList != null) {
			accountList.clear();
			System.out.println("Finished clearing local account data.");
		}else {
			System.out.println("There are no accounts stored locally.");
		}
	}
	/*
	 * account list getter
	 */
	public HashMap<String, Account> getLocalAccountList(){
		return accountList;
	}
	/*
	 * accounts file getter
	 */
	public File getDatabaseFile() {
		return accountsFile;
	}
	
	/*
	 * private methods for getting user input
	 */
	
	private String getUsernameInput() {
		System.out.println("Enter username: ");
		String username; 
		username = reader.nextLine();
		return username;
	}
	
	private String getPasswordInput() {
		System.out.println("Enter password: ");
		String password;
		password = reader.nextLine();
		return password;
	}
	/*
	 * test harness code
	 *
	public static void main(String[] args) {
		//make a new account manager
		AccountManager accountManager = new AccountManager();
		//clear its database file
		accountManager.clearDatabase();
		accountManager.clearAccountList();
		//create two customer accounts
		accountManager.createAccount("Alex", "testpassword");
		accountManager.createAccount("Alex2", "testpassword2");
		//print account list
		accountManager.printAccountList();
		//login an account
		accountManager.loginAccount("Alex","testpassword");
		System.out.println("Is Alex logged in?");
		System.out.println(accountManager.isLoggedIn("Alex"));
		//logout account
		accountManager.logoutAccount();
		System.out.println("Is Alex logged in now?");
		System.out.println(accountManager.isLoggedIn("Alex"));
		//test deleting account
		System.out.println("Test deleting an account:");
		System.out.println("Account list before:");
		accountManager.printAccountList();
		System.out.println("If not logged in, deleted?:");
		accountManager.deleteAccount("Alex2");
		accountManager.printAccountList();
		//login then delete
		accountManager.loginAccount("Alex2", "testpassword2");
		accountManager.deleteAccount("Alex2");
		accountManager.printAccountList();
	}*/
}
	