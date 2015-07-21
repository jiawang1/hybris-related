/**
 * 
 */
package com.tasly.anguo.core.location;

import java.util.List;

import de.hybris.platform.core.model.c2l.RegionModel;

/**
 * @author i319019
 *
 */
public interface RegionService {
	/**
	 * Find a region under a country and a given code
	 * 
	 * @param countryCode
	 * @param regionCode
	 * @return
	 */
	RegionModel getRegionByCountryAndCode(final String countryCode,
			final String regionCode);

	/**
	 * Find all regions under a country
	 * 
	 * @param countryCode
	 * @return
	 */
	List<RegionModel> getRegionsForCountryCode(final String countryCode);
}
