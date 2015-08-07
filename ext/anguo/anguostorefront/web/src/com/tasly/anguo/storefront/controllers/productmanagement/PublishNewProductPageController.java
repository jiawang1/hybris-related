/**
 * 
 */
package com.tasly.anguo.storefront.controllers.productmanagement;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tasly.anguo.core.enums.PackageUnit;
import com.tasly.anguo.facades.category.AnguoCategoryFacade;
import com.tasly.anguo.facades.constants.AnguoFacadesConstants;
import com.tasly.anguo.facades.data.AjaxMessageData;
import com.tasly.anguo.facades.product.AnguoProductFacade;
import com.tasly.anguo.facades.product.UnitFacade;
import com.tasly.anguo.facades.product.data.CategoryData;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.EnumData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;
import com.tasly.anguo.facades.product.data.UnitData;
import com.tasly.anguo.facades.utils.EnumUtils;
import com.tasly.anguo.storefront.controllers.ControllerConstants;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;

/**
 * Controller for publish new product page
 * 
 * @author i319019
 *
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/**/newproduct")
public class PublishNewProductPageController {
	private Logger LOG = Logger.getLogger(CategorySelectController.class);
	private static final String GET_SUB_CATEGORY = "/getSubCategory";
	private static final String FIND_CATEGORY = "/searchCategory";
	private static final String GET_CATEGORY_SELECTED = "/getCategorySelected";
	private static final String SAVE_NEW_PRODUCT = "/saveNewProduct";
	private static final String SUBMIT_NEW_PRODUCT = "/submitNewProuct";

	@Resource
	private AnguoCategoryFacade anguoCategoryFacade;
	@Resource
	private EnumUtils enumUtils;
	@Resource
	private UnitFacade unitFacade;
	@Resource
	private AnguoProductFacade anguoProductFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String renderPage(Model model)
	{
		List<CategoryNodeData> categoryRoots = anguoCategoryFacade
				.getSubCategoryByCode(AnguoFacadesConstants.ROOTCATEGORY);
		model.addAttribute("categoryRoots", categoryRoots);
		List<EnumData> packageUnits = enumUtils
				.getEnumValues(PackageUnit.class);
		model.addAttribute("packageUnits", packageUnits);
		List<UnitData> units = unitFacade.getAllUnits();
		model.addAttribute("units", units);
		return ControllerConstants.Views.Pages.ProductManagement.CategorySelect;
	}

	@RequestMapping(value = GET_SUB_CATEGORY)
	@ResponseBody
	public List<CategoryNodeData> getSubCategory(String categoryCode)
	{
		return anguoCategoryFacade
				.getSubCategoryByCode(categoryCode.equals("")
						|| categoryCode.equals("#") ? AnguoFacadesConstants.ROOTCATEGORY
						: categoryCode);
	}

	@RequestMapping(value = FIND_CATEGORY)
	@ResponseBody
	public List<CategoryData> findCategoryByKeyword(String keyword)
	{
		return anguoCategoryFacade.getCategoriesByKeyword(keyword, 10);
	}

	@RequestMapping(value = GET_CATEGORY_SELECTED)
	@ResponseBody
	public String getCategorySelected(String categoryCode,
			String categoryLv2Code, boolean newCat)
	{
		String catLv3 = categoryCode;
		String catLv2 = categoryLv2Code;
		if (!newCat)
		{
			MgmtCategoryData category = anguoCategoryFacade
					.getCategoryDetail(catLv3);
			catLv2 = category.getSuperCategory();
		}
		return catLv2 + " >> " + catLv3;
	}

