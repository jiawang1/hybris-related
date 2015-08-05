package com.tasly.anguo.core.product.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tasly.anguo.core.enums.ProductStatus;
import com.tasly.anguo.core.model.AnguoStoreModel;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;

@UnitTest
public class DefaultAnguoProductServiceTest {
	
	@Mock
	private DefaultAnguoProductService defaultAnguoProductService;
	
	@Mock
	private List<ProductModel> resultList;
	
	@Mock
	private AnguoStoreModel anguoStore;
	
	@Mock
	private PageableData pageableData;
	
	@Mock
	private SearchPageData searchPageData;
	
	@Mock
	private PaginationData pagination;
	
	@Mock
	private ProductModel product;
	
	private String productCode = null;
	private String productName = null;
	private String storeName = null;
	
	@Before
	public void setup(){
		
		MockitoAnnotations.initMocks(this);
		productCode = "人参";
		productName = "人参";
		storeName = "何家铺子";
		setPageableData();
		setSearchPageData();
	}
	
	@Test
	public void testGetProductList(){
		
		given(defaultAnguoProductService.getProductList(productCode, productName, storeName,ProductStatus.OFFLINE, pageableData)).willReturn(searchPageData);
		SearchPageData searchResult= defaultAnguoProductService.getProductList(productCode, productName, storeName,ProductStatus.OFFLINE, pageableData);
		
		for(int i = 0;i < searchResult.getResults().size();i++){
			ProductModel product = (ProductModel) searchResult.getResults().get(i);
			assertTrue(product.getName().contains(productName));
			assertTrue(product.getCode().contains(productCode));
			assertTrue(product.getAnguoStore().getName().contains(storeName));
		}
		
		//TEST NULL SAFE
		given(defaultAnguoProductService.getProductList(null,null,null,ProductStatus.OFFLINE,pageableData)).willReturn(searchPageData);
		searchResult = defaultAnguoProductService.getProductList(null,null,null,ProductStatus.OFFLINE, pageableData);
		assertNotNull(searchResult);
	}
	
	private void setPageableData(){
		pageableData.setCurrentPage(0);
		pageableData.setPageSize(10);
	}
	
	private void setSearchPageData(){
		
		for(int i = 0;i < 10;i++){
			product.setName(i+productCode);
			product.setCode(i+productCode);
			
			AnguoStoreModel anguoStore = new AnguoStoreModel();
			anguoStore.setName("何家铺子");
			product.setAnguoStore(anguoStore);

			resultList.add(product);
		}
		
		pagination.setCurrentPage(0);
		pagination.setPageSize(10);
		pagination.setTotalNumberOfResults(10);
		
		searchPageData.setPagination(pagination);
		searchPageData.setResults(resultList);
		
	}

}
