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

import com.tasly.anguo.core.location.DistrictService;
import com.tasly.anguo.facades.data.AbstractLocationItemData;
import com.tasly.anguo.facades.populators.AbstractLocationItemPopulator;

import de.hybris.bootstrap.annotations.UnitTest;
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
	private AbstractLocationItemPopulator districtPopulator;
	
	private DefaultDistrictFacade defaultDistrictFacade;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		defaultDistrictFacade = new DefaultDistrictFacade();
		defaultDistrictFacade.setDistrictService(districtService);
		defaultDistrictFacade.setAbstractLocationItemPopulator(districtPopulator);
	}
	
	@Test
	public void getDistrictByCode()
	{
		String districtCode = "districtCode";
		given(districtService.getDistrictByCode(districtCode)).willReturn(districtModel);
		doAnswer(new Answer<AbstractLocationItemData>() {
			@Override
			public AbstractLocationItemData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof AbstractLocationItemData) {
					final AbstractLocationItemData rd = (AbstractLocationItemData) args[1];
					rd.setName("districtName");
					rd.setCode("districtCode");
				}
				return null;
			}
		}).when(this.districtPopulator).populate(
				org.mockito.Matchers.any(DistrictModel.class),
				org.mockito.Matchers.any(AbstractLocationItemData.class));
		AbstractLocationItemData result = defaultDistrictFacade.getDistrictByCode(districtCode);
		assertEquals(result.getName(), "districtName");
		assertEquals(result.getCode(), "districtCode");
	}
	
	@Test
	public void getDistrictByCityCode()
	{
		List<DistrictModel> districtModels = new ArrayList<DistrictModel>();
		districtModels.add(districtModel);
		String cityCode = "cityCode";
		given(districtService.getDistrictsByCity(cityCode)).willReturn(districtModels);
		doAnswer(new Answer<AbstractLocationItemData>() {
			@Override
			public AbstractLocationItemData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof AbstractLocationItemData) {
					final AbstractLocationItemData rd = (AbstractLocationItemData) args[1];
					rd.setName("districtName");
					rd.setCode("districtCode");
				}
				return null;
			}
		}).when(this.districtPopulator).populate(
				org.mockito.Matchers.any(DistrictModel.class),
				org.mockito.Matchers.any(AbstractLocationItemData.class));
		List<AbstractLocationItemData> result = defaultDistrictFacade.getDistrictsByCityCode(cityCode);
		assertEquals(result.get(0).getName(), "districtName");
		assertEquals(result.get(0).getCode(), "districtCode");
	}
}
