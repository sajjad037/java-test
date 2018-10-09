package test.com.h2rd.refactoring.unit;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.h2rd.refactoring.models.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;

import junit.framework.Assert;

/**
 * Unit test case (test a specific piece of code) to test UserResource
 * functionality. (Here the the piece of code we testing will act as end to end
 * test case)
 * 
 * @author aldocuevas
 *
 */
public class UserResourceUnitTest {

	UserResource userResource;
	UserDao userDao;

	@Test
	/**
	 * Test Case: Get Users
	 */
	public void getUsersTest() {

		userResource = new UserResource();
		userDao = UserDao.getUserDao();

		User user = new User();
		user.setName("fake user");
		user.setEmail("fake@user.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userDao.saveUser(user);

		Response response = userResource.getUsers();
		Assert.assertEquals(200, response.getStatus());
	}
}
