package com.tasly.anguo.storefront.forms.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tasly.anguo.storefront.forms.EnterpriseInformationForm;


/**
 * @author i319510
 *
 */
@Component("anguoEnterpriseInformationValidator")
public class AnguoEnterpriseInformationValidator implements Validator
{
	@Override
	public boolean supports(Class<?> arg0)
	{
		return AnguoEnterpriseInformationValidator.class.equals(arg0);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		final EnterpriseInformationForm eif = (EnterpriseInformationForm) object;

		if (StringUtils.isEmpty(eif.getName()) || StringUtils.length(eif.getName()) > 100)
		{
			errors.rejectValue("name", "text.account.enterprise.name.invalid");
		}
	    if (StringUtils.isEmpty(eif.getRegisterId()) || StringUtils.length(eif.getName().trim()) != 13 || StringUtils.length(eif.getName().trim()) != 15)
	    {
	        errors.rejectValue("registerId", "text.account.enterprise.register.id.invalid");
	    }
	}

}
