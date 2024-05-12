package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class WelcomePage {
    private final SelenideElement loginButton = $(byText("Login"));

    public void goToLoginPage(){
        loginButton.click();
    }
}
