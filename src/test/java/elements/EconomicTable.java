package elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Instant;
import static com.codeborne.selenide.Selenide.$;

public class EconomicTable {
    private static final int WAIT_TIMEOUT_SECONDS = 1;
    private static final int POLLING_SLEEP_MILLIS = 10;

    private SelenideElement table = $(".ec-table");
    private String previousTableState = table.innerHtml();

    @Step("Click on the first row with currency {0}")
    public void clickFirstRowWithCurrency(Currency currency) {
        table.$x(".//div[contains(@class, 'ec-table__item') and .//div[text()='" + currency.name() + "']]//a").click();
    }

    @Step("Wait table reloading")
    public void waitTableReloading() {
        long endTime = Instant.now().plusSeconds(WAIT_TIMEOUT_SECONDS).toEpochMilli();

        while ( table.innerHtml().equals(previousTableState) && Instant.now().toEpochMilli() < endTime ) {
            try {
                Thread.sleep(POLLING_SLEEP_MILLIS);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        previousTableState = table.innerHtml();
    }
}
