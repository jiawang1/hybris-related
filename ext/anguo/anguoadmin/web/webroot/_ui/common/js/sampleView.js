define(['jquery','backbone', 'underscore','productModel','text!../template/sampleTpl.html'],function($,Backbone,_,ProductModel,sProductTemplate){
    "use strict";
    
    var SampleView = Backbone.View.extend({     /* create view class*/
        
        $el:$("<div>"),
        initialize:function(){                      /* constructor for this class*/

        },
        
        render:function(){   

        var that = this;                       /* render the view*/
//            _.templateSettings={
//
//                interpolate: /\{\{(.+?)\}\}/g
//            };

           $.when(new ProductModel().fetch()).done(function(model){
        	   
              that.$el.html(_.template(sProductTemplate,{"model": model}));

              $(".input-list",that.$el).inputList({"label":'category'},['a','b']);
             

           });

           return this;
        }
    
    });
    
    return SampleView;                           /* expose class */
});