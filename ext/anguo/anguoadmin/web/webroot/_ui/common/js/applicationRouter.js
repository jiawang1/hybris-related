define(['jquery','backbone', 'underscore','navigationMenu','productsView','ordersView','paymentView','sampleView',
    
    'widget/inputList' ],
       function($,Backbone,_, Navigation, Products,Orders,Payment,Sample){
    "use strict";


    var i = 0;
var AppRouter = Backbone.Router.extend({
    
        routes:{
            "product":"handleProduct",
            "category":"handleCategory",
            "products":"handleProductList",
            "category/:id":"handleSubCategory",
            "sample":"handleSample",
            "default":"createFrame" ,
            "payment":"handlePaymentmManagement" ,
            "orders":"handleOrderManagement" ,
            "stores":"handleManagement" ,
//            "users": "handleUserManagement",
            "users/enterprise-user": "handleEnterPriseUser",
            "users/seller": "handleSeller",
            "stores/new":"handleNewStore" ,
            "stores/change":"handlechangeStore" ,
            "stores/recommend":"handleRecStore" ,
            "products/check":"handleCheckProduct",
            "products/recommend":"handleRecProduct"
        },
        
    /*  destrpy method should be supplied to prevent memory leak*/
        handleCategory:function(){           
            require(["categoryView"], function(Category){
                $(".main-root").empty().append(new Category().render().$el);
            });
        },
        handleCheckProduct:function(){
             require(["checkProductView"], function(Product){
                    $(".main-root").empty().append(new Product().render().$el);
                });
        },
        handleRecProduct:function(){
                require(["recProductView"], function(Product){
                    $(".main-root").empty().append(new Product().render().$el);
                });
        },
    
        handleNewStore:function(){
            require(["newStoreView"], function(Store){
                $(".main-root").empty().append(new Store().render().$el);
            });
        },
    

        handleProductList:function(){
        	require(["productListView"], function(ProductList){
             $(".main-root").empty().append(new ProductList().render().$el);
        	});
        },
        

        handleRecStore:function(){
            require(["recStoreView"], function(Store){
                $(".main-root").empty().append(new Store().render().$el);
            });
        },
        
        handlechangeStore:function(){
            require(["changeStoreView"], function(Store){
                $(".main-root").empty().append(new Store().render().$el);
            });
        },

        handleOrderManagement:function(){
             $(".main-root").empty().append(new Orders().render().$el);
        },

        handlePaymentmManagement:function(){
             $(".main-root").empty().append(new Payment().render().$el);
        },

        handleProduct:function(){
        	$(".main-root").empty().append(new Products().render().$el);
        },
    
        handleOthers:function(){
             $(".main-root").empty().append(new Products().render().$el);
        },
    
        handleSeller:function(){
            require(["sellerView"], function(SellerView){
                $(".main-root").empty().append(new SellerView().render().$el);
            });
        },
    
        handleEnterPriseUser:function(){
            require(["enterpriseView"], function(SellerView){
                $(".main-root").empty().append(new SellerView().render().$el);
            });
        },
        handleSample:function(){
             $(".main-root").empty().append(new Sample().render().$el);
        },
        
        initialize:function(){
        
            i++;
        },
    
        getNumber:function(){
            return i;
        }

    }); 
    
    var appRouter = new AppRouter();

    require(['json!localization/ui-text-ZH.json'],function(json){
        Backbone.__initI18n(json);

     var __config = [
         {"label":Backbone.getI18n('app.user.userManagement'), "link": "#users", children:[{"label":"企业用户管理", "link": "/enterprise-user"},{"label":"个人卖家审核", "link": "/seller"}]},
         {"label":Backbone.getI18n('app.store.storeManagement'), "link": "#stores",children:[{"label":"新店铺申请审核", "link": "/new"},{"label":"店铺信息修改审核", "link": "/change"},{"label":"店铺推荐", "link": "/recommend"}]},
         {"label":"订单管理", "link": "#order"},
         {"label":"支付管理", "link": "#payment"},
         {"label":"目录管理", "link": "#category"},
         {"label":Backbone.getI18n('app.products.pageTitle'), "link": "#products"},
         {"label":"商品管理", "link": "#product",children:[{"label":"商品审核", "link": "/check"},{"label":"商品推荐", "link": "/recommend"}]},
         {"label":"示例", "link": "#sample"}
    ];


        $(".nav-root").append( new Navigation(__config).render().$el);
    });
    

    Backbone.history.start();
    return appRouter;
    
});