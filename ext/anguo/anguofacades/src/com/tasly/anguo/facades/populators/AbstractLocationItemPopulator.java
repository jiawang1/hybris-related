/**
 * 
 */
package com.tasly.anguo.facades.populators;

import org.springframework.util.Assert;

import com.tasly.anguo.facades.data.AbstractLocationItemData;

import de.hybris.platform.chinaaccelerator.services.model.location.AbstractLocationItemModel;
import de.hybris.platform.commerceservices.store.data.GeoPoint;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author i319019
 *
 */
public class AbstractLocationItemPopulator implements Populator<AbstractLocationItemModel, AbstractLocationItemData> {
	@Override
	public void populate(final AbstractLocationItemModel source, final AbstractLocationItemData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());

		// from ChinaStoreLocatorFacadeImpl
		target.setName(source.getName());
		target.setPk(source.getPk().getLong());
		final GeoPoint point = new GeoPoint();
		point.setLatitude(source.getLatitude() != null ? source.getLatitude().doubleValue() : 0d);
		point.setLongitude(source.getLongitude() != null ? source.getLongitude().doubleValue() : 0d);
		target.setGeoPoint(point);
	}
}
