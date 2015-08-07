<html>
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="compressible"
	tagdir="/WEB-INF/tags/desktop/template/compressible"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>
<link rel="stylesheet"
	href="${commonResourcePath}/js/themes/default/style.min.css" />
<link rel="stylesheet"
	href="${commonResourcePath}/css/jquery.dataTables.css" />
<link rel="stylesheet"
	href="${commonResourcePath}/css/dataTables.editor.min.css" />
<link rel="stylesheet"
	href="${commonResourcePath}/css/dataTables.tableTools.css" />
<link rel="stylesheet" href="${commonResourcePath}/css/datepicker.css" />

<compressible:js />
<script type="text/javascript"
	src="${commonResourcePath}/js/acc.publishnewproduct.js"></script>
<script type="text/javascript"
	src="${commonResourcePath}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${commonResourcePath}/js/jquery.validate.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${commonResourcePath}/js/messages_zh.js"></script>
<script type="text/javascript"
	src="${commonResourcePath}/js/datepicker.js"></script>

<style>
.categoryTree {
	float: left;
	margin: 10px;
	height: 280px;
	width: 275px;
	border: 1px solid #000000;
	background: #ffffff;
	overflow: auto
}
</style>
</head>
<body>
	<div id="categorySelection">
		<p>选择商品目录</p>
		<div style="margin: 10px">
			<p>
				类目搜索: <input type="text" id="keywordInput" />
			</p>
			<input type="button" id="catSearchBtn" value="快速找到类目"
				style="display: none" />
		</div>
		<div id="categoryTreeDiv"
			style="display: block; background-color: #f5fffa; height: 300px; width: 600px">
			<div id="categoryTreeDivlv2" class="categoryTree">
				<c:forEach items="${categoryRoots }" var="categoryRoot">
					<li catCode="${categoryRoot.id}" class="categorySelectorlv2"
						style="list-style: none; margin: 10px">${categoryRoot.text }</li>
				</c:forEach>
			</div>
			<div id="categoryTreeDivlv3" class="categoryTree"></div>
		</div>
		<div id="searchCatResDiv" style="display: none">
			<b>搜索到的结果：</b> <input type="button" id="backToCatTreeBtn"
				value="关闭，返回类目" />
			<div
				style="background: #f5fffa; margin-top: 10px; height: 300px; width: 600px; overflow: hidden">
				<div id="resultDiv"
					style="margin: 10px; height: 280px; width: 570px; border: 1px solid #000000; background: #ffffff; overflow: auto">
				</div>
			</div>
		</div>
		<div id="catSelectionResultDiv" style="margin: 10px; height: 20px">您选择的是：</div>
	</div>
	<hr noshade color="#aaa">
	<div>
		<p>填写产品信息</p>
		<table id="productInfo">
			<tr>
				<td><span style="color: red">*</span> 商品名称: <input type="text"
					id="productNameInput" /></td>
			</tr>
			<tr>
				<td><span style="color: red">*</span> 规格: <input type="text"
					id="productSpecificationInput" /><span>（如：统、浙统、大片、选片）</span></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;产地: <address:productionZone
						divName="productionAddress" /></td>
			</tr>
			<tr>
				<td><span style="color: red">*</span> 库存地: <address:productionZone
						divName="productionStorageAddress" /></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;年份: <select id="productProduceYearInput"></select>
<!-- 				<input type="text" id="productProduceYearInput"/> -->
				</td>
				<td>&nbsp;&nbsp;加工方法: <select id="productProcessMethodSelector">
						<option>--</option>
						<option value="qie">切</option>
						<option value="hong">烘</option>
						<option value="jingxuan">净选</option>
						<option value="wash">淘洗</option>
						<option value="paozhi">炮炙</option>
						<option value="dan">燀</option>
						<option value="zhishuang">制霜</option>
						<option value="shuifei">水飞</option>
						<option value="faya">发芽</option>
						<option value="fajiao">发酵</option>
						<option value="others">其他</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp; 包装规格: <select name="packageUnitSelector"
					id="packageUnitSelector">
						<c:forEach items="${packageUnits}" var="packageUnit">
							<option value="${packageUnit.code }">${packageUnit.name }</option>
						</c:forEach>
				</select></td>
				<td>&nbsp;&nbsp;灰分: <select id="productHuifenSelector">
						<option>--</option>
						<option value="more">较多</option>
						<option value="medium">适中</option>
						<option value="few">较少</option>
						<option value="none">无</option>
						<option value="others">自定义百分比</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;干度: <input type="text" id="productGanduInput" />
				</td>
				<td>&nbsp;&nbsp;是否含硫: <input type="text"
					id="productContainLiuInput" />
				</td>
			</tr>
		</table>
	</div>
	<hr noshade color="#aaa">
	<div>
		<p>填写销售价格信息</p>
		<table>
			<tr>
				<td><span style="color: red">*</span> 计量单位: <select
					name="unitSelector" id="unitSelector">
						<c:forEach items="${units}" var="unit">
							<option value="${unit.code }">${unit.name }</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td>库存量</td>
				<td>起售量</td>
				<td>采购数量和单价</td>
			</tr>
			<tr>
				<td><input type="text" id="stockInput" class="intInput digits" />
					公斤</td>
				<td><input type="text" id="minSalesQuantityInput"
					class="intInput" /> 公斤</td>
				<td>
					<div id="priceScalesDiv">
						<div class="priceLineDiv">
							<input type="text" id="minPriceScaleQuantity1Input"
								class="intInput salesQuntityInput" /> 公斤以上 <input type="text" id="priceScale1Input"
								class="doubleInput priceInput" style="display: inline" /> 元/公斤
						</div>
						<div class="priceLineDiv">
							<br> <input type="text" class="intInput salesQuntityInput" />
							公斤以上 <input type="text" class="doubleInput priceInput"
								style="display: inline" /> 元/公斤 <input type="button"
								class="deletePriceScaleBtn" value="删除" style="display: inline" />
						</div>
					</div> <br>
					<div>
						<input type="button" id="addPriceScaleBtn"
							style="border: 0 none; background: #ffffff; color: #daa520"
							value="添加按商品采购起售量的报价" />
					</div>
				</td>
			</tr>
		</table>
	</div>
	<hr noshade color="#aaa">
	<div>添加商品图片</div>
	<hr noshade color="#aaa">
	<div>添加追溯报告图片</div>
	<hr noshade color="#aaa">
	<div>添加质检报告图片</div>
	<hr noshade color="#aaa">
	<div>
		详细描述 <br>
		在详细说明中插入商品细节图片、商品参数等描述，突出您商品的优势和特点：如使用模块，请务必将模版中的说明修改为符合您商品的具体描述 <br>
		<br>
		<textarea name="descriptionEditor" id="descriptionEditor" rows="10"
			cols="80">
                在详细说明中插入商品细节图片、商品参数等描述，突出您商品的优势和特点
            </textarea>
		<script>
			// Replace the <textarea id="editor1"> with a CKEditor
			// instance, using default configuration.
			CKEDITOR.replace('descriptionEditor');
		</script>
	</div>
	<br>
	<br>
	<span style="color: red">*</span>
	<input type="checkbox" id="productAcceptAnguoContractInput"
		checked="checked" /> 我已阅读并接受
	<a id="anguoContractLink" href="http://www.baidu.com">平台协议</a>
	<br>
	<br>
	<input type="button" id="saveDraftBtn" style="background: #36793b"
		value="暂存" />
	<input type="button" id="saveAndPublishBtn"
		style="display: inline; background: #daa520" value="发布商品" />
</body>
</html>