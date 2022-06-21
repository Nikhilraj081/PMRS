package com.webapp.PatientMedicalRecordSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import io.lsn.logger.factory.LoggerFactory;
import io.lsn.logger.factory.logger.Logger;
public class DatabaseConnection
{
	private static final Logger  logger = LoggerFactory.getLogger(AuthenticationDatabaseRepository.class);
	Connection con = null;

// This method established connection with database and return connection object
	
	public Connection doConnection()
	{
		try {
			Properties properties = new Properties();
			properties.load(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties"));
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			String url = "jdbc:mysql://localhost:3306/patient_medical_record_system";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			logger.error("connection error" + e.getMessage());
		}
		return con;
	}
	
//This method close DB connection
	
	public void closeConnection()
	{
		try {
			con.close();
		} catch (SQLException e) {
			logger.error("connection error" + e.getMessage());
		}

	}
}
