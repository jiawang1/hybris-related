package com.tasly.anguo.core.anguostore;

import de.hybris.platform.core.model.media.MediaModel;

import java.io.InputStream;
import java.util.List;

import com.tasly.anguo.core.enums.StoreMediaSubFolder;




/**
 * Anguo Media Service
 */
public interface AnguoMediaService
{
	/**
	 * 获取店铺相册主目录
	 *
	 * @param storeId
	 * @return 目录
	 */
	public String getAnguoStoreMediaFolder(String storeId);

	/**
	 * 获取店铺相册分目录
	 *
	 * @param storeId
	 * @param subFolder
	 *           来自枚举类型
	 * @return 分目录名
	 */
	public String getMediaSubFolderPath(String storeId, StoreMediaSubFolder subFolder);

	/**
	 * 上传media到店铺相册中
	 * 
	 * @param inputStream
	 *
	 * @param mediaId
	 * @param storeId
	 * @param subfolderPath
	 * @return true 如果上传成功；false 失败。
	 */
	public boolean uploadMedia(InputStream inputStream, String mediaId, String storeId, String subfolderPath);

	/**
	 * 获取所有media在店铺相册主目录下
	 *
	 * @param storeId
	 * @return mediamodel list
	 */
	public List<MediaModel> getMediasFromStore(String storeId);

	/**
	 * 获取所有media在店铺相册分目录下（若指定）
	 *
	 * @param storeId
	 * @param subFolder
	 * @return mediamodel list
	 */
	public List<MediaModel> getMediasFromStore(String storeId, String subFolder);
}
