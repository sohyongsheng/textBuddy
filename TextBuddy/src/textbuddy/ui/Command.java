package textbuddy.ui;

/**
 * Parses <code>String</code> user commands into command actions and parameters
 * and encapsulates them neatly into this class.
 * 
 * @author Soh Yong Sheng
 */
public class Command {
	private static final int ARRAY_INDEX_OF_COMMAND_PARAMETER = 1;
	private static final int ARRAY_INDEX_OF_COMMAND_ACTION = 0;
	private static final int NUMBER_OF_SPLIT_STRINGS_FOR_COMMANDS_WITH_NO_PARAMETERS = 1;
	private static final int NUMBER_OF_SPLIT_STRINGS = 2;
	private static final String WHITE_SPACE = " ";

	String commandAction;
	String commandParameter;

	/**
	 * Returns the parsed and stored command action as a <code>String</code>.
	 * For example, the command action of "add this thing" is 'add'.
	 * 
	 * @return the action of this command
	 */
	public String getCommandAction() {
		return commandAction;
	}

	/**
	 * Sets the action of this command.
	 * 
	 * @param inputCommandAction
	 *            the action of this command
	 */
	public void setCommandAction(String inputCommandAction) {
		this.commandAction = inputCommandAction;
	}

	/**
	 * Returns the parsed and stored command parameter as a <code>String</code>.
	 * For example, the command parameter of "add this thing" is "this thing".
	 * 
	 * @return the parameter of this command
	 */
	public String getCommandParameter() {
		return commandParameter;
	}

	/**
	 * Sets the parameter of this command
	 * 
	 * @param inputCommandParameter
	 *            the parameter of this command
	 */
	public void setCommandParameter(String inputCommandParameter) {

		commandParameter = inputCommandParameter;
	}

	/**
	 * Splits a <code>String</code> input into two using the first whitespace as
	 * the delimiter, and stores them in a <code>String</code> array before
	 * returning the <code>String</code> array. For example, "add this thing"
	 * becomes split into 'add' and "this thing".
	 * 
	 * @param inputCommandLine
	 *            <code>String</code> input to be split
	 * @return THE <code>String</code> array storing the split strings
	 */
	public static String[] splitCommandLine(String inputCommandLine) {
		return inputCommandLine.split(WHITE_SPACE, NUMBER_OF_SPLIT_STRINGS);
	}

	/**
	 * Constructor for the <code>Command</code> object. Parses an input string
	 * into command action and parameter, and stores them appropriately.
	 * 
	 * @param inputCommandString
	 *            input string to be parsed
	 */
	public Command(String inputCommandString) {
		String[] inputCommand = splitCommandLine(inputCommandString);
		this.setCommandAction(inputCommand[ARRAY_INDEX_OF_COMMAND_ACTION]);
		if (inputCommand.length > NUMBER_OF_SPLIT_STRINGS_FOR_COMMANDS_WITH_NO_PARAMETERS) {
			this.setCommandParameter(inputCommand[ARRAY_INDEX_OF_COMMAND_PARAMETER]);
		}
	}

}
