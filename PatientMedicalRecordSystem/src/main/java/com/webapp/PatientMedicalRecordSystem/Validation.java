package com.webapp.PatientMedicalRecordSystem;
import java.util.regex.*;

import org.apache.commons.validator.routines.EmailValidator;
public class Validation 
{
	
//userId validation
	public boolean validateUserId(String userId)
	{
		boolean response=false;
		if(userId!=null && userId.matches("[A-Za-z0-9]*")==true)
		{
			response=true;
		}
		return response;
	}
		
//Name validation
	public boolean validateName(String name)
	{
		boolean response=false;
		if(name!=null && name.matches("[A-Za-z ]*")==true)
		{
			response= true;
		}	
		return response;
	}
	
//user type validation
	public boolean validateUserType(String userType)
	{
		boolean response=false;
		if(userType.equals("patient") || userType.equals("doctor") || userType.equals("admin"))
		{
			response= true;
		}	
		return response;
	}
		
//Email id validation
	public boolean validateEmail(String email)
	{
		boolean response=false;
		EmailValidator validator=EmailValidator.getInstance();
		if(validator.isValid(email))
		{
			response=true;
		}
		return response;
	}
		
//mobile validation
	public boolean  validateMobile(String mobile)
	{
		boolean response=false;
		String regex="^\\d{10}$";
		Pattern pat=Pattern.compile(regex);
		if(mobile!=null && pat.matcher(mobile).matches()==true)
		{
			response= true;
		}	
		return response;
	}
		
}
