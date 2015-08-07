package com.tasly.anguo.core.anguostore.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.Map;

import com.tasly.anguo.core.anguostore.dao.AnguoStoreDao;
import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.model.AnguoStoreTempModel;


/**
 *
 */
public class DefaultAnguoStoreDao extends AbstractItemDao implements AnguoStoreDao
{

	@Override
	public AnguoStoreModel findAnguoStoreById(final String uid)
	{
		final String query = "SELECT {" + AnguoStoreModel.PK + "} FROM {" + AnguoStoreModel._TYPECODE + "!} WHERE {"
				+ AnguoStoreModel.UID + "} =?" + AnguoStoreModel.UID;
		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(AnguoStoreModel.UID, uid);

		fQuery.addQueryParameters(params);
		final SearchResult<AnguoStoreModel> result = search(fQuery);
		if (result.getCount() > 0)
		{
			return result.getResult().iterator().next();
		}
		return null;
	}

	@Override
	public AnguoStoreTempModel findAnguoStoreTempById(final String uid)
	{
		final String query = "SELECT {" + AnguoStoreModel.PK + "} FROM {" + AnguoStoreTempModel._TYPECODE + "!} WHERE {"
				+ AnguoStoreModel.UID + "} =?" + AnguoStoreModel.UID;
		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(AnguoStoreModel.UID, uid);

		fQuery.addQueryParameters(params);
		final SearchResult<AnguoStoreTempModel> result = search(fQuery);
		if (result.getCount() > 0)
		{
			return result.getResult().iterator().next();
		}
		return null;
	}

}
