<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:url var="actionUrl" value="${fn:replace(url, '{productCode}', product.code)}" scope="page"/>
<p><a href="${actionUrl}">
    <spring:theme code="basket.add.to.buy" text="AddToBuy"/>
</a></p>
