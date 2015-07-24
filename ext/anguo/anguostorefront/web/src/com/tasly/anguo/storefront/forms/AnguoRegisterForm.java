package com.tasly.anguo.storefront.forms;

import com.tasly.anguo.core.enums.UserType;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;

public class AnguoRegisterForm extends RegisterForm {
	private String userId;
	private UserType userType = UserType.PERSONAL;
	private Boolean isAgreeTerms = false;



	public Boolean getIsAgreeTerms() {
		return isAgreeTerms;
	}

	public void setIsAgreeTerms(Boolean isAgreeTerms) {
		this.isAgreeTerms = isAgreeTerms;
	}

	public String getUserType() {
		return userType.toString();
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
