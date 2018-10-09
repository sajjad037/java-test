package com.h2rd.refactoring.utilities;

import java.util.ArrayList;

import com.h2rd.refactoring.models.User;
/**
 * A Class which handles all the required in this application
 * @author SajjadAshrafCan
 *
 */
public class Validation {

	/**
	 * Validate user mandatory fields. and return error lists if there any
	 * @param user User
	 * @return ArrayList<String>
	 */
	public ArrayList<String> validateUser(User user) {
		ArrayList<String> errorList = new ArrayList<String>();
		if ((user.getEmail() == null || user.getEmail().isEmpty())) {
			errorList.add(StaticContent.EMAIL_MISSING);
		}
		if ((user.getRoles() == null || user.getRoles().size() == 0)) {
			errorList.add(StaticContent.ROLE_MISSING);
		}
		return errorList;
	}

}
