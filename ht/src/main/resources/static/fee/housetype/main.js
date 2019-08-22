/**
 * 模块：供热缴费管理
 * 户型管理的前端控制JS
 * 作者：黄宇德
 * 
 */
$(function(){
	var typeno=0;
	var typename=null;
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>供热缴费模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>户型管理</span></li>");
	//设置日期的格式和选择
	
	//显示户型列表
	  
	$("table#HouseTypeGrid").jqGrid({
		url: host+'fee/housetype/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '户型编号', name: 'typeno', width: 75 },
			{ label: '户型名称', name: 'typename', width: 90 },
		          
		],
		viewrecords: true, 
		autowidth: true,
		height: 250,
		rowNum: 5,

		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "typeno"},
		pager: "#HouseTypeGridPager",
	});
	//更新jQGrid的列表显示
	function reloadList()
	{
		$("table#HouseTypeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,tyepname:typename}}).trigger("reloadGrid");

	}
	
	//点击增加链接处理，嵌入add.html
	$("a#HouseTypeAddLink").off().on("click",function(event){
				
		$("div#HouseTypeDialogArea").load("fee/housetype/add.html",function(){
			$("div#HouseTypeDialogArea" ).dialog({
				title:"增加户型",
				width:600
			});
			
			$("form#HouseTypeAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({
		            title: '户型操作信息',
		            message:result.message
		        });
				$("div#HouseTypeDialogArea" ).dialog( "close" );
				$("div#HouseTypeDialogArea" ).dialog( "destroy" );
				$("div#HouseTypeDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#HouseTypeDialogArea" ).dialog( "close" );
				$( "div#HouseTypeDialogArea" ).dialog( "destroy" );
				$("div#HouseTypeDialogArea").html("");
			});		
		});		
	});
	
	//点击修改按钮事件处理
	$("a#HouseTypeModifyLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的户型编号
		$("table#HouseTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '户型操作信息',
	            message:"请选择要修改的户型"
	        });
		}
		else {
			$("div#HouseTypeDialogArea").load("fee/housetype/modify.html",function(){
				
				$.getJSON(host+"fee/housetype/get",{typeno:typeno},function(data){
					if(data.status=="OK"){
						$("input[name='typeno']").val(typeno);
						$("input[name='typename']").val(data.model.typename);
					}
				});
				
				$("div#HouseTypeDialogArea" ).dialog({
					title:"户型修改",
					width:600
				});
				//拦截表单提交
				$("form#HouseTypeModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '户型操作信息',
			            message:result.message
			        });
					$("div#HouseTypeDialogArea" ).dialog( "close" );
					$("div#HouseTypeDialogArea" ).dialog( "destroy" );
					$("div#HouseTypeDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#HouseTypeDialogArea" ).dialog( "close" );
					$( "div#HouseTypeDialogArea" ).dialog( "destroy" );
					$("div#HouseTypeDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#HouseTypeDeleteLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的户型编号
		$("table#HouseTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '户型操作信息',
	            message:"请选择要删除的户型"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除户型么?', function(result){
				if(result) {
					$.post(host+"fee/housetype/delete",{typeno:typeno},function(result){
						if(result.status=="OK"){
							reloadList(); 
						}
						BootstrapDialog.show({
							title: '户型操作信息',
							message:result.message
						});
					});
				}
			});
				
	
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#HouseTypeDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的户型编号
		$("table#HouseTypeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		if(typeno==0){
			BootstrapDialog.show({
	            title: '户型操作信息',
	            message:"请选择要查看的户型"
	        });
		}
		else{
			$("div#HouseTypeDialogArea").load("fee/housetype/detail.html",function(){
				//取得选择的户型
				$.getJSON(host+"fee/housetype/get",{typeno:typeno},function(data){
					if(data.status=="OK"){
						$("span#typename").html(data.model.typename);				
					}
				});
				$("div#HouseTypeDialogArea" ).dialog({
					title:"户型详细",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#HouseTypeDialogArea" ).dialog( "close" );
					$( "div#HouseTypeDialogArea" ).dialog( "destroy" );
					$("div#HouseTypeDialogArea").html("");
				});
			});
		}
	});
});