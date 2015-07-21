package com.tasly.anguo.core.location.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.tasly.anguo.core.location.CityService;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

public class CityServiceImplTest extends ServicelayerTransactionalTest {

	@Resource
	private CityService cityService;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/test/LocationDaoImplTest.csv", "windows-1252");
	}
	
	@Test
	public void getCityByRegion()
	{
		List<CityModel> cities = cityService.getCitiesByRegion("CN-11");
		assertEquals(cities.size(), 1);
		
		List<CityModel> citiesOfJiangsu = cityService.getCitiesByRegion("CN-32");
		assertEquals(citiesOfJiangsu.size(), 3);
	}
}
