var editor;
var aliasGrid;
var nEditing;

ACC.jstree = {
	initTree: function(){
		$('#category-tree-div').jstree({
			
			'core' : {
				'data' : {
					'url' : ACC.config.contextPath+'/categoryManagement/getSubCategory',
					'data' : function (node) {
						return { 'categoryCode' : node.id };
					}
				},
				'check_callback' : true
			},
			'plugins' : ['contextmenu','crrm'],
			'contextmenu':{'items':getCustomMenu}
			
		}).bind("select_node.jstree", function (event, data) {  
			var selected_node_id = data.selected[0];
			$.ajax({
				type:'GET',
				url:ACC.config.contextPath + '/categoryManagement/getCategoryDetail',
				data:{'categoryCode':selected_node_id},
				success:function(data){
					$(':input','#category-detail-form').not(':button,:submit,:reset,:hidden').val('')  
					 .removeAttr('checked').removeAttr('selected');
					$('#aliasGrid').dataTable().fnClearTable();
					$('input[name="categoryCode"]').attr("value",data.categoryCode);
					$('input[name="categoryName"]').attr("value",data.name);
					
					var aliasName = data.alias;
					
					if(aliasName==null)
					  return;
					  
					for(var i = 0;i < aliasName.length;i++)
					  $('#aliasGrid').dataTable().fnAddData([aliasName[i].description,
					  '<a class="edit" href="">修改</a>', '<a class="delete" href="">删除</a>' ]);
					  
				//	$('#aliasGrid').dataTable().fnAddData(data.alias);

				}
			});
        });  	
		
	},
	initGrid:function(){
		aliasGrid =  $('#aliasGrid').dataTable({
    	"oLanguage": {
    		"sZeroRecords": "抱歉， 没有找到",
    		"sInfoEmpty": "没有数据",
    		"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据"
    	},
    	"bPaginate": false,
    	"bLengthChange": false,
    	"bFilter": false
        });
        $('#new').click( function (e) {
			e.preventDefault();
			
			var aiNew = aliasGrid.fnAddData( [ '', 
				'<a class="edit" href="">修改</a>', '<a class="delete" href="">删除</a>' ] );
		    var nRow = aliasGrid.fnGetNodes( aiNew[0] );
		    editRow( aliasGrid, nRow );
			nEditing = nRow;
		
	    } );
	
		$('#aliasGrid a.delete').live('click', function (e) {
			e.preventDefault();
			
			var nRow = $(this).parents('tr')[0];
			aliasGrid.fnDeleteRow( nRow );
		} );
		
		$('#aliasGrid a.edit').live('click', function (e) {
			e.preventDefault();
			
			/* Get the row as a parent of the link that was clicked on */
			var nRow = $(this).parents('tr')[0];
			
			if ( nEditing !== null && nEditing != nRow ) {
				/* Currently editing - but not this row - restore the old before continuing to edit mode */
				restoreRow( aliasGrid, nEditing );
				editRow( aliasGrid, nRow );
				nEditing = nRow;
			}
			else if ( nEditing == nRow && this.innerHTML == "保存" ) {
				/* Editing this row and want to save it */
				saveRow(aliasGrid, nEditing );
				nEditing = null;
			}
			else {
				/* No edit in progress - let's start one */
				editRow(aliasGrid, nRow );
				nEditing = nRow;
			}
		} );
        
	},
	initCategoryControl:function(){
		$('#saveCat').on('click',function(){
			
			var aliasRow = $('#aliasGrid').DataTable().rows().data();
			var aliasArray = new Array();
			//get alias array data
            for(var i = 0;i<aliasRow.length;i++){
	        	aliasArray.push({'description':aliasRow[i][0]});
	        	
	        }
	        //categoryDetail will be transfered to backend
			var categoryDetail = 
				{
					'categoryCode':	$('input[name="categoryCode"]').val(),
					'name':$('input[name="categoryName"]').val(),
					'superCategory':$('input[name="superCategory"]').val(),
					'alias':aliasArray
				};
			
			$.ajax({
	    		type:'POST',
	    		url:ACC.config.contextPath + '/categoryManagement/saveCategory',
	    		data:{'categoryDetail':JSON.stringify(categoryDetail)},
				dataType:"json",
				async:true,
	    		success:function(data){
	    			$("#category-tree-div").jstree().set_text(data.categoryCode , data.name);
	    			alert('更新成功');
	    		}
	    	});
	    	
		});
		
	}
};

$(document).ready(function (){
    
    ACC.jstree.initTree();
    ACC.jstree.initGrid();
    ACC.jstree.initCategoryControl();
	
});
	
function restoreRow ( oTable, nRow )
{
	var aData = oTable.fnGetData(nRow);
	var jqTds = $('>td', nRow);
	
	for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
		oTable.fnUpdate( aData[i], nRow, i, false );
	}
	
	oTable.fnDraw();
}

function editRow ( oTable, nRow )
{
	var aData = oTable.fnGetData(nRow);
	var jqTds = $('>td', nRow);
	jqTds[0].innerHTML = '<input type="text" value="'+aData[0]+'">';
	jqTds[1].innerHTML = '<a class="edit" href="">保存</a>';
}

function saveRow ( oTable, nRow )
{
	var jqInputs = $('input', nRow);
	oTable.fnUpdate( jqInputs[0].value, nRow, 0, false );
	oTable.fnUpdate( '<a class="edit" href="">修改</a>', nRow, 1, false );
	oTable.fnDraw();
}

function getCustomMenu(node) {

    var categoryCode = node.li_attr.id;
    
    var items = {
      'add': {
        'separator_before': false,
        'separator_after': true,
        'label': '添加子类目',
        'action': function (obj) {
        	$(':input','#category-detail-form').not(':button,:submit,:reset,:hidden').val('')  
			.removeAttr('checked').removeAttr('selected');
			$('#aliasGrid').dataTable().fnClearTable();
        	$('input[name="superCategory"]').attr("value",node.id);
        	
        	$.ajax({
        		type:'POST',
        		url:ACC.config.contextPath + '/categoryManagement/createCategory',
				data: {'superCategory':node.id},
        		success:function(data){
        	       $("#category-tree-div").jstree().create_node(node,{'id':data.categoryCode,'text':data.name});
        		}
        	});
        }
      },
      'delete': {
        'separator_before': false,
        'separator_after': false,
        'label': '删除该类目',
        'action': function (obj) {
        	$.ajax({
        		type:'POST',
        		url:ACC.config.contextPath + '/categoryManagement/deleteCategory',
				data:{'categoryCode':categoryCode},
        		success:function(data){
        	        $('#category-tree-div').jstree().delete_node(node);
        		}
        	});
        	
        }
      }
     };

    //  If this is a jsTree node for a Folder (rather than a Report) then 
    //  just show the "Refresh" ContextMenu item
    if (node.li_attr.ReportID == null)
    {
        delete items.Run;
    }

    return items;
}
