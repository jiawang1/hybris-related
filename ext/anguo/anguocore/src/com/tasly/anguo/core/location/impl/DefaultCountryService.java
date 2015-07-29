/**
 * 
 */
package com.tasly.anguo.core.location.impl;

import java.util.List;

import com.tasly.anguo.core.location.CountryService;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.i18n.daos.CountryDao;

/**
 * @author i319019
 *
 */
public class DefaultCountryService implements CountryService {
	
	private CountryDao countryDao;

	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.CountryService#getAllCountries()
	 */
	@Override
	public List<CountryModel> getAllCountries() {
		return countryDao.findCountries();
	}

	/**
	 * @return the countryDao
	 */
	public CountryDao getCountryDao() {
		return countryDao;
	}

	/**
	 * @param countryDao the countryDao to set
	 */
	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

}
