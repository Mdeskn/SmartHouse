package smarthouse.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionHandler {

    private static final String ERROR_LOG = "src/smarthouse/resources/logs/error.log";

    // General exception handler
    public static void handleException(String context, Exception e) {
        String errorMessage = formatErrorMessage(context, e.getMessage());
        System.err.println(errorMessage);
        logError(errorMessage);
    }

    // Custom exception handler with user-defined messages
    public static void handleCustomException(String context, String message) {
        String errorMessage = formatErrorMessage(context, message);
        System.err.println(errorMessage);
        logError(errorMessage);
    }

    // Specific handler for parsing errors
    public static void handleFileParsingError(String fileName, String invalidLine) {
        String errorMessage = String.format(
                "[%s] File Parsing Error in '%s': Invalid line -> '%s'",
                getCurrentTimestamp(), fileName, invalidLine
        );
        System.err.println(errorMessage);
        logError(errorMessage);
    }

    // Log error messages to a file
    private static void logError(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ERROR_LOG, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write to error log: " + e.getMessage());
        }
    }

    // Helper to format error messages
    private static String formatErrorMessage(String context, String message) {
        return String.format("[%s] Error in %s: %s", getCurrentTimestamp(), context, message);
    }

    // Helper to get the current timestamp
    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
