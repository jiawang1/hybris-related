package com.tasly.anguo.core.order;

import com.tasly.anguo.order.data.AnguoOrderStatusCountData;

/**
 * @author i313919, Yang
 * @since 2015-08-06 15:02:03
 *
 */
public interface AnguoOrderService
{
	/**
	 * @return AnguoOrderStatusCountData
	 */
	public AnguoOrderStatusCountData getStatusCount();
}
