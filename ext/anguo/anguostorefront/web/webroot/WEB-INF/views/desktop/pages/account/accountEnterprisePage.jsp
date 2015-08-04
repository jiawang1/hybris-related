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
<head>
<meta charset="utf-8"/>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.11.3.min.js"></script>
<title><spring:theme code="text.account.enterprise.information.update"/></title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
    <link rel="stylesheet" href="/anguostorefront/_ui/desktop/common/css/resetUser.css">
    <link rel="stylesheet" href="/anguostorefront/_ui/desktop/common/css/commonUser.css">
    <link rel="stylesheet" href="/anguostorefront/_ui/desktop/common/css/login.css">
    <link rel="stylesheet" href="/anguostorefront/_ui/desktop/common/css/register.css"/>
</head>
<body>
<div id="register"> 

       <header class="wrap clearfix">
            <div class="logoWrap fl">
                <a class="logo"></a>
                <p><spring:theme code="ango.top.search.largest.platform"/></p>
            </div>
            <h1 class="fl"><spring:theme code="text.account.enterprise.register"/></h1>
            <div class="tel fr"></div>
        </header>

<div class="wrap contenta"> 
    <div class="imprvBox">
        <div class="tipInfo">
            <span><spring:theme code="text.account.enterprise.welcome" arguments="${user.firstName},${user.lastName}%>"/></span>
        </div>
        <form:form method="post" commandName="enterpriseInformationForm" action="${action}" cssClass="firmImprvForm">
        <formElement:anguoFormInputBox idKey="name" labelKey="text.account.enterprise.name" path="name" inputCSS="text" mandatory="true"/>
        <formElement:anguoFormInputBox idKey="registerId" labelKey="text.account.enterprise.register.id" path="registerId" inputCSS="text" mandatory="true"
            desc="text.account.enterprise.register.desc"/>
    <formElement:anguoFormInputBox idKey="address" labelKey="text.account.enterprise.address" path="address" inputCSS="text"/>
    <formElement:anguoFormInputBox idKey="phone" labelKey="text.account.enterprise.phone" path="phone" inputCSS="text"/>
    <formElement:anguoFormInputBox idKey="fax" labelKey="text.account.enterprise.fax" path="fax" inputCSS="text"/>
    <formElement:anguoFormContacts path="contacts"/>
        <div class="btn">
           <button class="authenBtn" type="submit"><spring:theme code="text.account.enterprise.auth"></spring:theme></button>
        </div>
   </form:form>
    </div>  
</div>

<footer class="wrap clearfix">
            <ul class="fl clearfix">
                <li class="fl">
                    <b>买家指南</b>
                    <a href="">会员须知</a>
                    <a href="">买家攻略</a>
                    <a href="">买家交易规则</a>
                </li>
                <li class="fl">
                    <b>卖家指南</b>
                    <a href="">会员须知</a>
                    <a href="">卖家攻略</a>
                    <a href="">卖家交易规则</a>
                    <a href="">开通店铺</a>
                </li>
                <li class="fl">
                    <b>提货须知</b>
                    <a href="">委托流程</a>
                    <a href="">自提流程</a>
                    <a href="">自助一体机提货</a>
                    <a href="">赎货交易规则</a>
                </li>
            </ul>
            <div class="tel fr"></div>
        </footer>
</div>

</body>


</html>

