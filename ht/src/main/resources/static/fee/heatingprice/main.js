/**
 * 模块：供热缴费管理
 * 年度供热价格管理的前端控制JS
 * 作者：黄宇德
 * 
 */
$(function(){
	var year = null;//供热年份
	var days= 0 ;	//供热天数
	var homeprice = null;//居民价格
	var publichouseprice = null;//公建价格
	var memo = null;//备注
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>供热缴费模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>年度供热价格管理</span></li>");

	//显示列表
	$("table#HeatingPriceGrid").jqGrid({
		url: host+'fee/heatingprice/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '供热年份', name: 'heatingyear'},
			{ label: '供热天数', name: 'heatingdays'},
			{ label: '居民价格', name: 'homeprice'},
			{ label: '公建价格', name: 'publichouseprice'},
			          
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
		      id: "heatingyear"},
		pager: "#HeatingPriceGridPager",
		
	});
	//更新jQGrid的列表显示
	function reloadList()
	{
		$("table#HeatingPriceGrid").jqGrid('setGridParam',{postData:{heatingyear:year,heatingdays:days,
		homeprice:homeprice,publichouseprice:publichouseprice,heatingmemo:memo}}).trigger("reloadGrid");

	}
	
	//点击增加链接处理，嵌入add.html
	$("a#HeatingPriceAddLink").off().on("click",function(event){	
		$("div#HeatingPriceDialogArea").load("fee/heatingprice/add.html",function(){
			$("div#HeatingPriceDialogArea" ).dialog({
				title:"增加年度供热价格",
				width:600
			});			
			//验证年份是否已存在
			$("form#HeatingPriceAddForm").validate({
				rules: {
					heatingyear: {
						required: true,
						remote: host+"fee/heatingprice/checkyear"
					  
					},
					heatingdays: {
						required: true,
					},
					homeprice: {
						required: true,
					},
					publichouseprice: {
						required: true,
					},
					heatingmemo: {
						required: true,
					}
				},
				messages:{
					heatingyear: {
						required: "年份不能为空!",
						remote:"年份已经存在"
					},
					heatingdays: {
						required: "天数不能为空!",
					},
					homeprice: {
						required: "价格不能为空!",
					},
					publichouseprice: {
						required: "价格不能为空!",
					},
					heatingmemo: {
						required: "备注信息不能为空!",
					}
				}
				
			});
			$("form#HeatingPriceAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({
					title: '年度供热价格操作信息',
					message:result.message
				});
				$("div#HeatingPriceDialogArea" ).dialog( "close" );
				$("div#HeatingPriceDialogArea" ).dialog( "destroy" );
				$("div#HeatingPriceDialogArea").html("");
				
			});			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#HeatingPriceDialogArea" ).dialog( "close" );
				$( "div#HeatingPriceDialogArea" ).dialog( "destroy" );
				$("div#HeatingPriceDialogArea").html("");
			});				
		});		
	});
	
	//点击修改按钮事件处理
	$("a#HeatingPriceModifyLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的年份
		$("table#HeatingPriceGrid tbody tr").on("click",function(){
			year=$(this).attr("id");
			
		});
		if(year==null){
			BootstrapDialog.show({
	            title: '年度供热价格操作信息',
	            message:"请选择要修改的年份"
	        });
		}
		else {
			$("div#HeatingPriceDialogArea").load("fee/heatingprice/modify.html",function(){
				
				$.getJSON(host+"fee/heatingprice/get",{heatingyear:year},function(data){
					if(data.status=="OK"){
						$("input[name='heatingyear']").val(year);
						$("input[name='heatingdays']").val(data.model.heatingdays);
						$("input[name='homeprice']").val(data.model.homeprice);
						$("input[name='publichouseprice']").val(data.model.publichouseprice);
						$("input[name='heatingmemo']").val(data.model.heatingmemo);
					}
				});
				
				$("div#HeatingPriceDialogArea" ).dialog({
					title:"年度供热价格修改",
					width:600
				});
				//拦截表单提交
				$("form#HeatingPriceModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '年度供热价格操作信息',
			            message:result.message
			        });
					$("div#HeatingPriceDialogArea" ).dialog( "close" );
					$("div#HeatingPriceDialogArea" ).dialog( "destroy" );
					$("div#HeatingPriceDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#HeatingPriceDialogArea" ).dialog( "close" );
					$( "div#HeatingPriceDialogArea" ).dialog( "destroy" );
					$("div#HeatingPriceDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#HeatingPriceDeleteLink").off().on("click",function(event){
		
		//定义表格行的点击事件
		$("table#HeatingPriceGrid tbody tr").on("click",function(){
			year=$(this).attr("id");
			
		});
		if(year==null){
			BootstrapDialog.show({
	            title: ' 年度供热价格操作信息',
	            message:"请选择要删除的年份"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除该年份么?', function(result){
				if(result) {
					$.post(host+"fee/heatingprice/delete",{heatingyear:year},function(result){
						if(result.status=="OK"){
							reloadList(); 
						}
						BootstrapDialog.show({
							title: '年度供热价格操作信息',
							message:result.message
						});
					});
				}
			});
				
	
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#HeatingPriceDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的编号
		$("table#HeatingPriceGrid tbody tr").on("click",function(){
			year=$(this).attr("id");
			
		});
		if(year==null){
			BootstrapDialog.show({
	            title: '年度供热价格操作信息',
	            message:"请选择要查看的年份"
	        });
		}
		else{
			$("div#HeatingPriceDialogArea").load("fee/heatingprice/detail.html",function(){
				//取得选择的年份
				$.getJSON(host+"fee/heatingprice/get",{heatingyear:year},function(data){
					if(data.status=="OK"){
						$("span#heatingyear").html(data.model.heatingyear);
						$("span#heatingdays").html(data.model.heatingdays);
						$("span#homeprice").html(data.model.homeprice);
						$("span#publichouseprice").html(data.model.publichouseprice);
						$("span#heatingmemo").html(data.model.heatingmemo);
					
					}
				});
				$("div#HeatingPriceDialogArea" ).dialog({
					title:"年度供热价格详细",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#HeatingPriceDialogArea" ).dialog( "close" );
					$( "div#HeatingPriceDialogArea" ).dialog( "destroy" );
					$("div#HeatingPriceDialogArea").html("");
				});
			});
		}
	});
});