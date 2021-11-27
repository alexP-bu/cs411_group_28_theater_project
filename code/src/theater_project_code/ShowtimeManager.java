package theater_project_code;
import java.util.ArrayList;

public class ShowtimeManager {
	private ArrayList<Showtime> showtimes;
	
	/*
	 * constructors
	 */
	public ShowtimeManager() {
		showtimes = new ArrayList<Showtime>();
	}
	
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
}
