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

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

/**
 * @author i319019
 *
 */
public class DefaultAnguoCategoryServiceTest extends ServicelayerTransactionalTest {

	@Resource
	private AnguoCategoryService anguoCategoryService;
	
	@Before
	public void setUp() throws Exception
	{
		importCsv("/test/anguoCategoryServiceTest.csv", "utf-8");
	}
	
	@Test
	public void getCategoriesByKeywordTest()
	{
		String keyword = "精";
		int totalCount = 3;
		List<CategoryModel> results = anguoCategoryService.getCategoriesByKeyword(keyword, totalCount);
		assertEquals(results.size(), 2);
		
		String keyword2 = "人参";
		List<CategoryModel> results2 = anguoCategoryService.getCategoriesByKeyword(keyword2, totalCount);
		assertEquals(results2.size(), 1);
	}
}
