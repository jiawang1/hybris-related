/**
 * 
 */
package com.tasly.anguo.facades.populators;

import org.springframework.util.Assert;

import com.tasly.anguo.facades.product.data.UnitData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author i319019
 *
 */
public class UnitPopulator implements Populator<UnitModel, UnitData> {

	@Override
	public void populate(UnitModel source, UnitData target)
			throws ConversionException {
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		target.setCode(source.getCode());
		target.setConversion(source.getConversion());
		target.setName(source.getName());
		target.setUnitType(source.getUnitType());
	}

}
