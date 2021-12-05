package theaterProjectCode;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = -6525670258139946755L;
	private String username;
	private String password;
	protected int credit;
	protected int key;
	private String type;
	private double balance;
	private int rewardsPoints;
	private ArrayList<Ticket> purchaseHistory;

	/*
	 * constructors
	 */

	public Account(String username, String password, String type, int credit, int key) {
		this.setUsername(username);
		this.setPassword(password);
		this.setBalance(0.00);
		this.setType(type);
		this.setRewardsPoints(0);
		this.purchaseHistory = new ArrayList<Ticket>();
		this.setCredit(credit);
		this.setKey(key);
	}

	public Account() {
		this.setUsername("guest");
		this.setPassword("guest");
		this.setType("guest");
		this.setBalance(0.00);
	}

	/*
	 * getters/setters
	 */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getCredit() {
		return this.credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getKey() {
		return this.key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getRewardsPoints() {
		return rewardsPoints;
	}

	public void setRewardsPoints(int rewardsPoints) {
		this.rewardsPoints = rewardsPoints;
	}

	public ArrayList<Ticket> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(ArrayList<Ticket> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	/*
	 * methods
	 */
	/*
	 * add ticket to account
	 */
	public void addTicket(Ticket ticket) {
		if (ticket != null) {
			purchaseHistory.add(ticket);
		} else {
			System.out.println("ERROR: INVALID TICKET");
		}
	}

	@Override
	public String toString() {
		return this.getUsername() + "'s Account Data:" + "\n" + "\n" + "Current balance: " + this.getBalance() + "\n"
				+ "Rewards points: " + this.getRewardsPoints() + "\n" + "Purchase history: "
				+ this.getPurchaseHistory().toString() + "\n";
	}
}
