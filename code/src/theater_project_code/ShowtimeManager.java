package theater_project_code;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeManager {
	private ArrayList<Showtime> showtimes;
	
	/*
	 * constructors
	 */
	public ShowtimeManager() {
		showtimes = new ArrayList<Showtime>();
	}
	/*
	 * list show times
	 */
	public void listShowtimes() {
		if(showtimes != null) {
			if(!showtimes.isEmpty()) {
				System.out.println("Listing showtimes:");
				for(Showtime showtime : showtimes) {
					System.out.println(showtime.toString());
				}
			}
		}
	}
	/*
	 * add show time
	 * returns true on successful add, false otherwise
	 */
	public boolean addShowtime(Showtime show) {
		if(show != null) {
			showtimes.add(show);
		}
		return false;
	}
	/*
	 * test harness code
	 */
	public static void main(String[] args) {
		ShowtimeManager showtimes = new ShowtimeManager();
		Showtime show1 = new Showtime(new Date(), "Endgame", new ArrayList<Theater>(List.of(new Theater(1), new Theater(2), new Theater(3))));
		Showtime show2 = new Showtime(new Date(), "Infinity War", new ArrayList<Theater>(List.of(new Theater(1), new Theater(2), new Theater(3))));
		Showtime show3 = new Showtime(new Date(), "Breaking Bad", new ArrayList<Theater>(List.of(new Theater(1), new Theater(2), new Theater(3))));
		Showtime show4 = new Showtime(new Date(), "The Simpsons", new ArrayList<Theater>(List.of(new Theater(1), new Theater(2), new Theater(3))));
		showtimes.addShowtime(show1);
		showtimes.addShowtime(show2);
		showtimes.addShowtime(show3);
		showtimes.addShowtime(show4);
		showtimes.listShowtimes();
	}
}
