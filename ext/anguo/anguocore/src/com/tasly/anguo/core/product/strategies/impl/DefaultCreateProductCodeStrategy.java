package com.tasly.anguo.core.product.strategies.impl;

import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;

import org.springframework.beans.factory.annotation.Required;

import com.tasly.anguo.core.product.strategies.CreateProductCodeStrategy;


/**
 * AM-128.
 * 
 * @author i313923
 */
public class DefaultCreateProductCodeStrategy implements CreateProductCodeStrategy
{
	private KeyGenerator keyGenerator;

	/**
	 * Set KeyGenerator.
	 *
	 * @param keyGenerator
	 */
	@Required
	public void setKeyGenerator(final KeyGenerator keyGenerator)
	{
		this.keyGenerator = keyGenerator;
	}

	@Override
	public String generateProductCode()
	{
		final Object generatedValue = keyGenerator.generate();
		if (generatedValue instanceof String)
		{
			return (String) generatedValue;
		}
		else
		{
			return String.valueOf(generatedValue);
		}
	}
}
