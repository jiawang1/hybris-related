package com.tasly.anguo.storefront.controllers.productmanagement;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.storefront.controllers.ControllerConstants;

@Scope("tenant")
@Controller
@RequestMapping("/**/productManagement")
public class CategoryManagementController {
    private Logger LOG =  Logger.getLogger(CategoryManagementController.class);
    private static final String GET_CATEGORY_TREE = "/getCategoryTree";
    private static final String GET_SUB_CATEGORY = "/getSubCategory";
    private static final String GET_CATEGORY_DETAIL = "/getCategoryDetail";
    
    @Resource
	private AnguoCategoryFacade anguoCategoryFacade;
    
	@RequestMapping(value=GET_CATEGORY_TREE)
	public String renderPage(){
		return ControllerConstants.Views.Pages.ProductManagement.CategoryManagement;
	}
	
	@RequestMapping(value=GET_SUB_CATEGORY )
	@ResponseBody 
	public Object getSubCategory(String categoryCode){
		return anguoCategoryFacade.getSubCategoryByCode(categoryCode.equals("#") ? "":categoryCode);
	}
	
	@RequestMapping(value=GET_CATEGORY_DETAIL)
	@ResponseBody
	public Object getCategoryDetail(String categoryCode){
	    return anguoCategoryFacade.getCategoryDetail(categoryCode);
	}
	
	
}
