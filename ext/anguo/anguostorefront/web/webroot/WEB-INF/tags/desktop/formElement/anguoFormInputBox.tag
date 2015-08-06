<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="idKey" required="true" type="java.lang.String" %>
<%@ attribute name="labelKey" required="true" type="java.lang.String" %>
<%@ attribute name="path" required="true" type="java.lang.String" %>
<%@ attribute name="desc" required="false" type="java.lang.String" %>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean" %>
<%@ attribute name="labelCSS" required="false" type="java.lang.String" %>
<%@ attribute name="inputCSS" required="false" type="java.lang.String" %>
<%@ attribute name="tabindex" required="false" rtexprvalue="true" %>
<%@ attribute name="autocomplete" required="false" type="java.lang.String" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
 
<template:anguoErrorSpanField path="${path}">
	<ycommerce:testId code="LoginPage_Item_${idKey}">

	<label for="${idKey}">
		<c:if test="${mandatory != null && mandatory == true}">
            <b>*</b>
		</c:if>
        <spring:theme code="${labelKey}"/>
	</label>
	<form:input cssClass="${inputCSS}" id="${idKey}" path="${path}" tabindex="${tabindex}" autocomplete="${autocomplete}"/><span>
    <c:if test="${desc != null}">
        <spring:theme code="${desc}"/></span>
    </c:if>
	</ycommerce:testId>
</template:anguoErrorSpanField>
