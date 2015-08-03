<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<h1><spring:theme code="text.account.enterprise.information.update"></spring:theme></h1>
text.account.enterprise.welcome                          = 欢迎您, {0}, 请补全一下与企业相关信息
text.account.enterprise.name                             = 企业名称
text.account.enterprise.register.id                      = 企业注册号
text.account.enterprise.address                          = 企业地址
text.account.enterprise.phone                            = 企业座机号
text.account.enterprise.fax                              = 企业传真
text.account.enterprise.contact                          = 联系人
    <form:form method="post" commandName="enterpriseInformationForm" action="${action}">
<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
<div><h2><spring:theme code="text.account.enterprise.welcome" arguments="${user.firstName},${user.lastName}%>"/></h2></div>
</sec:authorize>
    <formElement:formInputBox idKey="name" labelKey="text.account.enterprise.name" path="name"/>
    <div><label for="name"><spring:theme code="text.account.enterprise.name"/></label><input type="text" id="name" name="name"></div>
    <div><label for="name"><spring:theme code="text.account.enterprise.register.id"/></label><input type="text" id="registerId" name="registerId"></div>
    <div><label for="name"><spring:theme code="text.account.enterprise.address"/></label><input type="text" id="address" name="address"></div>
    <div><label for="name"><spring:theme code="text.account.enterprise.phone"/></label><input type="text" id="phone" name="phone"></div>
    <div><label for="name"><spring:theme code="text.account.enterprise.fax"/></label><input type="text" id="fax" name="fax"></div>
    <div><label for="name"><spring:theme code="text.account.enterprise.contact"/></label><input type="text" id="contact" name="contact"></div>
</form>
</html>


