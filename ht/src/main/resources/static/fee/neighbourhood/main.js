/**
 * 模块：供热缴费管理
 * 小区管理的前端控制JS
 * 作者：黄宇德
 * 
 */

$(function(){
	var rows=5;
	var page=1;
	var pageCount=0;
	var no=0; //选择的小区编号
	
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>供热缴费模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>小区管理</span></li>");
	
	function getListInfo(){
		//取得列表，分页模式
		$.getJSON(host+"fee/neighbourhood/list/all/page",{page:page,rows:rows},function(data){
				//显示个数和页数
				$("span#count").html(data.count);
				$("span#pagecount").html(data.page+"/"+data.pageCount);
				pageCount=data.pageCount;
				//显示列表
				$("table#NeighbourHoodTable tbody").html("");
				for(var i=0;i<data.list.length;i++){
					var tr="<tr id='"+data.list[i].hoodno+"'><td>"+data.list[i].hoodno
							+"</td><td>"+data.list[i].hoodname+"</td><td>"+data.list[i].address+"</td></tr>";
					$("table#NeighbourHoodTable tbody").append(tr);
				}
				//定义表格行的点击时间，取得选择的小区号
				$("table#NeighbourHoodTable tbody tr").on("click",function(){
					no=$(this).attr("id");
					$("table#NeighbourHoodTable tbody tr").css("background-color","#FFFFFF");
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
	$("a#NeighbourHoodAddLink").off().on("click",function(event){
				
		$("div#NeighbourHoodDialogArea").load("fee/neighbourhood/add.html",function(){
			$("div#NeighbourHoodDialogArea" ).dialog({
				title:"增加小区",
				width:600
			});
			
			$("form#NeighbourHoodAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					getListInfo(); 
				}
				BootstrapDialog.show({
		            title: '小区操作信息',
		            message:result.message
		        });
				$("div#NeighbourHoodDialogArea" ).dialog( "close" );
				$("div#NeighbourHoodDialogArea" ).dialog( "destroy" );
				$("div#NeighbourHoodDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#NeighbourHoodDialogArea" ).dialog( "close" );
				$( "div#NeighbourHoodDialogArea" ).dialog( "destroy" );
				$("div#NeighbourHoodDialogArea").html("");
			});		
		});		
	});
	
	//点击修改按钮事件处理
	$("a#NeighbourHoodModifyLink").off().on("click",function(event){
		if(no==0){
			BootstrapDialog.show({
	            title: '小区操作信息',
	            message:"请选择要修改的小区"
	        });
		}
		else {
			$("div#NeighbourHoodDialogArea").load("fee/neighbourhood/modify.html",function(){
				//取得选择的部门
				$.getJSON(host+"fee/neighbourhood/get",{hoodno:no},function(data){
					if(data.status=="OK"){
						$("input[name='hoodno']").val(no);
						$("input[name='hoodname']").val(data.model.hoodname);
						$("input[name='address']").val(data.model.address);
					}
				});
				
				$("div#NeighbourHoodDialogArea" ).dialog({
					title:"小区修改",
					width:600
				});
				//拦截表单提交
				$("form#NeighbourHoodModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						getListInfo(); 
					}

					BootstrapDialog.show({
			            title: '小区操作信息',
			            message:result.message
			        });
					$("div#NeighbourHoodDialogArea" ).dialog( "close" );
					$("div#NeighbourHoodDialogArea" ).dialog( "destroy" );
					$("div#NeighbourHoodDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#NeighbourHoodDialogArea" ).dialog( "close" );
					$( "div#NeighbourHoodDialogArea" ).dialog( "destroy" );
					$("div#NeighbourHoodDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#NeighbourHoodDeleteLink").off().on("click",function(event){
		
		if(no==0){
			BootstrapDialog.show({
	            title: '小区操作信息',
	            message:"请选择要删除的小区"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除小区么?', function(result){
				if(result) {
					$.post(host+"fee/neighbourhood/delete",{hoodno:no},function(result){
						if(result.status=="OK"){
							getListInfo(); 
						}
						BootstrapDialog.show({
							title: '小区操作信息',
							message:result.message
						});
					});
				}
			});
				
	
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#NeighbourHoodDetailLink").off().on("click",function(event){
		
		if(no==0){
			BootstrapDialog.show({
	            title: '小区操作信息',
	            message:"请选择要查看的小区"
	        });
		}
		else{
			$("div#NeighbourHoodDialogArea").load("fee/neighbourhood/detail.html",function(){
				//取得选择的部门
				$.getJSON(host+"fee/neighbourhood/get",{hoodno:no},function(data){
					if(data.status=="OK"){
						$("span#hoodname").html(data.model.hoodname);
						$("span#address").html(data.model.address);
						
					}
				});
				$("div#NeighbourHoodDialogArea" ).dialog({
					title:"小区详细",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#NeighbourHoodDialogArea" ).dialog( "close" );
					$( "div#NeighbourHoodDialogArea" ).dialog( "destroy" );
					$("div#NeighbourHoodDialogArea").html("");
				});
			});
		}
	});
	
});