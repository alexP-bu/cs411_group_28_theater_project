package theater_project_code;

public class Theater {
	private int theaterNumber;
	private SeatingChart seats;
	/*
	 *constructors 
	 */
	public Theater(int number, SeatingChart seatingChart) {
		this.theaterNumber = number;
		this.seats = seatingChart;
	}
	/*
	 * construct theater with empty seating chart
	 */
	public Theater(int number) {
		this.theaterNumber = number;
		this.seats = new SeatingChart();
	}
	/*
	 * getters and setters
	 */
	public int getTheaterNumber() {
		return theaterNumber;
	}
	public void setTheaterNumber(int theaterNumber) {
		this.theaterNumber = theaterNumber;
	}
	public SeatingChart getSeats() {
		return seats;
	}
	public void setSeats(SeatingChart seats) {
		this.seats = seats;
	}
}
