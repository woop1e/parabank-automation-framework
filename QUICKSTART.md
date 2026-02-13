# ParaBank Automation Framework - Quick Start Guide

## Prerequisites Checklist
- [ ] Java 11 installed (`java -version`)
- [ ] Maven 3.9+ installed (`mvn -version`)
- [ ] Chrome browser installed
- [ ] IDE installed (optional)

## Setup in 5 Minutes

### Step 1: Navigate to Project
```bash
cd parabank-automation-framework
```

### Step 2: Install Dependencies
```bash
mvn clean install -DskipTests
```
**Expected Output**: BUILD SUCCESS

### Step 3: Run Your First Test
```bash
mvn clean test -Dtest=SmokeTests#testHomepageLoadsSuccessfully
```

### Step 4: Run All Smoke Tests
```bash
mvn clean test -Dtest=SmokeTests
```

### Step 5: Run Complete Suite
```bash
mvn clean test
```

## View Reports

### TestNG HTML Report
1. Navigate to `test-output/index.html`
2. Open in browser
3. View detailed test results

### Logs
1. Navigate to `logs/automation.log`
2. View execution details

### Screenshots (On Failures)
1. Navigate to `screenshots/`
2. View failed test screenshots

## Test Execution Examples

### Run Individual Test Suites
```bash
# Smoke Tests Only (5 tests)
mvn clean test -Dtest=SmokeTests

# Regression Tests Only (5 tests)
mvn clean test -Dtest=RegressionTests

# Negative Tests Only (3 tests)
mvn clean test -Dtest=NegativeTests
```

### Run Specific Test Method
```bash
mvn clean test -Dtest=SmokeTests#testValidLogin
```

### Run with Different Browser
Edit `src/test/resources/config/config.properties`:
```properties
browser=firefox  # or chrome or edge
```

## Quick Troubleshooting

### Issue: Tests fail with "WebDriver not found"
**Solution**: Run `mvn clean install` again

### Issue: Chrome version mismatch
**Solution**: WebDriverManager handles this automatically. Update Chrome browser.

### Issue: No test results
**Solution**: Check `test-output/` and `target/surefire-reports/` folders

### Issue: Permission denied for screenshots
**Solution**: Ensure `screenshots/` folder has write permissions

## Test Results Summary

After running `mvn clean test`, you should see:
```
[INFO] Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Expected Test Count:
- Smoke Tests: 5
- Regression Tests: 5
- Negative Tests: 3
- **Total: 13 tests**

## Next Steps

1. ✅ Review test results in TestNG report
2. ✅ Check logs for detailed execution flow
3. ✅ Examine page objects in `src/main/java/pages/`
4. ✅ Review test classes in `src/test/java/`
5. ✅ Explore framework utilities in `src/main/java/utils/`

## Common Maven Commands

```bash
# Clean and compile
mvn clean compile

# Run tests with verbose output
mvn clean test -X

# Skip tests during build
mvn clean install -DskipTests

# Run tests and generate reports
mvn clean test site

# Run with specific Java version
mvn clean test -Djava.version=11
```

## IDE Setup (IntelliJ IDEA)

1. Open IntelliJ IDEA
2. File → Open → Select project folder
3. Wait for Maven import
4. Right-click `testng.xml` → Run
5. View results in IDE

## IDE Setup (Eclipse)

1. Open Eclipse
2. File → Import → Maven → Existing Maven Projects
3. Browse to project folder
4. Right-click `testng.xml` → Run As → TestNG Suite

## Support

If you encounter issues:
1. Check logs in `logs/automation.log`
2. Review README.md for detailed documentation
3. Verify all prerequisites are met
4. Ensure internet connectivity for WebDriverManager

---

**Happy Testing! 🚀**
