/**
 * 
 */
package com.tasly.anguo.core.location.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.tasly.anguo.core.location.dao.CityDao;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

/**
 * @author i319019
 *
 */

@IntegrationTest
public class CityDaoImplTest extends ServicelayerTransactionalTest
{
	@Resource
	private CityDao cityDao;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/test/LocationDaoImplTest.csv", "windows-1252");
	}

	@Test
	public void getCityByRegion()
	{
		List<CityModel> cities = cityDao.getCitiesByRegion("CN-11");
		assertEquals(cities.size(), 1);
		
		List<CityModel> citiesOfJiangsu = cityDao.getCitiesByRegion("CN-32");
		assertEquals(citiesOfJiangsu.size(), 3);
	}
	
	@Test
	public void getZeroCityByRegion()
	{
		List<CityModel> cities = cityDao.getCitiesByRegion("CN-XX");
		assertEquals(cities.size(), 0);
	}
	
	@Test
	public void getCityByCode()
	{
		CityModel cityBeijing = cityDao.getCityByCode("110100");
		assertNotNull(cityBeijing);
		assertEquals(cityBeijing.getName(), "Beijing");
		
		CityModel cityNull = cityDao.getCityByCode("XXXXXX");
		assertNull(cityNull);
	}
}
