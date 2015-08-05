package com.tasly.anguo.facades.anguostore;

import java.io.IOException;

import com.tasly.anguo.store.data.AnguoStoreManagermentData;

import de.hybris.platform.servicelayer.media.MediaIOException;

public interface AnguoStoreManagermentFacade {
	
	void addStore(AnguoStoreManagermentData storeData)  throws MediaIOException, IllegalArgumentException, IOException;
	
	void updateStore(AnguoStoreManagermentData storeData) throws MediaIOException, IllegalArgumentException, IOException;
}
