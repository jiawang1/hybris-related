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
		final String mobileNumber = registerForm.getMobileNumber();
		final String captcha = registerForm.getCaptcha();

		if (StringUtils.isEmpty(userId))
		{
			errors.rejectValue("userId", "register.username.isBlank");
		}
		else if (StringUtils.length(userId) > 255)
		{
			errors.rejectValue("userId", "register.username.overLength");
		}
		
		//TODO check whether userId contains companyName or stock

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
			Pattern patter = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
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