package theater_project_code;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
 

public class account_manager {
	
	HashMap<String,String> login_info = new HashMap();
	
	/*
	 * initializing function for the account manager
	 * get list of usernames and passwords from database file and import them into hashmap
	 * 
	 */
	public account_manager() {
		try {
			File usernames = new File("usernames.txt");
			File passwords = new File("passwords.txt");
		
			Scanner user_reader = new Scanner(usernames);
			Scanner pass_reader = new Scanner(passwords);
		
			while (user_reader.hasNextLine() && pass_reader.hasNextLine()) {
				String get_username = user_reader.nextLine();
				String get_password = pass_reader.nextLine();
			
				login_info.put(get_username, get_password);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found error");
		}
	}
	
	public void login_account() {
		
	}
	
	public void logout_account() {
		
	}
	
	public void create_account() {
		
	}
	
	public void delete_account() {
		
	}
}
	