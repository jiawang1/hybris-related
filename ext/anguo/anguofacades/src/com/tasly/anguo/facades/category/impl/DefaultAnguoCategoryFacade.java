package com.tasly.anguo.facades.category.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.populators.CategoryNodePopulator;
import com.tasly.anguo.facades.populators.MgmtCategoryPopulator;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.daos.CategoryDao;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.converters.populator.CategoryPopulator;
import de.hybris.platform.commercefacades.product.data.CategoryData;

public class DefaultAnguoCategoryFacade implements AnguoCategoryFacade {
    
	private Logger LOG = Logger.getLogger(DefaultAnguoCategoryFacade.class);
	private CategoryService categoryService;
	private CategoryNodePopulator categoryNodePopulator;
	private MgmtCategoryPopulator mgmtCategoryPopulator;
	
	@Override
	public List<CategoryNodeData> getSubCategoryByCode(String categoryCode) {
		
		if(StringUtils.isEmpty(categoryCode))
			return getRootCategory();
		
		CategoryModel category = categoryService.getCategoryForCode(categoryCode);
	
		Collection<CategoryModel> categorys = category.getAllSubcategories();// categoryService.getAllSubcategoriesForCategory(category);
	
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
			LOG.info("here it is");
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

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCategoryNodePopulator(CategoryNodePopulator categoryNodePopulator) {
		this.categoryNodePopulator = categoryNodePopulator;
	}
	
	public void setMgmtCategoryPopulator(MgmtCategoryPopulator mgmtCategoryPopulator) {
		this.mgmtCategoryPopulator = mgmtCategoryPopulator;
	}
}
