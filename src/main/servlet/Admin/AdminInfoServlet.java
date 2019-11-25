package servlet.Admin;

import dao.AdminDAO;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminInfoServlet")
public class AdminInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin = null;
        String number = req.getParameter("number");

        admin = (new AdminDAO()).getInfo(number);

        resp.setContentType("text/html; charset=UTF-8");

        String header = "<html><head><meta charset='utf-8'></head><body>";
        String footer = "</body></html>";
        String copyright = "<div style=\"height: 40px;line-height: 40px; position: fixed; bottom: 0;  width: 100%; text-align: center;\">\n" +
                "    CopyRight@Leslie Leung Email:lesily9@gmail.com\n" +
                "</div>";

        String welcome = String.format("<h1>欢迎您，%s老师</h1>", number);
        String prizeList = "<a href='/PrizeManagement_war_exploded/listAllPrize'>查看奖项</a>";

        resp.getWriter().write(header + welcome + prizeList +copyright+ footer);

    }
}
