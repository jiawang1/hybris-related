/*
	input list 
*/

(function(factory){
	"use strict";
	if (typeof define === 'function' && define.amd) {
		define(['jquery',], factory);
	}
	else if(typeof exports === 'object') {
		factory(require('jquery'));
	}
	else {
		factory(jQuery);
	}

}(function($){
	"use strict";

	var _nid = 0;

	var InputList = function(rootEl, options, data){

		if(Object.prototype.toString.call(data) !== "[object Array]"){
			console.error("parameter data must be array");
			return;
		}
		var that = this;
		var _options = $.extend({

		},options);

		this.$root = $(rootEl).addClass("input-list-section");

		var _$label = $("<span>").addClass("").text(options.label);
		var _$areaHeader = $("<div>").addClass("section-header").append(_$label);
		
		this.$root.append(_$areaHeader);

		$.each(data,function(index,item){
			that.$root.append(new _InputPiece(_options.attributes,item).$root);
		});

		this.$root.append(new _InputPiece().$root);

	};

	InputList.prototype.add = function(){

		this.$root.find(":last-child > .glyphicon-plus").removeClass("glyphicon glyphicon-plus");
		var _$newItem = new _InputPiece().hide();
		this.$root.append(_$newItem.$root);

		setTimeout();
		_$newItem.slideDown();

	}


	var _InputPiece = function(options, data){

		var _isNew = data === undefined;
		var _options = $.extend({
			'class':'form-control',
			'disabled': false
			
		},options);

		this.$root = $("<div>").addClass("input-group").attr("data-nid", ++_nid);
		var _$input = $("<input>").attr(_options);
		var _$action1 = $("<span>");
		if(_isNew){
			_$input.val(data);

			var _$add = $("<span>").addClass("glyphicon glyphicon-plus action-before");

			_$action1.addClass("glyphicon glyphicon-floppy-save action-after");

		}else{
			var _$add = $("<span>").addClass("action-before");
			_$input.attr('disabled', true);
			_$input.val(data);
			_$action1.addClass("glyphicon glyphicon-pencil action-after");
		}
		
		var _$action2 = $("<span>").addClass("glyphicon glyphicon-trash action-after");

		this.$root.append(_$add).append(_$input).append(_$action1).append(_$action2);

	};

	_InputPiece.prototype.hide = function(){
		return this.$root.addClass("hide");

	};

   var Plugin = function(options, data){

	   	return this.each(function(){
	   		new InputList(this, options, data);
	   		
	   	});
   };
  $.fn.inputList             = Plugin;
  $.fn.inputList.Constructor = InputList;



}));