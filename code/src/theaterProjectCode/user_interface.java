package theaterProjectCode;
import java.util.Scanner;

public class user_interface {
	
	public static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args) {
		/*
		 * initialize system objects
		 */
		
		String[] commandList = {"login", "logout", "newAccount", "deleteAccount", 
								"listAccounts", "newAdmin", "clearAccountsData",
								"updateAccountDatabase","viewAccountData", "exit",
								"listShowtimes","listTheaters","newTheater","deleteTheater",
								"viewTheater","newShowtime","viewShowtime","deleteShowtime",
								"purchaseTicket","clearTheaterData","updateTheaterData"
		
																		};
		CommandManager commandManager = new CommandManager(commandList);
		String command = "";
		printWelcomeMessage();
		
		/*
		 * program loop
		 */
		
		do {
		printInstructions();
		command = reader.nextLine();
		System.out.printf("The command you entered is: %s \n", command);
		//check for valid user input command
		if(commandManager.validateCommand(command)) {
			System.out.println("Valid Command! Running...");
			commandManager.runCommand(command);
		}else {
			System.out.println("Invalid command entered. Please try again.");
		}
		}while(!command.equals("exit"));
		
		System.out.println("Goodbye.");
		reader.close();
	}
	
	public static void printWelcomeMessage() {
		System.out.println("*********************************");
		System.out.println("Hello, and welome to TheaterOS!");
		System.out.println("*********************************");
		System.out.println();
	}
	
	public static void printInstructions() {
		System.out.println();
		System.out.println("Commands currently available:");
		String str =  "         __________________________________________________________________   " + "\n" +
	                  "        / newAccount     / listAccounts   / clearAccountsData  / login  /   " + "\n" +
	                  "       / newAdmin       / listTheaters   / clearTheatersData  / logout /    " + "\n" +		
	                  "      / newEmployee    / listShowtimes  / clearShowtimesData / exit   /     " + "\n" +
	                  "     / newTheater     / viewAccount    / updateAccountsData /        /      " + "\n" +
		              "    / newShowtime    / viewTheater    / updateTheatersData /        /       " + "\n" +
		              "   / deleteAccount  / viewShowtime   / updateShowtimeData /        /        " + "\n" +
	                  "  / deleteTheater  / purchaseTicket /                    /        /         " + "\n" +
	                  " / deleteShowtime / purchaseTicket /                    /        /         " + "\n" +
		              "/________________/________________/____________________/________/          " + "\n" +
		              "                                                                                                         ";
		System.out.println(str);
	}
}
