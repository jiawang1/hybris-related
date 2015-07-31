<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>

<c:if test="${navigationNode.visible}">
	<div class="accountNav">
		<div class="headline">
			${navigationNode.title}
		</div>
		<ul>
			<c:forEach items="${navigationNode.links}" var="link">
				<c:set value="${ requestScope['javax.servlet.forward.servlet_path'] == link.url ? 'active':'' }" var="selected"/>
				<cms:component component="${link}" evaluateRestriction="true" element="li" class=" ${selected}"/>
			</c:forEach>
            <li class="yCmsComponent">
<a href="/anguostorefront/anguo/zh/my-account/enterprise"  title='<spring:theme code="text.account.enterprise.information.update"></spring:theme>' ><spring:theme code="text.account.enterprise.information.update"></spring:theme></a></li>
		</ul>
	</div>
</c:if>
