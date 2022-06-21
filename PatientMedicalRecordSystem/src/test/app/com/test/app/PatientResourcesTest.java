package com.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.webapp.PatientMedicalRecordSystem.Appointment;
import com.webapp.PatientMedicalRecordSystem.Medication;
import com.webapp.PatientMedicalRecordSystem.PatientDatabaseRepository;
import com.webapp.PatientMedicalRecordSystem.PatientResources;
import com.webapp.PatientMedicalRecordSystem.Patients;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class PatientResourcesTest extends JerseyTest
{

	 @Override
	    public Application configure()
	   {
	        enable(TestProperties.LOG_TRAFFIC);
	        enable(TestProperties.DUMP_ENTITY);
	        return new ResourceConfig(PatientResources.class);
	    }
	 
//To test getPatientInformation
	 	@Test
	    public void testgetPatientInformation() 
	 	{
	 		Response output = target("/patient/get_patient_information/pat1nr").request().get();
		     assertEquals("should return status 200", 200, output.getStatus());
		     assertNotNull("Should return list", output.getEntity()); 
	    }
	 	
//To test get all patient details
	 	
	 	@Test
	    public void testgetAllPatient() 
	 	{
	 		Response output = target("/patient/get_all_patient").request().get();
		     assertEquals("should return status 200", 200, output.getStatus());
		     assertNotNull("Should return list", output.getEntity()); 
	    }
	 	
//To test appointment
	 	@Test
		public void testAppointment()
	 	{
			Appointment appointment = new Appointment();
			appointment.setAppointmentDate("2022-03-10");
			appointment.setAppointmentTime("12:30");
			appointment.setPatientId("pat2nr");
			appointment.setDoctorId("doc1");
			appointment.setconsultingReason("fever");
			Response output = target("/patient/appointment").request().post(Entity.entity(appointment, MediaType.APPLICATION_JSON));
			assertEquals("Should return status 200", 200, output.getStatus());
			assertNotNull("Should return notification", output.getEntity());
		}
	 	
//To test getMedication
	 	@Test
	    public void testgetMedication() 
	 	{
	        Response output = target("/patient/getMedication/52").request().get();
	        assertEquals("should return status 200", 200, output.getStatus());
	        assertNotNull("Should return list", output.getEntity());
	    }
	 	
//To test setMedication
	 	@Test
		public void testsetMedication()
		{
			List<Medication> medicineList = new ArrayList<>();
			Medication medication = new Medication();

			medication.setAppointmentId(52);
			medication.setMedicineName("Hydromet");
			medication.setTimesADay(2);
			medication.setMedicineDuration(10);
			medication.setStatus("active");
			medication.setStartingDate("2022-04-06");
			medication.setEndDate("2022-04-16");

			medicineList.add(medication);

			Response output = target("/patient/setMedication").request().post(Entity.entity(medicineList, MediaType.APPLICATION_JSON));
			assertEquals("Should return status 200", 200, output.getStatus());
			assertNotNull("Should return notification", output.getEntity());
		}
	 	
//To test setPatientDetails
	 	@Test
	 	public void setPatientDetails()
	 	{
	 		Patients patient=new Patients();
	 		patient.setPatientUserid("pat1nr");
	 		patient.setPatientName("Nikhil Raj");
	 		patient.setPatientAddress("chhapra");
	 		patient.setPatientMobileNumber("8798756787");
	 		patient.setPatientDateOfBirth("1998-02-02");
	 		patient.setPatientGender("male");
	 		
	 		Response output = target("/patient/set_patient_information").request().post(Entity.entity(patient, MediaType.APPLICATION_JSON));
			assertEquals("Should return status 200", 200, output.getStatus());
			assertNotNull("Should return notification", output.getEntity());
	 	}
	 	
//To test getAppointment
	 @Test
	 public void getAppointment()
	 {
		 Response output = target("/patient/appointment/pat1nr").request().get();
	     assertEquals("should return status 200", 200, output.getStatus());
	     assertNotNull("Should return list", output.getEntity()); 
	 }

//To test updatePatient
	 @Test
	 public void  updatePatient()
	 {
		Patients patient=new Patients();
		patient.setPatientUserid("pat1nr");
		patient.setPatientName("Nikhil Raj");
		patient.setPatientAddress("chhapra");
		patient.setPatientMobileNumber("8798756787");
		patient.setPatientDateOfBirth("1998-02-02");
		patient.setPatientGender("male"); 
		Response output = target("/patient/update_patient_information").request().put(Entity.entity(patient, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return notification", output.getEntity());
		 	 	
	 }	

//To test delete patient
	 @Test
	 public void testDeletePatient()
	 {
		 Response output = target("/patient/delete_patient/pat2nr").request().delete();
	     assertEquals("should return status 200", 200, output.getStatus());
	     assertNotNull("Should return list", output.getEntity()); 
	 }
}
