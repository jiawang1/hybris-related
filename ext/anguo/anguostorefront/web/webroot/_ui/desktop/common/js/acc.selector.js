ACC.selector = {
	bindRegion : function(master, slave) {
		var masterselector = $("#countrySelector");
		var slaveselector = $("#regionSelector");
		var slaveselectorCity = $("#citySelector");
		var slaveselectorDistrict = $("#districtSelector");
		masterselector.change(function() {
			var country = masterselector.val();
			$.ajax({
				type : "GET",
				url : ACC.config.contextPath + "/productzone/getRegion",
				async : true,
				data : {
					country : country
				},
				success : function(data) {
					slaveselector.html("");
					slaveselectorCity.html("");
					slaveselectorDistrict.html("");
					slaveselector.append("<option>--</option>");
					slaveselectorCity.append("<option>--</option>");
					slaveselectorDistrict.append("<option>--</option>");
					for (var int = 0; int < data.length; int++) {
						slaveselector.append("<option value="
								+ data[int].isocode + ">" + data[int].name
								+ "</option>");
					}
				}
			});
		});

	},

	bindCity : function(master, slave) {
		var masterselector = $("#regionSelector");
		var slaveselector = $("#citySelector");
		var slaveselectorDistrict = $("#districtSelector");
		masterselector.change(function() {
			var region = masterselector.val();
			$.ajax({
				type : "GET",
				url : ACC.config.contextPath + "/productzone/getCity",
				async : true,
				data : {
					region : region
				},
				success : function(data) {
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
	
	bindDistrict : function(master, slave) {
		var masterselector = $("#citySelector");
		var slaveselector = $("#districtSelector");
		masterselector.change(function() {
			var city = masterselector.val();
			$.ajax({
				type : "GET",
				url : ACC.config.contextPath + "/productzone/getDistrict",
				async : true,
				data : {
					city : city
				},
				success : function(data) {
					slaveselector.html("");
					slaveselector.append("<option>--</option>");
					for (var int = 0; int < data.length; int++) {
						slaveselector.append("<option value=" + data[int].code
								+ ">" + data[int].districtName + "</option>");
					}
				}
			});
		});

	},
	
	bindAll : function() {
		ACC.selector.bindRegion();
		ACC.selector.bindCity();
		ACC.selector.bindDistrict();
	}
}

$(document).ready(function() {
	ACC.selector.bindAll();
});
