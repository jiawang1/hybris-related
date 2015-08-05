/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.location.CityService;
import com.tasly.anguo.facades.data.AbstractLocationItemData;
import com.tasly.anguo.facades.location.CityFacade;
import com.tasly.anguo.facades.populators.AbstractLocationItemPopulator;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;


/**
 * @author i319019
 *
 */
public class DefaultCityFacade implements CityFacade {
	private CityService cityService;
	private AbstractLocationItemPopulator abstractLocationItemPopulator;
	
	/* (non-Javadoc)
	 * @see com.tasly.anguo.facades.location.CityFacade#getCitiesByRegionCode(java.lang.String)
	 */
	@Override
	public List<AbstractLocationItemData> getCitiesByRegionCode(String regionIsocode) {
		final List<CityModel> cityModels = cityService.getCitiesByRegion(regionIsocode);
		final List<AbstractLocationItemData> cityFacadeData = new ArrayList<AbstractLocationItemData>();
		for (final CityModel cityModel : cityModels)
		{
			final AbstractLocationItemData cityDto = new AbstractLocationItemData();
			this.abstractLocationItemPopulator.populate(cityModel, cityDto);

			cityFacadeData.add(cityDto);
		}
		return cityFacadeData;
	}

	/* (non-Javadoc)
	 * @see com.tasly.anguo.facades.location.CityFacade#getCityForCode(java.lang.String)
	 */
	@Override
	public AbstractLocationItemData getCityForCode(String cityCode) {
		CityModel cityModel = cityService.getCityByCode(cityCode);
		AbstractLocationItemData city = new AbstractLocationItemData();
		this.abstractLocationItemPopulator.populate(cityModel, city);
		return city;
	}

	/**
	 * @return
	 */
	public CityService getCityService() {
		return cityService;
	}

	/**
	 * @param cityService
	 */
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	/**
	 * @return the abstractLocationItemPopulator
	 */
	public AbstractLocationItemPopulator getAbstractLocationItemPopulator() {
		return abstractLocationItemPopulator;
	}

	/**
	 * @param abstractLocationItemPopulator the abstractLocationItemPopulator to set
	 */
	public void setAbstractLocationItemPopulator(
			AbstractLocationItemPopulator abstractLocationItemPopulator) {
		this.abstractLocationItemPopulator = abstractLocationItemPopulator;
	}
}
