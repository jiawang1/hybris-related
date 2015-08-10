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
	
	<div class="column accountContentPane clearfix orderList">
		<div class="headline"><spring:theme code="text.account.orderHistory" text="Order History"/></div>
		
		<div class="walletBox">
			<c:url value="/anguo-buyercenter/orders" var="encodedUrl" />
			<a href="${encodedUrl}"><spring:theme code="text.buyercenter.link.order.all" text="Update personal details"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/anguo-buyercenter/orders?status=wait_for_payment" var="waitforpayment" />
			<a href="${waitforpayment}"><spring:theme code="text.buyercenter.link.order.waitforpayment" text="Update personal details" arguments="${anguoOrderStatusCountData.wait_for_payment == null ? 0 : anguoOrderStatusCountData.wait_for_payment}"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/anguo-buyercenter/orders?status=wait_for_ship" var="waitforship" />
			<a href="${waitforship}"><spring:theme code="text.buyercenter.link.order.waitforship" text="Update personal details" arguments="${anguoOrderStatusCountData.wait_for_ship == null ? 0 : anguoOrderStatusCountData.wait_for_ship}"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/anguo-buyercenter/orders?status=wait_for_receive" var="waitforreceive" />
			<a href="${waitforreceive}"><spring:theme code="text.buyercenter.link.order.waitforreceive" text="Update personal details" arguments="${anguoOrderStatusCountData.wait_for_receive == null ? 0 : anguoOrderStatusCountData.wait_for_receive}"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/anguo-buyercenter/orders?status=completed" var="done" />
			<a href="${done}"><spring:theme code="text.buyercenter.link.order.done" text="Update personal details" arguments="${anguoOrderStatusCountData.completed == null ? 0 : anguoOrderStatusCountData.completed}"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/anguo-buyercenter/orders?status=returning" var="returning" />
			<a href="${returning}"><spring:theme code="text.buyercenter.link.order.return" text="Update personal details" arguments="${anguoOrderStatusCountData.returning == null ? 0 : anguoOrderStatusCountData.returning}"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/anguo-buyercenter/orders?status=cancelled" var="close" />
			<a href="${close}"><spring:theme code="text.buyercenter.link.order.close" text="Update personal details" arguments="${anguoOrderStatusCountData.cancelled == null ? 0 : anguoOrderStatusCountData.cancelled}"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
			
			<c:url value="/anguo-buyercenter/orders?status=system_processing" var="processing" />
			<a href="${processing}"><spring:theme code="text.buyercenter.link.order.processing" text="Update personal details" arguments="${anguoOrderStatusCountData.system_processing == null ? 0 : anguoOrderStatusCountData.system_processing}"/></a>
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
						<c:forEach items="${order.entries}" var="entry" varStatus="loop">  
						
								<tr>
									<td headers="header1" style="max-width:270px">
										<div style="vertical-align:middle;">
										<c:forEach items="${entry.product.images}" var="image">
												<c:if test="${image.format=='cartIcon'}">
													<a href="${myAccountOrderDetailsUrl}"><img src="${image.url}" align="left"/></a>
												</c:if>
										</c:forEach>
									
										<ycommerce:testId code="orderHistory_orderStatus_label">
											<p>${entry.product.name}</p>
										</ycommerce:testId>
										</div>
									</td>
									
									<td headers="header2">
										<ycommerce:testId code="orderHistory_orderStatus_label">
											<p>${entry.basePrice.formattedValue}</p>
										</ycommerce:testId>
									</td>
									
									<td headers="header3">
										<ycommerce:testId code="orderHistory_orderStatus_label">
											<p>${entry.quantity}</p>
										</ycommerce:testId>
									</td>
									<c:if test="${ loop.index == 0}">
										<td headers="header4">
											<ycommerce:testId code="orderHistory_orderStatus_label">
												<p>${order.total.formattedValue}</p>
											</ycommerce:testId>
										</td>
	
										<td headers="header5">
											<ycommerce:testId code="orderHistory_orderStatus_label">
												<p><spring:theme code="text.buyercenter.order.status.display.${order.status}"/></p>
											</ycommerce:testId>
										</td>
									</c:if>
								</tr>
						</c:forEach>
						 
					</c:forEach>
				</tbody>
			</table>

			<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"  supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/anguo-buyercenter/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page"  numberPagesShown="${numberPagesShown}"/>

		</c:if>
		<c:if test="${empty searchPageData.results}">
			<p><spring:theme code="text.account.orderHistory.noOrders" text="You have no orders"/></p>
		</c:if>
	</div>
	


