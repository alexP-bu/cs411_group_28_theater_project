package theaterProjectCode;

import java.io.Serializable;

public class Ticket implements Serializable {
	/**
	 * id
	 */
	private static final long serialVersionUID = -4034562731486809874L;
	private Showtime show;
	private String theater;
	private Seat seat;
	private double price;
	private Date datePurchased = null;

	public Ticket(Showtime show, Seat seat, double price, Date datePurchased, String theaterID) {
		this.show = show;
		this.seat = seat;
		this.price = price;
		this.datePurchased = datePurchased;
		this.theater = theaterID;
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

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	@Override
	public String toString() {
		return "\n" + this.show.getName() + " Showing on " + this.show.getDate() + "\nPrice: " + this.show.getPrice();
	}

}
