ACC.categorysearch = {
	catSelected : '',
	catLv2Selected : '',

	resultSave : function(catSearchResItem) {
		var resultItem = $("." + catSearchResItem);
		resultItem.click(function() {
			resultItem.css('background', '#ffffff');
			$(this).css('background', '#e0ffff');
			ACC.categorysearch.catSelectd = $(this).attr("catCode");
			$("#createCatP").remove();
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
					ACC.categorysearch.catLv2Selectd = $(this).attr("catCode");
					categorySelect.css('background', '#ffffff');
					$(this).css('background', '#e0ffff');
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
									ACC.categorysearch
											.resultSave("categorySelectorlv3");
									$("#categoryTreeDivlv3")
											.append(
													'<li style="list-style:none; margin: 10px" class="unsavedCat" catCode="unsaved">未收录</li>');
									ACC.categorysearch.catUnsave("unsavedCat");
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
//		searchButton
//				.click(function() {
		searchInput.keyup(function() {
					var keyword = searchInput.val();
//					if (keyword.length == 0) {
//						alert("请输入搜索关键字，如人参");
//					} else {
					if (keyword.length != 0)
					{
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

	setCategory : function(button) {
		var selectButton = $("#" + button);
		selectButton.click(function() {
			if ($("#newCatInput").val() != null) {
				ACC.categorysearch.catSelectd = $("#newCatInput").val();
			}
			$.ajax({
				type : "GET",
				url : ACC.config.contextPath
						+ "/selectcategory/getCategorySelected",
				async : true,
				data : {
					categoryCode : ACC.categorysearch.catSelectd,
					categoryLv2Code : ACC.categorysearch.catLv2Selectd
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
			ACC.categorysearch.categorySelectlv2("categorySelectorlv2");
			ACC.categorysearch.setCategory("catDefineBtn");
		});
