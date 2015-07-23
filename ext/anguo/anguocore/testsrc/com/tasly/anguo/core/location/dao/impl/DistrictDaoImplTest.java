/**
 * 
 */
package com.tasly.anguo.core.location.dao.impl;

import static org.junit.Assert.assertEquals;

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
	public void getCityByRegion()
	{
		List<DistrictModel> districtsOfNanjing = districtDao.getDistrictsByCityCode("Nanjing");
		assertEquals(districtsOfNanjing.size(), 1);
		
		List<DistrictModel> districtsOfBeijing = districtDao.getDistrictsByCityCode("Beijing");
		assertEquals(districtsOfBeijing.size(), 3);
	}
}
