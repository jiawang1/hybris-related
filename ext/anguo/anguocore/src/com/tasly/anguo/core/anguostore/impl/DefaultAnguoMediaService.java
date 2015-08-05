package com.tasly.anguo.core.anguostore.impl;

import de.hybris.platform.core.model.media.MediaModel;

import java.io.File;
import java.util.List;

import com.tasly.anguo.core.anguostore.AnguoMediaService;
import com.tasly.anguo.core.constants.GeneratedAnguoCoreConstants.Enumerations.StoreMediaSubFolder;


public class DefaultAnguoMediaService implements AnguoMediaService
{

	@Override
	public String getAnguoStoreMediaFolder(final String storeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMediaSubFolderPath(final String storeId, final StoreMediaSubFolder subFolder)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean uploadMedia(final File file, final String mediaId, final String storeId, final String subfolderPath)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MediaModel> getMediasFromStore(final String storeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MediaModel> getMediasFromStore(final String storeId, final String subFolder)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
