/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.location.CountryService;
import com.tasly.anguo.facades.location.CountryFacade;

import de.hybris.platform.commercefacades.user.converters.populator.CountryPopulator;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.c2l.CountryModel;

/**
 * @author i319019
 *
 */
public class DefaultCountryFacade implements CountryFacade {
	
	private CountryService countryService;
	private CountryPopulator countryPopulator;

	/* (non-Javadoc)
	 * @see com.tasly.anguo.facades.location.CountryFacade#getAllCountries()
	 */
	@Override
	public List<CountryData> getAllCountries() {
		List<CountryModel> countryModels = countryService.getAllCountries();
		List<CountryData> countries = new ArrayList<CountryData>();
		for (CountryModel countryModel : countryModels) {
			CountryData country = new CountryData();
			countryPopulator.populate(countryModel, country);
			countries.add(country);
		}
		return countries;
	}

	/**
	 * @return the countryService
	 */
	public CountryService getCountryService() {
		return countryService;
	}

	/**
	 * @param countryService the countryService to set
	 */
	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	/**
	 * @return the countryPopulator
	 */
	public CountryPopulator getCountryPopulator() {
		return countryPopulator;
	}

	/**
	 * @param countryPopulator the countryPopulator to set
	 */
	public void setCountryPopulator(CountryPopulator countryPopulator) {
		this.countryPopulator = countryPopulator;
	}

}
