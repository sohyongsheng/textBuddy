/**
 * 
 */
package textbuddy.ui;

import java.io.File;

/**
 * @author Soh Yong Sheng
 *
 */
public class LogFileManager {
	File logFile;

	public LogFileManager(String inputFileName) {
		logFile = new File(inputFileName);
	}

	public File getLogFile() {
		return logFile;
	}

}
