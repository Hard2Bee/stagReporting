package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.DataSource;
import pro1.reports.report3.reportDataModel.WeekdayStats;
import pro1.apiDataModel.Thesis;
import pro1.apiDataModel.ThesisList;

public class DepartmentWeekdayReporting {

    public static WeekdayStats[] GetReport(DataSource dataSource, String rok,
                                           String katedra, String[] days) {
        String json = dataSource.getRozvrhByKatedra(rok, katedra);
        Gson gson = new Gson();
        ActionsList scheduleList = gson.fromJson(json, ActionsList.class);

        WeekdayStats[] result = new WeekdayStats[days.length];
        for (int i = 0; i < days.length; i++) {
            String day = days[i];
            int count = 0;
            for (var action : scheduleList.items) {
                if (day.equals(action.denZkr)) {
                    count++;
                }
            }
            result[i] = new WeekdayStats(day, count);
        }
        return result;
    }
}