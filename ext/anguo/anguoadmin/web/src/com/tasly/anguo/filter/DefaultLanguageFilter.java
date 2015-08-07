package com.tasly.anguo.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.filter.GenericFilterBean;

import com.tasly.anguo.constants.AnguoadminConstants;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.util.Config;

public class DefaultLanguageFilter extends GenericFilterBean{

	private static final Logger LOG = Logger.getLogger(DefaultLanguageFilter.class);
    
	@Resource
    private CommonI18NService commonI18NService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse))
		{
			throw new ServletException("Language Fileter just supports HTTP requests");
		}
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		final HttpSession httpSession = httpRequest.getSession();
		
		LanguageModel language = commonI18NService.getCurrentLanguage();
		if(language!=null&&!language.getIsocode().equals(Config.getParameter(AnguoadminConstants.EXTENSIONNAME+".defaultlanguage"))){
			commonI18NService.setCurrentLanguage(
					commonI18NService.getLanguage(Config.getParameter(AnguoadminConstants.EXTENSIONNAME+".defaultlanguage"))
					);
			
			LOG.debug("default langue was set to " + commonI18NService.getCurrentLanguage().getIsocode());
		}
		
		filterChain.doFilter(httpRequest, httpResponse);
		
	}

}
