package com.tasly.anguo.controllers.productmanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import com.tasly.anguo.controllers.ControllerConstants;
import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.constants.AnguoFacadesConstants;
import com.tasly.anguo.facades.data.AjaxMessageData;
import com.tasly.anguo.facades.product.AnguoProductFacade;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;
import com.tasly.anguo.store.data.ProductListData;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

@Scope("tenant")
@Controller
@RequestMapping("/**/productManagement")
public class ProductManagementController {
	private Logger LOG = Logger.getLogger(ProductManagementController.class);
	private static final String GET_PRODUCT_LIST = "/getProductList";
	private static final String DELETE_CATEGORY_ERROR = "由于有子类目或者产品,类目 %s 无法删除";
	
	@Resource(name="anguoProductFacade")
	private AnguoProductFacade anguoProductFacade;

	/**
	 * @param productCode
	 * @param productName
	 * @param storeName
	 * @param productStatus
	 * @param start -> start position of the data
	 * @param length -> page size
	 * @return
	 */
	@RequestMapping(value=GET_PRODUCT_LIST)
	public 	@ResponseBody Object getProductList(String productCode,String productName,String storeName,String productStatus,
			String draw,String start,String length)
	{
		
        PageableData pageableData = createPageableData(start,length);
       
        ProductListData resultList = anguoProductFacade.getProductList(storeName, productCode, productName, productStatus, pageableData);
        
		return resultList;
		
	}

	private PageableData createPageableData(String start,
			String length)
	{
		Integer currentPage = Integer.valueOf(start)/Integer.valueOf(length) ;
		PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(currentPage);
		pageableData.setPageSize(Integer.valueOf(length));
		return pageableData;
	}

}
