/**
 * 
 */
package com.tasly.anguo.facades.location;

import java.util.List;

import com.tasly.anguo.facades.data.DistrictData;

/**
 * @author i319019
 *
 */
public interface DistrictFacade {
	List<DistrictData> getDistrictsByCityCode(final String cityCode);
}
