/**
 * 
 */
package com.tasly.anguo.core.location.dao.impl;

import java.util.List;

import com.tasly.anguo.core.location.dao.DistrictDao;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

/**
 * @author i319019
 *
 */
public class DefaultDistrictDao implements DistrictDao {
	
	private FlexibleSearchService flexibleSearchService;

	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.dao.DistrictDao#getDistrictsByCityCode(java.lang.String)
	 */
	@Override
	public List<DistrictModel> getDistrictsByCityCode(String cityCode) {
		final String queryString = //
				"SELECT {d:"
						+ DistrictModel.PK
						+ "}" //
						+ "FROM {" + DistrictModel._TYPECODE + " AS d JOIN "
						+ CityModel._TYPECODE + " AS c " + "ON {c:" + CityModel.PK
						+ "} = {d:" + DistrictModel.CITY + "}  " + " AND {c:"
						+ CityModel.CODE + "} =?paramCityCode }"; //

				final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
				query.addQueryParameter("paramCityCode", cityCode);

				return getFlexibleSearchService().<DistrictModel> search(query).getResult();
	}
	
	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.dao.DistrictDao#getDistrictByCode(java.lang.String)
	 */
	@Override
	public DistrictModel getDistrictByCode(String code) {
		// select {d:pk} from { District as d} WHERE {d:code} = '110108'

		final String queryString = //
		"SELECT {d:" + DistrictModel.PK
				+ "}" //
				+ "FROM {" + DistrictModel._TYPECODE + " AS d } " + "WHERE "
				+ "{d:" + DistrictModel.CODE + "}=?paramDistrictCode ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("paramDistrictCode", code);

		final SearchResult<DistrictModel> result = flexibleSearchService
				.<DistrictModel> search(query);
		if (result == null || result.getCount() == 0) {
			return null;
		}
		return result.getResult().get(0);
	}

	/**
	 * getter
	 * @return
	 */
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	/**
	 * setter
	 * @param flexibleSearchService
	 */
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}
