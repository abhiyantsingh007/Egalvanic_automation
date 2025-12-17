package com.egalvanic.qa;

import com.egalvanic.qa.testcase.AuthenticationTest;

/**
 * Main class to run the QA automation suite
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting QA Automation Suite...");
        
        // Run authentication tests
        AuthenticationTest authTest = new AuthenticationTest();
        authTest.runAllTests();
        
        System.out.println("QA Automation Suite completed.");
    }
}