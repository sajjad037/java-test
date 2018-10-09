package com.h2rd.refactoring.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.h2rd.refactoring.models.User;
import com.h2rd.refactoring.usermanagement.UserDao;


/**
 * Service Layer, it communicate with DAO layer.
 * 
 * @author Sajjad Ashraf
 *
 */
@Component
public class UserService {

	/**
	 * Save User
	 * 
	 * @param user User
	 */
	public void saveUser(User user) {
		UserDao.getUserDao().saveUser(user);
	}

	/**
	 * update User
	 * 
	 * @param user User
	 */
	public void updateUser(User user) {
		UserDao.getUserDao().updateUser(user);
	}

	/**
	 * update User
	 * 
	 * @param user User
	 */
	public void deleteUser(User user) {
		UserDao.getUserDao().deleteUser(user);
	}

	/**
	 * get Users
	 * 
	 * @return List<User>
	 */
	public List<User> getUsers() {
		return UserDao.getUserDao().getUsers();
	}

	/**
	 * find User by Email
	 * 
	 * @param email String
	 * @return User
	 */
	public User findUserByEmail(String email) {
		return UserDao.getUserDao().findUserByEmail(email);
	}

	/**
	 * find User
	 * 
	 * @param name String
	 * @return ArrayList<User>
	 */
	public ArrayList<User> findUser(String name) {
		return UserDao.getUserDao().findUser(name);
	}

	/**
	 * Is User exist
	 * 
	 * @param user User
	 * @return boolean
	 */
	public boolean isUserExist(User user) {
		User usr = findUserByEmail(user.getEmail());
		if (usr == null) {
			return false;
		} else {
			return true;
		}
	}
}
