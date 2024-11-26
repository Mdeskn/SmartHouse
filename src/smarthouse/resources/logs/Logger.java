package smarthouse.resources.logs;

import smarthouse.utils.ExceptionHandler;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Logger {
    private static final String SIMULATION_LOG = "src/smarthouse/resources/logs/simulation.log";
    private static final String ERROR_LOG = "src/smarthouse/resources/logs/error.log";
    private static final Map<String, LocalDateTime> recentLogs = new HashMap<>();
    private static final long LOG_EXPIRY_DURATION = 10; // Log expiry in seconds

    // Logs simulation events
    public static void logSimulation(String message) {
        try {
            if (!isDuplicateLog(message)) { // Avoid duplicate messages within the expiry period
                log(SIMULATION_LOG, message);
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.logSimulation", e);
        }
    }

    // Logs errors and exceptions
    public static void logError(String message) {
        try {
            log(ERROR_LOG, message);
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.logError", e);
        }
    }

    // General logging method
    private static void log(String filePath, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[" + timestamp + "] " + message);
            writer.newLine();
        } catch (IOException e) {
            ExceptionHandler.handleException("Logger.log (" + filePath + ")", e);
        }
    }

    // Checks if the log message is a duplicate within the expiry period
    private static boolean isDuplicateLog(String message) {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime lastLoggedTime = recentLogs.get(message);

            if (lastLoggedTime != null && lastLoggedTime.plusSeconds(LOG_EXPIRY_DURATION).isAfter(now)) {
                return true; // Duplicate log within expiry period
            }

            // Update or add the message timestamp
            recentLogs.put(message, now);
            return false;
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.isDuplicateLog", e);
            return true; // Assume duplicate to avoid unintended logging
        }
    }

    public static void clearRecentLogs() {
        try {
            recentLogs.clear();
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.clearRecentLogs", e);
        }
    }

    // Clears outdated log entries from the recentLogs map
    public static void clearExpiredLogs() {
        try {
            LocalDateTime now = LocalDateTime.now();
            recentLogs.entrySet().removeIf(entry -> entry.getValue().plusSeconds(LOG_EXPIRY_DURATION).isBefore(now));
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.clearExpiredLogs", e);
        }
    }

    // Reads simulation logs
    public static String readSimulationLogs() {
        try {
            return readLogs(SIMULATION_LOG);
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.readSimulationLogs", e);
            return "Failed to read simulation logs.";
        }
    }

    // Reads error logs
    public static String readErrorLogs() {
        try {
            return readLogs(ERROR_LOG);
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.readErrorLogs", e);
            return "Failed to read error logs.";
        }
    }

    // Clears simulation logs
    public static void clearSimulationLogs() {
        try {
            clearLogs(SIMULATION_LOG);
            System.out.println("Simulation logs cleared.");
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.clearSimulationLogs", e);
        }
    }

    // Clears error logs
    public static void clearErrorLogs() {
        try {
            clearLogs(ERROR_LOG);
            System.out.println("Error logs cleared.");
        } catch (Exception e) {
            ExceptionHandler.handleException("Logger.clearErrorLogs", e);
        }
    }

    // Helper method to read logs from a file
    private static String readLogs(String filePath) {
        StringBuilder logContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logContent.append(line).append("\n");
            }
        } catch (IOException e) {
            ExceptionHandler.handleException("Logger.readLogs (" + filePath + ")", e);
            logContent.append("Failed to read log file: ").append(filePath).append("\n");
        }
        return logContent.toString();
    }

    // Helper method to clear logs from a file
    private static void clearLogs(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            // Overwrite file with empty content
        } catch (IOException e) {
            ExceptionHandler.handleException("Logger.clearLogs (" + filePath + ")", e);
        }
    }
}
