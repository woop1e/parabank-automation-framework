# ParaBank Automation Framework - Installation & Execution Checklist

## ✅ Pre-Installation Checklist

### System Requirements
- [ ] Operating System: Windows 10+, macOS 10.14+, or Linux (Ubuntu 18.04+)
- [ ] Minimum 4GB RAM available
- [ ] Minimum 2GB free disk space
- [ ] Internet connection for dependency downloads

### Software Prerequisites
- [ ] Java Development Kit (JDK) 11 or higher installed
- [ ] Apache Maven 3.9 or higher installed
- [ ] Google Chrome browser (latest version)
- [ ] IDE (Optional): IntelliJ IDEA / Eclipse / VS Code

## ✅ Installation Steps

### Step 1: Verify Java Installation
```bash
java -version
```
**Expected Output**: `java version "11.x.x"` or higher

If not installed:
- Download from: https://www.oracle.com/java/technologies/downloads/
- Set JAVA_HOME environment variable
- Add Java bin to PATH

### Step 2: Verify Maven Installation
```bash
mvn -version
```
**Expected Output**: `Apache Maven 3.9.x` or higher

If not installed:
- Download from: https://maven.apache.org/download.cgi
- Set M2_HOME environment variable
- Add Maven bin to PATH

### Step 3: Verify Chrome Browser
- Open Chrome browser
- Go to Settings → About Chrome
- Verify latest version installed

### Step 4: Extract Framework
- [ ] Extract `parabank-automation-framework.zip`
- [ ] Navigate to project directory
```bash
cd parabank-automation-framework
```

### Step 5: Install Dependencies
```bash
mvn clean install -DskipTests
```
**Expected Output**: 
```
[INFO] BUILD SUCCESS
[INFO] Total time: ~2-3 minutes
```

### Step 6: Verify Project Structure
Check that the following folders exist:
- [ ] src/main/java/
- [ ] src/test/java/
- [ ] src/test/resources/
- [ ] screenshots/
- [ ] logs/

## ✅ First Test Execution

### Quick Smoke Test (Single Test)
```bash
mvn clean test -Dtest=SmokeTests#testHomepageLoadsSuccessfully
```

