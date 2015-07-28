/**
 * 
 */
package com.tasly.anguo.core.category.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tasly.anguo.core.category.dao.AnguoCategoryDao;
import com.tasly.anguo.core.model.CategoryAliasModel;

import de.hybris.platform.category.daos.impl.DefaultCategoryDao;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

/**
 * @author i319019
 *
 */
public class DefaultAnguoCategoryDao extends DefaultCategoryDao implements AnguoCategoryDao {

	private PagedFlexibleSearchService pagedFlexibleSearchService;
	
	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.category.dao.AnguoCategoryDao#getCategoriesByKeyword(java.lang.String, int)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CategoryModel> getCategoriesByKeyword(String keyword,
			int totalCount) {
//		search query
//		select * from {category as c}
//		where
//		{c:level} = 3 and
//		{c:name[zh]:o} like '%人参%'
//		or
//		exists
//		({{select * from {categoryalias as ca}
//		where 
//		 {ca:category} = {c:pk}
//		 and {ca:alias[zh]:o} like '%人参%'}})
		 
		StringBuilder query = new StringBuilder();
		query.append("SELECT {c:");
		query.append(CategoryModel.PK);
		query.append("} FROM {");
		query.append(CategoryModel._TYPECODE);
		query.append(" as c} WHERE {c:");
		query.append(CategoryModel.LEVEL);
		query.append("} = 3 AND {c:");
		query.append(CategoryModel.NAME);
//		query.append("[zh]:o");
		query.append("} LIKE CONCAT('%',CONCAT(?keyword,'%'))");
		query.append("OR EXISTS ({{SELECT {ca:");
		query.append(CategoryAliasModel.PK);
		query.append("} FROM {");
		query.append(CategoryAliasModel._TYPECODE);
		query.append(" as ca} WHERE {ca:");
		query.append(CategoryAliasModel.CATEGORY);
		query.append("} = {c:");
		query.append(CategoryModel.PK);
		query.append("} AND {ca:");
		query.append(CategoryAliasModel.ALIAS);
//		query.append("[zh]:o");
		query.append("} LIKE CONCAT('%',CONCAT(?keyword,'%'))}})");
		
		Map params = new HashMap();
		params.put("keyword", keyword);
		
		PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(0);
		pageableData.setPageSize(totalCount);

		SearchPageData<CategoryModel> result = getPagedFlexibleSearchService().search(query.toString(), params, pageableData);
		return result.getResults();
	}
	
	/**
	 * @return
	 */
	public PagedFlexibleSearchService getPagedFlexibleSearchService() {
		return pagedFlexibleSearchService;
	}
	
	/**
	 * @param pagedFlexibleSearchService
	 */
	public void setPagedFlexibleSearchService(PagedFlexibleSearchService pagedFlexibleSearchService) {
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}
}
