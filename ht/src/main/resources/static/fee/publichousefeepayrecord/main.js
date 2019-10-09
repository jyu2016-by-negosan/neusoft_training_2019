/**
 * 公建缴费记录JS
 * 作者：张晓龙
 * */
 $(function(){
	//关联取
	var feeno = 0;
	var typeno = 0;

	var hoodno = 0;
	var heatingyear = null;
	//本表查询
	var recordno = 0;
	var payamount = 0;
	var paydate = null;
	var payperson = null;
	var checkcode = 0;
	var invoicecode = 0;
	var paydesc = null;
	var recordstatus = null;
	
	//设置系统标题
	$("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetitle'>供热缴费模块</span></li>"
	+"<li class='breadcrumb-item'><span id='mainpagetitle'>公建缴费记录</span></li>");
	
	//显示列表
	$("table#PublicHouseFeePayRecordGrid").jqGrid({
		url: host + 'fee/publichousefeepayrecord/getListByAllWithPage',
		datatype: "json",
		colModel: [
			{ label: '缴费序号', name: 'recordno' },
			{ label: '交款方式编号', name: 'typeno' },
			{ label: '缴费日期', name: 'paydate' },
			{ label: '缴费金额', name: 'payamount' },
			{ label: '缴费人', name: 'payperson' },
			{ label: '支票编号', name: 'checkcode' },
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
		pager: "#PublicHouseFeePayRecordGridPager",
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
		$("table#PublicHouseFeePayRecordGrid").jqGrid('setGridParam',
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
	
    $("a#PublicHouseFeePayRecordAddLink").off().on("click", function(event){
        $("div#PublicHouseFeePayRecordDialogArea").load("fee/publichousefeepayrecord/add.html", function(){
            $("div#PublicHouseFeePayRecordDialogArea").dialog({
				title: "增加公建供热缴费信息",
				width: 600
			});
			$("form#PublicHouseFeePayRecordAddForm").ajaxForm(function(result){
				if(result.status == "OK"){
					reloadList();
				}
				BootstrapDialog.show({
					title: '缴费操作信息',
					message: result.message
				});
				$("div#PublicHouseFeePayRecordDialogArea" ).dialog( "close" );
				$("div#PublicHouseFeePayRecordDialogArea" ).dialog( "destroy" );
				$("div#PublicHouseFeePayRecordDialogArea").html("");
			});
			$("input[value='取消']").on("click", function(){
			    $("div#PublicHouseFeePayRecordDialogArea").dialog("close");
			    $("div#PublicHouseFeePayRecordDialogArea").dialog("destroy");
			    $("div#PublicHouseFeePayRecordDialogArea").html("");
			});
		});
	});
	
	$("a#PublicHouseFeePayRecordModifyLink").off().on("click", function(){
		$("table#PublicHouseFeePayRecordGrid tbody tr").on("click", function(){
			recordno = $(this).attr("id");
		});
		if (recordno == 0) {
			BootstrapDialog.show({
				title: '公建缴费记录操作信息',
				message: "请选择要修改的记录"
			});
		} else{
			
		}
	});
	
	$("a#PublicHouseFeePayRecordDeleteLink").off().on("click", function(){
		$("table#PublicHouseFeePayRecordGrid tbody tr").on("click", function(){
			recordno = $(this).attr("id");
			//alert(recordno);
		});
		if (recordno == 0) {
			BootstrapDialog.show({
				title: '公建缴费记录操作信息',
				message: "请选择要删除的记录"
			});
		} else{
			BootstrapDialog.confirm('确认删除该记录吗？', function(result){
			    if(result){
			        $.post(host+"fee/publichousefeepayrecord/delete", {recordno:recordno}, function(result){
			            if(result.status == "OK"){
			                reloadList();
			            }
			            BootstrapDialog.show({
			               title: '供热缴费记录操作信息',
			               message: result.message 
			            });
			        });
			    }
			});
		}
	});
	
	$("a#PublicHouseFeePayRecordDetailLink").off().on("click", function(){
		$("table#PublicHouseFeePayRecordGrid tbody tr").on("click", function(){
			recordno = $(this).attr("id");
		});
		if (recordno == 0) {
			BootstrapDialog.show({
				title: '公建缴费记录操作信息',
				message: "请选择要查看的记录"
			});
		} else{
			
		}
	});
 });