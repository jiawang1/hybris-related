package com.tasly.anguo.facades.category.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.constants.AnguoFacadesConstants;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.constants.CatalogConstants;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;

@IntegrationTest
public class DefaultAnguoCategoryFacadeIntegrationTest extends ServicelayerTransactionalTest {
	private Logger LOG = Logger.getLogger(DefaultAnguoCategoryFacadeIntegrationTest.class);
	
	@Resource
	AnguoCategoryFacade anguoCategoryFacade;
	
	@Resource
	SessionService sessionService;
	
	@Resource
	CatalogVersionService catalogVersionService;
	
	@Resource
	CategoryService categoryService;
	
	@Before
	public void setup(){
		CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion(AnguoFacadesConstants.ACTIVEPRODUCTCATALOG, AnguoFacadesConstants.ACTIVEPRODUCTCATALOGVERSION);
		sessionService.getCurrentSession().setAttribute(CatalogConstants.SESSION_CATALOG_VERSIONS, catalogVersionModel);
	}
	
	@Test
	public void testGetSubCategory(){
		String categoryCode = AnguoFacadesConstants.ROOTCATEGORY;
		List<CategoryNodeData> categoryNodeDataList = anguoCategoryFacade.getSubCategoryByCode(categoryCode);
        
		assertNotNull(categoryNodeDataList);
	    assertTrue(categoryNodeDataList.size()>0?true:false);
	}
	
	@Test
	public void testGetCategoryDetail(){
		MgmtCategoryData categoryData = anguoCategoryFacade.getCategoryDetail(AnguoFacadesConstants.ROOTCATEGORY);
		assertEquals(categoryData.getCategoryCode(),"药材");
	}
	
}
