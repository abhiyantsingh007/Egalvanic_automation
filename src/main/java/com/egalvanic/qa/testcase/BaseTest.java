package com.egalvanic.qa.testcase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base test class containing common functionality for all test cases
 */
public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static JavascriptExecutor js;
    protected static Actions actions;
    protected static ExtentReports extent;
    protected static ExtentSparkReporter sparkReporter;
    
    protected static final int DEFAULT_TIMEOUT = 25;
    protected static final String BASE_URL = "https://acme.qa.egalvanic.ai";
    protected static final String EMAIL = "rahul+acme@egalvanic.com";
    protected static final String PASSWORD = "RP@egalvanic123";

    /**
     * Setup the WebDriver instance based on the browser type
     * @param browserType The browser to use (chrome, firefox, edge, safari)
     */
    protected static void setupDriver(String browserType) {
        switch(browserType.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().clearDriverCache();
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOpts = new ChromeOptions();
                chromeOpts.addArguments("--start-maximized");
                chromeOpts.addArguments("--remote-allow-origins=*");
                chromeOpts.addArguments("--disable-blink-features=AutomationControlled");
                chromeOpts.addArguments("--no-sandbox");
                chromeOpts.addArguments("--disable-dev-shm-usage");
                chromeOpts.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOpts.setExperimentalOption("useAutomationExtension", false);
                driver = new ChromeDriver(chromeOpts);
                break;
        }
        
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        js.executeScript("document.body.style.zoom='80%';");
    }

    /**
     * Quit the WebDriver instance
     */
    protected static void tearDownDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Setup Extent Reports for test reporting
     */
    protected static void setupExtentReports() {
        try {
            Files.createDirectories(Path.of("test-output/reports"));
            Files.createDirectories(Path.of("test-output/screenshots"));
        } catch (Exception e) {
            System.out.println("Failed to create directories: " + e.getMessage());
        }

        String reportFileName = "test-output/reports/TestReport.html";
        sparkReporter = new ExtentSparkReporter(reportFileName);
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
     * Pause execution for specified milliseconds
     * @param ms Milliseconds to pause
     */
    protected static void pause(long ms) { 
        try { 
            Thread.sleep(ms); 
        } catch (InterruptedException ignored) {} 
    }

    /**
     * Generate timestamp for file naming
     * @return Formatted timestamp string
     */
    protected static String stamp() { 
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")); 
    }

    /**
     * Take screenshot and return as base64 string
     * @return Base64 encoded screenshot
     */
    protected static String getBase64Screenshot() {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Wait for element to be visible
     * @param by Locator for the element
     * @param timeoutSec Timeout in seconds
     * @return Visible WebElement
     */
    protected static WebElement visible(By by, int timeoutSec) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Wait for element to be clickable
     * @param by Locator for the element
     * @param timeoutSec Timeout in seconds
     * @return Clickable WebElement
     */
    protected static WebElement clickable(By by, int timeoutSec) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Click element using JavaScript
     * @param by Locator for the element
     */
    protected static void jsClick(By by) {
        try {
            WebElement e = driver.findElement(by);
            js.executeScript("arguments[0].click();", e);
        } catch (Exception ex) {
            throw new RuntimeException("JS click failed for: " + by, ex);
        }
    }

    /**
     * Click element with fallback to JavaScript click
     * @param by Locator for the element
     */
    protected static void click(By by) {
        try {
            clickable(by, DEFAULT_TIMEOUT).click();
        } catch (Exception e) {
            try {
                jsClick(by);
            } catch (Exception ex) {
                throw new RuntimeException("Click failed for: " + by + " -> " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Type text into element
     * @param by Locator for the element
     * @param text Text to type
     */
    protected static void type(By by, String text) {
        WebElement e = visible(by, DEFAULT_TIMEOUT);
        try { e.clear(); } catch (Exception ignored) {}
        e.click();
        e.sendKeys(text);
    }
}