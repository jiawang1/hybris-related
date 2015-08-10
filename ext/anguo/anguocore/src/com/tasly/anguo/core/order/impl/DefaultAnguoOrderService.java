package com.tasly.anguo.core.order.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.tasly.anguo.core.order.AnguoOrderService;
import com.tasly.anguo.core.order.dao.AnguoOrderDao;
import com.tasly.anguo.order.data.AnguoOrderStatusCountData;

import de.hybris.platform.core.enums.OrderStatus;

/**
 * @author i313919, Yang
 * @since 2015-08-06 16:48:17
 *
 */
public class DefaultAnguoOrderService implements AnguoOrderService
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultAnguoOrderService.class);

	private AnguoOrderDao AnguoOrderDao;

	@Override
	public AnguoOrderStatusCountData getStatusCount()
	{
		List<List<?>> list = getAnguoOrderDao().getStatusCount();

		Map<String, Long> map = new HashMap<String, Long>();

		for (List<?> resultList : list)
		{
			OrderStatus status = (OrderStatus) resultList.get(0);
			Long count = (Long) resultList.get(1);

			map.put(status.toString().toLowerCase(), count);
		}

		AnguoOrderStatusCountData data = (AnguoOrderStatusCountData) BeanUtils.instantiateClass(AnguoOrderStatusCountData.class); 
		BeanWrapper beanWrapper = new BeanWrapperImpl(data); 
		beanWrapper.setPropertyValues(map); 

		return data;
	}

	@SuppressWarnings("javadoc")
	public AnguoOrderDao getAnguoOrderDao()
	{
		return AnguoOrderDao;
	}

	@SuppressWarnings("javadoc")
	public void setAnguoOrderDao(AnguoOrderDao anguoOrderDao)
	{
		AnguoOrderDao = anguoOrderDao;
	}

}
