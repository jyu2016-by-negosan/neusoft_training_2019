/**
 * 公建退费记录JS
 * 作者：张晓龙
 * */
 $(function(){
	//关联取
	var feeno = 0;
	var typeno = 0;
	//本表查询
	var recordno = 0;
	var amount = 0;
	var returndate = null;
	var person = null;
	var checkcode = 0;
	var invoicecode = 0;
	var recorddesc = null;
	var recordstatus = null;
	
	//设置系统标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetitle'>供热缴费模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetitle'>公建退费记录</span></li>");
	
	//显示列表
	$("table#PublicHouseFeeReturnRecordGrid").jqGrid({
		url: host + 'fee/publichousefeereturnrecord/getListByAllWithPage',
		datatype: "json",
		colModel: [
			{ label: '退费序号', name: 'recordno' },
			{ label: '交款方式编号', name: 'typeno' },
			{ label: '退费日期', name: 'returndate' },
			{ label: '退费金额', name: 'amount' },
			{ label: '收款人', name: 'person' },
			{ label: '退费单编号', name: 'invoicecode' },
			{ label: '记录处理状态', name: 'recordstatus' }
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
		pager: "#PublicHouseFeeReturnRecordGridPager",
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