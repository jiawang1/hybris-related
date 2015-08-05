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

import com.tasly.anguo.core.location.CountryService;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.user.converters.populator.CountryPopulator;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.c2l.CountryModel;


/**
 * Unit test for {@link DefaultCountryFacade}.
 *
 * @author i319019
 *
 */
@UnitTest
public class DefaultCountryFacadeTest {

	@Mock
	private CountryService countryService;
	@Mock
	private CountryModel countryModel;
	@Mock
	private CountryPopulator countryPopulator;
	
	private DefaultCountryFacade defaultCountryFacade;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		defaultCountryFacade = new DefaultCountryFacade();
		defaultCountryFacade.setCountryService(countryService);
		defaultCountryFacade.setCountryPopulator(countryPopulator);
	}
	
	@Test
	public void getCountries()
	{
		List<CountryModel> countryModels = new ArrayList<CountryModel>();
		countryModels.add(countryModel);
		given(countryService.getAllCountries()).willReturn(countryModels);
		doAnswer(new Answer<CountryData>() {
			@Override
			public CountryData answer(final InvocationOnMock invocation)
					throws Throwable {
				final Object[] args = invocation.getArguments();
				if (args[1] instanceof CountryData) {
					final CountryData rd = (CountryData) args[1];
					rd.setName("countryName");
					rd.setIsocode("countryCode");
				}
				return null;
			}
		}).when(this.countryPopulator).populate(
				org.mockito.Matchers.any(CountryModel.class),
				org.mockito.Matchers.any(CountryData.class));
		List<CountryData> result = defaultCountryFacade.getAllCountries();
		assertEquals(result.get(0).getName(), "countryName");
		assertEquals(result.get(0).getIsocode(), "countryCode");
	}
}
