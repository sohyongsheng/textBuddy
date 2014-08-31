package textbuddy.ui;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserInterfaceTest {
	TaskManager textBuddy = new TaskManager();

	@Test
	public void testMapCommandToMethod() {
		String addAction = "add";
		String testDescription = "test description";
		String addCommandString = addAction + " " + testDescription;
		Command addCommand = new Command(addCommandString);
		UserInterface textBuddyUi = new UserInterface();
		
		textBuddyUi.mapCommandToMethod(addCommand);
		assertEquals("add command did not map to add method", 1, textBuddy.getTasks().size());
		assertTrue("add command did not map task description to newly added task", textBuddy.getTasks().get(0).description.equals(testDescription));		
	}

}
