package com.tasly.anguo.core.product;

import com.tasly.anguo.core.enums.ProductStatus;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.product.ProductService;

public interface AnguoProductService extends ProductService{

	SearchPageData getProductList(String productCode, String productName,String storeName, ProductStatus productStatus,PageableData pageableData);

}
