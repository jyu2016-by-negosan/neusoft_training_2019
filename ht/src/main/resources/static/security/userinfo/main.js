/**模块：系统安全模块
 * 系统管理员辅助信息的前端控制JS
 * @author 吴澍淼
 *
 */
$(function(){
	var uuserid = null;		//登录账号
	var sex = null ;		//性别
	var age = 0;			//年龄
	var photofilename = null;		//照片文件名
	var photocontenttype = null;	//照片文件类型
	//设置系统页面标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetille'>系统安全模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetille'>系统管理员辅助信息</span></li>");

	//显示列表
	$("table#UserInfoGrid").jqGrid({
		url: host+'security/userinfo/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '登录账号', name: 'uuserid'},
			{ label: '性别', name: 'sex'},
			{ label: '年龄', name: 'age'},
			{ label: '照片文件名', name: 'photofilename'},
			{ label: '照片文件类型', name: 'photocontenttype'},
			          
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
		pager: "#UserInfoGridPager",
		
	});
	//更新jQGrid的列表显示
	function reloadList()
	{
		$("table#UserInfoGrid").jqGrid('setGridParam',{postData:{uuserid:uuserid,sex:sex,
		age:age,photofilename:photofilename,photocontenttype:photocontenttype}}).trigger("reloadGrid");

	}
	
	//点击增加链接处理，嵌入add.html
	$("a#UserInfoAddLink").off().on("click",function(event){	
		$("div#UserInfoDialogArea").load("security/userinfo/add.html",function(){
			$("div#UserInfoDialogArea" ).dialog({
				title:"增加系统管理员",
				width:600
			});			
			//验证账号是否已存在
			$("form#UserInfoAddForm").validate({
				rules: {
					uuserid: {
						required: true,
						remote: host+"security/userinfo/checkid" 
					},
					sex: {
						required: true,
					},
					age: {
						required: true,
					},
					photofilename: {
						required: true,
					},
					photocontenttype: {
						required: true,
					}
				},
				messages:{
					uuserid: {
						required: "登录账户不能为空!",
						remote:"登录账户已经存在"
					},
					sex: {
						required: "性别不能为空!",
					},
					age: {
						required: "年龄不能为空!",
					},
					photofilename: {
						required: "照片文件名不能为空!",
					},
					photocontenttype: {
						required: "照片文件类型不能为空!",
					}
				}
				
			});
			
			$("form#UserInfoAddForm").ajaxForm(function(result){
				alert(result.status);
				if(result.status=="OK"){
					reloadList();
				}
				BootstrapDialog.show({
					title: '系统管理员操作信息',
					message:result.message
				});
				$("div#UserInfoDialogArea" ).dialog( "close" );
				$("div#UserInfoDialogArea" ).dialog( "destroy" );
				$("div#UserInfoDialogArea").html("");
				
			});			
			//点击取消按钮处理
			$("input[value='取消']").on("click",function(){
				$( "div#UserInfoDialogArea" ).dialog( "close" );
				$( "div#UserInfoDialogArea" ).dialog( "destroy" );
				$("div#UserInfoDialogArea").html("");
			});				
		});		
	});
	
	//点击修改按钮事件处理
	$("a#UserInfoModifyLink").off().on("click",function(event){
		
		//定义表格行的点击事件，取得选择的账号
		$("table#UserInfoGrid tbody tr").on("click",function(){
			uuserid=$(this).attr("id");
			
		});
		if(uuserid==null){
			BootstrapDialog.show({
	            title: '系统管理员操作信息',
	            message:"请选择要修改的系统管理员"
	        });
		}
		else {
			$("div#UserInfoDialogArea").load("security/userinfo/modify.html",function(){
				
				$.getJSON(host+"security/userinfo/get",{uuserid:uuserid},function(data){
					if(data.status=="OK"){
						$("input[name='uuserid']").val(uuserid);
						$("input[name='sex']").val(data.model.sex);
						$("input[name='age']").val(data.model.age);
						$("input[name='photofilename']").val(data.model.photofilename);
						$("input[name='photocontenttype']").val(data.model.photocontenttype);
					}
				});
				
				$("div#UserInfoDialogArea" ).dialog({
					title:"系统管理员修改",
					width:600
				});
				//拦截表单提交
				$("form#UserInfoModifyForm").ajaxForm(function(result){
					
					if(result.status=="OK"){
						reloadList();
					}
	
					BootstrapDialog.show({
			            title: '系统管理员操作信息',
			            message:result.message
			        });
					$("div#UserInfoDialogArea" ).dialog( "close" );
					$("div#UserInfoDialogArea" ).dialog( "destroy" );
					$("div#UserInfoDialogArea").html("");
					
				});
				
				
				//点击取消按钮处理
				$("input[value='取消']").on("click",function(){
					$( "div#UserInfoDialogArea" ).dialog( "close" );
					$( "div#UserInfoDialogArea" ).dialog( "destroy" );
					$( "div#UserInfoDialogArea").html("");
				});
			});
			
		}
		
		
	});
	
	//点击删除按钮事件处理
	$("a#UserInfoDeleteLink").off().on("click",function(event){
		
		//定义表格行的点击事件
		$("table#UserInfoGrid tbody tr").on("click",function(){
			uuserid=$(this).attr("id");
			
		});
		if(uuserid==null){
			BootstrapDialog.show({
	            title: '系统管理员操作信息',
	            message:"请选择要删除的系统管理员"
	        });
		}
		else {
		
			BootstrapDialog.confirm('确认删除该系统管理员吗?', function(result){
				if(result) {
					$.get(host+"security/userinfo/getUserInfoById",{uuserid:uuserid},function(result){
						if(!result.model.sex&&!result.model.age){
							$.post(host+"security/userinfo/delete",{uuserid:uuserid},function(result){
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
	$("a#UserInfoDetailLink").off().on("click",function(event){
		//定义表格行的点击事件，取得选择的编号
		$("table#UserInfoGrid tbody tr").on("click",function(){
			uuserid=$(this).attr("id");
			
		});
		if(uuserid==null){
			BootstrapDialog.show({
	            title: '系统管理员操作信息',
	            message:"请选择要查看的系统管理员"
	        });
		}
		else{
			$("div#UserInfoDialogArea").load("security/userinfo/detail.html",function(){
				//取得选择的年份
				$.getJSON(host+"security/userinfo/getUserInfoById",{uuserid:uuserid},function(data){
					if(data.status=="OK"){
						$("span#uuserid").html(data.model.uuserid);
						$("span#sex").html(data.model.sex);
						$("span#age").html(data.model.age);
						$("span#photofilename").html(data.model.photofilename);
						$("span#photocontenttype").html(data.model.photocontenttype);
					
					}
				});
				$("div#UserInfoDialogArea" ).dialog({
					title:"系统管理员信息详细",
					width:600
				});
			
			
				//点击取消按钮处理
				$("input[value='返回']").on("click",function(){
					$( "div#UserInfoDialogArea" ).dialog( "close" );
					$( "div#UserInfoDialogArea" ).dialog( "destroy" );
					$( "div#UserInfoDialogArea").html("");
				});
			});
		}
	});
});