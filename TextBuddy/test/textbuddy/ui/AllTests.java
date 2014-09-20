package textbuddy.ui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CommandTest.class, TaskManagerTest.class, TaskTest.class,
        UserInterfaceTest.class, LogFileManagerTest.class })
public class AllTests {

}
