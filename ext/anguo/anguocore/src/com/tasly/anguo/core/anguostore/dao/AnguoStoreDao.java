package com.tasly.anguo.core.anguostore.dao;

import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.model.AnguoStoreTempModel;


/**
 *
 */
public interface AnguoStoreDao
{
	/**
	 * 根据uid找到AnguoStoreModel
	 * 
	 * @param uid
	 * @return AnguoStoreModel
	 */
	public AnguoStoreModel findAnguoStoreById(String uid);

	/**
	 * 根据uid找到AnguoStoreTempModel
	 * 
	 * @param uid
	 * @return AnguoStoreTempModel
	 */
	public AnguoStoreTempModel findAnguoStoreTempById(String uid);

}
