package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement signInButton = $("button[type='submit']");


    public void enterUserName(String userName) {
        usernameInput.setValue(userName);
    }

    public void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    public void clickOnSingInButton() {
        signInButton.click();
    }

    public void singIn(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickOnSingInButton();
    }
}
