package com.tasly.anguo.facades.product.populator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.FastDateFormat;

import com.tasly.anguo.store.data.ProductListData;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ProductListPopulator implements Populator<SearchPageData, ProductListData>{
	 public static final FastDateFormat ISO_DATETIME_FORMAT  = FastDateFormat.getInstance("yyyy年MM月dd日 HH:mm:ss");
	@Override
	public void populate(SearchPageData source, ProductListData target)
			throws ConversionException 
	{
		int currentpage = source.getPagination().getCurrentPage()+1;
		//draw is for front end display,is set in controller,this is required by dataTable plugin
		//target.setDraw(currentpage);
		//below two fields is for plugin display
		target.setRecordsFiltered(source.getPagination().getTotalNumberOfResults());
		target.setRecordsTotal(source.getPagination().getTotalNumberOfResults());
		
		List<String[]> data = new ArrayList<String[]>();
		
		if(CollectionUtils.isNotEmpty(source.getResults()))
		  for(int i = 0;i < source.getResults().size();i++)
		  {
			  String[] element = new String[7];
			  ProductModel product = (ProductModel) source.getResults().get(i);
			  if(product.getThumbnail()!=null)
			  element[0] = product.getThumbnail().getUrl();
			  element[1] = product.getCode();
			  element[2] = product.getName();
			  element[3] = getCategoryPath(product);
			  
			  if(product.getLastestOnlineDate()!=null)
			  element[4] = ISO_DATETIME_FORMAT.format(product.getLastestOnlineDate());
			  
			  if(product.getAnguoStore()!=null)
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
			  if(CollectionUtils.isNotEmpty(category.getSupercategories())&&category.getLevel()==3)
			  {
				  categoryPath.insert(0,">").insert(0, category.getSupercategories().get(0).getName());
			  }
		  }else
		  {
			  //TODO:need to handle if there is no category?
		  }
		  
		  return categoryPath.toString();
	}

}
