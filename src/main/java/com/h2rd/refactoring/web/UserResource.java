package com.h2rd.refactoring.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

@Path("/users")
@Repository

/**
 * Web service exposed API.
 * 
 * @author aldocuevas
 *
 */
public class UserResource {

	public UserDao userDao;

	@GET
	@Path("add/")
	/**
	 * Add user
	 * 
	 * @param name  String
	 * @param email String
	 * @param roles List<String>
	 * @return Response
	 */
	public Response addUser(@QueryParam("name") String name, @QueryParam("email") String email,
			@QueryParam("role") List<String> roles) {

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setRoles(roles);

		if (userDao == null) {
			userDao = UserDao.getUserDao();
		}

		userDao.saveUser(user);
		return Response.ok().entity(user).build();
	}

	@GET
	@Path("update/")
	/**
	 * Update User
	 * 
	 * @param name  String
	 * @param email String
	 * @param roles List<String>
	 * @return Response
	 */
	public Response updateUser(@QueryParam("name") String name, @QueryParam("email") String email,
			@QueryParam("role") List<String> roles) {

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setRoles(roles);

		if (userDao == null) {
			userDao = UserDao.getUserDao();
		}

		userDao.updateUser(user);
		return Response.ok().entity(user).build();
	}

	@GET
	@Path("delete/")
	/**
	 * delete User
	 * 
	 * @param name  String
	 * @param email String
	 * @param roles List<String>
	 * @return Response
	 */
	public Response deleteUser(@QueryParam("name") String name, @QueryParam("email") String email,
			@QueryParam("role") List<String> roles) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setRoles(roles);

		if (userDao == null) {
			userDao = UserDao.getUserDao();
		}

		userDao.deleteUser(user);
		return Response.ok().entity(user).build();
	}

	@GET
	@Path("find/")
	/**
	 * Get Users
	 * 
	 * @return Response
	 */
	public Response getUsers() {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:/application-config.xml" });
		userDao = context.getBean(UserDao.class);
		List<User> users = userDao.getUsers();
		if (users == null) {
			users = new ArrayList<User>();
		}

		GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {
		};
		return Response.status(200).entity(usersEntity).build();
	}

	@GET
	@Path("search/")
	/**
	 * find User
	 * 
	 * @param name String
	 * @return Response
	 */
	public Response findUser(@QueryParam("name") String name) {

		if (userDao == null) {
			userDao = UserDao.getUserDao();
		}

		ArrayList<User> user = userDao.findUser(name);
		return Response.ok().entity(user).build();
	}
}
