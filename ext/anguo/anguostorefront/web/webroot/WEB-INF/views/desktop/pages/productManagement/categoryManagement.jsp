<html>
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="compressible" tagdir="/WEB-INF/tags/desktop/template/compressible"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<link rel="stylesheet" href="${commonResourcePath}/js/themes/default/style.min.css" />
<link rel="stylesheet" href="${commonResourcePath}/css/jquery.dataTables.css" />
<link rel="stylesheet" href="${commonResourcePath}/css/dataTables.editor.min.css" />
<link rel="stylesheet" href="${commonResourcePath}/css/dataTables.tableTools.css" />

<compressible:js/>
<script type="text/javascript" src="${commonResourcePath}/js/jstree.js"></script>

<script type="text/javascript" src="${commonResourcePath}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.dataTables.editable.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/dataTables.tableTools.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/dataTables.editor.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.jstree.js"></script>
</head>
<body>
	<div style="float:left;overflow-y:scroll;overflow:auto;width:400px;height:300px;border:1px solid" id="category-tree-div"></div>
	<div style="float:none;clear:both;margin:10px;padding:10px">
    	<div>
			<button id="saveCat">保存类目</button>
		</div>
		<form id="category-detail-form">
			<input type="hidden" id="categoryCode" name="categoryCode"></input> 
			<label for="categoryCode">目录名称</label>
			<input type="text" name="categoryName"></input>
		</form>
		<button id="new">新建别名</button>
		<table id="aliasGrid" style="float:left" class="display" cellspacing="0" width="40%">
		<thead>
			<tr>
			  <th width="60px">别名</th>
			  <th width="20px"></th>
			  <th width="20px"></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	</div>
    <div id="test"></div>
</body>
</html>