package textbuddy.ui;

public class UserInterface {
	TaskManager textBuddy;
	

	public UserInterface() {
		textBuddy = new TaskManager();
	}


	public void mapCommandToMethod(Command inputCommand) {
		switch (inputCommand.getCommandAction())
		{
			// commands with no parameter
			case "exit" :
				System.exit(0);
				break;
				
			case "display" :
				System.out.println(textBuddy.toString());
				break;
				
			case "clear" :
				this.textBuddy.removeAllTasks();
				break;
			
			// commands with parameter
			case "add" :
				Task taskToAdd = new Task(inputCommand.getCommandParameter());
				this.textBuddy.addTask(taskToAdd);
				break;
				
			case "delete" :
				String deletionIndexString = inputCommand.getCommandParameter();
				int deletionIndex = Integer.parseUnsignedInt(deletionIndexString);
				textBuddy.removeTask(deletionIndex);
				break;
			
			// for all other unrecognised commands
			default:		
				// TODO:throw exception: bad commands
//				throw new IllegalArgumentException("Error: bad command. Supported commands are <exit>, <display>, <add>, <delete> and <clear>.");
				break;	  
		}
		
	}
}
