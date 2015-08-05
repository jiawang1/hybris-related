package com.tasly.anguo.facades.product.populator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tasly.anguo.store.data.ProductListData;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ProductListPopulator implements Populator<SearchPageData, ProductListData>{

	@Override
	public void populate(SearchPageData source, ProductListData target)
			throws ConversionException 
	{
		int currentpage = source.getPagination().getCurrentPage()+1;
		target.setDraw(currentpage);
		target.setRecordsFiltered(source.getPagination().getTotalNumberOfResults());
		List<String[]> data = new ArrayList<String[]>();
		
		if(CollectionUtils.isNotEmpty(source.getResults()))
		  for(int i = 0;i < source.getResults().size();i++)
		  {
			  String[] element = new String[7];
			  ProductModel product = (ProductModel) source.getResults().get(i);
			  element[0] = product.getCode();
			  element[1] = product.getCode();
			  element[2] = product.getName();
			  element[3] = getCategoryPath(product);
			  element[4] = product.getLastestOnlineDate().toString();
			  element[5] = product.getAnguoStore().getName();
			  element[6] = product.getCode();
			  
			  data.add(element);
		  }
		
		target.setData(data);
		
	}
	
	private String getCategoryPath(ProductModel product)
	{
		  StringBuilder categoryPath = new StringBuilder();
		  
		  if(CollectionUtils.isNotEmpty(product.getSupercategories()))
		  {
	          CategoryModel category = product.getSupercategories().iterator().next();
			  categoryPath.append(category.getName());
			  if(CollectionUtils.isNotEmpty(category.getSupercategories()))
			  {
				  categoryPath.append(">").append(category.getSupercategories().get(0));
			  }
		  }else
		  {
			  //TODO:need to handle the UNCHECK and UNAPPROVE case,maybe there is no category
		  }
		  
		  return categoryPath.toString();
	}

}
