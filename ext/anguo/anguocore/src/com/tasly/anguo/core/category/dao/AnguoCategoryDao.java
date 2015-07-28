/**
 * 
 */
package com.tasly.anguo.core.category.dao;

import java.util.List;

import de.hybris.platform.category.daos.CategoryDao;
import de.hybris.platform.category.model.CategoryModel;

/**
 * @author i319019
 *
 */
public interface AnguoCategoryDao extends CategoryDao {

	/**
	 * Search category by keyword
	 * 
	 * @param keyword
	 * @param totalCount
	 * @return
	 */
	public List<CategoryModel> findCategoriesByKeyword(String keyword, int totalCount);
}
