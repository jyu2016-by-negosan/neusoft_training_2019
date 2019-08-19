/**
 * 
 */
/**
*
 * 
 */
$(function(){
	var rows = 5;
	var page = 1;
	var pageCount = 0;
	var no = 0; // 选择的投诉编号

	// 设置系统页面标题
	$("ol.breadcrumb")
			.html(
					"<li class='breadcrumb-item'><span id='mainpagetille'>客户服务模块</span></li>"
							+ "<li class='breadcrumb-item'><span id='mainpagetille'>投诉管理</span></li>");

	function getListInfo() {
		// 取得列表，分页模式
		$.getJSON(host + "complain/homecomplain/list/all/page", {
			pages : page,
			rows : rows
		}, function(data) {
			// 显示个数和页数
			$("span#count").html(data.count);
			$("span#pagecount").html(data.page + "/" + data.pageCount);
			pageCount = data.pageCount;
			// 显示列表
			$("table#HomeComplainTable tbody").html("");
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
				$("table#HomeComplainTable tbody").append(tr);
			}
			// 定义表格行的点击时间，取得选择的小区号
			$("table#HomeComplainTable tbody tr").on(
					"click",
					function() {
						no = $(this).attr("id");
						$("table#HomeComplainTable tbody tr").css(
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
	
	
	
});