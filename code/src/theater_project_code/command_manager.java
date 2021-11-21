package theater_project_code;

public class command_manager {
	private String input_command;
	
	private String[] valid_commands;
	
	
	//default constructor for the command manager has no valid commands
	public command_manager() {
		
	}
	//constructor given input command and valid_commands
	public command_manager(String input_command, String[] valid_commands) {
		this.input_command = input_command;
		this.valid_commands = valid_commands;
	}
	//validates if a command is in the system, returns true if it is, false if it
	public boolean validate_command() {
		for(int i = 0; i < valid_commands.length - 1; i++) {
			if(input_command.equals(valid_commands[i])) {
				return true;
			}
		}
		return false;
	}
	//list valid commands for this class
	public void list_valid_command() {
		System.out.println("The valid commands for this command manager are:");
		for(int i = 0; i < valid_commands.length - 1; i++) {
			System.out.println(valid_commands[i]);
		}
	}
	
	public void delete_valid_command() {
		
	}
	
	public void add_valid_command() {
		
	}
	
	public void set_input_command() {
		
	}
	
}
