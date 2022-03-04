package elements;

public enum TimePeriod {
    CURRENT_WEEK("filterDate1"),
    PREVIOUS_WEEK("filterDate2"),
    NEXT_WEEK("filterDate3"),
    CURRENT_MONTH("filterDate4"),
    PREVIOUS_MONTH("filterDate5"),
    NEXT_MONTH("filterDate6");

    private String labelFor;

    TimePeriod(String labelFor) {
        this.labelFor = labelFor;
    }

    public String getLabelFor() {
        return labelFor;
    }
}
