ACC.selector = {
	bindCountry : function(master) {
		var masterselector = $("#" + master);
		$.ajax({
			type : "GET",
			url : ACC.config.contextPath + "/productzone/getCountry",
			async : true,
			success : function(data) {
				masterselector.append("<option>--</option>");
				for (var int = 0; int < data.length; int++) {
					masterselector.append("<option value=" + data[int].isocode
							+ ">" + data[int].name + "</option>");
				}
			}
		});
	},

	bindRegion : function(master, slave, slave2, slave3) {
		var masterselector = $("#" + master);
		var slaveselector = $("#" + slave);
		var slaveselectorCity = $("#" + slave2);
		var slaveselectorDistrict = $("#" + slave3);
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

	bindCity : function(master, slave, slave2) {
		var masterselector = $("#" + master);
		var slaveselector = $("#" + slave);
		var slaveselector2 = $("#" + slave2);
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
					slaveselector2.html("");
					slaveselector.append("<option>--</option>");
					slaveselector2.append("<option>--</option>");
					for (var int = 0; int < data.length; int++) {
						slaveselector.append("<option value=" + data[int].code
								+ ">" + data[int].name + "</option>");
					}
				}
			});
		});

	},

	bindDistrict : function(master, slave) {
		var masterselector = $("#" + master);
		var slaveselector = $("#" + slave);
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
								+ ">" + data[int].name + "</option>");
					}
				}
			});
		});

	},

	bindAll : function(div) {
		ACC.selector.bindCountry(div + "_countrySelector");
		ACC.selector.bindRegion(div + "_countrySelector", div + "_regionSelector",
				div+"_citySelector", div+"_districtSelector");
		ACC.selector.bindCity(div+"_regionSelector", div+"_citySelector",
				div+"_districtSelector");
		ACC.selector.bindDistrict(div+"_citySelector", div+"_districtSelector");
	}
}

//$(document).ready(function() {
//	ACC.selector.bindAll("address");
//});
