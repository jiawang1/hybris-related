/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.location.DistrictService;
import com.tasly.anguo.facades.data.AbstractLocationItemData;
import com.tasly.anguo.facades.location.DistrictFacade;
import com.tasly.anguo.facades.populators.AbstractLocationItemPopulator;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;


/**
 * @author i319019
 *
 */
public class DefaultDistrictFacade implements DistrictFacade {
	private DistrictService districtService;
	private AbstractLocationItemPopulator abstractLocationItemPopulator;
	
	/* (non-Javadoc)
	 * @see com.tasly.anguo.facades.location.DistrictFacade#getDistrictsByCityCode(java.lang.String)
	 */
	@Override
	public List<AbstractLocationItemData> getDistrictsByCityCode(String cityCode) {
		final List<DistrictModel> districtModels = districtService.getDistrictsByCity(cityCode);
		final List<AbstractLocationItemData> districtFacadeData = new ArrayList<AbstractLocationItemData>();
		for (final DistrictModel districtModel : districtModels)
		{
			final AbstractLocationItemData district = new AbstractLocationItemData();
			this.abstractLocationItemPopulator.populate(districtModel, district);

			districtFacadeData.add(district);
		}
		return districtFacadeData;
	}
	
	/* (non-Javadoc)
	 * @see com.tasly.anguo.facades.location.DistrictFacade#getDistrictByCode(java.lang.String)
	 */
	@Override
	public AbstractLocationItemData getDistrictByCode(String code) {
		DistrictModel districtModel = districtService.getDistrictByCode(code);
		AbstractLocationItemData district = new AbstractLocationItemData();
		this.abstractLocationItemPopulator.populate(districtModel, district);
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
