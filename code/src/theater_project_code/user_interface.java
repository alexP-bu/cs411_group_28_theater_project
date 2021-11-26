package theater_project_code;

import java.util.Scanner;

public class user_interface {
	
	public static void main(String[] args) {
		/*
		 * initialize system objects
		 */
		
		String[] commandList = {"login", "logout", "new", "delete", "listAccounts", "newAdmin", "clearData"};
		CommandManager commandManager = new CommandManager(commandList);
		Scanner readInput = new Scanner(System.in);
		String command = "";
		printWelcomeMessage();
		
		/*
		 * program loop
		 */
		
		do {
		printInstructions();
		command = readInput.nextLine();
		System.out.printf("The command you entered is: %s \n", command);
		//check for valid user input command
		if(commandManager.validateCommand(command)) {
			commandManager.runCommand(command);
		}else {
			System.out.println("Invalid command entered. Please try again.");
		}
		}while(!command.equals("exit"));
		
		System.out.println("Goodbye.");
		readInput.close();
	}
	
	public static void printWelcomeMessage() {
		System.out.println("*********************************");
		System.out.println("Hello, and welome to the theater!");
		System.out.println("*********************************");
		System.out.println();
	}
	
	public static void printInstructions() {
		System.out.println();
		System.out.println("To login to your account, please type \"login\"");
		System.out.println("To logout of your account, please type \"logout\"");
		System.out.println("To view showtimes, please type \"showtimes\"");
		System.out.println("To create a new account, please type \"new\"");
		System.out.println("To shut down the system, please type \"exit\"\n");
	}
}