**Expected Output**:
```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

**If Successful**: ✅ Framework is working correctly  
**If Failed**: See Troubleshooting section below

### Full Smoke Suite (5 Tests)
```bash
mvn clean test -Dtest=SmokeTests
```

**Expected Output**:
```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
Execution Time: ~2 minutes
[INFO] BUILD SUCCESS
```

### Complete Test Suite (All 13 Tests)
```bash
mvn clean test
```

**Expected Output**:
```
Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
Execution Time: ~5-7 minutes
[INFO] BUILD SUCCESS
```

## ✅ Verify Reports & Artifacts

### TestNG HTML Report
- [ ] Navigate to: `test-output/index.html`
- [ ] Open in browser
- [ ] Verify test results displayed

### Log Files
- [ ] Navigate to: `logs/automation.log`
- [ ] Verify log entries present
- [ ] Check for any ERROR entries

### Screenshots (Only for Failed Tests)
- [ ] Navigate to: `screenshots/`
- [ ] If tests passed, folder should be empty
- [ ] If tests failed, screenshots should be present

## ✅ Configuration Verification

### Check config.properties
```bash
cat src/test/resources/config/config.properties
```

Verify:
- [ ] base.url=https://parabank.parasoft.com/parabank/index.htm
- [ ] browser=chrome
- [ ] implicit.wait=10
- [ ] explicit.wait=15

### Check testng.xml
```bash
cat src/test/resources/testng.xml
```

Verify:
- [ ] Suite name defined
- [ ] All test classes included
- [ ] Parallel execution configured

## ✅ IDE Setup (Optional)

### IntelliJ IDEA
1. [ ] Open IntelliJ IDEA
2. [ ] File → Open → Select `parabank-automation-framework` folder
3. [ ] Wait for Maven project import (bottom right corner)
4. [ ] Verify project structure in Project view
5. [ ] Right-click `testng.xml` → Run
6. [ ] Verify tests execute successfully

### Eclipse
1. [ ] Open Eclipse
2. [ ] File → Import → Maven → Existing Maven Projects
3. [ ] Browse to `parabank-automation-framework` folder
4. [ ] Click Finish
5. [ ] Right-click `testng.xml` → Run As → TestNG Suite
6. [ ] Verify tests execute successfully

## ✅ Test Execution Validation

### Run Each Test Suite Individually

#### Smoke Tests
```bash
mvn clean test -Dtest=SmokeTests
```
- [ ] Tests run: 5
- [ ] Failures: 0
- [ ] Errors: 0

#### Regression Tests
```bash
mvn clean test -Dtest=RegressionTests
```
- [ ] Tests run: 5
- [ ] Failures: 0
- [ ] Errors: 0

#### Negative Tests
```bash
mvn clean test -Dtest=NegativeTests
```
- [ ] Tests run: 3
- [ ] Failures: 0
- [ ] Errors: 0

### Parallel Execution Test
```bash
mvn clean test -Dparallel=tests -DthreadCount=3
```
- [ ] Tests run: 13
- [ ] Execution time: ~3-4 minutes (faster than sequential)
- [ ] No threading issues

## ✅ Troubleshooting Common Issues

### Issue 1: Maven Build Failure
**Symptom**: `mvn clean install` fails

**Solutions**:
- [ ] Verify Java version: `java -version`
- [ ] Verify Maven version: `mvn -version`
- [ ] Delete `.m2/repository` folder and retry
- [ ] Check internet connection
- [ ] Run: `mvn clean install -U` (force update)

### Issue 2: ChromeDriver Error
**Symptom**: "ChromeDriver not found" or version mismatch

**Solutions**:
- [ ] Update Chrome browser to latest version
- [ ] Run: `mvn clean install -DskipTests`
- [ ] WebDriverManager will auto-download correct driver
- [ ] Verify in logs: "ChromeDriver initialized"

### Issue 3: Tests Failing
**Symptom**: Tests fail with errors

**Solutions**:
- [ ] Check logs: `logs/automation.log`
- [ ] Verify application URL is accessible: https://parabank.parasoft.com
- [ ] Increase wait times in `config.properties`
- [ ] Run single test to isolate issue
- [ ] Check screenshots folder for visual evidence

### Issue 4: Reports Not Generated
**Symptom**: `test-output/` folder empty

**Solutions**:
- [ ] Verify TestNG dependency in `pom.xml`
- [ ] Check Maven Surefire plugin configuration
- [ ] Run: `mvn clean test` (not just `mvn test`)
- [ ] Look in `target/surefire-reports/` as alternative

### Issue 5: Permission Errors
**Symptom**: Cannot write to screenshots/ or logs/

**Solutions**:
- [ ] Check folder permissions
- [ ] Run with admin/sudo if needed (not recommended)
- [ ] Verify folders exist and are writable
- [ ] Check disk space available

### Issue 6: Network Errors
**Symptom**: Cannot reach application URL

**Solutions**:
- [ ] Check internet connection
- [ ] Verify URL: https://parabank.parasoft.com/parabank/index.htm
- [ ] Try accessing URL in browser manually
- [ ] Check firewall settings
- [ ] Verify proxy settings if applicable

## ✅ Post-Installation Tasks

### Documentation Review
- [ ] Read README.md for comprehensive overview
- [ ] Read QUICKSTART.md for quick reference
- [ ] Read EXECUTION_GUIDE.md for detailed execution
- [ ] Read ARCHITECTURE.md for framework design

### Explore Framework
- [ ] Review Page Objects in `src/main/java/pages/`
- [ ] Review Test Classes in `src/test/java/`
- [ ] Review Utilities in `src/main/java/utils/`
- [ ] Review Configuration files

### Customization (Optional)
- [ ] Update test data in `testdata.properties`
- [ ] Modify wait times in `config.properties`
- [ ] Add new page objects as needed
- [ ] Create custom test suites

## ✅ Success Criteria

Your installation is successful when:
- ✅ All prerequisites installed
- ✅ Dependencies downloaded successfully
- ✅ All 13 tests pass
- ✅ Reports generated correctly
- ✅ Logs contain detailed execution info
- ✅ No errors in Maven output
- ✅ Screenshots captured on failures (if any)

## ✅ Next Steps After Installation

1. [ ] Run all tests to establish baseline
2. [ ] Review test reports
3. [ ] Examine logs for execution flow
4. [ ] Study page objects and utilities
5. [ ] Customize for your needs
6. [ ] Add to version control
7. [ ] Set up CI/CD integration (optional)

## ✅ Support Resources

If issues persist:
- Check logs: `logs/automation.log`
- Review documentation: README.md
- Verify system requirements
- Check Java and Maven paths
- Ensure Chrome is updated

## Quick Command Reference

```bash
# Installation
mvn clean install -DskipTests

# Run all tests
mvn clean test

# Run specific suite
mvn clean test -Dtest=SmokeTests

# Run with parallel execution
mvn clean test -Dparallel=tests -DthreadCount=3

# Run with specific browser
mvn clean test -Dbrowser=firefox

# Generate reports
mvn clean test site

# View help
mvn help:describe -Dplugin=surefire
```

---

## Installation Complete! 🎉

Your ParaBank Selenium Automation Framework is now ready to use.

**Next Command**: `mvn clean test`

**Happy Testing!** 🚀

---

**Installation Guide Version**: 1.0  
**Last Updated**: February 2026  
**Framework Version**: 1.0
