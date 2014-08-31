package textbuddy.ui;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TaskManagerTest {
	private TaskManager textBuddy;
	private String testDescription;
	private String anotherTestDescription;
	private Task testTask;
	private Task anotherTestTask;

	public void setup()
	{
		textBuddy = new TaskManager();
		testDescription = new String("test description");
		anotherTestDescription = new String("another test description");
		testTask = new Task(testDescription);
		anotherTestTask = new Task(anotherTestDescription);
	}
	
	public void addItems() {
		textBuddy.addTask(testTask);
		textBuddy.addTask(anotherTestTask);
	}

	@Test
	public void testTaskManager() {
		TaskManager textBuddy = new TaskManager();
		assertTrue("Task is not instance of ArrayList", textBuddy.tasks instanceof ArrayList);
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
	public void testToString() {
		setup();
		addItems();
		String expectedToStringOutput = "All tasks:" + "\n" + "1. " + testDescription + 
										"\n" + "2. " + anotherTestDescription;
		assertTrue(expectedToStringOutput.equals(textBuddy.toString()));
	}

	








}
