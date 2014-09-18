package textbuddy.ui;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandTest {
    @Test
    public void testsplitCommandLine() {
        String addAction = "add";
        String testDescription = "test description";
        String addCommandString = addAction + " " + testDescription;

        String[] testInputCommand = Command.splitCommandLine(addCommandString);
        assertEquals("size of add command is not 2", 2, testInputCommand.length);
        assertTrue("first element of add command array is not 'add'",
                testInputCommand[0].equals(addAction));
        assertTrue(
                "second element of add command array is not 'test description'",
                testInputCommand[1].equals(testDescription));
    }

    @Test
    public void testCommand() {
        // to test for commands with action without parameter
        String displayAction = "display";
        Command displayCommand = new Command(displayAction);
        assertTrue("displayCommand's action did not parse correctly",
                displayCommand.commandAction.equals(displayAction));

        // to test for commands with both action and parameter
        String addAction = "add";
        String testDescription = "test description";
        String addCommandString = addAction + " " + testDescription;
        Command addCommand = new Command(addCommandString);
        assertTrue("addCommand's action did not parse correctly",
                addCommand.commandAction.equals(addAction));
        assertTrue("addCommand's parameter did not parse correctly",
                addCommand.commandParameter.equals(testDescription));
    }

}
