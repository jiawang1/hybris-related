/**
 * 
 */
package com.tasly.anguo.core.location.dao.impl;

import java.util.List;

import com.tasly.anguo.core.location.dao.CityDao;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

/**
 * @author i319019
 *
 */
public class DefaultCityDao implements CityDao {
	
	private FlexibleSearchService flexibleSearchService;

	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.dao.CityDao#getCitiesByRegion(java.lang.String)
	 */
	@Override
	public List<CityModel> getCitiesByRegion(String regionCode) {
		final String queryString = //
		"SELECT {c:"
				+ CityModel.PK
				+ "}" //
				+ "FROM {" + CityModel._TYPECODE + " AS c JOIN "
				+ RegionModel._TYPECODE + " AS r " + "ON {r:" + RegionModel.PK
				+ "} = {c:" + CityModel.REGION + "}  " + " AND {r:"
				+ RegionModel.ISOCODE + "} =?paramRegionCode }"; //

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("paramRegionCode", regionCode);

		return getFlexibleSearchService().<CityModel> search(query).getResult();
	}
	
	/* (non-Javadoc)
	 * @see com.tasly.anguo.core.location.dao.CityDao#getCityByCode(java.lang.String)
	 */
	@Override
	public CityModel getCityByCode(String code) {
		// select {c:pk} from { City as c} WHERE {c:code} = '110100'

		final String queryString = //
		"SELECT {c:" + CityModel.PK
				+ "}" //
				+ "FROM {" + CityModel._TYPECODE + " AS c } " + "WHERE "
				+ "{c:" + CityModel.CODE + "}=?paramCityCode ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("paramCityCode", code);

		final SearchResult<CityModel> result = flexibleSearchService
				.<CityModel> search(query);
		if (result == null || result.getCount() == 0) {
			return null;
		}
		return result.getResult().get(0);
	}

	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}
