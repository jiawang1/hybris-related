package com.tasly.anguo.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.customer.dao.impl.AnguoCustomerAccountDao;
import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.core.model.EnterpriseAccountModel;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;

public class AnguoCustomerAccountService extends DefaultCustomerAccountService {
    public void saveEnterpriseAccount(EnterpriseAccountModel enterpriseAccount) throws DuplicateUidException {
        AnguoCustomerAccountDao dao  = (AnguoCustomerAccountDao)getCustomerAccountDao();
        List<ContactModel> dbContacts = new ArrayList<ContactModel>(dao.findContactsByEnterpriseAccount(enterpriseAccount));
        dbContacts.clear();
        dbContacts.addAll(enterpriseAccount.getContacts());
        getModelService().saveAll(dbContacts);
        getModelService().save(enterpriseAccount);
        getModelService().refresh(enterpriseAccount);
    }
}
