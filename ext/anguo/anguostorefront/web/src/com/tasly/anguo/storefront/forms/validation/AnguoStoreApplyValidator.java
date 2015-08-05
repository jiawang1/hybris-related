package com.tasly.anguo.storefront.forms.validation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tasly.anguo.store.data.AnguoStoreManagermentData;
import com.tasly.anguo.storefront.forms.AnguoStoreApplyForm;


/**
 * @author i313919
 *
 */
@Component("anguoStoreApplyValidator")
public class AnguoStoreApplyValidator implements Validator
{
	// 允许上传的文件格式的列表  
	final String[] allowedExt = new String[]
	{ "jpg", "jpeg", "bmp", "png" };

	@Override
	public boolean supports(Class<?> arg0)
	{
		return AnguoStoreManagermentData.class.equals(arg0);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		final AnguoStoreApplyForm storeApply = (AnguoStoreApplyForm) object;

		if (StringUtils.isEmpty(storeApply.getName()) || StringUtils.length(storeApply.getName()) > 100)
		{
			errors.rejectValue("name", "storeapply.name.invalid");
		}

		String logoName = storeApply.getLogo().getName();
		if (!StringUtils.isEmpty(logoName))
		{

			String t_ext = logoName.substring(logoName.lastIndexOf(".") + 1);
			boolean isAllow = false;
			for (String s : allowedExt)
			{
				if (s.equalsIgnoreCase(t_ext))
				{
					isAllow = true;
					break;
				}
			}

			if (!isAllow)
			{
				errors.rejectValue("logo", "storeapply.logo.invalid");
				return;
			}
			else

			if (storeApply.getLogo().getSize() > 1024 * 1024)
			{
				errors.rejectValue("logo", "storeapply.logo.invalid");
				return;
			}
			else
			{

				InputStream inputStream;
				try
				{
					inputStream = storeApply.getLogo().getInputStream();

					BufferedImage bi = ImageIO.read(inputStream);
					// another interface such as:read(new File(filename););;   
					int width = bi.getWidth();
					int height = bi.getHeight();
					if (width > 800 || height > 800)
					{
						errors.rejectValue("logo", "storeapply.logo.invalid");
					}

				}
				catch (IOException e)
				{
					e.printStackTrace();
					errors.rejectValue("name", "storeapply.name.invalid");
				}
			}
		}

		if (StringUtils.isEmpty(storeApply.getCityCode()) || StringUtils.isEmpty(storeApply.getCityDistrictCode())
				|| StringUtils.isEmpty(storeApply.getRegionIso()) || StringUtils.isEmpty(storeApply.getStreet())
				|| StringUtils.length(storeApply.getStreet()) > 100)
		{
			errors.rejectValue("contactName1", "storeapply.contactName1.invalid");
		}

		if (StringUtils.isEmpty(storeApply.getContactName1()) || StringUtils.length(storeApply.getContactName1()) > 30)
		{
			errors.rejectValue("contactName1", "storeapply.contactName1.invalid");
		}

		if (StringUtils.isEmpty(storeApply.getContactPhone1()) || StringUtils.length(storeApply.getContactPhone1()) != 11
				|| !StringUtils.isNumeric(storeApply.getContactPhone1()))
		{
			errors.rejectValue("contactPhone1", "storeapply.contactPhone1.invalid");
		}

		if (StringUtils.isEmpty(storeApply.getDescription()) || StringUtils.length(storeApply.getDescription()) > 2000)
		{
			errors.rejectValue("description", "storeapply.description.invalid");
		}

		if (StringUtils.isEmpty(storeApply.getQq()) || StringUtils.length(storeApply.getQq()) > 15
				|| !StringUtils.isNumeric(storeApply.getQq()))
		{
			errors.rejectValue("qq", "storeapply.qq.invalid");
		}

		if (StringUtils.isEmpty(storeApply.getFax()) || StringUtils.length(storeApply.getFax()) > 15
				|| !StringUtils.isNumeric(storeApply.getFax()))
		{
			errors.rejectValue("fax", "storeapply.fax.invalid");
		}

		if (StringUtils.isEmpty(storeApply.getTelephone()) || StringUtils.length(storeApply.getTelephone()) > 20
				|| !StringUtils.isNumeric(storeApply.getTelephone()))
		{
			errors.rejectValue("telephone", "storeapply.telephone.invalid");
		}

	}

}
