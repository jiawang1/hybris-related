/**
 * 
 */
package com.tasly.anguo.core.location;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;

import java.util.List;

/**
 * @author i319019
 *
 */
public interface CityService {

	/**
	 * Find all cities under a region
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
