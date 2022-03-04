package pages;

import com.codeborne.selenide.SelenideElement;
import elements.EventHistoryTable;
import elements.Importance;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EventPage {
    private final SelenideElement importance = $("[class^='event-table__importance']");
    private final SelenideElement country = $x("//div[@class='ec-event__header__item' and .//span[contains(text(), 'Country')]]//a");
    private final SelenideElement historyTab = $("[data-content='history']");

    private final EventHistoryTable historyTable = new EventHistoryTable();

    public Importance getImportance() {
        return Importance.valueOf(importance.text().toUpperCase());
    }

    public String getCountry() {
        return country.text();
    }

    @Step("Open history tab")
    public EventPage openHistoryTab() {
        historyTab.click();
        historyTable.logRows(historyTable.getRowsForMonths(12));
        return this;
    }
}
