package com.webapp.PatientMedicalRecordSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.lsn.logger.factory.LoggerFactory;
import io.lsn.logger.factory.logger.Logger;


public class AuthenticationDatabaseRepository
{  
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationDatabaseRepository.class);
	Connection con = null;
	DatabaseConnection connection = new DatabaseConnection();

	public AuthenticationDatabaseRepository() 
	{
		con = connection.doConnection();
	}

// This method authenticate user through userId and password
	
	public int doAuthenticate(User user) 
	{
		String userId = user.getuserId();
		String password = user.getPassword();
		int response = 0;
		String sql = "select userId from userauthentication where passwords=sha1('" + password + "')";
		try {
			Statement statement1 = con.createStatement();
			ResultSet result1 = statement1.executeQuery(sql);
			if (result1.next()) {
				String storedUserId = result1.getString("userId");
				if (storedUserId.equals(userId)) {
					response = 1;
				}
			}
		} catch (SQLException e) {
			logger.error("invalid userid and password" + e.getMessage());
		}

		return response;
	}

//To set new user info in database
	
	public int registerUser(User user)
	{
		String userid = user.getuserId();
		String password = user.getPassword();
		String type = user.getUserType();
		int response = 0;
		String sql = "insert into userauthentication values(?,sha1(?),?)";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, userid);
			statement.setString(2, password);
			statement.setString(3, type);
			int rs = statement.executeUpdate();
			if (rs == 1) {
				response = 1;
			}
		} catch (SQLException e) {
			logger.error("please enter valid information" + e.getMessage());
		}

		return response;
	}
	
//To find user by id
	
	public int getUserById(String userId)
	{
		int response = 0;
		String sql = "select*from patientInfo where patientId='" + userId + "'";
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				response = 1;
			}
		} catch (SQLException e) {
			logger.error("patient id not valid" + e.getMessage());
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
