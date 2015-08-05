/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.tasly.anguo.storefront.controllers.pages;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.TitleData;


/**
 * Controller for home page
 */
@Controller
@Scope("tenant")
@RequestMapping("/anguo-buyercenter")
public class AnguoBuyerCenterController extends AbstractSearchPageController
{

	// CMS Pages
	private static final String BUYERCENTER_CMS_PAGE = "buyercenter";
	private static final String ORDERMANAGE_CMS_PAGE = "buyercenterordermanage";

	private static final Logger LOG = Logger.getLogger(AnguoBuyerCenterController.class);

	@Resource(name = "userFacade")
	protected UserFacade userFacade;
	
	@Resource(name = "customerFacade")
	protected CustomerFacade customerFacade;
	
	@Resource(name = "buyerCenterBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder buyerCenterBreadcrumbBuilder;


	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String account(final Model model) throws CMSItemNotFoundException
	{
		LOG.info("进入买家中心页面。");
		storeCmsPageInModel(model, getContentPageForLabelOrId(BUYERCENTER_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(BUYERCENTER_CMS_PAGE));
		model.addAttribute("breadcrumbs", buyerCenterBreadcrumbBuilder.getBreadcrumbs(null));
		model.addAttribute("metaRobots", "noindex,nofollow");
		return getViewForPage(model);
	}
	
	@RequestMapping(value = "/ordermanage", method = RequestMethod.GET)
	@RequireHardLogIn
	public String profile(final Model model) throws CMSItemNotFoundException
	{
		LOG.info("进入买家中心订单管理页面。");
		final List<TitleData> titles = userFacade.getTitles();

		final CustomerData customerData = customerFacade.getCurrentCustomer();
		if (customerData.getTitleCode() != null)
		{
			model.addAttribute("title", findTitleForCode(titles, customerData.getTitleCode()));
		}

		model.addAttribute("customerData", customerData);

		storeCmsPageInModel(model, getContentPageForLabelOrId(ORDERMANAGE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ORDERMANAGE_CMS_PAGE));
		model.addAttribute("breadcrumbs", buyerCenterBreadcrumbBuilder.getBreadcrumbs("text.buyercenter.ordermanage"));
		model.addAttribute("metaRobots", "noindex,nofollow");
		return getViewForPage(model);
	}
	
	protected TitleData findTitleForCode(final List<TitleData> titles, final String code)
	{
		if (code != null && !code.isEmpty() && titles != null && !titles.isEmpty())
		{
			for (final TitleData title : titles)
			{
				if (code.equals(title.getCode()))
				{
					return title;
				}
			}
		}
		return null;
	}

}
