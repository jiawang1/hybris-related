package com.tasly.anguo.facades.populators;

import org.springframework.beans.factory.annotation.Required;

import com.tasly.anguo.core.location.CityService;
import com.tasly.anguo.core.location.DistrictService;
import com.tasly.anguo.core.model.AnguoStoreTempModel;
import com.tasly.anguo.store.data.AnguoStoreManagermentData;

import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import org.apache.commons.lang.StringUtils;


public class AnguoStoreReversePopulator implements Populator<AnguoStoreManagermentData, AnguoStoreTempModel>
{

	private CityService cityService;

	private DistrictService districtService;

	private CommonI18NService commonI18NService;

	@Override
	public void populate(AnguoStoreManagermentData source, AnguoStoreTempModel target) throws ConversionException
	{
		target.setName(source.getName());
		target.setQq(source.getQq());
		target.setDescription(source.getDescription());
		target.setStreet(source.getStreet());
		target.setContactName1(source.getContactName1());
		target.setContactName2(source.getContactName2());
		target.setContactName3(source.getContactName3());
		target.setContactPhone1(source.getContactPhone1());
		target.setContactPhone2(source.getContactPhone2());
		target.setContactPhone3(source.getContactPhone3());
		target.setFax(source.getFax());
		target.setTelephone(source.getTelephone());
		target.setStatus(source.getStatus());


		// set country
		if (source.getCountryIso() != null && !StringUtils.isEmpty(source.getCountryIso()))
		{
			final CountryModel country = this.getCommonI18NService().getCountry(source.getCountryIso());
			target.setCountry(country);
		}
		
		if (source.getRegionIso() != null && !StringUtils.isEmpty(source.getRegionIso())) 
		{
			final String isocode = source.getRegionIso();
			try
			{
				final RegionModel regionModel = getCommonI18NService().getRegion(getCommonI18NService().getCountry(source.getCountryIso()), isocode);
				target.setRegion(regionModel);
			}
			catch (final UnknownIdentifierException e)
			{
				throw new ConversionException("No region with the code " + isocode + " found.", e);
			}
			catch (final AmbiguousIdentifierException e)
			{
				throw new ConversionException("More than one region with the code " + isocode + " found.", e);
			}
		}

//		if (source.getRegionIso() != null && !StringUtils.isEmpty(source.getRegionIso()))
//		{
//			final String isocode = source.getRegionIso();
//			try
//			{
//				final RegionModel regionModel = getCommonI18NService().getRegion(getCommonI18NService().getCountry("CN"), isocode);
//				target.setRegion(regionModel);
//			}
//			catch (final UnknownIdentifierException e)
//			{
//				throw new ConversionException("No region with the code " + isocode + " found.", e);
//			}
//			catch (final AmbiguousIdentifierException e)
//			{
//				throw new ConversionException("More than one region with the code " + isocode + " found.", e);
//			}
//		}


		// set city
		if (source.getCityCode() != null && !StringUtils.isEmpty(source.getCityCode()))
		{
			final CityModel city = this.getCityService().getCityByCode(source.getCityCode());
			target.setCity(city);
		}

		// set district
		if (source.getCityDistrictCode() != null && !StringUtils.isEmpty(source.getCityDistrictCode()))
		{
			final DistrictModel district = this.getDistrictService().getDistrictByCode(source.getCityDistrictCode());
			target.setCityDistrict(district);
		}

	}


	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}


	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}


	protected CityService getCityService()
	{
		return cityService;
	}

	@Required
	public void setCityService(final CityService cityService)
	{
		this.cityService = cityService;
	}

	protected DistrictService getDistrictService()
	{
		return districtService;
	}

	@Required
	public void setDistrictService(final DistrictService districtService)
	{
		this.districtService = districtService;
	}
}
