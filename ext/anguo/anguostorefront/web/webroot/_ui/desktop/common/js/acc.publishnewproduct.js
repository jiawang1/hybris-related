ACC.publishnewproduct = {
	catSelected : '',
	catLv2Selected : '',
	minSalesQuantity : 0,

	resultSave : function(catSearchResItem) {
		var resultItem = $("." + catSearchResItem);
		resultItem.click(function() {
			resultItem.css('background', '#ffffff');
			$(this).css('background', '#dfd');
			ACC.publishnewproduct.catSelectd = $(this).attr("catCode");
			$("#createCatP").remove();
			ACC.publishnewproduct.setCategory("catSelectionResultDiv");
		})
	},

	catUnsave : function(catUnsave) {
		var categoryUnsave = $("." + catUnsave);
		categoryUnsave
				.click(function() {
					$(".categorySelectorlv3").css('background', '#ffffff');
					$(this)
							.parent()
							.append(
									'<p id="createCatP" style="margin-left : 10px">新建类目: <input type="text" id="newCatInput" /></p>');
				})
	},

	categorySelectlv2 : function(catSelectClass) {
		var categorySelect = $("." + catSelectClass);
		categorySelect
				.click(function() {
					ACC.publishnewproduct.catLv2Selectd = $(this).attr(
							"catCode");
					categorySelect.css('background', '#ffffff');
					$(this).css('background', '#dfd');
					var catCode = $(this).attr("catCode");
					$
							.ajax({
								type : "GET",
								url : ACC.config.contextPath
										+ "/newproduct/getSubCategory",
								async : true,
								data : {
									categoryCode : catCode
								},
								success : function(data) {
									$("#categoryTreeDivlv3").empty();
									for (var int = 0; int < data.length; int++) {
										$("#categoryTreeDivlv3")
												.append(
														'<li style="list-style:none; margin: 10px" class="categorySelectorlv3" catCode="'
																+ data[int].id
																+ '">'
																+ data[int].text
																+ '</li>');
									}
									ACC.publishnewproduct
											.resultSave("categorySelectorlv3");
									$("#categoryTreeDivlv3")
											.append(
													'<li style="list-style:none; margin: 10px" class="unsavedCat" catCode="unsaved">未收录</li>');
									ACC.publishnewproduct
											.catUnsave("unsavedCat");
								}
							});
				})
	},

	searchCategory : function(input, button, resdiv, treediv, searchdiv) {
		var searchButton = $("#" + button);
		var searchInput = $("#" + input);
		var resultDiv = $("#" + resdiv);
		var catTreeDiv = $("#" + treediv);
		var searchDiv = $("#" + searchdiv);
		// searchButton
		// .click(function() {
		searchInput
				.keyup(function() {
					var keyword = searchInput.val();
					// if (keyword.length == 0) {
					// alert("请输入搜索关键字，如人参");
					// } else {
					if (keyword.length != 0) {
						$
								.ajax({
									type : "GET",
									url : ACC.config.contextPath
											+ "/newproduct/searchCategory",
									async : true,
									data : {
										keyword : keyword
									},
									success : function(data) {
										resultDiv.empty();
										if (data.length > 0) {
											for (var int = 0; int < data.length; int++) {
												resultDiv
														.append('<li class="catSearchResItem" style="list-style:none; margin: 10px" catCode="'
																+ data[int].code
																+ '">'
																+ data[int].categoryPath
																+ '</li>');
											}
											ACC.publishnewproduct
													.resultSave("catSearchResItem");
										} else if (keyword.length > 0) {
											resultDiv
													.append('<span>对不起，没有找到相应的目录</span>')
										}
									}
								});
						catTreeDiv.css('display', 'none');
						searchDiv.css('display', 'block');
					}
				});
	},

	displayTree : function(button, treediv, searchdiv) {
		var backButton = $("#" + button);
		var catTreeDiv = $("#" + treediv);
		var searchDiv = $("#" + searchdiv);
		backButton.click(function() {
			catTreeDiv.css('display', 'block');
			searchDiv.css('display', 'none');
		});
	},

	setCategory : function(displayDiv) {
		var messageDiv = $("#" + displayDiv);
		if ($("#newCatInput").val() != null) {
			ACC.publishnewproduct.catSelectd = $("#newCatInput").val();
		}
		$.ajax({
			type : "GET",
			url : ACC.config.contextPath + "/newproduct/getCategorySelected",
			async : true,
			data : {
				categoryCode : ACC.publishnewproduct.catSelectd,
				categoryLv2Code : ACC.publishnewproduct.catLv2Selectd
			},
			success : function(data) {
				messageDiv.css('background', '#dfd');
				messageDiv.append('<span>' + data + '</span>');
			}
		})
	},

	addPriceScale : function(priceScaleBtn, priceScale, priceLine) {
		var addPriceScaleBtn = $('#' + priceScaleBtn);
		var priceScaleDiv = $('#' + priceScale);
		addPriceScaleBtn
				.click(function() {
					var priceLineDivs = $('.' + priceLine);
					if (priceLineDivs.length < 3) {
						priceScaleDiv
								.append('<div class="priceLineDiv"><br> <input class="salesQuntityInput" type="text" />公斤以上 '
										+ '<input type="text" style="display: inline" /> 元/公斤 '
										+ '<input type="button" class="deletePriceScaleBtn" value="删除" style="display: inline"></div>');
						ACC.publishnewproduct
								.deletePriceScale("deletePriceScaleBtn");
					} else {
						alert("最多可设置3个阶梯售价");
					}
				});
	},

	deletePriceScale : function(deletePriceScaleBtn) {
		var addPriceScaleBtn = $('.' + deletePriceScaleBtn);
		addPriceScaleBtn.click(function() {
			$(this).parent().remove();
		});
	},

	copyInput : function(masterInput, slaveInput) {
		var master = $('#' + masterInput);
		var slave = $('#' + slaveInput);
		master.change(function() {
			slave.val(master.val());
			ACC.publishnewproduct.minSalesQuantity = master.val();
		});
	},

	displayTextInput : function(master, selector, id) {
		var masterCell = $('#' + master);
		var optionSelector = $('#' + selector);
		optionSelector.change(function() {
			if (optionSelector.val() == "others") {
				optionSelector.parent().append(
						'<input type="text" id="' + id
								+ '" style="display: inline" />');
			} else {
				var slaveInput = $('#' + id);
				slaveInput.remove();
			}
		});
	},

	intInputCheck : function(inputClass) {
		var intInput = $("." + inputClass);
		intInput.change(function() {
			// if ($(this).val().search(/^\d+$/g) == -1) {
			// // $(this).parent().append('<span id="'+"test"+'ErrorMessage"
			// // style="color : red; display:block">只能为整数</span>')
			// alert("库存量、起售量、采购数量只能填写整数");
			// }
			$(this).validate();
		});
	},

	saleQuantityCheck : function(inputClass) {
		var saleQuantity = $("." + inputClass);
		saleQuantity.change(function() {
			if ($(this).val() < ACC.publishnewproduct.minSalesQuantity) {
				alert("采购数量必须大于起售量");
			}
		});
	},

	initYearPicker : function(yearPickerId) {
		var yearPicker = $("#" + yearPickerId);
		for (i = new Date().getFullYear(); i >= 1900; i--) {
			yearPicker.append($('<option />').val(i).html(i));
		}
		// var yearPicker = $("#" + yearPickerId);
		// $("#productProduceYearInput").DatePicker({
		// flat: true,
		// date: '2008-07-31',
		// current: '2008-07-31',
		// calendars: 1,
		// starts: 1
		// });
	}
}

$(document).ready(
		function() {
			ACC.publishnewproduct.searchCategory("keywordInput",
					"catSearchBtn", "resultDiv", "categoryTreeDiv",
					"searchCatResDiv");
			ACC.publishnewproduct.displayTree("backToCatTreeBtn",
					"categoryTreeDiv", "searchCatResDiv");
			ACC.publishnewproduct.categorySelectlv2("categorySelectorlv2");
			ACC.publishnewproduct.addPriceScale("addPriceScaleBtn",
					"priceScalesDiv", "priceLineDiv");
			ACC.publishnewproduct.deletePriceScale("deletePriceScaleBtn");
			ACC.publishnewproduct.copyInput("minSalesQuantityInput",
					"minPriceScaleQuantityInput");
			ACC.publishnewproduct
					.displayTextInput("productProcessMethodSelector",
							"productProcessMethodInput")
			ACC.publishnewproduct.displayTextInput("productHuifenSelector",
					"productHuifenInput")
			ACC.publishnewproduct.intInputCheck("intInput");
			ACC.publishnewproduct.saleQuantityCheck("salesQuntityInput");
			ACC.publishnewproduct.initYearPicker("productProduceYearInput");
		});
