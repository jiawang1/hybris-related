package com.tasly.anguo.storefront.forms;

import org.apache.commons.fileupload.FileItem;

public class EnterpriseIdentifyForm {

	private String companyName;

	private String bank;

	private String accountNumber;

	private String legalRepr;

	private FileItem[] licenses;

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
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	public FileItem[] getLicenses() {
		return licenses;
	}

	/**
	 * @param licenses
	 *            the licenses to set
	 */
	public void setLicenses(FileItem[] licenses) {
		this.licenses = licenses;
	}

}
