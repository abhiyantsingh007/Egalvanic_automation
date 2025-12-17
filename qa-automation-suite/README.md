# ACME QA Automation Suite

This comprehensive QA automation suite tests the ACME application (https://acme.egalvanic.ai) using industry-standard practices and tools. The suite provides full coverage for UI, API, security, and performance testing with detailed reporting.

## 🏗️ Project Architecture

```
qa-automation-suite/
├── pom.xml                           # Maven configuration with all dependencies
├── testng.xml                        # TestNG suite configuration
├── src/
│   ├── main/java/com/acme/
│   │   ├── config/
│   │   │   └── BaseConfig.java              # Global configuration and WebDriver management
│   │   ├── pages/
│   │   │   ├── LoginPage.java               # Page Object Model for Login functionality
│   │   │   └── DashboardPage.java           # Page Object Model for Dashboard functionality
│   │   └── utils/
│   │       ├── ExtentReporterNG.java        # Extent Reports integration with TestNG
│   │       └── PerformanceUtils.java        # Performance measurement utilities
│   └── test/java/com/acme/
│       └── tests/
│           ├── ui/
│           │   ├── LoginPageTest.java       # UI functional tests for login scenarios
│           │   ├── ComprehensiveWebsiteTest.java
│           │   └── DropdownFunctionalityTest.java
│           ├── api/
│           │   ├── BaseAPITest.java         # Base class for API tests
│           │   ├── UserAPITest.java         # User management API tests
│           │   └── AuthenticationTest.java  # Authentication API tests
│           ├── security/
│           │   ├── UISecurityTest.java      # UI security vulnerability tests
│           │   └── APISecurityTest.java    # API security vulnerability tests
│           └── performance/
│               ├── UIPerformanceTest.java   # UI performance benchmarking
│               └── APIPerformanceTest.java  # API response time testing
└── test-output/
    ├── reports/                   # Generated HTML reports (Extent & Allure)
    └── screenshots/               # Screenshots captured during test execution
```

## 🛠️ Technologies & Frameworks

- **Core**: Java 11+, Maven 3.6+
- **UI Testing**: Selenium WebDriver 4.15.0, TestNG 7.8.0
- **API Testing**: REST Assured 5.4.0
- **Mobile Testing**: Appium Java Client 8.6.0
- **Reporting**: Extent Reports 5.1.1, Allure 2.24.0
- **Utilities**: WebDriverManager 5.6.3, Jackson 2.16.0

## ▶️ Quick Start

```bash
# 1. Install dependencies
mvn clean install

# 2. Run all tests
mvn test

# 3. Run specific test suites
mvn test -DsuiteXmlFile=testng-ui.xml      # UI tests only
mvn test -DsuiteXmlFile=testng-api.xml     # API tests only
mvn test -DsuiteXmlFile=testng-security.xml # Security tests only
```

Reports are automatically generated in `test-output/reports/` after execution.

## 🧪 Test Coverage Matrix

| Test Type | Coverage Areas | Tools Used |
|-----------|----------------|------------|
| **UI Functional** | Login validation, Form handling, Navigation, Dropdown interactions | Selenium WebDriver, TestNG |
| **API Testing** | Authentication endpoints, User management, CRUD operations | REST Assured |
| **Security** | SQL injection, XSS protection, JWT validation, Parameter tampering | Custom security tests |
| **Performance** | Page load times, API response metrics, Resource optimization | Custom performance utilities |

## 📊 Reporting Features

- **Extent Reports**: Detailed HTML reports with step-by-step execution logs
- **Allure Reports**: Interactive dashboards with real-time test analytics
- **Screenshot Evidence**: Automatic capture for failed tests and key milestones
- **Performance Metrics**: Response time tracking and bottleneck identification

## 🏆 Best Practices Implemented

1. ✅ Page Object Model for maintainable test code
2. ✅ TestNG annotations for proper test organization
3. ✅ Parallel test execution capability
4. ✅ Comprehensive error handling and logging
5. ✅ Cross-browser compatibility support
6. ✅ CI/CD pipeline readiness

## 👥 Team Guidelines

- All new test cases should follow the existing POM structure
- Test methods should have clear priorities and descriptions
- Utility functions should be reusable across test classes
- All changes require peer review before merging

---

*For questions or support, contact the QA team.*