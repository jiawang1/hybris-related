/**
 * 
 */
package com.tasly.anguo.core.location.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.tasly.anguo.core.location.DistrictService;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

/**
 * @author i319019
 *
 */
public class DistrictServiceImplTest extends ServicelayerTransactionalTest {
	@Resource
	private DistrictService districtService;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/test/LocationDaoImplTest.csv", "windows-1252");
	}

	@Test
	public void getCityByRegion()
	{
		List<DistrictModel> districtsOfNanjing = districtService.getDistrictsByCity("Nanjing");
		assertEquals(districtsOfNanjing.size(), 1);
		
		List<DistrictModel> districtsOfBeijing = districtService.getDistrictsByCity("Beijing");
		assertEquals(districtsOfBeijing.size(), 3);
	}
}
