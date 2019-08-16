/**
 * 居民管理JS
 * 作者：黄宇德
 */
$(function(){
	//关联
	var hoodno=0;
	var housetypeno=0;
	//列表要显示的
	var homeno=0;
	var homename=null;
	var homearea=0;
	var heatingarea=0;
	var tel=null;
	var mobile=null;
	var buildingcode=null;
	var departmentcode=null;
	var heatingstatus=null;
	//详细多出的
	var heatingcode = null;
	var floorcode=null;
	var housecode = null;
	var direction = null;
	var mail = null;
	var qq = null;
	
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>供热缴费模块</span></li>"

	+"<li class='breadcrumb-item'><span id='mainpagetille'>供热居民管理</span></li>");

	+"<li class='breadcrumb-item'><span id='mainpagetille'>居民管理</span></li>");


	//显示列表
	$("table#HeatingHomeGrid").jqGrid({
		url: 'http://127.0.0.1:8080/fee/home/list/all/page/condition',
		datatype: "json",
		colModel: [
			{ label: '序号', name: 'homeno' },
			{ label: '姓名', name: 'homename' },
			{ label: '建筑面积', name: 'homearea' },
			{ label: '供热面积', name: 'heatingarea' },
			{ label: '电话', name: 'tel' },
			{ label: '手机', name: 'mobile' },
			{ label: '小区', name: 'neighbourhood.hoodname' },
			{ label: '楼号', name: 'buildingcode' },
			{ label: '单元', name: 'departmentcode' },
			{ label: '供热状态', name: 'heatingstatus' },          
		],
		viewrecords: true, 
		autowidth: true,
		height: 200,
		rowNum: 4,

		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "typeno",
			  },
		pager: "#HeatingHomeGridPager",
		multiselect:false,
		onSelectRow:function(no){
			homeno=no;
		}
		
	});

	//取得小区列表，填充小区下拉框
	$.getJSON(host+"fee/neighbourhood/list/all",function(neighbourhoodList){
		if(neighbourhoodList){
			$.each(neighbourhoodList,function(index,neighbourhood){
				$("select#NeighbourHoodSelection").append("<option value='"+neighbourhood.hoodno+"'>"+neighbourhood.hoodname+"</option>");
			});
		}
	});

	//设置检索参数，更新jQGrid的列表显示
	function reloadList()
	{

		$("table#PaymentTypeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,typename:typename}}).trigger("reloadGrid");

		$("table#HeatingHomeGrid").jqGrid('setGridParam',
		{
			postData:{				
				hoodno:hoodno
			},
			
		}).trigger("reloadGrid");

	}
	//点击检索事件处理
	$("a#HeatingHomeSearchButton").on("click",function(){
		hoodno=$("select#NeighbourHoodSelection").val();
		reloadList();
	});
	

	//点击增加链接处理，嵌入add.html
	$("a#ComplainTypeAddLink").off().on("click",function(event){

	//==========点击增加链接处理，嵌入add.html===================
	$("a#HeatingHomeAddLink").off().on("click",function(event){

		$("div#PaymentTypeDialogArea").load("fee/paymenttype/add.html",function(){
			$("div#PaymentTypeDialogArea" ).dialog({
				title:"增加投诉类型",

		$("div#HeatingHomeDialogArea").load("fee/heatinghome/add.html",function(){
			$("div#HeatingHomeDialogArea" ).dialog({
				title:"增加居民",

				width:600
			});
			//取得小区列表，填充小区下拉框
			$.getJSON(host+"fee/neighbourhood/list/all",function(neighbourhoodList){
				if(neighbourhoodList){
					$.each(neighbourhoodList,function(index,neighbourhood){
						$("select#NeighbourHoodSelection").append("<option value='"+neighbourhood.hoodno+"'>"+neighbourhood.hoodname+"</option>");
					});
				}
			});
			//取得户型列表，填充户型下拉框
			$.getJSON(host+"fee/housetype/list/all",function(housetypeList){
				if(housetypeList){
					$.each(housetypeList,function(index,housetype){
						$("select#HouseTypeSelection").append("<option value='"+housetype.typeno+"'>"+housetype.typename+"</option>");
						
					});
				}
			});
					
			$("form#HeatingHomeAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({

		            title: '投诉付款类型操作信息',

		            title: '居民操作信息',

		            message:result.message
		        });
				$("div#HeatingHomeDialogArea" ).dialog( "close" );
				$("div#HeatingHomeDialogArea" ).dialog( "destroy" );
				$("div#HeatingHomeDialogArea").html("");
				
			});
			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#HeatingHomeDialogArea" ).dialog( "close" );
				$( "div#HeatingHomeDialogArea" ).dialog( "destroy" );
				$("div#HeatingHomeDialogArea").html("");
			});		
		});		
	});
	
	//=================点击修改按钮事件处理=============================
	$("a#HeatingHomeModifyLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的编号
		$("table#HeatingHomeGrid tbody tr").on("click",function(){
			homeno=$(this).attr("id");
			
		});
		
		if(homeno==0){
			BootstrapDialog.show({

	            title: '投诉类型操作信息',
	            message:"请选择要修改的投诉类型"

	            title: '操作信息',
	            message:"请选择要修改的居民信息"

	        });
		}
		else {
			
			//取得小区列表，填充小区下拉框
			$.getJSON(host+"fee/neighbourhood/list/all",function(neighbourhoodList){
				if(neighbourhoodList){
					$.each(neighbourhoodList,function(index,neighbourhood){
						$("select#NeighbourHoodSelection").append("<option value='"+neighbourhood.hoodno+"'>"+neighbourhood.hoodname+"</option>");
					});
				}
			});
			
			//取得户型列表，填充下拉框
			$.getJSON(host+"fee/housetype/list/all",function(housetypeList){
				if(housetypeList){
					$.each(housetypeList,function(index,housetype){
						$("select#HousetTypeSelection").append("<option value='"+housetype.typeno+"'>"+housetype.typename+"</option>");
					});
				}
			});
			
			
			$("div#HeatingHomeDialogArea").load("fee/heatinghome/modify.html",function(){
				$.getJSON("http://127.0.0.1:8080/fee/home/get",{homeno:homeno},function(data){
					if(data.status=="OK"){
					
						$("input[name='homeno']").val(homeno);
						$("input[name='homename']").val(data.model.homename);
						$("input[name='heatingcode']").val(data.model.heatingcode);
						$("input[name='homearea']").val(data.model.homearea);
						$("input[name='heatingcode']").val(data.model.heatingcode);
						$("input[name='buildingcode']").val(data.model.buildingcode);
						$("input[name='departmentcode']").val(data.model.departmentcode);
						$("input[name='floorcode']").val(data.model.floorcode);
						$("input[name='heatingcode']").val(data.model.heatingcode);
						$("input[name='housecode']").val(data.model.housecode);
						$("input[name='direction']").val(data.model.direction);
						$("input[name='tel']").val(data.model.tel);
						$("input[name='mobile']").val(data.model.mobile);
						$("input[name='qq']").val(data.model.qq);
						$("input[name='mail']").val(data.model.mail);
						$("input[name='heatingstatus']").val(data.model.heatingstatus);
						$("input[name='heatingarea']").val(data.model.heatingarea);
					}
				});
		/* 
		 
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 
		 
		 */
				$("div#HeatingHomeDialogArea" ).dialog({
					title:"居民信息修改",
					width:600
				});
				//拦截表单提交
				$("form#HeatingHomeModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '操作信息',
			            message:result.message
			        });
					$("div#HeatingHomeDialogArea" ).dialog( "close" );
					$("div#HeatingHomeDialogArea" ).dialog( "destroy" );
					$("div#HeatingHomeDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#HeatingHomeDialogArea" ).dialog( "close" );
					$( "div#HeatingHomeDialogArea" ).dialog( "destroy" );
					$("div#HeatingHomeDialogArea").html("");
				});			
			});
		}				
	});
	
	//=================点击删除按钮事件处理===============================
	$("a#HeatingHomeDeleteLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的户型编号
		$("table#HeatingHomeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		/* 
		if(typeno==0){
			BootstrapDialog.show({
	            title: '操作信息',
	            message:"请选择要删除的"
	        });
		}
		
		else {
		 */
			BootstrapDialog.confirm('确认删除居民信息么?', function(result){
				if(result) {
					/* $.post("http://127.0.0.1:8080/fee/home/delete",{:},function(result){
						if(result.status=="OK"){
							reloadList(); 
						}
						BootstrapDialog.show({
							title: '操作信息',
							message:result.message
						});
					}); */
				}
			});
		/* 			
		}
		 */
	});
	
	//=================点击查看详细按钮事件处理=====================
	$("a#HeatingHomeDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的编号
		$("table#HeatingHomeGrid tbody tr").on("click",function(){
			typeno=$(this).attr("id");
			
		});
		/* 
		if(typeno==0){
			BootstrapDialog.show({
	            title: '付款类型操作信息',
	            message:"请选择要查看的付款类型"
	        });
		}
		else{ */
			$("div#HeatingHomeDialogArea").load("fee/heatinghome/detail.html",function(){
				//取得选择的付款类型
				/* $.getJSON("http://127.0.0.1:8080/fee/home/get",{:},function(data){
					if(data.status=="OK"){
						
					
						
					}
				}); */
				$("div#HeatingHomeDialogArea" ).dialog({
					title:"居民详细信息",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#HeatingHomeDialogArea" ).dialog( "close" );
					$( "div#HeatingHomeDialogArea" ).dialog( "destroy" );
					$("div#HeatingHomeDialogArea").html("");
				});
			});
		/* } */
	});
});