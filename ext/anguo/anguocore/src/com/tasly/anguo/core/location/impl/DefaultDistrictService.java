/**
 * 
 */
package com.tasly.anguo.core.location.impl;

import java.util.List;

import com.tasly.anguo.core.location.DistrictService;
import com.tasly.anguo.core.location.dao.DistrictDao;

import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;

/**
 * @author i319019
 *
 */
public class DefaultDistrictService implements DistrictService {
	
	private DistrictDao districtDao;

	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.DistrictService#getDistrictsByCity(java.lang.String)
	 */
	@Override
	public List<DistrictModel> getDistrictsByCity(String cityCode) {
		return districtDao.getDistrictsByCityCode(cityCode);
	}

	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.DistrictService#getDistrictByCode(java.lang.String)
	 */
	@Override
	public DistrictModel getDistrictByCode(String code) {
		return districtDao.getDistrictByCode(code);
	}

	/**
	 * getter
	 * @return
	 */
	public DistrictDao getDistrictDao() {
		return districtDao;
	}

	/**
	 * setter
	 * @param districtDao
	 */
	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}
}
