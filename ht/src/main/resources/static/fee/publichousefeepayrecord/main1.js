/**
 * 公建供热记录JS
 * 作者：张晓龙
 */
$(function(){
	//关联取
	var feeno = 0;
	var typeno = 0;
	//本表查询
	var recordno = 0;
	var payamount = 0;
	var paydate = null;
	var payperson = null;
	var checkcode = 0;
	var invoicecode = 0;
	var paydesc = null;
	var recordstatus = null;
    //设置系统页面标题
    $("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetitle'>供热缴费模块</span></li>"
    +"<li class='breadcrumb-item'><span id='mainpagetitle'>公建缴费记录</span></li>");

    //显示列表
    $("table#PublicHouseFeeGrid").jqGrid({
        url: host+ 'fee/publichousefee/getListByAllWithPage',
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
        viewrecords: true,
        autowidth: true,
        height: 200,
        rowNum: 5,

        jsonReader: {
            root: "list",
            page: "page",
            total: "pageCount",
            records: "count",
            repeatitems: true,
            id: "feeno"
        },
        pager: "#PublicHouseFeeGridPager",
    });
    //更新JQGrid的列表显示
    function reloadList() {
        $("table#PublicHouseFeeGrid").jqGrid('setGridParam', {
            postData: {
                recordno: recordno,
                typeno: typeno,
                paydate: paydate,
                payamount: payamount,
                payperson: payperson,
                checkcode: checkcode,
                recordstatus: recordstatus,
            }
        }).trigger("reloadGrid");
    }

    //点击增加链接处理，嵌入add.html
    $("a#PublicHouseFeePayRecordAddLink").off().on("click", function(event){
        $("div#PublicHouseFeePayRecordDialogArea").load("fee/publichousefeepayrecord/add.html", function(){
            $("div#PublicHouseFeePayRecordDialogArea").dialog({
                title: "增加公建缴费记录",
                width: 650
            });
            $("form#PublicHouseFeePayRecordAddForm").validate({
                rules: {
                    recordno: {
                        required: true,
                    },
                    typeno: {
                        required: true,
                    },
                    payamount: {
                        required: true,
                    },
                    paydate: {
                        required: true,
                    },
                    payperson: {
                        required: true,
                    },
                    checkcode: {
                        required: true,
                    },
                    recordstatus: {
                        required: true,
                    },
                },
                messages: {
                    feeno: {
                        required: "序号不能为空！",
                    },
                    houseno: {
                        required: "交款方式不能为空！",
                    },
                    heatingyear: {
                        required: "交款金额不能为空！",
                    },
                    agreefee: {
                        required: "交款日期不能为空！",
                    },
                    actualfee: {
                        required: "交款人不能为空！",
                    },
                    debtfee: {
                        required: "支票编号不能为空！",
                    },
                    feedesc: {
                        required: "记录处理信息不能为空！",
                    }
                }
            });
            $("form#PublicHouseFeePayRecordAddForm").ajaxForm(function(result){
                if(result.status == "OK"){
                    reloadList();
                }
                BootstrapDialog.show({
                    title: '公建供热记录操作信息',
                    message: result.message
                });
                $("div#PublicHouseFeePayRecordDialogArea").dialog("close");
                $("div#PublicHouseFeePayRecordDialogArea").dialog("destroy");
                $("div#PublicHouseFeePayRecordDialogArea").html("");
            });
            $("input[value='取消']").on("click", function(){
                $("div#PublicHouseFeePayRecordDialogArea").dialog("close");
                $("div#PublicHouseFeePayRecordDialogArea").dialog("destroy");
                $("div#PublicHouseFeePayRecordDialogArea").html("");
            });
        });
    });

    //点击修改按钮事件处理
    $("a#PublicHouseFeeModifyLink").off().on("click", function(event){
        $("table#PublicHouseFeeGrid tbody tr").on("click", function(){
            feeno=$(this).attr("id");
        });
        if(feeno == 0){
            BootstrapDialog.show({
                title: '公建供热记录操作信息',
                message: "请选择要修改的公建的供热编号"
            });
        }
        else {
            $("div#PublicHouseFeeDialogArea").load("fee/publichousefee/modify.html", function(){
                $.getJSON(host+"fee/publichousefee/getByNo", {feeno:feeno}, function(data){
                    if(data.status == "OK"){
                        $("input[name='feeno']").val(feeno);
                        $("input[name='houseno']").val(data.model.houseno);
                        $("input[name='heatingyear']").val(data.model.heatingyear);
                        $("input[name='agreefee']").val(data.model.agreefee);
                        $("input[name='actualfee']").val(data.model.actualfee);
                        $("input[name='debtfee']").val(data.model.debtfee);
                        $("input[name='feedesc']").val(data.model.feedesc);
                        $("input[name='feestatus']").val(data.model.feestatus);
                    }
                });
                $("div#PublicHouseFeeDialogArea").dialog({
                    title: "供热缴费记录信息修改",
                    width: 600
                });
                $("form#PublicHouseFeeModifyForm").ajaxForm(function(result){
                    if(result.status == "OK"){
                        reloadList();
                    }
                    BootstrapDialog.show({
                        title: '供热缴费记录操作信息',
                        message: result.message
                    });
                    $("div#PublicHouseFeeDialogArea").dialog("close");
                    $("div#PublicHouseFeeDialogArea").dialog("destroy");
                    $("div#PublicHouseFeeDialogArea").html("");
                });
                $("input[value='取消']").on("click", function(){
                    $("div#PublicHouseFeeDialogArea").dialog("close");
                    $("div#PublicHouseFeeDialogArea").dialog("destroy");
                    $("div#PublicHouseFeeDialogArea").html("");
                });
            });
        }        
    });

    //点击删除按钮事件处理
    $("a#PublicHouseFeeDeleteLink").off().on("click", function(event){
        $("table#PublicHouseFeeGrid tbody tr").on("click", function(){
            feeno=$(this).attr("id");
        });
        if(feeno==0){
            BootstrapDialog.show({
                title: '供热缴费记录操作信息',
                message: "请选择要删除的编号"
            });
        }
        else {
            BootstrapDialog.confirm('确认删除该记录吗？', function(result){
                if(result){
                    $.post(host+"fee/publichousefee/delete", {feeno:feeno}, function(result){
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

    //点击查看详细按钮事件处理
    $("a#PublicHouseFeeDetailLink").off().on("click", function(event){
        $("table#PublicHouseFeeGrid tbody tr").on("click", function(){
            feeno=$(this).attr("id");
        });
        if(feeno == 0){
            BootstrapDialog.show({
                title: '供热缴费记录操作信息',
                message: "请选择要查看的编号"
            });
        }
        else {
            $("div#PublicHouseFeeDialogArea").load("fee/publichousefee/detail.html", function(){
                $.getJSON(host+"fee/publichousefee/getByNo", {feeno:feeno}, function(data){
                    if(data.status == "OK"){
                        $("span#feeno").html(data.model.feeno);
                        $("span#houseno").html(data.model.houseno);
                        $("span#heatingyear").html(data.model.heatingyear);
                        $("span#agreefee").html(data.model.agreefee);
                        $("span#actualfee").html(data.model.actualfee);
                        $("span#debtfee").html(data.model.debtfee);
                        $("span#feedesc").html(data.model.feedesc);
                        $("span#feestatus").html(data.model.feestatus);
                    }
                });
                $("div#PublicHouseFeeDialogArea").dialog({
                    title: "供热缴费记录信息详情",
                    width: 600
                });
                $("input[value='返回']").on("click", function(){
                    $("div#PublicHouseFeeDialogArea").dialog("close");
                    $("div#PublicHouseFeeDialogArea").dialog("destroy");
                    $("div#PublicHouseFeeDialogArea").html("");
                });
            });
        }
    });
});