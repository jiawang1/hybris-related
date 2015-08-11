package com.tasly.anguo.facades.customer;

import org.springframework.web.multipart.MultipartFile;

import de.hybris.platform.commercefacades.product.data.ImageData;

public interface CustomerIdentifyFacade {

	/**
	 * upload enterprise licenses image
	 * @param uploadFile
	 * @return
	 */
	public ImageData uploadEnterpriseLiceneses(final MultipartFile uploadFile)  throws Exception;
	
}
