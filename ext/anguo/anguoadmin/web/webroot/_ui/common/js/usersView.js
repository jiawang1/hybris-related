define(['jquery','backbone','underscore','text!../template/usersTpl.html'],function($,Backbone,_,sUserTemplate){
    "use strict";
    
    var UserView = Backbone.View.extend({
        
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){
             this.$el.html(_.template(sUserTemplate));
        }
        
    });
    
    return UserView;
    
});