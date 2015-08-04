package com.tasly.anguo.core.anguostore;

import de.hybris.platform.cms2.jalo.contents.ContentCatalog;

import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.model.AnguoStoreTempModel;


/**
 *
 */
public interface AnguoStoreService
{
	/**
	 * 根据店铺
	 * 
	 * @param storeId
	 * @return contentCatalog 店铺模版
	 */
	public ContentCatalog getStoreContentCatalog(String storeId);

	public void copyTempToAnguoStore(AnguoStoreTempModel temp, AnguoStoreModel store, boolean approved);

	public void approveAnguoStoreCreation(AnguoStoreTempModel tempStore);

	public void approveAnguoStoreModification(AnguoStoreTempModel tempStore);

}
