package com.tasly.anguo.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;

public class AnguoLoginForm extends LoginForm {
	
	private String _spring_security_remember_me;

	/**
	 * @return the _spring_security_remember_me
	 */
	public String get_spring_security_remember_me()
	{
		return _spring_security_remember_me;
	}

	/**
	 * @param _spring_security_remember_me
	 *           the _spring_security_remember_me to set
	 */
	public void set_spring_security_remember_me(final String _spring_security_remember_me)
	{
		this._spring_security_remember_me = _spring_security_remember_me;
	}

}
