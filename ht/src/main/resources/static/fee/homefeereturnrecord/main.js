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
	var host = "http://127.0.0.1:8080"
	

	function getListInfo(){
		//取得列表，分页模式
		$.getJSON("http://127.0.0.1:8080/fee/homefeereturnrecord/getAllByListWithPages",{page:page,rows:rows},function(data){
				//显示居民缴费总条数和页数
				$("span#count").html(data.count);
				$("span#pagecount").html(page+"/"+data.pageCount);
				pageCount=data.pageCount;
				//显示列表
				$("table#HomeFeeReturnRecordTable tbody").html("");
				for(var i=0;i<data.list.length;i++){
					var tr="<tr id='"+data.list[i].recordno+"'><td>"+data.list[i].recordno
							+"</td><td>"+data.list[i].amount+"</td><td>"+data.list[i].returndate+"</td><td>" +data.list[i].person+"</td><td>"
									+data.list[i].invoicecode+"</td><td>" +data.list[i].recorddesc+"</td><td>"+data.list[i].recordstatus+"</td></tr>";
									
					$("table#HomeFeeReturnRecordTable tbody").append(tr);
				}
				//定义表格行的点击时间，取得选择的退费记录ID
				$("table#HomeFeeReturnRecordTable tbody tr").off().on("click",function(){
					no=$(this).attr("id");
					$("table#HomeFeeReturnRecordTable tbody tr").css("background-color","#FFFFFF");
					$(this).css("background-color","#6495ED");
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
	
	
	//点击增加链接处理，嵌入add.html
	$("a#ReturnRecordAddLink").off().on("click",function(event){
		$("div#ReturnRecordDialogArea").load("fee/homefeereturnrecord/add.html",function(){
			$("div#ReturnRecordDialogArea" ).dialog({
				title:"增加居民退费记录",
				width:400
			});
			$.getJSON(host+"/fee/paymenttype/list/all",function(PayTypeList){
				if(PayTypeList){
					$.each(PayTypeList,function(index,payType){
						$("#payType").append("<option value='"+payType.typeno+"'>"+payType.typename+"</option>");
					})
				}
			})

			$("form#HomeFeeReturnRecordAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					getListInfo(); 
				}
				BootstrapDialog.show({
		            title: '退款操作信息',
		            message:result.message
		        });
				$("div#ReturnRecordDialogArea" ).dialog( "close" );
				$("div#ReturnRecordDialogArea" ).dialog( "destroy" );
				$("div#ReturnRecordDialogArea").html("");
				
			});
		});
	});
	
	
	
	//点击修改按钮事件处理
	$("a#PayRecordModifyLink").off().on("click",function(event){
		if(no==-1){
			BootstrapDialog.show({
	            title: '缴费操作信息',
	            message:"请选择要修改的缴费记录"
	        });
		}
		else {
			$("div#PayRecordDialogArea").load("fee/homefeepayrecord/modify.html",function(){
				//取得居民缴费记录
				$.getJSON("http://127.0.0.1:8080/fee/housepayrecord/getById",{recordno:no},function(data){
					if(data.status=="OK"){
						$("input[name='recordno']").val(no);
						$("input[name='payamount']").val(data.model.payamount);
						$("input[name='paydate']").val(data.model.paydate);
						$("input[name='payperson']").val(data.model.payperson);
						$("input[name='invoicecode']").val(data.model.invoicecode);
						$("input[name='paydesc']").val(data.model.paydesc);
						$("input[name='recordstatus']").val(data.model.recordstatus);
					}
				});
				
				$("div#PayRecordDialogArea" ).dialog({
					title:"居民缴费记录修改",
					width:600
				});
				//拦截表单提交
				$("form#HomeFeePayRecordModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						getListInfo(); 
					}

					BootstrapDialog.show({
			            title: '记录操作信息',
			            message:result.message
			        });
					$("div#PayRecordDialogArea" ).dialog( "close" );
					$("div#PayRecordDialogArea" ).dialog( "destroy" );
					$("div#PayRecordDialogArea").html("");
					
				});
				
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#PayRecordDialogArea" ).dialog( "close" );
					$( "div#PayRecordDialogArea" ).dialog( "destroy" );
					$("div#PayRecordDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#ReturnRecordDeleteLink").off().on("click",function(event){
		
		if(no==-1){
			BootstrapDialog.show({
	            title: '记录操作信息',
	            message:"请选择要删除的缴费记录"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除此条缴费记录么?', function(result){
				if(result) {
					$.getJSON("http://127.0.0.1:8080/fee/homefeereturnrecord/delete",{recordno:no},function(result){
						if(result.status=="OK"){
							getListInfo(); 
						}
						BootstrapDialog.show({
							title: '记录操作信息',
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