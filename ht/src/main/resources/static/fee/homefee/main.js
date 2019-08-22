/**
 * 住宅供热记录管理JS
 * 作者：黄宇德
 */
$(function(){
	//关联
	var hoodno=0;
	var heatingyear=null;

	var feeno=0;
	var agreefee=0;
	var actualfee=0;
	var debtfee=0;
	//详细多出的
	var feestatus = null;
	var feedesc=null;
	
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>供热缴费模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>住宅供热记录管理</span></li>");

	//显示列表
	$("table#HomeFeeGrid").jqGrid({
		url: host+'fee/homefee/list/all/page/condition',
		datatype: "json",
		colModel: [
			{ label: '序号', name: 'feeno' },
			{ label: '小区', name: 'home.neighbourhood.hoodname' },
			{ label: '姓名', name: 'home.homename' },
			{ label: '实际供热面积', name: 'home.heatingarea' },
			{ label: '实际供热天数', name: 'heatingprice.heatingdays' },
			{ label: '应缴费', name:'agreefee'},
			{ label: '实际缴费', name:'actualfee'},
			{ label: '欠缴费', name:'debtfee'},
			{ label: '供热状态', name: 'home.heatingstatus' },          
		],
		caption:"住宅供热记录列表",
		viewrecords: true, 
		autowidth: true,
		height: 250,
		rowNum: 4,

		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "feeno",
			  },
		pager: "#HomeFeeGridPager",
		multiselect:false,
		onSelectRow:function(no){
			feeno=no;
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
	
	//取得供热年度价格列表，填充年度下拉框
	$.getJSON(host+"fee/heatingprice/list/all",function(heatingpriceList){
		if(heatingpriceList){
			$.each(heatingpriceList,function(index,heatingprice){
				$("select#HeatingYearSelection").append("<option value='"+heatingprice.heatingyear+"'>"+heatingprice.heatingyear+"</option>");
			});
		}
	});
	
	//设置检索参数，更新jQGrid的列表显示
	function reloadList()
	{
		$("table#HomeFeeGrid").jqGrid('setGridParam',
		{
			postData:{				
				hoodno:hoodno,
				heatingyear:heatingyear,
				feestatus:feestatus
			},
			
		}).trigger("reloadGrid");
	}
	//点击检索事件处理
	$("a#HomeFeeSearchButton").on("click",function(){
		hoodno=$("select#NeighbourHoodSelection").val();	
		heatingyear=$("select#HeatingYearSelection").val();
		feestatus=$("input[name='feestatus']:checked").val();
		reloadList();
	});
	
	
	//=================点击修改供热状态按钮事件处理======================
	$("a#ChangeHeatingStatusLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的编号
		$("table#HomeFeeGrid tbody tr").on("click",function(){
			feeno=$(this).attr("id");
		});
		
		if(feeno==0){
			BootstrapDialog.show({
	            title: '住宅供热记录操作信息',
	            message:"请选择要修改的住宅供热记录"
	        });
		}
		else {	
			$("div#HomeFeeDialogArea").load("fee/homefee/changeheatingstatus.html",function(){
				$.getJSON(host+"fee/homefee/get",{feeno:feeno},function(data){
					if(data.status=="OK"){
					
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

				$("div#HomeFeeDialogArea" ).dialog({
					title:"住宅供热记录信息修改",
					width:600
				});		
				//拦截表单提交
				$("form#ChangeHeatingStatusForm").ajaxForm(function(result){			
					if(result.status=="OK"){
						reloadList();
					}
					BootstrapDialog.show({
			            title: '住宅供热记录操作信息',
			            message:result.message
			        });
					$("div#HomeFeeDialogArea" ).dialog( "close" );
					$("div#HomeFeeDialogArea" ).dialog( "destroy" );
					$("div#HomeFeeDialogArea").html("");			
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#HomeFeeDialogArea" ).dialog( "close" );
					$( "div#HomeFeeDialogArea" ).dialog( "destroy" );
					$("div#HomeFeeDialogArea").html("");
				});			
			});
		}				
	});
	//=================点击修改供热天数按钮事件处理======================
	$("a#ChangeHeatingDaysLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的编号
		$("table#HomeFeeGrid tbody tr").on("click",function(){
			feeno=$(this).attr("id");
			
		});
		
		if(feeno==0){
			BootstrapDialog.show({
	            title: '住宅供热记录操作信息',
	            message:"请选择要修改的住宅供热记录"
	        });
		}
		else {	
			$("div#HomeFeeDialogArea").load("fee/homefee/changeheatingdays.html",function(){
				$.getJSON(host+"fee/homefee/get",{feeno:feeno},function(data){
					if(data.status=="OK"){
						
					$("span#homename").html(data.model.home.homename);
					$("span#neighbourhood").html(data.model.home.neighbourhood.hoodname);
					$("span#heatingarea").html(data.model.home.heatingarea);
					$("span#agreefee").html(data.model.agreefee);
					$("span#actualfee").html(data.model.actualfee);
					$("span#debtfee").html(data.model.debtfee);
					$("input[name='heatingdays']").val(data.model.heatingprice.heatingdays);
					$("input[name='heatingyear']").val(data.model.heatingprice.heatingyear);
					}
				});
	
				$("div#HomeFeeDialogArea" ).dialog({
					title:"住宅供热记录信息修改",
					width:600
				});		
				//拦截表单提交
				$("form#ChangeHeatingDaysForm").ajaxForm(function(result){
			
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '操作信息',
			            message:result.message
			        });
					$("div#HomeFeeDialogArea" ).dialog( "close" );
					$("div#HomeFeeDialogArea" ).dialog( "destroy" );
					$("div#HomeFeeDialogArea").html("");			
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#HomeFeeDialogArea" ).dialog( "close" );
					$( "div#HomeFeeDialogArea" ).dialog( "destroy" );
					$("div#HomeFeeDialogArea").html("");
				});			
			});
		}				
	});
	
	//=================点击查看详细按钮事件处理=====================
	$("a#HomeFeeDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的编号
		$("tableHomeFeeGrid tbody tr").on("click",function(){
			feeno=$(this).attr("id");
			
		});
		
		if(feeno==0){
			BootstrapDialog.show({
	            title: '住宅供热记录操作信息',
	            message:"请选择要查看的住宅供热记录"
	        });
		}
		else{
			$("div#HomeFeeDialogArea").load("fee/homefee/detail.html",function(){
				//取得选择的住宅供热记录信息
				$.getJSON(host+"fee/homefee/get",{feeno:feeno},function(data){
					if(data.status=="OK"){
					
					$("span#homename").html(data.model.home.homename);
					$("span#neighbourhood").html(data.model.home.neighbourhood.hoodname);
					$("span#heatingarea").html(data.model.home.heatingarea);
					$("span#heatingdays").html(data.model.heatingprice.heatingdays);
					$("span#agreefee").html(data.model.agreefee);
					$("span#actualfee").html(data.model.actualfee);
					$("span#debtfee").html(data.model.debtfee);
					$("span#heatingstatus").html(data.model.home.heatingstatus);
					var feestatus = null;
					if(data.model.feestatus=='N'){
						feestatus = '欠费';
					}
					else{
						feestatus = '正常';
					}
					$("span#feestatus").html(feestatus);
					$("span#feedesc").html(data.model.feedesc);			
					}
				});
				$("div#HomeFeeDialogArea" ).dialog({
					title:"住宅供热记录细信息",
					width:600
				});
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#HomeFeeDialogArea" ).dialog( "close" );
					$( "div#HomeFeeDialogArea" ).dialog( "destroy" );
					$("div#HomeFeeDialogArea").html("");
				});
			});
		}
	});

	//==========点击增加链接处理，嵌入add.html===================
	$("a#HomeFeeAddLink").off().on("click",function(event){
		
		//取得年份列表，填充下拉框
		$.getJSON(host+"fee/heatingprice/list/all",function(heatingpriceList){
			if(heatingpriceList){
				$.each(heatingpriceList,function(index,heatingprice){
					$("select#HeatingPriceSelection").append("<option value='"+heatingprice.heatingyear+ "'>"+heatingprice.heatingyear+"</option>");
					
				});
			}
		});
		//取得居民列表，填充下拉框
		$.getJSON(host+"fee/home/list/all",function(homeList){
			if(homeList){
				$.each(homeList,function(index,home){
					$("select#HomeSelection").append("<option value='"+home.homeno+"'>"+home.homename+"</option>");
					//alert(home.homeno);
				});
			}
		});
		$("div#HomeFeeDialogArea").load("fee/homefee/add.html",function(){
			$("div#HomeFeeDialogArea" ).dialog({
				title:"增加住宅供热记录信息",
				width:600
			})
			
			//验证添加的信息是否已合法
			$("form#HomeFeeAddForm").validate({
				rules:{
					agreefee:{
						required:true,
						isFloatGteZero:true
					},
					actualfee:{
						required:true
					},
					debtfee:{
						required:true
					},
					feestatus:{
						required:true
					},
				}				
			})
			
			$("form#HomeFeeAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({
					title: '住宅供热记录操作信息',
					message:result.message
				});
				$("div#HomeFeeDialogArea" ).dialog( "close" );
				$("div#HomeFeeDialogArea" ).dialog( "destroy" );
				$("div#HomeFeeDialogArea").html("");
					
			});
				
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#HomeFeeDialogArea" ).dialog( "close" );
				$( "div#HomeFeeDialogArea" ).dialog( "destroy" );
				$("div#HomeFeeDialogArea").html("");
			});	
			
		});
	
	});	
	
	//=================点击修改记录按钮事件处理======================
	$("a#HomeFeeModifyLink").off().on("click",function(event){						
		//定义表格行的点击事件，取得选择的编号
		$("table#HomeFeeGrid tbody tr").on("click",function(){
			feeno=$(this).attr("id");	
		});
			
		if(feeno==0){
			BootstrapDialog.show({

	            title: '操作信息',
	            message:"请选择要修改的记录"

	        });
		}
		else {
			//取得年份列表，填充下拉框
			$.getJSON(host+"fee/heatingprice/list/all",function(heatingpriceList){
				if(heatingpriceList){
					$.each(heatingpriceList,function(index,heatingprice){
						$("select#HeatingPriceSelection").append("<option value='"+heatingprice.heatingyear+ "'>"+heatingprice.heatingyear+"</option>");
						
					});
				}
			});
			//取得居民列表，填充下拉框
			$.getJSON(host+"fee/home/list/all",function(homeList){
				if(homeList){
					$.each(homeList,function(index,home){
						$("select#HomeSelection").append("<option value='"+home.homeno+"'>"+home.homename+"</option>");
					});
				}
			});
			$("div#HomeFeeDialogArea").load("fee/homefee/modify.html",function(){
				$.getJSON(host+"fee/homefee/get",{feeno:feeno},function(data){
					if(data.status=="OK"){
						
						$("input[name='feeno']").val(feeno);
						$("input[name='agreefee']").val(data.model.agreefee);
						$("input[name='actualfee']").val(data.model.actualfee);
						$("input[name='debtfee']").val(data.model.debtfee);
						$("input[name='feedesc']").val(data.model.desc);
						$("input[value='"+data.model.feestatus+"']").prop('checked',true);

					}
				});
				
				$("div#HomeFeeDialogArea" ).dialog({
					title:"住宅供热记录信息修改",
					width:600
				});		
				//拦截表单提交
				$("form#HomeFeeModifyForm").ajaxForm(function(result){
			
					if(result.status=="OK"){
						reloadList();
					}
				
					BootstrapDialog.show({
			            title: '住宅供热记录修改操作信息',
			            message:result.message
			        });
					$("div#HomeFeeDialogArea" ).dialog( "close" );
					$("div#HomeFeeDialogArea" ).dialog( "destroy" );
					$("div#HomeFeeDialogArea").html("");			
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#HomeFeeDialogArea" ).dialog( "close" );
					$( "div#HomeFeeDialogArea" ).dialog( "destroy" );
					$("div#HomeFeeDialogArea").html("");
				});			
			});
		}				
	});

	//点击删除按钮事件处理
	$("a#HomeFeeDeteleLink").off().on("click",function(event){
		//定义表格行的点击事件
		$("table#HomeFeeGrid tbody tr").on("click",function(){
			feeno=$(this).attr("id");
		});
		if(feeno==0){
			BootstrapDialog.show({
	            title: '住宅供热记录操作信息',
	            message:"请选择要删除的记录"
	        });
		}
		else {
			BootstrapDialog.confirm('确认删除该记录么?', function(result){
				if(result) {
					$.post(host+"fee/homefee/delete",{feeno:feeno},function(result){
						if(result.status=="OK"){
							reloadList(); 
						}
						BootstrapDialog.show({
							title: '住宅供热记录操作信息',
							message:result.message
						});
					});					
				}
			});		
		}	
	});
})

	