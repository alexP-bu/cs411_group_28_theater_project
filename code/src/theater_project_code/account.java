package theater_project_code;
import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = -6525670258139946755L;

	public class java {

	}
	protected String username;
	protected String password;
	protected String type;
	protected double balance;
	
	/*
	 * constructors
	 */
	
	public Account(String username, String password, String type, double balance) {
		this.set_username(username);
		this.set_password(password);
		this.set_balance(balance);
		this.set_type(type);
	}
	
	public Account(String username, String password) {
		this.set_username(username);
		this.set_password(password);
		this.set_balance(0.00);
		this.set_type("default");
	}
	
	public Account() {
		this.set_username("");
		this.set_password("");
		this.set_type("");
		this.set_balance(0.00);
	}
	
	/*
	 * getters
	 */
	
	public String get_username() {
		return username;
	}
	
	public String get_password() {
		return password;
	}
	
	public String get_type() {
		return type;
	}
	
	public double get_balance() {
		return balance;
	}
	
	/*
	 * setters
	 */
	
	public void set_username(String username) {
		this.username = username;
	}
	
	public void set_password(String password) {
		this.password = password;
	}
	
	public void set_type(String type) {
		this.type = type;
	}
	
	public void set_balance(double balance) {
		this.balance = balance;
	}
	
	public void add_balance(double amount) {
		this.balance += amount;
	}
	
	public void subtract_balance(double amount) {
		this.balance -= amount;
	}
}
