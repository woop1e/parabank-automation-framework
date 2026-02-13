# ParaBank Selenium UI Automation Framework

## Overview
This is a comprehensive Selenium WebDriver automation framework built for testing the ParaBank web application. The framework follows industry best practices including Page Object Model (POM), modular design, and extensive reporting capabilities.

## Technology Stack
- **Language**: Java 11
- **Build Tool**: Maven 3.9+
- **Testing Framework**: TestNG
- **WebDriver**: Selenium WebDriver 4.16.1
- **Driver Management**: WebDriverManager 5.6.3
- **Logging**: Log4j 2.22.0
- **Reporting**: TestNG HTML Reports
- **Browser**: Chrome (default), Firefox, Edge

## Framework Architecture

### Project Structure
```
parabank-automation-framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       │   ├── BasePage.java
│   │       │   └── BaseTest.java
│   │       ├── pages/
│   │       │   ├── HomePage.java
│   │       │   ├── AccountOverviewPage.java
│   │       │   ├── RegisterPage.java
│   │       │   ├── TransferFundsPage.java
│   │       │   └── BillPayPage.java
│   │       └── utils/
│   │           ├── ConfigReader.java
│   │           ├── DriverFactory.java
│   │           ├── LoggerUtil.java
│   │           ├── ScreenshotUtils.java
│   │           └── WaitUtils.java
│   └── test/
│       ├── java/
│       │   ├── smoke/
│       │   │   └── SmokeTests.java
│       │   ├── regression/
│       │   │   └── RegressionTests.java
│       │   └── negative/
│       │       └── NegativeTests.java
│       └── resources/
│           ├── config/
│           │   └── config.properties
│           ├── testdata/
│           │   └── testdata.properties
│           ├── testng.xml
│           └── log4j2.xml
├── screenshots/
├── logs/
├── pom.xml
└── README.md
```

## Key Features

### 1. Page Object Model (POM)
- Separate page classes for each web page
- Encapsulation of web elements and page actions
- Reusable methods across test classes

### 2. Utility Classes
- **DriverFactory**: WebDriver initialization and management
- **ConfigReader**: Read configuration from properties files
- **LoggerUtil**: Centralized logging mechanism
- **ScreenshotUtils**: Automatic screenshot capture on failures
- **WaitUtils**: Custom explicit wait utilities

### 3. Base Classes
- **BasePage**: Common page operations and wait methods
- **BaseTest**: Test setup, teardown, and screenshot capture

### 4. Test Organization
- **Smoke Tests**: Critical functionality verification (5 tests)
- **Regression Tests**: Comprehensive feature testing (5 tests)
- **Negative Tests**: Validation and error handling (3 tests)

### 5. Reporting & Logging
- TestNG HTML reports with detailed test results
- Log4j logging to console and file
- Automatic screenshot capture on test failures
- Execution timestamps and stack traces

## Test Coverage

### Smoke Tests (5)
1. Homepage loads successfully
2. Valid user login
3. Logout functionality
4. Account Overview page loads
5. Transfer funds valid transaction

### Regression Tests (5)
6. Registration new user success
7. Bill payment successful
8. Transfer funds insufficient balance validation
9. Registration duplicate username validation
10. Navigation menu links verification

### Negative Tests (3)
11. Login with invalid credentials
12. Registration with empty required fields
13. Bill payment with invalid amount format

## Prerequisites

### Required Software
1. **Java Development Kit (JDK) 11 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **Apache Maven 3.9 or higher**
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn -version`

3. **Google Chrome Browser**
   - Download from: https://www.google.com/chrome/
   - WebDriverManager will handle ChromeDriver automatically

4. **IDE (Optional but recommended)**
   - IntelliJ IDEA / Eclipse / VS Code

## Installation & Setup

### 1. Clone/Download the Project
```bash
cd parabank-automation-framework
```

### 2. Install Dependencies
```bash
mvn clean install -DskipTests
```

### 3. Verify Project Structure
Ensure all folders are created:
- `screenshots/`
- `logs/`

## Configuration

### config.properties
Located at: `src/test/resources/config/config.properties`
```properties
base.url=https://parabank.parasoft.com/parabank/index.htm
browser=chrome
implicit.wait=10
explicit.wait=15
```

### testdata.properties
Located at: `src/test/resources/testdata/testdata.properties`
Contains test data for various scenarios.

## Execution Instructions

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
# Run only Smoke Tests
mvn clean test -Dtest=SmokeTests

# Run only Regression Tests
mvn clean test -Dtest=RegressionTests

# Run only Negative Tests
mvn clean test -Dtest=NegativeTests
```

