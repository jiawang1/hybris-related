/**
 * 
 */
package com.tasly.anguo.core.category;

import java.util.List;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;

/**
 * @author i319019
 *
 */
public interface AnguoCategoryService extends CategoryService {

	public List<CategoryModel> getCategoriesByKeyword(String keyword, int totalCount);
}
