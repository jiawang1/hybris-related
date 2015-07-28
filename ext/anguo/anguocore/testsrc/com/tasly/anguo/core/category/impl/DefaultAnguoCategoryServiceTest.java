/**
 * 
 */
package com.tasly.anguo.core.category.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.tasly.anguo.core.category.AnguoCategoryService;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.constants.CatalogConstants;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;

/**
 * @author i319019
 *
 */
public class DefaultAnguoCategoryServiceTest extends ServicelayerTransactionalTest {

	@Resource
	private AnguoCategoryService anguoCategoryService;
	@Resource
	private SessionService sessionService;
	@Resource
	private CatalogVersionService catalogVersionService; 
	
	@Before
	public void setUp() throws Exception
	{
		importCsv("/test/anguoCategoryServiceTest.csv", "utf-8");
		CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("anguoProductCatalog", "Online");
		sessionService.getCurrentSession().setAttribute(CatalogConstants.SESSION_CATALOG_VERSIONS, catalogVersionModel);
		Language lang = jaloSession.getC2LManager().getLanguageByIsoCode("zh");
		jaloSession.getSessionContext().setLanguage(lang);
	}
	
	@Test
	public void getCategoriesByKeywordTest()
	{
		int totalCount = 3;
		String keyword = "人参";
		List<CategoryModel> results = anguoCategoryService.getCategoriesByKeyword(keyword, totalCount);
		assertEquals(results.size(), 1);
	}
}
