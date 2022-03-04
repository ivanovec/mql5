package elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class EventHistoryTable {
    private static final Logger log = LogManager.getLogger(EventHistoryTable.class);

    private final SelenideElement table = $("#tab_content_history");
    private final List<SelenideElement> rows = table.$$(".event-table-history__item");

    @Step("Get rows for {0} monthes")
    public List<SelenideElement> getRowsForMonths(int number) {
        LocalDate tillDate = LocalDate.now().minusMonths(number);
        log.info("Get event history from " + tillDate.toString());
        List<SelenideElement> selectedRows = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        for (SelenideElement row: rows) {
            LocalDate rowDate = LocalDate.parse(row.$("[data-date]").text(), formatter);
            if( rowDate.isBefore(tillDate) ) break;

            selectedRows.add(row);
        }

        return selectedRows;
    }

    @Step("Log rows {0}")
    public void logRows(List<SelenideElement> rows) {
        String rowFormat = "%n| %-15s| %-7s| %-9s| %-9s|";
        StringBuilder logTable = new StringBuilder();
        Formatter rowFormatter = new Formatter(logTable);

        rowFormatter.format(rowFormat, "Date", "Actual", "Forecast", "Previous");
        for (SelenideElement row: rows) {
            rowFormatter.format(rowFormat,
                    cleanElementForLog(row.$("[data-date] span")),
                    cleanElementForLog(row.$(".event-table-history__actual")),
                    cleanElementForLog(row.$(".event-table-history__forecast")),
                    cleanElementForLog(row.$(".event-table-history__previous span"))
            );
        }

        log.printf(Level.INFO, logTable.toString());
    }

    public String cleanElementForLog(SelenideElement element) {
        return element.text().replaceAll("%", "%%");
    }

}
