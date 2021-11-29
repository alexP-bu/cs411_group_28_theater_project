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
								"viewTheater","newShowtime","viewShowtime","deleteShowtime"
		
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
		System.out.println("Hello, and welome to the theater!");
		System.out.println("*********************************");
		System.out.println();
	}
	
	public static void printInstructions() {
		System.out.println();
		System.out.println("Commands currently available:");
		String str =  "      ______________________________________________________________________________________________  " + "\n" +
	                  "     /  newAccount   / newAdmin / newEmployee / clearAccountsData / login / logout /               /  " + "\n" +
	                  "    / listAccounts  /  updateAccountDatabase /  deleteAccount    /  viewAccount   /     exit      /   " + "\n" +		
	                  "   / listTheaters  /       newTheater       /   deleteTheater   /  viewTheater   /               /    " + "\n" +
	                  "  / listShowtimes /       newShowtime      /  deleteShowtime   /  viewShowtime  /               /     " + "\n" +
		              " /  reserveSeat  /                        /                   /                /               /      " + "\n" +
		              "/_______________/________________________/___________________/________________/_______________/       " + "\n" +
		              "                                                                                                      ";
		System.out.println(str);
	}
}
