/**
 * 
 */
package com.tasly.anguo.facades.populators;

import org.springframework.util.Assert;

import com.tasly.anguo.facades.data.DistrictData;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;
import de.hybris.platform.commerceservices.store.data.GeoPoint;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author i319019
 *
 */
public class DistrictPopulator implements Populator<DistrictModel, DistrictData> {
	@Override
	public void populate(final DistrictModel source, final DistrictData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		//target.setName(source.getName(Locale.ENGLISH)); //
		target.setName(source.getName());
		target.setCode(source.getCode());

		// from ChinaStoreLocatorFacadeImpl
		target.setDistrictName(source.getName());
		target.setDistrictPK(source.getPk().getLong());
		final GeoPoint point = new GeoPoint();
		point.setLatitude(source.getLatitude() != null ? source.getLatitude().doubleValue() : 0d);
		point.setLongitude(source.getLongitude() != null ? source.getLongitude().doubleValue() : 0d);
		target.setGeoPoint(point);
	}
}
