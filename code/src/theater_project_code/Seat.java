package theater_project_code;

public class Seat {
	private Showtime show;
	private int row;
	private char column;
	private Account reservedBy;
	
	/*
	 * constructors
	 */
	
	public Seat(Showtime show, int row, char column) {
		this.show = show;
		this.row = row;
		this.column = column;
		this.reservedBy = null;
	}
	
	public Seat(Showtime show, int row, char column, Account account) {
		this.show = show;
		this.row = row;
		this.column = column;
		this.reservedBy = account;
	}
	
	public Seat() {
		this.show = null;
		this.row = 0;
		this.column = 0;
		this.reservedBy = null;
	}
	
	/*
	 * getters
	 */
	
	public Showtime get_showtime() {
		return this.show;
	}
	
	public int get_row() {
		return this.row;
	}
	
	public char get_column() {
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
	
	public void set_showtime(Showtime show) {
		this.show = show;
	}
	
	public void set_row(int row) {
		this.row = row;
	}
	
	public void set_column(char column) {
		this.column = column;
	}
	
	public void reserve_seat(Account account) {
		this.reservedBy = account;
	}
}
