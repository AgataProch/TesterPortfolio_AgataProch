# Low-code Automation (IntelliJ IDEA)

This folder contains my **low-code UI automation practice from the Czechitas Digital Academy: Testing**.
The project is built on top of a pre-made educational framework, where I created and executed custom UI test scenarios directly in IntelliJ IDEA.

**The main part of my work is located in**:
👉 LowCodeAutomationTest.java

---

## Tools & Skills Shown
- Working with a structured low-code automation framework
- IntelliJ IDEA (test execution, debugging, understanding project structure)
- UI automation using predefined actions and page objects
- Designing clear end-to-end UI scenarios
- Filling forms, selecting options, navigating between pages
- Using built-in assertions to validate UI behaviour
- Reading and extending an existing codebase
- Logical thinking & scenario design

---

## Structure
1. **Framework actions** → reusable steps for interacting with UI elements
2. **Page objects** → mapped screens and components of the tested app
3. **Assertions** → built-in validations for verifying expected results
4. **My custom tests** → located in src/test/java/.../LowCodeAutomationTest.java

---

## LowCodeAutomationTest.java
**Goal**:
Create end-to-end UI test scenarios using predefined low-code actions and validate user flows inside the Czechitas demo application.

### Covered flows
1. **Navigation**
– switching between Parents / Teachers sections
2. **Form filling**
– first name, last name, date of birth, notes
3. **Category selection**
– choosing program types & options
4. **Payment selection**
– choosing payment method
5. **Terms & Conditions**
– accepting legal checkboxes
6. **Submitting an application**
– completing the flow end-to-end
7. **Validation**
– verifying application details using built-in assertions

---

## How I worked on this project
All test cases were written, executed, and debugged entirely inside IntelliJ IDEA, using:
- predefined action classes
- ready-made page objects
- custom assertion helpers
- step-by-step execution
- console output for debugging

This setup simulates real tester work in low-code automation tools.

---

## What This Demonstrates
- ability to understand and use a structured automation framework
- creating readable, scenario-based UI tests
- using predefined components effectively (actions, pages, assertions)
- executing automated tests in IntelliJ IDEA
- validating real UI behaviour
- strong logical & analytical thinking