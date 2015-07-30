package com.tasly.anguo.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.filter.GenericFilterBean;

import com.tasly.anguo.facades.constants.AnguoFacadesConstants;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.web.DynamicCatalogVersionActivationFilter;

public class ActiveCatalogVersionFilter extends GenericFilterBean{

	private static final Logger LOG = Logger.getLogger(ActiveCatalogVersionFilter.class);

	private CatalogVersionService catalogVersionService;

	private CatalogService catalogService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		LOG.info("LEGEND");
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse))
		{
			throw new ServletException("CatalogVersionActivationFilter just supports HTTP requests");
		}
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		final HttpSession httpSession = httpRequest.getSession();
		
		final Collection<CatalogVersionModel> catVersions = catalogVersionService.getSessionCatalogVersions();
		if (catVersions.isEmpty())
		{
			final Collection<CatalogVersionModel> versionsToSet = 
					Arrays.asList(catalogVersionService.getCatalogVersion(AnguoFacadesConstants.ACTIVEPRODUCTCATALOG, AnguoFacadesConstants.ACTIVEPRODUCTCATALOGVERSION));

			assignCatalogVersions(httpRequest, httpSession, versionsToSet);
		}
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Current session already got its catalog versions " + catVersions);
			}
		}
		
		filterChain.doFilter(httpRequest, httpResponse);
		
	}

	public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
		this.catalogVersionService = catalogVersionService;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	private void assignCatalogVersions(final HttpServletRequest request, final HttpSession httpSession,
			final Collection<CatalogVersionModel> versions)
	{
		if (versions == null || versions.isEmpty())
		{
			final CatalogModel def = catalogService.getDefaultCatalog();
			CatalogVersionModel defCatVer = null;
			if (def != null)
			{
				defCatVer = def.getActiveCatalogVersion();
			}
			//try to set the defaultCatalogVersion
			catalogVersionService.setSessionCatalogVersions(defCatVer == null ? Collections.EMPTY_LIST : Collections
					.singletonList(defCatVer));

			if (LOG.isDebugEnabled())
			{
				final String reqURI = request == null ? "<n/a>" : request.getRequestURI();
				LOG.debug("No active versions available for request " + reqURI + "!");
			}
		}
		else
		{
			catalogVersionService.setSessionCatalogVersions(versions);
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Active versions now are " + versions);
			}
		}
	}

	

}
