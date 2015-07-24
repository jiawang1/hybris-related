package com.tasly.anguo.facades.populators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tasly.anguo.core.model.CategoryAliasModel;
import com.tasly.anguo.facades.product.data.CategoryNodeData;
import com.tasly.anguo.facades.product.data.MgmtCatAliasData;
import com.tasly.anguo.facades.product.data.MgmtCategoryData;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class MgmtCategoryPopulator  implements Populator<CategoryModel, MgmtCategoryData> {

	@Override
	public void populate(CategoryModel source, MgmtCategoryData target)
			throws ConversionException {
		target.setCategoryCode(source.getCode());
		target.setName(source.getName());
		List<CategoryAliasModel> aliasList = source.getAlias();
		
		if(CollectionUtils.isNotEmpty(aliasList)){
			
			List<MgmtCatAliasData> aliasDatas = new ArrayList<MgmtCatAliasData>();
			
		    for(int i = 0;i < aliasList.size() ;i++){
		    	MgmtCatAliasData aliasData = new MgmtCatAliasData();
		    	CategoryAliasModel alias= aliasList.get(i);
		    	
		    	aliasData.setDescription(alias.getAlias());
		    	aliasDatas.add(aliasData);
		    }
		    
		    target.setAlias(aliasDatas);
		    
		}
	
	}

}
