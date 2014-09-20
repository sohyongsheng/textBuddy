package textbuddy.ui;

import java.util.ArrayList;
import java.util.Collections;

public class TaskManager {
    private static final int SIZE_OF_EMPTY_LIST = 0;
    private static final int DIFFERENCE_BETWEEN_LIST_INDEX_AND_ARRAY_LIST_INDEX = 1;
    private static final String MSG_PROMPT = "command: ";
    private static final String MSG_ALL_TASKS = "All tasks:";
    private static final String MSG_NO_TASKS = "No tasks at hand.";
    private static final String MSG_SEARCH_RESULTS_HEADER = "All search results:";
    private static final String MSG_NO_SEARCH_MATCH_FOUND = "No search match found.";

    ArrayList<Task> tasks;
    ArrayList<Task> searchResults;

    /**
     * Constructor for TaskManager. Creates a list of tasks which can be
     * manipulated easily.
     */
    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Returns the list of tasks stored inside this class as an
     * <code>ArrayList</code> of <code>Tasks</code>s
     * 
     * @return the list of tasks stored this class
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the existing list of tasks.
     */
    public void addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    /**
     * Remove a task, specified by the deletion index, from the existing list of
     * tasks. The deletion index must be valid, that is, an <code>int</code>
     * from 1 to the current number of tasks.
     */
    public void removeTask(int listIndex) {
        tasks.remove(listIndex
                - DIFFERENCE_BETWEEN_LIST_INDEX_AND_ARRAY_LIST_INDEX);
    }

    /**
     * Removes all tasks. User should use with caution as no warning prompt for
     * this functionality is implemented yet
     */
    public void removeAllTasks() {
        tasks.clear();
    }

    /**
     * Formats the list of tasks into a string which can be directly displayed
     * using the display command or written directly to the log file. If there
     * are no tasks, this method returns a message informing the user that there
     * are no tasks
     * 
     * @return string detailing all tasks
     */
    public String toString() {
        if (tasks.size() == SIZE_OF_EMPTY_LIST) {
            return MSG_NO_TASKS;
        } else {
            String displayAllTasks = MSG_ALL_TASKS;
            for (Task aTask : tasks) {
                int listIndex = tasks.indexOf(aTask)
                        + DIFFERENCE_BETWEEN_LIST_INDEX_AND_ARRAY_LIST_INDEX;
                displayAllTasks = displayAllTasks + "\n" + listIndex + ". "
                        + aTask.getDescription();
            }
            return displayAllTasks;
        }
    }

    /**
     * Prompts user to type in commands.
     */
    public void prompt() {
        System.out.print(MSG_PROMPT);
    }

    /**
     * Sorts list of tasks in this class in ascending alphabetical order of the
     * first alphabets of task descriptions. The order of tasks in the list of
     * this class is then changed permanently.
     */
    public void sort() {
        Collections.sort(tasks, Task.descriptionComparator);
    }

    /**
     * Searches for a string within all task descriptions and returns a list of
     * tasks whose descriptions match the search string. The search string can
     * carry more than one word. The formatting of the search results is
     * abstracted away, in case future developers want to change the format of
     * display for search results.
     * 
     * @param searchString
     *            the search string
     * @return the list of tasks whose task descriptions contain the search
     *         string
     */
    public ArrayList<Task> search(String searchString) {
        searchResults = new ArrayList<Task>();
        for (Task aTask : this.getTasks()) {
            if (aTask.getDescription().contains(searchString)) {
                searchResults.add(aTask);
            }
        }
        return searchResults;
    }

    /**
     * Formats the search results from a list of tasks to a string message and
     * returns the latter to be displayed.
     * 
     * @return the search results as a string
     */
    public String searchResultsToString() {
        if (searchResults.size() == 0) {
            return MSG_NO_SEARCH_MATCH_FOUND;
        } else {
            String displayAllSearchResults = MSG_SEARCH_RESULTS_HEADER;
            for (Task aTask : searchResults) {
                int listIndex = searchResults.indexOf(aTask)
                        + DIFFERENCE_BETWEEN_LIST_INDEX_AND_ARRAY_LIST_INDEX;
                displayAllSearchResults = displayAllSearchResults + "\n"
                        + listIndex + ". " + aTask.getDescription();
            }
            return displayAllSearchResults;
        }
    }

}