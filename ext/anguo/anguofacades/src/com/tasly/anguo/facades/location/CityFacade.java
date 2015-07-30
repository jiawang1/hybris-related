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
public interface CityFacade {
	List<AbstractLocationItemData> getCitiesByRegionCode(final String regionIsocode);

	AbstractLocationItemData getCityForCode(final String cityCode);
}
