package com.tasly.anguo.facades.customer.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.tasly.anguo.core.enums.UserType;
import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.core.model.PersonalAccountModel;
import com.tasly.anguo.facades.populators.EnterpriseInformationData2ModelPopulator;
import com.tasly.anguo.facades.user.data.EnterpriseInformationData;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.enums.PhoneContactInfoType;
import de.hybris.platform.core.model.user.AbstractContactInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.PhoneContactInfoModel;

public class AnguoCustomerFacade extends DefaultCustomerFacade

{
    private static final Logger LOG = Logger
            .getLogger(AnguoCustomerFacade.class);

    @Override
    public void register(final RegisterData registerData)
            throws DuplicateUidException
    {
        validateParameterNotNullStandardMessage("registerData", registerData);
        Assert.hasText(registerData.getLogin(),
                "The field [Login] cannot be empty");
        CustomerModel newCustomer = null;
        if (registerData.getUserType() == UserType.PERSONAL) {
            newCustomer = getModelService().create(PersonalAccountModel.class);
            newCustomer.setIdentified(true);
        } else if (registerData.getUserType() == UserType.ENTERPRISE) {
            newCustomer = getModelService()
                    .create(EnterpriseAccountModel.class);
            newCustomer.setIdentified(false);
        }
        newCustomer.setName(registerData.getLogin());
        setUidForRegister(registerData, newCustomer);
        newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
        newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
        PhoneContactInfoModel phoneModel = new PhoneContactInfoModel();
        phoneModel.setType(PhoneContactInfoType.MOBILE);
        phoneModel.setPhoneNumber(registerData.getMobile());
        phoneModel.setUser(newCustomer);
        newCustomer.setContactInfos(new ArrayList<AbstractContactInfoModel>());
        newCustomer.getContactInfos().add(phoneModel);
        getCustomerAccountService().register(newCustomer,
                registerData.getPassword());

    }
    
    public void updateEnterpriseInformation(
            final EnterpriseInformationData enterpriseInformationData) {
//        validateDataBeforeUpdate(customerData);
//
//        final String name = getCustomerNameStrategy().getName(
//                customerData.getFirstName(), customerData.getLastName());
        final CustomerModel customer = getCurrentSessionCustomer();
        if (customer instanceof EnterpriseAccountModel) {
            EnterpriseAccountModel eam = (EnterpriseAccountModel) customer;
            EnterpriseInformationData2ModelPopulator populator = new EnterpriseInformationData2ModelPopulator();
            populator.populate(enterpriseInformationData, eam);
//            getCustomerAccountService().updateProfile(customer,
//                  customerData.getTitleCode(), name, customerData.getUid());
        }
//        customer.setOriginalUid(customerData.getDisplayUid());
//        getCustomerAccountService().updateProfile(customer,
//                customerData.getTitleCode(), name, customerData.getUid());
    }

}