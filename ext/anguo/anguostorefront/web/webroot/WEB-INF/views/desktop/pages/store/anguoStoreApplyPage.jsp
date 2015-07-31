<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" %>
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
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>

<%@ taglib prefix="compressible" tagdir="/WEB-INF/tags/desktop/template/compressible" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>

<style>
.refinementToggle{
height:20px;
}
</style>
<spring:theme text="Your Shopping Cart" var="title" code="cart.page.title"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<compressible:js/>

<common:globalMessages/>
	<c:url value="/anguo-storemanagerment/select-address-location?cmd=countryselected&url=${formActionURL}" var="encodedUrlCountrySelector" />
	<c:url value="/anguo-storemanagerment/select-address-location?cmd=provinceselected&url=${formActionURL}" var="encodedUrlProvinceSelector" />
	<c:url value="/anguo-storemanagerment/select-address-location?cmd=cityselected&url=${formActionURL}" var="encodedUrlCitySelector" />

<script type="text/javascript">
			$(function(){
				
				$('#storeSaveBtn').on("click", function (){
					$('#CSRFToken').val = ACC.config.CSRFToken;
				});
				
				function attachOnChangeFunctionOntoCountrySelector () {
					$('.myAddEdit2 select[id="address\\.country"]').on("change", function () {
						/* $('.storeApplyForm').attr("action","${encodedUrlProvinceSelector}");
						$('.storeApplyForm').attr("method","POST");
						$('.storeApplyForm').submit(); */
						
						$.ajax({
			                cache: true,
			                type: "GET",
			                url:"${encodedUrlCountrySelector}",
			                data:$('.anguoStoreApplyForm').serialize(),// 你的formid
			                async: false,
			                error: function(request) {
			                    alert("Connection error");
			                },
			                success: function(data) {
			                    /* $("#commonLayout_appcreshi").parent().html(data); */
			                		$('#address\\.citydistrict option:gt(0)').remove();
			                		$('#address\\.city option:gt(0)').remove();
			                		$('#address\\.region option:gt(0)').remove();
			                	$.each(data.regions, function(i, item){
			                		$('#address\\.region').append('<option value=' + item.isocode + '>' + item.name + '</option>');
			                	}); 
			                }
			            });
					})
				}
				attachOnChangeFunctionOntoCountrySelector();

				function attachOnChangeFunctionOntoProvinceSelector () {
					$('.myAddEdit2 select[id="address\\.region"]').on("change", function () {
						/* $('.storeApplyForm').attr("action","${encodedUrlProvinceSelector}");
						$('.storeApplyForm').attr("method","POST");
						$('.storeApplyForm').submit(); */
						
						$.ajax({
			                cache: true,
			                type: "GET",
			                url:"${encodedUrlProvinceSelector}",
			                data:$('.anguoStoreApplyForm').serialize(),// 你的formid
			                async: false,
			                error: function(request) {
			                    alert("Connection error");
			                },
			                success: function(data) {
			                    /* $("#commonLayout_appcreshi").parent().html(data); */
			                		$('#address\\.citydistrict option:gt(0)').remove();
			                		$('#address\\.city option:gt(0)').remove();
			                	$.each(data.cities, function(i, item){
			                		$('#address\\.city').append('<option value=' + item.code + '>' + item.name + '</option>');
			                	}); 
			                }
			            });
					})
				}
				attachOnChangeFunctionOntoProvinceSelector();

				function attachOnChangeFunctionOntoCitySelector () {
					$('.myAddEdit2 select[id="address\\.city"]').on("change", function () {
						/* $('.storeApplyForm').attr("action","${encodedUrlCitySelector}");
						$('.storeApplyForm').attr("method","POST");
						$('.storeApplyForm').submit(); */
						
						$.ajax({
			                cache: true,
			                type: "GET",
			                url:"${encodedUrlCitySelector}",
			                data:$('.anguoStoreApplyForm').serialize(),// 你的formid
			                async: false,
			                error: function(request) {
			                    alert("Connection error");
			                },
			                success: function(data) {
			                    /* $("#commonLayout_appcreshi").parent().html(data); */
			                  $('#address\\.citydistrict option:gt(0)').remove();
			                 /*  $('#address\\.citydistrict option').remove(); */
			                	
			                  $.each(data.cityDistricts, function(i, item){
			                		$('#address\\.citydistrict').append('<option value=' + item.code + '>' + item.name + '</option>');
			                	}); 
			                }
			            });
					})
				}
				attachOnChangeFunctionOntoCitySelector();
			});
		</script>
</head>
<body>
	
