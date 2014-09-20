package textbuddy.ui;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class UserInterfaceTest {
    String logFileName = "log.txt";
    File testLogFile = new File(logFileName);
    UserInterface tbUserInterface = new UserInterface(logFileName);

    @Test
    public void testUserInterface() {
        assertTrue("textBuddy is not initialised as instance of TaskManager",
                tbUserInterface.tbTaskManager instanceof TaskManager);
        assertTrue(
                "textBuddyLogFileManager is not initialised as instance of LogFileManager",
                tbUserInterface.tbLogFileManager instanceof LogFileManager);
        assertTrue(
                "file in textBuddyLogFileManager is not initialised correctly",
                tbUserInterface.tbLogFileManager.getLogFile().equals(
                        testLogFile));
    }

    @Test(expected = NoSuchMethodException.class)
    public void testExecuteCommand() throws NoSuchMethodException {
        String fileName = "testLogFile.txt";
        String expectedDisplayedOutput;

        // prepare add "test description" command
        String addAction = "add";
        String testDescription = "test description";
        String addCommandString = addAction + " " + testDescription;
        Command addCommand = new Command(addCommandString);
        String statusMessage;

        // test for add
        statusMessage = tbUserInterface.executeCommand(addCommand, fileName);
        assertTrue(
                "Status message for first add task is incorrect",
                statusMessage.equals("Added to " + fileName + ": "
                        + testDescription));
        assertEquals("add command did not map to add method", 1,
                tbUserInterface.tbTaskManager.getTasks().size());
        assertTrue(
                "add command did not map task description to newly added task",
                tbUserInterface.tbTaskManager.getTasks().get(0).description
                        .equals(testDescription));

        // prepare for second add "another test description" command
        String anotherTestDescription = "another test description";
        String anotherAddCommandString = addAction + " "
                + anotherTestDescription;
        Command anotherAddCommand = new Command(anotherAddCommandString);

        // test for second add
        statusMessage = tbUserInterface.executeCommand(anotherAddCommand,
                fileName);
        assertTrue(
                "Status message for second add task is incorrect",
                statusMessage.equals("Added to " + fileName + ": "
                        + anotherTestDescription));
        assertEquals("second add command did not map to add method", 2,
                tbUserInterface.tbTaskManager.getTasks().size());
        assertTrue(
                "second add command did not map task description to newly added task",
                tbUserInterface.tbTaskManager.getTasks().get(1).description
                        .equals(anotherTestDescription));

        // test for display
        String displayAction = "display";
        Command displayCommand = new Command(displayAction);

        statusMessage = tbUserInterface
                .executeCommand(displayCommand, fileName);
        expectedDisplayedOutput = "All tasks:" + "\n" + "1. " + testDescription
                + "\n" + "2. " + anotherTestDescription;
        assertTrue("tasks not displayed correctly",
                expectedDisplayedOutput.equals(statusMessage));

        // test for invalid command
        String invalidCommandAction = "invalidCommand";
        String invalidCommandDescription = "invalid description";
        String invalidCommandCommandString = invalidCommandAction + " "
                + invalidCommandDescription;
        Command invalidCommand = new Command(invalidCommandCommandString);

        statusMessage = tbUserInterface
                .executeCommand(invalidCommand, fileName);
        assertEquals("invalid command changed number of tasks", 2,
                tbUserInterface.tbTaskManager.getTasks().size());
        assertTrue("invalid command changed first task",
                tbUserInterface.tbTaskManager.getTasks().get(0).description
                        .equals(testDescription));
        assertTrue("invalid command changed second task",
                tbUserInterface.tbTaskManager.getTasks().get(1).description
                        .equals(anotherTestDescription));

        // prepare delete command
        String deleteAction = "delete";
        String deletionIndex = "1";
        String deleteCommandString = deleteAction + " " + deletionIndex;
        Command deleteCommand = new Command(deleteCommandString);

        // test for delete
        statusMessage = tbUserInterface.executeCommand(deleteCommand, fileName);
        assertTrue(
                "Status message for delete command is incorrect",
                statusMessage.equals("Deleted from " + fileName + ": "
                        + testDescription));
        assertEquals("first task was not deleted", 1,
                tbUserInterface.tbTaskManager.getTasks().size());
        assertTrue("index of originally second task was not shifted up to one",
                tbUserInterface.tbTaskManager.getTasks().get(0).description
                        .equals(anotherTestDescription));

        // add back deleted task
        tbUserInterface.executeCommand(addCommand, fileName);
        String clearAction = "clear";
        Command clearCommand = new Command(clearAction);

        // test for clear
        statusMessage = tbUserInterface.executeCommand(clearCommand, fileName);
        assertTrue("Status message for clear command is incorrect",
                statusMessage.equals("All content cleared from " + fileName));
        assertEquals("tasks were not cleared", 0, tbUserInterface.tbTaskManager
                .getTasks().size());

        // add "test description", followed by add "another test description"
        tbUserInterface.executeCommand(addCommand, fileName);
        tbUserInterface.executeCommand(anotherAddCommand, fileName);

        // prepare sort command
        String sortAction = "sort";
        Command sortCommand = new Command(sortAction);

        // test for sort
        statusMessage = tbUserInterface.executeCommand(sortCommand, fileName);
        expectedDisplayedOutput = "2 tasks sorted:\n" + "All tasks:\n" + "1. "
                + anotherTestDescription + "\n" + "2. " + testDescription;
        assertTrue(statusMessage.equals(expectedDisplayedOutput));

        // prepare search "another" command
        String searchCommandString = "search another";
        Command searchCommand = new Command(searchCommandString);
        statusMessage = tbUserInterface.executeCommand(searchCommand, fileName);

        // test for search "another"
        expectedDisplayedOutput = "1 search match(es) found:\n"
                + "All search results:\n" + "1. " + anotherTestDescription;
        assertTrue("search 'another' results are not displayed correctly",
                statusMessage.equals(expectedDisplayedOutput));

        // prepare search "description" command
        searchCommandString = "search st description";
        searchCommand = new Command(searchCommandString);
        statusMessage = tbUserInterface.executeCommand(searchCommand, fileName);

        // test for search "st description"
        expectedDisplayedOutput = "2 search match(es) found:\n"
                + "All search results:\n" + "1. " + anotherTestDescription
                + "\n" + "2. " + testDescription;
        assertTrue(
                "search 'st description' results are not displayed correctly",
                statusMessage.equals(expectedDisplayedOutput));

        // prepare exit command
        String exitAction = "exit";
        Command exitCommand = new Command(exitAction);

        // test for exit
        statusMessage = tbUserInterface.executeCommand(exitCommand, fileName);
        assertTrue(statusMessage.equals("Exiting TextBuddy."));
    }

    @Test
    public void testIsExit() {
        Command notExitCommand = new Command(
                "invalidCommandAction invalidCommandParameter");
        assertFalse("program exited without correct exit command",
                tbUserInterface.isExit(notExitCommand));

        Command exitCommand = new Command("exit");
        assertTrue("program did not exit even with correct exit command",
                tbUserInterface.isExit(exitCommand));
    }

}
