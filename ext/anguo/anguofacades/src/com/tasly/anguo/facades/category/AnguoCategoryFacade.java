package com.tasly.anguo.facades.category;

import java.util.List;

import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;

import de.hybris.platform.commercefacades.product.data.CategoryData;

/**
 * @author Jack
 *
 */
public interface AnguoCategoryFacade{
	
	/**
	 * return only son not grandson
	 * @param categoryCode
	 * @return first level sub category node list
	 */
	public List<CategoryNodeData> getSubCategoryByCode(String categoryCode);
	/**
	 * 
	 * @param categoryCode
	 * @return category data,contains category name,category code,category alias
	 */
	public MgmtCategoryData getCategoryDetail(String categoryCode);
	
	/**
	 * delete category by code,not delete sub category
	 * @param categoryCode
	 */
	public void deleteCategory(String categoryCode);
	
	/**
	 * @param categoryCode
	 */
	public void saveCategory(MgmtCategoryData categoryData);
}
