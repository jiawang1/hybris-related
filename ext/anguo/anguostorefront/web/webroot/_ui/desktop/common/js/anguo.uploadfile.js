function removeFile() {
	var $table = $(this).parent().parent().parent();
	$(this).parent().remove();
	if($table.children("tbody").children("tr").length == 0){
		$table.parent().empty();
	}
}

function addSingleFile(obj, data, fieldName, hideName) {
	$(obj).parent().find("div[class=uploadedFiles]").empty();
	var $table= $("<table></table>"); 
	var $tr= $("<tr></tr>"); 
	var $td1 = $("<td class='fileName'>1. " + fieldName + "</td>"); 
	var $td2 = $("<td class='filePath'></td>");
	var $a1 = $("<a target='_blank' href='"+data.media.url+"'>"+data.media.altText+"</q>");
	$td2.append($a1);
	var $hide = $("<input type='hidden' name='"+hideName+"' value='"+data.media.code+"' />");
	$td2.append($hide);
	var $td3 = $("<td class='fileAction'><button type='button'>Delete</button></td>"); 
	$td3.on('click', removeFile);
	$tr.append($td1);
	$tr.append($td2);
	$tr.append($td3);
	$table.append($tr);
	$(obj).parent().find("div[class=uploadedFiles]").append($("<p>Uploaded Documents</p>"));
	$(obj).parent().find("div[class=uploadedFiles]").append($table);
}

function addMultiFile(obj, data) {
	var $div = $(obj).parent().find("div[class=uploadedFiles]");
	var index = $div.children("table").length;
	if($div.children("table").length == 0){
		addSingleFile(obj, data,data.media.name,data.media.code);
	}else{
		var $table= $div.children("table"); 
		var $tr= $("<tr></tr>"); 
		var $td1 = $("<td class='fileName'>" + ($table.children("tbody").children("tr").length+1) + ". " + data.media.name + "</td>"); 
		var $td2 = $("<td class='filePath'></td>"); 
		var $a1 = $("<a target='_blank' href='"+data.media.url+"'>"+data.media.altText+"</q>");
		$td2.append($a1);
		var $hide = $("<input type='hidden' name='"+"uploadedDocuments["+index+"].code"+"' value='"+data.media.code+"' />");
		$td2.append($hide);
		var $td3 = $("<td class='fileAction'><button type='button'>Delete</button></td>"); 
		$td3.on('click', removeFile);
		$tr.append($td1);
		$tr.append($td2);
		$tr.append($td3);
		$table.append($tr);
	}
}