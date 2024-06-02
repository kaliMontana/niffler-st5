package guru.qa.niffler.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PeoplePage {

    private final ElementsCollection peopleRows = $(".abstract-table tbody").$$("tr");


    public PeoplePage addFriendIsInviteSent(String userName) {
        findUserByName(userName).lastChild().
                click();
        return this;
    }

    public void checkSendedInvitation(String user) {
        findUserByName(user).$$("td").last().shouldHave(text("Pending invitation"));
    }

    public void checkFriendShip() {
        peopleRows.find(text("You are friends")).shouldBe(exist);
    }

    public void checkReceivedInvite(String user) {
        findUserByName(user).$$("td").last().$(".abstract-table__buttons div")
                .shouldHave(attribute("data-tooltip-id", "submit-invitation"));
    }

    private SelenideElement findUserByName(String userName) {
        return peopleRows.find(text(userName));
    }
}