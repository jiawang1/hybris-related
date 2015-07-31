<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%-- <cms:pageSlot position="Footer" var="feature" element="div" class="footer">
	<cms:component component="${feature}"/>
</cms:pageSlot> --%>
<div class="Footer">
   <div class="Buyers Frame">
    <ul>
     <li class="Buys"><spring:theme code="ango.footer.buyer.sguide"/></li>
     <li><spring:theme code="ango.footer.membership"/></li>
     <li><spring:theme code="ango.footer.buyer.sraiders"/></li>
     <li><spring:theme code="ango.footer.buyer.transactions"/></li>
    </ul>
   <ul>
     <li class="Buys"><spring:theme code="ango.footer.seller.sguide"/></li>
     <li><spring:theme code="ango.footer.membership"/></li>
     <li><spring:theme code="ango.footer.seller.raiders"/></li>
     <li><spring:theme code="ango.footer.seller.transaction.code"/></li>
     <li><spring:theme code="ango.footer.openstore"/></li>
    
    </ul>
    <ul>
     <li class="Buys"><spring:theme code="ango.footer.delivery.notice"/></li>
     <li><spring:theme code="ango.footer.client.processes"/></li>
     <li><spring:theme code="ango.footer.extraction.process"/></li>
     <li><spring:theme code="ango.footer.integratedmachine.delivery"/></li>
     <li><spring:theme code="ango.footer.foreclosuregoods.tradingrules"/></li>
    
    </ul>
    
     <ul>
     <li class="Buys"><spring:theme code="ango.footer.methods.payment"/></li>
     <li><spring:theme code="ango.footer.reservefund"/></li>
     <li><spring:theme code="ango.footer.onlinepayment"/></li>
     <li><spring:theme code="ango.footer.financing"/></li>
     <li><spring:theme code="ango.footer.bank.acceptance.bill"/></li>
      <li><spring:theme code="ango.footer.integral"/></li>
    
    </ul>
    
     <ul>
     <li class="Buys"><spring:theme code="ango.footer.aftersale.service"/></li>
     <li><spring:theme code="ango.footer.customer.service"/></li>
     <li><spring:theme code="ango.footer.invoice"/></li>
     <li><spring:theme code="ango.footer.cancel.order"/></li>
     <li><spring:theme code="ango.footer.refund.instructions"/></li>
     <li><spring:theme code="ango.footer.download.area"/></li>
    
    </ul>
     <ul>
     <li class="phonef"><img src="${contextPath}/_ui/desktop/common/images/phonef.jpg"></li>
    </ul>
    
   </div>
  
  </div>