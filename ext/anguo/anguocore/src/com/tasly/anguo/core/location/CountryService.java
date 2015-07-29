/**
 * 
 */
package com.tasly.anguo.core.location;

import java.util.List;

import de.hybris.platform.core.model.c2l.CountryModel;

/**
 * @author i319019
 *
 */
public interface CountryService {

	/**
	 * Get all countries
	 * @return
	 */
	public List<CountryModel> getAllCountries();
}
