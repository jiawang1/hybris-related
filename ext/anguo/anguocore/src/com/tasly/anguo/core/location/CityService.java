/**
 * 
 */
package com.tasly.anguo.core.location;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import java.util.List;

/**
 * @author i319019
 *
 */
public interface CityService {

	public List<CityModel> getCitiesByRegion(String regionCode);
}
