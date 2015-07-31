package com.tasly.anguo.storefront.controllers.pages;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tasly.anguo.core.enums.UserType;
import com.tasly.anguo.storefront.controllers.ControllerConstants;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
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
			return ControllerConstants.Views.Pages.StoreApply.StoreApplyPage;
		}else {
			return "";
		}
	}
	
	/**
	 * personal info identify page
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value="personalIdentify", method = RequestMethod.POST)
	public String personalIdentify(final Model model) throws CMSItemNotFoundException
	{
		
		return "";
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
