var AM = {};

define(['jquery','backbone', 'underscore','accessible','datatables','text!./../template/productListTpl.html'],function($,Backbone,_,Accessible,dataTable,sProductListTpl){
    'use strict';
   
   var ProductListView = Backbone.View.extend({
        initialize:function(){
      
        },
        events:{
        	'click #getProductListButton':'getProductListByCondition',
        	'click .tabs-list a':'getProductListByStatus'
        },
        getProductListByStatus:function(){
        	
        	var tabId = $('.current').eq(0).attr('id');
        	var index = parseInt(tabId.substr(tabId.length-1,tabId.length-1));
        	var productStatus;
        	switch(index){
        	case 0:
        		productStatus = 'UNCHECK';
        		break;
        	case 1:
        		productStatus = 'PROGRESS';
        		break;
        	case 2:
        		productStatus = 'REJECT';
        		break;
        	case 3:
        		productStatus = 'ONLINE';
        		break;
        	case 4:
        		productStatus = 'OFFLINE';
        		break;
        	}
        	
        	this.resetSearchCondition();
        	AM.grid.reset();
        	
        	$('input[name="productStatus"]').val(productStatus);
        	AM.grid.requestData();
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
                currentInfoClass: 'current-info',
                tabsListClass:'tabs-list',
                currentInfoText:'current tab:'
            });
            
            AM.grid.init('productListGrid','/anguoadmin/productManagement/getProductList');
            
            this.$('#productStatus').val('UNCHECK');
            
            this.$('.nextpage').on('click',function(){
            	AM.grid.nextPage();
            });
            
            this.$('.lastpage').on('click',function(){
            	AM.grid.lastPage();
            });
            
            AM.grid.requestData('UNCHECK');
            
            return this;
        },
        getProductListByCondition:function(){
        	$('input[name="productCodeHidden"]').val($('input[name="productCode"]').val());
        	$('input[name="productNameHidden"]').val($('input[name="productName"]').val());
        	$('input[name="storeNameHidden"]').val($('input[name="storeName"]').val());
        	AM.grid.reset();
        	AM.grid.requestData();
        },
        resetSearchCondition(){
//        	$('input[name="productCodeHidden"]').val('');
//            $('input[name="productCode"]').val('');
//        	$('input[name="productNameHidden"]').val('');
//            $('input[name="productName"]').val('');
//        	$('input[name="storeNameHidden"]').val('');
//        	$('input[name="storeName"]').val('');
//        	$('input[name="productStatus"]').val('');
        	
        	$('#searchCondition :hidden').val('');
        	$('#searchCondition :text').val('');
        }
    });
   
    
    return ProductListView;
});

AM.grid = {
	currentPage: 0,
	pageSize:2,
	totalPage:0,
	totalRecords:0,
	tableId:'',
	requestUrl:'',
	init:function(table,url){
		this.tableId = table;
		this.requestUrl = url;
	},
	requestData: function(productStatus){

    	this.emptyTable();
    	
		var aoData = new Array();
			
		aoData.push({ 'name': 'productCode', 'value': $('input[name="productCodeHidden"]').val()});
		aoData.push({ 'name': 'productName', 'value': $('input[name="productNameHidden"]').val()});
		aoData.push({ 'name': 'storeName', 'value': $('input[name="storeNameHidden"]').val()});
		
		//if we call this in view,we can not get productStatus,should give a default value
		if(arguments.length > 0)
		aoData.push({ 'name': 'productStatus', 'value': productStatus});
		else
		aoData.push({ 'name': 'productStatus', 'value': $('input[name="productStatus"]').val()});
		
		aoData.push({ 'name': 'currentPage', 'value': this.currentPage});
		aoData.push({ 'name': 'length', 'value': this.pageSize});
		
		$.getJSON(this.requestUrl,aoData,function(result){
			AM.grid.updateTable(result);
		 });
	},
	updateTable: function(result){
		this.updateData(result.data);
		this.totalPage = result.totalPage;
		this.totalRecords = result.totalRecords;
		
		$('.currentpage').html(this.currentPage+1);
		$('.totalpagetext').html('共'+this.totalPage+'页');
		$('.totalrecordstext').html('共'+this.totalRecords+'记录');
	},
	updateData: function(datas){
		if(datas.length == 0){
			//alert('无记录');
			return;
		}
		for(var i = 0;i < datas.length;i++)
			this.insertRow(datas[i]);
	},
	insertRow: function(data){   
		var row = $('<tr></tr>');
		row.append('<td><img src="'+data[0]+'"/></td>');
		
		for(var i = 1;i < data.length - 1;i++)
		{  
		   row.append('<td>'+data[i]+'</td>');
		}
		if($('input[name="productStatus"]').val()=='UNCHECK')
    		row.append('<a href="#products?code='+data[i]+'">审核</button>');
		else
		    row.append('<a href="#products?code='+data[i]+'">查看</button>');
		
		
		$('#'+this.tableId+' tbody').append(row);
		
	},
	nextPage:function(){
		if(this.currentPage == this.totalPage - 1){
			alert('已到末页');
			return;
		}
		
		this.currentPage = this.currentPage + 1;
		this.requestData();
	},
	lastPage:function(){
		if(this.currentPage==0){
			alert('已无前页');
			return;
	    }
	    
		this.currentPage = this.currentPage - 1;
		this.requestData();
	},
	reset:function(){
		this.emptyTable();
		this.currentPage = 0;
		this.totalPage = 0;
		this.totalRecords = 0;
    	$('.currentpage').html('1');
    	$('.totalpagetext').html('共 0 页');
		$('.totalrecordstext').html('共 0 记录');
	},
	emptyTable:function(){
		$('#'+this.tableId+' tbody').empty();
	}
}
