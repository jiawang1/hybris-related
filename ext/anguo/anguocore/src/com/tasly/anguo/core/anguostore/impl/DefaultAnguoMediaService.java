package com.tasly.anguo.core.anguostore.impl;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.io.File;
import java.util.List;

import com.tasly.anguo.core.anguostore.AnguoMediaService;
import com.tasly.anguo.core.anguostore.dao.AnguoStoreDao;
import com.tasly.anguo.core.enums.StoreMediaSubFolder;


/**
 *
 */
public class DefaultAnguoMediaService implements AnguoMediaService
{
	private AnguoStoreDao anguoStoreDao;

	private MediaService mediaService;

	private ModelService modelService;

	//hmm, maybe no use in future
	@Override
	public String getAnguoStoreMediaFolder(final String storeId)
	{
		return storeId;//currently the folder path equals to store ID.
	}

	//hmm, maybe no use in future
	@Override
	public String getMediaSubFolderPath(final String storeId, final StoreMediaSubFolder subFolder)
	{
		//AnguoStoreModel anguoStoreModel = anguoStoreDao.findAnguoStoreById(storeId);
		return subFolder.getCode();

	}

	@Override
	public boolean uploadMedia(final File file, final String mediaId, final String storeId, final String subfolderPath)
	{

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
