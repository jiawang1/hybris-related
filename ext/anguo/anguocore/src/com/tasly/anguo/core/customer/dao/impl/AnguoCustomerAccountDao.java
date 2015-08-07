package com.tasly.anguo.core.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.core.model.EnterpriseAccountModel;

import de.hybris.platform.commerceservices.customer.dao.impl.DefaultCustomerAccountDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;

public class AnguoCustomerAccountDao extends DefaultCustomerAccountDao {
    public List<ContactModel> findContactsByEnterpriseAccount(EnterpriseAccountModel enterpriseAccount)
    {
        ServicesUtil.validateParameterNotNull(enterpriseAccount, "Customer must not be null");
        Map queryParams = new HashMap();
        queryParams.put("enterpriseAccount", enterpriseAccount);
        String query;
        query = "select {pk} FROM {Contact} WHERE {EnterpriseAccount} = ?enterpriseAccount";// AND {versionID} IS NULL AND {store} = ?store AND {status} IN (?statusList)";
        @SuppressWarnings("unchecked")
        SearchResult<ContactModel> result = getFlexibleSearchService().<ContactModel>search(query, queryParams);
        return result.getResult();
    }
    
    public boolean isRegisterIdDuplicated(EnterpriseAccountModel enterpriseAccount)
    {
        ServicesUtil.validateParameterNotNull(enterpriseAccount, "Customer must not be null");
        Map queryParams = new HashMap();
        queryParams.put("registerNo", enterpriseAccount.getRegisteredNo());
        queryParams.put("uid", enterpriseAccount.getUid());
        String query;
        query = "select {pk} FROM {EnterpriseAccount} WHERE {registeredNo} = ?registerNo and {uid}!=?uid";// AND {versionID} IS NULL AND {store} = ?store AND {status} IN (?statusList)";
        @SuppressWarnings("unchecked")
        SearchResult<ContactModel> result = getFlexibleSearchService().<ContactModel>search(query, queryParams);
        return result.getResult().size() > 0;
    }
}
