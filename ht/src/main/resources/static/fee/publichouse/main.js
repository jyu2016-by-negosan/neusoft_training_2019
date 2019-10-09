/**
 * 模块：供热缴费管理 小区管理的前端控制JS 作者：罗妙忠
 * 
 */

$(function(){
	var rows=5;		  // 每页多少条数据
	var page=1;       // 当前页数
	var pageCount=0;  // 缴费总页数
	var no =-1;
	var host = "http://127.0.0.1:8080";
	
	function getListInfo(){
		// 取得列表，分页模式
		$.getJSON(host+"/fee/publichouse/getAllByListWithPages",{page:page,rows:rows},function(data){
				// 显示居民缴费总条数和页数
				$("span#count").html(data.count);
				$("span#pagecount").html(page+"/"+data.pageCount);
				pageCount=data.pageCount;
				// 显示列表
				$("table#PublicHouseTable tbody").html("");
				
				for(var i=0;i<data.list.length;i++){
					var heatingstatus = "";
					if(data.list[i].heatingstatus=="停供"){
						heatingstatus = "停供";
					}else{
						heatingstatus = "正常";
					}
					
					var tr="<tr id='"+data.list[i].houseno+"' data-status='"+data.list[i].heatingstatus+
					"'><td>"+data.list[i].houseno+"</td><td>"+data.list[i].housename+"</td><td>"
					+data.list[i].contactname+"</td><td>"+data.list[i].tel+"</td><td>"
					+data.list[i].mobile+"</td><td>"+data.list[i].neighbourHoodModel.hoodname+"</td><td>"+data.list[i].building+"</td><td>"+
					data.list[i].housearea+"</td><td>"+data.list[i].heatingarea+
					"</td><td data='status'>"
					+heatingstatus+"</td></tr>";					
					$("table#PublicHouseTable tbody").append(tr);
				}
				// 定义表格行的点击时间，取得选择的退费记录ID
				$("table#PublicHouseTable tbody tr").off().on("click",function(){
					no=$(this).attr("id");
					$("table#PublicHouseTable tbody tr").css("background-color","#FFFFFF");
					$(this).css("background-color","#d6d0d08c");
				});
				
				
		 });
			
	}
	
	// 定义分页导航链接处理事件
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
	
	// 初始调用取得分页列表数据
	getListInfo();
	
	// ==================================================
	
	$("a#PublicHouseStatusLink").off().on("click",function(event){
		if(no==-1){
			BootstrapDialog.show({
	            title: '停供操作信息',
	            message:"请选择要修改的停供记录"
	        });
		}
		else {	
			 $("div#PublicHouseDialogArea").load("fee/homefee/changeheatingstatus.html",function(){
					
				$.getJSON(host+"/fee/homefee/get",{feeno:feeno},function(data){
					if(data.status=="OK"){
						// console.log("feeNo: "+feeNo);
						// console.log("data.model.home:"+data.model.home);
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

				$("div#PublicHouseDialogArea" ).dialog({
					title:"供热状态信息操作",
					width:600
				});		
				// 拦截表单提交
				$("form#ChangeHeatingStatusForm").ajaxForm(function(result){			
					if(result.status=="OK"){
						var val = $('input[name="heatingstatus"]:checked').val(); 

						if(val=="正常"){
							val = "Y";
						}else{
							val = "N";
						}
						
						 // 修改此页面的heatingstatus状态
							
						$.getJSON(host+"/fee/homestoprecord/modify",{recordno:no,heatingstatus:val},function(){
							getListInfo();
						});
						
					}
					BootstrapDialog.show({
			            title: '供热状态操作信息',
			            message:result.message
			        });
					$("div#PublicHouseDialogArea" ).dialog( "close" );
					$("div#PublicHouseDialogArea" ).dialog( "destroy" );
					$("div#PublicHouseDialogArea").html("");	
					
				});
				
				
				// 点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#PublicHouseDialogArea" ).dialog( "close" );
					$( "div#PublicHouseDialogArea" ).dialog( "destroy" );
					$( "div#PublicHouseDialogArea").html("");
				});			
			});
		}				
	});
	
	
	// 点击增加链接处理，嵌入add.html
	$("a#PublicHouseAddLink").off().on("click",function(event){
		$.getJSON(host+"/fee/neighbourhood/list/all",function(neighbourHoodModelList){
			if(neighbourHoodModelList){
				$.each(neighbourHoodModelList,function(index,neighbourHoodModel){
					$("#NeighbourHoodSelection").append("<option value='"+neighbourHoodModel.hoodno+"'>"+neighbourHoodModel.hoodname+"</option>");
				})
			}
		});
		$("div#PublicHouseDialogArea").load("fee/publichouse/add.html",function(){
			
			$("div#PublicHouseDialogArea" ).dialog({
				title:"增加公建管理记录",
				width:600
			});
			$("form#PublicHouseAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					getListInfo(); 
				}
				BootstrapDialog.show({
		            title: '公建管理操作信息',
		            message:result.message
		        });
				$("div#PublicHouseDialogArea" ).dialog( "close" );
				$("div#PublicHouseDialogArea" ).dialog( "destroy" );
				$("div#PublicHouseDialogArea").html("");
				
			});
		});
	});
	
	
	
	// 点击修改按钮事件处理
	$("a#PublicHouseModifyLink").off().on("click",function(event){
		if(no==-1){
			BootstrapDialog.show({
	            title: '公建供热操作信息',
	            message:"请选择要修改的公建供热记录"
	        });
		}
		else {
			$("div#PublicHouseDialogArea").load("fee/publichouse/modify.html",function(){
				// 取得公建记录
				$.getJSON(host+"/fee/publichouse/getByNo",{houseno:no},function(data){
					if(data.status=="OK"){
						// 获取小区信息
						$.getJSON(host+"/fee/neighbourhood/list/all",function(neighbourHoodModelList){
							if(neighbourHoodModelList){
								$.each(neighbourHoodModelList,function(index,neighbourHoodModel){
									if(neighbourHoodModel.hoodno==no){
										$("#NeighbourHoodSelection").append("<option value='"+neighbourHoodModel.hoodno+"' selected='"+neighbourHoodModel.hoodno+"'>"+neighbourHoodModel.hoodname+"</option>");
									}else
									$("#NeighbourHoodSelection").append("<option value='"+neighbourHoodModel.hoodno+"'>"+neighbourHoodModel.hoodname+"</option>");	
								})
							}
						});
						
						$("input[name='houseno']").val(data.model.houseno);
						$("input[name='housearea']").val(data.model.housearea);
						$("input[name='heatingarea']").val(data.model.heatingarea);
						$("input[name='housename']").val(data.model.housename);
						$("input[name='address']").val(data.model.address);
						$("input[name='contactname']").val(data.model.contactname);
						$("input[name='tel']").val(data.model.tel);
						$("input[name='mobile']").val(data.model.mobile);
						$("input[name='mail']").val(data.model.mail);
						$("input[name='qq']").val(data.model.qq);
						$("input[name='building']").val(data.model.building);
						$("input[name='housecode']").val(data.model.housecode);
						if(data.model.heatingstatus=="正常"){
							$("input[name='heatingstatus'][value='正常']").attr("checked",true);
						}else{
							$("input[name='heatingstatus'][value='停供']").attr("checked",true);
						}
						
						 
					}
				});
			
				
				$("div#PublicHouseDialogArea" ).dialog({
					title:"公建供热记录修改",
					width:600
				});
				// 拦截表单提交
				$("form#PublicHouseModifyForm").ajaxForm(function(result){	
					if(result.status=="OK"){
						getListInfo(); 
					}
					BootstrapDialog.show({
			            title: '记录操作信息',
			            message:result.message
			        });
					$("div#PublicHouseDialogArea" ).dialog( "close" );
					$("div#PublicHouseDialogArea" ).dialog( "destroy" );
					$("div#PublicHouseDialogArea").html("");
				});
				// 点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#PublicHouseDialogArea" ).dialog( "close" );
					$( "div#PublicHouseDialogArea" ).dialog( "destroy" );
					$("div#PublicHouseDialogArea").html("");
				});
			})
			
		}
		
		
	});
	
	// 点击删除按钮事件处理
	$("a#PublicHouseDeleteLink").off().on("click",function(event){

		if(no==-1){
			BootstrapDialog.show({
	            title: '记录操作信息',
	            message:"请选择要删除的公建记录"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除此条公建记录么?', function(result){
				if(result) {
					$.getJSON("http://127.0.0.1:8080/fee/publichouse/delete",{houseno:no},function(result){
						if(result.status=="OK"){
							getListInfo(); 
						}
						BootstrapDialog.show({
							title: '公建供热操作信息',
							message:result.message
						});
					});
				}
			});
				
	
			
		}
		
	});
	
	// 点击查看详细按钮事件处理
	$("a#PublicHouseSelectLink").off().on("click",function(event){
		if(no==-1){
			BootstrapDialog.show({
	            title: '记录操作信息',
	            message:"请选择要查看的公建记录"
	        });
		}
		else{
			$("div#PublicHouseDialogArea").load("fee/publichouse/detail.html",function(){
				// 取得公建记录
				$.getJSON(host+"/fee/publichouse/getByNo",{houseno:no},function(data){
					if(data.status=="OK"){
						// 获取小区信息
						$.getJSON(host+"/fee/neighbourhood/list/all",function(neighbourHoodModelList){
							if(neighbourHoodModelList){
								$.each(neighbourHoodModelList,function(index,neighbourHoodModel){
									if(neighbourHoodModel.hoodno==no){
										$("#NeighbourHoodSelection").append("<option value='"+neighbourHoodModel.hoodno+"' selected='"+neighbourHoodModel.hoodno+"'>"+neighbourHoodModel.hoodname+"</option>");
									}else
									$("#NeighbourHoodSelection").append("<option value='"+neighbourHoodModel.hoodno+"'>"+neighbourHoodModel.hoodname+"</option>");	
								})
							}
						});
						
						$("input[name='houseno']").val(data.model.houseno);
						$("input[name='housearea']").val(data.model.housearea);
						$("input[name='heatingarea']").val(data.model.heatingarea);
						$("input[name='housename']").val(data.model.housename);
						$("input[name='address']").val(data.model.address);
						$("input[name='contactname']").val(data.model.contactname);
						$("input[name='tel']").val(data.model.tel);
						$("input[name='mobile']").val(data.model.mobile);
						$("input[name='mail']").val(data.model.mail);
						$("input[name='qq']").val(data.model.qq);
						$("input[name='building']").val(data.model.building);
						$("input[name='housecode']").val(data.model.housecode);
						if(data.model.heatingstatus=="正常"){
							$("input[name='heatingstatus'][value='正常']").attr("checked",true);
						}else{
							$("input[name='heatingstatus'][value='停供']").attr("checked",true);
						}
						
						 
					}
				});
				$("div#PublicHouseDialogArea" ).dialog({
					title:"公建供热记录修改",
					width:600
				});
			
				// 点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#PublicHouseDialogArea" ).dialog( "close" );
					$( "div#PublicHouseDialogArea" ).dialog( "destroy" );
					$("div#PublicHouseDialogArea").html("");
				});
			});
		}
	});
	
});