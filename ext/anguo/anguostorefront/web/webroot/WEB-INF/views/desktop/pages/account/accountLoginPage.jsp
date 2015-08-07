<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">


	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>

	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	
	
	<cms:pageSlot position="TopContent" var="feature" element="div">
		<cms:component component="${feature}"/>
	</cms:pageSlot>
	
	
	<div class="span-24">
	<!-- below commented component may be used in future -->
<%-- 		<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4">
			<cms:component component="${feature}"/>
		</cms:pageSlot> --%>

		<div class="span-12">
			<c:url value="/j_spring_security_check" var="loginActionUrl" />
			<user:login actionNameKey="login.login" action="${loginActionUrl}"/>
		</div>
		
		<cms:pageSlot position="RightContentSlot" var="feature" element="div" class="span-12 last">
			<cms:component component="${feature}"/>
		</cms:pageSlot>		
	</div>
    <div style="clear:both"></div>
</template:page>
