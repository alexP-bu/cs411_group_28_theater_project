package theaterProjectCode;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountManager {

	private HashMap<String, Account> accountList;
	private Account loggedIn;
	private File accountsFile;
	protected String access = "nedlam!42";
	private static final Scanner reader = new Scanner(System.in);

	/*
	 * initializing constructor for the account manager get list of user names and
	 * passwords from database file and import them into list
	 * 
	 */
	public AccountManager() {
		this.accountsFile = new File("accounts.ser");
		// if file is null or doesn't exist, create a new file
		if (this.accountsFile.exists()) {
			try {
				this.accountsFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.loggedIn = null;
		// read accounts from file and import them into accounts list
		this.accountList = new HashMap<String, Account>();
		try {
			this.importAccounts(accountsFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * login account using user name and password. return true on successful login,
	 * throws exception if an error occurred.
	 */
	public boolean loginAccount() throws IllegalArgumentException {
		String username = this.getUsernameInput();
		String password = this.getPasswordInput();
		// attempt an account login
		if (accountList.containsKey(username)) {
			if (accountList.get(username).getPassword().equals(password)) {
				if (loggedIn == null) {
					// if no other account logged in, login account
					loggedIn = accountList.get(username);
					System.out.println("Account successfully logged in!");
					return true;
				} else {
					System.out.println("Please log out before logging in a new account!");
					return false;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	public void login(String username) {
		loggedIn = accountList.get(username);
	}

	/*
	 * logout any account. returns true on successful logout
	 */
	public boolean logoutAccount() {
		if (loggedIn == null) {
			System.out.println("No account logged in!");
			return false;
		}
		loggedIn = null;
		System.out.println("Logout successful.");
		return true;
	}

	/*
	 * check if account is logged in. returns true if it is, false if not
	 */
	public boolean isLoggedIn() {
		return loggedIn != null;
	}

	/*
	 * method to add new account returns true on successful account creation throws
	 * exception on account creation failure
	 * 
	 */
	public boolean createAccount(String type) {
		String username = this.getUsernameInput();
		String password = this.getPasswordInput();
		if (accountList.containsKey(username)) {
			System.out.println("Username already exists, please try again!");
			return false;
		}
		if (type.equalsIgnoreCase("administrator") || type.equalsIgnoreCase("employee")) {
			System.out.println("Enter ACCESS CODE: ");
			String accessKey = this.getUserInputText();
			if (!accessKey.equals(access)) {
				System.out.println("ERROR! INCORRECT ACCESSKEY...");
				return false;
			}
		}

		System.out.println("Please enter your 8-digit credit card number: ");
		int credit = getCredit();
		String creditS = String.valueOf(credit);
		int creditH = creditS.hashCode();

		System.out.println("Please enter your 3-digit security key: ");
		int key = getKey();
		String keyS = String.valueOf(key);
		int keyH = keyS.hashCode();

		Account account = new Account(username, password, type, creditH, keyH);
		// add key to local account list
		accountList.put(username, account);
		// clear current account database file and export new one
		exportAccounts(accountsFile);
		if (type.equalsIgnoreCase("administrator") || type.equalsIgnoreCase("employee")) {
			login(username);
		}
		return true;
	}

	public boolean addBalance() {
		if (loggedIn != null) {
			System.out.println("Please enter your Credit Card Details: ");
			int credit = getCredit();
			System.out.println("Please enter your Security Key Details: ");
			int key = getKey();

			String creditS = String.valueOf(credit);
			int creditH = creditS.hashCode();

			String keyS = String.valueOf(key);
			int keyH = keyS.hashCode();

			if (creditH == loggedIn.getCredit() && keyH == loggedIn.getKey()) {
				System.out.println("How much would you like to add to your balance?");
				double amount = getAmount();
				double cur = loggedIn.getBalance();
				loggedIn.setBalance(cur + amount);
				exportAccounts(accountsFile);
				return true;
			}
			return false;
		} else {
			System.out.println("Please log-in before making a purchase");
			return false;
		}
	}

	/*
	 * method to return logged in account
	 */
	public Account getLoggedInAccount() {
		if (loggedIn != null) {
			return loggedIn;
		} else {
			return null;
		}
	}

	/*
	 * method to delete account by user name account must be logged in to delete
	 * itself administrator account must be logged in order to delete it returns
	 * true on successful deletion, false otherwise
	 */
	public boolean deleteAccount() throws IllegalArgumentException {
		if (loggedIn != null) {
			if (loggedIn.getType().equals("administrator")) {
				System.out.println("Access Granted...");
				user_interface.wait(1);
				System.out.println("...");
				System.out.println("Please enter the account username for deletion: ");
				String username = getUsernameInput();
				System.out.printf("Are you sure you want to delete %s's account?\n", username);
				System.out.println("Please re-enter account username to confirm: ");
				if (!username.equals(this.getUsernameInput())) {
					System.out.println("Account failed to delete!");
					return false;
				}
				// delete account from accountList
				accountList.remove(username);
				// export new database file with deleted account
				exportAccounts(accountsFile);
				System.out.printf("%s's account has been successfully deleted.\n", username);
				return true;
			} else {
				System.out.println("Insufficient permissions to delete account!");
			}
		} else {
			System.out.println("Please log in to delete account!");
		}
		return false;
	}

	/*
	 * print local accounts
	 */
	public void printAccountList() {
		if (accountList == null || accountList.isEmpty()) {
			System.out.println("There are no registered users on this system.");
		} else {
			System.out.println("Accounts List: ");
			for (Map.Entry<String, Account> entry : accountList.entrySet()) {
				System.out.println(entry.getKey());
			}
			System.out.println("Accounts list done printing.");
		}
	}

	/*
	 * read accounts from accounts database
	 */
	public void importAccounts(File file) {
		clearAccountList();
		try {
			ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file));
			while (true) {
				try {
					Account retreived = (Account) objIn.readObject();
					accountList.put(retreived.getUsername(), retreived);
				} catch (EOFException e) {
					objIn.close();
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("Finished reading file.");
		}
		System.out.println("Imported accounts database file.");
	}

	/*
	 * export accounts from account list in current session to file
	 */
	public void exportAccounts(File file) {
		clearAccountDatabase();
		try {
			ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(file));
			try {
				for (Map.Entry<String, Account> entry : accountList.entrySet()) {
					objOut.writeObject(entry.getValue());
				}
			} catch (Exception e) {
				objOut.close();
				System.out.println("Error exporting account.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Database file updated.");
	}

	/*
	 * clear accounts database file
	 */
	public void clearAccountDatabase() {
		try {
			new FileOutputStream(accountsFile).close();
		} catch (Exception e) {
			System.out.println("file not found!");
			e.printStackTrace();
		}
		// System.out.println("Database file cleared.");
	}

	/*
	 * method to clear local account list
	 */
	public void clearAccountList() {
		if (accountList != null) {
			accountList.clear();
			System.out.println("Finished clearing local account data.");
		} else {
			System.out.println("There are no accounts stored locally.");
		}
	}

	/*
	 * account list getter
	 */
	public HashMap<String, Account> getLocalAccountList() {
		return accountList;
	}

	/*
	 * accounts file getter
	 */
	public File getDatabaseFile() {
		return accountsFile;
	}

	/*
	 * get valid string from user
	 */
	private String getUserInputText() {
		String input = reader.nextLine();
		while (input.isBlank() || input == null) {
			System.out.println("Please enter valid text!");
			input = reader.nextLine();
		}
		return input;
	}

	private String getUsernameInput() {
		System.out.println("Enter username: ");
		return getUserInputText();
	}

	private String getPasswordInput() {
		System.out.println("Enter password: ");
		return getUserInputText();
	}

	private double getAmount() {
		double input = 0.0;
		do {
			try {
				input = Double.parseDouble(reader.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println(e);
			}
			System.out.println("invalid input entered! try again");
		} while (true);
		return input;
	}

	private int getCredit() {
		int input = 0;
		String temp = null;
		do {
			try {
				input = Integer.parseInt(reader.nextLine());
				temp = String.valueOf(input);
				if (temp != null && temp.toCharArray().length == 8) {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(e);
			}
			System.out.println("invalid input entered! try again");
		} while (true);
		return input;
	}

	private int getKey() {
		int input = 0;
		String temp = null;
		do {
			try {
				input = Integer.parseInt(reader.nextLine());
				temp = String.valueOf(input);
				if (temp != null && temp.toCharArray().length == 3) {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(e);
			}
			System.out.println("invalid input entered! try again");
		} while (true);
		return input;
	}

	/*
	 * test harness code
	 *
	 * public static void main(String[] args) { //make a new account manager
	 * AccountManager accountManager = new AccountManager(); //clear its database
	 * file accountManager.clearDatabase(); accountManager.clearAccountList();
	 * //create two customer accounts accountManager.createAccount("Alex",
	 * "testpassword"); accountManager.createAccount("Alex2", "testpassword2");
	 * //print account list accountManager.printAccountList(); //login an account
	 * accountManager.loginAccount("Alex","testpassword");
	 * System.out.println("Is Alex logged in?");
	 * System.out.println(accountManager.isLoggedIn("Alex")); //logout account
	 * accountManager.logoutAccount(); System.out.println("Is Alex logged in now?");
	 * System.out.println(accountManager.isLoggedIn("Alex")); //test deleting
	 * account System.out.println("Test deleting an account:");
	 * System.out.println("Account list before:");
	 * accountManager.printAccountList();
	 * System.out.println("If not logged in, deleted?:");
	 * accountManager.deleteAccount("Alex2"); accountManager.printAccountList();
	 * //login then delete accountManager.loginAccount("Alex2", "testpassword2");
	 * accountManager.deleteAccount("Alex2"); accountManager.printAccountList(); }
	 */
}
