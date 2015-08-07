<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="compressible" tagdir="/WEB-INF/tags/desktop/template/compressible"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Enterprise Information Identify</title>
<link href="${commonResourcePath}/css/uploadfile.css" type="text/css" rel="stylesheet" />
<compressible:js />
</head>
<body>
<h1 align="center"><spring:theme code="identify.title"></spring:theme></h1>
    <div id="globalMessages">
        <common:globalMessages/>
    </div>
<div class="form_field-elements js-recaptcha-captchaaddon">
<form:form method="post" commandName="enterpriseIdentifyForm" action="${contextPath }/identify/identifyEnterprise">
<input type="hidden" id="CSRFToken" value="${CSRFToken}"/>
<formElement:formInputBox idKey="companyName" labelKey="identify.companyName" path="companyName" inputCSS="text" mandatory="true"/>
<formElement:formInputBox idKey="bank" labelKey="identify.bank" path="bank" inputCSS="text" mandatory="true"/>
<formElement:formInputBox idKey="accountNumber" labelKey="identify.accountNumber" path="accountNumber" inputCSS="text" mandatory="true"/>
<formElement:formInputBox idKey="legalRepr" labelKey="identify.legalRepr" path="legalRepr" inputCSS="text" mandatory="true"/>
	<div class="input_hint">
		<p><spring:theme code="allowed.format"/>:jpg,png,bmp,jpeg / <spring:theme code="allowed.maxSize"/>: 2MB</p>
	</div>
	<div id="docfileuploader">Upload</div>
			<div class="clearDiv"></div>
			<div class="uploadedFiles" >
				<c:if test="${ not empty headOfficeDetailForm.uploadedDocuments }">
						<table>
							<tbody>
							</tbody>
						</table>
				</c:if>
			</div>	
<div id="licensesDiv" style="display:none;"></div>
<div id="errorMsg" style="color:red;display:none;"></div>
<button id="identifyButton" type="submit" name="identifyButton" class="positive"><spring:theme code="text.identify"/></button>
</form:form>
</div>

</body>
</html>