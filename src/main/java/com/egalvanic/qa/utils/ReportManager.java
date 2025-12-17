package com.egalvanic.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility class for managing Extent Reports
 */
public class ReportManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    
    /**
     * Initialize Extent Reports
     * @param reportFileName Name of the report file
     */
    public static void initReports(String reportFileName) {
        try {
            Files.createDirectories(Path.of("test-output/reports"));
            Files.createDirectories(Path.of("test-output/screenshots"));
        } catch (Exception e) {
            System.out.println("Failed to create directories: " + e.getMessage());
        }

        sparkReporter = new ExtentSparkReporter("test-output/reports/" + reportFileName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("QA Automation Test Report");
        sparkReporter.config().setReportName("QA Automation Report");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Organization", "ACME");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("Tester", "QA Automation Engineer");
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
    }
    
    /**
     * Create a test in the report
     * @param testName Name of the test
     * @return ExtentTest instance
     */
    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }
    
    /**
     * Create a node under a test
     * @param parentTest Parent test
     * @param nodeName Name of the node
     * @return ExtentTest instance for the node
     */
    public static ExtentTest createNode(ExtentTest parentTest, String nodeName) {
        return parentTest.createNode(nodeName);
    }
    
    /**
     * Log a message with status to a test
     * @param test Test to log to
     * @param status Status of the log
     * @param message Message to log
     */
    public static void log(ExtentTest test, Status status, String message) {
        test.log(status, message);
    }
    
    /**
     * Flush the reports to disk
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
    
    /**
     * Get the ExtentReports instance
     * @return ExtentReports instance
     */
    public static ExtentReports getExtent() {
        return extent;
    }
}