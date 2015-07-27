<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<spring:theme code="text.addToCart" var="addToCartText"/>

<div class="productDetailsPanel">

	<product:productImagePanel product="${product}" galleryImages="${galleryImages}"/>

	<div class="span-10 productDescription last">
		<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
			<h1>
					${fn:escapeXml(product.name)}
			</h1>
		</ycommerce:testId>

        <ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
            <product:productPricePanel product="${product}"  table="false" />
        </ycommerce:testId>

		<product:productReviewSummary product="${product}"/>
        <!--
		<div class="summary">
			${fn:escapeXml(product.summary)}
		</div>-->
        <!--界面所带的三种属性-->
        <div class="maijia">
        <ul>
		<li class="norm">
			<c:forEach items="${product.classifications}" var="classification">
				<c:forEach items="${classification.features}" var="feature">
				<c:if test="${feature.code eq 'PowertoolsClassification/1.0/4593.motor diameter, 6752'}">
						${feature.name}
				</c:if>
				</c:forEach>
			</c:forEach> :
		</li>
		<li>
			<c:forEach items="${product.classifications}" var="classification">
				<c:forEach items="${classification.features}" var="feature">
				<c:if test="${feature.code eq 'PowertoolsClassification/1.0/4593.motor diameter, 6752'}">
					<c:forEach items="${feature.featureValues}" var="value" varStatus="status">
						${value.value}
					</c:forEach>
				</c:if>
				</c:forEach>
			</c:forEach>
		</li>
		<li class="norm">
			<c:forEach items="${product.classifications}" var="classification">
				<c:forEach items="${classification.features}" var="feature">
				<c:if test="${feature.code eq 'PowertoolsClassification/1.0/4593.chuck type, 6751'}">
						${feature.name}
				</c:if>
				</c:forEach>
			</c:forEach> :
		</li>
		<li>
			<c:forEach items="${product.classifications}" var="classification">
				<c:forEach items="${classification.features}" var="feature">
				<c:if test="${feature.code eq 'PowertoolsClassification/1.0/4593.chuck type, 6751'}">
					<c:forEach items="${feature.featureValues}" var="value" varStatus="status">
						${value.value}
					</c:forEach>
				</c:if>
				</c:forEach>
			</c:forEach>
		</li>
		<li class="norm">
			<c:forEach items="${product.classifications}" var="classification">
				<c:forEach items="${classification.features}" var="feature">
				<c:if test="${feature.code eq 'PowertoolsClassification/1.0/4593.impact energy, 6782'}">
						${feature.name}
				</c:if>
				</c:forEach>
			</c:forEach> :
		</li>
		<li>
			<c:forEach items="${product.classifications}" var="classification">
				<c:forEach items="${classification.features}" var="feature">
				<c:if test="${feature.code eq 'PowertoolsClassification/1.0/4593.impact energy, 6782'}">
					<c:forEach items="${feature.featureValues}" var="value" varStatus="status">
						${value.value}
					</c:forEach>
				</c:if>
				</c:forEach>
			</c:forEach>
		</li>
		</ul>
		</div>
		<!--界面所带的三种属性-->
		
		<product:productPromotionSection product="${product}"/>

		<cms:pageSlot position="VariantSelector" var="component" element="div">
			<cms:component component="${component}"/>
		</cms:pageSlot>

		<cms:pageSlot position="AddToCart" var="component" element="div">
			<cms:component component="${component}"/>
		</cms:pageSlot>

		<product:productShareTag/>
	</div>

	<cms:pageSlot position="Section2" var="feature" element="div" class="span-8 section2 cms_disp-img_slot last">
		<cms:component component="${feature}"/>
	</cms:pageSlot>
</div>
