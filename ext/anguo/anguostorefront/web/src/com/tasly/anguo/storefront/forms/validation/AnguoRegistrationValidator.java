package com.tasly.anguo.storefront.forms.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.tasly.anguo.core.enums.UserType;
import com.tasly.anguo.storefront.forms.AnguoRegisterForm;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;

@Component("anguoRegistrationValidator")
public class AnguoRegistrationValidator extends RegistrationValidator
{
	public static final String EMAIL_REGEX = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
	public static final String USER_NAME_INVALID_SUBSTRING_COMPANY = "公司";
	public static final String USER_NAME_INVALID_SUBSTRING_STOCK   = "股份";

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
		final String mobileNumber = registerForm.getMobileNumber();
		final String captcha = registerForm.getCaptcha();
		UserType userType = UserType.valueOf(registerForm.getUserType());

		if (StringUtils.isEmpty(userId))
		{
			errors.rejectValue("userId", "register.username.isBlank");
		}
		else if (StringUtils.length(userId) > 30)
		{
			errors.rejectValue("userId", "register.username.overLength");
		}
		else if (userType == UserType.PERSONAL && 
				(userId.contains(USER_NAME_INVALID_SUBSTRING_COMPANY) || userId.contains(USER_NAME_INVALID_SUBSTRING_STOCK)))
		{
			errors.rejectValue("userId", "register.username.personal.invalid.substring");			
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
		
		if (StringUtils.isEmpty(mobileNumber))
		{
			errors.rejectValue("mobileNumber", "register.mobile.invalid");
		}
		else
		{
			Pattern patter = Pattern.compile("^1\\d{10}$"); 
			Matcher matcher = patter.matcher(mobileNumber); 
			if(!matcher.find()) {
				errors.rejectValue("mobileNumber", "register.mobile.invalid");		
			}
		}
		
		//TODO check whether the input captcha is the same with the captcha you received on your cellphone
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