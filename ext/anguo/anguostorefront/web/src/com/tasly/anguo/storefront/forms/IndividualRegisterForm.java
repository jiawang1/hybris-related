package com.tasly.anguo.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;

public class IndividualRegisterForm extends RegisterForm {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
