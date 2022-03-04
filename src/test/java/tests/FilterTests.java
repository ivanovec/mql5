package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import elements.Currency;
import elements.Importance;
import elements.TimePeriod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CalendarPage;
import pages.EventPage;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Calendar filter tests")
public class FilterTests {

    @BeforeAll
    public static void setUserAgent() {
        System.setProperty("chromeoptions.args", "--user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)");
    }

    @BeforeEach
    public void openCalendar() {
        Selenide.open("https://www.mql5.com/en/economic-calendar");
    }

    @Test
    @DisplayName("Filter by time period, importance and currency")
    public void testEventFiltering() {
        EventPage eventPage =
                new CalendarPage()
                        .selectTimePeriod(TimePeriod.CURRENT_MONTH)
                        .changeStateExcept(Importance.MEDIUM)
                        .changeStateExcept(Currency.CHF)
                        .selectFirstEventWithCurrency(Currency.CHF);

        assertThat(eventPage.getImportance()).isEqualTo(Importance.MEDIUM);
        assertThat(eventPage.getCountry()).isEqualTo(Currency.CHF.getCountry());

        eventPage.openHistoryTab();
    }
}
