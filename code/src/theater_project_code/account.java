package theater_project_code;

public class Account {
	public class java {

	}
	private String username;
	private String password;
	private String type;
	private float balance;
	
	public String get_username() {
		return username;
	}
	
	public String get_password() {
		return password;
	}
	
	public void set_username(String username) {
		this.username = username;
	}
	
	public void set_password(String password) {
		this.password = password;
	}
	
	public void set_type(String type) {
		this.type = type;
	}
	
	public String get_type() {
		return type;
	}
	
	public float get_balance() {
		return balance;
	}
	
	public void set_balance(float balance) {
		this.balance = balance;
	}
	
	public void add_balance(float amount) {
		this.balance += amount;
	}
	
	public void subtract_balance(float amount) {
		this.balance -= amount;
	}
}
