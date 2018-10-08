package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
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
		integration.setRoles(new ArrayList<String>());

		Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
		Assert.assertEquals(200, response.getStatus());
	}

	@Test
	/**
	 * Test Case: Update user.
	 */
	public void updateUserTest() {
		UserResource userResource = new UserResource();

		createUserTest();

		User updated = new User();
		updated.setName("integration");
		updated.setEmail("updated@integration.com");
		updated.setRoles(new ArrayList<String>());

		Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
		Assert.assertEquals(200, response.getStatus());
	}
}
