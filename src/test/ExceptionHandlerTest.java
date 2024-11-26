package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import smarthouse.utils.ExceptionHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ExceptionHandlerTest {

    @Test
    void testHandleException() {
        // Redirect system error stream to capture output
        ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorOutput));

        ExceptionHandler.handleException("TestContext", new Exception("Test Exception"));

        String output = errorOutput.toString();
        assertTrue(output.contains("Error in TestContext: Test Exception"),
                "Expected error message not found in output.");
    }

    @Test
    void testHandleCustomException() {
        // Redirect system error stream to capture output
        ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorOutput));

        ExceptionHandler.handleCustomException("CustomContext", "Custom Error Message");

        String output = errorOutput.toString();
        assertTrue(output.contains("Error in CustomContext: Custom Error Message"),
                "Expected custom error message not found in output.");
    }

    @Test
    void testHandleFileParsingError() {
        // Redirect system error stream to capture output
        ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorOutput));

        ExceptionHandler.handleFileParsingError("TestFile.txt", "InvalidLineContent");

        String output = errorOutput.toString();
        assertTrue(output.contains("File Parsing Error in 'TestFile.txt': Invalid line -> 'InvalidLineContent'"),
                "Expected file parsing error message not found in output.");
    }
}
