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
    	    		'sInfoEmpty': '没有数据',
    	    		'sInfo': '从 _START_ 到 _END_ /共 _TOTAL_ 条数据',
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
    	    	'pageLength':1,
    	    	'fnServerData':function ( sSource, aoData, fnCallback ) {
    	    		var requesturl = '/anguoadmin/productManagement/getProductList';
    	    		var productStatus;
    	        	$('.tabBody:visible').each(function(){
    	        		productStatus = $(this).attr('status');
    	        	});
    	        	
    	    		aoData.push({ 'name': 'productCode', 'value': this.$('input[name="productCode"]').val()});
    	    		aoData.push({ 'name': 'productName', 'value': this.$('input[name="productName"]').val()});
    	    		aoData.push({ 'name': 'storeName', 'value': this.$('input[name="storeName"]').val()});
    	    		aoData.push({ 'name': 'productStatus', 'value': 'UNCHECK'});
    	    		
//    	    		var serverdata = {
//    	    				  "draw": 1,
//    	    				  "recordsTotal": 57,
//    	    				  "recordsFiltered": 57,
//    	    				  "data": [
//    	    				       ['8','2','3','4','5','6','7'],
//    	    				       ['8','2','3','4','5','6','7']
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

        	$('#uncheckgrid').dataTable().fnDraw();
        	
        	
//        	$('input[name="productCode"]').val(
//        			$('input[name="productCodeInput"]').val()
//            );
//        	$('input[name="productName"]').val(
//        			$('input[name="productNameInput"]').val()
//        	);
//        	$('input[name="storeName"]').val(
//        			$('input[name="storeNameInput"]').val()
//        	);
        }
    });
    
    return ProductListView;
});