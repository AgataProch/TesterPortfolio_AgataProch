# API Testing (Postman)

This folder contains Postman collections created during my QA training at **Czechitas Digital Academy: Testing**.  
Each collection demonstrates practical API testing using real-world endpoints, assertions, and variables.

---

## Tools & Skills Shown
- Postman collections and environments  
- Variables and data chaining  
- Status code validation  
- JSON schema & field assertions  
- API authentication (Bearer token, API key)  
- Negative testing & error handling  

---

## Czechibank E2E

**Goal:** Verify complete end-to-end flow of the Czechibank application — from user and bank account creation to transactions and balance validation.

### Structure
1. **01_User_creation** → creates a new user and stores `apiKey`  
2. **02_Initial_bank_account** → verifies default account and balance (100 000)  
3. **03_New_account** → creates a new account with `CZECHITOKEN` currency  
4. **04_Rename_new_account** → renames the new account (PATCH method)  
5. **05_Create_transaction** → transfers 100 CZK between accounts  
6. **06_Balance_changed_IC** → verifies that the sender’s balance decreased  
7. **07_Balance_changed_NC** → verifies that the recipient’s balance increased  

### Key checks
- HTTP codes: `201`, `200`, `401` (for auth), `400/404` for negative cases  
- Data validation through `pm.expect()` assertions  
- Chaining variables (`apiKey`, `accId`, `accNr`) between requests  

### Run
1. Import the collection into **Postman**  
2. Set `baseURL` in **Collection Variables**  
3. Run all requests sequentially to simulate a full E2E user flow  

---

## CzechitasApp — US_33 Application Categories

**Goal:** Test the `/categories` endpoint for different user roles and HTTP methods.

### Covered scenarios
1. **List categories** — for multiple roles:
   - `MA` (Main Admin)
   - `Admin`
   - `Parent`
2. **Unauthorized cases**:
   - Missing token → expect `401 Unauthenticated`
   - Blocked user → expect `403 Unauthorized`
3. **Invalid methods**:
   - `POST`, `DELETE`, `PUT`, `PATCH` → expect `405 Method Not Allowed`

### Key checks
- JSON structure and field validation (`id`, `name`, `slug`, `content`, etc.)
- Data type assertions for every field (string, number, array)
- Bearer token authentication
- Handling of access levels and permission errors

### Run
1. Import `CzechitasApp_US_33 Application categories.postman_collection.json`  
2. Set `{{baseURL}}` variable  
3. Run individual requests or the entire collection

