/**
 * 
 */
package com.tasly.anguo.core.location.impl;

import java.util.List;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import com.tasly.anguo.core.location.CityService;
import com.tasly.anguo.core.location.dao.CityDao;

/**
 * @author i319019
 *
 */
public class DefaultCityService implements CityService {
	
	private CityDao cityDao;

	@Override
	public List<CityModel> getCitiesByRegion(String regionCode) {
		return cityDao.getCitiesByRegion(regionCode);
	}

	public CityDao getCityDao() {
		return cityDao;
	}

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

}
