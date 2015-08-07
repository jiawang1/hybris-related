<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header" %>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/desktop/common/footer" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<template:master pageTitle="${pageTitle}">

	<jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss"/>
	</jsp:attribute>
 
	<jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts"/>
	</jsp:attribute>

	<jsp:body>
<header:header hideHeaderLinks="${hideHeaderLinks}"/>
  <div class="Mebnav">
    <div class="Frame">
     <div class="Zong"><spring:theme code="ango.top.navigation.classification"/></div>
     <div class="nav">
      <ul>
       <li><spring:theme code="ango.top.navigation.store"/></li>
       <li><spring:theme code="ango.top.navigation.supply.demand.information"/></li>
       <li><spring:theme code="ango.top.navigation.auction"/></li>
       <li><spring:theme code="ango.top.navigation.bidding.procurement"/></li>
       <li><spring:theme code="ango.top.navigation.financial.services"/></li>
       <li><spring:theme code="ango.top.navigation.warehouse.logistics"/></li>
      </ul>     
     </div>
    </div>
</div>
		<div id="page" class="digitalHerbal" data-currency-iso-code="${currentCurrency.isocode}">
			<spring:theme code="text.skipToContent" var="skipToContent"/>
			<a href="#skip-to-content" class="skiptocontent" data-role="none">${skipToContent}</a>
			<spring:theme code="text.skipToNavigation" var="skipToNavigation"/>
			<a href="#skiptonavigation" class="skiptonavigation" data-role="none">${skipToNavigation}</a>
			
			<a id="skiptonavigation"></a>
			<%-- <nav:topNavigation/> --%>
			<header:bottomHeader />
			<div id="content" class="clearfix">
			<a id="skip-to-content"></a>
				<jsp:doBody/>
			</div>
		</div>

			<footer:footer/>
	</jsp:body>
	
</template:master>
