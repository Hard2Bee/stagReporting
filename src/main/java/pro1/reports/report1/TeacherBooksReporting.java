package pro1.reports.report1;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Book;
import pro1.apiDataModel.BooksList;
import pro1.apiDataModel.TeacherCourse;
import pro1.apiDataModel.TeacherCoursesList;
import pro1.reports.report1.reportDataModel.CourseBook;

import java.util.ArrayList;
import java.util.List;

public class TeacherBooksReporting {

    public static List<CourseBook> GetReport(DataSource dataSource, String rok,
                                             int ucitIdno, String katedra) {
        var coursesJson = dataSource.getPredmetyByUcitel(rok, ucitIdno, katedra);
        Gson gson = new Gson();
        TeacherCoursesList coursesList = gson.fromJson(coursesJson, TeacherCoursesList.class);

        var reportItems = new ArrayList<CourseBook>();

        if (coursesList == null || coursesList.items == null) return reportItems;

        for (TeacherCourse course : coursesList.items) {
            String booksJson = dataSource.getLiteraturaPredmetu(course.zkratka, katedra);
            BooksList booksList = gson.fromJson(booksJson, BooksList.class);

            if (booksList == null || booksList.items == null) continue;

            for (Book book : booksList.items) {
                reportItems.add(new CourseBook(book.title, book.author, course.zkratka));
            }
        }

        return reportItems;
    }
}