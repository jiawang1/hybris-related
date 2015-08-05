<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	
	<div class="column accountContentPane clearfix orderList">
		<div class="headline"><spring:theme code="text.account.orderHistory" text="Order History"/></div>
		
		<div class="walletBox">
			<c:url value="/my-account/viewMembershipCardHistory" var="encodedUrl" />
			<a href="${encodedUrl}"><spring:theme code="text.buyercenter.link.order.all" text="Update personal details"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/my-account/freeze-record?transType=Payment" var="encodedUrlPayment" />
			<a href="${encodedUrlPayment}"><spring:theme code="text.buyercenter.link.order.waitforpayment" text="Update personal details" arguments="1"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/my-account/freeze-record?transType=Refund" var="encodedUrlRefund" />
			<a href="${encodedUrlRefund}"><spring:theme code="text.buyercenter.link.order.waitforship" text="Update personal details" arguments="0"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/my-account/freeze-record?transType=ReplaceBlock" var="encodedUrlReplaceBlock" />
			<a href="${encodedUrlReplaceBlock}"><spring:theme code="text.buyercenter.link.order.waitforreceive" text="Update personal details" arguments="0"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/my-account/freeze-record?transType=CashOutBlock" var="encodedUrlCashOutBlock" />
			<a href="${encodedUrlCashOutBlock}"><spring:theme code="text.buyercenter.link.order.done" text="Update personal details" arguments="0"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/my-account/freeze-record?transType=CashOutBlock" var="encodedUrlCashOutBlock" />
			<a href="${encodedUrlCashOutBlock}"><spring:theme code="text.buyercenter.link.order.return" text="Update personal details" arguments="0"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/my-account/freeze-record?transType=CashOutBlock" var="encodedUrlCashOutBlock" />
			<a href="${encodedUrlCashOutBlock}"><spring:theme code="text.buyercenter.link.order.close" text="Update personal details" arguments="0"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/my-account/freeze-record?transType=CashOutBlock" var="encodedUrlCashOutBlock" />
			<a href="${encodedUrlCashOutBlock}"><spring:theme code="text.buyercenter.link.order.processing" text="Update personal details" arguments="0"/></a>
		</div>
		</br>
		<c:if test="${not empty searchPageData.results}">
			<table class="orderListTable">
				<thead>
					<tr>
						<th id="header1"><spring:theme code="text.buyercenter.anguoOrderHistory.productInfo" text="Order Number"/></th>
						<th id="header2"><spring:theme code="text.buyercenter.anguoOrderHistory.price" text="Order Status"/></th>
						<th id="header3"><spring:theme code="text.buyercenter.anguoOrderHistory.quantity" text="Date Placed"/></th>
						<th id="header4"><spring:theme code="text.buyercenter.anguoOrderHistory.total" text="Total"/></th>
						<th id="header5"><spring:theme code="text.account.orderHistory.orderStatus" text="Order Status"/></th>
						<th id="header6"><spring:theme code="text.buyercenter.anguoOrderHistory.actions" text="Actions"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${searchPageData.results}" var="order">

						<c:url value="/my-account/order/${order.code}" var="myAccountOrderDetailsUrl"/>
						<tr>
							<td colspan="5">
								<spring:theme code="text.buyercenter.anguoOrderHistory.orderNumber" text="Order Number"/>${order.code}&nbsp|&nbsp
								<spring:theme code="text.buyercenter.anguoOrderHistory.sellerName" text="Order Number"/>${order.code}&nbsp|&nbsp
								<spring:theme code="text.buyercenter.anguoOrderHistory.datePlaced" text="Order Number"/>
								<fmt:formatDate value="${order.placed}" dateStyle="long" timeStyle="short" type="both"/>
							</td>
						</tr>
						<c:forEach items="${order.entries}" var="entry">  
						
								<tr>
									<td headers="header1">
									<c:forEach items="${entry.product.images}" var="image">
												<c:if test="${image.format=='cartIcon'}">
													<a href="${myAccountOrderDetailsUrl}"><img src="${image.url}" /></a>
												</c:if>
									</c:forEach>
									
										<ycommerce:testId code="orderHistory_orderStatus_label">
											${entry.product.name}
										</ycommerce:testId>
									</td>
									
									<td headers="header2">
										<ycommerce:testId code="orderHistory_orderStatus_label">
											${entry.basePrice.formattedValue}
										</ycommerce:testId>
									</td>
									
									<td headers="header3">
										<ycommerce:testId code="orderHistory_orderStatus_label">
											<p>${entry.quantity}</p>
										</ycommerce:testId>
									</td>
									
									<c:if test="${ entry.entryNumber == '0'}">
										<td headers="header4">
											<ycommerce:testId code="orderHistory_orderStatus_label">
												<p>${order.total.formattedValue}</p>
											</ycommerce:testId>
										</td>
	
										<td headers="header5">
											<ycommerce:testId code="orderHistory_orderStatus_label">
												<p><spring:theme code="text.account.order.status.display.${order.statusDisplay}"/></p>
											</ycommerce:testId>
										</td>
									</c:if>
								</tr>
						</c:forEach>
						 
					</c:forEach>
				</tbody>
			</table>

			<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"  supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page"  numberPagesShown="${numberPagesShown}"/>

		</c:if>
		<c:if test="${empty searchPageData.results}">
			<p><spring:theme code="text.account.orderHistory.noOrders" text="You have no orders"/></p>
		</c:if>
	</div>
	

</template:page>

