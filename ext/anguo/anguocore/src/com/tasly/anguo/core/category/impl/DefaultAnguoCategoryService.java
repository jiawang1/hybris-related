/**
 * 
 */
package com.tasly.anguo.core.category.impl;

import java.util.List;

import com.tasly.anguo.core.category.AnguoCategoryService;
import com.tasly.anguo.core.category.dao.AnguoCategoryDao;

import de.hybris.platform.category.impl.DefaultCategoryService;
import de.hybris.platform.category.model.CategoryModel;

/**
 * @author i319019
 *
 */
public class DefaultAnguoCategoryService extends DefaultCategoryService implements AnguoCategoryService {
	
	private AnguoCategoryDao categoryDao;

	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.category.AnguoCategoryService#getCategoriesByKeyword(java.lang.String, int)
	 */
	@Override
	public List<CategoryModel> getCategoriesByKeyword(String keyword,
			int totalCount) {
		return categoryDao.getCategoriesByKeyword(keyword, totalCount);
	}

	/**
	 * @return the categoryDao
	 */
	public AnguoCategoryDao getCategoryDao() {
		return categoryDao;
	}

	/**
	 * @param categoryDao the categoryDao to set
	 */
	public void setCategoryDao(AnguoCategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

}
