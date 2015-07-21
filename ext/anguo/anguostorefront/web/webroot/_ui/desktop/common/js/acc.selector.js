ACC.selector = {
	bindCity : function(master,slave) {
		var masterselector = $("#regionSelector");
		var slaveselector = $("#citySelector");
		var slaveselectorDistrict = $("#districtSelector");
		masterselector.change(function() {
			var region = masterselector.val();
			$.ajax({
				type:"GET",
				url:ACC.config.contextPath+"/productzone/getCity",
				async: true,
				data:{region:region},
				success: function(data){
					slaveselector.html("");
					slaveselectorDistrict.html("");
					slaveselector.append("<option>--</option>");
					slaveselectorDistrict.append("<option>--</option>");
					for (var int = 0; int < data.length; int++) {
						slaveselector.append("<option value=" + data[int].code
								+ ">" + data[int].cityName + "</option>");
					}
				}
			});
		});

	},
	bindDistrict : function(master,slave) {
		var masterselector = $("#citySelector");
		var slaveselector = $("#districtSelector");
		masterselector.change(function() {
			var city = masterselector.val();
			$.ajax({
				type:"GET",
				url:ACC.config.contextPath+"/productzone/getDistrict",
				async: true,
				data:{city:city},
				success: function(data){
					slaveselector.html("");
					slaveselector.append("<option>--</option>");
					for (var int = 0; int < data.length; int++) {
						slaveselector.append("<option value="+ data[int].code +">" +data[int].districtName + "</option>");
					}
				}
			});
		});

	},
	bindAll : function() {
		ACC.selector.bindCity();
		ACC.selector.bindDistrict();
	}
}

$(document).ready(function() {
	ACC.selector.bindAll();
});
