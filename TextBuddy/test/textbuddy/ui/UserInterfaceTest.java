package textbuddy.ui;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserInterfaceTest {
	UserInterface textBuddyUi = new UserInterface();
	
	@Test
	public void testUserInterface() {
		assertTrue("textBuddy is not initialised as instance of TaskManager", textBuddyUi.textBuddy instanceof TaskManager);
	}

	@Test
	public void testMapCommandToMethod() {
		// not testing for display and exit as they require system interaction
		
		// test for add
		String addAction = "add";
		String testDescription = "test description";
		String addCommandString = addAction + " " + testDescription;
		Command addCommand = new Command(addCommandString);
		
		textBuddyUi.mapCommandToMethod(addCommand);
		assertEquals("add command did not map to add method", 1, textBuddyUi.textBuddy.getTasks().size());
		assertTrue("add command did not map task description to newly added task", textBuddyUi.textBuddy.getTasks().get(0).description.equals(testDescription));		
	
		
		// add a second task for testing of deletion later
		String anotherTestDescription = "another test description";
		String anotherAddCommandString = addAction + " " + anotherTestDescription;
		Command anotherAddCommand = new Command(anotherAddCommandString);
		
		textBuddyUi.mapCommandToMethod(anotherAddCommand);
		assertEquals("second add command did not map to add method", 2, textBuddyUi.textBuddy.getTasks().size());
		assertTrue("second add command did not map task description to newly added task", textBuddyUi.textBuddy.getTasks().get(1).description.equals(anotherTestDescription));	
		
		
		// test for delete
		String deleteAction = "delete";
		String deletionIndex = "1";
		String deleteCommandString = deleteAction + " " + deletionIndex;
		Command deleteCommand = new Command(deleteCommandString);
		
		textBuddyUi.mapCommandToMethod(deleteCommand);
		assertEquals("first task was not deleted", 1, textBuddyUi.textBuddy.getTasks().size());
		assertTrue("index of originally second task was not shifted up to one", textBuddyUi.textBuddy.getTasks().get(0).description.equals(anotherTestDescription));	
		
		
		// add back deleted task and test for clear
		textBuddyUi.mapCommandToMethod(addCommand);		
		String clearAction = "clear";		
		Command clearCommand = new Command(clearAction);
		
		textBuddyUi.mapCommandToMethod(clearCommand);
		assertEquals("tasks were not cleared", 0, textBuddyUi.textBuddy.getTasks().size());
	}
}
