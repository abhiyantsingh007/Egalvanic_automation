package com.acme.config;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static io.restassured.RestAssured.given;

public class BaseConfig {
    // Base URL for the application
    public static final String BASE_URL = "https://acme.egalvanic.ai";
    public static final String API_BASE_URL = "https://acme.egalvanic.ai/api";
    
    // Test credentials
    public static final String TEST_EMAIL = "rahul+acme@egalvanic.com";
    public static final String TEST_PASSWORD = "RP@egalvanic123";
    
    // WebDriver instance
    protected static WebDriver driver;
    
    // Authentication token (to be set after login)
    protected static String authToken;
    
    /**
     * Initialize WebDriver
     */
    public static void initializeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            
            driver = new ChromeDriver(options);
        }
    }
    
    /**
     * Close WebDriver
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    /**
     * Get WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
    
    /**
     * Perform login and get authentication token
     */
    public static String loginAndGetToken() {
        if (authToken != null) {
            return authToken;
        }
        
        try {
            // Set base URI for REST Assured
            RestAssured.baseURI = API_BASE_URL;
            
            // Create login request payload
            String loginPayload = "{\n" +
                    "  \"email\": \"" + TEST_EMAIL + "\",\n" +
                    "  \"password\": \"" + TEST_PASSWORD + "\"\n" +
                    "}";
            
            // Send POST request to login endpoint
            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(loginPayload)
                    .when()
                    .post("/login")  // Adjust endpoint as needed
                    .then()
                    .extract().response();
            
            // Extract token from response (adjust based on actual API response structure)
            if (response.getStatusCode() == 200) {
                authToken = response.jsonPath().getString("token"); // Adjust based on actual response
                return authToken;
            }
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Get authentication token
     */
    public static String getAuthToken() {
        if (authToken == null) {
            authToken = loginAndGetToken();
        }
        return authToken;
    }
}