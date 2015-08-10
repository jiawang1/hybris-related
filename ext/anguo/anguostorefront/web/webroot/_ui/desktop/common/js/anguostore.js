/**
 * 
 */
$(function(){
	checkApproveStatus();
	
	function checkApproveStatus(){
		

		if($("#fieldsNeedApprove").length === 0 || $("#approveStatus").length === 0)return;
		var fieldsNeedApprove = $needApprove.val().split(",");
		/* show * at the front of the need approve fields */
//		jQuery.each(fieldsNeedApprove, function(index, value) {
//		       console.log(this);
//		       $("#" + jQuery.trim(value) + "Star").show();
//		   });		
		
		if ($("#approveStatus").val() == "CREATE_WAIT") {
//TODO For test only, uncomment it for the product use.			
//			$('#anguoStoreDiv').find(':input').prop('disabled', true);
//			$('#anguoStoreDiv a').click(function(e) {
//			    e.preventDefault();
//			});
		}else if($("#approveStatus").val() == "MODIFY_WAIT"){
			jQuery.each(fieldsNeedApprove, function(index, value) {
			       console.log(this);
			       $("#" + jQuery.trim(value)).attr("readonly", "true");
			   });
		}else if($("#approveStatus").val() == "CREATE_NEW"){
			$("#anguoStoreRegistertimeTr").hide();
			$("#anguoStoreLevelTr").hide();
			$("#anguoStoreTemplateTr").hide();
			$("#anguoStorePlatformServiceTr").hide();
		}
		
	}
	
})