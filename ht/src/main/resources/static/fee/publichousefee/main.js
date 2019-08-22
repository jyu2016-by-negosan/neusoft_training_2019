/**
 * 公建供热记录JS
 * 作者：张晓龙
 */
$(function(){
    var feeno=0;
    var houseno=0;
    var heatingyear=null;
    var agreefee=0;
    var actualfee=0;
    var debtfee=0;
    var feedesc=null;
    var feestatus=null;
    //设置系统页面标题
    $("ol.breadcrumb").html("<li class='breadcrumb-item'><span id='mainpagetitle'>供热缴费模块</span></li>"
    +"<li class='breadcrumb-item'><span id='mainpagetitle'>公建供热记录</span></li>");

    //显示列表
    $("table#PublicHouseFeeGrid").jqGrid({
        url: host+ 'fee/publichousefee/getListByAllWithPage',
        datatype: "json",
        colModel: [
            { label: '公建供热序号', name: 'feeno' },
            { label: '公建编号', name: 'houseno' },
            { label: '年份', name: 'heatingyear' },
            { label: '应缴费', name: 'agreefee' },
            { label: '实缴费', name: 'actualfee' },
            { label: '欠缴费', name: 'debtfee' },
            { label: '备注', name: 'feedesc' },
            { label: '缴费状态(供热/报停)', name: 'feestatus' }
        ],
        viewrecords: true,
        autowith: true,
        height: 190,
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
                feeno: feeno,
                houseno: houseno,
                heatingyear: heatingyear,
                agreefee: agreefee,
                actualfee: actualfee,
                debtfee: debtfee,
                feedesc: feedesc,
                feestatus: feestatus
            }
        }).trigger("reloadGrid");
    }

    //点击增加链接处理，嵌入add.html
    $("a#PublicHouseFeeAddLink").off().on("click", function(event){
        $("div#PublicHouseFeeDialogArea").load("fee/publichousefee/add.html", function(){
            $("div#PublicHouseFeeDialogArea").dialog({
                title: "增加公建供热记录",
                width: 600
            });
            $("form#PublicHouseFeeAddForm").validate({
                rules: {
                    feeno: {
                        required: true,
                    },
                    houseno: {
                        required: true,
                    },
                    heatingyear: {
                        required: true,
                    },
                    agreefee: {
                        required: true,
                    },
                    actualfee: {
                        required: true,
                    },
                    debtfee: {
                        required: true,
                    },
                    feedesc: {
                        required: true,
                    },
                    feestatus: {
                        required: true,
                    }
                },
                messages: {
                    feeno: {
                        required: "公建供热序号不能为空！",
                    },
                    houseno: {
                        required: "公建编号不能为空！",
                    },
                    heatingyear: {
                        required: "年份不能为空！",
                    },
                    agreefee: {
                        required: "应缴费不能为空！",
                    },
                    actualfee: {
                        required: "实缴费不能为空！",
                    },
                    debtfee: {
                        required: "欠缴费不能为空！",
                    },
                    feedesc: {
                        required: "备注信息不能为空！",
                    },
                    feestatus: {
                        required: "缴费状态不能为空！",
                    }
                }
            });
            $("form#PublicHouseFeeAddForm").ajaxForm(function(result){
                if(result.status == "OK"){
                    reloadList();
                }
                BootstrapDialog.show({
                    title: '公建供热记录操作信息',
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