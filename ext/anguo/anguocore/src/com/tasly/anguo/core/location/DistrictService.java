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
	
	public List<DistrictModel> getDistrictsByCity(String cityCode);

}
