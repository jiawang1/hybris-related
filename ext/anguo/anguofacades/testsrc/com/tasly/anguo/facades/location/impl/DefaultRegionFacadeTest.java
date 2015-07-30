/**
 * 
 */
package com.tasly.anguo.facades.location.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.tasly.anguo.core.location.RegionService;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.user.converters.populator.RegionPopulator;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.core.model.c2l.RegionModel;


/**
 * Unit test for {@link DefaultCityFacade}.
 *
 * @author i319019
 *
 */
@UnitTest
public class DefaultRegionFacadeTest {

	@Mock
	private RegionService regionService;
	@Mock
	private RegionModel regionModel;
	@Mock
	private RegionPopulator regionPopulator;
	
	private DefaultRegionFacade defaultRegionFacade;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		defaultRegionFacade = new DefaultRegionFacade();
		defaultRegionFacade.setRegionService(regionService);
		defaultRegionFacade.setRegionPopulator(regionPopulator);
	}
	
	@Test
	public void getRegionByCode()
	{
		String regionCode = "regionCode";
		String countryCode = "CN";
		given(regionService.getRegionByCountryAndCode(countryCode, regionCode)).willReturn(regionModel);
		doAnswer(new Answer<RegionData>() {
			@Override
			public RegionData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof RegionData) {
					final RegionData rd = (RegionData) args[1];
					rd.setName("regionName");
					rd.setIsocode("regionCode");
				}
				return null;
			}
		}).when(this.regionPopulator).populate(
				org.mockito.Matchers.any(RegionModel.class),
				org.mockito.Matchers.any(RegionData.class));
		RegionData result = defaultRegionFacade.getRegionByCountryAndCode(countryCode, regionCode);
		assertEquals(result.getName(), "regionName");
		assertEquals(result.getIsocode(), "regionCode");
	}
	
	@Test
	public void getRegionsByCountryCode()
	{
		List<RegionModel> regionModels = new ArrayList<RegionModel>();
		regionModels.add(regionModel);
		String countryCode = "countryCode";
		given(regionService.getRegionsForCountryCode(countryCode)).willReturn(regionModels);
		doAnswer(new Answer<RegionData>() {
			@Override
			public RegionData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof RegionData) {
					final RegionData rd = (RegionData) args[1];
					rd.setName("regionName");
					rd.setIsocode("regionCode");
				}
				return null;
			}
		}).when(this.regionPopulator).populate(
				org.mockito.Matchers.any(RegionModel.class),
				org.mockito.Matchers.any(RegionData.class));
		List<RegionData> result = defaultRegionFacade.getRegionsForCountryCode(countryCode);
		assertEquals(result.get(0).getName(), "regionName");
		assertEquals(result.get(0).getIsocode(), "regionCode");
	}
}
