package textbuddy.ui;

import java.util.Comparator;

public class Task {
	String description;

	public Task(String inputDescription) {
		this.description = inputDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String inputDescription) {
		this.description = inputDescription;
	}

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
