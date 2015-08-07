package com.tasly.anguo.core.anguostore.impl;

import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.servicelayer.internal.converter.util.ModelUtils;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;

import org.apache.commons.lang.ArrayUtils;

import com.tasly.anguo.core.anguostore.AnguoStoreService;
import com.tasly.anguo.core.anguostore.dao.AnguoStoreDao;
import com.tasly.anguo.core.constants.AnguoCoreConstants;
import com.tasly.anguo.core.enums.StoreApproveStatus;
import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.model.AnguoStoreTempModel;


/**
 *
 */
public class DefaultAnguoStoreService extends AbstractBusinessService implements AnguoStoreService
{

	/**
	 * configuration in properties file
	 */
	public static final String APPROVEFIELDS = "anguostore.fields.need.approve";
	/**
	 * all fields in AnguoStoreModel
	 */
	public static final String[] FIELDS = new String[]
	{ AnguoStoreModel.NAME, AnguoStoreModel.LOGO, AnguoStoreModel.CONTENTCATALOG, AnguoStoreModel.DESCRIPTION,
			AnguoStoreModel.COUNTRY, AnguoStoreModel.REGION, AnguoStoreModel.CITY, AnguoStoreModel.CITYDISTRICT,
			AnguoStoreModel.STREET, AnguoStoreModel.CONTACTNAME1, AnguoStoreModel.CONTACTPHONE1, AnguoStoreModel.CONTACTNAME2,
			AnguoStoreModel.CONTACTPHONE2, AnguoStoreModel.CONTACTNAME3, AnguoStoreModel.CONTACTPHONE3, AnguoStoreModel.QQ,
			AnguoStoreModel.FAX, AnguoStoreModel.TELEPHONE, AnguoStoreModel.REGISTERDATE, AnguoStoreModel.STORELEVEL,
			AnguoStoreModel.STORETEMPLATE, AnguoStoreModel.STORETYPE, AnguoStoreModel.ANGUOPLATFORMSERVICE };

	private AnguoStoreDao anguoStoreDao;

	private ModelService modelService;

	@Override
	public ContentCatalogModel getStoreContentCatalog(final String storeId)
	{
		final AnguoStoreModel anguoStoreModel = this.getAnguoStoreById(storeId);
		return anguoStoreModel == null ? null : anguoStoreModel.getContentCatalog();
	}

	@Override
	public void copyTempToAnguoStore(final AnguoStoreTempModel temp, final AnguoStoreModel store, final boolean isNew,
			final boolean approved)
	{
		if (temp == null || store == null)
		{
			return; // do nothing if parameter is null
		}
		String[] fieldsToBeCopied = (String[]) ArrayUtils.clone(FIELDS);
		if (!approved) //remove the fields that need to be approved
		{
			final String fieldsNeedApprove = Config.getParameter(APPROVEFIELDS);
			final String[] approveFileds = fieldsNeedApprove.split(",");
			for (final String approveFiled : approveFileds)
			{
				fieldsToBeCopied = (String[]) ArrayUtils.removeElement(fieldsToBeCopied, approveFiled);
			}
		}
		//copy fields
		for (final String field : fieldsToBeCopied)
		{
			ModelUtils.setFieldValue(store, field, ModelUtils.getFieldValue(temp, field));
		}
		if (approved)
		{
			if (isNew)
			{
				temp.setStatus(StoreApproveStatus.CREATE_APPROVE);
			}
			else
			{
				temp.setStatus(StoreApproveStatus.MODIFY_APPROVE);
			}
			modelService.saveAll(temp, store);
		}
		else
		{
			modelService.save(store);
		}
	}

	//I don't think the method is really needed
	@Override
	public void approveAnguoStoreCreation(final AnguoStoreTempModel tempStore)
	{
		// TODO Auto-generated method stub

	}

	//I don't think the method is really needed
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

	@Override
	public void setSessionAnguoStore(final String storeId)
	{
		getSessionService().setAttribute(AnguoCoreConstants.ANGUO_STORE_SESSION, storeId);
	}

	@Override
	public String getSessionAnguoStore()
	{
		final String anguoStoreId = getSessionService().getAttribute(AnguoCoreConstants.ANGUO_STORE_SESSION);
		return anguoStoreId == null ? "" : anguoStoreId;
	}


	public AnguoStoreDao getAnguoStoreDao()
	{
		return anguoStoreDao;
	}

	public void setAnguoStoreDao(final AnguoStoreDao anguoStoreDao)
	{
		this.anguoStoreDao = anguoStoreDao;
	}

	@Override
	public ModelService getModelService()
	{
		return modelService;
	}

	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}



}
