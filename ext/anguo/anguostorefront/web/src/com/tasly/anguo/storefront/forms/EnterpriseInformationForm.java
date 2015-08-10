package com.tasly.anguo.storefront.forms;

import java.util.List;
import com.tasly.anguo.facades.user.data.ContactData;
public class EnterpriseInformationForm {
    private String firstTimeUpdate;
    public String getFirstTimeUpdate() {
        return firstTimeUpdate;
    }
    public void setFirstTimeUpdate(String firstTimeUpdate) {
        this.firstTimeUpdate = firstTimeUpdate;
    }
    private String name;
    private String registerId;
    private String address;
    private String phone;
    private String fax;
    private List<ContactData> contacts;

    public List<ContactData> getContacts() {
        return contacts;
    }
    public void setContacts(List<ContactData> contacts) {
        this.contacts = contacts;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRegisterId() {
        return registerId;
    }
    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }

}
