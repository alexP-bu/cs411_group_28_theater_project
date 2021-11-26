package theater_project_code;
import java.util.ArrayList;

public class CustomerAccount extends Account {
	/**
	 * Serialization UID (mostly to remove warning) 
	 */
	private static final long serialVersionUID = 8051187064897029556L;
	
	
	private int rewardsPoints;
	private ArrayList<Ticket> purchaseHistory;
	private Seat[] seatsReserved;
	private Ticket[] currentTickets;
	
	/*
	 * constructors
	 */
	
	public CustomerAccount(String username, String password) {
		super(username, password,"customer",0.00);
		this.setRewardsPoints(0);
		this.setPurchaseHistory(null);
		this.setSeatsReserved(null);
		this.setCurrentTickets(null);
	}
	/*
	 * default constructor. Used for guest users who buy tickets and reserve seats without account
	 */
	public CustomerAccount() {
		super("Guest","Guest","Guest",0.00);
		this.setRewardsPoints(0);
		this.setPurchaseHistory(null);
		this.setSeatsReserved(null);
		this.setCurrentTickets(null);
	}

	/*
	 * getters/setters
	 */
	
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

	public Seat[] getSeatsReserved() {
		return seatsReserved;
	}

	public void setSeatsReserved(Seat[] seatsReserved) {
		this.seatsReserved = seatsReserved;
	}

	public Ticket[] getCurrentTickets() {
		return currentTickets;
	}

	public void setCurrentTickets(Ticket[] currentTickets) {
		this.currentTickets = currentTickets;
	}
	
	@Override
	public String toString() {
		return this.get_username() + "'s Account Data:" + "\n" +"\n"
			   + "Current balance: " + this.get_balance() + "\n"
			   + "Rewards points: " + this.getRewardsPoints() + "\n" 
			   + "Seats currently reserved: " + this.getSeatsReserved() + "\n"
			   + "Current tickets purchased: " + this.getCurrentTickets() + "\n"
			   + "Purchase history: " + this.getPurchaseHistory() + "\n";
	}

}

