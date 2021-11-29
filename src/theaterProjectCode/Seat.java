package theaterProjectCode;

public class Seat {
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
	public char get_row() {
		return this.row;
	}
	
	public int get_column() {
		return this.column;
	}
	
	public Account get_reservedBy() {
		return this.reservedBy;
	}
	
	public boolean is_reserved() {
		if(this.get_reservedBy() != null) {
			return true;
		}
		return false;
	}
	/*
	 * setters/mutators
	 */
	public void set_row(char row) {
		this.row = row;
	}
	
	public void set_column(int column) {
		this.column = column;
	}
	/*
	 * function to reserve seat given with account
	 */
	public boolean reserveSeat(Account account) {
		if(this.reservedBy != null) {
			this.reservedBy = account;
			System.out.printf("Seat successfully reserved by %s", account.getUsername());
			return true;
		}else {
			System.out.println("Seat already reserved. Please select a different seat!");
			return false;
		}
	}
	/*
	 * function to reserve seat without account - use default guest account
	 */
	public boolean reserveSeat() {
		if(this.reservedBy == null) {
			this.reservedBy = new Account();
			System.out.println("Seat successfully reserved.");
			return true;
		}else {
			System.out.println("Seat already reserved. Please select a different seat!");
			return false;
		}
	}
	
	/*
	 * returns location of seat in string format. Ex. G8, E6, ETC
	 */
	public String getLocation() {
		return (String.valueOf(this.row)) + (String.valueOf(this.column));
	}
	
	@Override
	public String toString() {
		if(this.reservedBy != null) {
			return "[X]";
		}else {
			return "[O]";
		}
	}
}
