define(['jquery','backbone', 'underscore','text!../template/checkProductTpl.html'],function($,Backbone,_,sProductTemplate){
    "use strict";
    
    var CheckProductView = Backbone.View.extend({
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){
            this.$el.html(_.template(sProductTemplate));
            return this;
        },
        destroy:function(){
        
        }
    
    });
    
    return CheckProductView;
});