package com.tasly.anguo.storefront.forms.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tasly.anguo.facades.user.data.ContactData;
import com.tasly.anguo.storefront.forms.EnterpriseInformationForm;


/**
 * @author i319510
 *
 */
@Component("anguoEnterpriseInformationValidator")
public class AnguoEnterpriseInformationValidator implements Validator
{
    public static final String NUMBER_REGEX = "^[0-9]*$";
	@Override
	public boolean supports(Class<?> arg0)
	{
		return AnguoEnterpriseInformationValidator.class.equals(arg0);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
	    /**
企业名称：必填项，字符串型，长度为100个字符，该字段提交后不可更改；企业账户对外显示时用户名取该企业名称字段，且在交易时企业用户可以选择匿名/非匿名显示。
企业注册号：必填项，整型，15位（新）或13位（旧），该字段提交后不可更改，且做唯一性校验，若注册认证成功的企业账户中已经使用该注册号，则该号不能再次被使用。
企业地址：非必填项，字符串型，长度为300个字符；
企业座机号：非必填项，整型，格式为区号+固话号；长度为30个字符
企业传真：非必填项，整型，格式为区号+固话号；长度为30个字符
联系人：非必填项，列表格式，可以有多行记录，每行包含信息”联系人姓名“，“部门职位”，“联系方式”；

    <formElement:anguoFormInputBox idKey="address" labelKey="text.account.enterprise.address" path="address" inputCSS="text"/>
    <formElement:anguoFormInputBox idKey="phone" labelKey="text.account.enterprise.phone" path="phone" inputCSS="text"/>
    <formElement:anguoFormInputBox idKey="fax" labelKey="text.account.enterprise.fax" path="fax" inputCSS="text"/>

第二步，填完必须的信息后，保存，进入认证界面。
<div><label><spring:theme code="text.account.enterprise.contact.name"/></label><input type="text" name="contacts[${loop.index}].name" value="${contact.name}"></div>
<div><label><spring:theme code="text.account.enterprise.contact.position"/></label><input type="text" name="contacts[${loop.index}].posistion" value="${contact.posistion }"></div>
<div><label><spring:theme code="text.account.enterprise.contact.phone"/></label><input type="text" name="contacts[${loop.index}].contactInfo" value="${contact.contactInfo }"></div>
      

备注：
    联系人姓名：30个字符
    部门职位：100个字符
    邮编：12个字符
	     */
		final EnterpriseInformationForm eif = (EnterpriseInformationForm) object;

		if (StringUtils.isEmpty(eif.getName()) || StringUtils.length(eif.getName()) > 100)
		{
			errors.rejectValue("name", "text.account.enterprise.name.invalid");
		}
	    if (StringUtils.isEmpty(eif.getRegisterId()) || (StringUtils.length(eif.getRegisterId().trim()) != 13 
	            && StringUtils.length(eif.getRegisterId().trim()) != 15) || !isNumber(eif.getRegisterId()))
	    {
	        errors.rejectValue("registerId", "text.account.enterprise.register.id.invalid");
	    }
	    if (StringUtils.length(eif.getAddress()) > 300) {
            errors.rejectValue("address", "text.account.enterprise.address.invalid");	        
	    }
        if (StringUtils.length(eif.getPhone()) > 30 || !isNumber(eif.getPhone())) {
            errors.rejectValue("phone", "text.account.enterprise.phone.invalid");            
        }
        if (StringUtils.length(eif.getFax()) > 30 || !isNumber(eif.getFax())) {
            errors.rejectValue("fax", "text.account.enterprise.fax.invalid");             
        }
        
       List<ContactData> list = eif.getContacts();
       if (list != null) {
           for(int i = 0; i < list.size(); i++) {
               ContactData cd = list.get(i);
               if (StringUtils.length(cd.getName()) > 30) {
                   errors.rejectValue("contacts["+i+"].name", "text.account.enterprise.contact.name.invalid");
               }
               if (StringUtils.length(cd.getPosition()) > 100) {
                   errors.rejectValue("contacts["+i+"].position", "text.account.enterprise.contact.position.invalid");
               }
               if (StringUtils.length(cd.getContactInfo()) > 30 || !isNumber(cd.getContactInfo())) {
                   errors.rejectValue("contacts["+i+"].contactInfo", "text.account.enterprise.contact.phone.invalid");
               }
           }
       }
	}
	
    public boolean isNumber(final String str) {
        final Pattern pattern = Pattern.compile(NUMBER_REGEX);
        final Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
