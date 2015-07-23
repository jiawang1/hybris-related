<html>
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="compressible"
	tagdir="/WEB-INF/tags/desktop/template/compressible"%>
<compressible:js />
<script type="text/javascript" src="${commonResourcePath}/js/acc.selector.js"></script>

</head>
<body>
	<select name="regionSelector" id="regionSelector">
		<c:forEach items="${regions}" var="region">
			<option>${region.isocode }</option>
		</c:forEach>
	</select>
	<select name="citySelector" id="citySelector">
	</select>
	<select name="districtSelector" id="districtSelector">
	</select>
</body>
</html>