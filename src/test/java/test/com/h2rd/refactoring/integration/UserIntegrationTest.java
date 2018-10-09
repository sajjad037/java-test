package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.h2rd.refactoring.models.User;
import com.h2rd.refactoring.web.UserResource;

import junit.framework.Assert;

/**
 * Integration test case (end to end test case) to test UserResource
 * functionality.
 * 
 * @author aldocuevas
 *
 */
public class UserIntegrationTest {

	@Test
	/**
	 * Test Case: Create User
	 */
	public void createUserTest() {
		UserResource userResource = new UserResource();

		User integration = new User();
		integration.setName("integration");
		integration.setEmail("initial@integration.com");
		integration.setRoles(Arrays.asList("admin"));

		Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
		Assert.assertEquals(200, response.getStatus());
		
	}
	
	@Test
	/**
	 * Test Case: Create User Fail
	 */
	public void createUserFailTest() {
		UserResource userResource = new UserResource();

		User integration = new User();
		integration.setName("initial");
		integration.setEmail("integration@integration.com");
		integration.setRoles(new ArrayList<String>());

		Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
		Assert.assertEquals(403, response.getStatus());
	}

	@Test
	/**
	 * Test Case: Update user.
	 */
	public void updateUserTest() {
		UserResource userResource = new UserResource();

		User integration = new User();
		integration.setName("integration1");
		integration.setEmail("initial1@integration.com");
		integration.setRoles(Arrays.asList("admin"));

		Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
		
		User updated = new User();
		updated.setName("updated");
		updated.setEmail("initial1@integration.com");
		updated.setRoles(Arrays.asList("admin", "master"));

		response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
		Assert.assertEquals(200, response.getStatus());
		
	}
}
