package com.webapp.PatientMedicalRecordSystem;

public class Medication {

	private String medicineName;
	private int timesADay;
	private int medicineDuration;
	private String status;
	private String startingDate;
	private String endDate;
	private int appointmentId;
	private String patientId;
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public int getTimesADay() {
		return timesADay;
	}

	public void setTimesADay(int timesADay) {
		this.timesADay = timesADay;
	}

	public int getMedicineDuration() {
		return medicineDuration;
	}

	public void setMedicineDuration(int medicineDuration) {
		this.medicineDuration = medicineDuration;
	}

}
