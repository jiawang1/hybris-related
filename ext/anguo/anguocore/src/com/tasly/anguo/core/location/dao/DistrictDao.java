package com.tasly.anguo.core.location.dao;

import java.util.List;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;

/**
 * @author i319019
 *
 */
public interface DistrictDao {

	public List<DistrictModel> getDistrictsByCityCode (String cityCode);
}
