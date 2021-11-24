package theater_project_code;

public class command_manager {
	private linked_list valid_commands = new linked_list();
	
	//default constructor for the command manager has no valid commands
	public command_manager() {
		
	}
	//constructor given input command and initial valid_commands
	public command_manager(String input_command, String[] valid_commands_import) {
		for (int i = 0; i < valid_commands_import.length - 1; i++) {
			valid_commands.insert(valid_commands, valid_commands_import[i]);
		}
	}
	//validates if a command is in the system, returns true if it is, false if it
	public boolean validate_command(String keyword) {
		if (valid_commands.contains(valid_commands, keyword)) {
			return true;
		}
		return false;
	}
	//list valid commands for this class
	public void list_valid_commands() {
		valid_commands.print(valid_commands);
	}
	
	public void delete_valid_command(String keyword) {
		valid_commands.delete(valid_commands, keyword);
	}
	
	public void add_valid_command(String keyword) {
		valid_commands.insert(valid_commands, keyword);
	}
	
	public void run_command() {
		
	}
}
