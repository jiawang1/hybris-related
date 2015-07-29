<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<div class="userRegister">
	<div id="divPersonal">
		<div class="headline"><spring:theme code="register.new.account.personal" /></div>
		<div class="required right"><a href="javascript:personalEnterpriseSwitch('divEnterprise', 'divPersonal')"><spring:theme code="register.change.to.enterprise.account"/></a></div>
		<div class="description"><spring:theme code="register.description.personal"/></div>
	</div>
	<div id="divEnterprise" style="display:none">
		<div class="headline"><spring:theme code="register.new.account.enterprise" /></div>
		<div class="required right"><a href="javascript:personalEnterpriseSwitch('divPersonal', 'divEnterprise')"><spring:theme code="register.change.to.personal.account"/></a></div>
		<div class="description"><spring:theme code="register.description.enterprise"/></div>
	</div>
	<form:form method="post" commandName="anguoRegisterForm" action="${action}">
		<div class="form_field-elements js-recaptcha-captchaaddon">
			<!--<formElement:formSelectBox idKey="register.title" labelKey="register.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="form.select.empty" items="${titles}"/>-->
			<!--<formElement:formInputBox idKey="register.firstName" labelKey="register.firstName" path="firstName" inputCSS="text" mandatory="true"/>
			<formElement:formInputBox idKey="register.lastName" labelKey="register.lastName" path="lastName" inputCSS="text" mandatory="true"/>-->
			<formElement:formInputBox idKey="userId" labelKey="register.username" path="userId" inputCSS="text" mandatory="true"/>
			<formElement:formPasswordBox idKey="password" labelKey="register.pwd" path="pwd" inputCSS="text password strength" mandatory="true"/>
			<formElement:formPasswordBox idKey="register.checkPwd" labelKey="register.checkPwd" path="checkPwd" inputCSS="text password" mandatory="true"/>
			<formElement:formInputBox idKey="mobile" labelKey="register.mobile" path="mobileNumber" inputCSS="text" mandatory="true"/>
			<formElement:formInputButtonBox labelKey="register.captcha" idKey="captcha" path="captcha" buttonLabel="register.captcha" buttonId="btCaptcha"/>
			<formElement:formCheckbox idKey="cbIsAgreeTerms" labelKey="register.agree.terms" path="isAgreeTerms"/>
			<input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}"/>
			<input type="hidden" id="accountType" value="${requestScope.recaptchaChallangeAnswered}"/>
			<form:hidden path="userType" id="userType" value="PERSONAL"/>
		</div>
		<div class="form-actions clearfix">
			<ycommerce:testId code="register_Register_button">
				<button id="registerButton" type="submit" class="positive"><spring:theme code='${actionNameKey}'/></button>
			</ycommerce:testId>
		</div>
	</form:form>
</div>
<script type="text/javascript">
	var countdown=60; 
	function settime(obj) {
	     if (countdown == 0) { 
	        obj.removeAttribute("disabled");    
	        obj.innerText=<spring:theme code="register.recaptcha" />;
	        countdown = 60;
	        return;
	    } else { 
	        obj.setAttribute("disabled", true); 
	        obj.innerText='<spring:theme code="register.recaptcha" />'+"(" + countdown + ")"; 
	        countdown--; 
	    } 
	setTimeout(function() { 
	             			settime(obj)
	              		 }
	         ,1000);  
	}
    var cbIsAgreeTerms = document.getElementById("cbIsAgreeTerms");
    var btCaptcha = document.getElementById("btCaptcha");
    btCaptcha.onclick = function(){settime(btCaptcha);}
    cbIsAgreeTerms.onclick = function() {
		$("#registerButton").enable($("#cbIsAgreeTerms").attr("checked")? true :false);			
	};
	if (cbIsAgreeTerms.checked) {
		document.getElementById("registerButton").disabled = false;
	} else {
		document.getElementById("registerButton").disabled = true;		
	}
	function personalEnterpriseSwitch(displayDiv, hiddenDiv)
	{
		$("#"+displayDiv).show();
		$("#"+hiddenDiv).hide();
		$("#userType").val((displayDiv=="divPersonal")? "PERSONAL" : "ENTERPRISE");
	}
	document.getElementById("mobile").onblur=function() {
		var str = $("#mobile").val();
	    if(str==""){
	        alert('<spring:theme code="register.mobile.invalid" />');
	        $("#mobile").focus();
	    }
	    else{
	        var re = /^1\d{10}$/;
	        if (!re.test(str)) {
	        {
		        alert('<spring:theme code="register.mobile.invalid" />');
		        $("#mobile").focus();

	        }
	    }
	}	
  }
</script>



