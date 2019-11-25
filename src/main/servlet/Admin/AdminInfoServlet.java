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
        String number = req.getParameter("number"); //get传值

        admin = (new AdminDAO()).getInfo(number);

        String header = "<html><head><meta charset='utf-8'></head><body>";
        String footer = "</body></html>";

        String welcome = "<p>欢迎您，%s老师</p>";
//        String prizeList = String.format(); //TODO 写完显示奖项页面再做这里

        resp.setContentType("text/html; charset=UTF-8");


    }
}
