package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;

// Singleton desogn pattern or other creational design pattern,
/**
 * Data access Object Class, it handles the RCUD operation on user object.
 * 
 * @author aldocuevas
 *
 */
public class UserDao {

	public ArrayList<User> users;

	public static UserDao userDao;

	/**
	 * Get instance of user DOA
	 * 
	 * @return UserDao
	 */
	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	/**
	 * Save User
	 * 
	 * @param user User
	 */
	public void saveUser(User user) {
		if (this.users == null) {
			this.users = new ArrayList<User>();
		}
		synchronized (this) {
			this.users.add(user);
		}
	}

	/**
	 * Get Users
	 * 
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getUsers() {
		try {
			return this.users;
		} catch (Throwable e) {
			System.out.println("error");
			return null;
		}
	}

	/**
	 * Delete a user
	 * 
	 * @param userToDelete User
	 */
	public void deleteUser(User userToDelete) {
		try {
			int removeIdx = -1;
			for (int i = 0; i < this.users.size(); i++) {
				if (this.users.get(i).getEmail().equals(userToDelete.getEmail())) {
					removeIdx = i;
					break;
				}
			}

			if (removeIdx > -1) {
				synchronized (this) {
					this.users.remove(removeIdx);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update a user
	 * 
	 * @param userToUpdate User
	 */
	public void updateUser(User userToUpdate) {
		try {
			for (User user : this.users) {
				if (user.getName() == userToUpdate.getName()) {
					synchronized (this) {
						user.setEmail(userToUpdate.getEmail());
						user.setRoles(userToUpdate.getRoles());
					}
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find a user
	 * 
	 * @param name String
	 * @return User
	 */
	public User findUser(String name) {
		try {
			for (User user : this.users) {
				if (user.getName() == name) {
					return user;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
}
