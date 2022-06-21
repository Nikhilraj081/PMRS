package com.webapp.PatientMedicalRecordSystem;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("doctor")
public class DoctorResources  
{
	DoctorDatabaseRepository repository = new DoctorDatabaseRepository ();
	Validation validation = new Validation();
	
//It fetch doctor details and return to users
	
	@GET
	@Path("get_doctor_information/{doctorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Doctor> getDoctorInformation(@PathParam("doctorId") String doctorId) 
	{
		List<Doctor> doctorInfoList = new ArrayList<Doctor>();
		doctorInfoList = repository.getDoctorInformation(doctorId);
		return doctorInfoList;
	}
	
//This method take doctor information from users and save to database
	
	@POST
	@Path("set_doctor_information")
	@Consumes(MediaType.APPLICATION_JSON)
	public String setDoctorDetails(Doctor doctor )
	{
		String response = "";
		if (validation.validateUserId(doctor.getDoctorId()) == true) {
			if (validation.validateName(doctor.getDoctorName()) == true) {
				if (validation.validateMobile(doctor.getDoctorMobile()) == true) {
					repository.setdoctorInfo(doctor);
					response = "doctor information is saved sucessfully";
				}
			}
		}
		return response;
	}
}