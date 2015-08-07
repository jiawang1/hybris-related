package com.tasly.anguo.facades.populators;

import org.springframework.util.Assert;

import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;


/**
 * @author i313919, Yang
 * @since 2015-08-05 14:50:59
 *
 */
public class AnguoOrderHistoryPopulator implements Populator<OrderModel, OrderHistoryData>
{
	private Converter<AbstractOrderEntryModel, OrderEntryData> orderEntryConverter;

	@Override
	public void populate(final OrderModel source, final OrderHistoryData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setEntries(Converters.convertAll(source.getEntries(), getOrderEntryConverter()));
	}

	@SuppressWarnings("javadoc")
	public Converter<AbstractOrderEntryModel, OrderEntryData> getOrderEntryConverter()
	{
		return orderEntryConverter;
	}

	@SuppressWarnings("javadoc")
	public void setOrderEntryConverter(Converter<AbstractOrderEntryModel, OrderEntryData> orderEntryConverter)
	{
		this.orderEntryConverter = orderEntryConverter;
	}

}
