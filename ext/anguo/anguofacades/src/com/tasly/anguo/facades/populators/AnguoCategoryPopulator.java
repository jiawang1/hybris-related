/**
 * 
 */
package com.tasly.anguo.facades.populators;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;

import com.tasly.anguo.facades.product.data.CategoryData;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.converters.populator.CategoryPopulator;

/**
 * @author i319019
 *
 */
public class AnguoCategoryPopulator extends CategoryPopulator {

	public void populate(CategoryModel source, CategoryData target)
	{
		super.populate(source, target);
		StringBuilder catPath = new StringBuilder(source.getName());
		if (CollectionUtils.isNotEmpty(source.getSupercategories()))
		{
			CategoryModel catParent = source.getSupercategories().get(0);
			catPath.insert(0, catParent.getName() + StringEscapeUtils.escapeHtml(" >> "));
			if (CollectionUtils.isNotEmpty(catParent.getSupercategories()))
			{
				CategoryModel catGrandparent = catParent.getSupercategories().get(0);
				catPath.insert(0, catGrandparent.getName() + StringEscapeUtils.escapeHtml(" >> "));
			}
		}
		target.setCategoryPath(catPath.toString());
	}
}
