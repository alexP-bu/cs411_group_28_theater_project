package theaterProjectCode;

public class SeatingChart {
	private Seat[][] seats;
	/*
	 * constructors
	 */
	public SeatingChart(Seat[][] seats) {
		this.seats = seats;
	}
	/*
	 *constructor for seating chart given rows and columns 
	 */
	public SeatingChart(int rows, int cols) {
		this.seats = new Seat[rows][cols];
		for(int i = 0; i < this.seats.length; i++) {
			for(int j = 0; j < this.seats[0].length; j++) {
				this.seats[i][j] = new Seat(((char)(i+41)),j);
			}
		}
	}
	/*
	 * default seating chart constructor, populate 10x10 chart with empty seats
	 */
	public SeatingChart() {
		this.seats = new Seat[10][10];
		for(int i = 0; i < this.seats.length; i++) {
			for(int j = 0; j < this.seats[0].length; j++) {
				this.seats[i][j] = new Seat(((char)(i+41)),j);
			}
		}
	}
	/*
	 * getters/setters
	 */
	public Seat[][] getSeats() {
		return seats;
	}
	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}
	/*
	 * reserve seat in Seating Chart with no account
	 */
	public boolean reserveSeat(char row, int column) {
		if(seats[((int)row)-65][column].reserveSeat()) {
			return true;
		}
		return false;
	}
	/*
	 * reserve seat in Seating Chart with account
	 */
	public boolean reserveSeat(char row, int column, Account account) {
		if(seats[((int)row)-65][column].reserveSeat(account)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < this.seats.length; i++) {
			for(int j = 0; j < this.seats[0].length; j++) {
				str += seats[i][j].toString();
			}
			str += "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		SeatingChart seats = new SeatingChart();
		
		System.out.println(seats.toString());
		seats.reserveSeat('G', 8);
		System.out.println(seats.toString());
	}
}
