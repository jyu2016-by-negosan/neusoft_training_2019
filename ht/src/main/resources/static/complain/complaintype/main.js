/**
 * 模块：供热缴费管理 小区管理的前端控制JS 作者：黄宇德
 * 
 */

$(function() {
	var rows = 5;
	var page = 1;
	var pageCount = 0;
	var no = 0; // 选择的小区编号

	// 设置系统页面标题
	$("ol.breadcrumb")
			.html(
					"<li class='breadcrumb-item'><span id='mainpagetille'>客户服务模块</span></li>"
							+ "<li class='breadcrumb-item'><span id='mainpagetille'>投诉管理</span></li>");

	function getListInfo() {
		// 取得列表，分页模式
		$.getJSON(host + "complain/complaintype/list/all/page", {
			pages : page,
			rows : rows
		}, function(data) {
			// 显示个数和页数
			$("span#count").html(data.count);
			$("span#pagecount").html(data.page + "/" + data.pageCount);
			pageCount = data.pageCount;
			// 显示列表
			$("table#ComplainTypeTable tbody").html("");
			for (var i = 0; i < data.list.length; i++) {
				var tr = "<tr id='" + data.list[i].typeno + "'><td>"
						+ data.list[i].typeno + "</td><td>"
						+ data.list[i].typename + "</td></tr>";
				$("table#ComplainTypeTable tbody").append(tr);
			}
			// 定义表格行的点击时间，取得选择的小区号
			$("table#ComplainTypeTable tbody tr").on(
					"click",
					function() {
						no = $(this).attr("id");
						$("table#ComplainTypeTable tbody tr").css(
								"background-color", "#FFFFFF");
						$(this).css("background-color", "#6495ED");
					});
		});

	}
	// 定义分页导航链接处理事件
	$("div#page_nav a").on("click", function(event) {
		var action = $(this).attr("href");
		event.preventDefault();
		switch (action) {
		case "top":
			page = 1;
			getListInfo();
			break;
		case "pre":
			if (page > 1) {
				page = page - 1;
				getListInfo();
			}
			break;
		case "next":
			if (page < pageCount) {
				page = page + 1;
				getListInfo();
			}
			break;
		case "last":
			page = pageCount;
			getListInfo();
			break;
		}

	});

	// 初始调用取得分页列表数据
	getListInfo();

	// 点击增加链接处理，嵌入add.html
	$("a#ComplainTypeAddLink").off().on(
			"click",
			function(event) {

				$("div#ComplainTypeDialogArea").load(
						"complain/complaintype/add.html",
						function() {
							$("div#ComplainTypeDialogArea").dialog({
								title : "增加投诉",
								width : 600
							});

							$("form#ComplainTypeAddForm").ajaxForm(
									function(result) {
										if (result.status == "OK") {
											getListInfo();
										}
										BootstrapDialog.show({
											title : '投诉操作信息',
											message : result.message
										});
										$("div#ComplainTypeDialogArea").dialog(
												"close");
										$("div#ComplainTypeDialogArea").dialog(
												"destroy");
										$("div#ComplainTypeDialogArea")
												.html("");

									});

							// 点击取消按钮处理
							$("input[value='取消']").on(
									"click",
									function() {
										$("div#ComplainTypeDialogArea").dialog(
												"close");
										$("div#ComplainTypeDialogArea").dialog(
												"destroy");
										$("div#ComplainTypeDialogArea")
												.html("");
									});
						});
			});

	// 点击修改按钮事件处理
	$("a#ComplainTypeModifyLink").off().on(
			"click",
			function(event) {
				if (no == 0) {
					BootstrapDialog.show({
						title : '投诉操作信息',
						message : "请选择要修改的投诉"
					});
				} else {
					$("div#ComplainTypeDialogArea").load(
							"complain/complaintype/modify.html",
							function() {
								// 取得选择的部门
								$.getJSON(host + "complain/complaintype/get", {
									typeno : no
								}, function(data) {
									if (data.status == "OK") {
										$("input[name='typeno']").val(no);
										$("input[name='typename']").val(
												data.model.typename);
									}
								});

								$("div#ComplainTypeDialogArea").dialog({
									title : "投诉修改",
									width : 600
								});
								// 拦截表单提交
								$("form#ComplainTypeModifyForm").ajaxForm(
										function(result) {

											if (result.status == "OK") {
												getListInfo();
											}

											BootstrapDialog.show({
												title : '投诉操作信息',
												message : result.message
											});
											$("div#ComplainTypeDialogArea")
													.dialog("close");
											$("div#ComplainTypeDialogArea")
													.dialog("destroy");
											$("div#ComplainTypeDialogArea")
													.html("");

										});

								// 点击取消按钮处理
								$("input[value='取消']").on(
										"click",
										function() {
											$("div#ComplainTypeDialogArea")
													.dialog("close");
											$("div#ComplainTypeDialogArea")
													.dialog("destroy");
											$("div#ComplainTypeDialogArea")
													.html("");
										});
							});

				}

			});

	// 点击删除按钮事件处理
	$("a#ComplainTypeDeleteLink").off().on("click", function(event) {

		if (no == 0) {
			BootstrapDialog.show({
				title : '投诉操作信息',
				message : "请选择要删除的投诉"
			});
		} else {

			BootstrapDialog.confirm('确认删除投诉吗?', function(result) {
				if (result) {
					$.post(host + "complain/complaintype/delete", {
						typeno : no
					}, function(result) {
						if (result.status == "OK") {
							getListInfo();
						}
						BootstrapDialog.show({
							title : '投诉操作信息',
							message : result.message
						});
					});
				}
			});

		}

	});

});
