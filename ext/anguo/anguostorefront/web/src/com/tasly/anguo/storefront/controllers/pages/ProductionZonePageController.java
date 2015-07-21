package com.tasly.anguo.storefront.controllers.pages;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tasly.anguo.facades.data.CityData;
import com.tasly.anguo.facades.data.DistrictData;
import com.tasly.anguo.facades.location.CityFacade;
import com.tasly.anguo.facades.location.DistrictFacade;
import com.tasly.anguo.facades.location.RegionFacade;
import com.tasly.anguo.storefront.controllers.ControllerConstants;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.jalo.ConsistencyCheckException;

/**
 * Controller for product zone page
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/**/productzone")
public class ProductionZonePageController extends AbstractPageController {
	@Resource
	private RegionFacade regionFacade;
	@Resource
	private CityFacade cityFacade;
	@Resource
	private DistrictFacade districtFacade;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String initPage(final Model model) throws ConsistencyCheckException
	{
		List<RegionData> regions = regionFacade.getRegionsForCountryCode("CN");
		model.addAttribute("regions", regions);
		return ControllerConstants.Views.Pages.Product.ProductionZone;
	}
	
	@RequestMapping(value = "/getCity", method = RequestMethod.GET)
	@ResponseBody
	public List<CityData> getCity(@RequestParam(value = "region") String region)
	{
		List<CityData> cities = cityFacade.getCitiesByRegionCode(region);
		return cities;
	}

	@RequestMapping(value = "/getDistrict", method = RequestMethod.GET)
	@ResponseBody
	public List<DistrictData> getDistrict(@RequestParam(value = "city") String city)
	{
		List<DistrictData> districts = districtFacade.getDistrictsByCityCode(city);
		return districts;
	}
}
