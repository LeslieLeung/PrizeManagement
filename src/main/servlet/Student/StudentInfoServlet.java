package servlet.Student;

import com.google.gson.Gson;
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
@WebServlet(name = "StudentInfoServlet")
public class StudentInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student student = null;
        String number = req.getParameter("number");

        student = (new StudentDAO()).getInfo(number);

        String header = "<html><head><meta charset='utf-8'></head><body>";
        String footer = "</body></html>";
        String copyright = "<div style=\"height: 40px;line-height: 40px; position: fixed; bottom: 0;  width: 100%; text-align: center;\">\n" +
                "    CopyRight@Leslie Leung Email:lesily9@gmail.com\n" +
                "</div>";
        String prizeList = String.format("<a href='/PrizeManagement_war_exploded/listSinglePersonPrize?number=%s'>查看获奖情况</a>", number);
        String editInfo = String.format("<a href='/PrizeManagement_war_exploded/editStudentInfo?number=%s'>修改个人信息</a>", number);

        resp.setContentType("text/html; charset=UTF-8");

        resp.getWriter().write(header + String.format("    <p>学号：%s</p>\n" +
                "    <p>姓名：%s</p>\n" +
                "    <p>学院：%s</p>\n" +
                "    <p>专业：%s</p>\n" +
                "    <p>年级：%s</p>",
                student.number, student.name, student.school, student.major, student.grade)
        + prizeList + editInfo + copyright + footer);

//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(student));
    }
}
