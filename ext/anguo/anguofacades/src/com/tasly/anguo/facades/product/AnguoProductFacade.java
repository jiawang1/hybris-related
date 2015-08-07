package com.tasly.anguo.facades.product;


import com.tasly.anguo.facades.product.data.ProductListData;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

public interface AnguoProductFacade extends ProductFacade{
	ProductListData getProductList(String storeName,String productCode,String productName,String productStatus,PageableData pageableData);

	public boolean createProduct(String categoryCode, String productName,
			String specification, String madeIn, String storageLocation,
			int productYear, String processMethod, String packageUnit,
			String huifen, String gandu, String containLiu, String salesUnit,
			int stock, int minSalesQuantity, int minQtd1, double price1,
			int minQtd2, double price2, int minQtd3, double price3,
			String description, ArticleApprovalStatus status);
}
