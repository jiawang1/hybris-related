/**
 * 
 */
package com.tasly.anguo.facades.utils;


import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tasly.anguo.core.enums.PackageUnit;
import com.tasly.anguo.facades.product.data.EnumData;

import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.enumeration.EnumerationService;

/**
 * @author i319019
 *
 */
public class EnumUtilsTest {
	@Mock
	private EnumerationService enumService;
	@Mock
	private PackageUnit packageUnit;
	
	private EnumUtils enumUtils;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		enumUtils = new EnumUtils();
		enumUtils.setEnumerationService(enumService);
	}
	
	@Test
	public void getAllEnumValuesTest()
	{
		List<PackageUnit> enumValueList = new ArrayList<PackageUnit>();
		enumValueList.add(packageUnit);
		given(packageUnit.getCode()).willReturn("enumCode");
		given(enumService.getEnumerationValues(PackageUnit.class)).willReturn(enumValueList);
		given(enumService.getEnumerationName(packageUnit)).willReturn("enumName");
		
		List<EnumData> results = enumUtils.getEnumValues(PackageUnit.class);
		assertEquals(results.get(0).getCode(), "enumCode");
		assertEquals(results.get(0).getName(), "enumName");
	}
}
