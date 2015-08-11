package com.tasly.anguo.storefront.forms.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.core.service.IAnguoCustomerAccountService;
import com.tasly.anguo.storefront.forms.EnterpriseIdentifyForm;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.servicelayer.user.UserService;

@Component("enterpriseAccountValidator")
public class EnterpriseAccountValidator implements Validator {

	@Resource
	private IAnguoCustomerAccountService customerAccountService;
	@Resource
	public UserService userService;
	
	@Override
	public boolean supports(Class<?> class1) {
		return EnterpriseIdentifyForm.class.equals(class1);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EnterpriseIdentifyForm form = (EnterpriseIdentifyForm) obj;
		final Pattern pattern = Pattern.compile("\\d{15}|\\d{13}");
		String companyName = form.getCompanyName();
		String registeredNo = form.getRegisteredNo();
		String bank = form.getBank();
		String accountNumber = form.getAccountNumber();
		String legalRepr = form.getLegalRepr();
		String[] licenses = form.getLicenses();
		if(StringUtils.isBlank(companyName) || companyName.length() > 100){
			errors.rejectValue("companyName", "companyName.isInValid");
		}else if(StringUtils.isBlank(registeredNo) || !pattern.matcher(registeredNo).matches()) {
			errors.rejectValue("registeredNo", "registeredNo.isInValid");
		}else if(checkCompanyNameAndRegiNo(companyName, registeredNo)) {
			errors.rejectValue("registeredNo", "registeredNo.isExist");
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
	
	private boolean checkCompanyNameAndRegiNo(String companyName,String registerNO) {
		EnterpriseAccountModel user = (EnterpriseAccountModel)userService.getCurrentUser();
		user.setCompanyName(companyName);
		user.setRegisteredNo(Long.valueOf(registerNO));
		return customerAccountService.existCompanyNameAndRegiNo(user);
	}
	
	private boolean checkAccountNumber(String bank,String accountNumber) {
		
		//TODO call the bank interface to validate whether this is valid
		return true;
	}
	
}
