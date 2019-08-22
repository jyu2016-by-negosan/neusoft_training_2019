/**
 * 小区管理的前端控制JS
 * 作者：黄宇德
 * 
 */

$(function(){
	var rows=5;
	var page=1;
	var pageCount=0;
	var hoodno=0; //选择的小区编号

	function getListInfo(){
		//取得列表，分页模式
		$.getJSON("http://127.0.0.1:8080/fee/neighbourhood/list/all/page",{page:page,rows:rows},function(data){
				//显示个数和页数
				$("span#count").html(data.count);
				$("span#pagecount").html(data.page+"/"+data.pageCount);
				pageCount=data.pageCount;
				//显示列表
				$("table#NeighbourHoodTable tbody").html("");
				for(var i=0;i<data.list.length;i++){
					var tr="<tr><td>"+data.list[i].hoodno+"</td><td>"+data.list[i].hoodname+"</td>"
					+"<td>"+data.list[i].address+"</td></tr>";
					$("table#NeighbourHoodTable tbody").append(tr);
				}
	
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
				//alert(result.message);
				//BootstrapDialog.alert(result.message);
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
	$("a#DepartmentModifyLink").off().on("click",function(event){
		if(departmentNo==0){
			BootstrapDialog.show({
	            title: '部门操作信息',
	            message:"请选择要修改的部门"
	        });
		}
		else {
			$("div#DepartmentDialogArea").load("department/modify.html",function(){
				//取得选择的部门
				$.getJSON("department/get",{no:departmentNo},function(data){
					if(data.status=="OK"){
						$("input[name='no']").val(departmentNo);
						$("input[name='code']").val(data.model.code);
						$("input[name='name']").val(data.model.name);
						
					}
				});
				
				$("div#DepartmentDialogArea" ).dialog({
					title:"部门修改",
					width:600
				});
				//拦截表单提交
				$("form#DepartmentModifyForm").ajaxForm(function(result){
					if(result.status=="OK"){
						getListInfo(); 
					}
					//alert(result.message);
					//BootstrapDialog.alert(result.message);
					BootstrapDialog.show({
			            title: '部门操作信息',
			            message:result.message
			        });
					$("div#DepartmentDialogArea" ).dialog( "close" );
					$("div#DepartmentDialogArea" ).dialog( "destroy" );
					$("div#DepartmentDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#DepartmentDialogArea" ).dialog( "close" );
					$( "div#DepartmentDialogArea" ).dialog( "destroy" );
					$("div#DepartmentDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#DepartmentDelteLink").off().on("click",function(event){
		
		if(departmentNo==0){
			BootstrapDialog.show({
	            title: '部门操作信息',
	            message:"请选择要删除的部门"
	        });
		}
		else {
			//先检查此部门能否被删除
			$.getJSON("department/checkDelete",{no:departmentNo},function(data){
				if(data.status!="OK"){
					BootstrapDialog.show({
			            title: '部门操作信息',
			            message:data.message
			        });
				}
				else{
					BootstrapDialog.confirm('确认删除此部门么?', function(result){
			            if(result) {
			                $.post("department/delete",{no:departmentNo},function(result){
			                	if(result.status=="OK"){
									getListInfo(); 
								}
								BootstrapDialog.show({
						            title: '部门操作信息',
						            message:result.message
						        });
			                });
			            }
			        });
				}
			});
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#NeighbourHoodDetailLink").off().on("click",function(event){
		
		if(hoodno==0){
			BootstrapDialog.show({
	            title: '小区操作信息',
	            message:"请选择要查看的小区"
	        });
		}
		else{
			$("div#NeighbourHoodDialogArea").load("fee/neighbourhood/detail.html",function(){
				//取得选择的部门
				$.getJSON("http://127.0.0.1:8080/fee/neighbourhood/get",{hoodno:hoodno},function(data){
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