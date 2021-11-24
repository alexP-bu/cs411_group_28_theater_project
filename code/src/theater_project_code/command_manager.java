package theater_project_code;
import java.util.LinkedList;

public class command_manager {
	private LinkedList<String> valid_commands = new LinkedList<String>();
	
	//default constructor for the command manager has no valid commands
	public command_manager() {
		
	}
	//constructor given input command and initial valid_commands
	public command_manager(String input_command, String[] valid_commands_import) {
		for (int i = 0; i < valid_commands_import.length - 1; i++) {
			valid_commands.add(valid_commands_import[i]);
		}
	}
	//validates if a command is in the system, returns true if it is, false if it
	public boolean validate_command(String keyword) {
		if (valid_commands.contains(keyword)) {
			return true;
		}
		return false;
	}
	//list valid commands for this class
	public void list_valid_commands() {
		System.out.println(valid_commands.toString());
	}
	
	public void delete_valid_command(String keyword) {
		valid_commands.remove(keyword);
	}
	
	public void add_valid_command(String keyword) {
		valid_commands.add(keyword);
	}
	
	public void run_command() {
		
	}
}
