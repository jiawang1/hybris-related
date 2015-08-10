package com.tasly.anguo.core.product.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.tasly.anguo.core.constants.AnguoCoreConstants;
import com.tasly.anguo.core.enums.ProductStatus;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.constants.CatalogConstants;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.util.Config;

@IntegrationTest
public class DefaultAnguoProductDaoIntegrationTest extends ServicelayerTransactionalTest{
	
	private Logger LOG = Logger.getLogger(DefaultAnguoProductDaoIntegrationTest.class);
	
	@Resource
	private DefaultAnguoProductDao anguoProductDao;
	@Resource
	private SessionService sessionService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private CommonI18NService  commonI18NService;
	@Resource
	private TypeService typeService;
	
	@Before
	public void setup()
	{
		CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion(AnguoCoreConstants.ACTIVEPRODUCTCATALOG, AnguoCoreConstants.ACTIVEPRODUCTCATALOGVERSION);
		sessionService.getCurrentSession().setAttribute(CatalogConstants.SESSION_CATALOG_VERSIONS, catalogVersionModel);
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("ZH"));
	}
	
	@Test
	public void testFindProductByList()
	{
		PageableData pageableData = createPageableData();
		String productCode = "";
		String productName = "千1";
		String storeName = "02";
		String productStatus = typeService.getEnumerationValue(ProductStatus._TYPECODE, "UNCHECK").getPk().toString();
		
		SearchPageData searchPageData = anguoProductDao.findProductList(productCode, productName, storeName, productStatus,pageableData);
		List<ProductModel> productList = searchPageData.getResults();
		
		LOG.info(String.format("total number of results is %s", searchPageData.getPagination().getTotalNumberOfResults()));
		if(CollectionUtils.isNotEmpty(productList)){
			for(int i = 0;i < productList.size();i++){
				ProductModel product = productList.get(i);
				if(StringUtils.isNotBlank(storeName))
				assertTrue(product.getAnguoStore().getName().contains(storeName));
				
				if(StringUtils.isNotBlank(productName))
				assertTrue(product.getName().contains(productName));
				LOG.info("-----"+product.getCode());
			}
		}
	}
	
	private PageableData createPageableData(){
		PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(0);
		pageableData.setPageSize(2);
		return pageableData;
	}

}
