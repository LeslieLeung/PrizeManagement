package servlet.Prize;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.PrizeDAO;

@WebServlet(name = "PrizeDeleteServlet")
public class PrizeDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String number = req.getParameter("number");

        new PrizeDAO().deletePrize(id);

        resp.sendRedirect(String.format("/PrizeManagement_war_exploded/listSinglePersonPrize?number=%s", number));
    }
}
