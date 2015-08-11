<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="headline">
	<spring:theme code="text.account.identify" text="Identify"/>
</div>
<table class="account-profile-data">
	<tr>
		<td><spring:theme code="customer.status" text="Status"/>: </td>
		<td></td>
	</tr>
	<tr>
		<td><spring:theme code="profile.firstName" text="First name"/>: </td>
		<td>${fn:escapeXml(customerData.firstName)}</td>
	</tr>
	<tr>
		<td><spring:theme code="profile.lastName" text="Last name"/>: </td>
		<td>${fn:escapeXml(customerData.lastName)}</td>
	</tr>
	<tr>
		<td><spring:theme code="profile.email" text="E-mail"/>: </td>
		<td>${fn:escapeXml(customerData.displayUid)}</td>
	</tr>
</table>
