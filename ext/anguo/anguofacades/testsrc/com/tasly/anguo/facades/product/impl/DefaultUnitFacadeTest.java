/**
 * 
 */
package com.tasly.anguo.facades.product.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.tasly.anguo.facades.populators.UnitPopulator;
import com.tasly.anguo.facades.product.data.UnitData;

import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.product.UnitService;

/**
 * @author i319019
 *
 */
public class DefaultUnitFacadeTest {
	@Mock
	private UnitService unitService;
	@Mock
	private UnitPopulator unitPopulator;
	@Mock
	private UnitModel unitModel;
	
	private DefaultUnitFacade defaultUnitFacade;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		defaultUnitFacade = new DefaultUnitFacade();
		defaultUnitFacade.setUnitPopulator(unitPopulator);
		defaultUnitFacade.setUnitService(unitService);
	}
	
	@Test
	public void getAllUnitsTest()
	{
		Set<UnitModel> unitModels = new HashSet<UnitModel>();
		unitModels.add(unitModel);
		given(unitService.getAllUnits()).willReturn(unitModels);
		doAnswer(new Answer<UnitData>() {
			@Override
			public UnitData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof UnitData) {
					final UnitData rd = (UnitData) args[1];
					rd.setName("unitName");
					rd.setCode("unitCode");
				}
				return null;
			}
		}).when(this.unitPopulator).populate(
				org.mockito.Matchers.any(UnitModel.class),
				org.mockito.Matchers.any(UnitData.class));
		List<UnitData> results = defaultUnitFacade.getAllUnits();
		assertEquals(results.get(0).getName(), "unitName");
		assertEquals(results.get(0).getCode(), "unitCode");
	}
}
