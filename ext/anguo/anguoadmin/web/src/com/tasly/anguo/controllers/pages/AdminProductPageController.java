package com.tasly.anguo.controllers.pages;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hybris.platform.commercefacades.product.data.ProductData;

import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
@Controller
@RequestMapping("/product")
public class AdminProductPageController extends AbstractController {

	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Object showProducts(HttpServletRequest arg0,
			HttpServletResponse arg1){

		ProductData product = new ProductData();
		product.setName("test");
		product.setCode("p1");
		return product;
	}

}
