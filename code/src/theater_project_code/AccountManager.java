package theater_project_code;
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
	
	/*
	 * initializing function for the account manager
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
		} catch (Exception e) {
			System.out.println("error importing accounts file");
			e.printStackTrace();
		}
	}
	
	/*
	 * login account using user name and password.
	 * return true on successful login, throws exception if an error occurred.
	 */
	
	public boolean loginAccount(String username, String password) throws IllegalArgumentException {
		if(accountList.containsKey(username)) {
			if(accountList.get(username).get_password().equals(password)) {
				if(loggedIn == null) {
					//if no other account logged in, login account. else, logout and login
					loggedIn = accountList.get(username);
					return true;
				} else {
					logoutAccount();
					loggedIn = accountList.get(username);
					return true;
				}
			}else {
				System.out.println("Invalid password entered!");
			}
		}else {
			System.out.println("Invalid username entered!");
		}
		return false;
	}
	
	/*
	 * logout account using user name. returns true on successful logout, false otherwise 
	 */
	
	public boolean logoutAccount() {
		loggedIn = null;
		return true;
	}
	
	/*
	 * check if account is logged in. returns true if it is, false if not
	 */
	
	public boolean isLoggedIn(String username) {
		if(loggedIn == null) {
			return false;
		}
		if(loggedIn.get_username().equals(username)) {
			return true;
		}
		return false;
	}
	
	/*
	 * method to add new account
	 * returns true on successful account creation
	 * throws exception on account creation failure
	 * 
	 */
	
	public boolean createCustomerAccount(String username, String password) {
		if(accountList.containsKey(username)) {
			System.out.println("Username already exists, please enter a different one!");
			return false;
		}
		CustomerAccount account = new CustomerAccount(username, password);
		//add key to local account list
		accountList.put(username, account);
		//clear current account database file and export new one
		clearDatabase();
		exportAccounts(accountsFile, accountList);
		//export local account list to update database file
		exportAccounts(accountsFile, accountList);
		//login account
		this.loginAccount(username, password);
		return true;
	}
	
	/*
	 * method to delete account by user name
	 * account must be logged in to delete itself
	 * administrator account must be logged in order to delete it
	 * returns true on successful deletion, false otherwise
	 */
	
	public boolean deleteAccount(String username) throws IllegalArgumentException {
		if(loggedIn != null) {
			if(loggedIn.get_type().equals("administrator") || loggedIn.get_username().equals(username)) {
				Scanner reader = new Scanner(System.in);
				System.out.printf("Are you sure you want to delete %s's account?\n", username);
				System.out.println("Please re-enter account username to confirm: ");
				if (!reader.nextLine().equals(username)) {
					System.out.println("Account failed to delete!");
					reader.close();
					return false;
				}
				reader.close();
				//delete account from accountList
				accountList.remove(username);
				//export new database file with deleted account
				exportAccounts(accountsFile, accountList);
				System.out.printf("%s's account has been successfully deleted.\n", username);
				return true;
			} 
		}
		return false;
	}
	
	public void printAccountList() {
		System.out.println("Accounts List: ");
		for(Map.Entry<String, Account> entry : accountList.entrySet()) {
			System.out.println(entry.getKey());
		}
		System.out.println("Accounts list done printing.");
	}
	
	/*
	 * method to read accounts from accounts list using FileInputStreams
	 */
	
	private static HashMap<String,Account> importAccounts(File file) throws IOException, ClassNotFoundException {
		HashMap<String,Account> output = new HashMap<String,Account>();
		try {
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
			while(true) {
				try {
					Account get = (Account) reader.readObject();
					output.put(get.get_username(),get);
				}catch (EOFException e) {
					break;
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Error importing accounts");
			e.printStackTrace();
		}
		return output;
	}
	
	/*
	 * method to export accounts from account list in current session to file
	 */
	
	private static void exportAccounts(File file, HashMap<String,Account> accountList) {
		try {
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
			for(Map.Entry<String, Account> entry : accountList.entrySet()) {
				Account account = entry.getValue();
				writer.writeObject(account);
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Error exporting accounts");
			e.printStackTrace();
		}
	}
	
	/*
	 * method to database clear file
	 */
	
	private void clearDatabase() {
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
		accountList.clear();
	}
	
	/*
	 * test harness code
	 */
	/*
	public static void main(String[] args) {
		//make a new account manager
		AccountManager accountManager = new AccountManager();
		//clear its database file
		accountManager.clearDatabase();
		accountManager.clearAccountList();
		//create two customer accounts
		accountManager.createCustomerAccount("Alex", "testpassword");
		accountManager.createCustomerAccount("Alex2", "testpassword2");
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
	