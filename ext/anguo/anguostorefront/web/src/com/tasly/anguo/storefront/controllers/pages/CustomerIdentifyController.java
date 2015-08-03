package com.tasly.anguo.storefront.controllers.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tasly.anguo.core.constants.GeneratedAnguoCoreConstants.Attributes.DebitPaymentInfo;
import com.tasly.anguo.core.enums.UserType;
import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.core.model.PersonalAccountModel;
import com.tasly.anguo.storefront.controllers.ControllerConstants;
import com.tasly.anguo.storefront.forms.PersonalIdentifyForm;
import com.tasly.anguo.storefront.forms.validation.AccountNumberValidator;
import com.tasly.anguo.storefront.forms.validation.IdCardValidator;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

/**
 * identify customer page
 * @author i313514
 *
 */
@Controller
@Scope("tenant")
@RequestMapping("/identify")
public class CustomerIdentifyController extends AbstractSearchPageController {

	@Resource
	private SessionService sessionService;
	@Resource
	private IdCardValidator idCardValidator;
	@Resource
	private AccountNumberValidator accountNumberValidator;
	@Resource
	public UserService userService;
	@Resource(name = "modelService")
	private ModelService modelService;
	
	/**
	 * customer identify page
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String customerIdentify(final Model model) throws CMSItemNotFoundException
	{
		String userType = sessionService.getCurrentSession().getAttribute("userType");
		if(userType.equals(UserType.PERSONAL.toString())) {
			model.addAttribute(new PersonalIdentifyForm());
			return ControllerConstants.Views.Pages.CustomerIdentify.IdCardIdentify;
		}else {
			return "";
		}
	}
	
	/**
	 * idCard identify page
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value="/identifyIdCard", method = RequestMethod.POST)
	public String identifyIdCard(final PersonalIdentifyForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response,final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		idCardValidator.validate(form, bindingResult);
		if (bindingResult.hasErrors())
		{
			model.addAttribute(form);
			GlobalMessages.addErrorMessage(model, "form.global.error");
			return ControllerConstants.Views.Pages.CustomerIdentify.IdCardIdentify;
		}
		String userType = sessionService.getAttribute("userType");
		if(userType.equals(UserType.PERSONAL)) {
			PersonalAccountModel user = (PersonalAccountModel)userService.getCurrentUser();
			user.setIdCard(form.getIdCard());
			user.setIdName(form.getIdName());
			modelService.save(user);
		}
		
		return ControllerConstants.Views.Pages.CustomerIdentify.AccountNumberIdentify;
	}
	
	/**
	 * accountNumber identify page
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value="/identifyAccountNumber", method = RequestMethod.POST)
	public String identifyAccountNumber(final PersonalIdentifyForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response,final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		accountNumberValidator.validate(form, bindingResult);
		if (bindingResult.hasErrors())
		{
			model.addAttribute(form);
			GlobalMessages.addErrorMessage(model, "form.global.error");
			return ControllerConstants.Views.Pages.CustomerIdentify.AccountNumberIdentify;
		}
		
		String userType = sessionService.getAttribute("userType");
		if(userType.equals(UserType.PERSONAL)) {
			PersonalAccountModel user = (PersonalAccountModel)userService.getCurrentUser();
			DebitPaymentInfoModel paymentInfo =  modelService.create(DebitPaymentInfoModel.class);
			paymentInfo.setBank(form.getBank());
			paymentInfo.setAccountNumber(form.getAccountNumber());
			paymentInfo.setBaOwner(form.getAccountOwer());
			Collection<PaymentInfoModel> paymentInfos = new ArrayList<PaymentInfoModel>();
			paymentInfos.add(paymentInfo);
			user.setPaymentInfos(paymentInfos);
			modelService.save(user);
		}
		return ControllerConstants.Views.Pages.CustomerIdentify.AccountNumberIdentify;
	}
	
	/**
	 * enterprise info identify page
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value="enterpriseIdentify", method = RequestMethod.POST)
	public String enterpriseIdentify(final Model model) throws CMSItemNotFoundException
	{
		
		return "";
	}
	
	
	
}
