package com.tasly.anguo.storefront.forms.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tasly.anguo.storefront.forms.EnterpriseIdentifyForm;

@Component("enterpriseAccountValidator")
public class EnterpriseAccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> class1) {
		return EnterpriseIdentifyForm.class.equals(class1);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EnterpriseIdentifyForm form = (EnterpriseIdentifyForm) obj;
		
	}
	
}
