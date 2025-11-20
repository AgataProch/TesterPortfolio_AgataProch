package cz.czechitas.automation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

/**
 * Login/logout specific selenium actions
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@ParametersAreNonnullByDefault
final class LoginAction {

    private final ElementFinder elementFinder;

    LoginAction(ElementFinder elementFinder)
    {
        this.elementFinder = Objects.requireNonNull(elementFinder);
    }

    void clickLoginMenuLink() {
        var loginButton = elementFinder.findByCssSelector(".navbar-right .nav-item");
        loginButton.click();
    }

    void insertEmail(String email) {
        Objects.requireNonNull(email);

        var emailInputBox = elementFinder.findByXPath("//*[@id='email']");
        emailInputBox.sendKeys(email);
    }

    void insertPassword(String password) {
        Objects.requireNonNull(password);

        var passwordInputBox = elementFinder.findByXPath("//*[@id='password']");
        passwordInputBox.sendKeys(password);
    }

    void clickLoginButton() {
        var loginButton = elementFinder.findByXPath("//button[@type='submit']");
        loginButton.click();
    }

    void logout() {
        var signedInUserElement = elementFinder.findByXPath("//*[@id='navbarSupportedContent']/div[2]/div/a");
        signedInUserElement.click();
        var logoutButton = elementFinder.findByXPath("//*[@id='logout-link']");
        logoutButton.click();
    }
    // Přidáváme metodu na login, abychom to nemuseli vypisovat.
    void login(String username, String password) {
        clickLoginMenuLink();
        insertEmail(username);//parametrizujeme to na náš email a heslo, abychom to nemuseli neustále vypisovat znovu,stačí tady ale obecně "username" a "password"
        // a pak v tom LowCodeAutomationTest.java nahoře můžu nadefinovat userEmail, AdminEmail a bude to fungovat.
        insertPassword(password);
        clickLoginButton();
    }
}
