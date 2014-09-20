package textbuddy.ui;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class LogFileManagerTest {
	String inputFileName = "log.txt";
	LogFileManager tbLogFileManager;

	private void setup() {
		tbLogFileManager = new LogFileManager(inputFileName);
	}

	@Test
	public void testLogFileManager() {
		setup();
		File testLogFile = new File(inputFileName);
		assertTrue(tbLogFileManager.getLogFile().equals(testLogFile));
	}

	@Test
	public void testGetTaskDescription() {
		String listIndex = "1. ";
		String testDescription = "test. description";
		String aLine = listIndex + testDescription;
		setup();

		assertTrue(
				"extracted task description is not 'test. description'",
				tbLogFileManager.getTaskDescription(aLine).equals(
						testDescription));
	}
}
