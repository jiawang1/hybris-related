<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">
	<jsp:attribute name="pageScripts">
		<script type="text/javascript" src="${commonResourcePath}/js/acc.productlisting.js"></script>
	</jsp:attribute>
	
	<jsp:body>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<cms:pageSlot position="Section1" var="feature">
		<cms:component component="${feature}" element="div" class="span-24 section1 cms_disp-img_slot"/>
	</cms:pageSlot>

	<div class="span-24">
	<div class="Fnav">
   <div class="all-goods">
        <div class="item btnone">
            <cms:pageSlot position="SectionLeftNav1" var="feature" element="div" class="product">
		      <cms:component component="${feature}"/>
	        </cms:pageSlot>
          <div class="product-wrap posone"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot1" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv1" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav2" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap postwo"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot2" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv2" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav3" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posthree"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot3" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv3" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav4" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posfour"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot4" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv4" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav5" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posfive"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot5" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv5" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav6" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap possix"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot6" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv6" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav7" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posseven"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot7" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv7" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav8" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap poseight"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot8" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv8" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav9" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posnine"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot9" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv9" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav10" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posten"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot10" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv10" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
     </div>
   </div>
		<div class="span-6 facetNavigation topmargin">
			<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
			<%-- <nav:facetNavRefinements pageData="${searchPageData}"/> --%>

			<cms:pageSlot position="Section5" var="feature">
				<cms:component component="${feature}" element="div" class="section5 cms_disp-img_slot"/>
			</cms:pageSlot>
		</div>
		<div class="span-18 last">

			<div id="breadcrumb" class="breadcrumb">
				<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
			</div>
			<cms:pageSlot position="Section2" var="feature">
				<cms:component component="${feature}" element="div" class="section2 cms_disp-img_slot"/>
			</cms:pageSlot>
			
				<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
		

		
				<cms:pageSlot position="Section3" var="feature">
					<cms:component component="${feature}" element="div" class="section3 cms_disp-img_slot"/>
				</cms:pageSlot>

				<product:productLister pageData="${searchPageData}" path="${searchPageData.categoryCode}"/>

				<cms:pageSlot position="Section4" var="feature">
					<cms:component component="${feature}" element="div" class="section4 cms_disp-img_slot last"/>
				</cms:pageSlot>
			
			
				<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
		
		</div>
	</div>
</jsp:body>


</template:page>