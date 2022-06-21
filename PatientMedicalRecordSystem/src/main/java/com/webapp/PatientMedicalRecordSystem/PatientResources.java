package com.webapp.PatientMedicalRecordSystem;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("patient")
public class PatientResources 
{
	PatientDatabaseRepository repository = new PatientDatabaseRepository();
	Validation validation=new Validation();
	
//This method fetch all patient data by ID from database and return to patient
	
	@GET
	@Path("get_patient_information/{patientId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patients> getPatientInformation(@PathParam("patientId") String patientId) 
	{
		List<Patients> patientInfoList = new ArrayList<Patients>();
		patientInfoList = repository.getPatientInformation(patientId);
		return patientInfoList;
	}

//This method fetch all patient details from Database
	
	@GET
	@Path("get_all_patient")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patients> getAllPatient() 
	{
		List<Patients> patientInfoList = new ArrayList<Patients>();
		patientInfoList = repository.getAllPatientInfo();
		return patientInfoList;
	}

//This method take patient information from users and save to database
	
	@POST
	@Path("set_patient_information")
	@Consumes(MediaType.APPLICATION_JSON)
	public String setPatientDetails(Patients patient )
	{
		String response = "";

		if (validation.validateName(patient.getPatientName()) == true) {
			if (validation.validateMobile(patient.getPatientMobileNumber()) == true) {
				if (validation.validateEmail(patient.getEmailId()) == true) {
					repository.setPatientInfo(patient);
					response = "patient information saved";
				} else {
					response = "please enter a valid email id";
				}
			} else {
				response = "please enter a valid mobile number";
			}
		} else {
			response = "please enter a valid name";
		}
		return response;
	}
	
//This method update patient data 
	
	@PUT
	@Path("update_patient_information")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePatient(Patients patient)
	{
		String response = "";

		if (validation.validateName(patient.getPatientName()) == true) {
			if (validation.validateMobile(patient.getPatientMobileNumber()) == true) {
				if (validation.validateEmail(patient.getEmailId()) == true) {
					repository.updatePatientInfo(patient);
					response = "updated patient information";
				} else {
					response = "please enter a valid email id";
				}
			} else {
				response = "please enter a valid mobile number";
			}
		} else {
			response = "please enter a valid name";
		}

		return response;
	}
	
//This method update patient data 
	
	@DELETE
	@Path("delete_patient/{patientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String deletePatientInfo(@PathParam("patientId") String patientId)
	{
		String response = "";
		if (repository.getPatientInformation(patientId).isEmpty()) {
			response = "patient id doesn't exist";
		} else {
			if (repository.deletePatientInfo(patientId) == 1) {
				response = "patient information is deleted sucessfully";
			}
		}
		return response;
	}		

//This method take appointment details and book appointment
	
	@POST
	@Path("appointment")
	@Consumes(MediaType.APPLICATION_JSON)
	public String Appointment(Appointment patientAppointment)
	{
		String response = "";
		if (repository.bookAppointment(patientAppointment) == 1) {
			response = "your appointment is booked";
		} else {
			response = "please enter a valid information";
		}
		return response;
	}
	
//This method fetch appointment details and return to users
	
	@GET
	@Path("appointment/{patientId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Appointment> getAppointment(@PathParam("patientId") String patientId)
	{
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		appointmentList = repository.getAppointmentInformation(patientId);
		return appointmentList;
	}
	
//This method take appointment id and fetch medication details and return to users
	
	@GET
	@Path("getMedication/{appointmentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Medication> getMedication(@PathParam("appointmentId") String appointmentId)
	{
		List<Medication> medicineList = new ArrayList<Medication>();
		medicineList = repository.getMedicationDetails(appointmentId);
		return medicineList;
	}
		
//This method set medication details
	
	@POST
	@Path("setMedication")
	@Consumes(MediaType.APPLICATION_JSON)
	public String setMedication(List<Medication> medicationDetails)
	{
		String response = "";
		if (repository.setMedicationDetails(medicationDetails) == 1) {
			response = "medicine details is saved";
		}
		return response;
	}
}