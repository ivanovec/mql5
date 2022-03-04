package pages;

import com.codeborne.selenide.SelenideElement;
import elements.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Step;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

public class CalendarPage {

    private static final Logger log = LogManager.getLogger(CalendarPage.class);
    private static final String LABEL_ATTR = "for";

    private final EconomicTable economicTable = new EconomicTable();
    private final SelenideElement importanceFilter = $("#economicCalendarFilterImportance");
    private final SelenideElement currencyFilter = $("#economicCalendarFilterCurrency");
    private final SelenideElement dateFilter = $("#economicCalendarFilterDate");

    @Step("Select first event with currency {0}")
    public EventPage selectFirstEventWithCurrency(Currency currency) {
        log.info("select currency " + currency);
        economicTable.clickFirstRowWithCurrency(currency);
        return new EventPage();
    }

    @Step("Change all importance checkboxes except {0}")
    public CalendarPage changeStateExcept(Importance... values){
        log.info("change state for all importancies except " + values.toString());
        changeStateExcept(importanceFilter, values);
        return this;
    }

    @Step("Change all curency checkboxes except {0}")
    public CalendarPage changeStateExcept(Currency... values){
        log.info("change state for all currencies except " + values.toString());
        changeStateExcept(currencyFilter, values);
        return this;
    }

    @Step("Select time period {0}")
    public CalendarPage selectTimePeriod(TimePeriod period) {
        log.info("select time period " + period.toString());

        dateFilter.$("label[for='" + period.getLabelFor() + "']").click();
        return this;
    }

    //The 'checked' attribute is not changed when you change a checkbox state. So.. we expect default value for checkboxes
    // is enabled and tester should know the current state on the tests level
    public void changeStateExcept(SelenideElement filter, AbstractCheckboxGroup... values){
        //select all checkboxes in the group
        List<SelenideElement> checkboxes = filter.$$("li input[checked='checked'] ~ label");

        for(SelenideElement checkbox : checkboxes) {
            //transform checkbox list to list of checkbox labels
            List<String> labels = Arrays.stream(values).map(AbstractCheckboxGroup::getLabelFor).collect(Collectors.toList());

            //click on label only if array from arguments does not contain this label. As result, only checkboxes from arguments
            //will be checked (or unchecked, depends on initial state)
            if( !labels.contains(checkbox.attr(LABEL_ATTR)) ) {
                log.info("change state for " + checkbox.attr(LABEL_ATTR));
                checkbox.scrollTo().click();
                economicTable.waitTableReloading();
            }
        }
    }
}
