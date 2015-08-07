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

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tasly.anguo.facades.order.AnguoOrderFacade;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;


/**
 * @author i313919, Yang
 * @since 2015-08-06 10:19:34
 * 
 */
@Controller
@Scope("tenant")
@RequestMapping("/anguo-buyercenter")
public class AnguoBuyerCenterController extends AbstractSearchPageController
{
	// CMS Pages
	private static final String BUYERCENTER_CMS_PAGE = "buyercenter";
	private static final String ORDERMANAGE_CMS_PAGE = "buyercenterordermanage";

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(AnguoBuyerCenterController.class);

	@Resource(name = "userFacade")
	protected UserFacade userFacade;

	@Resource(name = "customerFacade")
	protected CustomerFacade customerFacade;

	@Resource(name = "buyerCenterBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder buyerCenterBreadcrumbBuilder;

	@Resource(name = "anguoOrderFacade")
	private AnguoOrderFacade anguoOrderFacade;

	@SuppressWarnings("javadoc")
	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String account(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(BUYERCENTER_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(BUYERCENTER_CMS_PAGE));
		model.addAttribute("breadcrumbs", buyerCenterBreadcrumbBuilder.getBreadcrumbs(null));
		model.addAttribute("metaRobots", "noindex,nofollow");
		return getViewForPage(model);
	}

	@SuppressWarnings("javadoc")
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@RequireHardLogIn
	public String orders(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode, final Model model, final String status) throws CMSItemNotFoundException
	{
		final PageableData pageableData = createPageableData(page, 5, sortCode, showMode);
		final SearchPageData<OrderHistoryData> searchPageData = status == null ? anguoOrderFacade
				.getPagedOrderHistoryForStatuses(pageableData) : anguoOrderFacade.getPagedOrderHistoryForStatuses(pageableData, OrderStatus.valueOf(status));
		
				
		populateModel(model, searchPageData, showMode);
		
		model.addAttribute("anguoOrderStatusCountData", anguoOrderFacade.getStatusCount());

		storeCmsPageInModel(model, getContentPageForLabelOrId(ORDERMANAGE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ORDERMANAGE_CMS_PAGE));
		model.addAttribute("breadcrumbs", buyerCenterBreadcrumbBuilder.getBreadcrumbs("text.buyercenter.ordermanage"));
		model.addAttribute("metaRobots", "noindex,nofollow");
		return getViewForPage(model);
	}

}
