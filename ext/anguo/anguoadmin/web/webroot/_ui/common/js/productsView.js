define(['jquery','backbone', 'underscore','productModel','text!../template/productTpl.html'],function($,Backbone,_,ProductModel,sProductTemplate){
    "use strict";
    
    var ProductsView = Backbone.View.extend({     /* create view class*/
        
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){   

        var that = this;                       /* render the view*/
//            _.templateSettings={
//
//                interpolate: /\{\{(.+?)\}\}/g
//            };

           $.when(new ProductModel().fetch()).done(function(model){
        	   
        	  console.log("productview: " + model);
        	 
              that.$el.html(_.template(sProductTemplate,{"model": model}));
              
              console.log(that.$el.html());

           });

           return this;
        }
    
    });
    
    return ProductsView;                           /* expose class */
});