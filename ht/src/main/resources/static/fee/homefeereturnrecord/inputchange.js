

var feeNo = $("#feeno").val();
$("#feeno").on("change", function(event) {
	var feeNo = $("#feeno").val();
	if (feeNo) {
		$.getJSON("http://127.0.0.1:8080/fee/homefeereturnrecord/getReturnAmount", {
			feeno : feeNo
		}, function(data) {
			if(data==-9999){
				BootstrapDialog.show({
		            message:"供热序号不存在！"
		        });
			}else{
				$("#amount").val(data);
			}
	
		});
	} else {
		$("#amount").val(0.00);
	}
});