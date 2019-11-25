package dao;

import model.Prize;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrizeDAO {

    public PrizeDAO() {
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

    public List<Prize> getSinglePersonPrize(String number) {
        List<Prize> prizes = new ArrayList<Prize>();

        String sql = "SELECT * FROM prize WHERE student_number = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, number);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prize prize = new Prize();
                prize.id = rs.getInt("id");
                prize.name = rs.getString("name");
                prize.level = rs.getString("level");
                prize.teacher = rs.getString("teacher");
                prize.date = rs.getString("date");
                prize.department = rs.getString("department");
                prizes.add(prize);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prizes;
    }

    public void editPrize(Prize prize) {

        String sql = "UPDATE prize SET name = ?, level = ?, teacher = ?, date = ?, department = ? WHERE id = ?";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, prize.name);
            ps.setString(2, prize.level);
            ps.setString(3, prize.teacher);
            ps.setString(4, prize.date);
            ps.setString(5, prize.department);
            ps.setInt(6, prize.id);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Prize getPrize(int id) {
        Prize prize = null;

        String sql = "SELECT * FROM prize WHERE id = ?";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                prize = new Prize();
                prize.id = id;
                prize.name = rs.getString("name");
                prize.level = rs.getString("level");
                prize.teacher = rs.getString("teacher");
                prize.date = rs.getString("date");
                prize.department = rs.getString("department");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prize;
    }

    public void deletePrize(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {
            String sql = "DELETE FROM prize WHERE id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPrize(Prize prize) {

        String sql = "INSERT INTO prize (name, level, teacher, date, department, student_number) VALUES (?,?,?,?,?,?)";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, prize.name);
            ps.setString(2, prize.level);
            ps.setString(3, prize.teacher);
            ps.setString(4, prize.date);
            ps.setString(5, prize.department);
            ps.setInt(6, prize.student_number);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prize> getAllPrize() {
        List<Prize> prizes = new ArrayList<Prize>();


        String sql = "SELECT * FROM prize";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prize prize = new Prize();
                prize.id = rs.getInt("id");
                prize.name = rs.getString("name");
                prize.level = rs.getString("level");
                prize.teacher = rs.getString("teacher");
                prize.date = rs.getString("date");
                prize.department = rs.getString("department");
                prizes.add(prize);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prizes;
    }

}
