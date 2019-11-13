package servlet.Student;

import dao.StudentDAO;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author leslie
 */
@WebServlet(name = "StudentEditInfoServlet")
public class StudentEditInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student student = null;
        String number = req.getParameter("number");

        student = (new StudentDAO()).getInfo(number);

        String header = "<html><head><meta charset='utf-8'></head><body>";
        String footer = "</body></html>";

        String sb = "<!DOCTYPE html>" +
                "<div align='center'><form action='/PrizeManagement_war_exploded/updateStudentInfo' method='post'>" +
                "学号<input type='text' name='number' value='%s'><br>" +
                "    姓名<input type='text' name='name' value='%s'><br>" +
                "    学院<input type='text' name='school' value='%s'><br>" +
                "    专业<input type='text' name='major' value='%s'><br>" +
                "    年级<input type='text' name='grade' value='%s'><br>" +
                "    <input type='submit' value='确定'>\n" +
                "</form>\n" +
                "</div>";
        String html = String.format(sb, student.number, student.name,
                student.school, student.major, student.grade);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(html);
    }
}
