ACC.jstree = {
	bindAll: function(){
		$('#category-tree-div').jstree({
			"core" : {
				'data' : {
					'url' : ACC.config.contextPath+"/productManagement/getSubCategory",
					'data' : function (node) {
						return { 'categoryCode' : node.id };
					}
				},
				'check_callback' : true,
				'themes' : {
					'responsive' : false
				}
			}
			
		}).bind("select_node.jstree", function (event, data) {  
			var selected_node_id = data.selected[0];
			$.ajax({
				type:'GET',
				url:ACC.config.contextPath + "/productManagement/getCategoryDetail",
				data:{'categoryCode':selected_node_id},
				success:function(data){
					$(':input','#category-detail-form').not(':button,:submit,:reset,:hidden').val('')  
					 .removeAttr('checked').removeAttr('selected');
					$('#aliasGrid').dataTable().fnClearTable();
					$('input[name="categoryCode"]').attr("value",data.categoryCode);
					$('input[name="categoryName"]').attr("value",data.name);
					$('#aliasGrid').dataTable().fnAddData(data.alias);
				   
				}
			});
        });  	
		
	}

};

$(document).ready(function ()
{
    ACC.jstree.bindAll();
    
    $('#aliasGrid').dataTable({
    	"oLanguage": {
    		"sZeroRecords": "抱歉， 没有找到",
    		"sInfoEmpty": "没有数据",
    		"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据"
    	},
    	"bPaginate": false,
    	"bLengthChange": false,
    	"columns": [
    	     {"data": "description"}
    	]
    });

});
