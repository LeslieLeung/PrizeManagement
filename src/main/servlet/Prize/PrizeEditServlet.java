package servlet.Prize;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import model.Prize;
import dao.PrizeDAO;

/**
 * @author leslie
 */
@WebServlet(name = "PrizeEditServlet")
public class PrizeEditServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String number = req.getParameter("number");
        String auth = req.getParameter("auth");

        Prize prize = (new PrizeDAO()).getPrize(id);

        resp.setContentType("text/html; charset=UTF-8");
        StringBuffer format = new StringBuffer();

        format.append("<!DOCTYPE html>");

        format.append("<div align='center'><form action='/PrizeManagement_war_exploded/updatePrize?auth=%s' method='post'>");
        format.append("<h1>修改奖项</h1>");
        format.append("奖项名称 ： <input type='text' name='name' value='%s' > <br>");
        format.append("奖项等级 ： <input type='text' name='level'  value='%s' > <br>");
        format.append("指导老师 ： <input type='text' name='teacher'  value='%s' > <br>");
        format.append("获奖日期 ： <input type='text' name='date' value='%s' > <br>");
        format.append("授奖部门 ： <input type='text' name='department' value='%s' > <br>");
        format.append("<input type='hidden' name='id' value='%d'>");
        format.append("<input type='hidden' name='number' value='%s'>");
        format.append("<input type='submit' value='更新'>");
        format.append("</form></div>");

        String html = String.format(format.toString(), auth, prize.name, prize.level, prize.teacher, prize.date,
                prize.department, prize.id, number);

        resp.getWriter().write(html);
    }
}
