package textbuddy.ui;

import java.util.Comparator;

/**
 * Encapsulates the task description in the <code>Task</code> object. This may
 * look unnecessary since there is only a single string field holding the task
 * description. However, future developers may want to include fields like
 * dates, times, and so on, which calls for such encapsulation.
 * 
 * @author Soh Yong Sheng
 *
 */
public class Task {
    String description;

    /**
     * Constructor for a <code>Task</code>, which initialises the task
     * description.
     * 
     * @param inputDescription
     *            task description
     */
    public Task(String inputDescription) {
        this.description = inputDescription;
    }

    /**
     * Returns the task description as a string
     * 
     * @return the task description as a string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task. Though 'edit' is not yet a supported
     * functionality of this program, this method may become more useful when
     * 'edit' is implemented.
     * 
     * @param inputDescription
     *            the task description
     */
    public void setDescription(String inputDescription) {
        this.description = inputDescription;
    }

    /**
     * Compares the alphabetical order, ignoring case and in ascending order, of
     * the first alphabets of the task descriptions between two tasks. This
     * method is used for the 'sort' functionality, where tasks are sorted in
     * ascending alphabetical order of the first alphabets of their task
     * descriptions.
     */
    public static Comparator<Task> descriptionComparator = new Comparator<Task>() {
        @Override
        public int compare(Task firstTask, Task secondTask) {
            String firstTaskDescription = firstTask.getDescription();
            String secondTaskDescription = secondTask.getDescription();
            return firstTaskDescription
                    .compareToIgnoreCase(secondTaskDescription);
        }
    };
}