### Run with Custom TestNG XML
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run Specific Test Method
```bash
mvn clean test -Dtest=SmokeTests#testValidLogin
```

### Parallel Execution
The framework supports parallel execution configured in `testng.xml`:
```xml
<suite name="ParaBank Test Suite" parallel="tests" thread-count="3">
```

## Reports & Logs

### TestNG HTML Report
After execution, find the report at:
```
target/surefire-reports/index.html
```
Or
```
test-output/index.html
```

### Log Files
Located at: `logs/automation.log`
- Contains detailed execution logs
- Timestamped entries for each action
- Error traces for failed tests

### Screenshots
Located at: `screenshots/`
- Automatically captured on test failures
- Named with test name and timestamp
- Format: `TestName_YYYYMMDD_HHMMSS.png`

## Framework Design Patterns

### 1. Page Object Model
Each web page is represented as a Java class with:
- WebElement declarations using @FindBy
- Page-specific action methods
- Inheritance from BasePage

### 2. Factory Pattern
DriverFactory manages WebDriver instance creation:
- Singleton pattern for driver instance
- Support for multiple browsers
- Automatic driver management via WebDriverManager

### 3. Singleton Pattern
- ConfigReader: Single instance for properties
- LoggerUtil: Centralized logging

## Best Practices Implemented

1. **Explicit Waits**: Used throughout for element interactions
2. **Page Factory**: Lazy initialization of web elements
3. **Thread-Safe Driver**: ThreadLocal for parallel execution
4. **Logging**: Comprehensive logging at each step
5. **Exception Handling**: Try-catch blocks for robust error handling
6. **Clean Code**: Meaningful names, comments, and documentation
7. **DRY Principle**: Reusable methods in base classes
8. **Configuration Management**: Externalized configuration
9. **Test Data Separation**: Properties files for test data
10. **Screenshot Evidence**: Automatic capture on failures

## Browser Support

### Chrome (Default)
```properties
browser=chrome
```

### Firefox
```properties
browser=firefox
```

### Edge
```properties
browser=edge
```

## Troubleshooting

### Common Issues

1. **ChromeDriver version mismatch**
   - WebDriverManager automatically handles this
   - Clear Maven cache: `mvn clean`

2. **Tests failing randomly**
   - Increase wait times in config.properties
   - Check network connectivity

3. **Screenshots not captured**
   - Verify `screenshots/` folder exists
   - Check write permissions

4. **Logs not generated**
   - Verify `logs/` folder exists
   - Check log4j2.xml configuration

## Extending the Framework

### Adding New Page Objects
1. Create new class in `src/main/java/pages/`
2. Extend `BasePage`
3. Define page elements using `@FindBy`
4. Implement page action methods

### Adding New Tests
1. Create test class in appropriate package
2. Extend `BaseTest`
3. Use `@Test` annotation
4. Add to `testng.xml`

### Adding New Utilities
1. Create utility class in `src/main/java/utils/`
2. Implement static helper methods
3. Use across test classes

## CI/CD Integration

### Jenkins
```bash
mvn clean test -Dbrowser=chrome
```

### GitLab CI
```yaml
test:
  script:
    - mvn clean test
  artifacts:
    paths:
      - target/surefire-reports/
      - screenshots/
      - logs/
```

### GitHub Actions
```yaml
- name: Run Tests
  run: mvn clean test
```

## Maintenance

### Regular Updates
1. Update dependencies in pom.xml
2. Review and update test data
3. Refactor flaky tests
4. Update documentation

## Support & Contact
For issues or questions:
- Create an issue in the repository
- Contact QA team

## License
Proprietary - Internal Use Only

---

**Last Updated**: February 2026  
**Framework Version**: 1.0  
**Author**: QA Automation Team
