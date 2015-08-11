package com.tasly.anguo.core.service;

import com.tasly.anguo.core.model.EnterpriseAccountModel;

public interface IAnguoCustomerAccountService {

	 /**
     * check if there's a customer with the same companyName
     * 
     * @param companyName
     * @return 
     */
	public boolean existCompanyNameAndRegiNo(EnterpriseAccountModel enterpriseAccount);
}
