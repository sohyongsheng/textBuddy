package textbuddy.ui;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TaskManagerTest {
	private TaskManager textBuddy;

	private String testDescription;
	private String anotherTestDescription;
	private String taskDescriptionStartingWithAlphabetZ;
	private String taskDescriptionStartingWithCapitalAlphabetY;

	private Task testTask;
	private Task anotherTestTask;
	private Task zTask;
	private Task capitalYTask;

	public void setup() {
		textBuddy = new TaskManager();

		testDescription = new String("test description");
		anotherTestDescription = new String("another test description");

		testTask = new Task(testDescription);
		anotherTestTask = new Task(anotherTestDescription);

		taskDescriptionStartingWithAlphabetZ = "z alphabet is at the beginning of this task description";
		zTask = new Task(taskDescriptionStartingWithAlphabetZ);

		taskDescriptionStartingWithCapitalAlphabetY = "Y capital alphabet is at the beginning of this task description";
		capitalYTask = new Task(taskDescriptionStartingWithCapitalAlphabetY);
	}

	public void addItems() {
		textBuddy.addTask(testTask);
		textBuddy.addTask(anotherTestTask);
	}

	public void addMoreItemsToTestSort() {
		textBuddy.addTask(zTask);
		textBuddy.addTask(capitalYTask);
	}

	@Test
	public void testTaskManager() {
		TaskManager textBuddy = new TaskManager();
		assertTrue("tasks is not instance of ArrayList",
				textBuddy.tasks instanceof ArrayList);
		assertEquals(0, textBuddy.getTasks().size());
	}

	@Test
	public void testRemoveTasks() {
		setup();

		textBuddy.addTask(testTask);
		assertEquals(1, textBuddy.getTasks().size());
		assertEquals(0, textBuddy.getTasks().indexOf(testTask));

		textBuddy.addTask(anotherTestTask);
		assertEquals(2, textBuddy.getTasks().size());
		assertEquals(0, textBuddy.getTasks().indexOf(testTask));
		assertEquals(1, textBuddy.getTasks().indexOf(anotherTestTask));

		int taskUserIndexToRemove = 1;
		textBuddy.removeTask(taskUserIndexToRemove);
		assertEquals(1, textBuddy.getTasks().size());
		assertEquals(0, textBuddy.getTasks().indexOf(anotherTestTask));
	}

	@Test
	public void testRemoveAllTasks() {
		setup();
		addItems();

		textBuddy.removeAllTasks();
		assertEquals(0, textBuddy.getTasks().size());

		String noTasksAtHand = "No tasks at hand.";
		assertTrue(textBuddy.toString().equals(noTasksAtHand));
	}

	@Test
	public void testSort() {
		setup();
		addItems();
		addMoreItemsToTestSort();

		assertEquals("initial number of tasks is not 4", 4, textBuddy
				.getTasks().size());
		assertEquals("initial order of tasks are not correct", 1, textBuddy
				.getTasks().indexOf(anotherTestTask));
		assertEquals("initial order of tasks are not correct", 0, textBuddy
				.getTasks().indexOf(testTask));
		assertEquals("initial order of tasks are not correct", 3, textBuddy
				.getTasks().indexOf(capitalYTask));
		assertEquals("initial order of tasks are not correct", 2, textBuddy
				.getTasks().indexOf(zTask));

		textBuddy.sort();

		assertEquals("number of tasks do not remain at 4 after sorting", 4,
				textBuddy.getTasks().size());
		assertEquals("order of sorted tasks are not correct", 0, textBuddy
				.getTasks().indexOf(anotherTestTask));
		assertEquals("order of sorted tasks are not correct", 1, textBuddy
				.getTasks().indexOf(testTask));
		assertEquals("order of sorted tasks are not correct", 2, textBuddy
				.getTasks().indexOf(capitalYTask));
		assertEquals("order of sorted tasks are not correct", 3, textBuddy
				.getTasks().indexOf(zTask));
	}

	@Test
	public void testSearch() {
		setup();
		addItems();
		String searchString;
		ArrayList<Task> searchResults;
		String expectedSearchResultsToStringOutput;

		searchString = "invalid";
		assertTrue(
				"searchResults (declared in TaskManager class) is not instance of ArrayList",
				textBuddy.search(searchString) instanceof ArrayList);
		searchResults = textBuddy.search(searchString);
		assertEquals(
				"size of searchResults arraylist for 'invalid' search string is not 0",
				0, searchResults.size());
		expectedSearchResultsToStringOutput = "No search match found.";
		assertTrue(expectedSearchResultsToStringOutput.equals(textBuddy
				.searchResultsToString()));

		searchString = "another";
		searchResults = textBuddy.search(searchString);
		assertEquals("size of searchResults arraylist is not 1", 1,
				searchResults.size());
		assertEquals(
				"search string 'another' does not return index of testTask, "
						+ "which contains 'another' in its task description",
				anotherTestDescription, searchResults.get(0).getDescription());

		searchString = "test";
		searchResults = textBuddy.search(searchString);
		assertEquals("size of searchResults arraylist is not 2", 2,
				searchResults.size());
		assertEquals("search string 'test' does not return index of testTask, "
				+ "which contains 'test' in its task description",
				testDescription, searchResults.get(0).getDescription());
		assertEquals(
				"search string 'test' does not return index of anotherTestTask, "
						+ "which contains 'test' in its task description",
				anotherTestDescription, searchResults.get(1).getDescription());

		expectedSearchResultsToStringOutput = "All search results:\n"
				+ "1. " + testDescription + "\n" + "2. "
				+ anotherTestDescription;
		assertTrue(expectedSearchResultsToStringOutput.equals(textBuddy
				.searchResultsToString()));

	}

	@Test
	public void testToString() {
		setup();
		addItems();
		String expectedToStringOutput = "All tasks:" + "\n" + "1. "
				+ testDescription + "\n" + "2. " + anotherTestDescription;
		assertTrue("tasks not displayed correctly", expectedToStringOutput.equals(textBuddy.toString()));
	}

}
