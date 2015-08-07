package com.tasly.anguo.core.util;



import de.hybris.platform.commerceservices.url.impl.DefaultProductModelUrlResolver;
import de.hybris.platform.core.model.product.ProductModel;

import org.apache.commons.lang.StringUtils;

import com.tasly.anguo.core.model.AnguoStoreModel;


public class AnguoProductModelUrlResolver extends DefaultProductModelUrlResolver
{

	protected static final String PRODUCT_TOKEN = "/p/";

	@Override
	protected String resolveInternal(final ProductModel source)
	{

		String url = super.resolveInternal(source);

		if (url.indexOf(PRODUCT_TOKEN) > 0 && !url.endsWith(PRODUCT_TOKEN))
		{
			final String[] split = StringUtils.splitByWholeSeparator(url, PRODUCT_TOKEN);
			url = split[0] + "/p/" + urlSafe(split[1]);
		}

		//Add anguo store information into URL
		final AnguoStoreModel anguoStoreModel = source.getAnguoStore();

		url = getAnguoStoreString(anguoStoreModel) + url;

		return url;
	}

	/**
	 * get anguo store Stirng
	 *
	 * @param anguoStoreModel
	 * @return
	 */
	protected String getAnguoStoreString(final AnguoStoreModel anguoStoreModel)
	{
		if (anguoStoreModel == null)
		{
			return ""; // Default anguo store when the product dose not belong to any
		}

		return "/" + anguoStoreModel.getUid();

	}

}
