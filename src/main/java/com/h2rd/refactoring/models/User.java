package com.h2rd.refactoring.models;

import java.util.List;


/**
 * This a model class of User. It contains the properties of a user.
 * 
 * @author aldocuevas
 *
 */
public class User {

	String name;
	String email;
	List<String> roles;

	/**
	 * Get Name
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set Name
	 * 
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get Email
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set Email
	 * 
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get roles
	 * 
	 * @return - List of String
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * Set Roles
	 * 
	 * @param roles - List of String
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
