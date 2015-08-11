<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>

<div class="headline"></div>
<c:forEach items="${navigationNode}" var="node">
	<c:if test="${node.visible}">
		<div class="accountNav">
			<ul>
				<c:forEach items="${node.links}" var="link">
					<c:set value="${ requestScope['javax.servlet.forward.servlet_path'] == link.url ? 'active':'' }" var="selected"/>
					<cms:component component="${link}" evaluateRestriction="true" element="li" class=" ${selected}"/>
				</c:forEach>
			</ul>
		</div>
	</c:if>
</c:forEach>