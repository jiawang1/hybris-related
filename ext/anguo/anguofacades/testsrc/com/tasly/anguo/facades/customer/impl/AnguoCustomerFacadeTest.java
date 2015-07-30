package com.tasly.anguo.facades.customer.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tasly.anguo.core.enums.UserType;
import com.tasly.anguo.core.model.EnterpriseAccountModel;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

@UnitTest
public class AnguoCustomerFacadeTest {
	private AnguoCustomerFacade anguoCustomerFacade;
	@Mock
	private CustomerAccountService customerAccountService;
	@Mock
	private ModelService modelService;
	@Mock
	private CommonI18NService commonI18NService;
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		anguoCustomerFacade = new AnguoCustomerFacade();
		anguoCustomerFacade.setCustomerAccountService(customerAccountService);
		anguoCustomerFacade.setModelService(modelService);
		anguoCustomerFacade.setCommonI18NService(commonI18NService);
	}
	
	@Test
	public void register() throws DuplicateUidException
	{
		CustomerModel newCustomer = new EnterpriseAccountModel();
		Mockito.when(modelService.create(EnterpriseAccountModel.class)).thenReturn(newCustomer);
		RegisterData rd = new RegisterData();
		rd.setLogin("test1");
		rd.setMobile("13331123214");
		rd.setCaptcha("test");
		rd.setPassword("test123");
		rd.setUserType(UserType.ENTERPRISE);
//		Mockito.when(customerAccountService.register(newCustomer,"s"));
		anguoCustomerFacade.register(rd);
	}
}
