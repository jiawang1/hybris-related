package com.tasly.anguo.facades.order;

import com.tasly.anguo.order.data.AnguoOrderStatusCountData;

import de.hybris.platform.commercefacades.order.OrderFacade;

/**
 * @author i313919, Yang
 * @since 2015-08-06 14:29:21
 *
 */
public interface AnguoOrderFacade extends OrderFacade
{
	/**
	 * @return AnguoOrderStatusCountData
	 */
	public AnguoOrderStatusCountData getStatusCount();
}
