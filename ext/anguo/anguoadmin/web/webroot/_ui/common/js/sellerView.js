define(['jquery','backbone', 'underscore','text!../template/sellerTpl.html'],function($,Backbone,_,sSellerTemplate){
    "use strict";
    
        var SellerView = Backbone.View.extend({
        
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){
             this.$el.html(_.template(sSellerTemplate));
            return this;
        },
        destroy:function(){
        
        }
        
    });
    
    return SellerView;
        

    
});