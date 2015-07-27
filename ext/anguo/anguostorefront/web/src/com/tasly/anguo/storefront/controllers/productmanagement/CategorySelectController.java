package com.tasly.anguo.storefront.controllers.productmanagement;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.storefront.controllers.ControllerConstants;

import de.hybris.platform.commercefacades.user.data.RegionData;

@Scope("tenant")
@Controller
@RequestMapping("/**/selectcategory")
public class CategorySelectController {
    private Logger LOG =  Logger.getLogger(CategorySelectController.class);
    private static final String GET_CATEGORY_TREE = "/getCategoryTree";
    private static final String GET_SUB_CATEGORY = "/getSubCategory";
    private static final String FIND_CATEGORY = "/searchCategory";
    private static final String GET_CATEGORY_SELECTED = "/getCategorySelected";
    
    @Resource
	private AnguoCategoryFacade anguoCategoryFacade;
    
	@RequestMapping(value=GET_CATEGORY_TREE)
	public String renderPage(Model model){
		List<CategoryNodeData> categoryRoots = anguoCategoryFacade.getSubCategoryByCode("");
		model.addAttribute("categoryRoots", categoryRoots);
		return ControllerConstants.Views.Pages.ProductManagement.CategorySelect;
	}
	
	@RequestMapping(value=GET_SUB_CATEGORY )
	@ResponseBody 
	public Object getSubCategory(String categoryCode){
		return anguoCategoryFacade.getSubCategoryByCode(categoryCode.equals("#") ? "":categoryCode);
	}
	
	@RequestMapping(value=FIND_CATEGORY)
	@ResponseBody
	public Object findCategoryByKeyword(String keyword){
	    return anguoCategoryFacade.getCategoriesByKeyword(keyword, 10);
	}
	
	@RequestMapping(value=GET_CATEGORY_SELECTED)
	@ResponseBody
	public String getCategorySelected(String categoryCode, String categoryLv2Code){
	    LOG.info("Category Lv2 Selected : " + categoryLv2Code);
	    LOG.info("Category Selected : " + categoryCode);
	    return categoryLv2Code + " >> " + categoryCode;
	}
}
