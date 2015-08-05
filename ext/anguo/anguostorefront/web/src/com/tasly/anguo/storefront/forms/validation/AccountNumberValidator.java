package com.tasly.anguo.storefront.forms.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tasly.anguo.storefront.forms.PersonalIdentifyForm;

@Component("accountNumberValidator")
public class AccountNumberValidator implements Validator {

	@Override
	public boolean supports(Class<?> class1) {
		return PersonalIdentifyForm.class.equals(class1);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		PersonalIdentifyForm form = (PersonalIdentifyForm) obj;
		String bank = form.getBank();
		String accountNumber = form.getAccountNumber();
		String accountOwer = form.getAccountOwer();
		if(StringUtils.isBlank(bank)){
			errors.rejectValue("bank", "bank.isBlank");
		}
		if(StringUtils.isBlank(accountNumber)){
			errors.rejectValue("accountNumber", "accountNumber.isBlank");
		}
		if(StringUtils.isBlank(accountOwer)){
			errors.rejectValue("accountOwer", "accountOwer.isBlank");
		}
		//TODO call the bank check interface to validate whether the accoutNumber is correct or not
		if(!checkAccountNumber(bank, accountNumber, accountOwer)) {
			
		}
		
	}
	
	private boolean checkAccountNumber(String bank,String accountNumber,String accountOwer) {
	
		return true;
	}

}
