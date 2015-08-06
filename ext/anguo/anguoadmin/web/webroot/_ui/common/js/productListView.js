var searchlfag ;//stop 
define(['jquery','backbone', 'underscore','accessible','datatables','text!./../template/productListTpl.html'],function($,Backbone,_,Accessible,dataTable,sProductListTpl){
    'use strict';
   
   var ProductListView = Backbone.View.extend({
        initialize:function(){
      
        },
        events:{
        	'click #getProductListButton':'getProductList'
        	//'click #UNCHECK':'setProductStatus'
        },
        render:function(){
        	this.$el.html(_.template(sProductListTpl));
            this.$('#productListTab').accessibleTabs({
            	wrapperClass:'content',
            	currentClass:'current',
                tabhead:'.tabHead',
                tabbody:'.tabBody',
                fx:'show',
                fxspeed:'normal',
                currentInfoText:'cirrent tab:'
            });
            
            
            this.$('#uncheckgrid').dataTable({
    	    	'oLanguage': {
    	    		'sZeroRecords': '抱歉， 没有找到',
    	    		'sInfoEmpty': '无数据',
    	    		'sEmptyTable': '抱歉,没有找到符合要求的数据',
    	    		'sInfo': '从 _START_ 到 _END_ /共 _TOTAL_ 条数据',
    	    		'sInfoFiltered':'',
    	    		'oPaginate': {
        	    		'sPrevious':'上一页',
        	    		'sNext':'下一页'
        	    	}
    	    	},
    	    	'bServerSide':true,
    	    	'bPaginate': true,
    	    	'bLengthChange': false,
    	    	'bFilter': false,
    	    	'iDeferLoading':1,
    	    	'pageLength':2,
    	    	"createdRow": function ( row, data, index ) {
    	             $('td', row).eq(0).html('<img src="'+data[0]+'"/>');
    	             $('td', row).eq(6).html('<a href="/anguoadmin/#products?productCode='+data[1]+'">查看</a>');
    	             
    	        },
    	    	'fnServerData':function ( sSource, aoData, fnCallback ) {
    	    		var requesturl = '/anguoadmin/productManagement/getProductList';
    	    		var productStatus;
    	        	$('.tabBody:visible').each(function(){
    	        		productStatus = $(this).attr('status');
    	        	});
    	        	
    	    		aoData.push({ 'name': 'productCode', 'value': $('input[name="productCode"]').val()});
    	    		aoData.push({ 'name': 'productName', 'value': $('input[name="productName"]').val()});
    	    		aoData.push({ 'name': 'storeName', 'value': $('input[name="storeName"]').val()});
    	    		aoData.push({ 'name': 'productStatus', 'value': 'UNCHECK'});
//                  local test data   	    		
//    	    		var serverdata = {
//    	    				  "draw": 2,
//    	    				  "recordsTotal": 0,
//    	    				  "recordsFiltered": 0,
//    	    				  "data": [
//    	    				      
//    	    		]};
    	    		//fnCallback(serverdata);
    	    		$.getJSON( requesturl, aoData, 
    	    				function (json) { 
    	    			// because this is triggered through button,so 
    	    			fnCallback(json);
    				} );
    	    	}
    	    	});
        	return this;
        },
        getProductList:function(){
        	var productStatus; 

        	this.$('#datagrid').dataTable().fnDraw();
        	
        }
    });
    
    return ProductListView;
});