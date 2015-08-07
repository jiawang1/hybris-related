package com.tasly.anguo.facades.order.impl;

import com.tasly.anguo.core.order.AnguoOrderService;
import com.tasly.anguo.facades.order.AnguoOrderFacade;
import com.tasly.anguo.order.data.AnguoOrderStatusCountData;

import de.hybris.platform.commercefacades.order.impl.DefaultOrderFacade;


/**
 * @author i313919, Yang
 * @since 2015-08-06 14:40:20
 * 
 */
public class DefaultAnguoOrderFacade extends DefaultOrderFacade implements AnguoOrderFacade
{

	private AnguoOrderService anguoOrderService;

	@Override
	public AnguoOrderStatusCountData getStatusCount()
	{

		return getAnguoOrderService().getStatusCount();
	}

	@SuppressWarnings("javadoc")
	public AnguoOrderService getAnguoOrderService()
	{
		return anguoOrderService;
	}

	@SuppressWarnings("javadoc")
	public void setAnguoOrderService(AnguoOrderService anguoOrderService)
	{
		this.anguoOrderService = anguoOrderService;
	}

}
