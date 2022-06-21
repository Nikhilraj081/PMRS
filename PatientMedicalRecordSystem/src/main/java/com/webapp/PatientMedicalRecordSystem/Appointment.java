package com.webapp.PatientMedicalRecordSystem;

public class Appointment
{
	private String appontmentId;
	private String appointmentDate;
	private String appointmentTime;
	private String patientId;
	private String doctorId;
	private String consultingReason;
	private String patientName;
	private String doctorName;
	

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getconsultingReason() {
		return consultingReason;
	}

	public void setconsultingReason(String consultingReason) {
		this.consultingReason = consultingReason;
	}

	public String getAppontmentId() {
		return appontmentId;
	}
	
	public String setAppontmentId(String appointmentId) {
		return appontmentId=appointmentId;
	}


	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
}

