package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;

import com.h2rd.refactoring.models.User;

/**
 * Data access Object Class, it handles the RCUD operation on user object.
 * 
 * @author aldocuevas
 *
 */
public class UserDao {

	public ArrayList<User> users = new ArrayList<User>();

	private static UserDao userDao;

	/**
	 * Constructor: It is private so that this class cannot be instantiated
	 */
	private UserDao() {
	}

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
				if (user.getEmail().equals(userToUpdate.getEmail())) {
					synchronized (this) {
						user.setEmail(userToUpdate.getEmail());
						user.setRoles(userToUpdate.getRoles());
					}
					break;
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
	public ArrayList<User> findUser(String name) {
		ArrayList<User> usr = new ArrayList<User>();
		try {
			for (User user : this.users) {
				if (user.getName().equals(name)) {
					usr.add(user);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return usr;
	}

	/**
	 * Find a user by email
	 * 
	 * @param name String
	 * @return User
	 */
	public User findUserByEmail(String email) {
		try {
			for (User user : this.users) {
				if (user.getEmail().equals(email)) {
					return user;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
}
