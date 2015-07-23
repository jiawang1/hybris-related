package com.tasly.anguo.storefront.forms.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.tasly.anguo.storefront.forms.AnguoRegisterForm;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;

@Component("anguoRegistrationValidator")
public class AnguoRegistrationValidator extends RegistrationValidator
{
	public static final String EMAIL_REGEX = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return AnguoRegisterForm.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final AnguoRegisterForm registerForm = (AnguoRegisterForm) object;
		final String userId = registerForm.getUserId();
		final String pwd = registerForm.getPwd();
		final String checkPwd = registerForm.getCheckPwd();
		final String mobile = registerForm.getMobileNumber();
		final String captcha = registerForm.getCaptcha();

		if (StringUtils.isEmpty(userId))
		{
			errors.rejectValue("userId", "register.username.invalid");
		}
		else if (StringUtils.length(userId) > 255)
		{
			errors.rejectValue("titleCode", "register.username.invalid");
		}

		if (StringUtils.isEmpty(pwd))
		{
			errors.rejectValue("pwd", "register.pwd.invalid");
		}
		else if (StringUtils.length(pwd) < 6 || StringUtils.length(pwd) > 20)
		{
			errors.rejectValue("pwd", "register.pwd.invalid");
		}

		if (StringUtils.isNotEmpty(pwd) && StringUtils.isNotEmpty(checkPwd) && !StringUtils.equals(pwd, checkPwd))
		{
			errors.rejectValue("checkPwd", "validation.checkPwd.equals");
		}
		else
		{
			if (StringUtils.isEmpty(checkPwd))
			{
				errors.rejectValue("checkPwd", "register.checkPwd.invalid");
			}
		}
		
		if (StringUtils.isEmpty(mobile))
		{
			errors.rejectValue("mobile", "register.mobile.invalid");
		}
		else if (StringUtils.length(mobile) != 11)
		{
			errors.rejectValue("mobile", "register.mobile.invalid");			
		}
		
		if (StringUtils.isEmpty(captcha))
		{
			errors.rejectValue("captcha", "register.captcha.invalid");			
		}
	}

	public boolean validateEmailAddress(final String email)
	{
		final Pattern pattern = Pattern.compile(EMAIL_REGEX);
		final Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}