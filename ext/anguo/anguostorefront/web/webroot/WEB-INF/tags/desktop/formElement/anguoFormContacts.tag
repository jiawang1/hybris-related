<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="idKey" required="false" type="java.lang.String" %>
<%@ attribute name="labelKey" required="false" type="java.lang.String" %>
<%@ attribute name="path" required="true" type="java.lang.String" %>
<%@ attribute name="desc" required="false" type="java.lang.String" %>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean" %>
<%@ attribute name="labelCSS" required="false" type="java.lang.String" %>
<%@ attribute name="inputCSS" required="false" type="java.lang.String" %>
<%@ attribute name="tabindex" required="false" rtexprvalue="true" %>
<%@ attribute name="autocomplete" required="false" type="java.lang.String" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<div class="contacts" style="height:120px;">
   <table>
      <tr><td>
         <label style="margin-left:0px;"><spring:theme code="text.account.enterprise.contact"/></label>
          </td><td id="tdContacts">
            <c:forEach items="${enterpriseInformationForm.contacts}" var="contact" varStatus="loop">
               <template:anguoErrorSpanField path="contacts[${loop.index}].contactInfo">         
              <div class="contactInfo" style="margin-left:8px;">
                <input type="hidden" name="contacts[${loop.index}].id">
                <div><label><spring:theme code="text.account.enterprise.contact.name"/></label><input type="text" name="contacts[${loop.index}].name" value="${contact.name}"></div>
                <div><label><spring:theme code="text.account.enterprise.contact.position"/></label><input type="text" name="contacts[${loop.index}].position" value="${contact.position }"></div>
                <div><label><spring:theme code="text.account.enterprise.contact.phone"/></label><input type="text" name="contacts[${loop.index}].contactInfo" value="${contact.contactInfo }"></div>
                <div><a name="deleteEle" style="cursor:pointer;"><spring:theme code="text.delete"/></a></div>
              </template:anguoErrorSpanField>
              </div>
            </c:forEach>
            </td></tr>
            <tr><td>&nbsp;</td><td><div><a name="addEle" style="cursor:pointer;"><spring:theme code="text.add"/></a></div></td></tr>
    </table>     
</div>
<script type="text/javascript">
$(function(){
function updateDeleteButtonValue() {
    var index = $("#tdContacts").children().length;
    if (index == 1) {
    	$("#tdContacts").first().find("a[name='deleteEle']").text('<spring:theme code="text.clear"/>')	
    } else {
        $("#tdContacts").first().find("a[name='deleteEle']").text('<spring:theme code="text.delete"/>')      	
    }
}
$("a[name='addEle']").on("click", function(){
	addContact();
});	
$("a[name='deleteEle']").click(function(){
	deleteContact(this);
});
function addContact() {
	var index = $("#tdContacts").children().length;
	if (index >= 3) {
		alert('<spring:theme code="text.account.enterprise.contact.cannot.exceed.max"/>');
		return;
	}
	var el = '<div><div class="contactInfo" style="margin-left:8px;">';
	el += '<div style="margin-right:19px;"><label><spring:theme code="text.account.enterprise.contact.name"/></label><input type="text" name="contacts[' + index +'].name" value=""></div>';
    el += '<div style="margin-right:19px;"><label><spring:theme code="text.account.enterprise.contact.position"/></label><input type="text" name="contacts[' + index +'].position" value=""></div>';
    el += '<div><label><spring:theme code="text.account.enterprise.contact.phone"/></label><input type="text" name="contacts[' + index + '].contactInfo" value=""></div>';
    el += '<div><a name="deleteEle" style="cursor:pointer;"><spring:theme code="text.delete"/></a></div>';
    el += '</div></div>';
	$("#tdContacts").append(el);
	$("#tdContacts").last().find("a[name='deleteEle']").click(function(){
		deleteContact(this);
	});
	updateDeleteButtonValue();
}

function deleteContact(ele) {
    var delEle = $(ele).parent().parent().parent();
    if (delEle.parent().children().length > 1) {   	   
    	   var nextEle = delEle.next();
    	   delEle.remove();
    	   while(nextEle.length > 0) {
    		   updateContactIndex(nextEle);
    		   nextEle = nextEle.next();
    	   }
    } else {
    	delEle.find("input").each(function(i, e){
    	    $(e).val("");
    	});
    }
    updateDeleteButtonValue();
	
}
function updateContactIndex(el) {
	el.find("input").each(function(i,e){
		var origalName = $(e).attr("name");
		var index = parseInt(origalName.match(/\d+/g)) - 1;
		$(e).attr("name", origalName.replace(/\d+/g,index));
	});
}
if ($("#tdContacts").children().length == 0) {
    addContact();
}
updateDeleteButtonValue();
})
</script>
