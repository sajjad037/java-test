package com.h2rd.refactoring.utilities;

import java.util.ArrayList;

import com.h2rd.refactoring.usermanagement.User;

public class Validation {

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
