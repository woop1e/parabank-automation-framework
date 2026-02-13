# Test Execution Guide - ParaBank Automation Framework

## Table of Contents
1. [Execution Methods](#execution-methods)
2. [Test Suite Configuration](#test-suite-configuration)
3. [Parallel Execution](#parallel-execution)
4. [Browser Configuration](#browser-configuration)
5. [Report Generation](#report-generation)
6. [CI/CD Integration](#cicd-integration)

---

## Execution Methods

### Method 1: Maven Command Line (Recommended)

#### Run All Tests
```bash
mvn clean test
```

#### Run Specific Test Class
```bash
mvn clean test -Dtest=SmokeTests
mvn clean test -Dtest=RegressionTests
mvn clean test -Dtest=NegativeTests
```

#### Run Specific Test Method
```bash
mvn clean test -Dtest=SmokeTests#testValidLogin
mvn clean test -Dtest=RegressionTests#testRegistrationNewUserSuccess
```

#### Run Multiple Test Classes
```bash
mvn clean test -Dtest=SmokeTests,RegressionTests
```

### Method 2: TestNG XML Suite
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Method 3: IDE Execution

#### IntelliJ IDEA
1. Right-click on `testng.xml`
2. Select "Run testng.xml"
3. View results in Run window

#### Eclipse
1. Right-click on `testng.xml`
2. Select "Run As" → "TestNG Suite"
3. View results in TestNG tab

---

## Test Suite Configuration

### TestNG XML Structure
```xml
<suite name="ParaBank Test Suite" parallel="tests" thread-count="3">
    <test name="Smoke Tests">
        <classes>
            <class name="smoke.SmokeTests"/>
        </classes>
    </test>
</suite>
```

### Run Custom Suite
Create custom XML file:
```xml
<!-- smoke-only.xml -->
<suite name="Smoke Suite">
    <test name="Smoke Tests">
        <classes>
            <class name="smoke.SmokeTests"/>
        </classes>
    </test>
</suite>
```

Execute:
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/smoke-only.xml
```

---

## Parallel Execution

### Configuration in testng.xml

#### Parallel Tests (Recommended)
```xml
<suite parallel="tests" thread-count="3">
```

#### Parallel Classes
```xml
<suite parallel="classes" thread-count="5">
```

#### Parallel Methods
```xml
<suite parallel="methods" thread-count="10">
```

### Maven Command for Parallel Execution
```bash
mvn clean test -Dparallel=tests -DthreadCount=3
```

### Important Notes
- Each test gets its own WebDriver instance
- ThreadLocal ensures thread safety
- Screenshots captured correctly per thread

---

## Browser Configuration

### Option 1: config.properties
Edit `src/test/resources/config/config.properties`:
```properties
browser=chrome  # Options: chrome, firefox, edge
```

### Option 2: Maven Command Line
```bash
mvn clean test -Dbrowser=firefox
```

### Option 3: TestNG Parameter
```xml
<suite>
    <parameter name="browser" value="chrome"/>
    <test name="Chrome Tests">
        ...
    </test>
</suite>
```

### Browser-Specific Execution
```bash
# Chrome
mvn clean test -Dbrowser=chrome

# Firefox
mvn clean test -Dbrowser=firefox

# Edge
mvn clean test -Dbrowser=edge
```

---

## Report Generation

### TestNG Default Report
Location: `test-output/index.html`

Generated automatically after:
```bash
mvn clean test
```

### Surefire Report
Location: `target/surefire-reports/index.html`

### View Reports
```bash
# Linux/Mac
open test-output/index.html

# Windows
start test-output/index.html
```

### Report Contents
- Test execution summary
- Pass/Fail status
- Execution time
- Stack traces for failures
- Test method details

---

## Test Execution Scenarios

### Scenario 1: Quick Smoke Test
**Purpose**: Verify critical functionality  
**Execution Time**: ~2-3 minutes
```bash
mvn clean test -Dtest=SmokeTests
```

### Scenario 2: Complete Regression
**Purpose**: Full test coverage  
**Execution Time**: ~5-7 minutes
```bash
mvn clean test
```

### Scenario 3: Negative Testing Only
**Purpose**: Validation testing  
**Execution Time**: ~1-2 minutes
```bash
mvn clean test -Dtest=NegativeTests
```

### Scenario 4: Parallel Full Suite
**Purpose**: Fast execution  
**Execution Time**: ~3-4 minutes
```bash
mvn clean test -Dparallel=tests -DthreadCount=5
```

---

## CI/CD Integration

### Jenkins Pipeline
```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'repository-url'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Report') {
            steps {
                publishHTML([
                    reportDir: 'test-output',
                    reportFiles: 'index.html',
                    reportName: 'TestNG Report'
                ])
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'screenshots/**,logs/**'
        }
    }
}
```

### GitLab CI/CD
```yaml
test:
  stage: test
  script:
    - mvn clean test
  artifacts:
    when: always
    paths:
      - test-output/
      - screenshots/
      - logs/
    reports:
      junit: target/surefire-reports/TEST-*.xml
```

### GitHub Actions
```yaml
name: Selenium Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: '11'
      - name: Run tests
        run: mvn clean test
      - name: Upload reports
        uses: actions/upload-artifact@v2
        with:
          name: test-reports
          path: |
            test-output/
            screenshots/
            logs/
```

---

## Execution Best Practices

### 1. Clean Before Test
```bash
mvn clean test
```
**Why**: Ensures fresh build and removes old artifacts

### 2. Verify Setup First
```bash
mvn clean test -Dtest=SmokeTests#testHomepageLoadsSuccessfully
```
**Why**: Quick validation before full suite

### 3. Use Parallel Execution
```bash
mvn clean test -Dparallel=tests -DthreadCount=3
```
**Why**: Reduces total execution time

### 4. Generate Fresh Logs
Delete `logs/` folder before execution for clean logs

### 5. Monitor Resource Usage
- Limit thread count based on system resources
- Recommended: thread-count = CPU cores

---

## Troubleshooting Execution Issues

### Issue: Tests Not Running
**Check**:
```bash
mvn clean compile
mvn dependency:tree
```

### Issue: Slow Execution
**Solutions**:
- Enable parallel execution
- Increase wait timeout values
- Use headless browser mode

### Issue: Random Failures
**Solutions**:
- Increase explicit wait times
- Add retry logic
- Check network connectivity

### Issue: Report Not Generated
**Check**:
- `test-output/` folder exists
- TestNG dependency in pom.xml
- Sufficient disk space

---

## Execution Checklist

Before running tests:
- [ ] Java 11+ installed
- [ ] Maven 3.9+ installed
- [ ] Chrome browser updated
- [ ] Dependencies installed (`mvn clean install`)
- [ ] config.properties configured
- [ ] Test data updated
- [ ] Sufficient disk space
- [ ] Network connectivity available

After running tests:
- [ ] Review TestNG report
- [ ] Check logs for errors
- [ ] Verify screenshot captures
- [ ] Analyze failures
- [ ] Document issues

---

## Performance Tips

1. **Use Headless Mode** (Optional)
   ```java
   ChromeOptions options = new ChromeOptions();
   options.addArguments("--headless");
   ```

2. **Optimize Wait Times**
   - Use minimum necessary wait times
   - Prefer explicit waits over Thread.sleep()

3. **Parallel Execution**
   - Use thread-count = number of CPU cores
   - Monitor memory usage

4. **Clean Resources**
   - Close browser after each test
   - Clean screenshots periodically

---

**End of Execution Guide**

For detailed framework documentation, see README.md
