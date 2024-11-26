package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    ApplianceControllerTest.class,
    EnergyControllerTest.class,
    HouseManagerTest.class,
    ExceptionHandlerTest.class,
    FileHandlerTest.class,
    LoggerTest.class,
    ResourceLockTest.class,
    TaskSchedulerTest.class
})
public class AllTests {
}
