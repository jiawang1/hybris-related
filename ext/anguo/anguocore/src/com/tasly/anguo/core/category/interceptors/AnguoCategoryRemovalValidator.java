package com.tasly.anguo.core.category.interceptors;

import java.util.List;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

public class AnguoCategoryRemovalValidator implements RemoveInterceptor {
    
	private CategoryService categoryService;
	@Override
	public void onRemove(Object model, InterceptorContext arg1)
			throws InterceptorException {
		if (model instanceof CategoryModel)
		{  
			if (((CategoryModel) model).getAllSubcategories().size() > 0 || ((CategoryModel) model).getProducts().size() > 0)
			{
				throw new InterceptorException("无法删除 [" + ((CategoryModel) model).getCode()
						+ "], 该类目下尚存在产品或者类目");
			}
		}
	}

}
