/**
 * 
 */
package com.tasly.anguo.core.location.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.tasly.anguo.core.location.dao.DistrictDao;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

/**
 * @author i319019
 *
 */
public class DistrictDaoImplTest extends ServicelayerTransactionalTest {
	@Resource
	private DistrictDao districtDao;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/test/LocationDaoImplTest.csv", "windows-1252");
	}

	@Test
	public void getDistrictByCity()
	{
		List<DistrictModel> districtsOfNanjing = districtDao.getDistrictsByCityCode("320100");
		assertEquals(districtsOfNanjing.size(), 1);
		
		List<DistrictModel> districtsOfBeijing = districtDao.getDistrictsByCityCode("110100");
		assertEquals(districtsOfBeijing.size(), 3);
	}
	
	@Test
	public void getZeroDistrictByCity()
	{
		List<DistrictModel> districts = districtDao.getDistrictsByCityCode("XXXXX");
		assertEquals(districts.size(), 0);
	}
	
	@Test
	public void getDistrictByCode()
	{
		DistrictModel districtHaidian = districtDao.getDistrictByCode("110108");
		assertNotNull(districtHaidian);
		assertEquals(districtHaidian.getName(), "Haidian");
		
		DistrictModel districtNull = districtDao.getDistrictByCode("XXXXXX");
		assertNull(districtNull);
	}
}
