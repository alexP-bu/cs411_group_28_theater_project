package theater_project_code;

public class Ticket {
	private Showtime show;
	private Seat seat;
	private double price;
	private Date date_purchased = null;
	
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
	public Date get_date_purchased() {
		return date_purchased;
	}
	public void set_date_purchased(Date date_purchased) {
		this.date_purchased = date_purchased;
	}
	
	
}
