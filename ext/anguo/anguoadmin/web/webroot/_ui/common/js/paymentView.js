define(['jquery','backbone', 'underscore','text!../template/paymentTpl.html'],function($,Backbone,_,sPaymentTemplate){
    "use strict";
    
        var PaymentView = Backbone.View.extend({
        
            $el:$("<div>"),
            initialize:function(){                      /* constructor for this class*/

            },

            render:function(){
                this.$el.html(_.template(sPaymentTemplate));
                return this;
            },

            destroy:function(){

            }
        
        });
    
    return PaymentView;
});