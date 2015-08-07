define(['jquery','backbone', 'underscore',"text!./../template/navigationTpl.html"],function($,Backbone,_,sTemplate){
    "use strict";
    
    var NavigationMenu = Backbone.View.extend({
        $el:$("<ul>"),
        initialize: function(){
            
            this.$el.on("click", ">ul",this.menuClickHandler);

        },
        
        render:function(){
            this.$el.html(_.template(sTemplate,{"data":[
                     {"label": "Products", "link":"#products"},
                     {"label": "Category", "link":"#category"},
                     {"label": "Others", "link":"#others"},
                     {"label": "ProductList", "link":"#productList"}
                     ]}));
            return this;
        },
        
        menuClickHandler:function(e){
            
            e = e || window.event;
            var $element = $(e.target);
            
           function findTarget($element, finalDom){
            
               if($element[0] == finalDom) return;
               if($element.attr("data-link")){
                    return $element;
               }else{
                    return findTarget($element.parent());
               }
           }
            
            if($element.hasClass("glyphicon")){
                var $subFrame =  $element.parent().next();
                
                if($element.hasClass("glyphicon-plus")){
                    $element.removeClass("glyphicon-plus").addClass("glyphicon-minus");
//                    $subFrame.removeClass("relayout-hide");
                    $subFrame.slideDown("fast");
                }else{
                    $element.removeClass("glyphicon-minus").addClass("glyphicon-plus");
//                    $subFrame.addClass("relayout-hide");
                    $subFrame.slideUp("fast");
                    $subFrame.find("li.active").removeClass("active");
                }
                
                return false;
            }
            
            var $target = findTarget($(e.target), this);
            
            if(!$target) return false;

            $target.siblings(".active").removeClass("active");
            $target.addClass("active");
            
            
            require(["applicationRouter"], function(oRouter){
                 var targetLink = $target.attr("data-link");
                 
                targetLink&&oRouter.navigate(targetLink,{trigger: true});
                
            });
        }
        
        
    });
    
    return NavigationMenu;
});