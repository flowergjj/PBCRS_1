$(function () {
    initTable();
})

function getQueryParam() {
    var parameter = new Object();
  /*  parameter.rptDate = $('#rptDate').datebox('getValue');
    parameter.name = $('#name').textbox('getValue');
    parameter.certNum = $('#certNum').textbox('getValue');
    parameter.contractCode = $('#contractCode').textbox('getValue');
    parameter.acctCode = $('#acctCode').textbox('getValue');
    parameter.ccCode = $('#ccCode').textbox('getValue');*/
    return parameter;
}

var toolbar = [
    {
        text: "添加",
        iconCls: "icon-add",
        "class": "tooltip-info",
        handler: function () {
            add();
        }
    },
    {
        text:"批量添加模版下载",
        iconCls: "icon-save",
        "class": "tooltip-info",
        handler:function(){
            window.open(path + '/filterData/downloadAdd.html')
        }
    },
    {
        text:"批量添加导入",
        iconCls: "icon-save",
        "class": "tooltip-info",
        handler:function(){
            //e.preventDefault;
            $('#dialog-importAdd').dialog('open');
        }
    },{
        text:"批量删除模版下载",
        iconCls: "icon-save",
        "class": "tooltip-info",
        handler:function(){
            window.open(path + '/filterData/downloadDel.html')
        }
    },
    {
        text:"批量删除导入",
        iconCls: "icon-save",
        "class": "tooltip-info",
        handler:function(){
            $('#dialog-importDel').dialog('open');

        }
    },
    {
        text:"全量信息导出",
        iconCls: "icon-save",
        "class": "tooltip-info",
        handler:function(){
            //e.preventDefault;
            window.open(path + '/filterData/downloadAll.html')
        }
    }
];

function add(){
    $('#dialog-add').dialog('open');
}

function remove(seqNo){
    $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
        if(r){
            var param = {};
            param.seqNo = seqNo
            $.ajax({
                url : path + '/filterData/delete.html',
                type : "post",
                data:param,
                async : false,
                success : function(data) {
                    if(data.RET_CODE == 'SUCCESS'){
                        $.messager.alert('成功', '删除成功!', 'info');
                        $('#filterDataTable').datagrid('load', getQueryParam());
                    }else{
                        $.messager.alert('错误', '删除失败!', 'error');
                    }
                }
            });
        }
    })

}

function reverseEnable(seqNo,isEnable){
    var msg ;
    if(isEnable == 'Y'){
        msg = "启用"
    }else{
        msg = "停用"
    }
    $.messager.confirm('确认','您确认'+msg+'该记录？',function(r) {
        if (r) {
            var param = {};
            param.seqNo = seqNo
            param.isEnable = isEnable;
            $.ajax({
                url : path + '/filterData/updateEnable.html',
                type : "post",
                data:param,
                async : false,
                success : function(data) {
                    if(data.RET_CODE == 'SUCCESS'){
                        $.messager.alert('成功', msg+'成功!', 'info');
                        $('#filterDataTable').datagrid('load', getQueryParam());
                    }else{
                        $.messager.alert('错误', msg+'失败!', 'error');
                    }
                }
            });
        }
    })

}

