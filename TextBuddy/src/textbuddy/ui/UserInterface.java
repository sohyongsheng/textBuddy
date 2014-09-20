package textbuddy.ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Implements the user interface for TextBuddy.
 * <p>
 * This class essentially bridges the gap between the machinery handling task
 * operations, that is the <code>Task Manager</code> class, as well as the
 * machinery handling log files, that is the <code>LogFileManager</code> class
 *
 * @author Soh Yong Sheng
 */
public class UserInterface {
	private static final String ERROR_INVALID_COMMAND = "Error: Invalid command."
			+ " Supported commands are <exit>, <display>, <add>, <delete>,"
			+ " <clear>, <search> and <sort>";
	private static final String MSG_WELCOME = "Welcome to TextBuddy.";
	private static final String MSG_PROMPT = "command: ";
	private static final String MSG_DELETED_FROM = "Deleted from ";
	private static final String MSG_ADDED_TO = "Added to ";
	private static final String MSG_SEARCH_MATCHES = " search match(es) found:\n";
	private static final String MSG_SORTED = " tasks sorted:\n";
	private static final String MSG_ALL_CLEARED = "All content cleared from ";
	private static final String MSG_EXIT = "Exiting TextBuddy.";

	private static final String CMD_SEARCH = "search";
	private static final String CMD_DELETE = "delete";
	private static final String CMD_ADD = "add";
	private static final String CMD_SORT = "sort";
	private static final String CMD_CLEAR = "clear";
	private static final String CMD_DISPLAY = "display";
	private static final String CMD_EXIT = "exit";

	TaskManager tbTaskManager;
	LogFileManager tbLogFileManager;

	/**
	 * Returns the constructor for the <code>UserInterface</code> class. Also
	 * gets the name for the log file.
	 * 
	 * @param logFileName
	 *            the name of log file selected for task logging.
	 */
	public UserInterface(String logFileName) {
		tbTaskManager = new TaskManager();
		tbLogFileManager = new LogFileManager(logFileName);
	}

	/**
	 * Executes the relevant functionality given a valid <code>Command</code>
	 * object and returns the a status message describing the status of
	 * execution, for example whether the deletion of a task was successful. The
	 * <code>Command</code> object must be valid, in the sense that the command
	 * actions and parameters must be valid for execution to be successful.
	 * <p>
	 * In addition, the status message is the very reason for taking in the log
	 * file name as a parameter
	 * 
	 * @param inputCommand
	 *            the command to be executed
	 * @param fileName
	 *            the name of the log file
	 * @return a status message describing the status of command execution
	 * @throws NoSuchMethodException
	 *             if the command action in the input command is invalid
	 */
	public String executeCommand(Command inputCommand, String fileName)
			throws NoSuchMethodException {

		switch (inputCommand.getCommandAction()) {
		// commands with no parameter
		case CMD_EXIT:
			return MSG_EXIT;

		case CMD_DISPLAY:
			return tbTaskManager.toString();

		case CMD_CLEAR:
			this.tbTaskManager.removeAllTasks();
			return MSG_ALL_CLEARED + fileName;

		case CMD_SORT:
			this.tbTaskManager.sort();
			int numberOfTasks = this.tbTaskManager.getTasks().size();
			String displayTasks = this.tbTaskManager.toString();
			return numberOfTasks + MSG_SORTED + displayTasks;

			// commands with parameter
		case CMD_ADD:
			Task taskToAdd = new Task(inputCommand.getCommandParameter());
			this.tbTaskManager.addTask(taskToAdd);
			return MSG_ADDED_TO + fileName + ": " + taskToAdd.getDescription();

		case CMD_DELETE:
			String deletionIndexString = inputCommand.getCommandParameter();
			int deletionIndex = Integer.parseUnsignedInt(deletionIndexString);
			String deletedTaskDescription = tbTaskManager.getTasks()
					.get(deletionIndex - 1).getDescription();
			this.tbTaskManager.removeTask(deletionIndex);
			return MSG_DELETED_FROM + fileName + ": " + deletedTaskDescription;

		case CMD_SEARCH:
			String searchString = inputCommand.getCommandParameter();
			int numberOfSearchMatches = this.tbTaskManager.search(searchString)
					.size();
			String searchResults = this.tbTaskManager.searchResultsToString();
			return numberOfSearchMatches + MSG_SEARCH_MATCHES + searchResults;

		default:
			// for all other unrecognised commands
			throw new NoSuchMethodException();
		}

	}

	/**
	 * Takes in a <code>String</code> message and prints it onto the screen
	 * 
	 * @param message
	 *            message to be printed on screen
	 */
	public void showToUser(String message) {
		System.out.println(message);
	}

	/**
	 * Shows a message to prompt user for command input
	 */
	public void prompt() {
		System.out.print(MSG_PROMPT);
	}

	/**
	 * Returns true if user has keyed in an exit <code>Command</code>, and false
	 * otherwise. This boolean method allows the program to continually prompt
	 * the user for more commands until it turns false.
	 * 
	 * @param inputCommand
	 *            input command from user
	 * @return
	 */
	public boolean isExit(Command inputCommand) {
		if (inputCommand.getCommandAction().equals(CMD_EXIT)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Entry point for TextBuddy program. This program allows users to key in
	 * tasks and organises them into a log file. Supported actions are 'exit',
	 * 'display', 'clear', 'add', 'delete', 'sort' and 'clear'. This program is
	 * invoked along with the name of log file. If the log file is exists, this
	 * program loads in the previously logged tasks. Otherwise, a new log file
	 * is created and tasks are written to it.
	 * 
	 * @param args
	 *            contains the name of the log file
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String logFileName = args[0];
		UserInterface tbUserInterface = new UserInterface(logFileName);
		tbUserInterface.showToUser(MSG_WELCOME);
		String prepareLogFile = tbUserInterface.tbLogFileManager
				.prepareLogFile(tbUserInterface.tbTaskManager);
		tbUserInterface.showToUser(prepareLogFile);

		Scanner userInput = new Scanner(System.in);
		boolean isExit = false;
		while (!isExit) {
			tbUserInterface.prompt();
			Command inputCommand = new Command(userInput.nextLine());

			String statusMessage;
			try {
				statusMessage = tbUserInterface.executeCommand(inputCommand,
						logFileName);
			} catch (NoSuchMethodException e) {
				statusMessage = ERROR_INVALID_COMMAND;
			}

			tbUserInterface.showToUser(statusMessage);
			isExit = tbUserInterface.isExit(inputCommand);

			String tasks = tbUserInterface.tbTaskManager.toString();
			tbUserInterface.tbLogFileManager.writeToLogFile(tasks);
		}
		userInput.close();

	}

}
