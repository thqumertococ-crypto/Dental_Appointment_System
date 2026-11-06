package dentalappoitment_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // =========================
    // LOGIN
    // =========================
    // Login using EMAIL and PASSWORD
    public static User login(String email, String password) {

        String sql = "SELECT id, uname, email, role "
                   + "FROM tbl_users "
                   + "WHERE email = ? AND password = ?";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("id");
                String uname = rs.getString("uname");
                String userEmail = rs.getString("email");
                String role = rs.getString("role");

                rs.close();
                pst.close();
                con.close();

                return new User(
                        id,
                        uname,
                        userEmail,
                        role
                );
            }

            rs.close();
            pst.close();
            con.close();

            return null;

        } catch (Exception e) {

            System.out.println(
                    "Login Error: " + e.getMessage()
            );

            return null;
        }
    }


    // =========================
    // SIGN UP
    // =========================
    // New accounts are automatically USER accounts.
    public static boolean signup(
            String username,
            String email,
            String password) {

        String checkSql =
                "SELECT id FROM tbl_users "
              + "WHERE email = ?";

        String insertSql =
                "INSERT INTO tbl_users "
              + "(uname, email, password, role) "
              + "VALUES (?, ?, ?, 'USER')";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            // Check if email already exists
            PreparedStatement checkPst =
                    con.prepareStatement(checkSql);

            checkPst.setString(1, email);

            ResultSet rs =
                    checkPst.executeQuery();

            if (rs.next()) {

                rs.close();
                checkPst.close();
                con.close();

                return false;
            }

            rs.close();
            checkPst.close();

            // Create USER account
            PreparedStatement insertPst =
                    con.prepareStatement(insertSql);

            insertPst.setString(1, username);
            insertPst.setString(2, email);
            insertPst.setString(3, password);

            insertPst.executeUpdate();

            insertPst.close();
            con.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Signup Error: " + e.getMessage()
            );

            return false;
        }
    }
}
