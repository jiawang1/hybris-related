package com.tasly.anguo.storefront.forms.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tasly.anguo.storefront.forms.PersonalIdentifyForm;

@Component("idCardValidator")
public class IdCardValidator implements Validator {

	@Override
	public boolean supports(Class<?> class1) {
		return PersonalIdentifyForm.class.equals(class1);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		PersonalIdentifyForm form = (PersonalIdentifyForm) obj;
		String idName = form.getIdName();
		String idCard = form.getIdCard();
		if(StringUtils.isBlank(idName)){
			errors.rejectValue("idName", "idName.isBlank");
		}
		if(StringUtils.isBlank(idCard)){
			errors.rejectValue("idCard", "idCard.isBlank");
		}
		//TODO call the idcard check interface to validate whether the idcard number is correct or not
		if(!checkIdCard(idName,idCard)) {
			errors.rejectValue("idCard", "idCard.invalid");;
		}
		
		
	}
	
	private boolean checkIdCard(String idName,String idCard) {
		
		return false;
	}
}
