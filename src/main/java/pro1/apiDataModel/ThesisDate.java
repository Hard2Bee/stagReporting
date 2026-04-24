package pro1.apiDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ThesisDate {
    public String value;

    public boolean isValid() {
        if (value == null || value.isEmpty()) return false;
        try {
            toLocalDate();
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate toLocalDate() {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("d.M.yyyy"));
    }
}