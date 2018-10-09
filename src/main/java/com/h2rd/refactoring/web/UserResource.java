package com.h2rd.refactoring.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.h2rd.refactoring.models.User;
import com.h2rd.refactoring.service.UserService;
import com.h2rd.refactoring.utilities.Validation;

@Repository
@Path("/users")
/**
 * Web service exposed API.
 * 
 * @author aldocuevas
 *
 */
public class UserResource {

	@Autowired
	private UserService userService = new UserService();

	@POST
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

		ArrayList<String> errorrs = new Validation().validateUser(user);
		if (errorrs.size() > 0) {
			return Response.status(Response.Status.FORBIDDEN).entity(errorrs).build();
		}
		if (userService.isUserExist(user)) {
			return Response.status(Response.Status.CONFLICT).entity("User '" + user.getEmail() + "' already exist.")
					.build();
		}

		userService.saveUser(user);
		return Response.ok().entity(user).build();

	}

	@PUT
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

		ArrayList<String> errorrs = new Validation().validateUser(user);
		if (errorrs.size() > 0) {
			return Response.status(Response.Status.FORBIDDEN).entity(errorrs).build();
		}

		if (!userService.isUserExist(user)) {
			return Response.status(Response.Status.FORBIDDEN).entity("User '" + user.getEmail() + "' does not exist.")
					.build();
		}

		userService.updateUser(user);
		return Response.ok().entity(user).build();
	}

	@DELETE
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

		ArrayList<String> errorrs = new Validation().validateUser(user);
		if (errorrs.size() > 0) {
			return Response.status(Response.Status.FORBIDDEN).entity(errorrs).build();
		}

		if (!userService.isUserExist(user)) {
			return Response.status(Response.Status.FORBIDDEN).entity("User '" + user.getEmail() + "' does not exist.")
					.build();
		}

		userService.deleteUser(user);
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
		List<User> users = userService.getUsers();
		if (users == null) {
			return Response.status(Response.Status.FORBIDDEN).entity("User not found.").build();
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
	 * @param email String
	 * @return Response
	 */
	public Response findUser(@QueryParam("email") String email) {
		User user = userService.findUserByEmail(email);
		if (user == null) {
			return Response.status(Response.Status.FORBIDDEN).entity("User not found.").build();
		}
		return Response.ok().entity(user).build();
	}
}
