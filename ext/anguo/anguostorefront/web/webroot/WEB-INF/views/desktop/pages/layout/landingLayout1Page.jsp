<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<template:page pageTitle="${pageTitle}">
    <div id="globalMessages">
		<common:globalMessages/>
	</div> 
	<div id="Wrap">
  <div class="Mebbanner Frame">
   <div class="Fnav">
   <div class="all-goods">
        <div class="item btnone">
            <cms:pageSlot position="SectionLeftNav1" var="feature" element="div" class="product">
		      <cms:component component="${feature}"/>
	        </cms:pageSlot>
          <div class="product-wrap posone"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot1" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv1" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav2" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap postwo"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot2" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv2" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav3" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posthree"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot3" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv3" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav4" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posfour"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot4" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv4" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav5" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posfive"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot5" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv5" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav6" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap possix"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot6" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv6" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav7" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posseven"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot7" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv7" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav8" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap poseight"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot8" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv8" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav9" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posnine"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot9" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv9" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
              
            </div>
          </div>
        </div>
        <div class="item">
          <cms:pageSlot position="SectionLeftNav10" var="feature" element="div" class="product">
		    <cms:component component="${feature}"/>
	      </cms:pageSlot>
          <div class="product-wrap posten"> 
            <div class="cf">
              <div class="fl wd252 pr20">
              <h2>
              <cms:pageSlot position="SectionLeftNavDivRoot10" var="feature" element="div">
		        <cms:component component="${feature}"/>
	          </cms:pageSlot>
	          </h2>
              <cms:pageSlot position="SectionLeftNavDiv10" var="feature" element="ul">
		        <cms:component component="${feature}" element="li"/>
	          </cms:pageSlot>
              </div>
            </div>
          </div>
        </div>
     </div>
   </div>
<div class="RList">
     <div class="RLeft">
      <div class="Rbanner">
      	<cms:pageSlot position="SectionG" var="feature">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	  </div>
      <div class="Grouping">
       <ul>
        <li class="now">
        <div id="wrapper">
         <div class="main_img">
		 <img src="${commonResourcePath}/images/anguo.jpg">
		 <div class="show">
			<span class="imgArea">
				<a title="安国市场" href="#">安国市场</a>
			</span>
		 </div>
	</div>
</div>
        </li>
        <li>
        <div id="wrapper">
         <div class="main_img">
		 <img src="${commonResourcePath}/images/bozhou.jpg">
		 <div class="show">
			<span class="imgArea">
				<a title="亳州市场" href="#">亳州市场</a>
			</span>
		 </div>
	</div>
</div>
        </li>
        <li>
        <div id="wrapper">
         <div class="main_img">
		 <img src="${commonResourcePath}/images/hehua.jpg">
		 <div class="show">
			<span class="imgArea">
				<a title="荷花池市场" href="#">荷花池市</a>
			</span>
		 </div>
	</div>
</div>
        </li>
        <li>
        <div id="wrapper">
         <div class="main_img">
		 <img src="${commonResourcePath}/images/yulin.jpg">
		 <div class="show">
			<span class="imgArea">
				<a title="玉林市场" href="#">玉林市场</a>
			</span>
		 </div>
	</div>
</div>
        </li>
       </ul>
      </div>
     </div>
     <div class="Rright">
      <div class="Rtop">
      <%--  <span class="Rnow"><a href="#"><spring:theme code="ango.homepage.register"/></a></span> --%>
       <span><a href="/auguostorefront/login"><spring:theme code="ango.homepage.login"/></a></span>
       <span class="Release"><a href="#"><spring:theme code="ango.homepage.medicine.release"/></a></span>
      </div>
      <div class="RNew">
      <figure class="tabBlock">
  <ul class="tabBlock-tabs">
    <li class="tabBlock-tab is-active"><spring:theme code="ango.homepage.notice"/></li>
    <li class="tabBlock-tab"><spring:theme code="ango.homepage.rule"/></li>
    <li class="tabBlock-tab"><spring:theme code="ango.homepage.letters"/></li>
  </ul>
  <div class="tabBlock-content">
    <div class="tabBlock-pane">
	      <cms:pageSlot position="SectionA" var="feature" element="ul" limit="8">
	          <cms:component component="${feature}" element="li"/>
		  </cms:pageSlot>
    </div>
    <div class="tabBlock-pane">
    	<cms:pageSlot position="SectionB" var="feature" element="ul" limit="8">
	    	<cms:component component="${feature}" element="li"/>
		</cms:pageSlot>
    </div>
    <div class="tabBlock-pane">
       <cms:pageSlot position="SectionC" var="feature" element="ul" limit="8">
       		<cms:component component="${feature}" element="li" />
	   </cms:pageSlot>
    </div>
  </div>
