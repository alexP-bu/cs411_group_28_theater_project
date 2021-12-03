package theaterProjectCode;

import java.util.Scanner;

public class user_interface {

	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		/*
		 * initialize system objects
		 */

		String[] commandList = { "login", "logout", "newAccount", "newEmployee", "newAdmin", "newTheater",
				"newShowtime", "deleteAccount", "deleteTheater", "deleteShowtime", "listAccounts", "listTheaters",
				"listShowtimes", "viewAccount", "viewTheater", "viewShowtime", "purchaseTicket", "clearAccountsData",
				"clearTheatersData", "clearShowtimesData", "updateAccountDatabase", "updateTheatersData",
				"updateShowtimesData", "exit" };
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
			// check for valid user input command
			if (commandManager.validateCommand(command)) {
				System.out.println("Valid Command! Running...");
				commandManager.runCommand(command);
			} else {
				System.out.println("Invalid command entered. Please try again.");
			}
		} while (!command.equals("exit"));

		System.out.println("Goodbye.");
		reader.close();
	}

	public static void printWelcomeMessage() {
		for (int i = 0; i < 40; i++) {
			System.out.print("*");
		}
		System.out.println("\n\t\tHello \n\tWelome to TheaterOS!");
		for (int i = 0; i < 40; i++) {
			System.out.print("*");
		}
	}

	public static void printInstructions() {
		System.out.println();
		System.out.println("Commands currently available:");
		String str = "         __________________________________________________________________   " + "\n"
				+ "        / newAccount     / listAccounts   / clearAccountsData  / login  /   " + "\n"
				+ "       / newAdmin       / listTheaters   / clearTheatersData  / logout /    " + "\n"
				+ "      / newEmployee    / listShowtimes  / clearShowtimesData / exit   /     " + "\n"
				+ "     / newTheater     / viewAccount    / updateAccountsData /        /      " + "\n"
				+ "    / newShowtime    / viewTheater    / updateTheatersData /        /       " + "\n"
				+ "   / deleteAccount  / viewShowtime   /updateShowtimesData /        /        " + "\n"
				+ "  / deleteTheater  / purchaseTicket /                    /        /         " + "\n"
				+ " / deleteShowtime / 				  /                    /        /         " + "\n"
				+ "/________________/________________/____________________/________/          " + "\n"
				+ "                                                                                                         ";
		System.out.println(str);
	}
}
