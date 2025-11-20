package cz.czechitas.automation;

import org.junit.jupiter.api.Test;

/**
 * Test class for custom student tests
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class LowCodeAutomationTest extends TestRunner {

    String userEmail = "agi.test@gmail.com";
    String userPassword = "Agitest1";

    String otherEmail = "honza.test@gmail.com";
    String otherPassword = "Honzatest1";


    // LOW-CODE AUTOMATION PRACTICE I.
// NAVIGACE
// 1. Napište test, který přejde do sekce Pro rodiče -> Návody a formuláře
    @Test
    void goToForParentSectionTest() {
        browser.headerMenu.goToInstructionsAndFormsForParentSection();
    //   asserter.checkPageUrl("www.czechitas.cz/pro-rodice");
}

// 2. Napište test, který přejde do sekce Pro učitele -> Objednávka pro MŠ/ZŠ a jako
// objednávanou službu v dolní části obrazovky zvolí Příměstský tábor.
    @Test
    void goToTeacherSectionTest() {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.selectSuburbanCampOption();
        // asserter.checkPageUrl("");
}

// 3. Napište test, který postupně projde všechny sekce horního veřejného menu.
    @Test
    void goThroughHeaderMenuTest() {
        browser.headerMenu.goToInstructionsAndFormsForParentSection();
        browser.headerMenu.goToCreateApplicationSection();
        browser.headerMenu.goToInstructionsAndFormsForTeacherSection();
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.headerMenu.goToContactsSection();
        browser.headerMenu.goToHomePage();
        // asserter.checkPageUrl("");
    }
// 4. Napište test, který přejde do sekce Pro učitele -> Objednávka pro MŠ/ZŠ a do
// políčka pro IČO vyplní IČO organizace Czechitas.
    @Test
    void teacherApplicationICOInsertTest() {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO("22834958");
        // asserter.checkPageUrl("");
    }

// 5. Vytvořte si v aplikaci vlastního uživatele, pomocí kterého se budete následně
// přihlašovat do aplikace. POZOR uživatele vytvořte ručně. Proč nemůžeme uživatele
// vytvářet pomocí testu? Zamyslete se nad tím, jak by se dali uživatelé vytvářet
// v testech?
    @Test
    void newUserLoginTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(userEmail);
        browser.loginSection.insertPassword(userPassword);
        browser.loginSection.clickLoginButton();
        // asserter.checkPageUrl("");
    }

// 6. Přihlaste se do aplikace pod Vámi nově vytvořeným uživatelem. Klikněte na
// vytvoření nové přihlášky. Vyberte období programování a nakonec klikněte na
// vytvoření přihlášky. Výsledkem navigace by měla být obrazovka s formulářem pro
// založení přihlášky.
    @Test
    void userApplicationCreationTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(userEmail);
        browser.loginSection.insertPassword(userPassword);
        browser.loginSection.clickLoginButton();
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreatePythonApplicationButton();
        // asserter.checkPageUrl("");
    }

// ASERTACE
// 1. Ověřte, že na úvodní stránce se nachází „dlaždice“ Programování.
    @Test
    void checkHomePageProgrammingTest() {
        asserter.checkProgrammingSectionPresense();
    }

// 2. Ověřte, že pokud jde uživatel do sekce pro vytváření přihlášky pro rodiče (Pro rodiče
// -> Vytvořit přihlášku) a není přihlášen, je na obrazovce k dispozici tlačítko pro
// registraci.
    @Test
    void logInErrorParentApplicationSectionTest() {
        browser.headerMenu.goToCreateApplicationSection();
        asserter.checkRegistrationButtonPresense();
    }

// 3. V aplikaci se přihlašte pod Vámi založeným uživatelem a ručně si vytvořte libovolnou
// přihlášku.
    @Test
    void createNewApplicationTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(userEmail);
        browser.loginSection.insertPassword(userPassword);
        browser.loginSection.clickLoginButton();
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreatePythonApplicationButton();
        browser.applicationDetailsSection.selectTerm("02.02. - 06.02.2026");
        browser.applicationDetailsSection.insertStudentFirstName("Meda");
        browser.applicationDetailsSection.insertStudentLastName("Procházková");
        browser.applicationDetailsSection.insertBirthdate("29.10.2015");
        browser.applicationDetailsSection.selectBankTransferPaymentMethod();
        browser.applicationDetailsSection.insertNote("Už se moc těší!");
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();
    }

// 4. Napište test, který provede přihlášení Vámi založeným uživatelem, otevře detail
// první přihlášky v seznamu přihlášek a zkontroluje, že způsob úhrady odpovídá
// způsobu úhrady, který jste zadali při jejím zakládání.
    @Test
    void logedInUserApplicationPaymentCheckTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(userEmail);
        browser.loginSection.insertPassword(userPassword);
        browser.loginSection.clickLoginButton();
        browser.applicationSection.openFirstApplicationDetailsPage();
        asserter.applicationDetailAction.checkPaymentMethod("Bankovní převod");
    }

// LOW-CODE AUTOMATION PRACTICE II.
// 1. Napište test, který přejde do sekce Pro učitelé -> Objednávka pro MŠ/ZŠ, vyplní do pole IČO
// libovolné existující IČO a v dolní části formuláře se přepne na záložku Škola v přírodě, kde do
// pole Počet dětí vyplní libovolný počet dětí.
    @Test
    void TeacherApplicationCreateTest() {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO("22834958");
        browser.orderSection.selectSchoolInNatureOption();
        browser.orderSection.insertChildrenCount(23);
    }

// 2. Napište test, který se přihlásí do aplikace, přejde pomocí horního menu do sekce Přihlášky a
// ověří, že v tabulce přihlášek jsou sloupce pojmenované Jméno a Kategorie.
    @Test
    void checklApplicationColumnNameTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(userEmail);
        browser.loginSection.insertPassword(userPassword);
        browser.loginSection.clickLoginButton();
        asserter.applicationSection.checkColumnExists("Jméno");
        asserter.applicationSection.checkColumnExists("Kategorie");
    }

// 3. Napište test, který bude plnit následující posloupnost úkonů:
 //a. Přihlaste se do aplikace.
 //b. Přejděte pomocí horního menu do sekce Přihlášky.
 //c. Klikněte zde na tlačítko Vytvořit novou přihlášku.
 //d. Vyberte období akce programování.
 //e. Klikněte na tlačítko Vytvořit přihlášku pro akcí JavaScript a NodeJS.
 //f. Vyplňte pole ve formuláři (Termín, Křestní jméno, Příjmení, Datum narození,
 //Poznámka) pro vytvoření přihlášky (Jako způsob platby zvolte možnost hotově) a na
 //závěr přihlášku vytvořte.
 //g. Ověřte, že nově vytvořená přihláška má Vámi nastavené hodnoty (Termín, Křestní
 //jméno, Příjmení, Datum narození, Poznámka) správně uložené.
    @Test
    void createNewApplicationTest2() {
        //Login
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(userEmail);
        browser.loginSection.insertPassword(userPassword);
        browser.loginSection.clickLoginButton();
        //Application creation
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection(); //JavaScript a NodeJS
        browser.applicationSection.clickCreatePythonApplicationButton();
        browser.applicationDetailsSection.selectTerm("02.02. - 06.02.2026");
        browser.applicationDetailsSection.insertStudentFirstName("Josefina");
        browser.applicationDetailsSection.insertStudentLastName("Procházková");
        browser.applicationDetailsSection.insertBirthdate("28.10.2010");
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.insertNote("Už se moc těší!");
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();
        //Check created application
        asserter.applicationDetailAction.checkTerm("02.02. - 06.02.2026");
        asserter.applicationDetailAction.checkFirstName("Josefina");
        asserter.applicationDetailAction.checkLastName("Procházková");
        asserter.applicationDetailAction.checkDateOfBirth("28.10.2010");
        asserter.applicationDetailAction.checkNote("Už se moc těší!");
}

// 4. Vytvořte ručně přihlášku, kde jako příjmení žáka uveďte Tester001. (Toto jméno by v rámci
// testovacích dat mělo být unikátní).
    //DONE

// 5. Napište test, který bude plnit následující posloupnost úkonů:
 //a. Přihlaste se do aplikace.
 //b. Přejděte pomocí horního menu do sekce Přihlášky.
 //c. Do vyhledávacího pole nad tabulkou přihlášek zadejte příjmení žáka z
 //předcházejícího úkolu.
 //d. Pro vyhledanou přihlášku zvolte možnost upravit a nastavte způsob platby na
 //Bankovní převod.
 //e. Uložte otevřenou přihlášku.
 //f. Opět pomocí vyhledávacího pole nad tabulkou vyhledejte příjmení žáka z
 //předchozího úkolu.
 //g. Pro vyhledanou přihlášku otevřete její detail.
 //h. Ověřte, že způsob platby je nastaven na Bankovní převod.
 //i. Ověřte, že zbývající částka k uhrazení je 1 800 Kč.
 //j. Ověřte, že zpráva pro příjemce obsahuje příjmení žáka z předchozího úkolu.
    @Test
    void findAndEditParentApplicationTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(userEmail);
        browser.loginSection.insertPassword(userPassword);
        browser.loginSection.clickLoginButton();
        browser.applicationSection.search("Tester001");
        browser.applicationSection.clickEditFirstApplicationButton();
        browser.applicationDetailsSection.selectBankTransferPaymentMethod();
        browser.applicationDetailsSection.clickEditApplicationButton();
        browser.applicationSection.search("Tester001");
        browser.applicationSection.openFirstApplicationDetailsPage();
        asserter.applicationDetailAction.checkPaymentMethod("Bankovní převod");
        asserter.applicationDetailAction.checkRemainingAmountToPay("1 800 Kč");
        asserter.applicationDetailAction.checkMessageContainsStudentLastName("Tester001");
    }

// 6. Vytvořte ručně druhého uživatele, pomocí kterého se budete moci přihlásit
    // Honza Jeřábek, honza.test@gmail.com, Honzatest1

// 7. Prodiskutujte s lektorem nebo kouči, jak se dá v Java programu uložit hodnota k
// opakovanému použití.
    // Proměnná: String / Int *název* = hodnota

//8. Přihlaste se pod jedním z Vámi založených uživatelů a vytvořte přihlášku pro žáka s náhodně
//vygenerovaným příjmením (lze použít metodu prohlizec.vygenerujNahodnePrijmeni()).
//Ověřte, že v tabulce přihlášek nově založená přihláška skutečně existuje. Následně se
//odhlaste a přihlaste pod druhým uživatelem. Nyní ověřte, že se přihláška prvního uživatele
//nezobrazuje mezi přihláškami uživatele druhého.
    @Test
    void checkApplicationOfAnotherUserNotAvailableTest() {
 //       browser.loginSection.clickLoginMenuLink();
 //       browser.loginSection.insertEmail(userEmail);
 //       browser.loginSection.insertPassword(userPassword);
 //       browser.loginSection.clickLoginButton();

        browser.loginSection.login(userEmail, userPassword);
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreatePythonApplicationButton();
        browser.applicationDetailsSection.selectTerm("02.02. - 06.02.2026");
        browser.applicationDetailsSection.insertStudentFirstName("Max");
        var generated = browser.generateRandomName(9);
        browser.applicationDetailsSection.insertStudentLastName(generated);
        browser.applicationDetailsSection.insertBirthdate("1.2.2005");
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.insertNote("Už se moc těší!");
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();
        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.search(generated);
        browser.loginSection.logout();
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(otherEmail);
        browser.loginSection.insertPassword(otherPassword);
        browser.loginSection.clickLoginButton();
        browser.applicationSection.search(generated);
        asserter.applicationSection.checkApplicationsTableIsEmpty();
    }

//9. Napište test, který ověří, že v aplikaci funguje funkcionalita změny hesla.
    @Test
    void changePasswordFuncionalityWorksTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("honza.test@gmail.com");
        browser.loginSection.insertPassword("Honzatest2");
        browser.loginSection.clickLoginButton();
        browser.profileSection.goToProfilePage();
        browser.profileSection.insertPassword("Honzatest1");
        browser.profileSection.insertPasswordVerification("Honzatest1");
        browser.profileSection.clickChangeButton();
        browser.headerMenu.goToHomePage();
        browser.loginSection.logout();
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("honza.test@gmail.com");
        browser.loginSection.insertPassword("Honzatest1");
        browser.loginSection.clickLoginButton();
    }

// 10. Napište test pro vytvoření přihlášky se špatným datem narození a zkontrolujte pomocí nové assertace.
    @Test
    void checkBirthDateErrorMessage() {
        browser.loginSection.login(userEmail, userPassword);
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreatePythonApplicationButton();
        browser.applicationDetailsSection.selectTerm("02.02. - 06.02.2026");
        browser.applicationDetailsSection.insertStudentFirstName("Johanka");
        browser.applicationDetailsSection.insertStudentLastName("Procházková");
        browser.applicationDetailsSection.insertBirthdate("9999999");
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.insertNote("Už se moc těší!");
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();
        asserter.applicationSection.checkBirthDateVerificationError();
    }
}