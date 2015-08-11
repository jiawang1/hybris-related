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
    
    /**
     * check if there's a customer with the same companyName
     * 
     * @param companyName
     * @return 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean existCompanyNameAndRegiNo(EnterpriseAccountModel enterpriseAccount)
    {
        ServicesUtil.validateParameterNotNull(enterpriseAccount, "enterpriseAccount must not be null");
        Map queryParams = new HashMap();
        queryParams.put("companyName", enterpriseAccount.getCompanyName());
        queryParams.put("registerNo", enterpriseAccount.getRegisteredNo());
        queryParams.put("uid", enterpriseAccount.getUid());
        String query = "select {pk} FROM {EnterpriseAccount} WHERE {companyName} = ?companyName and {registeredNo} = ?registerNo and {uid}!=?uid";
        SearchResult<ContactModel> result = getFlexibleSearchService().<ContactModel>search(query, queryParams);
        return result.getResult().size() > 0;
    }
}
