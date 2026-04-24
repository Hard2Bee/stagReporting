package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.apiDataModel.Thesis;
import pro1.apiDataModel.ThesisList;
import pro1.DataSource;
import pro1.reports.report4.reportDataModel.YearDuration;

import java.time.temporal.ChronoUnit;

public class ThesisDurationReporting {

    public static YearDuration[] GetReport(DataSource dataSource, String katedra, String[] years) {
        Gson gson = new Gson();
        YearDuration[] result = new YearDuration[years.length];

        for (int i = 0; i < years.length; i++) {
            String year = years[i];
            String json = dataSource.getKvalifikacniPrace(year, katedra);
            ThesisList list = gson.fromJson(json, ThesisList.class);

            long totalDays = 0;
            int count = 0;

            for (Thesis t : list.kvalifikacniPrace) {
                if (t.datumZadani != null && t.datumOdevzdani != null
                        && t.datumZadani.isValid() && t.datumOdevzdani.isValid()) {
                    long days = ChronoUnit.DAYS.between(
                            t.datumZadani.toLocalDate(),
                            t.datumOdevzdani.toLocalDate()
                    );
                    totalDays += days;
                    count++;
                }
            }

            int avg = count > 0 ? (int) Math.round((double) totalDays / count) : 0;
            result[i] = new YearDuration(year, avg);

        }
        return result;
    }
}