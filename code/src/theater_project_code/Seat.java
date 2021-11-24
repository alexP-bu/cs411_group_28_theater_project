package theater_project_code;

public class Seat {
	private Showtime show;
	private int row;
	private char column;
	private Account reserved_by = null;
	
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
	
	public Account get_reserved_by() {
		return this.reserved_by;
	}
	
	public boolean is_reserved() {
		if(this.get_reserved_by() != null) {
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
		this.reserved_by = account;
	}
}
