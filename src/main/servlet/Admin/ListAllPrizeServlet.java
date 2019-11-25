package servlet.Admin;

import dao.PrizeDAO;
import dao.StudentDAO;
import model.Prize;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListAllPrizeServlet")
public class ListAllPrizeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        List<Prize> prizes = new PrizeDAO().getAllPrize();

        StringBuffer sb = new StringBuffer();
        sb.append("<table align=\"center\" border=\"1\" cellspacing=\"0\">\n" +
                "    <tr>\n" +
                "        <th>id</th>\n" +
                "        <th>奖项名称</th>\n" +
                "        <th>获奖时间</th>\n" +
                "        <th>获奖等级</th>\n" +
                "        <th>指导老师</th>\n" +
                "        <th>授奖部门</th>\n" +
                "        <th>学生姓名</th>\n" +
                "        <th>学生学号</th>\n" +
                "        <th>编辑</th>\n" +
                "        <th>删除</th>\n" +
                "    </tr>");
        String trFormat = "<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td><a href='/PrizeManagement_war_exploded/editPrize?id=%d&number=%s&auth=1'>编辑</a></td><td><a href='/PrizeManagement_war_exploded/deletePrize?id=%d&number=%s&auth=1'>删除</a></td></tr>";
        for (Prize prize:prizes) {
            String number = prize.student_number;
            String studentName = (new StudentDAO().getName(number));
            String tr = String.format(trFormat, prize.id, prize.name, prize.date, prize.level, prize.teacher, prize.department, studentName, number,prize.id, number, prize.id, number);
            sb.append(tr);
        }

        sb.append("</table>");

        resp.getWriter().write(sb.toString());
    }
}