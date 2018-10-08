package test.com.h2rd.refactoring.unit;

import java.util.Arrays;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

/**
 * Unit test case (test a specific piece of code) to test UserDao functionality.
 * 
 * @author aldocuevas
 *
 */
public class UserDaoUnitTest {

	UserDao userDao;

	@Test
	/**
	 * Test case: Save User
	 */
	public void saveUserTest() {
		userDao = UserDao.getUserDao();

		User user = new User();
		user.setName("Fake Name");
		user.setEmail("fake@email.com");
		user.setRoles(Arrays.asList("admin", "master"));

		userDao.saveUser(user);
	}

	@Test
	/**
	 * Test Case: Delete User.
	 */
	public void deleteUserTest() {
		userDao = UserDao.getUserDao();

		User user = new User();
		user.setName("Fake Name");
		user.setEmail("fake@email.com");
		user.setRoles(Arrays.asList("admin", "master"));

		try {
			userDao.deleteUser(user);
		} catch (NullPointerException e) {
		}
	}
}