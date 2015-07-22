/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.location.DistrictService;
import com.tasly.anguo.facades.data.DistrictData;
import com.tasly.anguo.facades.location.DistrictFacade;
import com.tasly.anguo.facades.populators.DistrictPopulator;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;


/**
 * @author i319019
 *
 */
public class DefaultDistrictFacade implements DistrictFacade {
	private DistrictService districtService;
	private DistrictPopulator districtPopulator;
	
	/* (non-Javadoc)
	 * @see com.tasly.anguo.facades.location.DistrictFacade#getDistrictsByCityCode(java.lang.String)
	 */
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
	
	/* (non-Javadoc)
	 * @see com.tasly.anguo.facades.location.DistrictFacade#getDistrictByCode(java.lang.String)
	 */
	@Override
	public DistrictData getDistrictByCode(String code) {
		DistrictModel districtModel = districtService.getDistrictByCode(code);
		DistrictData district = new DistrictData();
		this.districtPopulator.populate(districtModel, district);
		return district;
	}

	/**
	 * getter
	 * @return
	 */
	public DistrictService getDistrictService() {
		return districtService;
	}

	/**
	 * setter
	 * @param districtService
	 */
	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	/**
	 * getter
	 * @return
	 */
	public DistrictPopulator getDistrictPopulator() {
		return districtPopulator;
	}

	/**
	 * setter
	 * @param districtPopulator
	 */
	public void setDistrictPopulator(DistrictPopulator districtPopulator) {
		this.districtPopulator = districtPopulator;
	}

}
