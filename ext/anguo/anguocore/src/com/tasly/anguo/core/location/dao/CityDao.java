/**
 * 
 */
package com.tasly.anguo.core.location.dao;

import java.util.List;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;

/**
 * Dao to retrieve Cites
 * @author i319019
 *
 */
public interface CityDao {

	/**
	 * Find all cities under given region
	 * @param regionCode
	 * @return
	 */
	public List<CityModel> getCitiesByRegion(String regionCode);
	
	/**
	 * Find city with given code
	 * @param code
	 * @return
	 */
	public CityModel getCityByCode(String code);
}
