package com.webapp.PatientMedicalRecordSystem;

public class Patients
{
	private String patientUserId;
	private String patientName;
	private String patientAddress;
	private String patientMobileNumber;
	private String emailId;
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	private String patientDateOfBirth;
	private String patientGender;
	
	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientUserid() {
		return patientUserId;
	}

	public void setPatientUserid(String patientUserid) {
		this.patientUserId = patientUserid;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientMobileNumber() {
		return patientMobileNumber;
	}

	public void setPatientMobileNumber(String patientMobileNumber) {
		this.patientMobileNumber = patientMobileNumber;
	}

	public String getPatientDateOfBirth() {
		return patientDateOfBirth;
	}

	public void setPatientDateOfBirth(String patientDateOfBirth) {
		this.patientDateOfBirth = patientDateOfBirth;
	}

}