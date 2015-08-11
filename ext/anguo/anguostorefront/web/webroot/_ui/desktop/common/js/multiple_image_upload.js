/**
 * 
 */
$(document).ready(function(){
	var settings = {
	    url: "/fileUpload",
	    method: "POST",
	    fileName: "uploadFiles",
	    multiple: true,
	    autoSubmit: true,
	    allowedTypes:"jpg,png,bmp,jpeg",
	    
	    maxFileSize:1024*1024*2,
	    dragDropStr :"",
	    dragDropContainerClass :"upload_button",
	    statusBarWidth : '100%',
	    showFileCounter:false,
	    showStatusAfterSuccess: false,
	    dynamicFormData: function()
	    {
	    	var data ={ CSRFToken:$("#CSRFToken").val()}
	    	return data;
	    },
	    onSelect:function(files)
		{
			//$("#docfileuploader").parent().find("input[name=upload_btn]").val(files[0].name);
		    return true; 
		},
	    onSuccess:function(files,data,xhr)
	    {
	    	i = 0;
	    	if(data.status == 1){
	    		addMultiFile($("#docfileuploader"), data);
            i = i+1;
	    	}else{
				$("#errorMsg").text(data.msg);
				$("#errorMsg").show();
	    	}
	    },
	    onError: function(files,status,errMsg)
	    {       
	    }
	}
	$("#docfileuploader").uploadFile(settings);
	
	if($(".fileAction")){
		$(".fileAction").on('click', removeFile);
	}
});