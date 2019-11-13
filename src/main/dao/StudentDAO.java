package dao;

import model.Student;

import java.sql.*;

/**
 * @author leslie
 */
public class StudentDAO {

    public StudentDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/prize_management?characterEncoding=UTF-8",
                "root", "");
    }



    public boolean validatePassword(Student student) {

        String sql = "SELECT * FROM student WHERE number = ? AND password = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            System.out.println(student.toString());
            ps.setString(1, student.number);
            ps.setString(2, student.password);

            ResultSet rs = ps.executeQuery();

            System.out.println(ps);
            if (rs.next()) {
                //正确
                return true;
            } else {
                //错误
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editPassword(Student student) {
        String sql = "UPDATE student SET password = ? WHERE number = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, student.password);
            ps.setString(2, student.number);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Student getInfo(String number)
    {
        Student student = null;
        String sql = "SELECT * FROM student WHERE number = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, number);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.id = rs.getInt("id");
                student.school = rs.getString("school");
                student.major = rs.getString("major");
                student.grade = rs.getString("grade");
                student.number = number;
                student.name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }



}
