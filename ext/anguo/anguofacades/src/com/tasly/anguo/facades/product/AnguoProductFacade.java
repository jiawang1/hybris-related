package com.tasly.anguo.facades.product;


import com.tasly.anguo.facades.product.data.ProductListData;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

public interface AnguoProductFacade extends ProductFacade{
	ProductListData getProductList(String storeName,String productCode,String productName,String productStatus,PageableData pageableData);

}
