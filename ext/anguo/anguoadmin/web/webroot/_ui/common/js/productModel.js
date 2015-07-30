define(['jquery','backbone', 'underscore'],function($,Backbone,_){
    "use strict";
   
   var ProductModel = Backbone.Model.extend({
        url: "product",
        initialize:function(){
        
            // this.set({
            
            //     "id":"product1",
            //     "name": "test",
            //     "description":"this is the first product, show in admin page"
            // });

        }
    });
    
    return ProductModel;
});