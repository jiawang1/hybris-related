/**
 * 
 */
package com.tasly.anguo.facades.location;

import java.util.List;

import de.hybris.platform.commercefacades.user.data.RegionData;

/**
 * @author i319019
 *
 */
public interface RegionFacade {
	
	List<RegionData> getRegionsForCountryCode(final String countryCode);

	RegionData getRegionByCountryAndCode(final String countryCode, final String regionCode);
}
