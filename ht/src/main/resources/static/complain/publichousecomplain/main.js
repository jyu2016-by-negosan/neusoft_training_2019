
$(function(){
	var rows = 5;
	var page = 1;
	var pageCount = 0;
	var no = 0; // 选择的投诉编号

	// 设置系统页面标题
	$("ol.breadcrumb")
			.html(
					"<li class='breadcrumb-item'><span id='mainpagetille'>客户服务模块</span></li>"
							+ "<li class='breadcrumb-item'><span id='mainpagetille'>公建投诉管理</span></li>");

	function getListInfo() {
		// 取得列表，分页模式
		$.getJSON(host + "complain/housecomplain/list/all/page", {
			pages : page,
			rows : rows
		}, function(data) {
			// 显示个数和页数
			$("span#count").html(data.count);
			$("span#pagecount").html(data.page + "/" + data.pageCount);
			pageCount = data.pageCount;
			// 显示列表
			$("table#HouseComplainTable tbody").html("");
			for (var i = 0; i < data.list.length; i++) {
				var tr = "<tr id='" + data.list[i].complainno + "'><td>"
						+ data.list[i].complainno + "</td><td>"
						+ data.list[i].complaintitle + "</td><td>"
						+ data.list[i].complaincontent + "</td><td>"
						+ data.list[i].requestcontent + "</td><td>"
						+ data.list[i].complaindate + "</td><td>"
						+ data.list[i].contactperson + "</td><td>"
						+ data.list[i].tel + "</td><td>"
						+ data.list[i].mobile + "</td><td>"
						+ data.list[i].mail + "</td><td>"
						+ data.list[i].qq + "</td></tr>";
				$("table#HouseComplainTable tbody").append(tr);
			}
			// 定义表格行的点击时间，取得选择的小区号
			$("table#HouseComplainTable tbody tr").on(
					"click",
					function() {
						no = $(this).attr("id");
						$("table#HouseComplainTable tbody tr").css(
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
	$("a#HouseComplainAddLink").off().on(
			"click",
			function(event) {

				$("div#HouseComplainDialogArea").load(
						"complain/complaintype/add.html",
						function() {
							$("div#HouseComplainDialogArea").dialog({
								title : "增加公建投诉",
								width : 600
							});

							$("form#HouseComplainAddForm").ajaxForm(
									function(result) {
										if (result.status == "OK") {
											getListInfo();
										}
										BootstrapDialog.show({
											title : '投诉操作信息',
											message : result.message
										});
										$("div#HouseComplainDialogArea").dialog(
												"close");
										$("div#HouseComplainDialogArea").dialog(
												"destroy");
										$("div#HouseComplainDialogArea")
												.html("");

									});

							// 点击取消按钮处理
							$("input[value='取消']").on(
									"click",
									function() {
										$("div#HouseComplainDialogArea").dialog(
												"close");
										$("div#HouseComplainDialogArea").dialog(
												"destroy");
										$("div#HouseComplainDialogArea")
												.html("");
									});
						});
			});
	
});