package com.tasly.anguo.storefront.controllers.pages;

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

import com.tasly.anguo.core.enums.UserType;
import com.tasly.anguo.storefront.controllers.ControllerConstants;
import com.tasly.anguo.storefront.forms.PersonalIdentifyForm;
import com.tasly.anguo.storefront.forms.validation.AccountNumberValidator;
import com.tasly.anguo.storefront.forms.validation.IdCardValidator;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.session.SessionService;

/**
 * Store apply page
 * @author i313514
 *
 */
@Controller
@Scope("tenant")
@RequestMapping("/identify")
public class CustomerIdentifyController extends AbstractSearchPageController {

	@Resource
	SessionService sessionService;
	
	@Resource
	IdCardValidator idCardValidator;
	
	@Resource
	AccountNumberValidator accountNumberValidator;
	
	
	/**
	 * store apply page
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
		return FORWARD_PREFIX+"/identify";
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
		idCardValidator.validate(form, bindingResult);
		if (bindingResult.hasErrors())
		{
			model.addAttribute(form);
			GlobalMessages.addErrorMessage(model, "form.global.error");
			return ControllerConstants.Views.Pages.CustomerIdentify.AccountNumberIdentify;
		}
		return FORWARD_PREFIX+"/identify";
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
