package com.tasly.anguo.initialdata.service;

import java.util.Iterator;
import java.util.List;

import de.hybris.platform.commerceservices.dataimport.impl.CoreDataImportService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.validation.services.ValidationService;

public class AnguoCoreDataImportService extends CoreDataImportService {

	
	/* 
	 * to avoid sync,overwrite this method
	 * @see de.hybris.platform.commerceservices.dataimport.impl.CoreDataImportService#execute(de.hybris.platform.commerceservices.setup.AbstractSystemSetup, de.hybris.platform.core.initialization.SystemSetupContext, java.util.List)
	 */
	@Override
	public void execute(AbstractSystemSetup systemSetup,SystemSetupContext context, List importData) {
		
		boolean importCoreData = systemSetup.getBooleanSystemSetupParameter(context, "importCoreData");
		
		if (importCoreData) {
			ImportData data;
			String extensionName = context.getExtensionName();
		    Iterator iterator = importData.iterator();
		    
		    while(iterator.hasNext()){
			  data = (ImportData) iterator.next();
			  
			  this.importCommonData(extensionName);
			  
			  importProductCatalog(extensionName, data.getProductCatalogName());
			  
			  List<String> contentCatalogList = data.getContentCatalogNames();
			  for(int i=0; i<contentCatalogList.size();i++){
				  this.importContentCatalog(extensionName, contentCatalogList.get(i));
			  }
			  
			  List<String> storeNameList = data.getStoreNames();			  
			  for(int i = 0; i < storeNameList.size();i++)
			      this.importStore(extensionName, storeNameList.get(i), data.getProductCatalogName()); 
			  
		    }

			ValidationService validation = (ValidationService) getBeanForName("validationService");
			
			validation.reloadValidationEngine();
		}
	}

}
