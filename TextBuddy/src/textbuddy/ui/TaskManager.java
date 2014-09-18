package textbuddy.ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static final int NUMBER_OF_PARTS_MAKING_COMMAND_LINE = 2;
    ArrayList<Task> tasks;
    private static TaskManager textBuddy;

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    public void removeTask(int taskUserIndexToRemove) {
        int taskActualArrayIndexToRemove = taskUserIndexToRemove - 1;
        tasks.remove(taskActualArrayIndexToRemove);
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public String toString() {
        if (tasks.size() == 0) {
            return "No tasks at hand.";
        } else {
            String displayAllTasks = "All tasks:";
            for (Task aTask : tasks) {
                int taskUserIndex = tasks.indexOf(aTask) + 1;
                displayAllTasks = displayAllTasks + "\n" + taskUserIndex + ". "
                        + aTask.getDescription();
            }
            return displayAllTasks;
        }
    }

    public void prompt() {
        System.out.print("command: ");
    }

    public static void parseCommandLines(TaskManager textBuddy) {
        Scanner commandLineScanner = new Scanner(System.in);

        for (textBuddy.prompt(); commandLineScanner.hasNextLine(); textBuddy
                .prompt()) {
            String commandLine = commandLineScanner.nextLine().replaceAll("\n",
                    "");
            String[] commandLineChunks = commandLine.split(" ",
                    NUMBER_OF_PARTS_MAKING_COMMAND_LINE);

            String commandAction = commandLineChunks[0];
            String commandParameter = null;
            if (commandLineChunks.length > 1) {
                commandParameter = commandLineChunks[1];
            }
            mapCommandsToFunctions(textBuddy, commandAction, commandParameter);
        }
        commandLineScanner.close();
    }

    public static void mapCommandsToFunctions(TaskManager textBuddy,
            String commandAction, String commandParameter) {
        switch (commandAction) {
        // commands with no parameter
        case "exit":
            System.exit(0);
            break;

        case "display":
            System.out.println(textBuddy.toString());
            break;

        case "clear":
            textBuddy.removeAllTasks();

            // commands with parameter
        case "add":
            Task taskToAdd = new Task(commandParameter);
            textBuddy.addTask(taskToAdd);
            break;

        case "delete":
            int taskUserIndexToRemove = Integer
                    .parseUnsignedInt(commandParameter);
            textBuddy.removeTask(taskUserIndexToRemove);
            break;

        default:
            System.err
                    .println("Error: bad command. Supported commands are <exit>, <display>, <add>, <delete> and <clear>.");
            break;
        }
    }

    public static void main(String[] args) {
        textBuddy = new TaskManager();
        parseCommandLines(textBuddy);
    }

}