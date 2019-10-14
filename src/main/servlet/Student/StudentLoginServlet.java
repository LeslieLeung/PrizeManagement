package servlet.Student;

import java.io.IOException;

import com.google.gson.Gson;
import model.Response;
import model.Student;
import dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentLoginServlet extends javax.servlet.http.HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Student student = new Student();
        student.number = request.getParameter("number");
        student.password = request.getParameter("password");
        System.out.println(student.toString());

        boolean rs = (new StudentDAO()).validatePassword(student);

        response.setCharacterEncoding("GBK");
        Gson gson = new Gson();
        if (!rs) {
//            response.getWriter().write("<script>alert('用户名或密码错误');window.location.href='login.html';</script>");
            response.getWriter().write(gson.toJson((new Response(400, "用户名或密码错误", null))));
        } else {
//            response.getWriter().write(String.format("<script>alert('登陆成功');window.location.href='/PrizeManagement_war_exploded/studentInfo?number=%s';</script>", student.number));
            response.getWriter().write(gson.toJson((new Response(200, "登陆成功", null))));
        }



    }
}
