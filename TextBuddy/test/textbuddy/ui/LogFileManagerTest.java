package textbuddy.ui;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class LogFileManagerTest {
	String inputFileName = "log.txt";
	LogFileManager textBuddyLogFileManager;

	private void setup() {
		textBuddyLogFileManager = new LogFileManager(inputFileName);
	}

	@Test
	public void testLogFileManager() {
		setup();
		File testLogFile = new File(inputFileName);
		assertTrue(textBuddyLogFileManager.getLogFile().equals(testLogFile));
	}

	@Test
	public void testGetTaskDescription() {
		String listIndex = "1. ";
		String testDescription = "test. description";
		String aLine = listIndex + testDescription;
		setup();

		assertTrue(
				"extracted task description is not 'test. description'",
				textBuddyLogFileManager.getTaskDescription(aLine).equals(
						testDescription));
	}
}
