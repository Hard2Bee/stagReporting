package pro1.reports.report5.reportDataModel;

import java.util.List;

public class ExamsStats {
    public int realizedExamsCount;
    public List<Integer> teacherIds;

    public ExamsStats(int realizedExamsCount, List<Integer> teacherIds) {
        this.realizedExamsCount = realizedExamsCount;
        this.teacherIds = teacherIds;
    }
}