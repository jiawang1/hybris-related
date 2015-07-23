package com.tasly.anguo.facades.customer.impl;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.enums.PhoneContactInfoType;
import de.hybris.platform.core.model.user.AbstractContactInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.PhoneContactInfoModel;

public class AnguoCustomerFacade extends DefaultCustomerFacade

{
	private static final Logger LOG = Logger.getLogger(AnguoCustomerFacade.class);

	@Override
	public void register(final RegisterData registerData) throws DuplicateUidException

	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		Assert.hasText(registerData.getLogin(),
				"The field [Login] cannot be empty");

		final CustomerModel newCustomer = getModelService().create(
				CustomerModel.class);

		newCustomer.setName(registerData.getLogin());


		setUidForRegister(registerData, newCustomer);

		newCustomer.setSessionLanguage(getCommonI18NService()
				.getCurrentLanguage());

		newCustomer.setSessionCurrency(getCommonI18NService()
				.getCurrentCurrency());

		PhoneContactInfoModel phoneModel = new PhoneContactInfoModel();

		phoneModel.setType(PhoneContactInfoType.MOBILE);

		phoneModel.setPhoneNumber(registerData.getMobile());

		phoneModel.setUser(newCustomer);

		newCustomer.setContactInfos(new ArrayList<AbstractContactInfoModel>());

		newCustomer.getContactInfos().add(phoneModel);

		getCustomerAccountService().register(newCustomer,
				registerData.getPassword());

	}

	
}