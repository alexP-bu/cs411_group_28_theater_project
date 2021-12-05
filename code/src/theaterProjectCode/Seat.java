package theaterProjectCode;

import java.io.Serializable;

public class Seat implements Serializable {
	/**
	 * id
	 */
	private static final long serialVersionUID = 2283190859901058721L;
	private char row;
	private int column;
	private Account reservedBy;

	/*
	 * constructors
	 */

	public Seat(Showtime show, char row, int column, Account account) {
		this.row = row;
		this.column = column;
		this.reservedBy = account;
	}

	public Seat(char row, int column) {
		this.row = row;
		this.column = column;
		this.reservedBy = null;
	}

	public Seat() {
		this.row = 0;
		this.column = 0;
		this.reservedBy = null;
	}

	/*
	 * getters
	 */
	public char getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public Account getReservedBy() {
		return this.reservedBy;
	}

	public boolean is_reserved() {
		if (this.getReservedBy() != null) {
			return true;
		}
		return false;
	}

	/*
	 * setters/mutators
	 */
	public void setRow(char row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	/*
	 * function to reserve seat given with account
	 */
	public boolean reserveSeat(Account account) {
		if (this.reservedBy == null) {
			this.reservedBy = account;
			// System.out.printf("Seat successfully reserved by %s", account.getUsername());
			return true;
		} else {
			System.out.println("Seat already reserved. Please select a different seat!");
			return false;
		}
	}

	/*
	 * function to reserve seat without account - use guest account
	 */
	public boolean reserveSeat() {
		if (this.reservedBy == null) {
			this.reservedBy = new Account();
			System.out.println("Seat successfully reserved.");
			return true;
		} else {
			System.out.println("Seat already reserved. Please select a different seat!");
			return false;
		}
	}

	@Override
	public String toString() {
		if (this.reservedBy != null) {
			return "[X]";
		} else {
			return "[O]";
		}
	}
}
