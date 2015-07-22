/**
 * 
 */
package com.tasly.anguo.facades.location;

import java.util.List;

import com.tasly.anguo.facades.data.DistrictData;

/**
 * @author i319019
 *
 */
public interface DistrictFacade {
	/**
	 * Find all districts under a city
	 * @param cityCode
	 * @return
	 */
	List<DistrictData> getDistrictsByCityCode(final String cityCode);
	
	/**
	 * Find district with given code
	 * @param code
	 * @return
	 */
	DistrictData getDistrictByCode(final String code);
}