</figure>
      </div>
      <div class="RService">
       <div class="Rstitle"><spring:theme code="ango.homepage.market.service"/></div>
       <div class="Rslist">
         <ul>
          <li><a href="#"><div class="small_image bg_position_left"></div></a>
          <p><a href="#"><spring:theme code="ango.homepage.quality.check"/></a></p></li>
          <li><a href="#"><div class="small_image bg_position_two"></div></a>
          <p><a href="#"><spring:theme code="ango.homepage.flow.back"/></a></p></li>
          <li><a href="#"><div class="small_image bg_position_three"></div></a>
          <p><a href="#"><spring:theme code="ango.homepage.new.offer"/></a></p></li>
          <li class="Four"><a href="#"><div class="small_image bg_position_four"></div></a>
          <p><a href="#"><spring:theme code="ango.homepage.market.data"/></a></p></li>
         </ul>
       </div>
      </div>
     </div>
    </div>
   </div>
  <div class="Advert Frame">
   <div class="Abanner"><img src="${commonResourcePath}/images/advert.jpg"></div></div>
  <div class="Main Frame">
   <div class="Mleft">
     <div class="OneFloor">
      <div class="OneTitle">
       <div class="OneTit"><img src="${commonResourcePath}/images/1F.jpg"><h1><a href="#"><spring:theme code="ango.homepage.honest.shop"/></a></h1></div>
       <div class="Onenav">
        <ul>
	        <li><spring:theme code="ango.homepage.producer.information"/></li>
	        <li><spring:theme code="ango.homepage.variety.analyse"/></li>
	        <li><spring:theme code="ango.homepage.data.testing"/></li>
	        <li><spring:theme code="ango.homepage.variety.moves"/></li>
        </ul>
       </div>
       <div class="OneMore"><a href="#">更多></a></div>
      </div>
      <div class="Fleft">
      <div class="OneFhover">
       <ul>
       <li><div class="Hot_List"><span class="Orange">1</span><p>德音中药材批发店</p></div></li>
       <li><div class="Hot_List"><span class="Orange">2</span><p>包子药材批发</p></div></li>
       <li><div class="Hot_List"><span class="Orange">3</span><p>云南茯苓</p></div></li>
       <li><div class="Hot_List"><span class="Grey">4</span><p>诚心堂药材批发</p></div></li>
       <li><div class="Hot_List"><span class="Grey">5</span><p>天龙中药行</p></div></li>
       </ul>
       <div class="Fmore"><a href="#">More+</a></div>
      </div>
       <div class="Flogo"><img src="${commonResourcePath}/images/Flogo1.jpg"><img style=" border:none;" src="${commonResourcePath}/images/Flogo1.jpg"></div>
      </div>
      <div class="Fright">
       <div id="zzsc">
	    <ul>
    	<li style="margin-left:0px;">
    	    <cms:pageSlot position="SectionImg1" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText1" var="Text">
				<cms:component component="${Text}" element="div" class="text"/>
			</cms:pageSlot>
        </li>
        <li>
    	    <cms:pageSlot position="SectionImg2" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText2" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>      
        </li>
        <li>
    	    <cms:pageSlot position="SectionImg3" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText3" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>        
        </li>
        <li style="margin-left:0px;">
    	    <cms:pageSlot position="SectionImg4" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText4" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>      
        </li>
        <li>
    	    <cms:pageSlot position="SectionImg5" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText5" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>      
        </li>
        <li>
    	    <cms:pageSlot position="SectionImg6" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText6" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>      
        </li>
        </ul>
        </div>
      </div>
     </div>
     <div class="TwoFloor">
      <div class="TwoTitle">
       <div class="TwoTit"><img src="${commonResourcePath}/images/2F.jpg"><h1><a href="#"><spring:theme code="ango.homepage.producer.supply"/></a></h1></div>
       <div class="Twonav">
        <ul>
	        <li><spring:theme code="ango.homepage.producer.information"/></li>
	        <li><spring:theme code="ango.homepage.variety.analyse"/></li>
	        <li><spring:theme code="ango.homepage.data.testing"/></li>
	        <li><spring:theme code="ango.homepage.variety.moves"/></li>
        </ul>
       </div>
       <div class="TwoMore"><a href="#">更多></a></div>
      </div>
      <div class="TwoFleft">
      <div class="TwoFhover">
       <ul>
        <li>枸杞子</li>
        <li>枸杞子</li>
        <li>枸杞子</li>
        <li>枸杞子</li>
        <li>枸杞子</li>
        <li>枸杞子</li>
        <li>枸杞子</li>
        <li>枸杞子</li>
        <li>枸杞子</li>
        <li>枸杞子</li>
       </ul>
       <div class="TwoFmore"><a href="#">More+</a></div>
     
      </div>
       <div class="TwoFlogo"><img src="${commonResourcePath}/images/Flogo1.jpg"><img style=" border:none;" src="${commonResourcePath}/images/Flogo1.jpg"></div>
      </div>
      <div class="TwoFright">
       <div id="zzFF">
	    <ul>
    	<li style="margin-left:0px;">
        	<cms:pageSlot position="SectionImg1" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText1" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>  
        </li>
        <li>
        	<cms:pageSlot position="SectionImg2" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText2" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>  
        </li>
        <li>
        	<cms:pageSlot position="SectionImg3" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText3" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>     
        </li>
        <li style="margin-left:0px;">
        	<cms:pageSlot position="SectionImg4" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText4" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>  
        </li>
        <li>
        	<cms:pageSlot position="SectionImg5" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText5" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>  
        </li>
        <li>
        	<cms:pageSlot position="SectionImg6" var="Img" element="div">
				<cms:component component="${Img}"/>
			</cms:pageSlot>
            <cms:pageSlot position="SectionText6" var="Text" element="div" class="text">
				<cms:component component="${Text}"/>
			</cms:pageSlot>  
        </li>
        </ul>
        </div>
      </div>
     </div>
     <div class="ThreeFloor">
     <div class="ThreeTitle">
       <div class="ThreeTit"><img src="${commonResourcePath}/images/3F.jpg"><h1><a href="#"><spring:theme code="ango.homepage.market.opportunity"/></a></h1></div>
       <div class="Threenav">
        <ul><li></li><li></li><li></li><li></li></ul>
       </div>
       <div class="ThreeMore"><a href="#">更多></a></div>
      </div>
     <div class="Meeting">
      <div class="Meetit">
        <h2><a href="#"><spring:theme code="ango.homepage.convention"/></a></h2></div>
      <div class="Meetmore">
      	<cms:pageSlot position="SectionN" var="feature">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
      </div>
      <div class="Meetnew">
       	<cms:pageSlot position="SectionD" var="feature" element="ul"  limit="5">
	    	<cms:component component="${feature}" element="li"/>
		</cms:pageSlot>
      </div>
     </div>
     <div class="Meeting">
      <div class="Meetit">
        <h2><a href="#"><spring:theme code="ango.homepage.company"/></a></h2></div>
      <div class="Meetmore">
      	<cms:pageSlot position="SectionO" var="feature">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
      </div>
      <div class="Meetnew">
      	<cms:pageSlot position="SectionE" var="feature" element="ul"  limit="5">
	    	<cms:component component="${feature}" element="li"/>
		</cms:pageSlot>
      </div>
     </div>
     <div class="Meeting">
      <div class="Meetit">
        <h2><a href="#"><spring:theme code="ango.homepage.bidding"/></a></h2></div>
      <div class="Meetmore">
      	<cms:pageSlot position="SectionP" var="feature">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	  </div>
      <div class="Meetnew">
      	<cms:pageSlot position="SectionF" var="feature" element="ul"  limit="5">
	    	<cms:component component="${feature}" element="li"/>
		</cms:pageSlot>
      </div>
     </div>
     <div class="Newpeople">
      <div class="Newpeotit">
        <h2><a href="#"><spring:theme code="ango.homepage.newspeople"/></a></h2></div>
      <div class="Newpeopic">
       <ul>
        <li class="Newnow">
        <div id="znews">
	    <ul>
    	<li>
        		<cms:pageSlot position="SectionH" var="feature" element="div">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
			
        	<div class="text">
	           	<cms:pageSlot position="SectionK" var="feature" element="div">
		    		<cms:component component="${feature}"/>
				</cms:pageSlot>
			</div>    
        </li>
        </ul>
        </div></li>
        <li><div id="znews">
	    <ul>
    	<li>
        	
        		<cms:pageSlot position="SectionI" var="feature" element="div">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
        	
        	<div class="text">
            	<cms:pageSlot position="SectionL" var="feature" element="div">
		    		<cms:component component="${feature}"/>
				</cms:pageSlot>
            </div>    
        </li>
        </ul>
        </div></li>
        <li><div id="znews">
	    <ul>
    	<li>
        
        		<cms:pageSlot position="SectionJ" var="feature" element="div">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
      
        	<div class="text">
           		<cms:pageSlot position="SectionM" var="feature" element="div">
		    		<cms:component component="${feature}"/>
				</cms:pageSlot>
			</div>    
        </li>
        </ul>
        </div></li>
       </ul>
      </div>
     </div>
     </div>
   </div>
   <div class="Mright">
    <div class="Business">
     <div class="Bustit"><h2><a href="#"><spring:theme code="ango.homepage.deal.dynamic"/></a></h2></div>
     <div class="Busmore"><a href="#">更多></a></div>
     <div class="Bustitle">
      <ul>
       <li><span class="titles">包子药材批发 卖出 绞股蓝</span><span class="Price">成交总额：<span class="Red">￥1,950.00 </span>元</span></li>
       <li><span class="titles">包子药材批发 卖出 绞股蓝</span><span class="Price">成交总额：<span class="Red">￥1,950.00 </span>元</span></li>
       <li><span class="titles">包子药材批发 卖出 绞股蓝</span><span class="Price">成交总额：<span class="Red">￥1,950.00 </span>元</span></li>
       <li><span class="titles">包子药材批发 卖出 绞股蓝</span><span class="Price">成交总额：<span class="Red">￥1,950.00 </span>元</span></li>
       <li class="Last"><span class="titles">包子药材批发 卖出 绞股蓝</span><span class="Price">成交总额：<span class="Red">￥1,950.00 </span>元</span></li>
      </ul>
     </div>
    </div>
    <div class="Focus"><img src="${commonResourcePath}/images/Rjiao.jpg"></div>
    <div class="Sale">
     <div class="Satit">
       <h2><a href="#"><spring:theme code="ango.homepage.new.instorage.medicine"/></a></h2></div>
     <div class="Stits">
     <ul>
      <li><span class="Spic"><img src="${commonResourcePath}/images/Salepic/Spic1.jpg"></span><span class="Stitle">三七 水洗全干50头<br/>产地：云南文山<br/>规格：统货<br/>交货地点：亳州<br/><p style="color:#F00;">参考单价：￥370元</p></span></li>
      <li><span class="Spic"><img src="${commonResourcePath}/images/Salepic/Spic1.jpg"></span><span class="Stitle">三七 水洗全干50头<br/>产地：云南文山<br/>规格：统货<br/>交货地点：亳州<br/><p style="color:#F00;">参考单价：￥370元</p></span></li>
      <li><span class="Spic"><img src="${commonResourcePath}/images/Salepic/Spic1.jpg"></span><span class="Stitle">三七 水洗全干50头<br/>产地：云南文山<br/>规格：统货<br/>交货地点：亳州<br/><p style="color:#F00;">参考单价：￥370元</p></span></li>
      <li><span class="Spic"><img src="${commonResourcePath}/images/Salepic/Spic1.jpg"></span><span class="Stitle">三七 水洗全干50头<br/>产地：云南文山<br/>规格：统货<br/>交货地点：亳州<br/><p style="color:#F00;">参考单价：￥370元</p></span></li>
     </ul>
    </div>
    </div>
   </div>
   <div class="Advertising Frame">
    <div class="Adbanner"><img src="${commonResourcePath}/images/Adbanner.jpg"></div>
    </div>
  </div>   
</div>
</template:page>