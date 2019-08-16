/**
 * 
 */
/**
*
 * 
 */
$(function(){
	var typeno=0;
	var typename=null;
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>供热缴费模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>付款类型管理</span></li>");

	//显示列表
	$("table#PaymentTypeGrid").jqGrid({
		url: 'http://127.0.0.1:8080/fee/paymenttype/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '方式编号', name: 'typeno', width: 75 },
			{ label: '方式名称', name: 'typename', width: 90, align: 'left' },
		          
		],
		viewrecords: true, 
		autowidth: true,
		height: 200,
		rowNum: 5,

		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "typeno"},
		pager: "#PaymentTypeGridPager",
		
	});
	//更新jQGrid的列表显示
	function reloadList()
	{
		$("table#PaymentTypeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,tyepname:typename}}).trigger("reloadGrid");

	}
	
	//点击增加链接处理，嵌入add.html
	$("a#PaymentTypeAddLink").off().on("click",function(event){
				
		$("div#PaymentTypeDialogArea").load("fee/paymenttype/add.html",function(){
			$("div#PaymentTypeDialogArea" ).dialog({
				title:"增加付款类型",
				width:600
			});
			
			$("form#PaymentTypeAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({
		            title: '付款类型操作信息',
		            message:result.message
		        });
				$("div#PaymentTypeDialogArea" ).dialog( "close" );
				$("div#PaymentTypeDialogArea" ).dialog( "destroy" );
				$("div#PaymentTypeDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#PaymentTypeDialogArea" ).dialog( "close" );
				$( "div#PaymentTypeDialogArea" ).dialog( "destroy" );
				$("div#PaymentTypeDialogArea").html("");
			});		
		});		
	});
	
	//点击修改按钮事件处理
	$("a#PaymentTypeModifyLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的编号
		$("table#PaymentTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '付款类型操作信息',
	            message:"请选择要修改的付款类型"
	        });
		}
		else {
			$("div#PaymentTypeDialogArea").load("fee/paymenttype/modify.html",function(){
				
				$.getJSON("http://127.0.0.1:8080/fee/paymenttype/get",{typeno:typeno},function(data){
					if(data.status=="OK"){
						$("input[name='typeno']").val(typeno);
						$("input[name='typename']").val(data.model.typename);
					}
				});
				
				$("div#PaymentTypeDialogArea" ).dialog({
					title:"付款类型修改",
					width:600
				});
				//拦截表单提交
				$("form#PaymentTypeModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '付款类型操作信息',
			            message:result.message
			        });
					$("div#PaymentTypeDialogArea" ).dialog( "close" );
					$("div#PaymentTypeDialogArea" ).dialog( "destroy" );
					$("div#PaymentTypeDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#PaymentTypeDialogArea" ).dialog( "close" );
					$( "div#PaymentTypeDialogArea" ).dialog( "destroy" );
					$("div#PaymentTypeDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#PaymentTypeDeleteLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的户型编号
		$("table#PaymentTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '付款类型操作信息',
	            message:"请选择要删除的付款类型"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除付款类型么?', function(result){
				if(result) {
					$.post("http://127.0.0.1:8080/fee/paymenttype/delete",{typeno:typeno},function(result){
						if(result.status=="OK"){
							reloadList(); 
						}
						BootstrapDialog.show({
							title: '付款类型操作信息',
							message:result.message
						});
					});
				}
			});
				
	
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#PaymentTypeDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的编号
		$("table#PaymentTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '付款类型操作信息',
	            message:"请选择要查看的付款类型"
	        });
		}
		else{
			$("div#PaymentTypeDialogArea").load("fee/paymenttype/detail.html",function(){
				//取得选择的付款类型
				$.getJSON("http://127.0.0.1:8080/fee/paymenttype/get",{typeno:typeno},function(data){
					if(data.status=="OK"){
						$("span#typename").html(data.model.typename);
					
						
					}
				});
				$("div#PaymentTypeDialogArea" ).dialog({
					title:"户型详细",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#PaymentTypeDialogArea" ).dialog( "close" );
					$( "div#PaymentTypeDialogArea" ).dialog( "destroy" );
					$("div#PaymentTypeDialogArea").html("");
				});
			});
		}
	});
});