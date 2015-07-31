<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


<c:url value="/search/" var="searchUrl"/>
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl"/>


<div class="siteSearch">
	<form name="search_form" method="get" action="${searchUrl}">
		<%-- <div class="control-group left">
			<spring:theme code="text.search" var="searchText"/>
			<label class="control-label skip" for="search">${searchText}</label>
			<div class="controls">
				<spring:theme code="search.placeholder" var="searchPlaceholder"/>
				<ycommerce:testId code="header_search_input">
			         <input id="search" class="siteSearchInput left" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
			     </ycommerce:testId>
			     <ycommerce:testId code="header_search_button">
			         <button class="siteSearchSubmit" type="submit"/><i></i><spring:theme code='ango.top.search.button'/></button>
			     </ycommerce:testId>
			</div>
		</div> --%>
            <div class="tabNav">
                <a class="tab on" href="javascript:void(0);"><spring:theme code="ango.top.search.synthesize"/>êà</a>
                <%-- <a class="tab" href="javascript:void(0);"><spring:theme code="ango.top.search.price"/></a>
                <a class="tab" href="javascript:void(0);"><spring:theme code="ango.top.search.supply.demand"/>Ç</a>
                <a class="tab" href="javascript:void(0);"><spring:theme code="ango.top.search.quotations"/></a>
                <a class="more"><spring:theme code="ango.top.search.more"/>ö<i></i></a> --%>
            </div>
            <div class="tabContainer">
	            
                <div class="siteSearch tabCont on">
                    <%-- <form name="search_form" method="get" action="${searchUrl}"> --%>
                        <div class="control-group left">
                            <spring:theme code="text.search" var="searchText"/>
                            <label class="control-label skip" for="search"></label>
                            <div class="controls">
                                <spring:theme code="search.placeholder" var="searchPlaceholder"/>
                                
                               <!--  <ycommerce:testId code="header_search_input"> -->
                                    <input id="search" class="siteSearchInput left" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
                               <!--   </ycommerce:testId> -->
                              <!--   <ycommerce:testId code="header_search_button"> -->
                                    <button class="siteSearchSubmit" type="submit"/><i></i><spring:theme code='ango.top.search.button'/></button>
                              <!--   </ycommerce:testId> -->
                                
                            </div>
                        </div>
                    <%-- </form> --%>
                </div>
                <%-- 
                <div class="siteSearch tabCont none">
                    <form name="search_form" method="get" action="${searchUrl}">
                        <div class="control-group left">
                            <spring:theme code="text.search" var="searchText"/>
                            <label class="control-label skip" for="search"></label>
                            <div class="controls">
                                <spring:theme code="search.placeholder" var="searchPlaceholder"/>
                                <ycommerce:testId code="header_search_input">
                                    <input id="search" class="siteSearchInput left" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
                                </ycommerce:testId>
                                <ycommerce:testId code="header_search_button">
                                    <button class="siteSearchSubmit" type="submit"/><i></i><spring:theme code='ango.top.search.button'/></button>
                                </ycommerce:testId>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="siteSearch tabCont none">
                    <form name="search_form" method="get" action="${searchUrl}">
                        <div class="control-group left">
                            <spring:theme code="text.search" var="searchText"/>
                            <label class="control-label skip" for="search"></label>
                            <div class="controls">
                                <spring:theme code="search.placeholder" var="searchPlaceholder"/>
                                <ycommerce:testId code="header_search_input">
                                    <input id="search" class="siteSearchInput left" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
                                </ycommerce:testId>
                                <ycommerce:testId code="header_search_button">
                                    <button class="siteSearchSubmit" type="submit"/><i></i><spring:theme code='ango.top.search.button'/></button>
                                </ycommerce:testId>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="siteSearch tabCont none">
                    <form name="search_form" method="get" action="${searchUrl}">
                        <div class="control-group left">
                            <spring:theme code="text.search" var="searchText"/>
                            <label class="control-label skip" for="search"></label>
                            <div class="controls">
                                <spring:theme code="search.placeholder" var="searchPlaceholder"/>
                                <ycommerce:testId code="header_search_input">
                                    <input id="search" class="siteSearchInput left" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
                                </ycommerce:testId>
                                <ycommerce:testId code="header_search_button">
                                    <button class="siteSearchSubmit" type="submit"/><i></i><spring:theme code='ango.top.search.button'/></button>
                                </ycommerce:testId>
                            </div>
                        </div>
                    </form>
                </div> --%>
        </div>
	</form>
	<%-- <div class="advanced"><a href="<c:url value="/search/advanced"/>"><spring:theme code="search.advanced" /></a></div> --%>
</div>

