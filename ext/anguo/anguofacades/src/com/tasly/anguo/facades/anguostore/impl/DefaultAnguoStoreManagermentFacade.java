package com.tasly.anguo.facades.anguostore.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.tasly.anguo.core.enums.StoreApproveStatus;
import com.tasly.anguo.core.model.AnguoPlatformServiceModel;
import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.model.AnguoStoreTempModel;
import com.tasly.anguo.facades.anguostore.AnguoStoreManagermentFacade;
import com.tasly.anguo.store.data.AnguoStoreManagermentData;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.media.MediaIOException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;


/**
 *
 * @author i313919, Yang
 * @since 2015-07-24 10:23:08
 *
 */
public class DefaultAnguoStoreManagermentFacade implements AnguoStoreManagermentFacade
{

	private ModelService modelService;

	private UserService userService;

	private TimeService timeService;

	private MediaService mediaService;

	private CatalogVersionService catalogVersionService;

	private MediaStorageConfigService mediaStorageConfigService;

	private KeyGenerator anguoStoreCodeGenerator;

	private Populator<AnguoStoreManagermentData, AnguoStoreTempModel> anguoStoreReversePopulator;

	@Override
	public void addStore(final AnguoStoreManagermentData storeData) throws MediaIOException, IllegalArgumentException, IOException
	{
		final AnguoStoreTempModel newStore = getModelService().create(AnguoStoreTempModel.class);
		getAnguoStoreReversePopulator().populate(storeData, newStore);

		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		newStore.setOwner(customer);
		newStore.setUid(anguoStoreCodeGenerator.generate().toString());
		newStore.setRegisterDate(timeService.getCurrentTime());

		//TODO the folder and catalog version is for mock---start
		final String code = RandomStringUtils.randomAlphabetic(5);
		final MediaModel media = createEmptyMediaModelInFolder(code, createTestFolder());
		media.setMime("image/jpeg");
		CatalogVersionModel catalogVersionModel = null;
		for (final CatalogVersionModel model : catalogVersionService.getSessionCatalogVersions())
		{
			catalogVersionModel = model;
		}
		media.setCatalogVersion(catalogVersionModel);
		media.setCode(String.valueOf(System.currentTimeMillis()));
		//		getModelService().save(media);
		//TODO the folder and catalog version is for mock---end

		mediaService.setStreamForMedia(media, storeData.getLogo().getInputStream(), storeData.getLogo().getName(), "image/jpeg");

		newStore.setLogo(media);

		//AM-26 mock data add by liyao -start
		final List<AnguoPlatformServiceModel> mockPlatformService = new ArrayList<AnguoPlatformServiceModel>();
		final AnguoPlatformServiceModel anguoPlatformServiceModel01 = new AnguoPlatformServiceModel();
		anguoPlatformServiceModel01.setDescription("实名认证");
		final AnguoPlatformServiceModel anguoPlatformServiceModel02 = new AnguoPlatformServiceModel();
		anguoPlatformServiceModel02.setDescription("七天无条件退货");
		mockPlatformService.add(anguoPlatformServiceModel01);
		mockPlatformService.add(anguoPlatformServiceModel02);
		newStore.setAnguoPlatformService(mockPlatformService);
//		newStore.setStoreLevel("中级");
//		newStore.setStoreTemplate("基础");
		//AM-26 mock data add by liyao -end

		customer.setAnguoStoreTemp(newStore);

		getModelService().save(customer);
	}

	private MediaModel createEmptyMediaModelInFolder(final String code, final MediaFolderModel folder)
	{
		final MediaModel media = modelService.create(CatalogUnawareMediaModel.class);
		media.setCode(code);
		media.setFolder(folder);
		modelService.save(media);
		return media;
	}

	private MediaFolderModel createTestFolder()
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

	private void setSubfoldersDepthForFolder(final MediaFolderModel folder, final Integer hashingDepth)
	{
		Config.setParameter("media.folder." + folder.getQualifier() + ".hashing.depth",
				hashingDepth == null ? null : hashingDepth.toString());
	}

	public TimeService getTimeService()
	{
		return timeService;
	}

	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}

	public KeyGenerator getAnguoStoreCodeGenerator()
	{
		return anguoStoreCodeGenerator;
	}

	public void setAnguoStoreCodeGenerator(final KeyGenerator anguoStoreCodeGenerator)
	{
		this.anguoStoreCodeGenerator = anguoStoreCodeGenerator;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public Populator<AnguoStoreManagermentData, AnguoStoreTempModel> getAnguoStoreReversePopulator()
	{
		return anguoStoreReversePopulator;
	}

	public void setAnguoStoreReversePopulator(final Populator<AnguoStoreManagermentData, AnguoStoreTempModel> anguoStoreReversePopulator)
	{
		this.anguoStoreReversePopulator = anguoStoreReversePopulator;
	}

	public MediaService getMediaService()
	{
		return mediaService;
	}

	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	public MediaStorageConfigService getMediaStorageConfigService()
	{
		return mediaStorageConfigService;
	}

	public void setMediaStorageConfigService(final MediaStorageConfigService mediaStorageConfigService)
	{
		this.mediaStorageConfigService = mediaStorageConfigService;
	}

	@Override
	public void updateStore(final AnguoStoreManagermentData storeData) throws MediaIOException, IllegalArgumentException, IOException {
		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		final AnguoStoreTempModel anguoStoreTempModel = customer.getAnguoStoreTemp();
		AnguoStoreModel anguoStoreModel = customer.getAnguoStore();

		final String logoName = storeData.getLogo().getName();
		if (logoName != null && !logoName.isEmpty()) {
			mediaService.setStreamForMedia(anguoStoreTempModel.getLogo(), storeData.getLogo().getInputStream(), logoName, "image/jpeg");
		}

		getAnguoStoreReversePopulator().populate(storeData, anguoStoreTempModel);
		anguoStoreModel = anguoStoreTempModel;

		if (StoreApproveStatus.MODIFY_APPROVE.equals(anguoStoreTempModel.getStatus())) {
			getModelService().save(anguoStoreTempModel);
			getModelService().save(anguoStoreModel);
		}else if(StoreApproveStatus.MODIFY_WAIT.equals(anguoStoreTempModel.getStatus()) || StoreApproveStatus.CREATE_WAIT.equals(anguoStoreTempModel.getStatus())){
			getModelService().save(anguoStoreTempModel);
		}
	}

}
