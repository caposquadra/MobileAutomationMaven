package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Ex3.class,
        Ex4.class,
        Ex5.class,
        Ex6.class,
})

public class TestSuite {
}