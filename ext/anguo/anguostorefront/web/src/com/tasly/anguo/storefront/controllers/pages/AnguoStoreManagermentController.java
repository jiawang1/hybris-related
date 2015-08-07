package com.tasly.anguo.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.chinaaccelerator.services.model.location.DistrictModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tasly.anguo.core.enums.StoreApproveStatus;
import com.tasly.anguo.core.model.AnguoPlatformServiceModel;
import com.tasly.anguo.core.model.AnguoStoreModel;
import com.tasly.anguo.core.model.AnguoStoreTempModel;
import com.tasly.anguo.facades.anguostore.AnguoStoreManagermentFacade;
import com.tasly.anguo.facades.data.AbstractLocationItemData;
import com.tasly.anguo.facades.data.RegionResultData;
import com.tasly.anguo.facades.location.CityFacade;
import com.tasly.anguo.facades.location.DistrictFacade;
import com.tasly.anguo.store.data.AnguoStoreManagermentData;
import com.tasly.anguo.storefront.forms.AnguoStoreApplyForm;


@Controller
@Scope("tenant")
@RequestMapping("/anguo-storemanagerment")
public class AnguoStoreManagermentController extends AbstractSearchPageController
{

	private static final Logger LOG = Logger.getLogger(AnguoStoreManagermentController.class);

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	@Resource(name = "cityFacade")
	private CityFacade cityFacade;

	@Resource(name = "districtFacade")
	private DistrictFacade districtFacade;

	@Resource(name = "userService")
	private UserService userService;

	private static final String REDIRECT_SHOW_ANGUOSTORE_MANAGE = REDIRECT_PREFIX
			+ "/anguo-storemanagerment/show-anguostore-manage";

	private static final String FORWARD_SHOW_ANGUOSTORE_MANAGE = FORWARD_PREFIX + "/anguo-storemanagerment/show-anguostore-manage";


	@Resource(name = "anguoStoreManagermentFacade")
	private AnguoStoreManagermentFacade anguoStoreManagermentFacade;

	@Resource(name = "mediaService")
	private MediaService mediaService;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;

	@Resource(name = "anguoStoreApplyValidator")
	private Validator anguoStoreApplyValidator;

	@Resource(name = "acceleratorCheckoutFacade")
	private CheckoutFacade checkoutFacade;

	@RequestMapping(value = "/saveStore", method = RequestMethod.POST)
	public String addStore(final AnguoStoreApplyForm anguoStoreApplyForm, final HttpServletRequest request,
			final BindingResult bindingResult, final Model model) throws Exception
	{
		// 判断enctype属性是否为multipart/form-data
		final boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		final String encoding = request.getCharacterEncoding();

		final CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		final AnguoStoreModel anguoStore = customer.getAnguoStore();
		final AnguoStoreTempModel anguoTempStore = customer.getAnguoStoreTemp();

		StoreApproveStatus approveStatus = null;
		// Create a factory for disk-based file items
		final DiskFileItemFactory factory = new DiskFileItemFactory();

		// 当上传文件太大时，因为虚拟机能使用的内存是有限的，所以此时要通过临时文件来实现上传文件的保存
		// 此方法是设置是否使用临时文件的临界值（单位：字节）
		//		factory.setSizeThreshold(yourMaxMemorySize);

		// 与上一个结合使用，设置临时文件的路径（绝对路径）
		//		factory.setRepository(yourTempDirectory);

		// Create a new file upload handler
		final ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置上传内容的大小限制（单位：字节）
		//		upload.setSizeMax(yourMaxRequestSize);

		upload.setHeaderEncoding(encoding);

		// Parse the request
		final List<?> items = upload.parseRequest(request);

		final AnguoStoreManagermentData anguoStoreData = new AnguoStoreManagermentData();

		final Iterator iter = items.iterator();
		while (iter.hasNext())
		{
			final FileItem item = (FileItem) iter.next();

			buildAnguoStoreApplyForm(anguoStoreApplyForm, item, encoding);
		}

		getAnguoStoreApplyValidator().validate(anguoStoreApplyForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "form.global.error");
			return FORWARD_SHOW_ANGUOSTORE_MANAGE;
		}

		BeanUtils.copyProperties(anguoStoreApplyForm, anguoStoreData);

		if (anguoTempStore != null)
		{
			approveStatus = anguoTempStore.getStatus();
			//			if (StoreApproveStatus.CREATE_APPROVE.equals(approveStatus) || StoreApproveStatus.MODIFY_APPROVE.equals(approveStatus)) {
			anguoStoreData.setStatus(StoreApproveStatus.MODIFY_WAIT);
			getAnguoStoreManagermentFacade().updateStore(anguoStoreData);
			//			}

		}
		else if (anguoTempStore == null)
		{
			anguoStoreData.setStatus(StoreApproveStatus.CREATE_WAIT);
			anguoStoreData.setRegisterDate(Calendar.getInstance().getTime());
			getAnguoStoreManagermentFacade().addStore(anguoStoreData);
		}

