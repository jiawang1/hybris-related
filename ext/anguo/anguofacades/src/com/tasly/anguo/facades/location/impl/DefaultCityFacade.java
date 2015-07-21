/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.location.CityService;
import com.tasly.anguo.facades.data.CityData;
import com.tasly.anguo.facades.location.CityFacade;
import com.tasly.anguo.facades.populators.CityPopulator;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;


/**
 * @author i319019
 *
 */
public class DefaultCityFacade implements CityFacade {
	private CityService cityService;
	private CityPopulator cityPopulator;
	
	@Override
	public List<CityData> getCitiesByRegionCode(String regionIsocode) {
		final List<CityModel> cityModels = cityService.getCitiesByRegion(regionIsocode);
		final List<CityData> cityFacadeData = new ArrayList<CityData>();
		for (final CityModel cityModel : cityModels)
		{
			final CityData cityDto = new CityData();
			this.cityPopulator.populate(cityModel, cityDto);

			cityFacadeData.add(cityDto);
		}
		return cityFacadeData;
	}

	@Override
	public CityData getCityForCode(String cityCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public CityPopulator getCityPopulator() {
		return cityPopulator;
	}

	public void setCityPopulator(CityPopulator cityPopulator) {
		this.cityPopulator = cityPopulator;
	}
}