<div id="dealerreg">
  <div class="facetNavigation">
   <div class="facet">
	<div class="facetHead">
	   <a href="#" class="refinementToggle"></a>	
	   	<%-- <div class="headline"><spring:theme code="register.new.customer" /></div>	      --%>
	</div> 
	     	     
    <div class="facetValues">   
	 <div class="allFacetValues">	    
	 
	 <div id="anguoStoreDiv" class="myAddEdit2"> 
	  <form:form method="POST" class="anguoStoreApplyForm" commandName="anguoStoreApplyForm" action="saveStore" enctype="multipart/form-data">
	   <input type="hidden" id="CSRFToken"/>
	   <input type="hidden" id="approveStatus" value="${anguoStoreApplyForm.approveStatus}"/> 
	   <input type="hidden" id="fieldsNeedApprove" value="${anguoStoreApplyForm.fieldsNeedApprove}"/> 
	  <%-- <div class="required right"><spring:theme code="form.required"/></div>
	     <div class="description"><spring:theme code="register.description"/></div>  	 --%>
	     <table>
	     <tr>
        	 <th><span id="anguoStoreNameStar" style="display: none">*</span><spring:theme code="text.store.storeApply.name" text=""/></th>
             <td>  	    		    
		       <form:input id="anguoStoreName" labelKey="" path="name" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.name.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		   </tr>
	     <tr>
        	 <th><span id="anguoStoreLogoStar" style="display: none">*</span><spring:theme code="text.store.storeApply.logo" text="Qualification.Name"/></th>
             <td>
             	<img alt="" src="${anguoStoreApplyForm.logoUrl}">  	    		    
		       <input id="anguoStoreLogo" type="file" name="logo" size="15" id="logo" maxlength="100" />
		       <%-- <span><spring:theme code="text.store.storeApply.logo.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		   </tr>
		   
		   <tr>
        	 <th><span id="anguoStoreQQStar" style="display:none">*</span><spring:theme code="text.store.storeApply.qq" text="Qualification.Name"/></th>
             <td>  	    		    
		       <form:input id="anguoStoreQQ" labelKey="" path="qq" cssClass="text" mandatory="true" />		
		      <%--  <span><spring:theme code="text.store.storeApply.qq.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		   </tr>
		   
		   <tr>
        	 <th><span id="anguoStoreFaxStar" style="display:none">*</span><spring:theme code="text.store.storeApply.fax" text="Qualification.Name"/></th>
             <td>  	    		    
		       <form:input id="anguoStoreFax" labelKey="" path="fax" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.fax.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		   </tr>
		   
		   <tr>
        	 <th><span id="anguoStoreTeleStar" style="display:none">*</span><spring:theme code="text.store.storeApply.telephone" text="Qualification.Name"/></th>
             <td>  	    		    
		       <form:input id="anguoStoreTele" labelKey="" path="telephone" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.telephone.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		   </tr>
		   
		   <tr>
        	<th><span id="addressStar" class="account_address_span" style="display:none">*</span><spring:theme code="text.store.storeApply.region" text="ZH VALUE TO BE TRANSLATED"/></th>
            <td>
            <form:select id="address.country" labelKey="" path="countryIso" mandatory="true" >
				<option value=""  ${empty addressForm.countryIso ? 'selected="selected"' : ''}><spring:theme code='text.checkout.address.select' text="[Select something ZH TO BE TRANSLATED]" /></option>
					<form:options items="${countries}" itemValue="${useShortCountryIso ? 'isocodeShort' : 'isocode'}" itemLabel="${not empty itemLabel ? itemLabel :'name'}"/>
				</form:select>
				<form:select id="address.region" labelKey="" path="regionIso" mandatory="true" >
				<option value=""  ${empty addressForm.regionIso ? 'selected="selected"' : ''}><spring:theme code='text.checkout.address.select' text="[Select something ZH TO BE TRANSLATED]" /></option>
					<form:options items="${regions}" itemValue="${useShortRegionIso ? 'isocodeShort' : 'isocode'}" itemLabel="${not empty itemLabel ? itemLabel :'name'}"/>
				</form:select>
				<form:select id="address.city" path="cityCode">
					<option value=""  ${empty addressForm.cityCode ? 'selected="selected"' : ''}><spring:theme code='text.checkout.address.select' text="[Select something ZH TO BE TRANSLATED]" /></option>
					<form:options items="${cities}" itemValue="code" itemLabel="${not empty itemLabel ? itemLabel :'name'}"/>
				</form:select>
                <form:select id="address.citydistrict" path="cityDistrictCode">
					<option value=""  ${empty addressForm.cityDistrictCode ? 'selected="selected"' : ''}><spring:theme code='text.checkout.address.select' text="[Select something ZH TO BE TRANSLATED]" /></option>
					<form:options items="${cityDistricts}" itemValue="code" itemLabel="${not empty itemLabel ? itemLabel :'name'}"/>
				</form:select> 
                <%-- <span><spring:theme code="cnacc.txt.account.address.form.location.validation.message" text="ZH VALUE TO BE TRANSLATED"/></span> --%>
			</td>
			<td>
			<form:input id="account.storeApply.name" labelKey="" path="street" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.name.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		</tr>
		   
		   <tr>
        	  <th><span></span><spring:theme code="text.store.storeApply.contact" text="Qualification.IdCard"/></th>
        	  <th><span></span><spring:theme code="text.store.storeApply.cname" text="Qualification.IdCard"/></th>
        	  <th><span></span><spring:theme code="text.store.storeApply.cphone" text="Qualification.IdCard"/></th>
		   </tr>
		   
		   <tr>
		   	<td></td>
        	  <td>
			    <form:input id="anguoStoreContactName1" labelKey="" path="contactName1" cssClass="text" mandatory="true" />		
		      <%--  <span><spring:theme code="text.store.storeApply.name.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
        	  <td>
			    <form:input id="anguoStoreContactPhone1" labelKey="" path="contactPhone1" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.name.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		   </tr>
		   <tr>
		   	<td></td>
        	  <td>
			    <form:input id="anguoStoreContactName2" labelKey="" path="contactName2" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.name.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
        	  <td>
			    <form:input id="anguoStoreContactPhone2" labelKey="" path="contactPhone2" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.name.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		   </tr>
		   <tr>
		   	<td></td>
        	  <td>
			    <form:input id="anguoStoreContactName3" labelKey="" path="contactName3" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.name.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
        	  <td>
			    <form:input id="anguoStoreContactPhone3" labelKey="" path="contactPhone3" cssClass="text" mandatory="true" />		
		       <%-- <span><spring:theme code="text.store.storeApply.name.err" text="[ZH VALUE TO BE TRANSLATED]"/></span>    --%>
	         </td>
		   </tr>
		   
		   <tr>
        	  <th><span id="anguoStoreDescStar" style="display:none">*</span><spring:theme code="text.store.storeApply.description" text="Qualification.IdCard"/></th>
              <td colspan="5">
			     <form:textarea id="anguoStoreDesc" labelKey="" path="description" cssClass="textarea" mandatory="true" style="height:100px;width:500px"/>
	             <%--  <span><spring:theme code="text.store.storeApply.idCard.err" text="[ZH VALUE TO BE TRANSLATED]" /></span> --%>
	         </td>
		   </tr>
		   
		   
		   <tr id="anguoStoreRegistertimeTr">
        	  <th><span id="anguoStoreRegistertimeStar" style="display:none">*</span><spring:theme code="text.store.storeApply.registerTime" text="Qualification.IdCard"/></th>
              <td colspan="5">
			     <form:input id="anguoStoreRegistertime" readonly="true" labelKey="" path="registerTime" cssClass="text" mandatory="false" style="width:100%;border:0"/>
	             <%--  <span><spring:theme code="text.store.storeApply.idCard.err" text="[ZH VALUE TO BE TRANSLATED]" /></span> --%>
	         </td>
		   </tr>
		   
		   <tr id="anguoStoreLevelTr">
        	  <th><span id="anguoStoreLevelStar" style="display:none">*</span><spring:theme code="text.store.storeApply.storeLevel" text="Qualification.IdCard"/></th>
              <td colspan="5">
			     <form:input id="anguoStoreLevel" readonly="true" labelKey="" path="storeLevel" cssClass="text" mandatory="false" style="width:100%;border:0"/>
	             <%--  <span><spring:theme code="text.store.storeApply.idCard.err" text="[ZH VALUE TO BE TRANSLATED]" /></span> --%>
	         </td>
		   </tr>
		   
		   <tr id="anguoStoreTemplateTr">
        	  <th><span id="anguoStoreTemplateStar" style="display:none">*</span><spring:theme code="text.store.storeApply.storeTemplate" text="Qualification.IdCard"/></th>
              <td colspan="5">
			     <form:input id="anguoStoreTemplate" readonly="true" labelKey="" path="storeTemplate" cssClass="text" mandatory="false" style="width:100%;border:0"/>
	             <%--  <span><spring:theme code="text.store.storeApply.idCard.err" text="[ZH VALUE TO BE TRANSLATED]" /></span> --%>
	         </td>
		   </tr>		   		   		   
		   
		   <tr id="anguoStorePlatformServiceTr">
        	  <th><span  id="anguoStorePlatformServiceStar" style="display:none">*</span><spring:theme code="text.store.storeApply.anguoPlatformService" text="Qualification.IdCard"/></th>
              <td colspan="5">
			     <form:input id="anguoStorePlatformService" readonly="true" labelKey="" path="anguoPlatformService" cssClass="text" mandatory="false" style="width:100%;border:0"/>
	             <%--  <span><spring:theme code="text.store.storeApply.idCard.err" text="[ZH VALUE TO BE TRANSLATED]" /></span> --%>
	         </td>
		   </tr>			   
	     </table>
	     
	     <div id="saveandresetbtn">
			<ycommerce:testId code="register_Register_button">
				<button id="anguoStoreSaveBtn" type="submit" class="positive saveBtn"><spring:theme code="text.store.apply.savebutton" /></button>
				<button id="anguoStoreResetBtn" type="button" class="positive"><spring:theme code="text.store.apply.resetbutton" /></button>
			</ycommerce:testId>
		</div>   	
	  </form:form>
	  
	  </div>
     </div>
    </div>
   </div>
  </div>
 </div>		
</body>
</html>
