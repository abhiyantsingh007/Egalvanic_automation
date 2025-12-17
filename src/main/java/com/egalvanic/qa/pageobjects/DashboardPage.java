package com.egalvanic.qa.pageobjects;

import com.egalvanic.qa.testcase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object Model for Dashboard Page
 */
public class DashboardPage extends BaseTest {
    
    // Locators
    private By navigationMenu = By.cssSelector("nav");
    private By dashboardHeader = By.xpath("//*[contains(text(),'Dashboard') or contains(text(),'Sites')]");
    private By logoutLink = By.xpath("//a[contains(@href,'logout') or contains(text(),'Logout')]");
    
    /**
     * Wait for dashboard to load
     * @return True if dashboard loaded, false otherwise
     */
    public boolean waitForDashboard() {
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(navigationMenu),
                ExpectedConditions.presenceOfElementLocated(dashboardHeader)
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if user is on dashboard page
     * @return True if on dashboard, false otherwise
     */
    public boolean isOnDashboard() {
        return driver.getCurrentUrl().contains("dashboard") || driver.getCurrentUrl().contains("sites");
    }
    
    /**
     * Click logout link
     */
    public void clickLogout() {
        click(logoutLink);
    }
}