/**
 * 
 */
package com.tasly.anguo.core.location.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.tasly.anguo.core.location.CountryService;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

/**
 * @author i319019
 *
 */
public class CountryServiceImplTest extends ServicelayerTransactionalTest {
	Logger logger = Logger.getLogger(CountryServiceImplTest.class);
	@Resource
	private CountryService countryService;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/test/LocationDaoImplTest.csv", "windows-1252");
	}
	
	@Test
	public void getCountries()
	{
		List<CountryModel> countries = countryService.getAllCountries();
		for (CountryModel countryModel : countries) {
			logger.info("Country Code : " + countryModel.getIsocode());
		}
		assertEquals(countries.size(), 6);
	}
}
