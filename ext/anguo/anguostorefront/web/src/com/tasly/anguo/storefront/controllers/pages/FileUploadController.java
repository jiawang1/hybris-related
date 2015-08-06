/*
 *VMS solution
 */
package com.tasly.anguo.storefront.controllers.pages;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tasly.anguo.facades.customer.CustomerIdentifyFacade;

import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.util.Config;


@Controller
@RequestMapping("/fileUpload")
public class FileUploadController
{
	protected static Logger logger = Logger.getLogger(FileUploadController.class);

	private static String CONF_FILE_MAX_SIZE_KEY = "uploadfile.fileMaxSize";
	private static String CONF_IMAGE_MAX_WIDTH_KEY = "uploadfile.imageMaxWidth";
	private static String CONF_IMAGE_MAX_HEIGHT_KEY = "uploadfile.imageMaxHeight";
	private static String CONF_IMAGE_LEGAL_TYPE_KEY = "uploadfile.imageLegalType";
	
	private CustomerIdentifyFacade customerIdentifyFacade;

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Map uploadFile(@RequestParam final MultipartFile[] uploadFiles) throws IOException
	{
		Map map = null;
		if (uploadFiles.length <= 0)
		{
			map = getReturnMap(-1, "no file upload");
		}
		else
		{
			for (final MultipartFile uploadFile : uploadFiles)
			{
				if (uploadFile.isEmpty())
				{
					map = getReturnMap(-1, "uploaded file is null");
					break;
				}
				else
				{
					map = this.validateFile(uploadFile);
					if (map != null)
					{
						break;
					}
					map = this.uploadMedia( uploadFile);
				}
			}
		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map uploadMedia(final MultipartFile uploadFile)
	{
		final Map map = getReturnMap(1, "success");
		final ImageData imageData = customerIdentifyFacade.uploadEnterpriseLiceneses(uploadFile);
		map.put("media", imageData);
		return map;
	}

	@SuppressWarnings("rawtypes")
	private Map validateFile(final MultipartFile uploadFile) throws IOException
	{
		final int maxSize = Integer.parseInt(Config.getParameter(CONF_FILE_MAX_SIZE_KEY)); 
		if (uploadFile.getInputStream().available() / (1024 * 1024) > maxSize)
		{
			return getReturnMap(-1, "file size is too large");
		}
		return validateImageFile(uploadFile);
	}

	@SuppressWarnings("rawtypes")
	private Map validateImageFile(final MultipartFile uploadFile) throws IOException
	{
		final int imageMaxWidth = Integer.parseInt(Config.getParameter(CONF_IMAGE_MAX_WIDTH_KEY)); // PX
		final int imageMaxHeight = Integer.parseInt(Config.getParameter(CONF_IMAGE_MAX_HEIGHT_KEY)); // PX
		final String imageLegalType = Config.getParameter(CONF_IMAGE_LEGAL_TYPE_KEY);
		final List types = Arrays.asList(imageLegalType.split(","));
		if (!types.contains(uploadFile.getContentType()))
		{
			return getReturnMap(-1, "file type is not supported");
		}
		final BufferedImage image = ImageIO.read(uploadFile.getInputStream());
		if (image.getWidth() > imageMaxWidth || image.getHeight() > imageMaxHeight)
		{
			return getReturnMap(-1, "pixel is too large");
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getReturnMap(final int status, final String msg)
	{
		final Map map = new HashMap();
		map.put("status", status);
		map.put("msg", msg);
		return map;
	}
}
