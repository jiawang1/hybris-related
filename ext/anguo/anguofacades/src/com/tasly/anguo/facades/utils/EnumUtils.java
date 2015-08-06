/**
 * 
 */
package com.tasly.anguo.facades.utils;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.facades.product.data.EnumData;

import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.enumeration.EnumerationService;

/**
 * @author i319019
 *
 */
public class EnumUtils{

	private EnumerationService enumerationService;

	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService() {
		return enumerationService;
	}

	/**
	 * @param enumerationService the enumerationService to set
	 */
	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}

	public <T extends HybrisEnumValue> List<EnumData> getEnumValues(Class<T> paramClass)
	{   
		List<T> enumValues = enumerationService.getEnumerationValues(paramClass);
		List<EnumData> enumDatas = new ArrayList<EnumData>();
		for (T enumValue : enumValues) {
			EnumData enumData = new EnumData();
			enumData.setCode(enumValue.getCode());
			enumData.setName(enumerationService.getEnumerationName(enumValue));
			enumDatas.add(enumData);
		}
		return enumDatas;
	}
}
