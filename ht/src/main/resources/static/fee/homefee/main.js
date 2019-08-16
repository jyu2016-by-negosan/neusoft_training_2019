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
	
	
	/* //=================点击修改按钮事件处理======================
	$("a#HeatingHomeModifyLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的编号
		$("table#HeatingHomeGrid tbody tr").on("click",function(){
			homeno=$(this).attr("id");
			
		});
		
		if(homeno==0){
			BootstrapDialog.show({
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
				$.getJSON(host+"fee/home/get",{homeno:homeno},function(data){
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
						$("input[value='"+data.model.direction+"']").prop('checked',true);
						$("input[name='tel']").val(data.model.tel);
						$("input[name='mobile']").val(data.model.mobile);
						$("input[name='qq']").val(data.model.qq);
						$("input[name='mail']").val(data.model.mail);
						$("input[value='"+data.model.heatingstatus+"']").prop('checked',true);
						$("input[name='heatingarea']").val(data.model.heatingarea);
					}
				});

				$("div#HeatingHomeDialogArea" ).dialog({
					title:"居民信息修改",
					width:600
				});
				
				//验证添加的信息是否已合法
				$("form#HeatingHomeModifyForm").validate({
					rules: {
						neighbourhood: {
							required: true,			  
						},
						heatingcode: {
							required: true,
						},
						homearea: {
							required: true,
						},	
						heatingarea:{
							required: true,
						},
						homename: {
							required: true,
						},
						mail:{
					    	required:true,
					    	email: true
					    },
					    mobile:{
					    	required:true,
					    	mobile:true
					    },
						qq: {
							required: true,
						},
						buildingcode: {
							required: true,
						},
						departmentcode: {
							required: true,
						},
						floorcode: {
							required: true,
						},
						housecode: {
							required: true,
						}
					},
					messages:{
						neighbourhood: {
							required: "必须选择小区!",
						},
						homename: {
							required: "姓名不能为空!",
						},			
					}	
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
	 */
	
});