/**
 * 模块：供热缴费管理
 * 小区管理的前端控制JS
 * 作者：罗妙忠
 * 
 */

$(function(){
	var rows=5;		  //每页多少条数据
	var page=1;       //当前页数
	var pageCount=0;  //缴费总页数
	var no =-1;
	var recordno =-1;
	var host = "http://127.0.0.1:8080";
	var status ="";
	var feeno = 0;
	
	function getListInfo(){
		//取得列表，分页模式
		$.getJSON(host+"/fee/homestoprecord/getAllByListWithPages",{page:page,rows:rows},function(data){
				//显示居民缴费总条数和页数
				$("span#count").html(data.count);
				$("span#pagecount").html(page+"/"+data.pageCount);
				pageCount=data.pageCount;
				//显示列表
				$("table#HomeFeeStopRecordTable tbody").html("");
				
				for(var i=0;i<data.list.length;i++){
					var stopstatus = "";
					if(data.list[i].stopstatus=="N"){
						stopstatus = "处理中...";
					}else{
						stopstatus = "处理完毕";
					}
					var tr="<tr id='"+data.list[i].recordno+"' data-status='"+data.list[i].stopstatus+
					"'><td>"+data.list[i].recordno+"</td><td>"+data.list[i].stoparea+"</td><td>"
					+data.list[i].stopreason+"</td><td>"+data.list[i].stopdate+"</td><td>"
					+data.list[i].stopperson+"</td><td data='status'>"
					+stopstatus+"</td><td>"+data.list[i].stopdesc+"</td></tr>";					
					
					$("table#HomeFeeStopRecordTable tbody").append(tr);
				}
				//定义表格行的点击时间，取得选择的退费记录ID
				$("table#HomeFeeStopRecordTable tbody tr").off().on("click",function(){
					no=$(this).attr("id");

					stopstatus = $("tr#stopstatus").val();

					status = $(this).data("status");
					 $.getJSON(host+"/fee/homestoprecord/getByIdWithHomeFee",{recordno:no},function(data){
							feeno = data.model.homeFeeModel.feeno;
							//alert(feeNo);
						 })

					$("table#HomeFeeStopRecordTable tbody tr").css("background-color","#FFFFFF");
					$(this).css("background-color","#d6d0d08c");
				});
				
				
		 });
			
	}
	
	//定义分页导航链接处理事件
	$("div#page_nav a").on("click",function(event){
			  var action=$(this).attr("href");
			  event.preventDefault();
			  switch(action){
			  	case "top":
			  		page=1;
			  		getListInfo();
			  		break;
			  	case "pre":
			  		if(page>1){
			  			page=page-1;
			  			getListInfo();
			  		}
			  		break;
			  	case "next":
			  		if(page<pageCount){
			  			page=page+1;
			  			getListInfo();
			  		}
			  		break;
			  	case "last":
			  		page=pageCount;
			  		getListInfo();
			  		break;
			  }
		
	});
	
	//初始调用取得分页列表数据
	getListInfo();
	
	//==================================================
	
	$("a#StopRecordStatusLink").off().on("click",function(event){
		if(no==-1){
			BootstrapDialog.show({
	            title: '停供操作信息',
	            message:"请选择要修改的停供记录"
	        });
		}
		else {	
			 $("div#StopRecordDialogArea").load("fee/homefee/changeheatingstatus.html",function(){
					
				$.getJSON(host+"/fee/homefee/get",{feeno:feeno},function(data){
					if(data.status=="OK"){
						//console.log("feeNo: "+feeNo);
						//console.log("data.model.home:"+data.model.home);
						$("span#homename").html(data.model.home.homename);
						$("span#neighbourhood").html(data.model.home.neighbourhood.hoodname);
						$("span#heatingarea").html(data.model.home.heatingarea);
						$("span#agreefee").html(data.model.agreefee);
						$("span#actualfee").html(data.model.actualfee);
						$("span#debtfee").html(data.model.debtfee);
						$("span#feedesc").html(data.model.feedesc);
						$("input[value='"+data.model.home.heatingstatus+"']").prop('checked',true);
						$("input[name='homeno']").val(data.model.home.homeno);
					}
				});

				$("div#StopRecordDialogArea" ).dialog({
					title:"供热状态信息操作",
					width:600
				});		
				//拦截表单提交
				$("form#ChangeHeatingStatusForm").ajaxForm(function(result){			
					if(result.status=="OK"){
						var val = $('input[name="heatingstatus"]:checked').val(); 

						if(val=="正常"){
							val = "Y";
						}else{
							val = "N";
						}
						
						 //修改此页面的stopstatus状态
							
						$.getJSON(host+"/fee/homestoprecord/modify",{recordno:no,stopstatus:val},function(){
							getListInfo();
						});
						
					}
					BootstrapDialog.show({
			            title: '供热状态操作信息',
			            message:result.message
			        });
					$("div#StopRecordDialogArea" ).dialog( "close" );
					$("div#StopRecordDialogArea" ).dialog( "destroy" );
					$("div#StopRecordDialogArea").html("");	
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#StopRecordDialogArea" ).dialog( "close" );
					$( "div#StopRecordDialogArea" ).dialog( "destroy" );
					$( "div#StopRecordDialogArea").html("");
				});			
			});
		}				
	});
	
	
	//点击增加链接处理，嵌入add.html
	$("a#StopRecordAddLink").off().on("click",function(event){
		$("div#StopRecordDialogArea").load("fee/homestoprecord/add.html",function(){
			$("div#StopRecordDialogArea" ).dialog({
				title:"增加居民停供记录",
				width:400
			});

			$("form#HomeFeeStopRecordAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					getListInfo(); 
				}
				BootstrapDialog.show({
		            title: '停供操作信息',
		            message:result.message
		        });
				$("div#StopRecordDialogArea" ).dialog( "close" );
				$("div#StopRecordDialogArea" ).dialog( "destroy" );
				$("div#StopRecordDialogArea").html("");
				
			});
		});
	});
	
	
	
	//点击修改按钮事件处理
	$("a#StopRecordModifyLink").off().on("click",function(event){
		
		if(status!="N"){
			BootstrapDialog.show({
				message:"已完成处理，无法修改！"
			});
			return;
		}
		if(no==-1){
			BootstrapDialog.show({
	            title: '停供操作信息',
	            message:"请选择要修改的停供记录"
	        });
		}
		else {
			$("div#StopRecordDialogArea").load("fee/homestoprecord/modify.html",function(){
				//取得居民停供记录
				$.getJSON("http://127.0.0.1:8080/fee/homestoprecord/getById",{recordno:no},function(data){
					
					if(data.status=="OK"){
						$("input[name='recordno']").val(no);
						$("input[name='stopstatus']").val(data.model.stopstatus);
						$("input[name='stopreason']").val(data.model.stopreason);
						$("input[name='stopdate']").val(data.model.stopdate);
						$("input[name='stopperson']").val(data.model.stopperson);
						$("input[name='stopdesc']").val(data.model.stopdesc);
					}
				});
				
				$("div#StopRecordDialogArea" ).dialog({
					title:"居民停供记录修改",
					width:600
				});
				//拦截表单提交
				$("form#HomeStopRecordModifyForm").ajaxForm(function(result){	
					if(result.status=="OK"){
						getListInfo(); 
					}

					BootstrapDialog.show({
			            title: '记录操作信息',
			            message:result.message
			        });
					$("div#StopRecordDialogArea" ).dialog( "close" );
					$("div#StopRecordDialogArea" ).dialog( "destroy" );
					$("div#StopRecordDialogArea").html("");
				});
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#StopRecordDialogArea" ).dialog( "close" );
					$( "div#StopRecordDialogArea" ).dialog( "destroy" );
					$("div#StopRecordDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#StopRecordDeleteLink").off().on("click",function(event){
		if(status!="N"){
			BootstrapDialog.show({
				message:"已完成处理，无法修改！"
			})
			return;
		}
		if(no==-1){
			BootstrapDialog.show({
	            title: '记录操作信息',
	            message:"请选择要删除的缴费记录"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除此条缴费记录么?', function(result){
				if(result) {
					$.getJSON("http://127.0.0.1:8080/fee/homestoprecord/delete",{recordno:no},function(result){
						if(result.status=="OK"){
							getListInfo(); 
						}
						BootstrapDialog.show({
							title: '停供操作信息',
							message:result.message
						});
					});
				}
			});
				
	
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#PayRecordSelectLink").off().on("click",function(event){
		
		if(no==-1){
			BootstrapDialog.show({
	            title: '记录操作信息',
	            message:"请选择要查看的缴费记录"
	        });
		}
		else{
			$("div#PayRecordDialogArea").load("fee/homefeepayrecord/detail.html",function(){
				//取得选择的部门
				$.getJSON("http://127.0.0.1:8080/fee/housepayrecord/getById",{recordno:no},function(data){
					if(data.status=="OK"){
						$("span#recordno").html(data.model.recordno);
						$("span#payamount").html(data.model.payamount);
						$("span#paydate").html(data.model.paydate);
						$("span#payperson").html(data.model.payperson);
						$("span#invoicecode").html(data.model.invoicecode);
						$("span#paydesc").html(data.model.paydesc);
						$("span#recordstatus").html(data.model.recordstatus);
					}
				});
				$("div#PayRecordDialogArea" ).dialog({
					
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#PayRecordDialogArea" ).dialog( "close" );
					$( "div#PayRecordDialogArea" ).dialog( "destroy" );
					$("div#PayRecordDialogArea").html("");
				});
			});
		}
	});
	
});