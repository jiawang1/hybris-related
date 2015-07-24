package com.tasly.anguo.core.location.dao;

import java.util.List;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;

/**
 * @author i319019
 *
 */
public interface DistrictDao {

	/**
	 * Find all districts under a city
	 * @param cityCode
	 * @return
	 */
	public List<DistrictModel> getDistrictsByCityCode (String cityCode);
	
	/**
	 * Find district with given code
	 * @param code
	 * @return
	 */
	public DistrictModel getDistrictByCode (String code);
}
