/**
*
 * 
 */
$(function(){
	var typeno=0;
	var typename=null;
	//设置系统页面标题
	$("span#mainpagetille").html("户型管理");
	//设置日期的格式和选择
	
	//显示员工列表
	$("table#HouseTypeGrid").jqGrid({
		url: 'http://127.0.0.1:8080/fee/housetype/list/all/page',
		datatype: "json",
		colModel: [
			{ label: '户型编号', name: 'typeno', width: 75 },
			{ label: '户型名称', name: 'typename', width: 90, align: 'left' },
		          
		],
		viewrecords: true, 
		autowidth: true,
		height: 120,
		rowNum: 5,

		jsonReader : { 
		      root: "list", 
		      page: "page", 
		      total: "pageCount", 
		      records: "count", 
		      repeatitems: true, 
		      id: "typeno"},
		pager: "#HouseTypeGridPager",
		
	});
	//更新jQGrid的列表显示
	function reloadEmployeeList()
	{
		$("table#HouseTypeGrid").jqGrid('setGridParam',{postData:{typeno:typeno,tyepname:typenmae}}).trigger("reloadGrid");
		
	}

	
	
});