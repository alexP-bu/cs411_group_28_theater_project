package theaterProjectCode;

import java.io.Serializable;

public class SeatingChart implements Serializable {
	/**
	 * id
	 */
	private static final long serialVersionUID = 8611123495243223849L;
	private Seat[][] seats;

	/*
	 * constructors
	 */
	public SeatingChart(Seat[][] seats) {
		this.seats = seats;
	}

	/*
	 * constructor for seating chart given rows and columns
	 */
	public SeatingChart(int rows, int cols) {
		this.seats = new Seat[rows][cols];
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < this.seats[0].length; j++) {
				this.seats[i][j] = new Seat(((char) (i + 41)), j);
			}
		}
	}

	/*
	 * default seating chart constructor, populate 10x10 chart with empty seats
	 */
	public SeatingChart() {
		this.seats = new Seat[10][10];
		for (int i = 0; i < this.seats.length; i++) {
			for (int j = 0; j < this.seats[0].length; j++) {
				this.seats[i][j] = new Seat(((char) (i + 41)), j);
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
		if (seats[((int) Character.toUpperCase(row)) - 65][column].is_reserved()) {
			System.out.println("Seat is already reserved!");
			return false;
		}
		if (seats[((int) Character.toUpperCase(row)) - 65][column].reserveSeat()) {
			return true;
		}
		System.out.println("Error reserving seat in seating chart.");
		return false;
	}

	/*
	 * reserve seat in Seating Chart with account
	 */
	public boolean reserveSeat(char row, int column, Account account) {
		if (seats[((int) Character.toUpperCase(row)) - 65][column].reserveSeat(account)) {
			return true;
		}
		return false;
	}

	/*
	 * check if seating chart is full
	 */
	public boolean hasEmptySeats() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[0].length; j++) {
				if (!(seats[i][j].is_reserved())) {
					return true;
				}
			}
		}
		return false;
	}

	public String toString() {
		String str = "   ";
		for (int i = 0; i < seats[0].length; i++) {
			str += Integer.toString(i) + "  ";
		}
		str += "\n";
		for (int i = 0; i < seats.length; i++) {
			str += Character.toString((char) i + 65) + " ";
			for (int j = 0; j < seats[0].length; j++) {
				str += seats[i][j].toString();
			}
			str += "\n";
		}
		return str;
	}
}
