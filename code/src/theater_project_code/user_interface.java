package theater_project_code;

import java.util.Scanner;

public class user_interface {
	
	public static void main(String[] args) {
		System.out.println("*********************************");
		System.out.println("Hello, and welome to the theater!");
		System.out.println("*********************************");
		
		System.out.println("To login to your account, please type \"login\"");
		System.out.println("To view showtimes, please type \"showtimes\"");
		System.out.println("To create a new account, please type \"new\"");
		System.out.println("To shut down the system, please type \"exit\"");
		
		String command = "";
		Scanner readInput = new Scanner(System.in);
		
		do {
		command = readInput.nextLine();
		System.out.printf("The command you entered is: %s\n", command);
		//check for valid user input
		
		
		}while(!command.equals("exit"));
		
		readInput.close();
	}
}
