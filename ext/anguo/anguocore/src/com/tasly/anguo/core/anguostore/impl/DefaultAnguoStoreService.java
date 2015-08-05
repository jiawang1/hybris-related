package com.tasly.anguo.core.anguostore.impl;

import de.hybris.platform.cms2.jalo.contents.ContentCatalog;

import com.tasly.anguo.core.anguostore.AnguoStoreService;
import com.tasly.anguo.core.anguostore.dao.AnguoStoreDao;
import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.model.AnguoStoreTempModel;


/**
 *
 */
public class DefaultAnguoStoreService implements AnguoStoreService
{
	private AnguoStoreDao anguoStoreDao;

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
		return anguoStoreDao.findAnguoStoreTempById(uid);
	}

	@Override
	public AnguoStoreModel getAnguoStoreById(final String uid)
	{
		return anguoStoreDao.findAnguoStoreById(uid);
	}

	public AnguoStoreDao getAnguoStoreDao()
	{
		return anguoStoreDao;
	}

	public void setAnguoStoreDao(final AnguoStoreDao anguoStoreDao)
	{
		this.anguoStoreDao = anguoStoreDao;
	}

}
