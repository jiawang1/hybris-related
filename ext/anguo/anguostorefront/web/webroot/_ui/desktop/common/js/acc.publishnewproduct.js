ACC.publishnewproduct = {
	catSelected : '',
	catLv2Selected : '',
	newCat : false,
	productName : '',
	specification : '',
	madeIn : '',
	storageLocation : '',
	productYear : '',
	processMethod : '',
	packageUnit : '',
	huiFen : '',
	ganDu : '',
	containLiu : '',
	salesUnit : '',
	stockLevel : '',
	minSalesQuantity : '',
	minSaleQtds : [],
	prices : [],
	minSaleQtd1 : 0,
	price1 : 0,
	minSaleQtd2 : 0,
	price2 : 0,
	minSaleQtd3 : 0,
	price3 : 0,
	description : '',
	okToSubmit : true,
	warningMessage : '',

	resultSave : function(catSearchResItem) {
		var resultItem = $("." + catSearchResItem);
		resultItem.click(function() {
			resultItem.removeClass("currentSelected");
			resultItem.css('background', '#ffffff');
			$(this).css('background', '#dfd');
			$(this).addClass("currentSelected");
			ACC.publishnewproduct.catSelected = $(this).attr("catCode");
			if ($(this).attr("superCatCode") != null) {
				ACC.publishnewproduct.catLv2Selected = $(this).attr(
						"superCatCode");
			}
			$("#createCatP").remove();
			ACC.publishnewproduct.setCategory("catSelectionResultDiv");
		})
	},

	catUnsave : function(catUnsave) {
		var categoryUnsave = $("." + catUnsave);
		categoryUnsave
				.click(function() {
					$(".categorySelectorlv3").css('background', '#ffffff');
					if ($(this).parent().find("#createCatP").length == 0) {
						$(this)
								.parent()
								.append(
										'<p id="createCatP" style="margin-left : 10px">新建类目: <input type="text" id="newCatInput" /></p>');
					}
				})
	},

	categorySelectlv2 : function(catSelectClass) {
		var categorySelect = $("." + catSelectClass);
		categorySelect
				.click(function() {
					ACC.publishnewproduct.catLv2Selected = $(this).attr(
							"catCode");
					categorySelect.css('background', '#ffffff');
					$(this).css('background', '#dfd');
					categorySelect.removeClass("currentSelected");
					$(this).addClass("currentSelected");
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
																+ '" superCatCode="'
																+ data[int].superCategoryCode
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
			ACC.publishnewproduct.catSelected = $("#newCatInput").val();
			ACC.publishnewproduct.newCat = true;
		}
		messageDiv.empty();
		messageDiv.css('background', '#dfd');
		messageDiv.append('您选择的是 ： <span>'
				+ ACC.publishnewproduct.catLv2Selected + ' >> '
				+ ACC.publishnewproduct.catSelected + '</span>');
	},

	addPriceScale : function(priceScaleBtn, priceScale, priceLine) {
		var addPriceScaleBtn = $('#' + priceScaleBtn);
		var priceScaleDiv = $('#' + priceScale);
		addPriceScaleBtn
				.click(function() {
					var priceLineDivs = $('.' + priceLine);
					if (priceLineDivs.length < 3) {
						priceScaleDiv
								.append('<div class="priceLineDiv"><br> <input class="salesQuntityInput intInput" type="text" />公斤以上 '
										+ '<input type="text" class="doubleInput priceInput" style="display: inline" /> 元/公斤 '
										+ '<input type="button" class="deletePriceScaleBtn" value="删除" style="display: inline"></div>');
						ACC.publishnewproduct
								.deletePriceScale("deletePriceScaleBtn");
						ACC.publishnewproduct.intInputCheck("intInput");
						ACC.publishnewproduct.doubleInputCheck("doubleInput");
						ACC.publishnewproduct
								.saleQuantityCheck("salesQuntityInput");
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
			if ($(this).val().search(/^\d+$/g) != -1) {
				slave.val(master.val());
				slave.attr("readonly", "readonly");
				ACC.publishnewproduct.minSalesQuantity = master.val();
			}
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
			if ($(this).val().search(/^\d+$/g) == -1) {
				alert("库存量、起售量、采购数量只能填写整数");
				$(this).val("");
			}
		});
	},

	doubleInputCheck : function(inputClass) {
		var intInput = $("." + inputClass);
		intInput.change(function() {
			if ($(this).val().search(/^\d+(\.\d(\d?))?$/g) == -1) {
				alert("价格只能填写最多两位小数");
				$(this).val("");
			}
		});
	},

	saleQuantityCheck : function(inputClass) {
		var saleQuantity = $("." + inputClass);
		saleQuantity
				.change(function() {
					if (parseInt($(this).val()) < parseInt(ACC.publishnewproduct.minSalesQuantity)) {
						alert("采购数量必须大于起售量");
						$(this).val("");
					}
					var currentInput = $(this);
					var count = 0;
					saleQuantity.each(function() {
						if ($(this).val() == currentInput.val()) {
							count++;
						}
					})
					if (count > 1) {
						alert("已有针对此采购数量的价格");
						currentInput.val("");
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
	},

	getProductProperties : function() {
		ACC.publishnewproduct.productName = $("#productNameInput").val();
		if (ACC.publishnewproduct.productName == '') {
			ACC.publishnewproduct.warningMessage = ACC.publishnewproduct.warningMessage
					+ "请填写商品名称\n";
			ACC.publishnewproduct.okToSubmit = false;
		}
		ACC.publishnewproduct.specification = $("#productSpecificationInput")
				.val();
		if (ACC.publishnewproduct.specification == '') {
			ACC.publishnewproduct.warningMessage = ACC.publishnewproduct.warningMessage
					+ "请填写商品规格\n";
			ACC.publishnewproduct.okToSubmit = false;
		}
		ACC.publishnewproduct.madeIn = $("#productionAddress_districtSelector")
				.val();
		ACC.publishnewproduct.storageLocation = $(
				"#productionStorageAddress_districtSelector").val();
		if (ACC.publishnewproduct.storageLocation == '--') {
			ACC.publishnewproduct.warningMessage = ACC.publishnewproduct.warningMessage
					+ "请填写库存地，选择到区县\n";
			ACC.publishnewproduct.okToSubmit = false;
		}
		ACC.publishnewproduct.productYear = $("#productProduceYearInput").val();
		ACC.publishnewproduct.processMethod = $("#productProcessMethodSelector")
				.val();
		if (ACC.publishnewproduct.processMethod == "others") {
			ACC.publishnewproduct.processMethod = $("#productProcessMethodInput");
		}
		ACC.publishnewproduct.packageUnit = $("#packageUnitSelector").val();
		ACC.publishnewproduct.huiFen = $("#productHuifenSelector").val();
		if (ACC.publishnewproduct.huiFen == "others") {
			ACC.publishnewproduct.huifen = $("#productHuifenInput").val();
		}
		ACC.publishnewproduct.ganDu = $("#productGanduInput").val();
		ACC.publishnewproduct.containLiu = $("#productContainLiuInput").val();
		ACC.publishnewproduct.salesUnit = $("#unitSelector").val();
		ACC.publishnewproduct.stockLevel = $("#stockInput").val();
		if (ACC.publishnewproduct.stockLevel == '') {
			ACC.publishnewproduct.stockLevel = 0;
			ACC.publishnewproduct.warningMessage = ACC.publishnewproduct.warningMessage
					+ "请填写库存量\n";
			ACC.publishnewproduct.okToSubmit = false;
		}
		ACC.publishnewproduct.minSalesQuantity = $("#minSalesQuantityInput")
				.val();
		if (ACC.publishnewproduct.minSalesQuantity == '') {
			ACC.publishnewproduct.minSalesQuantity = 0;
			ACC.publishnewproduct.warningMessage = ACC.publishnewproduct.warningMessage
					+ "请填写起售量\n";
			ACC.publishnewproduct.okToSubmit = false;
		}
		var priceDiv = $(".priceLineDiv");
		var minSaleQtds = [];
		var prices = [];
		priceDiv.each(function() {
			var saleQuantity = $(this).children(".salesQuntityInput")[0].value;
			var price = $(this).children(".priceInput")[0].value;
			if (saleQuantity != '' && saleQuantity > 0) {
				minSaleQtds.push(saleQuantity);
			}
			if (price != '' && price > 0) {
				prices.push(price);
			}
		})
		ACC.publishnewproduct.minSaleQtd1 = $("#minPriceScaleQuantity1Input")
				.val();
		ACC.publishnewproduct.price1 = $("#priceScale1Input").val();
		if (minSaleQtds.length == 0
				|| prices.length == 0) {
			ACC.publishnewproduct.warningMessage = ACC.publishnewproduct.warningMessage
					+ "请给出至少一个阶梯售价\n";
			ACC.publishnewproduct.okToSubmit = false;
		}
		if (prices.length >= 2 && minSaleQtds.length >= 2) {
			ACC.publishnewproduct.minSaleQtd2 = minSaleQtds[1];
			ACC.publishnewproduct.price2 = prices[1];
		}
		if (prices.length == 3 && minSaleQtds.length == 3) {
			ACC.publishnewproduct.minSaleQtd3 = minSaleQtds[2];
			ACC.publishnewproduct.price3 = prices[2];
		}
		ACC.publishnewproduct.description = $("#descriptionEditor").val();
		if (ACC.publishnewproduct.description == '') {
			ACC.publishnewproduct.warningMessage = ACC.publishnewproduct.warningMessage
					+ "请填写商品描述\n";
			ACC.publishnewproduct.okToSubmit = false;
		}
		var acceptContract = $("#productAcceptAnguoContractInput");
		if (!acceptContract.prop("checked")) {
			ACC.publishnewproduct.warningMessage = ACC.publishnewproduct.warningMessage
					+ "请选择接受平台协议\n";
			ACC.publishnewproduct.okToSubmit = false;
		}
	},

	saveProduct : function(button) {
		var saveBtn = $("#" + button);
		saveBtn.click(function() {
			ACC.publishnewproduct.getProductProperties();
			ACC.publishnewproduct.okToSubmit = 0;
			ACC.publishnewproduct.warningMessage = '';
			$.ajax({
				type : "GET",
				url : ACC.config.contextPath + "/newproduct/saveNewProduct",
				async : true,
				traditional : true,
				data : {
					categoryCodeLv2 : ACC.publishnewproduct.catLv2Selected,
					categoryCodeLv3 : ACC.publishnewproduct.catSelected,
					newCat : ACC.publishnewproduct.newCat,
					productName : ACC.publishnewproduct.productName,
					specification : ACC.publishnewproduct.specification,
					madeIn : ACC.publishnewproduct.madeIn,
					storageLocation : ACC.publishnewproduct.storageLocation,
					productYear : ACC.publishnewproduct.productYear,
					processMethod : ACC.publishnewproduct.processMethod,
					packageUnit : ACC.publishnewproduct.packageUnit,
					huifen : ACC.publishnewproduct.huiFen,
					gandu : ACC.publishnewproduct.ganDu,
					containLiu : ACC.publishnewproduct.containLiu,
					salesUnit : ACC.publishnewproduct.salesUnit,
					stock : ACC.publishnewproduct.stockLevel,
					minSalesQuantity : ACC.publishnewproduct.minSalesQuantity,
					minSalesQtd1 : ACC.publishnewproduct.minSaleQtd1,
					price1 : ACC.publishnewproduct.price1,
					minSalesQtd2 : ACC.publishnewproduct.minSaleQtd2,
					price2 : ACC.publishnewproduct.price2,
					minSalesQtd3 : ACC.publishnewproduct.minSaleQtd3,
					price3 : ACC.publishnewproduct.price3,
					description : ACC.publishnewproduct.description
				},
				success : function(data) {
					alert(data);
				},
				error : function(data) {
					alert(data.responseText);
				}
			});
		});
	},

	submitProduct : function(button) {
		var saveBtn = $("#" + button);
		saveBtn
				.click(function() {
					ACC.publishnewproduct.getProductProperties();
					if (ACC.publishnewproduct.okToSubmit) {
						$
								.ajax({
									type : "GET",
									url : ACC.config.contextPath
											+ "/newproduct/submitNewProuct",
									async : true,
									data : {
										categoryCodeLv2 : ACC.publishnewproduct.catLv2Selected,
										categoryCodeLv3 : ACC.publishnewproduct.catSelected,
										newCat : ACC.publishnewproduct.newCat,
										productName : ACC.publishnewproduct.productName,
										specification : ACC.publishnewproduct.specification,
										madeIn : ACC.publishnewproduct.madeIn,
										storageLocation : ACC.publishnewproduct.storageLocation,
										productYear : ACC.publishnewproduct.productYear,
										processMethod : ACC.publishnewproduct.processMethod,
										packageUnit : ACC.publishnewproduct.packageUnit,
										huifen : ACC.publishnewproduct.huiFen,
										gandu : ACC.publishnewproduct.ganDu,
										containLiu : ACC.publishnewproduct.containLiu,
										salesUnit : ACC.publishnewproduct.salesUnit,
										stock : ACC.publishnewproduct.stockLevel,
										minSalesQuantity : ACC.publishnewproduct.minSalesQuantity,
										minSalesQtd1 : ACC.publishnewproduct.minSaleQtd1,
										price1 : ACC.publishnewproduct.price1,
										minSalesQtd2 : ACC.publishnewproduct.minSaleQtd2,
										price2 : ACC.publishnewproduct.price2,
										minSalesQtd3 : ACC.publishnewproduct.minSaleQtd3,
										price3 : ACC.publishnewproduct.price3,
										description : ACC.publishnewproduct.description
									},
									success : function(data) {
										alert(data);
									}
								});
					} else {
						alert(ACC.publishnewproduct.warningMessage);
						ACC.publishnewproduct.okToSubmit = true;
						ACC.publishnewproduct.warningMessage = '';
					}
				});
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
					"minPriceScaleQuantity1Input");
			ACC.publishnewproduct
					.displayTextInput("productProcessMethodSelector",
							"productProcessMethodInput")
			ACC.publishnewproduct.displayTextInput("productHuifenSelector",
					"productHuifenInput")
			ACC.publishnewproduct.intInputCheck("intInput");
			ACC.publishnewproduct.doubleInputCheck("doubleInput");
			ACC.publishnewproduct.saleQuantityCheck("salesQuntityInput");
			ACC.publishnewproduct.initYearPicker("productProduceYearInput");
			ACC.publishnewproduct.saveProduct("saveDraftBtn");
			ACC.publishnewproduct.submitProduct("saveAndPublishBtn");
		});
