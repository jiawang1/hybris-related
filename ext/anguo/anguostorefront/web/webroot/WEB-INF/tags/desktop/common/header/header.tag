<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<%-- Test if the UiExperience is currently overriden and we should show the UiExperience prompt --%>
<c:if test="${uiExperienceOverride and not sessionScope.hideUiExperienceLevelOverridePrompt}">
	<c:url value="/_s/ui-experience?level=" var="clearUiExperienceLevelOverrideUrl"/>
	<c:url value="/_s/ui-experience-level-prompt?hide=true" var="stayOnDesktopStoreUrl"/>
	<div class="backToMobileStore">
		<a href="${clearUiExperienceLevelOverrideUrl}"><span class="greyDot">&lt;</span><spring:theme code="text.swithToMobileStore" /></a>
		<span class="greyDot closeDot"><a href="${stayOnDesktopStoreUrl}">x</a></span>
	</div>
</c:if>
<div id="header" class="clearfix Wrap">

<%-- 	<cms:pageSlot position="TopHeaderSlot" var="component">
		<cms:component component="${component}"/>
	</cms:pageSlot>
	<div class="headerContent ">
		<ul class="nav clearfix">
			<cms:pageSlot position="HeaderLinks" var="links">
				<cms:component component="${links}"/>
			</cms:pageSlot>
 
			<cms:pageSlot position="MiniCart" var="cart" limit="1">
				<cms:component component="${cart}" element="li" class="miniCart" />
			</cms:pageSlot>
		</ul>
	</div>
	<cms:pageSlot position="SearchBox" var="component" element="div" class="headerContent secondRow">
		<cms:component component="${component}" element="div" />
	</cms:pageSlot> --%>
  <div class="MedHeader">
   <div class="Topnav">
    <div class="Frame">
     <div class="Topleft"><p style=" margin-left:0px;"><spring:theme code="ango.top.welcome"/></p><p>
        <cms:pageSlot position="HeaderLinks" var="links">
			<cms:component component="${links}"/>
		</cms:pageSlot></p>
	 </div>
     <div class="Topright">
      <ul>
       <li><a href="${contextPath}/anguo-buyercenter"><spring:theme code="ango.top.buyerscenter"/></a></li>
       <li><a href="${contextPath}/anguo-storemanagerment/anguoStoreapplypage"><spring:theme code="ango.top.sellercenter"/></a></li>
       <li><a href="${contextPath}/my-account"><spring:theme code="ango.top.accountmanagement"/></a></li>
       <li><cms:pageSlot position="MiniCart" var="cart">
				<cms:component component="${cart}" class="miniCart" />
			</cms:pageSlot></li>
       <li><a href="#"><spring:theme code="ango.top.Favorites"/></a></li>
       <li><a href="#"><spring:theme code="ango.top.sethome"/></a></li>
       <li><a href="#"><spring:theme code="ango.top.favorites"/></a></li>
       <spring:theme code=""/>
      </ul>
     </div>
    </div>
   </div>
   <div class="MebTitle Frame">
    <div class="Meblogo">
		<cms:pageSlot position="SiteLogo" var="logo" limit="1">
			<cms:component component="${logo}"  element="div"/>
		</cms:pageSlot>
	    <p><spring:theme code="ango.top.search.largest.platform"/></p>
    </div>
    <div class="searchBox">
      <div class="tabList">
	    <cms:pageSlot position="SearchBox" var="component" element="div" class="headerContent secondRow">
			<cms:component component="${component}" element="div" />
		</cms:pageSlot>
		</div>
    </div>
    <div class="Phone"><img src="${contextPath}/_ui/desktop/common/images/phone.jpg" alt="" /></div>
    <div class="erweima"><img src="${contextPath}/_ui/desktop/common/images/erweima.jpg" alt="" /></div>
</div> 
  </div>
</div>
