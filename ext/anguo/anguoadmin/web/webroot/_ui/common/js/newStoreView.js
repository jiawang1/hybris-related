define(['jquery','backbone', 'underscore','text!../template/newStoreTpl.html'],function($,Backbone,_,sStoreTemplate){
    "use strict";
    
    var NewStoreView = Backbone.View.extend({
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){
            this.$el.html(_.template(sStoreTemplate));
            return this;
        },
        destroy:function(){
        
        }
    
    });
    
    return NewStoreView;
});