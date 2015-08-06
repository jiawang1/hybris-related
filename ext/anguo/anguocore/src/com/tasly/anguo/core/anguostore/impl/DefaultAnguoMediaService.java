package com.tasly.anguo.core.anguostore.impl;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.io.InputStream;
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
	public boolean uploadMedia(final InputStream inputStream, final String mediaId, final String storeId,
			final String subfolderPath)
	{
		final MediaModel media = createMediaModelInFolder(mediaId, createFolder(storeId));
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

	private MediaModel createMediaModelInFolder(final String code, final MediaFolderModel folder)
	{
		final MediaModel media = modelService.create(CatalogUnawareMediaModel.class);
		media.setCode(code);
		media.setFolder(folder);
		modelService.save(media);
		return media;
	}

	private MediaFolderModel createFolder(final String folderId)
	{
		MediaFolderModel folder;

		try
		{
			folder = mediaService.getFolder("test");
		}
		catch (final Exception e)
		{
			folder = modelService.create(MediaFolderModel.class);
			folder.setQualifier("test");
			folder.setPath("test");
			modelService.save(folder);
			setSubfoldersDepthForFolder(folder, Integer.valueOf(4));
			return folder;
		}

		return folder;

	}
}
