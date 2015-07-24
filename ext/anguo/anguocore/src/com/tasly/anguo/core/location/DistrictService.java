/**
 * 
 */
package com.tasly.anguo.core.location;

import java.util.List;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;

/**
 * @author i319019
 *
 */
public interface DistrictService {
	
	/**
	 * Find all districts under given city
	 * @param cityCode
	 * @return
	 */
	public List<DistrictModel> getDistrictsByCity(String cityCode);

	/**
	 * Find district with given code
	 * @param code
	 * @return
	 */
	public DistrictModel getDistrictByCode (String code);
}
