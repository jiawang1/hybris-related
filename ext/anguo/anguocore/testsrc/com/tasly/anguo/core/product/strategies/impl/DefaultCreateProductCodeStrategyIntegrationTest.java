package com.tasly.anguo.core.product.strategies.impl;

import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.tasly.anguo.core.product.strategies.CreateProductCodeStrategy;


/**
 * @author i313923
 */
@IntegrationTest
public class DefaultCreateProductCodeStrategyIntegrationTest extends ServicelayerTransactionalTest
{
	private final Logger LOG = Logger.getLogger(DefaultCreateProductCodeStrategyIntegrationTest.class);

	@Resource
	private CreateProductCodeStrategy createProductCodeStrategy;

	/**
	 * CreateProductCodeStrategy
	 */
	@Test
	public void testCreateProductCodeStrategy()
	{
		final String code1 = createProductCodeStrategy.generateProductCode();
		LOG.info("code1  : " + code1);
		final Long number1 = Long.parseLong(code1);
		LOG.info("number1: " + number1);
		final String code2 = createProductCodeStrategy.generateProductCode();
		LOG.info("code2  : " + code2);
		final Long number2 = Long.parseLong(code2);
		LOG.info("number2: " + number2);

		assertEquals(Long.valueOf(number1 + 1), Long.valueOf(number2));
	}
}
