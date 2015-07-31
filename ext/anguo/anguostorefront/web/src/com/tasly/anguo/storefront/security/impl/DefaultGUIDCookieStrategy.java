/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.tasly.anguo.storefront.security.impl;

import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;

import com.tasly.anguo.storefront.interceptors.beforecontroller.RequireHardLoginBeforeControllerHandler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;
import org.springframework.web.util.CookieGenerator;


/**
 * Default implementation of {@link GUIDCookieStrategy}
 */
public class DefaultGUIDCookieStrategy implements GUIDCookieStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultGUIDCookieStrategy.class);

	private final SecureRandom random;
	private final MessageDigest sha;

	private CookieGenerator cookieGenerator;

	private Integer cookieMaxAge;

	public DefaultGUIDCookieStrategy() throws NoSuchAlgorithmException
	{
		random = SecureRandom.getInstance("SHA1PRNG");
		sha = MessageDigest.getInstance("SHA-1");
		Assert.notNull(random);
		Assert.notNull(sha);
	}

	@Override
	public void setCookie(final HttpServletRequest request, final HttpServletResponse response)
	{
		
		if (!request.isSecure())
		{
			// We must not generate the cookie for insecure requests, otherwise there is not point doing this at all
			throw new IllegalStateException("Cannot set GUIDCookie on an insecure request!");
		}

		final String guid = createGUID();
		getCookieGenerator().addCookie(response, guid);
		request.getSession().setAttribute(RequireHardLoginBeforeControllerHandler.SECURE_GUID_SESSION_KEY, guid);

		if (LOG.isInfoEnabled())
		{
			LOG.info("Setting guid cookie and session attribute: " + guid);
		}
		
		//if remember me is on,then create a cookie to remember the userName
		setRememberMeCookie(request, response);
	}

	private void setRememberMeCookie(final HttpServletRequest request,
			final HttpServletResponse response) {
		final String rememberMe = request
				.getParameter("_spring_security_remember_me");
		if (rememberMe != null
				&& (rememberMe.equalsIgnoreCase("true")
						|| rememberMe.equalsIgnoreCase("on")
						|| rememberMe.equalsIgnoreCase("yes") || rememberMe
							.equals("1"))) {
			final Cookie usernameCookie = new Cookie("rememberMeCookie",
					request.getParameter("j_username"));
			usernameCookie.setHttpOnly(false);
			usernameCookie.setSecure(false);
			usernameCookie.setPath("/");
			usernameCookie.setMaxAge(getCookieMaxAge() == null ? 1296000
					: getCookieMaxAge());
			response.addCookie(usernameCookie);
		}
	}

	@Override
	public void deleteCookie(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (!request.isSecure())
		{
			LOG.error("Cannot remove secure GUIDCookie during an insecure request. I should have been called from a secure page.");
		}
		else
		{
			// Its a secure page, we can delete the cookie
			getCookieGenerator().removeCookie(response);
		}
	}

	protected String createGUID()
	{
		final String randomNum = String.valueOf(getRandom().nextInt());
		final byte[] result = getSha().digest(randomNum.getBytes());
		return String.valueOf(Hex.encodeHex(result));
	}

	protected CookieGenerator getCookieGenerator()
	{
		return cookieGenerator;
	}

	/**
	 * @param cookieGenerator
	 *           the cookieGenerator to set
	 */
	@Required
	public void setCookieGenerator(final CookieGenerator cookieGenerator)
	{
		this.cookieGenerator = cookieGenerator;
	}


	protected SecureRandom getRandom()
	{
		return random;
	}

	protected MessageDigest getSha()
	{
		return sha;
	}
	
	/**
	 * @return the cookieMaxAge
	 */
	public Integer getCookieMaxAge()
	{
		return cookieMaxAge;
	}



	/**
	 * @param cookieMaxAge
	 *           the cookieMaxAge to set
	 */
	public void setCookieMaxAge(Integer cookieMaxAge)
	{
		this.cookieMaxAge = cookieMaxAge;
	}
}
