# OrangeHRM DemoSite UI Automation ✅

Comprehensive overview of the UI automation project for the OrangeHRM demo site: folder structure, technology stack, how to run tests, reports, and development guidelines.

---

## 🚀 Project Overview

This repository contains an automated UI test framework built with Java, using Cucumber BDD feature files and TestNG/JUnit runners. It is designed to test the OrangeHRM demo site and provide readable reports and logs.

## 🧰 Tech Stack

- **Language:** Java 17
- **Build tool:** Apache Maven
- **Test frameworks:** Cucumber (6.10.2), TestNG (7.x), JUnit 4 (for Cucumber JUnit runner)
- **Browser automation:** Selenium WebDriver (4.x)
- **Logging:** Log4j2
- **Reporting:** maven-cucumber-reporting (generates HTML reports from `target/cucumber.json`)

> Note: The exact dependency versions are declared in `pom.xml` (e.g., Cucumber, Selenium, TestNG, Log4j2).

---

## 📁 Folder Structure

Below is the main project tree (top-level important files and folders):

```
config.properties
pom.xml
logs/
screenshots/
src/
  main/
    java/
    resources/
  test/
    java/
      baseClass/
        BaseClass.java
        DriverManager.java
        EnvParams.java
      features/
        Login.feature
        ResetPage.feature
      hooks/
        Hooks.java
      pageObjects/
        BasePage.java
        LoginPage.java
        ResetPasswordPage.java
      stepDefinitions/
        LoginSteps.java
        ResetPasswordSteps.java
      testRunner/
        TestRunnerJunit.java
        TestRunnerTestNG.java
        TestRunnerTestNGParallel.java
      utils/
        ReadProperties.java
    resources/
      log4j2.properties
suites/
  testng.xml
target/
  cucumber-html-report.html
  cucumber.json
testData/
  TestData.properties
```

### Folder & file descriptions

- **config.properties** - central configuration (URLs, env-specific values).
- **pom.xml** - Maven build file; declares dependencies and plugins (Surefire, cucumber reporting).
- **logs/** - folder for runtime logs (Log4j2 output).
- **screenshots/** - screenshots captured during test failures or for debugging.
- **src/test/java/baseClass/** - foundational test classes (test bootstrap, driver management, env params).
- **src/test/java/features/** - Cucumber `.feature` files (Gherkin scenarios).
- **src/test/java/hooks/** - Cucumber/Test hooks (setup/teardown; e.g., `Hooks.java`).
- **src/test/java/pageObjects/** - Page Object Model classes representing UI pages for reusability.
- **src/test/java/stepDefinitions/** - Cucumber step implementations used by feature files.
- **src/test/java/testRunner/** - Test runners for JUnit and TestNG (launchers for Cucumber scenarios or TestNG suites).
- **src/test/java/utils/** - Helper utilities (property readers, extra hooks/backups).
- **src/test/resources/** - test resource files (logging config such as `log4j2.properties`).
- **suites/testng.xml** - TestNG suite configuration (used by Maven Surefire plugin).
- **target/** - generated artifacts and reports after running tests (e.g., `cucumber.json`, HTML reports).
- **testData/** - data files used by tests (e.g., `TestData.properties`).

---

## ▶️ How to run tests

- Run the default TestNG suite (configured in `suites/testng.xml`):

```bash
mvn test
```

- Run a specific TestNG suite or test class via Maven/Surefire options (example):

```bash
mvn -Dtest=TestRunnerJunit test
```
- Generate the cucumber HTML report (the reporting plugin runs in `verify` phase):

```bash
mvn verify
# Look for generated reports under target/cucumber-report or target/cucumber-html-report.html
```

---

## 🔁 CI / CD — GitHub Actions

This project can be run in CI using GitHub Actions or Jenkins. Below are example configurations and recommendations.

### GitHub Actions (example workflow)

Create `.github/workflows/maven.yml` containing (example):

```yaml
name: Java CI with Maven

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Cache Maven packages
        uses: actions/cache@v4
        with:
          path: ~/.m2/repository
          key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-maven-
      - name: Run tests and generate reports
        run: mvn -B -DskipTests=false verify
      - name: Upload cucumber report and artifacts
        uses: actions/upload-artifact@v4
        with:
          name: cucumber-report
          path: |
            target/cucumber-report
            target/cucumber.json
```

## 🧾 Example test run output (sample)

When you run `mvn test` you will see an output similar to the following (truncated example):

```text
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running org.example.test.TestRunnerTestNG
2025-12-17 12:34:56 INFO  BaseClass: Starting ChromeDriver
2025-12-17 12:35:00 INFO  LoginSteps: Given I am on the login page
2025-12-17 12:35:03 INFO  LoginSteps: When I enter valid credentials
2025-12-17 12:35:12 INFO  LoginSteps: Then I should be logged in
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 18.123 s - in org.example.test.TestRunnerTestNG
[INFO] 
[INFO] --- maven-surefire-plugin:3.5.4:test (default-test) @ OrangeHRM_DemoSiteUIAutomation ---
[INFO] BUILD SUCCESS
```

Reports and artifacts are stored under `target/` — e.g., `target/cucumber.json`, `target/cucumber-report`, and `target/cucumber-html-report.html`.

---

## 📷 Example screenshot



## 📊 Reports & Artifacts

- **Cucumber JSON**: `target/cucumber.json` (used to generate HTML reports).
- **Cucumber HTML**: `target/cucumber-html-report.html` (or generated under `target/cucumber-report` by the reporting plugin).
- **Screenshots**: saved in `/screenshots` on failures.
- **Logs**: saved in `/logs` (configurable via `log4j2.properties`).

---

## ⚙️ Configuration & Environment

- **Java**: Install Java 17 and set `JAVA_HOME`.
- **Maven**: Install Maven and ensure `mvn` is in PATH.
- **Browsers & Drivers**: Ensure browsers are installed (Chrome, Firefox) and corresponding WebDriver binaries are available, or configure automatic driver management in `DriverManager`.

> Tip: The `DriverManager` usually controls WebDriver setup; check `src/test/java/baseClass/DriverManager.java` to confirm how drivers are managed in your environment.

---

## ✨ Adding tests

1. Add a new `.feature` file to `src/test/java/features/`.
2. Implement step definitions in `src/test/java/stepDefinitions/` (create new classes as needed).
3. Add or update Page Object classes in `src/test/java/pageObjects/`.
4. Run the feature using the appropriate runner (TestNG or JUnit runner).

