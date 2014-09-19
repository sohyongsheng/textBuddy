package textbuddy.ui;

import java.util.ArrayList;
import java.util.Collections;

public class TaskManager {
	ArrayList<Task> tasks;
	
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

	public void sort() {
		Collections.sort(tasks, Task.descriptionComparator);
	}

}