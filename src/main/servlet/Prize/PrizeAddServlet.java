package servlet.Prize;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import model.Prize;
import dao.PrizeDAO;

@WebServlet(name = "PrizeAddServlet")
public class PrizeAddServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Prize prize = new Prize();
        prize.name = req.getParameter("name");
        prize.level = req.getParameter("level");
        prize.teacher = req.getParameter("teacher");
        prize.date = req.getParameter("date");
        prize.department = req.getParameter("department");
        prize.student_number = (req.getParameter("student_number"));

        new PrizeDAO().addPrize(prize);

        resp.sendRedirect(String.format("/PrizeManagement_war_exploded/listSinglePersonPrize?number=%s", prize.student_number));
    }
}
