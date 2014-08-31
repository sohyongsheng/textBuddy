package textbuddy.ui;

public class Command {
	String commandAction;
	String commandParameter;
	
	
	public String getCommandAction() {
		return commandAction;
	}
	
	public void setCommandAction(String inputCommandAction) {
		this.commandAction = inputCommandAction;
	}
	
	public String getCommandParameters() {
		return commandParameter;
	}
	
	public void setCommandParameters(String inputCommandParameter) {
		
		commandParameter = inputCommandParameter;		
	}
	
	public static String[] splitCommandLine(String inputCommandLine) {
		return inputCommandLine.split(" ", 2);
	}
	
	public Command(String inputCommandString) {
		String[] inputCommand = splitCommandLine(inputCommandString);
		this.setCommandAction(inputCommand[0]);
		if (inputCommand.length > 1) {
			this.setCommandParameters(inputCommand[1]);
		}
	}

	
	
}
