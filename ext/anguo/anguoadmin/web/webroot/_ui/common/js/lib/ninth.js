/*
	ninth.js 
*/

(function(factory){
	"use strict";
	if (typeof define === 'function' && define.amd) {
		define(['jquery', 'Backbone'], factory);
	}
	else if(typeof exports === 'object') {
        
        var jquery = require('jquery'), Backbone = require('Backbone');
		factory(jquery,Backbone);
	}
	else {
		factory(window.jQuery, window.Backbone);
	}

}(function($, Backbone){
	"use strict";

    var Ninth = function(){
        this.VERSION = 0.1;
    };
    var _realBackbone = Ninth.prototype = Backbone.noConflict();
    var ninth = window.Backbone = new Ninth();
    
    
    
    
    return ninth;
}));