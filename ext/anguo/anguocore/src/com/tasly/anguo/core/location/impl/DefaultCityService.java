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

	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.CityService#getCitiesByRegion(java.lang.String)
	 */
	@Override
	public List<CityModel> getCitiesByRegion(String regionCode) {
		return cityDao.getCitiesByRegion(regionCode);
	}

	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.CityService#getCityByCode(java.lang.String)
	 */
	@Override
	public CityModel getCityByCode(String code) {
		return cityDao.getCityByCode(code);
	}
	
	/**
	 * getter
	 * @return
	 */
	public CityDao getCityDao() {
		return cityDao;
	}

	/**
	 * setter
	 * @param cityDao
	 */
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}
}
