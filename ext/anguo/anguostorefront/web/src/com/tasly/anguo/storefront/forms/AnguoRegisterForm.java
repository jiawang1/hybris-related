package com.tasly.anguo.storefront.forms;

import com.tasly.anguo.core.enums.UserType;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;

public class AnguoRegisterForm extends RegisterForm {
	private String userId;
	private UserType userType;


	public UserType getUserType() {
		return userType;
	}

	public void setUserType(String userTypeValue) {
		this.userType = UserType.valueOf(userTypeValue);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
