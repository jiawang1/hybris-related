package com.tasly.anguo.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.customer.dao.impl.AnguoCustomerAccountDao;
import com.tasly.anguo.core.exceptions.DuplicateEnterpriseRegisterIdException;
import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.core.service.IAnguoCustomerAccountService;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;

public class AnguoCustomerAccountService extends DefaultCustomerAccountService implements IAnguoCustomerAccountService {
	
	
    public void saveEnterpriseAccount(EnterpriseAccountModel enterpriseAccount) throws DuplicateUidException, DuplicateEnterpriseRegisterIdException {
        AnguoCustomerAccountDao customerDao  = (AnguoCustomerAccountDao)getCustomerAccountDao();
        if (this.existCompanyNameAndRegiNo(enterpriseAccount))
        {
            throw new DuplicateEnterpriseRegisterIdException();
        }
        List<ContactModel> dbContacts = new ArrayList<ContactModel>(customerDao.findContactsByEnterpriseAccount(enterpriseAccount));
        getModelService().removeAll(dbContacts);
        dbContacts.clear();
//        dbContacts.addAll(enterpriseAccount.getContacts());
//        getModelService().saveAll(dbContacts);
        getModelService().save(enterpriseAccount);
        getModelService().refresh(enterpriseAccount);
    }

	@Override
	public boolean existCompanyNameAndRegiNo(EnterpriseAccountModel enterpriseAccount) {
		AnguoCustomerAccountDao customerDao  = (AnguoCustomerAccountDao)getCustomerAccountDao();
		return customerDao.existCompanyNameAndRegiNo(enterpriseAccount);
	}
    
    
}
