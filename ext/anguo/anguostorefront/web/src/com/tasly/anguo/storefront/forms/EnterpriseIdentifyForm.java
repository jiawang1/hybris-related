package com.tasly.anguo.storefront.forms;


public class EnterpriseIdentifyForm  extends AccountIdentifyForm{

	private String companyName;

	private String legalRepr;

	private String[] licenses;

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
	 * @param licenses the licenses to set
	 */
	public void setLicenses(String[] licenses) {
		this.licenses = licenses;
	}


}
