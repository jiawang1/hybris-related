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
	 * 根据店铺Id找到店铺模版
	 *
	 * @param storeId
	 * @return contentCatalog 店铺模版
	 */
	public ContentCatalog getStoreContentCatalog(String storeId);

	/**
	 * 拷贝临时店铺数据到店铺对象中
	 *
	 * @param temp
	 * @param store
	 * @param approved
	 *           是否已经批准
	 */
	public void copyTempToAnguoStore(AnguoStoreTempModel temp, AnguoStoreModel store, boolean approved);

	/**
	 * 批准店铺创建
	 *
	 * @param tempStore
	 */
	public void approveAnguoStoreCreation(AnguoStoreTempModel tempStore);

	/**
	 * 批准店铺修改
	 *
	 * @param tempStore
	 */
	public void approveAnguoStoreModification(AnguoStoreTempModel tempStore);

	/**
	 * 根据uid得到AnguoStoreTempModel
	 * 
	 * @param uid
	 * @return
	 */
	public AnguoStoreTempModel getAnguoStoreTempById(String uid);

	/**
	 * 根据uid得到AnguoStoreModel
	 * 
	 * @param uid
	 * @return
	 */
	public AnguoStoreModel getAnguoStoreById(String uid);
}
