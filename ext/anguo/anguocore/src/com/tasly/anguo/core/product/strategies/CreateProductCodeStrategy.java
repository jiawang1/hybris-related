package com.tasly.anguo.core.product.strategies;

import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;


/**
 * <p>
 * The strategy creates a product code.
 * </p>
 *
 * @author i313923
 */
public interface CreateProductCodeStrategy
{
	/**
	 * Generate a code for created product. Default implementation use {@link KeyGenerator}.
	 *
	 * @return Generated product code.
	 */
	public String generateProductCode();
}
