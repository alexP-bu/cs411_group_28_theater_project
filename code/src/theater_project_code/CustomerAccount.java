package theater_project_code;
import java.util.LinkedList;

public class CustomerAccount extends Account {
	/**
	 * Serialization UID (mostly to remove warning) 
	 */
	private static final long serialVersionUID = 8051187064897029556L;
	
	
	private int rewardsPoints;
	private LinkedList<Ticket> purchaseHistory;
	private Seat[] seatsReserved;
	private Ticket[] currentTickets;
	
	/*
	 * constructors
	 */
	
	public CustomerAccount(String username, String password) {
		super(username, password,"customer",0.00);
		this.rewardsPoints = 0;
		this.purchaseHistory = null;
		this.seatsReserved = null;
		this.currentTickets = null;
	}
	
	/*
	 * getters/setters
	 */
	
	public String get_type() {
		return super.type;
	}
	public void set_type(String type) {
		this.type = type;
	}
	public int get_rewardsPoints() {
		return rewardsPoints;
	}
	public void set_rewardsPoints(int rewardsPoints) {
		this.rewardsPoints = rewardsPoints;
	}
	public LinkedList<Ticket> get_purchaseHistory() {
		return purchaseHistory;
	}
	public void set_purchaseHistory(LinkedList<Ticket> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}
	public Seat[] get_seatsReserved() {
		return seatsReserved;
	}
	public void set_seatsReserved(Seat[] seatsReserved) {
		this.seatsReserved = seatsReserved;
	}
	public Ticket[] get_currentTickets() {
		return currentTickets;
	}
	public void set_currentTickets(Ticket[] currentTickets) {
		this.currentTickets = currentTickets;
	}
}