function initTable() {
    $('#filterDataTable').datagrid({
        striped: true,
        url: path + '/filterData/getList.html',
        remoteSort: false,
        queryParams: getQueryParam(),
        toolbar: toolbar,
        singleSelect: false,// 是否单选
        pagination: true,// 分页控件
        pageSize: 20,
        pageList: [20],
        columns: [[
            /*{
                field: 'seqNo',
                width: '2%',
                checkbox: true
            },*/ {
                field: 'sourcecustID',
                title: '客户号',
                width: '10%',
                align: 'center'
            }, {
                field: 'name',
                title: '名称',
                width: '20%',
                align: 'center'
            }, {
                field: 'id',
                title: '证件号码',
                width: '15%',
                align: 'center'
            }, {
                field: 'createDate',
                title: '录入日期',
                width: '10%',
                align: 'center'
            }, {
                field: 'createTime',
                title: '录入时间',
                width: '15%',
                align: 'center'
            }, {
                field: 'reason',
                title: '原因',
                width: '15%',
                align: 'center'
            },{
                field: 'isEnable',
                title: '是否启用',
                width: '5%',
                align: 'center',
                formatter(value, row, index) {
                    if (value == 'Y') {
                        return '已启用'
                    } else {
                        return '未启用'
                    }
                }
            }, {
                field: 'action',
                title: '操作',
                width: '10%',
                align: 'center',
                formatter(value, row, index) {
                    var enableContent ;
                    var enableValue
                    if(row.isEnable == 'Y'){
                        enableContent = "停用"
                        enableValue = 'N'
                    }else{
                        enableContent = "启用"
                        enableValue = 'Y'
                    }
                    return   '<a href="javascript:void(0)" onClick=reverseEnable(\"'+row.seqNo+"\",\""+enableValue+'\")>'+enableContent+'</a>     ' +
                    '<a href="javascript:void(0)" onClick=remove('+row.seqNo+')>删除</a>';
                }
            }]]
    });

    // 设置分页控件
    var p = $('#filterDataTable').datagrid('getPager');
    $(p).pagination({
        pageSize: 20,// 每页显示的记录条数，默认为10
        showPageList: false,
        beforePageText: '第',// 页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    $('#filterDataTable').datagrid('clearChecked');
}

function report(){
    var set = new Set();
    var custArr = $('#filterDataTable').datagrid("getSelections");
    ;
    if(custArr.length == 0){
        return;
    }

    var reportId="";
    var id="";
    var etl_date="";

    for (var int = 0; int < custArr.length; int++) {
        reportId=custArr[int].infRecType;
        if(id==""){
            id=id+"'"+custArr[int].keyword+"'";
        }else{
            id=id+",'"+custArr[int].keyword+"'";
        }
        etl_date=custArr[int].rptDate;
        set.add(reportId);
        set.add(etl_date);
    }
    if(set.size !=2 ){
        $.messager.alert('错误', '请选择同一种报文类型和同一报送时间!', 'error');
        return ;
    }
    var SYSID = "";
    if(reportId == "514"){
        if(tableName == "EnReportModTable"){
            SYSID = "ENT";
        }else if(tableName == "MnMmbInfTable"){
            SYSID = "IND";
        }
    }

    var url =path+"/filterData/report.html";
    $.ajax({
        url : url,
        type : "post",
        data : {
            reportId : reportId,
            id		 :	id,
            etl_date  :etl_date,
            type:'updSub',//需要存储id
            SYSID:SYSID
        },
        async : false,
        success : function(res) {
            if(res.RET_CODE=="SUCCESS"){
                $.messager.alert('成功', '提交成功请等待校验，校验成功后请进入报送文件下载查看校验信息及报送文件!', 'info');

            }else{

                if(res.ERR_MSG!=null){
                    $.messager.alert('错误', res.ERR_MSG, 'error');
                }else{
                    $.messager.alert('错误', '提交失败!', 'error');
                }


            }
            //清空集合
            set== new Set();
        }
    });
}

function doQuery() {
    $('#filterDataTable').datagrid('load', getQueryParam());
}

function doClear() {
    $('#rptDate').datebox('clear');
    $('#name').textbox('clear');
    $('#certNum').textbox('clear');
    $('#contractCode').textbox('clear');
    $('#acctCode').textbox('clear');
    $('#ccCode').textbox('clear');
}

function addClick(){
    var param = {};
    param.sourcecustId = $('#sourcecustId1').textbox('getValue');
    param.name = $('#name1').textbox('getValue');
    param.certNum = $('#certNum1').textbox('getValue');
    param.reason = $('#reason').textbox('getValue');
    $.ajax({
        url : path + '/filterData/insert.html',
        type : "post",
        data:param,
        async : false,
        success : function(data) {
            if(data.RET_CODE == 'SUCCESS'){
                $('#dialog-add').dialog('close');
                $.messager.alert('成功', '添加成功!', 'info');
                $('#filterDataTable').datagrid('load', getQueryParam());
                clearForm();
            }else{
                $.messager.alert('错误', '添加失败!', 'error');
            }
        }
    });

}
function addCancel(){
    $('#dialog-add').dialog('close');
    clearForm();
}

function clearForm(){
     $('#sourcecustId1').textbox('clear');
     $('#name1').textbox('clear');
     $('#certNum1').textbox('clear');
     $('#reason').textbox('clear');
}

function addFileClick(){
    MaskUtil.mask("正在添加中,请稍后");
    $('#dialog-importAddForm').form('submit', {
        url : path + '/filterData/uploadFileAdd.html',
        success : function(data) {
            $('#dialog-fileupload').unmask();
            data = JSON.parse(data);
            if (data.RET_CODE == 'SUCCESS') {
                $.messager.alert('成功', data.RET_MSG, 'info');
                doQuery();
                $('#dialog-importAdd').dialog('close');
                $('#addFile').val("")
            } else if (data.RET_CODE == 'FAILD') {
                $.messager.alert('错误', data.RET_MSG, 'error');
            }
            MaskUtil.unmask();
        },
    });
}

function delClick(){
    MaskUtil.mask("正在删除中,请稍后");
    $('#dialog-importDelForm').form('submit', {
        url : path + '/filterData/uploadFileDel.html',
        success : function(data) {
            $('#dialog-fileupload').unmask();
            data = JSON.parse(data);
            if (data.RET_CODE == 'SUCCESS') {
                $.messager.alert('成功', data.RET_MSG, 'info');
                doQuery();
                $('#dialog-importDel').dialog('close');
                $('#delFile').val("")
            } else if (data.RET_CODE == 'FAILD') {
                $.messager.alert('错误', data.RET_MSG, 'error');
            }
            MaskUtil.unmask();

        }
    });
}
