package com.tasly.anguo.facades.customer.impl;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.tasly.anguo.facades.customer.CustomerIdentifyFacade;

import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.media.MediaIOException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

public class DefaultCustomerIdentifyFacade implements CustomerIdentifyFacade{
	
	private static final Logger LOG = Logger.getLogger(DefaultCustomerIdentifyFacade.class);
	
	@Resource
	private ModelService modelService;
	@Resource
	private MediaService mediaService;
	@Resource
	private UserService userService;
	@Resource
	private CMSSiteService cmsSiteService;
	
	@Resource
	private CustomerIdentifyFacade customerIdentifyFacade;
	
	private Converter<MediaModel, ImageData> imageConverter;
	
	private final String LICENSE_FOLDER = "LICENSE";
	
	/**
	 * upload enterprise licenses image
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public ImageData uploadEnterpriseLiceneses(final MultipartFile file) throws Exception {
		try{
			final MediaModel mediaModel = createMedia(file);
			final ImageData imageData = imageConverter.convert(mediaModel);
			imageData.setCode(mediaModel.getCode());
			imageData.setName(file.getOriginalFilename());
			return imageData;
		}catch(Exception e){
			throw e;
		}
		
	}
	
	private MediaFolderModel createMediaFoler() throws Exception{
		MediaFolderModel folder = null;
		try
		{
			folder = mediaService.getFolder(LICENSE_FOLDER);
			if(folder == null) {
				folder = modelService.create(MediaFolderModel.class);
				folder.setQualifier(LICENSE_FOLDER);
				folder.setPath(LICENSE_FOLDER);
				modelService.save(folder);
				setSubfoldersDepthForFolder(folder, Integer.valueOf(4));
			}
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
			throw e;
		}
		return folder;
	}
	

	private void setSubfoldersDepthForFolder(final MediaFolderModel folder, final Integer hashingDepth)
	{
		Config.setParameter("media.folder." + folder.getQualifier() + ".hashing.depth",
				hashingDepth == null ? null : hashingDepth.toString());
	}
	
	private MediaModel createMedia(MultipartFile file) throws Exception {
		try
		{
			final MediaModel mediaModel = modelService.create(MediaModel.class);
			mediaModel.setCode(UUID.randomUUID().toString());
			mediaModel.setRealFileName(file.getOriginalFilename());
			mediaModel.setAltText(StringUtils.substring(file.getOriginalFilename(), 0,StringUtils.lastIndexOf(file.getOriginalFilename(), ".")));
			mediaModel.setFolder(createMediaFoler());
			mediaModel.setMime("image/jpeg");
			mediaModel.setCatalogVersion(cmsSiteService.getCurrentSite().getDefaultCatalog().getActiveCatalogVersion());
			modelService.save(mediaModel);
			mediaService.setStreamForMedia(mediaModel, file.getInputStream());
			return mediaModel;
		}
		catch (MediaIOException | IllegalArgumentException | IOException e)
		{
			LOG.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * @return the imageConverter
	 */
	public Converter<MediaModel, ImageData> getImageConverter() {
		return imageConverter;
	}

	/**
	 * @param imageConverter the imageConverter to set
	 */
	public void setImageConverter(Converter<MediaModel, ImageData> imageConverter) {
		this.imageConverter = imageConverter;
	}
	
	
	
}
