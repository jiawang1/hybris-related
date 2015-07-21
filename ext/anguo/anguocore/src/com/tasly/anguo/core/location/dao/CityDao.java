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

	public List<CityModel> getCitiesByRegion(String regionCode);
}
