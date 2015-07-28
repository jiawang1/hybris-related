package com.tasly.anguo.facades.category.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tasly.anguo.core.category.AnguoCategoryService;
import com.tasly.anguo.core.model.CategoryAliasModel;
import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.constants.AnguoFacadesConstants;
import com.tasly.anguo.facades.populators.AnguoCategoryPopulator;
import com.tasly.anguo.facades.populators.CategoryNodePopulator;
import com.tasly.anguo.facades.populators.MgmtCategoryPopulator;
import com.tasly.anguo.facades.product.data.CategoryData;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.exceptions.ModelRemovalException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;

/**
 * @author Jack
 *
 */
public class DefaultAnguoCategoryFacade implements AnguoCategoryFacade {
    
	private Logger LOG = Logger.getLogger(DefaultAnguoCategoryFacade.class);
	private AnguoCategoryService categoryService;
	private ModelService modelService;
	private CategoryNodePopulator categoryNodePopulator;
	private MgmtCategoryPopulator mgmtCategoryPopulator;
	private AnguoCategoryPopulator categoryPopulator;
	private CatalogVersionService catalogVersionService;
	private KeyGenerator categoryCodeGenerator;
	
	@Override
	public List<CategoryNodeData> getSubCategoryByCode(String categoryCode) {
		if(StringUtils.isEmpty(categoryCode))
			return getRootCategory();
		CategoryModel category = categoryService.getCategoryForCode(categoryCode);
		Collection<CategoryModel> categorys = category.getCategories();
	
		List<CategoryNodeData> subCategoryNodeDataList = new ArrayList<CategoryNodeData>();
		
		if(CollectionUtils.isNotEmpty(categorys)){
			
			CategoryModel categoryModel = null;
			
			Iterator<CategoryModel> categoryIterator = categorys.iterator();
			
			while(categoryIterator.hasNext()){
				categoryModel = categoryIterator.next();
		        CategoryNodeData subCategoryNodeData = new CategoryNodeData();
		        categoryNodePopulator.populate(categoryModel, subCategoryNodeData);
		        subCategoryNodeDataList.add(subCategoryNodeData);
			}
		}
		
		return subCategoryNodeDataList;
	}
	
	/**
	 * get root category
	 * @return
	 */
	protected List<CategoryNodeData> getRootCategory(){
		
		List<CategoryNodeData> categoryNodeDataList = new ArrayList<CategoryNodeData>();
		
		//TODO: need to refactor this with fetch all the root code,need to set catalogversion when user log in
		Collection<CategoryModel> categorys = categoryService.getCategoriesForCode(AnguoFacadesConstants.ROOTCATEGORY);
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
			if(CollectionUtils.isNotEmpty(parentCategory.getSupercategories()))
				category.setLevel(3);
			else
                category.setLevel(2);
			
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
	public void deleteCategory(String categoryCode) throws InterceptorException {
		
		CategoryModel categoryModel = categoryService.getCategoryForCode(categoryCode);
		
		try{
		    modelService.remove(categoryModel);
		}catch(ModelRemovalException ex)
		{  
			LOG.error("remove was not allowed for " + categoryCode);
			//TODO:need a framework to handle the exception,current handle it in a simple way
			throw new InterceptorException(ex.getLocalizedMessage());
		}
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
