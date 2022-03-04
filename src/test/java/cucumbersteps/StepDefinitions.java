package cucumbersteps;

import com.codeborne.selenide.Selenide;
import elements.Currency;
import elements.Importance;
import elements.TimePeriod;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.CalendarPage;
import pages.EventPage;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    private static final Logger log = LogManager.getLogger(StepDefinitions.class);

    @Given("calendar is opened")
    public void openCalendar() {
        log.info("set googlebot as user agent");
        System.setProperty("chromeoptions.args", "--user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)");
        log.info("open calendar");
        Selenide.open("https://www.mql5.com/en/economic-calendar");
    }

    @When("filter by currency {string}")
    public void filterByCurrency(String currency) {
        log.info("filter calendar by currency " + currency);
        new CalendarPage().changeStateExcept(Currency.valueOf(currency));
    }

    @When("filter by importance {string}")
    public void filterByImportance(String importance) {
        log.info("filter calendar by importance " + importance);
        new CalendarPage().changeStateExcept(Importance.valueOf(importance));
    }

    @When("select period {string}")
    public void selectPeriod(String period) {
        log.info("select calendar period " + period);

        String formattedPeriod = String.join("_", period.split(" "));
        new CalendarPage().selectTimePeriod(TimePeriod.valueOf(formattedPeriod));
    }

    @When("open first event with currency {string}")
    public void openFirstEvent(String currency) {
        log.info("open first even with currency " + currency);

        new CalendarPage().selectFirstEventWithCurrency(Currency.valueOf(currency));
    }

    @Then("importance {string} is displayed")
    public void isImportanceDisplayed(String importance) {
        assertThat(new EventPage().getImportance()).isEqualTo(Importance.valueOf(importance));
    }

    @Then("country {string} is displayed")
    public void isCountryDisplayed(String country) {
        assertThat(new EventPage().getCountry()).isEqualTo(country);
    }

    @Then("log history table")
    public void logHistoryTable() {
        new EventPage().openHistoryTab();
    }
}
