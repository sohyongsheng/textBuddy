/**
 * 
 */
package textbuddy.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author Soh Yong Sheng
 *
 */
public class LogFileManager {
	File logFile;
	BufferedWriter textBuddyWriter;
	BufferedReader textBuddyReader;

	public LogFileManager(String inputFileName) {
		logFile = new File(inputFileName);
	}

	public File getLogFile() {
		return logFile;
	}

	public String prepareLogFile(TaskManager textBuddy) throws IOException {

		String fileName = this.getLogFile().getName();
		boolean isLogFileExist = this.getLogFile().exists();
		// boolean isReadable = this.getLogFile().canRead();
		// boolean isWritable = this.getLogFile().canWrite();

		String result;
		if (isLogFileExist) {
			result = "Loading tasks from " + fileName + ". ";
			this.loadTasks(textBuddy);
		} else {
			result = "Creating new log file. ";
			this.logFile.createNewFile();
		}
		result = result + fileName + " is now ready for use";

		return result;
	}

	public void writeToLogFile(String tasks) throws IOException {
		Path logFilePath = this.getLogFile().toPath();
		try {
			textBuddyWriter = Files.newBufferedWriter(logFilePath,
					StandardCharsets.UTF_8,
					StandardOpenOption.TRUNCATE_EXISTING);
			textBuddyWriter.write(tasks);
		} finally {
			textBuddyWriter.close();
		}
	}

	public void loadTasks(TaskManager textBuddy) throws IOException {
		Path logFilePath = this.getLogFile().toPath();
		String aLine;
		try {
			textBuddyReader = Files.newBufferedReader(logFilePath);

			@SuppressWarnings("unused")
			String headerLineToDiscard = textBuddyReader.readLine();
			while ((aLine = textBuddyReader.readLine()) != null) {
				String aTaskDescription = this.getTaskDescription(aLine);
				textBuddy.addTask(new Task(aTaskDescription));
			}
		} finally {
			textBuddyReader.close();
		}
	}

	public String getTaskDescription(String aLine) {
		String[] splitLine = aLine.split(" ", 2);
		return splitLine[1];
	}

}
