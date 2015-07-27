package com.tasly.anguo.facades.category;

import java.util.List;

import com.tasly.anguo.facades.product.data.CategoryData;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;


public interface AnguoCategoryFacade{
	
	/**
	 * 
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
	 * @param keyword
	 * @param totalCount
	 * @return
	 */
	public List<CategoryData> getCategoriesByKeyword(String keyword, int totalCount);
}
