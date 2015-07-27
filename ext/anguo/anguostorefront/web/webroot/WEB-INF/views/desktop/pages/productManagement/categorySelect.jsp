<html>
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="compressible"
	tagdir="/WEB-INF/tags/desktop/template/compressible"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<link rel="stylesheet"
	href="${commonResourcePath}/js/themes/default/style.min.css" />
<link rel="stylesheet"
	href="${commonResourcePath}/css/jquery.dataTables.css" />
<link rel="stylesheet"
	href="${commonResourcePath}/css/dataTables.editor.min.css" />
<link rel="stylesheet"
	href="${commonResourcePath}/css/dataTables.tableTools.css" />

<compressible:js />
<script type="text/javascript"
	src="${commonResourcePath}/js/acc.categorysearch.js"></script>

<style>
.categoryTree {
	float: left;
	margin: 10px;
	height: 280px;
	width: 175px;
	border: 1px solid #000000;
	background: #ffffff;
	overflow: auto
}
</style>
</head>
<body>
	<div style="margin: 10px">
		<p>
			类目搜索: <input type="text" id="keywordInput" />
		</p>
		<input type="button" id="catSearchBtn" value="快速找到类目" />
	</div>
	<div id="categoryTreeDiv"
		style="display: block; background-color: #87cefa; height: 300px; width: 600px">
		<div id="categoryTreeDivLev1" class="categoryTree">
			<c:forEach items="${categoryRoots }" var="categoryRoot">
				<li catCode="${categoryRoot.id}" class="categorySelectorLev1"
					style="list-style: none; margin: 10px">${categoryRoot.text }</li>
			</c:forEach>
		</div>
		<div id="categoryTreeDivLev2" class="categoryTree"></div>
		<div id="categoryTreeDivLev3" class="categoryTree"></div>
	</div>
	<div id="searchCatResDiv" style="display: none">
		<b>搜索到的结果：</b> <input type="button" id="backToCatTreeBtn"
			value="关闭，返回类目" />
		<div
			style="background-color: #87cefa; margin-top: 10px; height: 300px; width: 600px; overflow: hidden">
			<div id="resultDiv"
				style="margin: 10px; height: 280px; width: 580px; border: 1px solid #000000; background: #ffffff; overflow: auto">
			</div>
		</div>
	</div>
	<div style="margin: 10px">
		<input type="button" id="catDefineBtn" value="使用所选类目" />
	</div>
</body>
</html>