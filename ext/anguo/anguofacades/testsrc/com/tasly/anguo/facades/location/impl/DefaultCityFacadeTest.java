/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

import com.tasly.anguo.core.location.CityService;
import com.tasly.anguo.facades.data.CityData;
import com.tasly.anguo.facades.populators.CityPopulator;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;


/**
 * Unit test for {@link DefaultCityFacade}.
 *
 * @author i319019
 *
 */
@UnitTest
public class DefaultCityFacadeTest {

	@Mock
	private CityService cityService;
	@Mock
	private CityModel cityModel;
	@Mock
	private CityPopulator cityPopulator;
	
	private DefaultCityFacade defaultCityFacade;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		defaultCityFacade = new DefaultCityFacade();
		defaultCityFacade.setCityService(cityService);
		defaultCityFacade.setCityPopulator(cityPopulator);
	}
	
	@Test
	public void getCityByCode()
	{
		String cityCode = "cityCode";
		given(cityService.getCityByCode(cityCode)).willReturn(cityModel);
		doAnswer(new Answer<CityData>() {
			@Override
			public CityData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof CityData) {
					final CityData rd = (CityData) args[1];
					rd.setCityName("cityName");
					rd.setCode("cityCode");
				}
				return null;
			}
		}).when(this.cityPopulator).populate(
				org.mockito.Matchers.any(CityModel.class),
				org.mockito.Matchers.any(CityData.class));
		CityData result = defaultCityFacade.getCityForCode(cityCode);
		assertEquals(result.getCityName(), "cityName");
		assertEquals(result.getCode(), "cityCode");
	}
	
	@Test
	public void getCityByRegionCode()
	{
		List<CityModel> cityModels = new ArrayList<CityModel>();
		cityModels.add(cityModel);
		String regionCode = "regionCode";
		given(cityService.getCitiesByRegion(regionCode)).willReturn(cityModels);
		doAnswer(new Answer<CityData>() {
			@Override
			public CityData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof CityData) {
					final CityData rd = (CityData) args[1];
					rd.setCityName("cityName");
					rd.setCode("cityCode");
				}
				return null;
			}
		}).when(this.cityPopulator).populate(
				org.mockito.Matchers.any(CityModel.class),
				org.mockito.Matchers.any(CityData.class));
		List<CityData> result = defaultCityFacade.getCitiesByRegionCode(regionCode);
		assertEquals(result.get(0).getCityName(), "cityName");
		assertEquals(result.get(0).getCode(), "cityCode");
	}
}
