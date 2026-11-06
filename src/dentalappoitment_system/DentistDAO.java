package dentalappoitment_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DentistDAO {

    public static List<String> getDentistNames() {

        List<String> dentists = new ArrayList<>();

        String sql = "SELECT uname FROM tbl_users "
                   + "WHERE role = 'DENTIST' "
                   + "ORDER BY uname";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {

            while (rs.next()) {
                dentists.add(rs.getString("uname"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dentists;
    }
}
