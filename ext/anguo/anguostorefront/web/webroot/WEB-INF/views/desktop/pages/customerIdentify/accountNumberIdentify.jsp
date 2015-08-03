<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal Information Identify</title>
</head>
<body>
<h1 align="center"><spring:theme code="identify.title"></spring:theme></h1>
    <div id="globalMessages">
        <common:globalMessages/>
    </div>
<div class="form_field-elements js-recaptcha-captchaaddon">
<form:form method="post" commandName="personalIdentifyForm" action="${contextPath }/identify/identifyAccountNumber">
<formElement:formInputBox idKey="bank" labelKey="identify.bank" path="bank" inputCSS="text" mandatory="true"/>
<formElement:formInputBox idKey="accountOwer" labelKey="identify.accountOwer" path="accountOwer" inputCSS="text" mandatory="true"/>
<formElement:formInputBox idKey="accountNumber" labelKey="identify.accountNumber" path="accountNumber" inputCSS="text" mandatory="true"/>
<button id="identifyButton" type="submit" class="positive"><spring:theme code="text.identify"/></button>
</form:form>
</div>
</body>
</html>