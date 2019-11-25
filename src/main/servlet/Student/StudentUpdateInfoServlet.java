package servlet.Student;

import dao.StudentDAO;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author leslie
 */
@WebServlet(name = "StudentUpdateInfoServlet")
public class StudentUpdateInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Student student = new Student();
        student.number = req.getParameter("number");
        student.name = req.getParameter("name");
        student.school = req.getParameter("school");
        student.major = req.getParameter("major");
        student.grade = req.getParameter("grade");

        new StudentDAO().editInfo(student);

        resp.sendRedirect(String.format("/PrizeManagement_war_exploded/studentInfo?number=%s", student.number));
    }
}
