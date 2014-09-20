package textbuddy.ui;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class LogFileManagerTest {
	String inputFileName = "log.txt";
	LogFileManager textBuddyLogFileManager;

	@Test
	public void testLogFileManager() {
		textBuddyLogFileManager = new LogFileManager(inputFileName);
		File testLogFile = new File(inputFileName);
		assertTrue(textBuddyLogFileManager.getLogFile().equals(testLogFile));
	}

}
