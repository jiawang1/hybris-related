package com.tasly.anguo.facades.customer.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.tasly.anguo.core.enums.UserType;
import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.core.model.PersonalAccountModel;
import com.tasly.anguo.core.service.impl.AnguoCustomerAccountService;
import com.tasly.anguo.facades.populators.EnterpriseInformationPopulator;
import com.tasly.anguo.facades.populators.EnterpriseInformationReversePopulator;
import com.tasly.anguo.facades.user.data.EnterpriseInformationData;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
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
    
    private EnterpriseInformationReversePopulator enterpriseInformationReversePopulator;

    public EnterpriseInformationReversePopulator getEnterpriseInformationReversePopulator() {
        return enterpriseInformationReversePopulator;
    }

    public void setEnterpriseInformationReversePopulator(
            EnterpriseInformationReversePopulator enterpriseInformationReversePopulator) {
        this.enterpriseInformationReversePopulator = enterpriseInformationReversePopulator;
    }

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
    
    public EnterpriseInformationData getEnterpriseInformation() {
        EnterpriseAccountModel eam = (EnterpriseAccountModel)getCurrentSessionCustomer();
        EnterpriseInformationData eid = new EnterpriseInformationData();
        EnterpriseInformationPopulator populator = new EnterpriseInformationPopulator();
        populator.populate(eam, eid);
        return eid;
    }
    
    public void updateEnterpriseInformation(
            final EnterpriseInformationData enterpriseInformationData) throws DuplicateUidException {
//        validateDataBeforeUpdate(customerData);
//
//        final String name = getCustomerNameStrategy().getName(
//                customerData.getFirstName(), customerData.getLastName());
        final CustomerModel customer = getCurrentSessionCustomer();
        if (customer instanceof EnterpriseAccountModel) {
            EnterpriseAccountModel eam = (EnterpriseAccountModel) customer;
            enterpriseInformationReversePopulator.populate(enterpriseInformationData, eam);
//            eam.setUid("seller");
//            eam.setOriginalUid("seller");
           AnguoCustomerAccountService customerAccountService = (AnguoCustomerAccountService)getCustomerAccountService();
           customerAccountService.saveEnterpriseAccount(eam);

        }
//        customer.setOriginalUid(customerData.getDisplayUid());
//        getCustomerAccountService().updateProfile(customer,
//                customerData.getTitleCode(), name, customerData.getUid());
    }

}