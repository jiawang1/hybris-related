define(['jquery','backbone', 'underscore','navigationMenu','productsView','widget/inputList' ],function($,Backbone,_, Navigation, Products){
    "use strict";
    
    
//    window.onpopstate = function(event){
//        console.log(event);
//    };
//    
//    window.onhashchange = function(event){
//        console.log(event);
//    };
    var i = 0;
var AppRouter = Backbone.Router.extend({
    
        routes:{
            "products":"handleProduct",
            "category":"handleCategory",
            "category/:id":"handleSubCategory",
            "others":"handleOthers",
            "default":"createFrame" 
        },
        
        handleCategory:function(){           
            require(["categoryView"], function(Category){
                $(".main-root").empty().append(new Category().render().$el);
            });
        },
    
        handleProduct:function(){
             $(".main-root").empty().append(new Products().render().$el);
        },
    
        handleOthers:function(){
             $(".main-root").empty().append(new Products().render().$el);
        },
    
        handleSubCategory:function(id){
            console.log(id);
        },
        
        initialize:function(){
        
            i++;
        },
    
        getNumber:function(){
            return i;
        }

    }); 
    
    var appRouter = new AppRouter();
    
    appRouter.on("createFrame", function(){
       $(".nav-root").append( new Navigation().render().$el);
    });
    
//    appRouter.on("route:handleProduct", function(){
//        $(".main-root").empty().append(new Products().render().$el);
//    });
    

    
    
    
    $(".nav-root").append( new Navigation().render().$el);
    Backbone.history.start();
    return appRouter;
    
});