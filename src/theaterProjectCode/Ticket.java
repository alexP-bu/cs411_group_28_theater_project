package theaterProjectCode;

public class Ticket {
	private Showtime show;
	private Seat seat;
	private double price;
	private Date datePurchased = null;
	
	public Ticket(Showtime show, Seat seat, double price, Date datePurchased) {
		this.show = show;
		this.seat = seat;
		this.price = price;
		this.datePurchased = datePurchased;
	}
	
	public Ticket() {
		this.show = null;
		this.seat = null;
		price = 0.00;
		this.datePurchased = null;
	}
	
	/*
	 * getters/setters
	 */
	
	public Showtime get_show() {
		return show;
	}
	public void set_show(Showtime show) {
		this.show = show;
	}
	public Seat get_seat() {
		return seat;
	}
	public void set_seat(Seat seat) {
		this.seat = seat;
	}
	public double get_price() {
		return price;
	}
	public void set_price(double price) {
		this.price = price;
	}
	public Date get_datePurchased() {
		return datePurchased;
	}
	public void set_datePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}
	
	
}
