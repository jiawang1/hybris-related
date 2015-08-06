package com.tasly.anguo.facades.product.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.tasly.anguo.core.enums.ProductStatus;
import com.tasly.anguo.core.product.AnguoProductService;
import com.tasly.anguo.facades.product.AnguoProductFacade;
import com.tasly.anguo.facades.product.populator.ProductListPopulator;
import com.tasly.anguo.store.data.ProductListData;

import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

public class DefaultAnguoProductFacade extends DefaultProductFacade implements AnguoProductFacade{

	private AnguoProductService anguoProductService;
	private ProductListPopulator productListPopulator;

	@Override
	public ProductListData getProductList(String storeName, String productCode,
			String productName, String productStatus,PageableData pageableData)
	{
		ProductStatus status = ProductStatus.valueOf(productStatus);
		SearchPageData searchPageData =  this.anguoProductService.getProductList(productCode, productName, storeName, status, pageableData);
		ProductListData resultList = new ProductListData();
		
		productListPopulator.populate(searchPageData, resultList);
		
		return resultList;
	}

	@Required
	public void setAnguoProductService(AnguoProductService anguoProductService)
	{
		this.anguoProductService = anguoProductService;
	}

	@Required
	public void setProductListPopulator(ProductListPopulator productListPopulator)
	{
		this.productListPopulator = productListPopulator;
	}
	
}
