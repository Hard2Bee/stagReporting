package pro1.reports.report4.reportDataModel;

public class YearDuration {
    public String year;
    public int averageDuration;   // ← musí se jmenovat přesně "averageDuration"

    public YearDuration(String year, int averageDuration) {
        this.year = year;
        this.averageDuration = averageDuration;
    }
}