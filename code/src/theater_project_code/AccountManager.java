package theater_project_code;
import java.util.HashMap;
import java.util.Map;
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
	
	public boolean login_account(String username, String password) throws IllegalArgumentException {
		if(accountList.containsKey(username)) {
			if(accountList.get(username).get_password().equals(password)) {
				if(loggedIn == null) {
					//if no other account logged in, login account. else, logout and login
					loggedIn = accountList.get(username);
					return true;
				} else {
					logout_account(username);
					loggedIn = accountList.get(username);
					return true;
				}
			}else {
				throw new IllegalArgumentException("Invalid password entered!");
			}
		}else {
			throw new IllegalArgumentException("Invalid username entered!");
		}
	}
	
	/*
	 * logout account using username. returns true on successful logout, false otherwise 
	 */
	
	public boolean logout_account(String username) {
		if(this.is_loggedIn(username)) {
			loggedIn = null;
			return true;
		}
		return false;
	}
	
	/*
	 * check if account is logged in. returns true if it is, false if not
	 */
	
	public boolean is_loggedIn(String username) {
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
	
	public boolean create_customer_account(String username, String password) throws IllegalArgumentException {
		if(accountList.containsKey(username)) {
			throw new IllegalArgumentException("Username already exists, please select a different one!");
		}
		CustomerAccount account = new CustomerAccount(username, password);
		//add key to local account list
		accountList.put(username, account);
		//clear current account database file and export new one
		clearFile(accountsFile);
		exportAccounts(accountsFile, accountList);
		//export local account list to update database file
		exportAccounts(accountsFile, accountList);
		//login account
		this.login_account(username, password);
		return true;
	}
	
	/*
	 * method to delete account by user name
	 * account must be logged in to delete itself
	 * administrator account must be logged in order to delete it
	 * returns true on successful deletion, false otherwise
	 */
	
	public boolean delete_account(String username) throws IllegalArgumentException {
		if(loggedIn == null) {
			if(loggedIn.get_type().equals("administrator")) {
				//delete account from accountList
				accountList.remove(username);
				//delete account from account database
				
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
	 * method to clear file
	 */
	
	private static void clearFile(File file) {
		try {
			new FileOutputStream(file).close();
		}catch (Exception e) {
			System.out.println("file not found!");
			e.printStackTrace();
		}
	}
	
	/*
	 * test harness
	 */
	
	public static void main(String[] args) {
		AccountManager accountManager = new AccountManager();
		
		accountManager.printAccountList();
		accountManager.create_customer_account("Alex", "testpassword");
		
		accountManager.printAccountList();
	}
}
	