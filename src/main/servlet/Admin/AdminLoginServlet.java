package servlet.Admin;

import dao.AdminDAO;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin = new Admin();

        admin.number = req.getParameter("number");
        admin.password = req.getParameter("password");
        System.out.println(admin.toString());

        boolean rs = (new AdminDAO()).validatePassword(admin);

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<html><head><meta charset='UTF-8'></head>");

        if (!rs) {
            resp.getWriter().write("<script>alert('用户名或密码错误');window.location.href='adminLogin.html';</script>");
        } else {
            resp.getWriter().write(String.format("<script>alert('登陆成功');window.location.href='/PrizeManagement_war_exploded/adminInfo?number=%s';</script>", admin.number));
        }
    }
}
