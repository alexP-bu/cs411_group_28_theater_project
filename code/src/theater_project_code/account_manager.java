package theater_project_code;
import java.util.HashMap;
import java.util.HashSet;
import java.io.File;
import java.util.Scanner;

import javax.naming.NoPermissionException;

import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class account_manager {
	
	private HashMap<String,String> login_info = new HashMap<String,String>();
	private Account logged_in = null;
	private File usernames = new File("usernames.txt");
	private File passwords = new File("passwords.txt");
	
	/*
	 * initializing function for the account manager
	 * get list of usernames and passwords from database file and import them into hashmap
	 * 
	 */
	
	public account_manager() {
		try {		
			Scanner user_reader = new Scanner(usernames);
			Scanner pass_reader = new Scanner(passwords);
		
			while (user_reader.hasNextLine() && pass_reader.hasNextLine()) {
				String get_username = user_reader.nextLine();
				String get_password = pass_reader.nextLine();
			
				login_info.put(get_username, get_password);
			}
			
			user_reader.close();
			pass_reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found error");
			e.printStackTrace();
		}	
	}
	
	/*
	 * login account using username and password.
	 * return true on successful login, throws exception if an error occured.
	 */
	
	public boolean login_account(String username, String password) throws IllegalArgumentException{
		if(login_info.containsKey(username)) {
			if(login_info.get(username).equals(password)) {
				if(logged_in.isEmpty()) {
					//if no other account logged in, login account. else, logout and login
					logged_in = account;
					return true;
				} else {
					this.logout_account(username);
					logged_in = account;
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
		if(this.is_logged_in(username)) {
			logged_in = null;
			return true;
		}
		return false;
	}
	
	/*
	 * check if account is logged in. returns true if it is, false if not
	 */
	
	public boolean is_logged_in(String username) {
		if(/*logged_in == account.username*/)) {
			return true;
		}
		return false;
	}
	
	/*
	 * method to add new account
	 * username must be added to username file
	 * password must be added to password file
	 * returns true on successful account creation
	 * throws exception on account creation failure and returns false
	 * 
	 */
	
	public boolean create_account(String username, String password) throws IllegalArgumentException {
		if(login_info.containsKey(username)) {
			throw new IllegalArgumentException("Username already exists, please select a different one!");
		}
		
		try {
			//write username in database
			Writer out;
			out = new BufferedWriter(new FileWriter(usernames, true));
			out.append(username);
			out.close();
			//write password in database
			out = new BufferedWriter(new FileWriter(passwords, true));
			out.append(password);
			out.close();
			//add key to login info hashmap
			login_info.put(username, password);
			//login account
			this.login_account(username, password);
			return true;
		} catch (Exception e) {
			System.out.println("Error writing to files!");
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * method to delete account by username
	 * account must be logged in to delete itself
	 * administrator account must be logged in order to delete it
	 * 
	 */
	
	public void delete_account(String username) throws IllegalArgumentException, NoPermissionException {
		if(logged_in == null) {
			if(logged_in())
		}
	}
	
	public void set_account_type() {
		
	}
	
	public String get_account_type() {
		
	}
	
	public String get_account_password() {
		
	}
	
	public String get_account_username() {
		
	}
	
	public void change_account_password() {
		
	}
	
	public void change_account_username() {
		
	}
}
	