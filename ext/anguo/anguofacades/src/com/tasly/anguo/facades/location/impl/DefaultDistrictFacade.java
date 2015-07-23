/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.location.CityService;
import com.tasly.anguo.core.location.DistrictService;
import com.tasly.anguo.facades.data.CityData;
import com.tasly.anguo.facades.data.DistrictData;
import com.tasly.anguo.facades.location.CityFacade;
import com.tasly.anguo.facades.location.DistrictFacade;
import com.tasly.anguo.facades.populators.CityPopulator;
import com.tasly.anguo.facades.populators.DistrictPopulator;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;


/**
 * @author i319019
 *
 */
public class DefaultDistrictFacade implements DistrictFacade {
	private DistrictService districtService;
	private DistrictPopulator districtPopulator;
	
	@Override
	public List<DistrictData> getDistrictsByCityCode(String cityCode) {
		final List<DistrictModel> districtModels = districtService.getDistrictsByCity(cityCode);
		final List<DistrictData> districtFacadeData = new ArrayList<DistrictData>();
		for (final DistrictModel districtModel : districtModels)
		{
			final DistrictData district = new DistrictData();
			this.districtPopulator.populate(districtModel, district);

			districtFacadeData.add(district);
		}
		return districtFacadeData;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	public DistrictPopulator getDistrictPopulator() {
		return districtPopulator;
	}

	public void setDistrictPopulator(DistrictPopulator districtPopulator) {
		this.districtPopulator = districtPopulator;
	}
}
