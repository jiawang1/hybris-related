package com.tasly.anguo.core.order.dao;

import java.util.List;

/**
 * @author i313919, Yang
 * @since 2015-08-06 16:49:46
 *
 */
public interface AnguoOrderDao
{
	/**
	 * @return AnguoOrderStatusCountData
	 */
	public List<List<?>> getStatusCount();
}