		//		return "pages/store/anguoStoreApplyPage";
		return REDIRECT_SHOW_ANGUOSTORE_MANAGE;
	}

	private void buildAnguoStoreApplyForm(final AnguoStoreApplyForm newStore, final FileItem item, final String encoding)
			throws UnsupportedEncodingException
	{
		if ("name".equals(item.getFieldName()))
		{
			newStore.setName(item.getString(encoding));
		}
		else if ("qq".equals(item.getFieldName()))
		{
			newStore.setQq(item.getString(encoding));
		}
		else if ("description".equals(item.getFieldName()))
		{
			newStore.setDescription(item.getString(encoding));
		}
		else if ("countryIso".equals(item.getFieldName()))
		{
			newStore.setCountryIso(item.getString(encoding));
		}
		else if ("regionIso".equals(item.getFieldName()))
		{
			newStore.setRegionIso(item.getString(encoding));
		}
		else if ("cityCode".equals(item.getFieldName()))
		{
			newStore.setCityCode(item.getString(encoding));
		}
		else if ("cityDistrictCode".equals(item.getFieldName()))
		{
			newStore.setCityDistrictCode(item.getString(encoding));
		}
		else if ("street".equals(item.getFieldName()))
		{
			newStore.setStreet(item.getString(encoding));
		}
		else if ("contactName1".equals(item.getFieldName()))
		{
			newStore.setContactName1(item.getString(encoding));
		}
		else if ("contactName2".equals(item.getFieldName()))
		{
			newStore.setContactName2(item.getString(encoding));
		}
		else if ("contactName3".equals(item.getFieldName()))
		{
			newStore.setContactName3(item.getString(encoding));
		}
		else if ("contactPhone1".equals(item.getFieldName()))
		{
			newStore.setContactPhone1(item.getString(encoding));
		}
		else if ("contactPhone2".equals(item.getFieldName()))
		{
			newStore.setContactPhone2(item.getString(encoding));
		}
		else if ("contactPhone3".equals(item.getFieldName()))
		{
			newStore.setContactPhone3(item.getString(encoding));
		}
		else if ("fax".equals(item.getFieldName()))
		{
			newStore.setFax(item.getString(encoding));
		}
		else if ("telephone".equals(item.getFieldName()))
		{
			newStore.setTelephone(item.getString(encoding));
		}
		else if ("logo".equals(item.getFieldName()) && !item.isFormField())
		{
			newStore.setLogo(item);
		}
	}



	@RequestMapping(value = "/anguoStoreapplypage", method = RequestMethod.GET)
	public String anguoStoreapplypage(final Model model)
	{

		model.addAttribute("formActionURL", "teststoreapplypage");
		final AnguoStoreApplyForm anguoStoreApplyForm = new AnguoStoreApplyForm();
		anguoStoreApplyForm.setApproveStatus("CREATE_NEW");
		model.addAttribute(anguoStoreApplyForm);

		//		model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso("CN"));
		model.addAttribute("countries", getCountries());
		return "pages/store/anguoStoreApplyPage";
	}

	@ModelAttribute("countries")
	public Collection<CountryData> getCountries()
	{
		return checkoutFacade.getDeliveryCountries();
	}

	@ResponseBody
	@RequestMapping(value = "/select-address-location", method = RequestMethod.GET)
	public RegionResultData getAutocompleteSuggestions(final AnguoStoreApplyForm incomingAddressForm, final Model model,
			@RequestParam(value = "cmd", required = false) final String cmd,
			@RequestParam(value = "url", required = false) final String url) throws CMSItemNotFoundException
	{
		final RegionResultData data = new RegionResultData();

		if (incomingAddressForm.getCountryIso() != null && !incomingAddressForm.getCountryIso().isEmpty())
		{
			final List<RegionData> regionDTOs = getI18NFacade().getRegionsForCountryIso(incomingAddressForm.getCountryIso());
			data.setRegions(regionDTOs);
		}

		if (incomingAddressForm.getRegionIso() != null && !incomingAddressForm.getRegionIso().isEmpty())
		{
			final List<AbstractLocationItemData> cityDTOs = getCityFacade()
					.getCitiesByRegionCode(incomingAddressForm.getRegionIso());
			data.setCities(cityDTOs);
		}

		if (incomingAddressForm.getCityCode() != null && !incomingAddressForm.getCityCode().isEmpty() && cmd.equals("cityselected"))
		{
			final List<AbstractLocationItemData> cityDistricts = this.getDistrictFacade().getDistrictsByCityCode(
					incomingAddressForm.getCityCode());
			data.setCityDistricts(cityDistricts);
		}

		return data;
	}

	/**
	 * Show the store info modify page.
	 *
	 * @param isModify
	 * @param model
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/show-anguostore-manage")
	@RequireHardLogIn
	public String showStoreInfoManage(final Model model) throws IllegalAccessException, InvocationTargetException
	{

		final UserModel currentUser = userService.getCurrentUser();
		final CustomerModel customer = (CustomerModel) currentUser;
		AnguoStoreModel anguoStore = null;
		AnguoStoreTempModel anguoStoreTemp = null;
		StoreApproveStatus approveStatus = null;
		final StringBuffer platformServiceStr = new StringBuffer();

		AnguoStoreApplyForm storeApplyForm = new AnguoStoreApplyForm();
		CountryModel country = null;
		RegionModel region = null;
		CityModel city = null;
		DistrictModel cityDistrict = null;

		anguoStore = customer.getAnguoStore();
		anguoStoreTemp = customer.getAnguoStoreTemp();


		if (anguoStoreTemp != null)
		{
			approveStatus = anguoStoreTemp.getStatus();

			if (!StoreApproveStatus.CREATE_REJECT.equals(approveStatus))
			{
				city = anguoStoreTemp.getCity();
				if (city != null)
				{
					storeApplyForm.setCityCode(city.getCode());
				}
				cityDistrict = anguoStoreTemp.getCityDistrict();
				if (cityDistrict != null)
				{
					storeApplyForm.setCityDistrictCode(cityDistrict.getCode());
				}
				region = anguoStoreTemp.getRegion();
				if (region != null)
				{
					storeApplyForm.setRegionIso(region.getIsocode());
				}
				country = anguoStoreTemp.getCountry();
				if (country != null)
				{
					storeApplyForm.setCountryIso(country.getIsocode());
				}

				storeApplyForm.setContactName1(anguoStoreTemp.getContactName1());
				storeApplyForm.setContactName2(anguoStoreTemp.getContactName2());
				storeApplyForm.setContactName3(anguoStoreTemp.getContactName3());
				storeApplyForm.setContactPhone1(anguoStoreTemp.getContactPhone1());
				storeApplyForm.setContactPhone2(anguoStoreTemp.getContactPhone2());
				storeApplyForm.setContactPhone3(anguoStoreTemp.getContactPhone3());
				storeApplyForm.setDescription(anguoStoreTemp.getDescription());
				storeApplyForm.setFax(anguoStoreTemp.getFax());
				//				storeApplyForm.setLogo(anguoStore.getLogo());
				storeApplyForm.setLogoUrl(anguoStoreTemp.getLogo().getURL());
				storeApplyForm.setName(anguoStoreTemp.getName());
				storeApplyForm.setQq(anguoStoreTemp.getQq());
				storeApplyForm.setStreet(anguoStoreTemp.getStreet());
				storeApplyForm.setTelephone(anguoStoreTemp.getTelephone());
				storeApplyForm.setRegisterTime(anguoStoreTemp.getRegisterDate().toString());
				//				storeApplyForm.setStoreLevel(anguoStoreTemp.getStoreLevel());
				//				storeApplyForm.setStoreTemplate(anguoStoreTemp.getStoreTemplate());

				for (final AnguoPlatformServiceModel anguoPlatformServiceModel : anguoStoreTemp.getAnguoPlatformService())
				{
					platformServiceStr.append(anguoPlatformServiceModel.getDescription() + " ");
				}
				storeApplyForm.setAnguoPlatformService(platformServiceStr.toString());

			}

			storeApplyForm.setApproveStatus(anguoStoreTemp.getStatus().getCode());
		}
		else
		{
			storeApplyForm = new AnguoStoreApplyForm();
			storeApplyForm.setApproveStatus("CREATE_NEW");
		}



		final String fieldsNeedApprove = Config.getParameter("anguostore.fields.need.approve");
		storeApplyForm.setFieldsNeedApprove(fieldsNeedApprove);

		model.addAttribute("anguoStoreApplyForm", storeApplyForm);

		//Add common address data
		model.addAttribute("countries", getCountries());
		model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso("CN"));

		if (storeApplyForm.getRegionIso() != null && !(storeApplyForm.getRegionIso().isEmpty()))
		{
			model.addAttribute("cities", getCityFacade().getCitiesByRegionCode(storeApplyForm.getRegionIso()));
		}

		if (storeApplyForm.getCityCode() != null && !(storeApplyForm.getCityCode().isEmpty()))
		{
			model.addAttribute("cityDistricts", getDistrictFacade().getDistrictsByCityCode(storeApplyForm.getCityCode()));
		}

		return "pages/store/anguoStoreApplyPage";
	}


	public ModelService getModelService()
	{
		return modelService;
	}

	public MediaService getMediaService()
	{
		return mediaService;
	}

	public AnguoStoreManagermentFacade getAnguoStoreManagermentFacade()
	{
		return anguoStoreManagermentFacade;
	}

	protected CityFacade getCityFacade()
	{
		return this.cityFacade;
	}

	protected DistrictFacade getDistrictFacade()
	{
		return this.districtFacade;
	}

	protected I18NFacade getI18NFacade()
	{
		return i18NFacade;
	}

	public Validator getAnguoStoreApplyValidator()
	{
		return anguoStoreApplyValidator;
	}

}
