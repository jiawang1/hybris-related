package com.tasly.anguo.core.order.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.tasly.anguo.core.order.dao.AnguoOrderDao;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

/**
 * @author i313919, Yang
 * @since 2015-08-06 15:06:11
 *
 */
public class DefaultAnguoOrderDao extends AbstractItemDao implements AnguoOrderDao
{
	
	private static final String FIND_ORDER_COUNT_BY_STATUS_QUERY ="select {o:" + OrderModel.STATUS + "} as status,count(" + OrderModel.PK + ") as count from {order as o} group by {o:" + OrderModel.STATUS + "}";
	
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultAnguoOrderDao.class);

	@Override
	public List<List<?>> getStatusCount()
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_ORDER_COUNT_BY_STATUS_QUERY);
		query.setResultClassList(Arrays.asList(OrderStatus.class, Long.class));
		final SearchResult<List<?>> result = getFlexibleSearchService().search(query);
		
		return result.getResult();
	}

}
