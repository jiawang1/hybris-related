package com.tasly.anguo.storefront.forms;


public class PersonalIdentifyForm  extends AccountIdentifyForm{

	private String idName;
	private String idCard;
	private String accountOwer;

	/**
	 * @return the idName
	 */
	public String getIdName() {
		return idName;
	}

	/**
	 * @param idName
	 *            the idName to set
	 */
	public void setIdName(String idName) {
		this.idName = idName;
	}

	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard
	 *            the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the accountOwer
	 */
	public String getAccountOwer() {
		return accountOwer;
	}

	/**
	 * @param accountOwer
	 *            the accountOwer to set
	 */
	public void setAccountOwer(String accountOwer) {
		this.accountOwer = accountOwer;
	}

}
