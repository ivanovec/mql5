package elements;

public enum Importance implements AbstractCheckboxGroup { // TO clarify: is labelFor static or it's better to choose another selectors
    HOLIDAYS("filterImportance1", "Holidays"),
    LOW("filterImportance2", "Low"),
    MEDIUM("filterImportance4", "Medium"),
    HIGH("filterImportance8", "High");

    Importance(String labelFor, String title) {
        this.labelFor = labelFor;
        this.title = title;
    }

    private String labelFor;
    public String title;

    @Override
    public String getLabelFor() {
        return labelFor;
    }
}