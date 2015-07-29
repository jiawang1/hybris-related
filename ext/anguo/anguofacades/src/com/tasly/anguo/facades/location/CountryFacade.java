/**
 * 
 */
package com.tasly.anguo.facades.location;

import java.util.List;

import de.hybris.platform.commercefacades.user.data.CountryData;

/**
 * @author i319019
 *
 */
public interface CountryFacade {

	/**
	 * Get all countries
	 * @return
	 */
	List<CountryData> getAllCountries();
}
