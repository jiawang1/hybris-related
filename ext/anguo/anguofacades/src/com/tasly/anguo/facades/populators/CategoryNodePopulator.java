package com.tasly.anguo.facades.populators;

import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.GenderData;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CategoryNodePopulator implements Populator<CategoryModel, CategoryNodeData> {

	@Override
	public void populate(CategoryModel source, CategoryNodeData target)
			throws ConversionException {
		target.setId(source.getCode());
		target.setText(source.getName());
		target.setChildren(Boolean.TRUE);
	}

}
