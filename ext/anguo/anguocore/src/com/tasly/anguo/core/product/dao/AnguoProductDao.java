package com.tasly.anguo.core.product.dao;

import java.util.List;

import com.tasly.anguo.core.enums.ProductStatus;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.ProductDao;

public interface AnguoProductDao extends ProductDao{

	public SearchPageData findProductList(String productCode,String productName, String storeName,String productStatus,PageableData pageableData);

}
