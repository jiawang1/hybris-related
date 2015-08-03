<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.11.3.min.js"></script>
<title>
</title>
</head>
<h1><spring:theme code="text.account.enterprise.information.update"></spring:theme></h1>
    <form:form method="post" commandName="enterpriseInformationForm" action="${action}">
        <div class="form_field-elements js-recaptcha-captchaaddon">
<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
<div><h2><spring:theme code="text.account.enterprise.welcome" arguments="${user.firstName},${user.lastName}%>"/></h2></div>
</sec:authorize>
    <formElement:formInputBox idKey="name" labelKey="text.account.enterprise.name" path="name" inputCSS="text"/>
    <formElement:formInputBox idKey="registerId" labelKey="text.account.enterprise.register.id" path="registerId" inputCSS="text"/>
    <formElement:formInputBox idKey="address" labelKey="text.account.enterprise.address" path="address" inputCSS="text"/>
    <formElement:formInputBox idKey="phone" labelKey="text.account.enterprise.phone" path="phone" inputCSS="text"/>
    <formElement:formInputBox idKey="fax" labelKey="text.account.enterprise.fax" path="fax" inputCSS="text"/>
    <div><label for="contact"><spring:theme code="text.account.enterprise.contact"/></label><input type="text" id="contact" name="contact"></div>
    <formElement:formInputBox idKey="contact" labelKey="text.account.enterprise.contact" path="contact" inputCSS="text"/>
    <div class="form-actions clearfix">
            <ycommerce:testId code="register_Register_button">
                <button id="registerButton" type="submit" class="positive"><spring:theme code='text.account.enterprise.next'/></button>
            </ycommerce:testId>
    </div>
    <!-- 
    <div><label for="name"><spring:theme code="text.account.enterprise.phone"/></label><input type="text" id="phone" name="phone"></div>
    <div><label for="name"><spring:theme code="text.account.enterprise.fax"/></label><input type="text" id="fax" name="fax"></div>
    <div><label for="name"><spring:theme code="text.account.enterprise.contact"/></label><input type="text" id="contact" name="contact"></div>
     -->
    </div>
</form:form>
</html>


