package com.tasly.anguo.facades.category.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tasly.anguo.core.category.AnguoCategoryService;
import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.populators.AnguoCategoryPopulator;
import com.tasly.anguo.facades.populators.CategoryNodePopulator;
import com.tasly.anguo.facades.populators.MgmtCategoryPopulator;
import com.tasly.anguo.facades.product.data.CategoryData;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.daos.CategoryDao;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.converters.populator.CategoryPopulator;

public class DefaultAnguoCategoryFacade implements AnguoCategoryFacade {
    
	private Logger LOG = Logger.getLogger(DefaultAnguoCategoryFacade.class);
	private AnguoCategoryService categoryService;
	private CategoryNodePopulator categoryNodePopulator;
	private MgmtCategoryPopulator mgmtCategoryPopulator;
	private AnguoCategoryPopulator categoryPopulator;
	
	@Override
	public List<CategoryNodeData> getSubCategoryByCode(String categoryCode) {
		if(StringUtils.isEmpty(categoryCode))
			return getRootCategory();
		CategoryModel category = categoryService.getCategoryForCode(categoryCode);
		Collection<CategoryModel> categorys = category.getCategories();
	
		List<CategoryNodeData> childrenCategoryNodeDataList = new ArrayList<CategoryNodeData>();
		CategoryNodeData parentCategoryNode = new CategoryNodeData();
		
		if(CollectionUtils.isNotEmpty(categorys)){
			
			CategoryModel categoryModel = null;
			
			Iterator<CategoryModel> categoryIterator = categorys.iterator();
			
			while(categoryIterator.hasNext()){
				categoryModel = categoryIterator.next();
		        CategoryNodeData childrenCategoryNodeData = new CategoryNodeData();
		        categoryNodePopulator.populate(categoryModel, childrenCategoryNodeData);
		        childrenCategoryNodeDataList.add(childrenCategoryNodeData);
			}
		}
		
		return childrenCategoryNodeDataList;
	}
	/**
	 * get root category
	 * @return
	 */
	protected List<CategoryNodeData> getRootCategory(){
		List<CategoryNodeData> categoryNodeDataList = new ArrayList<CategoryNodeData>();
		
		//TODO: need to refactor this with fetch all the root code,need to set catalogversion when user log in
		Collection<CategoryModel> categorys = categoryService.getCategoriesForCode("药材");
		Iterator<CategoryModel> categoryIterator = categorys.iterator();
		while(categoryIterator.hasNext()){
			CategoryModel category = categoryIterator.next();
			CategoryNodeData categoryNodeData = new CategoryNodeData();
			categoryNodePopulator.populate(category,categoryNodeData);
			categoryNodeDataList.add(categoryNodeData);
		}
		
		return categoryNodeDataList;
	}
	
	@Override
	public MgmtCategoryData getCategoryDetail(String categoryCode) {
		CategoryModel category = categoryService.getCategoryForCode(categoryCode);
		MgmtCategoryData mgmtCategoryData = new MgmtCategoryData();
		
		if(category!=null)
			mgmtCategoryPopulator.populate(category, mgmtCategoryData);
		
		return mgmtCategoryData;
	}

	@Override
	public List<CategoryData> getCategoriesByKeyword(String keyword,
			int totalCount) {
		List<CategoryModel> categoryModels = categoryService.getCategoriesByKeyword(keyword, totalCount);
		List<CategoryData> result = new ArrayList<CategoryData>();
		for (CategoryModel categoryModel : categoryModels) {
			CategoryData category = new CategoryData();
			categoryPopulator.populate(categoryModel, category);
			result.add(category);
		}
		return result;
	}
	
	public void setCategoryService(AnguoCategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCategoryNodePopulator(CategoryNodePopulator categoryNodePopulator) {
		this.categoryNodePopulator = categoryNodePopulator;
	}
	
	public void setMgmtCategoryPopulator(MgmtCategoryPopulator mgmtCategoryPopulator) {
		this.mgmtCategoryPopulator = mgmtCategoryPopulator;
	}
	/**
	 * @return the categoryPopulator
	 */
	public AnguoCategoryPopulator getCategoryPopulator() {
		return categoryPopulator;
	}
	/**
	 * @param categoryPopulator the categoryPopulator to set
	 */
	public void setCategoryPopulator(AnguoCategoryPopulator categoryPopulator) {
		this.categoryPopulator = categoryPopulator;
	}
}
