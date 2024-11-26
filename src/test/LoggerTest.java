package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smarthouse.resources.logs.Logger;

class LoggerTest {
    @Test
    void testLogSimulation() {
        Logger.logSimulation("Test Simulation Log");
        String logs = Logger.readSimulationLogs();
        Assertions.assertTrue(logs.contains("Test Simulation Log"));
    }

    @Test
    void testClearLogs() {
        Logger.clearSimulationLogs();
        String logs = Logger.readSimulationLogs();
        Assertions.assertTrue(logs.isEmpty());
    }
}

