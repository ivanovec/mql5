package elements;

public enum Currency implements AbstractCheckboxGroup {
    AUD("filterCurrency32", "Australia"),
    BRL("filterCurrency1024", "Brazil"),
    CAD("filterCurrency16", "Canada"),
    CHF("filterCurrency64", "Switzerland"),
    CNY("filterCurrency128", "China"),
    EUR("filterCurrency2", "European Union"),
    GBP("filterCurrency8", "United Kingdom"),
    HKD("filterCurrency4096", "Hong Kong"),
    INR("filterCurrency65536", "India"),
    JPY("filterCurrency4", "Japan"),
    KRW("filterCurrency2048", "South Korea"),
    MXN("filterCurrency16384", "Mexico"),
    NOK("filterCurrency131072", "Norway"),
    NZD("filterCurrency256", "New Zealand"),
    SEK("filterCurrency512", "Sweden"),
    SGD("filterCurrency8192", "Singapore"),
    USD("filterCurrency1", "United States"),
    ZAR("filterCurrency32768", "South Africa");

    Currency(String labelFor, String country) {
        this.labelFor = labelFor;
        this.country = country;
    }

    private String labelFor;
    private String country;


    @Override
    public String getLabelFor() {
        return labelFor;
    }

    public String getCountry() {
        return country;
    }
}
