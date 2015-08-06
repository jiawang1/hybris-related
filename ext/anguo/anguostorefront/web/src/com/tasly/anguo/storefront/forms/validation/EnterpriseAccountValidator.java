package com.tasly.anguo.storefront.forms.validation;

import org.apache.commons.lang.StringUtils;
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
		String companyName = form.getCompanyName();
		String bank = form.getBank();
		String accountNumber = form.getAccountNumber();
		String legalRepr = form.getLegalRepr();
		String[] licenses = form.getLicenses();
		if(StringUtils.isBlank(companyName)){
			errors.rejectValue("companyName", "companyName.isBlank");
		}else if(StringUtils.isBlank(bank)){
			errors.rejectValue("bank", "bank.isBlank");
		}else if(StringUtils.isBlank(accountNumber)) {
			errors.rejectValue("accountNumber", "accountNumber.isBlank");
		}else if(StringUtils.isBlank(legalRepr)) {
			errors.rejectValue("legalRepr", "legalRepr.isBlank");
		}else if(licenses == null && licenses.length <=  0) {
			errors.rejectValue("licenses", "licenses.isBlank");
		}else if(!checkAccountNumber(bank,accountNumber)) {
			errors.rejectValue("bank", "bank.invalid");;
		}
	}
	
	private boolean checkAccountNumber(String bank,String accountNumber) {
		
		//TODO call the bank interface to validate whether this is valid
		return true;
	}
	
}
