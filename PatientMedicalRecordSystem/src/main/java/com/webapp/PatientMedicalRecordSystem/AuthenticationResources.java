package com.webapp.PatientMedicalRecordSystem;

import java.sql.SQLException;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("user")
public class AuthenticationResources
{
	AuthenticationDatabaseRepository repository = new AuthenticationDatabaseRepository();
	Validation validation=new Validation();
	
//This method take userId and password from user and authenticate from database
	
	@Path("authenticate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String authentication(User user) throws SQLException
	{
		String response = "database error";
		int result = repository.doAuthenticate(user);
		if (result == 1) {
			response = "you have sucessfully login";
		} else {
			throw new SQLException("Invalid userid or Password");
		}
		return response;
	}
	
//This method take patient details and save to database
	
	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerUser(User user) throws SQLException
	{
		String response = "";
		if (repository.getUserById(user.getuserId()) == 1) {
			response = "This userId is already exist";
		} else {
			if (validation.validateUserId(user.getuserId()) == true) {
				if (validation.validateUserType(user.getUserType()) == true) {
					repository.registerUser(user);
					response = "Register sucessfully";
				} else {
					response = "user type should be patient,doctor or admin";
				}
			} else {
				response = "user id shoud be character,number or combination of both";
			}
		}
		return response;
	}
}
