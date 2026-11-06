package dentalappoitment_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AppointmentDAO {

    // =========================
    // BOOK APPOINTMENT
    // =========================
    public static boolean bookAppointment(
            int patientId,
            String patient,
            String phone,
            String date,
            String time,
            String service) {

        String sql = "INSERT INTO tbl_appointments "
                   + "(patient_id, patient, phone, appointment_date, "
                   + "appointment_time, service, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, 'Pending')";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, patientId);
            pst.setString(2, patient);
            pst.setString(3, phone);
            pst.setString(4, date);
            pst.setString(5, time);
            pst.setString(6, service);

            pst.executeUpdate();

            pst.close();
            con.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Appointment Error: " + e.getMessage()
            );

            return false;
        }
    }


    // =========================
    // GET USER APPOINTMENTS
    // =========================
    public static ArrayList<Appointment> getAppointments(int patientId) {

        ArrayList<Appointment> list = new ArrayList<>();

        String sql = "SELECT * FROM tbl_appointments "
                   + "WHERE patient_id = ? "
                   + "ORDER BY appointment_date ASC";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, patientId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Appointment appointment = new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getString("patient"),
                    rs.getString("phone"),
                    rs.getString("appointment_date"),
                    rs.getString("appointment_time"),
                    rs.getString("service"),
                    rs.getString("dentist"),
                    rs.getString("status")
                );

                list.add(appointment);
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Get Appointment Error: " + e.getMessage()
            );
        }

        return list;
    }


    // =========================
    // GET ALL APPOINTMENTS
    // =========================
    public static ArrayList<Appointment> getAllAppointments() {

        ArrayList<Appointment> list = new ArrayList<>();

        String sql = "SELECT * FROM tbl_appointments "
                   + "ORDER BY appointment_date ASC";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Appointment appointment = new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getString("patient"),
                    rs.getString("phone"),
                    rs.getString("appointment_date"),
                    rs.getString("appointment_time"),
                    rs.getString("service"),
                    rs.getString("dentist"),
                    rs.getString("status")
                );

                list.add(appointment);
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Get All Appointments Error: " + e.getMessage()
            );
        }

        return list;
    }


    // =========================
    // UPDATE APPOINTMENT STATUS
    // =========================
    public static boolean updateAppointmentStatus(
            int appointmentId,
            String status) {

        String sql = "UPDATE tbl_appointments "
                   + "SET status = ? "
                   + "WHERE id = ?";

        try {

            Connection con = DatabaseConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, status);
            pst.setInt(2, appointmentId);

            pst.executeUpdate();

            pst.close();
            con.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Update Appointment Error: "
                    + e.getMessage()
            );

            return false;
        }
    }

}