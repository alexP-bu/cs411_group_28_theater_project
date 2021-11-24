package theater_project_code;
import java.util.LinkedList;

public class customer_account extends Account {
	private String type = "customer";
	private int rewards_points;
	private LinkedList purchase_history;
	//private Seat[] seats_reserved;
	//private Ticket[] current_tickets;
	
	/*
	 * getters
	 */
	
	public int get_rewards_points() {
		return this.rewards_points;
	}
	
	public LinkedList get_purchase_history() {
		return this.purchase_history;
	}

	/*
	 * setters
	 */
	
	public void set_rewards_points(int rewards_points) {
		this.rewards_points = rewards_points;
	}
	
	public void add_to_purchase_history(Ticket ticket) {
		purchase_history.add(ticket);
	}
}

