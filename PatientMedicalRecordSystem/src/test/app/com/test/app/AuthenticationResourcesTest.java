package com.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import com.webapp.PatientMedicalRecordSystem.AuthenticationResources;
import com.webapp.PatientMedicalRecordSystem.User;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;

public class AuthenticationResourcesTest extends JerseyTest
{
	@Override
	public Application configure()
	{
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(AuthenticationResources.class);
	}

// To test authentication
	@Test
	public void testAuthentication()
	{
		User user = new User();
		user.setuserId("pat1nr");
		user.setPassword("Nr@1234");
		Response output = target("/user/authenticate").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
		assertEquals("Should return status 200", 200, output.getStatus());
		assertNotNull("Should return notification", output.getEntity());
	}
	
// To test register user
		@Test
		public void registerUser()
		{
			User user = new User();
			user.setuserId("pat2nr");
			user.setPassword("Nr@1234");
			user.setUserType("patient");
			Response output = target("/user/register").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
			assertEquals("Should return status 200", 200, output.getStatus());
			assertNotNull("Should return notification", output.getEntity());
		}
}
