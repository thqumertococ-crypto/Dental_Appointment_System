package dentalappoitment_system;

public class Appointment {

    private int id;
    private int patientId;
    private String patient;
    private String phone;
    private String appointmentDate;
    private String appointmentTime;
    private String service;
    private String dentist;
    private String status;

    public Appointment(int id, int patientId, String patient,
                       String phone,
                       String appointmentDate, String appointmentTime,
                       String service, String dentist, String status) {

        this.id = id;
        this.patientId = patientId;
        this.patient = patient;
        this.phone = phone;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.service = service;
        this.dentist = dentist;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatient() {
        return patient;
    }

    public String getPhone() {
        return phone;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getService() {
        return service;
    }

    public String getDentist() {
        return dentist;
    }

    public String getStatus() {
        return status;
    }
}