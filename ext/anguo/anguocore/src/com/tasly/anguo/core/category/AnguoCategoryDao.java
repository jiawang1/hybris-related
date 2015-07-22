package com.tasly.anguo.core.category;

import de.hybris.platform.category.daos.CategoryDao;
import de.hybris.platform.category.model.CategoryModel;


public interface AnguoCategoryDao extends CategoryDao{
	public void getSubCategories(CategoryModel categoryModel);
}
