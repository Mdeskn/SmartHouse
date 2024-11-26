package test;

import org.junit.jupiter.api.Test;
import smarthouse.concurrency.TaskScheduler;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TaskSchedulerTest {

    @Test
    void testScheduleAtFixedRate() {
        TaskScheduler taskScheduler = new TaskScheduler();

        Runnable task = () -> System.out.println("Running task...");
        assertDoesNotThrow(() -> taskScheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS));

        taskScheduler.shutdown();
        assertDoesNotThrow(taskScheduler::shutdown);
    }
}

