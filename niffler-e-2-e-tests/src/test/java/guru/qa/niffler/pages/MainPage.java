package guru.qa.niffler.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.model.SpendJson;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final ElementsCollection spendingRows = $(".spendings-table tbody").$$("tr");
    private final SelenideElement deleteSpendingButton = $(".spendings__bulk-actions button");
    private final SelenideElement peoplePage = $("[data-tooltip-id='people']");


    public SelenideElement findSpendingByDescription(String description) {
        return spendingRows.find(text(description));
    }

    public void chooseSpending(SpendJson spendJson) {
        findSpendingByDescription(spendJson.description())
                .$("td").scrollIntoView(true).click();
    }

    public void deleteSpending() {
        deleteSpendingButton.click();
    }

    public void checkSpendingWasDeleted(int size) {
        spendingRows.shouldHave(size(size));
    }

    public PeoplePage openPeoplePage(){
        peoplePage.click();
        return new PeoplePage();
    }
}
