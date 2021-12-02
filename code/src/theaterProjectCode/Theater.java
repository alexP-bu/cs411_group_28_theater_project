package theaterProjectCode;

import java.io.Serializable;

public class Theater implements Serializable{
	/**
	 * serializable session ID
	 */
	private static final long serialVersionUID = 6287745973157089822L;
	private String theaterID;
	private SeatingChart seatingChart;
	
	/*
	 *constructors 
	 */
	public Theater(String ID, SeatingChart seatingChart) {
		this.theaterID = ID;
		this.seatingChart = seatingChart;
	}
	public Theater(String ID) {
		this.theaterID = ID;
		this.seatingChart = null;
	}
	/*
	 * construct theater with empty seating chart
	 */
	public Theater() {
		this.theaterID = "";
		this.seatingChart = null;
	}
	/*
	 * getters and setters
	 */
	public String getTheaterID() {
		return theaterID;
	}
	public void setTheaterID(String theaterID) {
		this.theaterID = theaterID;
	}
	public SeatingChart getSeatingChart() {
		return seatingChart;
	}
	public void setSeatingChart(SeatingChart seats) {
		this.seatingChart = seats;
	}
	
	/*
	 * check if theater is full
	 */
	public boolean isFull() {
		if(seatingChart.hasEmptySeats()) {
			return false;
		}
		return true;
	}
	
	/*
	 * other methods
	 */
	@Override
	public String toString() {
		String str = "***********************************"  + "\n" + 
				     "      THEATER " + this.getTheaterID() + "\n" + 
					 "***********************************"  + "\n" +
				     seatingChart.toString();
		return str;
	}
}
