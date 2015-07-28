package com.tasly.anguo.facades.populators;

import org.fest.util.Collections;

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
		//to decide if there is expand button before every node
		target.setChildren(source.getCategories().size()>0?Boolean.TRUE:Boolean.FALSE);
	}

}
