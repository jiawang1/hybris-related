define(['jquery','backbone', 'underscore','text!../template/enterpriseTpl.html'],function($,Backbone,_,sEnterPriseTemplate){
    "use strict";
    
        var EnterpriseView = Backbone.View.extend({
        
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){
             this.$el.html(_.template(sEnterPriseTemplate));
            return this;
        },
        destroy:function(){
        
        }
        
    });
    
    return EnterpriseView;
        

    
});