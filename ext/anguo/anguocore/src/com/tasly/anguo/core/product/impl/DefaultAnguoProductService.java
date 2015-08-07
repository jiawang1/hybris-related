package com.tasly.anguo.core.product.impl;

import org.apache.commons.lang.StringUtils;

import com.tasly.anguo.core.enums.ProductStatus;
import com.tasly.anguo.core.product.AnguoProductService;
import com.tasly.anguo.core.product.dao.AnguoProductDao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.type.TypeService;

public class DefaultAnguoProductService extends DefaultProductService implements AnguoProductService  {
	
	private AnguoProductDao anguoProductDao;
	private TypeService typeService;


	@Override
	public SearchPageData getProductList(String productCode,String productName, String storeName,ProductStatus productStatus,PageableData pageableData) 
	{
		
		EnumerationValueModel productStatusValue = typeService.getEnumerationValue(productStatus);
		
		return anguoProductDao.findProductList(productCode==null?StringUtils.EMPTY:productCode,
				productName==null?StringUtils.EMPTY:productName, 
				storeName==null?StringUtils.EMPTY:storeName,
				productStatusValue.getPk().toString(),
			    pageableData);
	}
	
	public void setAnguoProductDao(AnguoProductDao anguoProductDao) 
	{
		this.anguoProductDao = anguoProductDao;
	}

	public void setTypeService(TypeService typeService)
	{
		this.typeService = typeService;
	}
	
}
