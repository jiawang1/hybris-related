/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

import com.tasly.anguo.core.location.CityService;
import com.tasly.anguo.core.location.DistrictService;
import com.tasly.anguo.facades.data.CityData;
import com.tasly.anguo.facades.data.DistrictData;
import com.tasly.anguo.facades.populators.CityPopulator;
import com.tasly.anguo.facades.populators.DistrictPopulator;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;


/**
 * Unit test for {@link DefaultCityFacade}.
 *
 * @author i319019
 *
 */
@UnitTest
public class DefaultDistrictFacadeTest {

	@Mock
	private DistrictService districtService;
	@Mock
	private DistrictModel districtModel;
	@Mock
	private DistrictPopulator districtPopulator;
	
	private DefaultDistrictFacade defaultDistrictFacade;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		defaultDistrictFacade = new DefaultDistrictFacade();
		defaultDistrictFacade.setDistrictService(districtService);
		defaultDistrictFacade.setDistrictPopulator(districtPopulator);
	}
	
	@Test
	public void getDistrictByCode()
	{
		String districtCode = "districtCode";
		given(districtService.getDistrictByCode(districtCode)).willReturn(districtModel);
		doAnswer(new Answer<DistrictData>() {
			@Override
			public DistrictData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof DistrictData) {
					final DistrictData rd = (DistrictData) args[1];
					rd.setDistrictName("districtName");
					rd.setCode("districtCode");
				}
				return null;
			}
		}).when(this.districtPopulator).populate(
				org.mockito.Matchers.any(DistrictModel.class),
				org.mockito.Matchers.any(DistrictData.class));
		DistrictData result = defaultDistrictFacade.getDistrictByCode(districtCode);
		assertEquals(result.getDistrictName(), "districtName");
		assertEquals(result.getCode(), "districtCode");
	}
	
	@Test
	public void getDistrictByCityCode()
	{
		List<DistrictModel> districtModels = new ArrayList<DistrictModel>();
		districtModels.add(districtModel);
		String cityCode = "cityCode";
		given(districtService.getDistrictsByCity(cityCode)).willReturn(districtModels);
		doAnswer(new Answer<DistrictData>() {
			@Override
			public DistrictData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof DistrictData) {
					final DistrictData rd = (DistrictData) args[1];
					rd.setDistrictName("districtName");
					rd.setCode("districtCode");
				}
				return null;
			}
		}).when(this.districtPopulator).populate(
				org.mockito.Matchers.any(DistrictModel.class),
				org.mockito.Matchers.any(DistrictData.class));
		List<DistrictData> result = defaultDistrictFacade.getDistrictsByCityCode(cityCode);
		assertEquals(result.get(0).getDistrictName(), "districtName");
		assertEquals(result.get(0).getCode(), "districtCode");
	}
}
