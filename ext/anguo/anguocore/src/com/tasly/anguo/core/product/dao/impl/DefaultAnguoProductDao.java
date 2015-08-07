package com.tasly.anguo.core.product.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.product.dao.AnguoProductDao;

import de.hybris.platform.category.constants.CategoryConstants;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.impl.DefaultProductDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

/**
 * @author Jack
 *
 */
public class DefaultAnguoProductDao  extends DefaultProductDao implements AnguoProductDao{
    
	private Logger LOG = Logger.getLogger(DefaultAnguoProductDao.class);
	
	private  PagedFlexibleSearchService pagedFlexibleSearchService;
	
	public DefaultAnguoProductDao(String typecode) 
	{
		super(typecode);
		// TODO Auto-generated constructor stub
	}

	
	/* 
	 * Query:
	 * SELECT {p.code} FROM {Product AS p JOIN AnguoStore AS s ON {p.anguoStore} = {s.PK}}
	 *  WHERE {p.name} LIKE '%%' AND {p.code} LIKE '%%' AND {s.name} LIKE '%%'
	 */
	@Override
	public SearchPageData findProductList(String productCode,
			String productName, String storeName,String productStatus,PageableData pageableData) 
	{
		
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT {p:").append(ProductModel.PK).append("} ");
		stringBuilder.append("FROM {").append(ProductModel._TYPECODE).append(" AS p ");
		stringBuilder.append("JOIN ").append(AnguoStoreModel._TYPECODE).append(" AS s ");
		stringBuilder.append("ON {p:anguoStore}={s:PK} } ");
		stringBuilder.append("WHERE {p.code} LIKE CONCAT('%',CONCAT(?productCode,'%')) ")
		.append(" AND {p.name} LIKE CONCAT('%',CONCAT(?productName,'%'))")
		.append(" AND {s.name} LIKE CONCAT('%',CONCAT(?storeName,'%'))")
		.append(" AND {p.productStatus} = ?productStatus");
//		stringBuilder.append("WHERE {p.code} LIKE '%").append(productCode)
//		.append("%' AND {p.name} LIKE '%").append(productName)
//		.append("%' AND {s.name} LIKE '%").append(storeName).append("%'");
		
		Map params = new HashMap();
		params.put("productCode", productCode);
		params.put("productName", productName);
		params.put("storeName", storeName);
		params.put("productStatus", productStatus);
		
		LOG.debug(stringBuilder.toString());
		FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(stringBuilder.toString());
		
		SearchPageData searchResult =  pagedFlexibleSearchService.search(stringBuilder.toString(),params,pageableData);
		
		return searchResult;
	}


	public void setPagedFlexibleSearchService(
			PagedFlexibleSearchService pagedFlexibleSearchService) 
	{
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}

}
