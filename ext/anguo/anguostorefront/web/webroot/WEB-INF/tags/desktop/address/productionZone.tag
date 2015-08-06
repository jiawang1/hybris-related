<%@ attribute name="divName" required="true" type="java.lang.String"%>

<div id="${divName }">
	<select name="countrySelector" id="${divName }_countrySelector">
	</select> <select name="regionSelector" id="${divName }_regionSelector">
		<option>--</option>
	</select> <select name="citySelector" id="${divName }_citySelector">
		<option>--</option>
	</select> <select name="districtSelector" id="${divName }_districtSelector">
		<option>--</option>
	</select>
</div>

<script>
	$(document).ready(function() {
		ACC.selector.bindAll("${divName}");
	});
</script>