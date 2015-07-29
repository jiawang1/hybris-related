define(['jquery','backbone', 'underscore', 'text!./../template/categoryTpl.html'],function($,Backbone,_, sCategoryTpl){
    "use strict";
    var CategoryView = Backbone.View.extend({
        $el: $("<div>"),
        render:function(){
           this.$el.html(_.template(sCategoryTpl));
           return this;
        }
    
    });
    return CategoryView;
});