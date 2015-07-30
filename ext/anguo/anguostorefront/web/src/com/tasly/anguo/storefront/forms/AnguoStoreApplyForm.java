package com.tasly.anguo.storefront.forms;

import java.util.Date;

import org.apache.commons.fileupload.FileItem;


public class AnguoStoreApplyForm
{
	private String name;

	private String description;

	private String countryIso;

	private String regionIso;

	private String cityCode;

	private String cityDistrictCode;

	private String street;

	private String contactName1;

	private String contactPhone1;

	private String contactName2;

	private String contactPhone2;

	private String contactName3;

	private String contactPhone3;

	private FileItem logo;
	
	private String logoUrl;

	private String qq;

	private String fax;

	private String telephone;
	
	private String approveStatus;
	
	private String fieldsNeedApprove;
	
	private String registerTime;
	
	private String storeLevel;
	
	private String storeTemplate;
	
	private String anguoPlatformService;


	public String getCountryIso()
	{
		return countryIso;
	}

	public void setCountryIso(String countryIso)
	{
		this.countryIso = countryIso;
	}

	public FileItem getLogo()
	{
		return logo;
	}

	public void setLogo(FileItem logo)
	{
		this.logo = logo;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(final String street)
	{
		this.street = street;
	}

	public String getContactName1()
	{
		return contactName1;
	}

	public void setContactName1(final String contactName1)
	{
		this.contactName1 = contactName1;
	}

	public String getContactPhone1()
	{
		return contactPhone1;
	}

	public void setContactPhone1(final String contactPhone1)
	{
		this.contactPhone1 = contactPhone1;
	}

	public String getContactName2()
	{
		return contactName2;
	}

	public void setContactName2(final String contactName2)
	{
		this.contactName2 = contactName2;
	}

	public String getContactPhone2()
	{
		return contactPhone2;
	}

	public void setContactPhone2(final String contactPhone2)
	{
		this.contactPhone2 = contactPhone2;
	}

	public String getContactName3()
	{
		return contactName3;
	}

	public void setContactName3(final String contactName3)
	{
		this.contactName3 = contactName3;
	}

	public String getContactPhone3()
	{
		return contactPhone3;
	}

	public void setContactPhone3(final String contactPhone3)
	{
		this.contactPhone3 = contactPhone3;
	}


	public String getQq()
	{
		return qq;
	}

	public void setQq(final String qq)
	{
		this.qq = qq;
	}

	public String getFax()
	{
		return fax;
	}

	public void setFax(final String fax)
	{
		this.fax = fax;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(final String telephone)
	{
		this.telephone = telephone;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}


	public String getRegionIso()
	{
		return regionIso;
	}

	public void setRegionIso(final String regionIso)
	{
		this.regionIso = regionIso;
	}

	public String getCityCode()
	{
		return cityCode;
	}

	public void setCityCode(final String cityCode)
	{
		this.cityCode = cityCode;
	}

	public String getCityDistrictCode()
	{
		return cityDistrictCode;
	}

	public void setCityDistrictCode(final String cityDistrictCode)
	{
		this.cityDistrictCode = cityDistrictCode;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getFieldsNeedApprove() {
		return fieldsNeedApprove;
	}

	public void setFieldsNeedApprove(String fieldsNeedApprove) {
		this.fieldsNeedApprove = fieldsNeedApprove;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getStoreLevel() {
		return storeLevel;
	}

	public void setStoreLevel(String storeLevel) {
		this.storeLevel = storeLevel;
	}

	public String getStoreTemplate() {
		return storeTemplate;
	}

	public void setStoreTemplate(String storeTemplate) {
		this.storeTemplate = storeTemplate;
	}

	public String getAnguoPlatformService() {
		return anguoPlatformService;
	}

	public void setAnguoPlatformService(String anguoPlatformService) {
		this.anguoPlatformService = anguoPlatformService;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
}
