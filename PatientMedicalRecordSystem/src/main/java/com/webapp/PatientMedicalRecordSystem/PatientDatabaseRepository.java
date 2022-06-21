package com.webapp.PatientMedicalRecordSystem;

import java.sql.*;
import java.util.List;
import io.lsn.logger.factory.LoggerFactory;
import java.util.ArrayList;

public class PatientDatabaseRepository
{
	private static final io.lsn.logger.factory.logger.Logger logger = LoggerFactory.getLogger(AuthenticationDatabaseRepository.class);
	Connection con = null;
	DatabaseConnection connection = new DatabaseConnection();

// DB connection
	public PatientDatabaseRepository()
	{
		con = connection.doConnection();
	}

// This method fetch patient Information from database and return to patient
	
	public List<Patients> getPatientInformation(String patientId)
	{
		String sql = "select*from patientInfo where patientId='" + patientId + "'";
		List<Patients> patientInfoList = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Patients patient = new Patients();
				patient.setPatientUserid(result.getString("patientId"));
				patient.setPatientName(result.getString("patientName"));
				patient.setPatientAddress(result.getString("patientAddress"));
				patient.setPatientMobileNumber(result.getString("patientMobile"));
				patient.setEmailId(result.getString("emailId"));
				patient.setPatientDateOfBirth(result.getString("patientDob"));
				patient.setPatientGender(result.getString("gender"));
				patientInfoList.add(patient);
			}
		} catch (SQLException e) {
			logger.error("patient id not valid" + e.getMessage());
		}
		return patientInfoList;
	}

//Fetch all patient details
	
	public List<Patients> getAllPatientInfo()
	{
		String sql = "select*from patientInfo";
		List<Patients> patientInfoList = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Patients patient = new Patients();
				patient.setPatientUserid(result.getString("patientId"));
				patient.setPatientName(result.getString("patientName"));
				patient.setPatientAddress(result.getString("patientAddress"));
				patient.setPatientMobileNumber(result.getString("patientMobile"));
				patient.setEmailId(result.getString("emailId"));
				patient.setPatientDateOfBirth(result.getString("patientDob"));
				patient.setPatientGender(result.getString("gender"));
				patientInfoList.add(patient);
			}
		} catch (SQLException e) {
			logger.error("patient id not valid" + e.getMessage());
		}
		return patientInfoList;
	}
	
//Insert patientInfo into database
	
	public int setPatientInfo(Patients patient)
	{
		int response = 0;
		String patientId = patient.getPatientUserid();
		String patientName = patient.getPatientName();
		String patientAddress = patient.getPatientAddress();
		String patientMobile = patient.getPatientMobileNumber();
		String emailId = patient.getEmailId();
		String patientDob = patient.getPatientDateOfBirth();
		String patientGender = patient.getPatientGender();

		String sql = "insert into patientinfo(patientId,patientName,patientAddress,patientMobile,patientDob,gender,emailId) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, patientId);
			statement.setString(2, patientName);
			statement.setString(3, patientAddress);
			statement.setString(4, patientMobile);
			statement.setString(5, patientDob);
			statement.setString(6, patientGender);
			statement.setString(7, emailId);
			int rs = statement.executeUpdate();
			if (rs >= 1) {
				response = 1;
			}
		} catch (SQLException e) {
			logger.error("please enter valid information" + e.getMessage());
		}
		return response;
	}
	
//To update patient information in database
	
	public int updatePatientInfo(Patients patient)
	{
		int response = 0;
		String sql = "update patientinfo set patientName=?,patientAddress=?,patientMobile=?,emailId=? where patientId='"
				+ patient.getPatientUserid() + "'";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, patient.getPatientName());
			statement.setString(2, patient.getPatientAddress());
			statement.setString(3, patient.getPatientMobileNumber());
			statement.setString(4, patient.getEmailId());

			int rs = statement.executeUpdate();
			if (rs == 1) {
				response = 1;
			}
		} catch (Exception e) {
			logger.error("please enter valid information" + e.getMessage());
		}
		return response;
	}

//This method delete all patient data from database
	
	public int deletePatientInfo(String patientId)
	{
		int response = 0;
		String deleteMedicine = "delete from medicine where patient_Id=?";
		String deleteAppointment = "delete from appointment where patientId=?";
		String deletePatientInfo = "delete from patientinfo where patientId=?";
		String userCredentials = "delete from userauthentication where userId=?";
		try {
			// delete medicine details
			PreparedStatement statementF = con.prepareStatement(deleteMedicine);
			statementF.setString(1, patientId);
			statementF.executeUpdate();

			// delete appointment details
			PreparedStatement statementS = con.prepareStatement(deleteAppointment);
			statementS.setString(1, patientId);
			statementS.executeUpdate();

			// delete user information
			PreparedStatement statementT = con.prepareStatement(deletePatientInfo);
			statementT.setString(1, patientId);
			statementT.executeUpdate();

			// delete user credentials
			PreparedStatement statementFt = con.prepareStatement(userCredentials);
			statementFt.setString(1, patientId);
			int rsFt = statementFt.executeUpdate();

			if (rsFt == 1) {
				response = 1;
			}
		} catch (Exception e) {
			logger.error("please enter valid information" + e.getMessage());
		}
		return response;
	}
	
