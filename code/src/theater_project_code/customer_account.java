package theater_project_code;
import java.util.LinkedList;

public class customer_account extends Account {
	private String type = "customer";
	private int rewards_points;
	private LinkedList<Ticket> purchase_history;
	private Seat[] seats_reserved;
	private Ticket[] current_tickets;
	
	public String get_type() {
		return type;
	}
	public void set_type(String type) {
		this.type = type;
	}
	public int get_rewards_points() {
		return rewards_points;
	}
	public void set_rewards_points(int rewards_points) {
		this.rewards_points = rewards_points;
	}
	public LinkedList<Ticket> get_purchase_history() {
		return purchase_history;
	}
	public void set_purchase_history(LinkedList<Ticket> purchase_history) {
		this.purchase_history = purchase_history;
	}
	public Seat[] get_seats_reserved() {
		return seats_reserved;
	}
	public void set_seats_reserved(Seat[] seats_reserved) {
		this.seats_reserved = seats_reserved;
	}
	public Ticket[] get_current_tickets() {
		return current_tickets;
	}
	public void set_current_tickets(Ticket[] current_tickets) {
		this.current_tickets = current_tickets;
	}
	
	
}

