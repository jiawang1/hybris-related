ACC.categorysearch = {
	catSelected : '',

	resultSave : function(catSearchResItem) {
		var resultItem = $("." + catSearchResItem);
		resultItem.click(function() {
			ACC.categorysearch.catSelectd = $(this).attr("catCode");
		})
	},

	categorySelectLev1 : function(catSelectClass) {
		var categorySelect = $("." + catSelectClass);
		categorySelect
				.click(function() {
					var catCode = $(this).attr("catCode");
					$
							.ajax({
								type : "GET",
								url : ACC.config.contextPath
										+ "/selectcategory/getSubCategory",
								async : true,
								data : {
									categoryCode : catCode
								},
								success : function(data) {
									$("#categoryTreeDivLev2").empty();
									for (var int = 0; int < data.length; int++) {
										$("#categoryTreeDivLev2")
												.append(
														'<li style="list-style:none; margin: 10px" class="categorySelectorLev2" catCode="'
																+ data[int].id
																+ '">'
																+ data[int].text
																+ '</li>');
									}
									ACC.categorysearch
											.categorySelectLev2("categorySelectorLev2");
								}
							});
				})
	},

	categorySelectLev2 : function(catSelectClass) {
		var categorySelect = $("." + catSelectClass);
		categorySelect
				.click(function() {
					var catCode = $(this).attr("catCode");
					$
							.ajax({
								type : "GET",
								url : ACC.config.contextPath
										+ "/selectcategory/getSubCategory",
								async : true,
								data : {
									categoryCode : catCode
								},
								success : function(data) {
									$("#categoryTreeDivLev3").empty();
									for (var int = 0; int < data.length; int++) {
										$("#categoryTreeDivLev3")
												.append(
														'<li style="list-style:none; margin: 10px" class="categorySelectorLev3" catCode="'
																+ data[int].id
																+ '">'
																+ data[int].text
																+ '</li>');
									}
									ACC.categorysearch
											.resultSave("categorySelectorLev3");
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
		searchButton
				.click(function() {
					var keyword = searchInput.val();
					$
							.ajax({
								type : "GET",
								url : ACC.config.contextPath
										+ "/selectcategory/searchCategory",
								async : true,
								data : {
									keyword : keyword
								},
								success : function(data) {
									resultDiv.empty();
									for (var int = 0; int < data.length; int++) {
										resultDiv
												.append('<li class="catSearchResItem" style="list-style:none; margin: 10px" catCode="'
														+ data[int].code
														+ '">'
														+ data[int].categoryPath
														+ '</li>');
									}
									ACC.categorysearch
											.resultSave("catSearchResItem");
								}
							});
					catTreeDiv.css('display', 'none');
					searchDiv.css('display', 'block');
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

	setCategory : function(button) {
		var selectButton = $("#" + button);
		selectButton.click(function() {
			$.ajax({
				type : "GET",
				url : ACC.config.contextPath
						+ "/selectcategory/getCategorySelected",
				async : true,
				data : {
					categoryCode : ACC.categorysearch.catSelectd
				},
				success : function(data) {
					alert(data);
				}
			})
		})
	}
}

$(document).ready(
		function() {
			ACC.categorysearch.searchCategory("keywordInput", "catSearchBtn",
					"resultDiv", "categoryTreeDiv", "searchCatResDiv");
			ACC.categorysearch.displayTree("backToCatTreeBtn",
					"categoryTreeDiv", "searchCatResDiv");
			ACC.categorysearch.categorySelectLev1("categorySelectorLev1");
			ACC.categorysearch.setCategory("catDefineBtn");
		});
