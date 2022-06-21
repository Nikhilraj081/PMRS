package com.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.webapp.PatientMedicalRecordSystem.Doctor;
import com.webapp.PatientMedicalRecordSystem.DoctorResources;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class DoctorResourcesTest extends JerseyTest 
{
	@Override
	public Application configure() 
	{
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(DoctorResources.class);
	}

//To test getDoctorInformation
	@Test
	public void testgetDoctorInformation()
	{
		Response output = target("/doctor/get_doctor_information/doc1rr").request().get();
		assertEquals("should return status 200", 200, output.getStatus());
		assertNotNull("Should return list", output.getEntity());
	}

//To test setDoctorDetails
	@Test
	public void setDoctorDetails()
	{
		Doctor doctor = new Doctor();
		doctor.setDoctorId("doc1rr");
		doctor.setDoctorName("Rajeev Ranjan");
		doctor.setDoctorType("general physician");
		doctor.setDoctorAddress("chhapra");
		doctor.setDoctorMobile("8756767876");
		doctor.setDoctorGender("male");

		Response output = target("/doctor/set_doctor_information").request()
				.post(Entity.entity(doctor, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return notification", output.getEntity());
	}
}
