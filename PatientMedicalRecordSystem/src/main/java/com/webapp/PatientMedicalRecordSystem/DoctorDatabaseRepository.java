package com.webapp.PatientMedicalRecordSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import io.lsn.logger.factory.LoggerFactory;

public class DoctorDatabaseRepository   
{
	private static final io.lsn.logger.factory.logger.Logger logger = LoggerFactory.getLogger(AuthenticationDatabaseRepository.class);
	Connection con = null;
	
	DatabaseConnection connection = new DatabaseConnection();

// DB connection
	
	public DoctorDatabaseRepository()
	{
		con = connection.doConnection();
	}
	
// This method fetch doctor Information from database and return to doctor
	
	public List<Doctor> getDoctorInformation(String doctorId)
	{
		String sql = "select*from doctor where doctorId='" + doctorId + "'";
		List<Doctor> doctorInfoList = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Doctor doctor = new Doctor();
				doctor.setDoctorId(result.getString("doctorId"));
				doctor.setDoctorName(result.getString("doctorName"));
				doctor.setDoctorType(result.getString("doctorType"));
				doctor.setDoctorAddress(result.getString("doctorAddress"));
				doctor.setDoctorMobile(result.getString("doctorMobile"));
				doctorInfoList.add(doctor);
			}
		} catch (SQLException e) {
			logger.error("doctor id id not valid" + e.getMessage());
		}
		return doctorInfoList;
	}
	
//Insert DoctorInfo into database
	
	public int setdoctorInfo(Doctor doctor)
	{
		int response = 0;
		String doctorId = doctor.getDoctorId();
		String doctorName = doctor.getDoctorName();
		String doctorAddress = doctor.getDoctorAddress();
		String doctorMobile = doctor.getDoctorMobile();
		String doctorType = doctor.getDoctorType();
		String doctorGender = doctor.getDoctorGender();

		String sql = "insert into doctor(doctorId,doctorName,doctorType,doctorAddress,doctorMobile,gender) values(?,?,?,?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, doctorId);
			statement.setString(2, doctorName);
			statement.setString(3, doctorType);
			statement.setString(4, doctorAddress);
			statement.setString(5, doctorMobile);
			statement.setString(6, doctorGender);
			int rs = statement.executeUpdate();
			if (rs >= 1) {
				response = 1;
			}
		} catch (SQLException e) {
			logger.error("please enter valid information" + e.getMessage());
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