package servlet.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import model.Response;
import model.Student;
import dao.StudentDAO;
import model.Token;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StudentLoginServlet")
public class StudentLoginServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
//        Gson gson = new Gson();
        Student student = new Student();
//
//        BufferedReader reader = request.getReader();
//        String json = "";
//        String line;
//        while ((line = reader.readLine()) != null) {
//            json = json.concat(line);
//        }
//        System.out.println(json);
//        reader.close();

//        student = gson.fromJson(json, student.getClass());

        student.number = request.getParameter("number");
        student.password = request.getParameter("password");
        System.out.println(student.toString());

        boolean rs = (new StudentDAO()).validatePassword(student);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("<html><head><meta charset='UTF-8'></head>");

        if (!rs) {
            response.getWriter().write("<script>alert('用户名或密码错误');window.location.href='studentLogin.html';</script>");
//            response.getWriter().write(gson.toJson((new Response(400, "用户名或密码错误", null))));
        } else {
            response.getWriter().write(String.format("<script>alert('登陆成功');window.location.href='/PrizeManagement_war_exploded/studentInfo?number=%s';</script>", student.number));
            //拿token
//            String token = (new Token()).getToken(student.number);
//            response.getWriter().write(gson.toJson((new Response(200, "登陆成功", token))));
        }



    }
}
