package com.tasly.anguo.facades.category.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.product.data.CategoryNodeData;

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
public class DefaultAnguoCategoryFacadeTest extends ServicelayerTransactionalTest {
	private Logger LOG = Logger.getLogger(DefaultAnguoCategoryFacadeTest.class);
	
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
		CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("anguoProductCatalog", "Online");
		sessionService.getCurrentSession().setAttribute(CatalogConstants.SESSION_CATALOG_VERSIONS, catalogVersionModel);
	}
	
	@Test
	public void testGetSubCategory(){
		String categoryCode="药材";
		List<CategoryNodeData> categoryNodeDataList = anguoCategoryFacade.getSubCategoryByCode(categoryCode);
		
//		CategoryModel category = categoryService.getCategoryForCode(categoryCode);
//		Collection<CategoryModel> categorys= categoryService.getAllSubcategoriesForCategory(category);
//		
//		CategoryNodeData categoryNode = new CategoryNodeData();
//		Collection<CategoryNodeData> categoryNodeDatas = new ArrayList<CategoryNodeData>();
//		categoryNode.setId(category.getCode());
//		categoryNode.setText(category.getName());
//		
//		Iterator<CategoryModel> itr = categorys.iterator();
//		while (itr.hasNext()) {
//			CategoryNodeData childrenCategoryNode = new CategoryNodeData();
//			CategoryModel subCategory = itr.next();
//			childrenCategoryNode.setId(subCategory.getCode());
//			childrenCategoryNode.setText(subCategory.getName());
//			categoryNodeDatas.add(childrenCategoryNode);
//		}
//		
//		//category.
//		categoryNode.setChildren(categoryNodeDatas);

		LOG.info(JSON.toJSONString(categoryNodeDataList));

	
	}
	
	
	
}
