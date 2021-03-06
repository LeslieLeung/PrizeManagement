package dao;

import model.Admin;
import model.Student;

import java.sql.*;

/**
 * @author leslie
 */
public class AdminDAO {

    public AdminDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
             e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/prize_management?characterEncoding=UTF-8",
                "root","");
    }

    public boolean validatePassword(Admin admin) {

        String sql = "SELECT * FROM admin WHERE number = ? AND password = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            System.out.println(admin.toString());
            ps.setString(1, admin.number);
            ps.setString(2, admin.password);

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

    public Admin getInfo(String number) {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE number = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, number);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.id = rs.getInt("id");
                admin.number = rs.getString("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }


}
