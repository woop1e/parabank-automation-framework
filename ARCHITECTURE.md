# ParaBank Automation Framework - Architecture

## Framework Architecture Overview

```
┌─────────────────────────────────────────────────────────────────┐
│                    TEST EXECUTION LAYER                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐          │
│  │ Smoke Tests  │  │ Regression   │  │ Negative     │          │
│  │   (5 Tests)  │  │ Tests        │  │ Tests        │          │
│  │              │  │   (5 Tests)  │  │   (3 Tests)  │          │
│  └──────────────┘  └──────────────┘  └──────────────┘          │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                       BASE TEST LAYER                            │
│  ┌────────────────────────────────────────────────────────┐     │
│  │  BaseTest.java                                         │     │
│  │  - setUp() - Browser initialization                   │     │
│  │  - tearDown() - Browser cleanup & Screenshot capture  │     │
│  └────────────────────────────────────────────────────────┘     │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                      PAGE OBJECT LAYER                           │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐       │
│  │ HomePage │  │ Account  │  │ Register │  │ Transfer │       │
│  │          │  │ Overview │  │ Page     │  │ Funds    │       │
│  │          │  │ Page     │  │          │  │ Page     │       │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘       │
│                                                                  │
│  ┌──────────┐                                                   │
│  │ BillPay  │  All extend BasePage.java                        │
│  │ Page     │  - Common wait methods                           │
│  │          │  - Element interaction methods                   │
│  └──────────┘                                                   │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                      UTILITY LAYER                               │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐         │
│  │DriverFactory │  │ ConfigReader │  │ LoggerUtil   │         │
│  │ - WebDriver  │  │ - Properties │  │ - Log4j      │         │
│  │   Management │  │   Reader     │  │   Logging    │         │
│  └──────────────┘  └──────────────┘  └──────────────┘         │
│                                                                  │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐         │
│  │ Screenshot   │  │ WaitUtils    │  │ TestData     │         │
│  │ Utils        │  │ - Explicit   │  │ Reader       │         │
│  │ - Capture    │  │   Waits      │  │ - Test Data  │         │
│  └──────────────┘  └──────────────┘  └──────────────┘         │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                    WEBDRIVER LAYER                               │
│  ┌────────────────────────────────────────────────────────┐     │
│  │  WebDriverManager                                      │     │
│  │  - ChromeDriver                                        │     │
│  │  - FirefoxDriver                                       │     │
│  │  - EdgeDriver                                          │     │
│  └────────────────────────────────────────────────────────┘     │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│                 APPLICATION UNDER TEST                           │
│              https://parabank.parasoft.com                       │
└─────────────────────────────────────────────────────────────────┘

                              ↓

┌─────────────────────────────────────────────────────────────────┐
│                    REPORTING LAYER                               │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐         │
│  │ TestNG HTML  │  │ Log Files    │  │ Screenshots  │         │
│  │ Report       │  │ (logs/)      │  │ (screenshots)│         │
│  └──────────────┘  └──────────────┘  └──────────────┘         │
└─────────────────────────────────────────────────────────────────┘
```

## Component Responsibilities

### 1. Test Execution Layer
- **Smoke Tests**: Critical path validation (5 tests)
- **Regression Tests**: Feature-level testing (5 tests)
- **Negative Tests**: Validation & error handling (3 tests)

### 2. Base Test Layer
- Browser initialization and configuration
- Test lifecycle management (setUp/tearDown)
- Automatic screenshot capture on failures
- Resource cleanup

### 3. Page Object Layer
- Encapsulation of web elements
- Page-specific actions and validations
- Reusable methods across tests
- Inheritance from BasePage for common operations

### 4. Utility Layer
- **DriverFactory**: WebDriver instance management
- **ConfigReader**: Configuration properties handling
- **LoggerUtil**: Centralized logging mechanism
- **ScreenshotUtils**: Screenshot capture and storage
- **WaitUtils**: Custom explicit wait utilities
- **TestDataReader**: Test data management

### 5. WebDriver Layer
- Browser driver management via WebDriverManager
- Automatic driver download and configuration
- Multi-browser support

### 6. Reporting Layer
- TestNG HTML reports with execution summary
- Detailed logs with timestamps
- Screenshots for failed test cases

## Data Flow

```
Test Execution
     ↓
BaseTest setUp()
     ↓
DriverFactory creates WebDriver
     ↓
ConfigReader loads configuration
     ↓
Navigate to Application
     ↓
Page Objects interact with elements
     ↓
Assertions validate results
     ↓
LoggerUtil logs actions
     ↓
BaseTest tearDown()
     ↓
Screenshot on failure (if applicable)
     ↓
Generate TestNG Report
```

## Design Patterns Used

### 1. Page Object Model (POM)
- Separation of page structure from test logic
- Maintainability and reusability
- Reduced code duplication

### 2. Factory Pattern
- DriverFactory for WebDriver creation
- Centralized driver management
- Easy browser switching

### 3. Singleton Pattern
- Single instance of ConfigReader
- Single instance of Logger
- Resource optimization

### 4. Repository Pattern
- TestDataReader for centralized test data
- Configuration externalization
- Easy data maintenance

## Test Execution Flow

```
1. TestNG initiates test execution
   ↓
2. @BeforeMethod: setUp()
   - Create WebDriver instance
   - Load configuration
   - Navigate to base URL
   - Maximize window
   ↓
3. @Test: Execute test logic
   - Initialize page objects
   - Perform actions
   - Validate results
   - Log steps
   ↓
4. @AfterMethod: tearDown()
   - Check test result
   - Capture screenshot (if failed)
   - Close browser
   - Log summary
   ↓
5. Generate TestNG Report
```

## File Organization

```
Framework Root
│
├── src/main/java/          # Source code
│   ├── base/               # Base classes
│   ├── pages/              # Page objects
│   └── utils/              # Utility classes
│
├── src/test/java/          # Test classes
│   ├── smoke/              # Smoke tests
│   ├── regression/         # Regression tests
│   └── negative/           # Negative tests
│
├── src/test/resources/     # Resources
│   ├── config/             # Configuration files
│   ├── testdata/           # Test data
│   ├── testng.xml          # TestNG suite
│   └── log4j2.xml          # Logging config
│
├── screenshots/            # Screenshot storage
├── logs/                   # Log file storage
├── test-output/            # TestNG reports
├── target/                 # Build artifacts
│
└── pom.xml                 # Maven configuration
```

## Key Features

### Modularity
- Separate concerns across layers
- Independent components
- Easy maintenance

### Reusability
- Base classes for common operations
- Utility methods across framework
- Page objects shared across tests

### Scalability
- Easy to add new pages
- Simple to add new tests
- Configurable parallel execution

### Maintainability
- Clear separation of concerns
- Consistent naming conventions
- Comprehensive documentation

### Reporting
- Multiple reporting formats
- Detailed logs
- Visual evidence via screenshots

## Benefits of This Architecture

1. **Separation of Concerns**: Each layer has specific responsibility
2. **Reusability**: Common code in base and utility classes
3. **Maintainability**: Changes localized to specific layers
4. **Scalability**: Easy to extend and add new features
5. **Testability**: Independent components easy to test
6. **Readability**: Clear structure and organization
7. **Flexibility**: Support for multiple browsers and configurations

---

**Framework Version**: 1.0  
**Last Updated**: February 2026
