package com.tasly.anguo.controllers.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.impl.DefaultCatalogService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.commercefacades.catalog.CatalogOption;
import de.hybris.platform.commercefacades.catalog.data.CatalogData;
import de.hybris.platform.commercefacades.catalog.impl.DefaultCatalogFacade;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.c2l.Language;

import org.apache.log4j.Logger;
import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.common.collect.ImmutableSet;
@Controller
@RequestMapping("/product")
public class AdminProductPageController extends AbstractController {
	Logger LOG = Logger.getLogger(AdminProductPageController.class);
    @Resource
	DefaultCatalogFacade catalogFacade;
//    @Resource
//    CatalogService catalogService;
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Object showProduct(HttpServletRequest arg0,
			HttpServletResponse arg1){

		ProductData product = new ProductData();
		
//		for(CatalogModel catalogModel : catalogService.getAllCatalogs())
//		{
//			LOG.info("Catalog ZH name : " + catalogModel.getName(Locale.CHINESE));
//		}
		JaloSession jaloSession = JaloSession.getCurrentSession();
		Language lang = jaloSession.getC2LManager().getLanguageByIsoCode("zh");
		jaloSession.getSessionContext().setLanguage(lang);
		final List<CatalogData> catalogs = catalogFacade.getAllCatalogsWithOptions(ImmutableSet.of(CatalogOption.BASIC));
		List<String> catalogNames = new ArrayList<String>();
		
		for (int i=0; i<catalogs.size();i++) 
		{
			String contentCatalogId = catalogs.get(i).getId();
			
			if(contentCatalogId.toUpperCase().contains("STORE")){
				String contentCatalogName = catalogs.get(i).getName();
				catalogNames.add(contentCatalogName);
			}			
		}
		
		product.setName("test");
		product.setCode("p1");
//		return product;
		
		return catalogNames;
	}

}
