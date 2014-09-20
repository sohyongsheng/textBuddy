package textbuddy.ui;

import java.util.ArrayList;
import java.util.Collections;

public class TaskManager {
	private static final int DIFFERENCE_BETWEEN_LIST_INDEX_AND_ARRAY_LIST_INDEX = 1;
	private static final String MSG_PROMPT = "command: ";
	private static final String MSG_SEARCH_RESULTS_HEADER = "All search results:";
	private static final String MSG_NO_SEARCH_MATCH_FOUND = "No search match found.";
	private static final String MSG_NO_TASKS = "No tasks at hand.";

	ArrayList<Task> tasks;
	private ArrayList<Task> searchResults;

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
			return MSG_NO_TASKS;
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
		System.out.print(MSG_PROMPT);
	}

	public void sort() {
		Collections.sort(tasks, Task.descriptionComparator);
	}

	public ArrayList<Task> search(String searchString) {
		searchResults = new ArrayList<Task>();
		for (Task aTask : this.getTasks()) {
			if (aTask.getDescription().contains(searchString)) {
				searchResults.add(aTask);
			}
		}
		return searchResults;
	}

	public String searchResultsToString() {
		if (searchResults.size() == 0) {
			return MSG_NO_SEARCH_MATCH_FOUND;
		} else {
			String displayAllSearchResults = MSG_SEARCH_RESULTS_HEADER;
			for (Task aTask : searchResults) {
				int listIndex = searchResults.indexOf(aTask) + DIFFERENCE_BETWEEN_LIST_INDEX_AND_ARRAY_LIST_INDEX;
				displayAllSearchResults = displayAllSearchResults + "\n"
						+ listIndex + ". " + aTask.getDescription();
			}
			return displayAllSearchResults;
		}
	}

}