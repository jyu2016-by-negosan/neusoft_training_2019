/**
 * 
 */
$(function(){
	var typeno=0;
	var typename=null;
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>客户服务模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>投诉类型管理</span></li>");

	//显示列表
	$("table#ComplainTypeGrid").jqGrid({
		url: 'http://127.0.0.1:8080/complain/complaintype/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '类型编号', name: 'typeno', width: 75 },
			{ label: '类型名称', name: 'typename', width: 90, align: 'left' },
		          
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
		pager: "#ComplainTypeGridPager",
		
	});
	//更新jQGrid的列表显示
	function reloadList()
	{
		$("table#ComplainTypeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,typename:typename}}).trigger("reloadGrid");

	}
	
	//点击增加链接处理，嵌入add.html
	$("a#ComplainTypeAddLink").off().on("click",function(event){
				
		$("div#ComplainTypeDialogArea").load("complain/complaintype/add.html",function(){
			$("div#ComplainTypeDialogArea" ).dialog({
				title:"增加投诉类型",
				width:600
			});
			
			$("form#ComplainTypeAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({
		            title: '投诉类型操作信息',
		            message:result.message
		        });
				$("div#ComplainTypeDialogArea" ).dialog( "close" );
				$("div#ComplainTypeDialogArea" ).dialog( "destroy" );
				$("div#ComplainTypeDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#ComplainTypeDialogArea" ).dialog( "close" );
				$( "div#ComplainTypeDialogArea" ).dialog( "destroy" );
				$("div#ComplainTypeDialogArea").html("");
			});		
		});		
	});
	
	//点击修改按钮事件处理
	$("a#ComplainTypeModifyLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的编号
		$("table#ComplainTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '投诉类型操作信息',
	            message:"请选择要修改的投诉类型"
	        });
		}
		else {
			$("div#ComplainTypeDialogArea").load(complain/complaintype/modify.html",function(){
				
				$.getJSON("http://127.0.0.1:8080/complain/complaintype/get",{typeno:typeno},function(data){
					if(data.status=="OK"){
						$("input[name='typeno']").val(typeno);
						$("input[name='typename']").val(data.model.typename);
					}
				});
				
				$("div#ComplainTypeDialogArea" ).dialog({
					title:"投诉类型修改",
					width:600
				});
				//拦截表单提交
				$("form#ComplainTypeModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '投诉类型操作信息',
			            message:result.message
			        });
					$("div#ComplainTypeDialogArea" ).dialog( "close" );
					$("div#ComplainTypeDialogArea" ).dialog( "destroy" );
					$("div#ComplainTypeDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#ComplainTypeDialogArea" ).dialog( "close" );
					$( "div#ComplainTypeDialogArea" ).dialog( "destroy" );
					$("div#ComplainTypeDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#ComplainTypeDeleteLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的户型编号
		$("table#ComplainTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '投诉类型操作信息',
	            message:"请选择要删除的投诉类型"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除投诉类型么?', function(result){
				if(result) {
					$.post("http://127.0.0.1:8080/complain/complaintype/delete",{typeno:typeno},function(result){
						if(result.status=="OK"){
							reloadList(); 
						}
						BootstrapDialog.show({
							title: '投诉类型操作信息',
							message:result.message
						});
					});
				}
			});
				
	
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#ComplainTypeDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的编号
		$("table#ComplainTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '投诉类型操作信息',
	            message:"请选择要查看的投诉类型"
	        });
		}
		else{
			$("div#ComplainTypeDialogArea").load("complain/complaintype/detail.html",function(){
				//取得选择的付款类型
				$.getJSON("http://127.0.0.1:8080/complain/complaintype/get",{typeno:typeno},function(data){
					if(data.status=="OK"){
						$("span#typename").html(data.model.typename);
					
						
					}
				});
				$("div#ComplainTypeDialogArea" ).dialog({
					title:"户型详细",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#ComplainTypeDialogArea" ).dialog( "close" );
					$( "div#ComplainTypeDialogArea" ).dialog( "destroy" );
					$("div#ComplainTypeDialogArea").html("");
				});
			});
		}
	});
});