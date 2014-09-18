package textbuddy.ui;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserInterfaceTest {
	UserInterface textBuddyUi = new UserInterface();

	@Test
	public void testUserInterface() {
		assertTrue("textBuddy is not initialised as instance of TaskManager",
				textBuddyUi.textBuddy instanceof TaskManager);
	}

	@Test
	public void testMapCommandToMethod() {
		// not testing for display and exit as they require system interaction
		String fileName = "testLogFile.txt";

		// test for add
		String addAction = "add";
		String testDescription = "test description";
		String addCommandString = addAction + " " + testDescription;
		Command addCommand = new Command(addCommandString);
		String statusMessage;

		statusMessage = textBuddyUi.executeCommand(addCommand, fileName);
		assertTrue(
				"Status message for first add task is incorrect",
				statusMessage.equals("Added to " + fileName + ": "
						+ testDescription));
		assertEquals("add command did not map to add method", 1,
				textBuddyUi.textBuddy.getTasks().size());
		assertTrue(
				"add command did not map task description to newly added task",
				textBuddyUi.textBuddy.getTasks().get(0).description
						.equals(testDescription));

		// add a second task for testing of deletion later
		String anotherTestDescription = "another test description";
		String anotherAddCommandString = addAction + " "
				+ anotherTestDescription;
		Command anotherAddCommand = new Command(anotherAddCommandString);

		statusMessage = textBuddyUi.executeCommand(anotherAddCommand,
				fileName);
		assertTrue(
				"Status message for second add task is incorrect",
				statusMessage.equals("Added to " + fileName + ": "
						+ anotherTestDescription));
		assertEquals("second add command did not map to add method", 2,
				textBuddyUi.textBuddy.getTasks().size());
		assertTrue(
				"second add command did not map task description to newly added task",
				textBuddyUi.textBuddy.getTasks().get(1).description
						.equals(anotherTestDescription));

		// test for invalid command
		String invalidCommandAction = "invalidCommand";
		String invalidCommandDescription = "invalid description";
		String invalidCommandCommandString = invalidCommandAction + " "
				+ invalidCommandDescription;
		Command invalidCommand = new Command(invalidCommandCommandString);

		statusMessage = textBuddyUi
				.executeCommand(invalidCommand, fileName);
		assertNull(
				"Status message for invalid command is incorrect, i.e. not null",
				statusMessage);
		assertEquals("invalid command changed number of tasks", 2,
				textBuddyUi.textBuddy.getTasks().size());
		assertTrue("invalid command changed first task", textBuddyUi.textBuddy
				.getTasks().get(0).description.equals(testDescription));
		assertTrue("invalid command changed second task", textBuddyUi.textBuddy
				.getTasks().get(1).description.equals(anotherTestDescription));

		// test for delete
		String deleteAction = "delete";
		String deletionIndex = "1";
		String deleteCommandString = deleteAction + " " + deletionIndex;
		Command deleteCommand = new Command(deleteCommandString);

		statusMessage = textBuddyUi.executeCommand(deleteCommand, fileName);
		assertTrue(
				"Status message for delete command is incorrect",
				statusMessage.equals("Deleted from " + fileName + ": "
						+ testDescription));
		assertEquals("first task was not deleted", 1, textBuddyUi.textBuddy
				.getTasks().size());
		assertTrue("index of originally second task was not shifted up to one",
				textBuddyUi.textBuddy.getTasks().get(0).description
						.equals(anotherTestDescription));

		// add back deleted task and test for clear
		textBuddyUi.executeCommand(addCommand, fileName);
		String clearAction = "clear";
		Command clearCommand = new Command(clearAction);

		statusMessage = textBuddyUi.executeCommand(clearCommand, fileName);
		assertTrue("Status message for clear command is incorrect",
				statusMessage.equals("All content cleared from " + fileName));
		assertEquals("tasks were not cleared", 0, textBuddyUi.textBuddy
				.getTasks().size());
		
		// test for exit command
		String exitAction = "exit";
        Command exitCommand = new Command(exitAction);

        statusMessage = textBuddyUi.executeCommand(exitCommand, fileName);
        assertTrue(statusMessage.equals("Exiting TextBuddy."));
	}
	
	@Test
	public void testDecideWhetherToExit() {
	    Command notExitCommand = new Command("invalidCommandAction invalidCommandParameter");
	    assertFalse("program exited without correct exit command", textBuddyUi.decideWhetherToExit(notExitCommand));
	    
	    Command exitCommand = new Command("exit");
	    assertTrue("program did not exit even with correct exit command", textBuddyUi.decideWhetherToExit(exitCommand));	    
	}

}
