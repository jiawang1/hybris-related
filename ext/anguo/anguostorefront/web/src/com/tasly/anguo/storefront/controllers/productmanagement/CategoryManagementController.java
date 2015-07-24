package com.tasly.anguo.storefront.controllers.productmanagement;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;
import com.tasly.anguo.storefront.controllers.ControllerConstants;

@Scope("tenant")
@Controller
@RequestMapping("/**/categoryManagement")
public class CategoryManagementController {
    private Logger LOG =  Logger.getLogger(CategoryManagementController.class);
    private static final String GET_CATEGORY_TREE = "/getCategoryTree";
    private static final String GET_SUB_CATEGORY = "/getSubCategory";
    private static final String GET_CATEGORY_DETAIL = "/getCategoryDetail";
    private static final String DELETE_CATEGORY = "/deleteCategory";
    private static final String SAVE_CATEGORY = "/saveCategory";
    private static final String CREATE_CATEGORY = "/createCategory";
    
    @Resource
	private AnguoCategoryFacade anguoCategoryFacade;
    
	@RequestMapping(value=GET_CATEGORY_TREE)
	public String renderPage(){
		return ControllerConstants.Views.Pages.ProductManagement.CategoryManagement;
	}
	
	@RequestMapping(value=GET_SUB_CATEGORY )
	public 	@ResponseBody Object getSubCategory(String categoryCode){
		return anguoCategoryFacade.getSubCategoryByCode(categoryCode.equals("#") ? "":categoryCode);
	}
	
	@RequestMapping(value=GET_CATEGORY_DETAIL)
	public 	@ResponseBody Object getCategoryDetail(String categoryCode){
	    return anguoCategoryFacade.getCategoryDetail(categoryCode);
	}
	
	@RequestMapping(value=DELETE_CATEGORY)
	public 	@ResponseBody Object deleteCategory(String categoryCode){
	    anguoCategoryFacade.deleteCategory(categoryCode);
	    return null;
	}
	
	@RequestMapping(value=SAVE_CATEGORY)
	public 	@ResponseBody Object saveCategory(String categoryDetail,final HttpServletRequest request, final HttpServletResponse response){
		final MgmtCategoryData categoryData = JSON.parseObject(categoryDetail,new TypeReference<MgmtCategoryData>(){});
		anguoCategoryFacade.saveCategory(categoryData);
		return categoryData;
	}
	
	@RequestMapping(value=CREATE_CATEGORY)
	public 	@ResponseBody Object createCategory(String superCategory){
		return anguoCategoryFacade.createCategory(superCategory);
	}
	
}
