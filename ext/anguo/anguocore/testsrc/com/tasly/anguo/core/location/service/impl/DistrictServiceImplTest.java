/**
 * 
 */
package com.tasly.anguo.core.location.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
	public void getDistrictByCity()
	{
		List<DistrictModel> districtsOfNanjing = districtService.getDistrictsByCity("320100");
		assertEquals(districtsOfNanjing.size(), 1);
		
		List<DistrictModel> districtsOfBeijing = districtService.getDistrictsByCity("110100");
		assertEquals(districtsOfBeijing.size(), 3);
	}
	
	@Test
	public void getZeroDistrictByCity()
	{
		List<DistrictModel> districts = districtService.getDistrictsByCity("XXXXX");
		assertEquals(districts.size(), 0);
	}
	
	@Test
	public void getDistrictByCode()
	{
		DistrictModel districtHaidian = districtService.getDistrictByCode("110108");
		assertNotNull(districtHaidian);
		assertEquals(districtHaidian.getName(), "Haidian");
		
		DistrictModel districtNull = districtService.getDistrictByCode("XXXXXX");
		assertNull(districtNull);
	}
}
