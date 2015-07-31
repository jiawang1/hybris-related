/**
 * 
 */
package com.tasly.anguo.facades.location;

import java.util.List;

import com.tasly.anguo.facades.data.AbstractLocationItemData;

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
	List<AbstractLocationItemData> getDistrictsByCityCode(final String cityCode);
	
	/**
	 * Find district with given code
	 * @param code
	 * @return
	 */
	AbstractLocationItemData getDistrictByCode(final String code);
}
