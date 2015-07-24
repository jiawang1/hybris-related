package com.tasly.anguo.facades.category.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tasly.anguo.core.jalo.CategoryAlias;
import com.tasly.anguo.core.model.CategoryAliasModel;
import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.constants.AnguoFacadesConstants;
import com.tasly.anguo.facades.populators.CategoryNodePopulator;
import com.tasly.anguo.facades.populators.MgmtCategoryPopulator;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.daos.CategoryDao;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.converters.populator.CategoryPopulator;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;


/**
 * @author Jack
 *
 */
public class DefaultAnguoCategoryFacade implements AnguoCategoryFacade {
    
	private Logger LOG = Logger.getLogger(DefaultAnguoCategoryFacade.class);
	private CategoryService categoryService;
	private ModelService modelService;
	private CategoryNodePopulator categoryNodePopulator;
	private MgmtCategoryPopulator mgmtCategoryPopulator;
	private CatalogVersionService catalogVersionService;
	private KeyGenerator categoryCodeGenerator;
	
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
	
	public void saveCategory(MgmtCategoryData categoryData){
		
		CategoryModel category = categoryService.getCategoryForCode(categoryData.getCategoryCode());
		List<CategoryAliasModel> categoryAliasList = new ArrayList<CategoryAliasModel>();
	    category.setAlias(categoryAliasList);
	    //TODO:need to check if could remove this
		modelService.save(category);
		
		category.setName(categoryData.getName());
		
		for(int i = 0;i<categoryData.getAlias().size();i++){
			CategoryAliasModel categoryAlias = modelService.create(CategoryAliasModel.class);
			categoryAlias.setAlias(categoryData.getAlias().get(i).getDescription());
			categoryAliasList.add(categoryAlias);
		}
		
		category.setAlias(categoryAliasList);
		modelService.save(category);
		
	}
	
    public MgmtCategoryData createCategory(String superCategoryCode) {
		try{
			CategoryModel parentCategory = categoryService.getCategoryForCode(superCategoryCode);
			CategoryModel category = modelService.create(CategoryModel.class);
			//TODO: need to refact this once catalog version is loaded by session
			category.setCatalogVersion(catalogVersionService.getCatalogVersion(AnguoFacadesConstants.ACTIVECATALOG,AnguoFacadesConstants.ACTIVECATALOGVERSION));
			category.setCode((String)categoryCodeGenerator.generate());
			category.setSupercategories(Arrays.asList(parentCategory));
			category.setName(AnguoFacadesConstants.DEFAULTCATEGORYNAME);
			
			modelService.save(category);
			
			MgmtCategoryData categoryData = new MgmtCategoryData();
			categoryData.setCategoryCode(category.getCode());
			categoryData.setName(category.getName());
			
			return categoryData;
		}
		catch(UnknownIdentifierException ex)
		{
			LOG.warn(String.format("no supercategory when create children for category %s", superCategoryCode));
		    
		}
		
		return null;
	}
	
	@Override
	public void deleteCategory(String categoryCode) {
		CategoryModel categoryModel = categoryService.getCategoryForCode(categoryCode);
		modelService.remove(categoryModel);		
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
	
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	
	public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
		this.catalogVersionService = catalogVersionService;
	}
	
	public void setCategoryCodeGenerator(KeyGenerator categoryCodeGenerator) {
		this.categoryCodeGenerator = categoryCodeGenerator;
	}
	
}
