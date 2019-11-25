package servlet.Prize;

import dao.PrizeDAO;
import model.Prize;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.PrinterAbortException;
import java.io.IOException;

@WebServlet(name = "PrizeUpdateServlet")
public class PrizeUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String number = req.getParameter("number");

        Prize prize = new Prize();
        prize.id = Integer.parseInt(req.getParameter("id"));
        prize.name = req.getParameter("name");
        prize.level = req.getParameter("level");
        prize.teacher = req.getParameter("teacher");
        prize.date = req.getParameter("date");
        prize.department =req.getParameter("department");

        new PrizeDAO().editPrize(prize);

        int auth = Integer.parseInt(req.getParameter("auth"));
        System.out.println(auth);
        if (auth == 1) {
            resp.sendRedirect("/PrizeManagement_war_exploded/listAllPrize");
        } else {
            resp.sendRedirect(String.format("/PrizeManagement_war_exploded/listSinglePersonPrize?number=%s", number));
        }
    }
}