	@RequestMapping(value = SAVE_NEW_PRODUCT)
	@ResponseBody
	public String saveNewProduct(String categoryCodeLv2,
			String categoryCodeLv3, boolean newCat, String productName,
			String specification, String madeIn, String storageLocation,
			int productYear, String processMethod, String packageUnit,
			String huifen, String gandu, String containLiu, String salesUnit,
			int stock, int minSalesQuantity, int minSalesQtd1, double price1,
			int minSalesQtd2, double price2, int minSalesQtd3, double price3,
			String description, HttpServletResponse response)
	{
		LOG.info("name : " + productName);
		LOG.info("specification : " + specification);
		LOG.info("madeIn : " + madeIn);
		LOG.info("storageLocation : " + storageLocation);
		LOG.info("productYear : " + productYear);
		LOG.info("processMethod : " + processMethod);
		LOG.info("packageUnit : " + packageUnit);
		LOG.info("huifen : " + huifen);
		LOG.info("gandu : " + gandu);
		LOG.info("containLiu : " + containLiu);
		LOG.info("salesUnit : " + salesUnit);
		LOG.info("stock : " + stock);
		LOG.info("minSalesQuantity : " + minSalesQuantity);
		LOG.info("minSalesQtd1 : " + minSalesQtd1);
		LOG.info("price1 : " + price1);
		LOG.info("description : " + description);
		// if (madeIn.equals("--"))
		// {
		// response.setStatus(500);
		// }
		String categoryCode = categoryCodeLv3;
		if (newCat)
		{
			categoryCode = categoryCodeLv2;
		}
		boolean result = anguoProductFacade.createProduct(categoryCode,
				productName, specification, madeIn, storageLocation,
				productYear, processMethod, packageUnit, huifen, gandu,
				containLiu, salesUnit, stock, minSalesQuantity, minSalesQtd1,
				price1, minSalesQtd2, price2, minSalesQtd3, price3,
				description, ArticleApprovalStatus.UNAPPROVED);
		if (result)
		{
			return "OK";
		} else
		{
			return "NOK";
		}
	}

	@RequestMapping(value = SUBMIT_NEW_PRODUCT)
	@ResponseBody
	public String submitNewProduct(String categoryCodeLv2,
			String categoryCodeLv3, boolean newCat, String productName,
			String specification, String madeIn, String storageLocation,
			int productYear, String processMethod, String packageUnit,
			String huifen, String gandu, String containLiu, String salesUnit,
			int stock, int minSalesQuantity, int minSalesQtd1, double price1,
			int minSalesQtd2, double price2, int minSalesQtd3, double price3,
			String description)
	{
		LOG.info("name : " + productName);
		LOG.info("specification : " + specification);
		LOG.info("madeIn : " + madeIn);
		LOG.info("storageLocation : " + storageLocation);
		LOG.info("productYear : " + productYear);
		LOG.info("processMethod : " + processMethod);
		LOG.info("packageUnit : " + packageUnit);
		LOG.info("huifen : " + huifen);
		LOG.info("gandu : " + gandu);
		LOG.info("containLiu : " + containLiu);
		LOG.info("salesUnit : " + salesUnit);
		LOG.info("stock : " + stock);
		LOG.info("minSalesQuantity : " + minSalesQuantity);
		LOG.info("description : " + description);
		boolean result = true;
		if (StringUtils.isBlank(productName)
				|| StringUtils.isBlank(specification)
				|| StringUtils.isBlank(storageLocation)
				|| StringUtils.isBlank(salesUnit) || stock <= 0
				|| minSalesQuantity <= 0
				|| (((minSalesQtd1 == 0) || (price1 == 0))
				&& ((minSalesQtd2 == 0) || (price2 == 0))
				&& ((minSalesQtd3 == 0) || (price3 == 0))))
		{
			result = false;
		}
		String categoryCode = categoryCodeLv3;
		if (newCat)
		{
			categoryCode = categoryCodeLv2;
		}
		result = anguoProductFacade.createProduct(categoryCode, productName,
				specification, madeIn, storageLocation, productYear,
				processMethod, packageUnit, huifen, gandu, containLiu,
				salesUnit, stock, minSalesQuantity, minSalesQtd1, price1,
				minSalesQtd2, price2, minSalesQtd3, price3, description,
				ArticleApprovalStatus.UNAPPROVED);
		if (result)
		{
			return "OK";
		} else
		{
			return "NOK";
		}
	}
}
