package com.tasly.anguo.core.anguostore.impl;

import de.hybris.platform.cms2.jalo.contents.ContentCatalog;

import com.tasly.anguo.core.anguostore.AnguoStoreService;
import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.model.AnguoStoreTempModel;


public class DefaultAnguoStoreService implements AnguoStoreService
{

	@Override
	public ContentCatalog getStoreContentCatalog(final String storeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void copyTempToAnguoStore(final AnguoStoreTempModel temp, final AnguoStoreModel store, final boolean approved)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void approveAnguoStoreCreation(final AnguoStoreTempModel tempStore)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void approveAnguoStoreModification(final AnguoStoreTempModel tempStore)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public AnguoStoreTempModel getAnguoStoreTempById(final String uid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnguoStoreModel getAnguoStoreById(final String uid)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
