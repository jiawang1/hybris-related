define(['jquery','backbone', 'underscore','productModel','text!../template/productTpl.html'],function($,Backbone,_,ProductModel,sProductTemplate){
    "use strict";
    
    var ProductsView = Backbone.View.extend({     /* create view class*/
        
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){                          /* render the view*/
            _.templateSettings={

                interpolate: /\{\{(.+?)\}\}/g
            };
           this.$el.html(_.template(sProductTemplate,{"model": new ProductModel()}));
           return this;
        }
    
    });
    
    return ProductsView;                           /* expose the class */
});