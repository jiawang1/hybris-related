package com.tasly.anguo.core.util;



import org.apache.commons.lang.StringUtils;

import de.hybris.platform.commerceservices.url.impl.DefaultProductModelUrlResolver;
import de.hybris.platform.core.model.product.ProductModel;

public class AnguoProductModelUrlResolver extends
		DefaultProductModelUrlResolver {
	
	protected static final String PRODUCT_TOKEN = "/p/";
	
	 protected String resolveInternal(ProductModel source){
		 
		 String url = super.resolveInternal(source);
		 
		 if(url.indexOf(PRODUCT_TOKEN) > 0 && !url.endsWith(PRODUCT_TOKEN)){
			 String[] split =  StringUtils.splitByWholeSeparator(url, PRODUCT_TOKEN);
			 url = split[0] + "/p/" + urlSafe(split[1]);
		 }
		 
		 return url;
	 }

}
