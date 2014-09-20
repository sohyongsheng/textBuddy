package textbuddy.ui;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
	private static final String MSG_SEARCH_MATCHES = " search match(es) found:\n";
	private static final String CMD_SEARCH = "search";
	private static final String MSG_SORTED = " tasks sorted:\n";
	private static final String MSG_ALL_CLEARED = "All content cleared from ";
	private static final String MSG_EXIT = "Exiting TextBuddy.";
	private static final String CMD_DELETE = "delete";
	private static final String CMD_ADD = "add";
	private static final String CMD_SORT = "sort";
	private static final String CMD_CLEAR = "clear";
	private static final String CMD_DISPLAY = "display";
	private static final String CMD_EXIT = "exit";
	TaskManager textBuddy;
	LogFileManager textBuddyLogFileManager;

	public UserInterface(String logFileName) {
		textBuddy = new TaskManager();
		textBuddyLogFileManager = new LogFileManager(logFileName);
	}

	public String executeCommand(Command inputCommand, String fileName) {
		
		switch (inputCommand.getCommandAction()) {
		// commands with no parameter
		case CMD_EXIT:
			return MSG_EXIT;

		case CMD_DISPLAY:
			return textBuddy.toString();

		case CMD_CLEAR:
			this.textBuddy.removeAllTasks();
			return MSG_ALL_CLEARED + fileName;

		case CMD_SORT:
			this.textBuddy.sort();
			int numberOfTasks = this.textBuddy.getTasks().size();
			String displayTasks = this.textBuddy.toString();
			return numberOfTasks + MSG_SORTED + displayTasks;

			// commands with parameter
		case CMD_ADD:
			Task taskToAdd = new Task(inputCommand.getCommandParameter());
			this.textBuddy.addTask(taskToAdd);
			return "Added to " + fileName + ": " + taskToAdd.getDescription();

		case CMD_DELETE:
			String deletionIndexString = inputCommand.getCommandParameter();
			int deletionIndex = Integer.parseUnsignedInt(deletionIndexString);
			String deletedTaskDescription = textBuddy.getTasks()
					.get(deletionIndex - 1).getDescription();
			this.textBuddy.removeTask(deletionIndex);
			return "Deleted from " + fileName + ": " + deletedTaskDescription;
			
		case CMD_SEARCH:
			String searchString = inputCommand.getCommandParameter();
			int numberOfSearchMatches = this.textBuddy.search(searchString).size();
			String searchResults = this.textBuddy.searchResultsToString();
			return numberOfSearchMatches + MSG_SEARCH_MATCHES + searchResults;

			// for all other unrecognised commands
		default:
			return null;
			// TODO see if can implement this correctly
			// throw new
			// NoSuchMethodException("Error: bad command. Supported commands are <exit>, <display>, <add>, <delete> and <clear>.");
		}

	}

	public void showToUser(String message) {
		System.out.println(message);
	}

	public void prompt() {
		System.out.print("command: ");
	}

	public boolean decideWhetherToExit(Command inputCommand) {
		if (inputCommand.getCommandAction().equals(CMD_EXIT)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		String logFileName = args[0];
		UserInterface textBuddyUi = new UserInterface(logFileName);
		textBuddyUi.showToUser("Welcome to TextBuddy.");
		
		String prepareLogFile = textBuddyUi.textBuddyLogFileManager.prepareLogFile(textBuddyUi.textBuddy);
		textBuddyUi.showToUser(prepareLogFile);

		Scanner userInput = new Scanner(System.in);
		boolean isExit = false;
		while (!isExit) {
			textBuddyUi.prompt();
			Command inputCommand = new Command(userInput.nextLine());
			String statusMessage = textBuddyUi.executeCommand(inputCommand,
					logFileName);
			textBuddyUi.showToUser(statusMessage);
			isExit = textBuddyUi.decideWhetherToExit(inputCommand);
			
			String tasks = textBuddyUi.textBuddy.toString();
			textBuddyUi.textBuddyLogFileManager.writeToLogFile(tasks);
		}
		userInput.close();

	}

}
