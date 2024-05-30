package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import guru.qa.niffler.jupiter.annotation.User;
import guru.qa.niffler.jupiter.annotation.meta.WebTest;
import guru.qa.niffler.model.UserJson;
import guru.qa.niffler.pages.LoginPage;
import guru.qa.niffler.pages.MainPage;
import guru.qa.niffler.pages.PeoplePage;
import guru.qa.niffler.pages.WelcomePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static guru.qa.niffler.jupiter.annotation.User.Selector.*;

@WebTest
public class FriendStatusTest {

    private final WelcomePage welcomePage = new WelcomePage();
    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();
    private final PeoplePage peoplePage = new PeoplePage();

    static {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void checkIfFriendIsPresent(@User(selector = WITH_FRIEND)UserJson userForTest){
        open("http://127.0.0.1:3000/");

        welcomePage.goToLoginPage();
        loginPage.singIn(userForTest.username(), userForTest.testData().password());
        mainPage.openPeoplePage();
        peoplePage.checkFriendShip();

    }

    @Test
    void checkFriendShipInviteIsSend(@User(selector = INVITE_SENT)UserJson userForTest){
        open("http://127.0.0.1:3000/");

        welcomePage.goToLoginPage();
        loginPage.singIn(userForTest.username(), userForTest.testData().password());
        mainPage.openPeoplePage();
        peoplePage.addFriendIsInviteSent("mary");
        peoplePage.checkSendedInvitation("mary");

    }

    @Test
    void CheckFriendshipInvitationReceived(@User(selector = INVITE_RECEIVED) UserJson userForTest) {
        open("http://127.0.0.1:3000/main");
        welcomePage.goToLoginPage();
        loginPage.singIn(userForTest.username(), userForTest.testData().password());
        mainPage.openPeoplePage();
        peoplePage.checkReceivedInvite("dania");
    }

    @Test
    void testCheckFriendsAndInvitationReceived(@User(selector = WITH_FRIEND) UserJson userForTest,
                                               @User(selector = INVITE_SENT) UserJson userForTest2) {
        open("http://127.0.0.1:3000/main");
        welcomePage.goToLoginPage();
        loginPage.singIn(userForTest.username(), userForTest.testData().password());
        mainPage.openPeoplePage();
    }
}