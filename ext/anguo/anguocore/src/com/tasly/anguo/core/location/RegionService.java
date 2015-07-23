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
	
		RegionModel getRegionByCountryAndCode(final String countryCode, final String regionCode);

		List<RegionModel> getRegionsForCountryCode(final String countryCode);
}
