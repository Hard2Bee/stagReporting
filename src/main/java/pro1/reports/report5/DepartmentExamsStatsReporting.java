package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.apiDataModel.Exam;
import pro1.apiDataModel.ExamList;
import pro1.DataSource;
import pro1.reports.report5.reportDataModel.ExamsStats;

import java.util.ArrayList;
import java.util.TreeSet;

public class DepartmentExamsStatsReporting {

    public static ExamsStats GetReport(DataSource dataSource, String katedra) {
        String json = dataSource.getTerminyZkousek2(katedra);
        Gson gson = new Gson();
        ExamList examsList = gson.fromJson(json, ExamList.class);

        int realizedCount = 0;
        TreeSet<Integer> teacherIdSet = new TreeSet<>();

        for (Exam exam : examsList.termin) {
            if (exam.obsazeni > 0) {
                realizedCount++;
            }
            if (exam.ucitIdno != 0) {
                teacherIdSet.add(exam.ucitIdno);
            }
        }

        return new ExamsStats(realizedCount, new ArrayList<>(teacherIdSet));
    }
}