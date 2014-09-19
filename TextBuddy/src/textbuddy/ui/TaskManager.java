package textbuddy.ui;

import java.util.ArrayList;
import java.util.Collections;

public class TaskManager {
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
		System.out.print("command: ");
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
		// TODO Auto-generated method stub
		return null;
	}

}