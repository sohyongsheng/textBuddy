package textbuddy.ui;

import java.util.Scanner;

public class UserInterface {
    TaskManager textBuddy;

    public UserInterface() {
        textBuddy = new TaskManager();
    }

    public String executeCommand(Command inputCommand, String fileName) {
        switch (inputCommand.getCommandAction()) {
        // commands with no parameter
        case "exit":
            return "Exiting TextBuddy.";

        case "display":
            return textBuddy.toString();

        case "clear":
            this.textBuddy.removeAllTasks();
            return "All content cleared from " + fileName;

            // commands with parameter
        case "add":
            Task taskToAdd = new Task(inputCommand.getCommandParameter());
            this.textBuddy.addTask(taskToAdd);
            return "Added to " + fileName + ": " + taskToAdd.getDescription();

        case "delete":
            String deletionIndexString = inputCommand.getCommandParameter();
            int deletionIndex = Integer.parseUnsignedInt(deletionIndexString);
            String deletedTaskDescription = textBuddy.getTasks()
                    .get(deletionIndex - 1).getDescription();
            textBuddy.removeTask(deletionIndex);
            return "Deleted from " + fileName + ": " + deletedTaskDescription;

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
        if (inputCommand.getCommandAction().equals("exit")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String logFileName = args[0];
        UserInterface textBuddyUi = new UserInterface();
        textBuddyUi.showToUser("Welcome to TextBuddy.");

        Scanner userInput = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            textBuddyUi.prompt();
            Command inputCommand = new Command(userInput.nextLine());
            String statusMessage = textBuddyUi.executeCommand(inputCommand, logFileName);
            textBuddyUi.showToUser(statusMessage);
            isExit = textBuddyUi.decideWhetherToExit(inputCommand);
        }
        userInput.close();

    }

}
