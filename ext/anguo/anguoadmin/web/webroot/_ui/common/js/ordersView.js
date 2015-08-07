define(['jquery','backbone', 'underscore','text!../template/orderTpl.html'],function($,Backbone,_,sOrderTemplate){
    "use strict";
    
        
    var OrderView = Backbone.View.extend({
        
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){
            this.$el.html(_.template(sOrderTemplate));
            return this;
        },
        
        destroy:function(){
        
        }
        
    });
    
    return OrderView;
});