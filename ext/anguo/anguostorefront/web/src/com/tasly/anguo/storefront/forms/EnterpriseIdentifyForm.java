package com.tasly.anguo.storefront.forms;

import java.util.Map;

public class EnterpriseIdentifyForm extends AccountIdentifyForm {

	private String companyName;

	private String registeredNo;

	private String legalRepr;

	private String[] licenses;

	private Map<String, String> licenseUrls;

	/**
	 * @return the licenseUrls
	 */
	public Map<String, String> getLicenseUrls() {
		return licenseUrls;
	}

	/**
	 * @param licenseUrls
	 *            the licenseUrls to set
	 */
	public void setLicenseUrls(Map<String, String> licenseUrls) {
		this.licenseUrls = licenseUrls;
	}

	/**
	 * @return the registeredNo
	 */
	public String getRegisteredNo() {
		return registeredNo;
	}

	/**
	 * @param registeredNo
	 *            the registeredNo to set
	 */
	public void setRegisteredNo(String registeredNo) {
		this.registeredNo = registeredNo;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the legalRepr
	 */
	public String getLegalRepr() {
		return legalRepr;
	}

	/**
	 * @param legalRepr
	 *            the legalRepr to set
	 */
	public void setLegalRepr(String legalRepr) {
		this.legalRepr = legalRepr;
	}

	/**
	 * @return the licenses
	 */
	public String[] getLicenses() {
		return licenses;
	}

	/**
	 * @param licenses
	 *            the licenses to set
	 */
	public void setLicenses(String[] licenses) {
		this.licenses = licenses;
	}

}
