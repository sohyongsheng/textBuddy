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
 * Implements all methods associated with log file management.
 * 
 * @author Soh Yong Sheng
 *
 */
public class LogFileManager {
    File logFile;
    BufferedWriter tbWriter;
    BufferedReader tbReader;

    /**
     * Constructor for <code>LogFileManager</code> that takes in the name of the
     * log file from user input. The log file does not necessarily have to end
     * with the '.txt' extension, but it must be readable and writable.
     * 
     * @param inputFileName
     *            name of log file
     */
    public LogFileManager(String inputFileName) {
        logFile = new File(inputFileName);
    }

    /**
     * Returns the <code>File</code> object representing the log file. Used
     * primarily for <code>File</code> and <code>Path</code> manipulation.
     * 
     * @return the <code>File</code> object representing the log file
     */
    public File getLogFile() {
        return logFile;
    }

    /**
     * Prepares the log file to be read/written from/to and returns the status
     * of preparation. If the log file exists, this method assumes that the log
     * file came from a previous session. Thus to use an existing log file, the
     * text in the log file must be properly formatted. If the log file does not
     * exist, this method creates it and logs all tasks into newly created log
     * file.
     * 
     * @param tbTaskManager
     *            the task manager of TextBuddy handling all task operations
     * @return status message describing status of preparation
     * @throws IOException
     *             if there are read/write problems from/to the log file
     */
    public String prepareLogFile(TaskManager tbTaskManager) throws IOException {

        String fileName = this.getLogFile().getName();
        boolean isLogFileExist = this.getLogFile().exists();

        String result;
        if (isLogFileExist) {
            result = "Loading tasks from " + fileName + ". ";
            this.loadTasks(tbTaskManager);
        } else {
            result = "Creating new log file. ";
            this.logFile.createNewFile();
        }
        result = result + fileName + " is now ready for use";

        return result;
    }

    /**
     * Writes all existing tasks represented as a <code>String</code> into the
     * log file
     * 
     * @param tasks
     *            all currently existing tasks in TextBuddy's task manager
     *            represented as a <code>String</code>
     * @throws IOException
     *             if there are write problems to the log file
     */
    public void writeToLogFile(String tasks) throws IOException {
        Path logFilePath = this.getLogFile().toPath();
        try {
            tbWriter = Files.newBufferedWriter(logFilePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING);
            tbWriter.write(tasks);
        } finally {
            tbWriter.close();
        }
    }

    /**
     * Reads tasks from the log file and loads it into the task manager.
     * 
     * @param tbTaskManager
     *            the current task manager for TextBuddy
     * @throws IOException
     *             if there problems reading from the log file
     */
    public void loadTasks(TaskManager tbTaskManager) throws IOException {
        Path logFilePath = this.getLogFile().toPath();
        String aLine;
        try {
            tbReader = Files.newBufferedReader(logFilePath);

            // always remove first header line
            @SuppressWarnings("unused")
            String headerLineToDiscard = tbReader.readLine();

            while ((aLine = tbReader.readLine()) != null) {
                String aTaskDescription = this.getTaskDescription(aLine);
                tbTaskManager.addTask(new Task(aTaskDescription));
            }
        } finally {
            tbReader.close();
        }
    }

    /**
     * Gets only the task description given a read line from the log file and
     * returns it as a string. For example, a read line from the log file may be
     * "1. this thing". Only the task description "this thing" is extracted and
     * loaded into the task manager
     * 
     * @param aLine
     *            a single read line from the log file
     * @return the task description of the read line
     */
    public String getTaskDescription(String aLine) {
        String[] splitLine = aLine.split(" ", 2);
        return splitLine[1];
    }

}
