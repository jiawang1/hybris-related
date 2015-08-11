package com.tasly.anguo.storefront.controllers.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tasly.anguo.core.enums.CustomerStatus;
import com.tasly.anguo.core.enums.UserType;
import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.core.model.PersonalAccountModel;
import com.tasly.anguo.facades.constants.AnguoFacadesConstants;
import com.tasly.anguo.storefront.controllers.ControllerConstants;
import com.tasly.anguo.storefront.forms.AccountIdentifyForm;
import com.tasly.anguo.storefront.forms.EnterpriseIdentifyForm;
import com.tasly.anguo.storefront.forms.PersonalIdentifyForm;
import com.tasly.anguo.storefront.forms.validation.AccountNumberValidator;
import com.tasly.anguo.storefront.forms.validation.EnterpriseAccountValidator;
import com.tasly.anguo.storefront.forms.validation.IdCardValidator;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.media.MediaService;
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
	private EnterpriseAccountValidator enterpriseAccountValidator;
	@Resource
	public UserService userService;
	@Resource(name = "modelService")
	private ModelService modelService;
	@Resource(name = "mediaService")
	private MediaService mediaService;
	@Resource
	private CMSSiteService cmsSiteService;
	
	/**
	 * customer identify page
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String customerIdentify(final Model model) throws CMSItemNotFoundException
	{
		String userType = sessionService.getCurrentSession().getAttribute("userType");
		if(userType.equals(UserType.PERSONAL.toString())) {
			model.addAttribute(new PersonalIdentifyForm());
			return ControllerConstants.Views.Pages.CustomerIdentify.IdCardIdentify;
		}else {
			model.addAttribute(new EnterpriseIdentifyForm());
			return ControllerConstants.Views.Pages.CustomerIdentify.EnterpriseIdentify;
		}
	}
	
	/**
	 * idCard identify page
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value="/identifyIdCard", method = RequestMethod.POST)
	@RequireHardLogIn
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
		if(userType.equals(UserType.PERSONAL.toString())) {
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
	@RequireHardLogIn
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
		if(userType.equals(UserType.PERSONAL.toString())) {
			PersonalAccountModel user = (PersonalAccountModel)userService.getCurrentUser();
			user.setPaymentInfos(getPaymentInfo(form));
			user.setStatus(CustomerStatus.PASS);
			modelService.save(user);
		}
		//TODO
		return ControllerConstants.Views.Pages.CustomerIdentify.AccountNumberIdentify;
	}

	/**
	 * enterprise info identify page
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value="identifyEnterprise", method = RequestMethod.POST)
	@RequireHardLogIn
	public String identifyEnterprise(final EnterpriseIdentifyForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response,final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		enterpriseAccountValidator.validate(form, bindingResult);
		setMediaUrls(form);
		if (bindingResult.hasErrors())
		{
			model.addAttribute(form);
			GlobalMessages.addErrorMessage(model, "form.global.error");
			return ControllerConstants.Views.Pages.CustomerIdentify.EnterpriseIdentify;
		}
		updateEnterpriseAccountInfo(form);
	    return ControllerConstants.Views.Pages.CustomerIdentify.EnterpriseIdentifySuccess;
	}
	
	private void updateEnterpriseAccountInfo(final EnterpriseIdentifyForm form) {
		EnterpriseAccountModel user = (EnterpriseAccountModel)userService.getCurrentUser();
		//if it's not the first time to identify,then remove all the old license pictures 
		if(user.getStatus().toString().equals(CustomerStatus.ABJECT.toString())) {
			Set<MediaModel> medias = user.getMedias();
			modelService.removeAll(medias);
			modelService.removeAll(user.getPaymentInfos());
		}
		user.setStatus(CustomerStatus.AUDITING);
		user.setCompanyName(form.getCompanyName());
		user.setRegisteredNo(Long.valueOf(form.getRegisteredNo()));
		user.setPaymentInfos(getPaymentInfo(form));
		user.setMedias(getLicenses(form));
		modelService.save(user);
		modelService.refresh(user);
	}
	
	private void setMediaUrls(final EnterpriseIdentifyForm form) {
		String[] licenses = form.getLicenses();
		Map<String, String> licenseUrls = new HashMap<String, String>();
		for (String code : licenses) {
			MediaModel media = mediaService.getMedia(code); 
			licenseUrls.put(media.getURL(),media.getAltText());
		}
		form.setLicenseUrls(licenseUrls);
	}
	
	private Set<MediaModel> getLicenses(final EnterpriseIdentifyForm form) {
		Set<MediaModel> medias = new HashSet<MediaModel>();
		String[] licenses = form.getLicenses();
		for (String code : licenses) {
			MediaModel media = mediaService.getMedia(code); 
			medias.add(media);
		}
		return medias;
	}
	
	private Collection<PaymentInfoModel> getPaymentInfo(final AccountIdentifyForm form) {
		DebitPaymentInfoModel paymentInfo =  modelService.create(DebitPaymentInfoModel.class);
		String userType = sessionService.getCurrentSession().getAttribute("userType");
		if(userType.equals(UserType.PERSONAL.toString())) {
			PersonalIdentifyForm personalForm = (PersonalIdentifyForm)form;
			paymentInfo.setBaOwner(personalForm.getAccountOwer());
		}else {
			EnterpriseIdentifyForm personalForm = (EnterpriseIdentifyForm)form;
			paymentInfo.setBaOwner(personalForm.getCompanyName());
		}
		paymentInfo.setBank(form.getBank());
		paymentInfo.setUser(userService.getCurrentUser());
		paymentInfo.setAccountNumber(form.getAccountNumber());
		paymentInfo.setCode(UUID.randomUUID().toString());
		paymentInfo.setBankIDNumber(RandomStringUtils.randomNumeric(16));
		Collection<PaymentInfoModel> paymentInfos = new ArrayList<PaymentInfoModel>();
		paymentInfos.add(paymentInfo);
		return paymentInfos;
	}
	
}
