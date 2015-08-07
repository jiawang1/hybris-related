var nEditing;
define(['jquery','backbone', 'underscore', 'lib/jstree','datatables','text!./../template/categoryTpl.html'],function($,Backbone,_, jstree,dataTable,sCategoryTpl){
    "use strict";
    var CategoryView = Backbone.View.extend({
        $el: $("div"),
        initialize:function(){
        },
        renderTree:function(){
            var tree = this.$el.find('#category-tree-div');
            tree.jstree({
    			'core' : {
    				'data' : {
    					'url' : '/anguoadmin/categoryManagement/getSubCategory',
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
    				url:'/anguoadmin/categoryManagement/getCategoryDetail',
    				data:{'categoryCode':selected_node_id},
    				success:function(data){
    			    	$('#category-detail-form :input').attr('value','');
    					$('#categoryCode').attr("value",data.categoryCode);
    					$('#categoryName').attr("value",data.name);
    					
    					$('#aliasGrid').dataTable().fnClearTable();
    					var aliasName = data.alias;
    					
    					if(aliasName==null)
    	    			  return;
    					
    					for(var i = 0;i < aliasName.length;i++)
        				   $('#aliasGrid').dataTable().fnAddData([aliasName[i].description,    					 
        				   '<a class="edit" href="">修改</a>', '<a class="delete" href="">删除</a>' ]);
//    					 data table ootb how to add data
//    					$('#aliasGrid').dataTable().fnAddData(data.alias);
    				}
    			});
            });  	
        },
        renderGrid:function(){
        	var aliasGrid = this.$el.find('#aliasGrid');
    		aliasGrid.dataTable(
    			{
    	    	"oLanguage": {
    	    		"sZeroRecords": "抱歉， 没有找到",
    	    		"sInfoEmpty": "没有数据",
    	    		"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据"
    	    	},
    	    	"bPaginate": false,
    	    	"bLengthChange": false,
    	    	"bFilter": false
    	    }
    	    );
		     
		     this.$('#new').click(function(e) {
				e.preventDefault();
	
				var aiNew = aliasGrid.fnAddData(['',
						'<a class="edit" href="">修改</a>',
						'<a class="delete" href="">删除</a>']);
				var nRow = aliasGrid.fnGetNodes(aiNew[0]);
				editRow(aliasGrid, nRow);
				nEditing = nRow;
			 });
			 
			this.$('#aliasGrid').on('click', function (e) {
			   if(e.target.className =='delete' || e.target.className == 'edit')
			     e.preventDefault();
			     
			   if(e.target.className == 'delete'){
			     var nRow = $(e.target).parents('tr')[0];
			     aliasGrid.fnDeleteRow( nRow );
			   }
			   
			   if(e.target.className == 'edit'){
			   	var nRow = $(e.target).parents('tr')[0];
			    if ( nEditing !== null && nEditing != nRow ) {
				/* Currently editing - but not this row - restore the old before continuing to edit mode */
				  restoreRow( aliasGrid, nEditing );
				  editRow( aliasGrid, nRow );
				  
				  nEditing = nRow;
			    }
			    else if ( nEditing == nRow && e.target.innerHTML == "保存" ) {
				/* Editing this row and want to save it */
				  saveRow(aliasGrid, nEditing );
				  nEditing = null;
			    }
			    else {
				/* No edit in progress - let's start one */
				  editRow(aliasGrid, nRow );
				  nEditing = nRow;
			    }
			   }
		    } );
			 
        },
        renderCategoryPanel:function(){
        	this.$('#saveCat').on('click',function(){
			if($('input[name="categoryCode"]').val()==''){
			  alert('请先选择或者建立新类目');
			  return;
			}
			
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
	    		url:'/anguoadmin/categoryManagement/saveCategory',
	    		data:{'categoryDetail':JSON.stringify(categoryDetail)},
				dataType:"json",
				async:true,
	    		success:function(data){
	    			$("#category-tree-div").jstree().set_text(data.categoryCode,data.name);
	    			alert('更新成功');
	    		}
	    	});
	    	
		});
        },
        render:function(){
            this.$el.html(_.template(sCategoryTpl));
            this.renderTree();
            this.renderGrid();
            this.renderCategoryPanel();
            return this;
        }
    
    });
    return CategoryView;
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
    
    var items;
    
    items = {
      'add': {
        'separator_before': false,
        'separator_after': true,
        'label': '添加子类目',
        'action': function (obj) {
        	$('#category-detail-form :input').attr('value','');
			$('#aliasGrid').dataTable().fnClearTable();
        	$('input[name="superCategory"]').attr("value",node.id);
        	
        	$.ajax({
        		type:'POST',
        		url:'/anguoadmin/categoryManagement/createCategory',
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
        		url:'/anguoadmin/categoryManagement/deleteCategory',
				data:{'categoryCode':categoryCode},
        		success:function(data){
        			if(data.isSuccessFlag == false){
        			    alert(data.message);
        			    return;
        			}
        			
        	        $('#category-tree-div').jstree().delete_node(node);
        	        $('#category-detail-form :input').attr('value','');
        	        $('#aliasGrid').dataTable().fnClearTable();
        		}
        	});
        	
        }
      }
     }
   
    // cat level 3 not show add button
    if(node.parents.length > 2)
    {
        delete items.add;
    }

    return items;
}