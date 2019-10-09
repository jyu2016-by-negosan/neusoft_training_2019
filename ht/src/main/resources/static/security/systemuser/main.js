/**模块：系统安全模块
 * 系统操作员的前端控制JS
 * @author 吴澍淼
 *
 */
$(function(){
	var userid = null;		//登录账号
	var password = null ;	//密码
	var name = null;		//角色
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>系统安全模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>系统操作员</span></li>");

	//显示列表
	$("table#SystemUserGrid").jqGrid({
		url: host+'user/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '登录账号', name: 'uuserid'},
			{ label: '密码', name: 'upassword'},
			{ label: '角色', name: 'uname'},
			          
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
		      id: "uuserid"},
		pager: "#SystemUserPager",
		
	});
	//更新jQGrid的列表显示
	function reloadList()
	{
		$("table#SystemUserGrid").jqGrid('setGridParam',{postData:{uuserid:userid,upassword:password,
		uname:name}}).trigger("reloadGrid");

	}
	
	//点击增加链接处理，嵌入add.html
	$("a#SystemUserAddLink").off().on("click",function(event){	
		$("div#SystemUserDialogArea").load("security/systemuser/add.html",function(){
			$("div#SystemUserDialogArea" ).dialog({
				title:"增加系统管理员",
				width:600
			});			
			//验证账号是否已存在
			$("form#SystemUserAddForm").validate({
				rules: {
					uuserid: {
						required: true,
						remote: host+"security/userinfo/getUserInfoById"
					  
					},
					upassword: {
						required: true,
					},
					uname: {
						required: true,
					},
				},
				messages:{
					uuserid: {
						required: "登录账户不能为空!",
						remote:"登录账户已经存在"
					},
					upassword: {
						required: "密码不能为空!",
					},
					uname: {
						required: "角色不能为空!",
					},
				}
				
			});
			
			$("form#SystemUserAddForm").ajaxForm(function(result){
				alert(result.status);
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({
					title: '系统管理员操作信息',
					message:result.message
				});
				$("div#SystemUserDialogArea" ).dialog( "close" );
				$("div#SystemUserDialogArea" ).dialog( "destroy" );
				$("div#SystemUserDialogArea").html("");
				
			});			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#SystemUserDialogArea" ).dialog( "close" );
				$( "div#SystemUserDialogArea" ).dialog( "destroy" );
				$("div#SystemUserDialogArea").html("");
			});				
		});		
	});
	
	//点击修改按钮事件处理
	$("a#SystemUserModifyLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的年份
		$("table#SystemUserGrid tbody tr").on("click",function(){
			userid=$(this).attr("id");
			
		});
		if(userid==null){
			BootstrapDialog.show({
	            title: '系统管理员操作信息',
	            message:"请选择要修改的系统管理员"
	        });
		}
		else {
			$("div#SystemUserDialogArea").load("security/systemuser/modify.html",function(){
				
				$.getJSON(host+"user/getSystemUserById",{uuserid:userid},function(data){
					if(data.status=="OK"){
						$("input[name='uuserid']").val(userid);
						$("input[name='upassword']").val(data.model.upassword);
						$("input[name='uname']").val(data.model.uname);
					}
				});
				
				$("div#SystemUserDialogArea" ).dialog({
					title:"系统管理员修改",
					width:600
				});
				//拦截表单提交
				$("form#SystemUserModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '系统管理员操作信息',
			            message:result.message
			        });
					$("div#SystemUserDialogArea" ).dialog( "close" );
					$("div#SystemUserDialogArea" ).dialog( "destroy" );
					$("div#SystemUserDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#SystemUserDialogArea" ).dialog( "close" );
					$( "div#SystemUserDialogArea" ).dialog( "destroy" );
					$( "div#SystemUserDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#SystemUserDeleteLink").off().on("click",function(event){
		
		//定义表格行的点击事件
		$("table#SystemUserGrid tbody tr").on("click",function(){
			userid=$(this).attr("id");
			
		});
		if(userid==null){
			BootstrapDialog.show({
	            title: '系统管理员操作信息',
	            message:"请选择要删除的系统管理员"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除该系统管理员吗?', function(result){
				if(result) {
					$.get(host+"user/getSystemUserById",{uuserid:userid},function(result){
						if(!result.model.upassword&&!result.model.uname){
							$.post(host+"user/delete",{uuserid:userid},function(result){
								if(result.status=="OK"){
									reloadList(); 
								}
								BootstrapDialog.show({
									title: '系统管理员操作信息',
									message:result.message
								});
							});
						}
						BootstrapDialog.show({
							title: '系统管理员操作信息',
							message:"不能删除有相关信息的系统管理员！"
						});
					});
					
				}
			});
				
	
			
		}
		
	});
	
	//点击查看详细按钮事件处理
	$("a#SystemUserDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的编号
		$("table#SystemUserGrid tbody tr").on("click",function(){
			userid=$(this).attr("id");
			
		});
		if(userid==null){
			BootstrapDialog.show({
	            title: '系统管理员操作信息',
	            message:"请选择要查看的系统管理员"
	        });
		}
		else{
			$("div#SystemUserDialogArea").load("security/systemuser/detail.html",function(){
				//取得选择的年份
				$.getJSON(host+"user/getSystemUserById",{uuserid:userid},function(data){
					if(data.status=="OK"){
						$("span#uuserid").html(data.model.uuserid);
						$("span#upassword").html(data.model.upassword);
						$("span#uname").html(data.model.uname);
					}
				});
				$("div#SystemUserDialogArea" ).dialog({
					title:"系统管理员信息详细",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#SystemUserDialogArea" ).dialog( "close" );
					$( "div#SystemUserDialogArea" ).dialog( "destroy" );
					$( "div#SystemUserDialogArea").html("");
				});
			});
		}
	});
});