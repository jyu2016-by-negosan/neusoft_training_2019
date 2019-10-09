/**模块：系统安全模块
 * 系统功能的前端控制JS
 * @author 吴澍淼
 *
 */
$(function(){
	var no = 0;		//功能编号
	var name= null ;	//功能名称
	var url = null;	//URL地址
	var levelno = 0;	//功能级别号
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>系统安全模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>系统功能管理</span></li>");

	//显示列表
	$("table#SystemFunctionGrid").jqGrid({
		url: host+'security/systemfunction/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '功能编号', name: 'funno'},
			{ label: '功能名称', name: 'funname'},
			{ label: 'URL地址', name: 'funurl'},
			{ label: '功能级别号', name: 'levelno'},
			          
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
		      id: "funno"},
		pager: "#SystemFunctionGridPager",
		
	});
	//更新jQGrid的列表显示
	function reloadList()
	{
		$("table#SystemFunctionGrid").jqGrid('setGridParam',{postData:{funno:no,funname:name,
		funurl:url,levelno:levelno}}).trigger("reloadGrid");

	}
	
	//点击增加链接处理，嵌入add.html
	$("a#SystemFunctionAddLink").off().on("click",function(event){	
		$("div#SystemFunctionDialogArea").load("security/systemfunction/add.html",function(){
			$("div#SystemFunctionDialogArea" ).dialog({
				title:"增加系统功能",
				width:600
			});			
			//验证系统功能是否已存在
			$("form#SystemFunctionAddForm").validate({
				rules: {
					funno: {
						required: true,
					},
					funname: {
						required: true,
					},
					funurl: {
						required: true,
					},
					levelno: {
						required: true,
					},
				},
				messages:{
					funno: {
						required: "功能编号不能为空!",
					},
					funname: {
						required: "功能名称不能为空!",
					},
					funurl: {
						required: "URL地址不能为空!",
					},
					levelno: {
						required: "功能级别号不能为空!",
					},
				}
				
			});
			$("form#SystemFunctionAddForm").ajaxForm(function(result){
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({
					title: '系统功能操作信息',
					message:result.message
				});
				$("div#SystemFunctionDialogArea" ).dialog( "close" );
				$("div#SystemFunctionDialogArea" ).dialog( "destroy" );
				$("div#SystemFunctionDialogArea").html("");
				
			});			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#SystemFunctionDialogArea" ).dialog( "close" );
				$( "div#SystemFunctionDialogArea" ).dialog( "destroy" );
				$("div#SystemFunctionDialogArea").html("");
			});				
		});		
	});
	
	//点击修改按钮事件处理
	$("a#SystemFunctionModifyLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的功能编号
		$("table#SystemFunctionGrid tbody tr").on("click",function(){
			no=$(this).attr("id");
		});
		if(no==0){
			BootstrapDialog.show({
	            title: '系统功能操作信息',
	            message:"请选择要修改的系统功能编号"
	        });
		}
		else {
			$("div#SystemFunctionDialogArea").load("security/systemfunction/modify.html",function(){
				
				$.getJSON(host+"security/systemfunction/getSystemFunctionByNo",{funno:no},function(data){
					if(data.status=="OK"){
						$("input[name='funno']").val(no);
						$("input[name='funname']").val(data.model.funname);
						$("input[name='funurl']").val(data.model.funurl);
						$("input[name='levelno']").val(data.model.levelno);
					}
				});
				
				$("div#SystemFunctionDialogArea" ).dialog({
					title:"系统功能修改",
					width:600
				});
				//拦截表单提交
				$("form#SystemFunctionModifyForm").ajaxForm(function(result){
					alert(result.status);
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '系统功能操作信息8888888',
			            message:result.message
			        });
					$("div#SystemFunctionDialogArea" ).dialog( "close" );
					$("div#SystemFunctionDialogArea" ).dialog( "destroy" );
					$("div#SystemFunctionDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#SystemFunctionDialogArea" ).dialog( "close" );
					$( "div#SystemFunctionDialogArea" ).dialog( "destroy" );
					$("div#SystemFunctionDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#SystemFunctionDeleteLink").off().on("click",function(event){
		
		//定义表格行的点击事件
		$("table#SystemFunctionGrid tbody tr").on("click",function(){
			no=$(this).attr("id");
			
		});
		if(no==0){
			BootstrapDialog.show({
	            title: ' 系统功能操作信息',
	            message:"请选择要删除的系统功能"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除该系统功能吗?', function(result){
				if(result) {
					$.post(host+"security/systemfunction/delete",{funno:no},function(result){
						if(result.status=="OK"){
							reloadList(); 
						}
						BootstrapDialog.show({
							title: '系统功能操作信息',
							message:result.message
						});
					});
				}
			});
				
	
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#SystemFunctionDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的系统功能编号
		$("table#SystemFunctionGrid tbody tr").on("click",function(){
			no=$(this).attr("id");
			
		});
		if(no==0){
			BootstrapDialog.show({
	            title: '系统功能操作信息',
	            message:"请选择要查看的系统功能"
	        });
		}
		else{
			$("div#SystemFunctionDialogArea").load("security/systemfunction/detail.html",function(){
				//取得选择的年份
				$.getJSON(host+"security/systemfunction/getSystemFunctionByNo",{funno:no},function(data){
					if(data.status=="OK"){
						$("span#funno").html(data.model.funno);
						$("span#funname").html(data.model.funname);
						$("span#funurl").html(data.model.funurl);
						$("span#levelno").html(data.model.levelno);
					}
				});
				$("div#SystemFunctionDialogArea" ).dialog({
					title:"系统功能详细",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#SystemFunctionDialogArea" ).dialog( "close" );
					$( "div#SystemFunctionDialogArea" ).dialog( "destroy" );
					$("div#SystemFunctionDialogArea").html("");
				});
			});
		}
	});
});