// This method insert appointment Details in Database
	
	public int bookAppointment(Appointment patientAppointment)
	{
		String appointmentDate = patientAppointment.getAppointmentDate();
		String appointmentTime = patientAppointment.getAppointmentTime();
		String patientId = patientAppointment.getPatientId();
		String doctorId = patientAppointment.getDoctorId();
		String consultingReason = patientAppointment.getconsultingReason();
		int response = 0;

		String sql = "insert into appointment(appointmentDate,appointmentTime,patientId,doctorId,consultingReason) values(?,?,?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, appointmentDate);
			statement.setString(2, appointmentTime);
			statement.setString(3, patientId);
			statement.setString(4, doctorId);
			statement.setString(5, consultingReason);
			int rs = statement.executeUpdate();
			if (rs == 1) {
				response = 1;
			}
		} catch (SQLException e) {
			logger.error("please enter valid information" + e.getMessage());
		}

		return response;
	}
	
//To fetch appointment details by patientId
	
	public List<Appointment> getAppointmentInformation(String patientId)
	{
		String sql = "select appointmentId,appointmentDate,appointmentTime,consultingReason,patientName,doctorName from appointment a ,patientinfo p,doctor d where a.patientId=p.patientId and a.doctorId=d.doctorId and p.patientId='"
				+ patientId + "'";
		List<Appointment> appointmentList = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Appointment appointment = new Appointment();
				appointment.setAppontmentId(result.getString("appointmentId"));
				appointment.setAppointmentDate(result.getString("appointmentDate"));
				appointment.setAppointmentTime(result.getString("appointmentTime"));
				appointment.setconsultingReason(result.getString("consultingReason"));
				appointment.setDoctorName(result.getString("doctorName"));
				appointment.setPatientName(result.getString("patientName"));
				appointmentList.add(appointment);
			}
		} catch (SQLException e) {
			logger.error("patient id not valid" + e.getMessage());
		}
		return appointmentList;
	}		
		
//This method fetch medicine details from database
	
	public List<Medication> getMedicationDetails(String appointmentId)
	{
		String sql = "select*from medicine where appointment_id='" + appointmentId + "'";
		List<Medication> medicationList = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Medication medication = new Medication();
				medication.setAppointmentId(result.getInt("appointment_id"));
				medication.setMedicineName(result.getString("medicine_name"));
				medication.setTimesADay(result.getInt("medicine_times_a_day"));
				medication.setMedicineDuration(result.getInt("medicine_duration"));
				medication.setStatus(result.getString("status"));
				medication.setStartingDate(result.getString("startDate"));
				medication.setEndDate(result.getString("endDate"));
				medicationList.add(medication);
			}
		} catch (SQLException e) {
			logger.error("appointment id not valid " + e.getMessage());
		}
		return medicationList;
	}					
				
//This method insert medicine details in DB
	
	 public int setMedicationDetails(List<Medication> medicalDetails)
		{
			int response = 0;
			try {
				for (Medication record : medicalDetails) {
					int appointmentId = record.getAppointmentId();
					String medicineName = record.getMedicineName();
					int medicineTimesADay = record.getTimesADay();
					int medicineDuration = record.getMedicineDuration();
					String status = record.getStatus();
					String startDate = record.getStartingDate();
					String endDate = record.getEndDate();
					String patientId = record.getPatientId();

					String sql = "insert into medicine values(?,?,?,?,?,?,?,?)";

					PreparedStatement statement = con.prepareStatement(sql);
					statement.setInt(1, appointmentId);
					statement.setString(2, medicineName);
					statement.setInt(3, medicineTimesADay);
					statement.setInt(4, medicineDuration);
					statement.setString(5, status);
					statement.setString(6, startDate);
					statement.setString(7, endDate);
					statement.setString(8, patientId);

					int rs = statement.executeUpdate();
					if (rs >= 1) {
						response = 1;
					} else {
						response = 0;
					}
				}
			} catch (SQLException e) {
				logger.error("please enter a valid details " + e.getMessage());
			}

			return response;
		}
		   
//To close Database connection
	@Override
	protected void finalize()
	{ 
		connection.closeConnection();
	}

}
