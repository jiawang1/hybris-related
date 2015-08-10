define(['jquery','backbone', 'underscore','text!../template/changeStoreTpl.html'],function($,Backbone,_,sStoreTemplate){
    "use strict";
    
    var ChangeStoreView = Backbone.View.extend({
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
    
    return ChangeStoreView;
});