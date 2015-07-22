<html>
<head>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="compressible" tagdir="/WEB-INF/tags/desktop/template/compressible"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<compressible:js/>
<script type="text/javascript" src="${commonResourcePath}/js/jstree.min.js"></script>
<link rel="stylesheet" href="${commonResourcePath}/js/themes/default/style.min.css" />
<link rel="stylesheet" href="${commonResourcePath}/css/jquery.dataTables.css" />
<link rel="stylesheet" href="${commonResourcePath}/css/dataTables.editor.min.css" />
<link rel="stylesheet" href="${commonResourcePath}/css/dataTables.tableTools.css" />
<script type="text/javascript" src="${commonResourcePath}/js/acc.jstree.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/dataTables.editor.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/dataTables.tableTools.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.dataTables.editable.js"></script>
</head>
<body>
	<div style="float:left; width: 50%" id="category-tree-div"></div>
	<div style="float:left;width:50%">
		<form id="category-detail-form">
			<label for="categoryCode">目录编码</label> 
			<input type="text" id="categoryCode" name="categoryCode"></input> 
			<label for="categoryCode">目录名称</label>
			<input type="text" name="categoryName"></input>
		</form>
			<table id="aliasGrid" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
			  <th>Name</th>
			</tr>
		</thead>

		<tbody>
		    <td></td>

		</tbody>
	</table>
	</div>

</body>
</html>