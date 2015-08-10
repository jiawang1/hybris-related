define(['jquery','backbone', 'underscore','text!../template/recProductTpl.html'],function($,Backbone,_,sProductTemplate){
    "use strict";
    
    var RecProductView = Backbone.View.extend({
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
    
    return RecProductView;
});