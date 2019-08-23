/**
 * 公建报停记录JS
 * 作者：张晓龙
 * */
 $(function(){
	//关联取
	var feeno = 0;
	//本表查询
	var recordno = 0;
	var stoparea = 0;
	var stopdate = null;
	var stopreason = null;
	var stopperson = null;
	var stopdesc = null;
	var stopstatus = null;
	
	//设置系统标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetitle'>供热缴费模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetitle'>公建报停记录</span></li>");
	
	//显示列表
	$("table#PublicHouseStopRecordGrid").jqGrid({
		url: host + 'fee/publichousestoprecord/getListByAllWithPage',
		datatype: "json",
		colModel: [
			{ label: '报停序号', name: 'recordno' },
			{ label: '报停面积', name: 'stoparea' },
			{ label: '报停日期', name: 'stopdate' },
			{ label: '报停原因', name: 'stopreason' },
			{ label: '报停人', name: 'stopperson' },
			{ label: '报停处理状态', name: 'stopstatus' },
		],
		viewrecords:true,
		autowidth: true,
		height: 200,
		rowNum: 5,
		jsonReader: {
			root: "list",
			page: "page",
			total: "pageCount",
			records: "count",
			repeatitems: true,
			id: "recordno"
		},
		pager: "#PublicHouseStopRecordGridPager",
	});
	
	$.getJSON(host+"fee/neighbourhood/list/all",function(neighbourhoodList){
		if(neighbourhoodList){
			$.each(neighbourhoodList,function(index,neighbourhood){
				$("select#NeighbourHoodSelection").append("<option value='"+neighbourhood.hoodno+"'>"+neighbourhood.hoodname+"</option>");
			});
		}
	});
	
	$.getJSON(host+"fee/heatingprice/list/all",function(heatingpriceList){
		if(heatingpriceList){
			$.each(heatingpriceList,function(index,heatingprice){
				$("select#HeatingYearSelection").append("<option value='"+heatingprice.heatingyear+"'>"+heatingprice.heatingyear+"</option>");
			});
		}
	});
	
	function reloadList()
	{
		$("table#HomeFeeGrid").jqGrid('setGridParam',
		{
			postData:{				
				hoodno:hoodno,
				heatingyear:heatingyear,
				recordstatus:recordstatus
			},
			
		}).trigger("reloadGrid");
	}

	$("a#HomeFeeSearchButton").on("click",function(){
		hoodno=$("select#NeighbourHoodSelection").val();	
		heatingyear=$("select#HeatingYearSelection").val();
		recordstatus=$("input[name='recordstatus']:checked").val();
		reloadList();
	});
	
 });