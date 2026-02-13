# ParaBank Automation Framework - Complete File Structure

## Project Root
```
parabank-automation-framework/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       │   ├── BasePage.java              (Common page operations)
│   │       │   └── BaseTest.java              (Test setup/teardown)
│   │       │
│   │       ├── pages/
│   │       │   ├── AccountOverviewPage.java   (Account overview page object)
│   │       │   ├── BillPayPage.java           (Bill payment page object)
│   │       │   ├── HomePage.java              (Home/Login page object)
│   │       │   ├── RegisterPage.java          (Registration page object)
│   │       │   └── TransferFundsPage.java     (Transfer funds page object)
│   │       │
│   │       └── utils/
│   │           ├── ConfigReader.java          (Configuration reader)
│   │           ├── DriverFactory.java         (WebDriver factory)
│   │           ├── LoggerUtil.java            (Logging utility)
│   │           ├── ScreenshotUtils.java       (Screenshot capture)
│   │           ├── TestDataReader.java        (Test data reader)
│   │           └── WaitUtils.java             (Custom wait utilities)
│   │
│   └── test/
│       ├── java/
│       │   ├── smoke/
│       │   │   └── SmokeTests.java            (5 smoke tests)
│       │   │
│       │   ├── regression/
│       │   │   └── RegressionTests.java       (5 regression tests)
│       │   │
│       │   └── negative/
│       │       └── NegativeTests.java         (3 negative tests)
│       │
│       └── resources/
│           ├── config/
│           │   └── config.properties          (Application configuration)
│           │
│           ├── testdata/
│           │   └── testdata.properties        (Test data)
│           │
│           ├── testng.xml                     (TestNG suite configuration)
│           └── log4j2.xml                     (Logging configuration)
│
├── screenshots/                               (Auto-generated screenshots)
├── logs/                                      (Auto-generated log files)
├── test-output/                               (TestNG HTML reports)
├── target/                                    (Maven build output)
│
├── pom.xml                                    (Maven configuration)
├── .gitignore                                 (Git ignore file)
│
├── README.md                                  (Main documentation)
├── QUICKSTART.md                              (Quick start guide)
├── EXECUTION_GUIDE.md                         (Execution instructions)
├── ARCHITECTURE.md                            (Architecture documentation)
└── FRAMEWORK_SUMMARY.md                       (Complete summary)
```

## File Count Summary

### Java Source Files (Main)
- Base Classes: 2
- Page Objects: 5
- Utilities: 6
**Total**: 13 files

### Java Test Files
- Smoke Tests: 1 class (5 tests)
- Regression Tests: 1 class (5 tests)
- Negative Tests: 1 class (3 tests)
**Total**: 3 test classes, 13 test methods

### Configuration Files
- pom.xml (Maven)
- config.properties (App config)
- testdata.properties (Test data)
- testng.xml (TestNG suite)
- log4j2.xml (Logging)
**Total**: 5 files

### Documentation Files
- README.md
- QUICKSTART.md
- EXECUTION_GUIDE.md
- ARCHITECTURE.md
- FRAMEWORK_SUMMARY.md
**Total**: 5 files

## Total Project Statistics

- **Java Classes**: 16 (13 main + 3 test)
- **Test Methods**: 13
- **Configuration Files**: 5
- **Documentation Files**: 5
- **Page Objects**: 5
- **Utility Classes**: 6
- **Base Classes**: 2

## Key Directories

### Source Code (src/main/java/)
All production code including page objects, utilities, and base classes.

### Test Code (src/test/java/)
All test classes organized by test type (smoke, regression, negative).

### Resources (src/test/resources/)
Configuration files, test data, and TestNG suite definitions.

### Reports & Artifacts
- screenshots/ - Test failure screenshots
- logs/ - Execution logs
- test-output/ - TestNG HTML reports
- target/ - Maven build artifacts

## File Relationships

```
BaseTest.java
    ├── Uses → DriverFactory.java
    ├── Uses → ConfigReader.java
    ├── Uses → LoggerUtil.java
    ├── Uses → ScreenshotUtils.java
    └── Extended by → All Test Classes

BasePage.java
    ├── Uses → LoggerUtil.java
    ├── Uses → WaitUtils.java
    └── Extended by → All Page Objects

All Page Objects
    ├── Extend → BasePage.java
    └── Used by → Test Classes

All Test Classes
    ├── Extend → BaseTest.java
    └── Use → Page Objects
```

## Configuration Dependencies

```
pom.xml
    └── Defines → All Maven dependencies

config.properties
    └── Used by → ConfigReader.java → BaseTest.java

testdata.properties
    └── Used by → TestDataReader.java → Test Classes

testng.xml
    └── Defines → Test execution flow

log4j2.xml
    └── Configures → LoggerUtil.java
```

## Build & Execution Flow

```
pom.xml
    ↓
Maven Surefire Plugin
    ↓
testng.xml
    ↓
Test Classes (Smoke, Regression, Negative)
    ↓
BaseTest Setup
    ↓
Page Objects
    ↓
Test Execution
    ↓
BaseTest Teardown
    ↓
Reports (test-output/, logs/, screenshots/)
```

---

**Framework Structure Version**: 1.0  
**Total Files**: 40+  
**Total Lines of Code**: ~2,500+
