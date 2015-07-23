/**
 * 
 */
package com.tasly.anguo.facades.location;

import java.util.List;

import com.tasly.anguo.facades.data.CityData;

/**
 * @author i319019
 *
 */
public interface CityFacade {
	List<CityData> getCitiesByRegionCode(final String regionIsocode);

	CityData getCityForCode(final String cityCode);
}
