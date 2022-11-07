$(function () {
    initTable();
})


function getQueryParam() {
    var parameter = new Object();

    return parameter;
}

var toolbar = [
    {
        text:"选择报送",
        iconCls: "icon-task",
        "class": "tooltip-info",
        handler:function(){
            var selectList = $('#filterDataTable').datagrid("getSelections");
            var set = new Set();
            var seqS=""
            $.each(selectList,function(index,item) {
                set.add(item.INFRECTYPE);
                seqS+=","+item.BSSGMTSEQNO
            });
            if(set.size>1){
                $.messager.alert('错误', "请选择相同信息记录类型的数据进行报送", 'error');
                return ;
            }
            $.ajax({
                url : path + '/filterReport/report.html',
                type : "post",
                dataType: 'json',
                contentType: 'application/json',
                data : JSON.stringify(selectList),
                async : false,
                success : function(data) {
                    if (data.RET_CODE == 'SUCCESS') {
                        $.messager.alert('成功', data.RET_MSG, 'info');
                    }else{
                        $.messager.alert('错误',  data.RET_MSG, 'error');
                    }
                }
            });


        }
    }

];

function initTable() {

    $('#filterDataTable').datagrid({
        striped: true,
        url: path + '/filterReport/getList.html',
        remoteSort: false,
        queryParams: getQueryParam(),
        toolbar: toolbar,
        singleSelect: false,// 是否单选
        pagination: true,// 分页控件
        pageSize: 20,
        pageList: [20],
        columns: [[
            {
                field : 'CHECK',
                checkbox:true
            },
            {
                field: 'BSSGMTSEQNO',
                title: '客户号/借贷账户标识码/抵质押物标识码',
                width: '10%',
                align: 'center'
            },
            {
                field: 'INFRECTYPE',
                title: '信息记录类型',
                width: '10%',
                align: 'center',
                formatter(value, row, index) {
                    if(value == '310'){
                        return "企业基本信息"
                    }
                    return value
                }
            },
            {
                field: 'ENTNAME',
                title: '名称',
                width: '20%',
                align: 'center'
            },{
                field: 'ENTCERTTYPE',
                title: '证件类型',
                width: '10%',
                align: 'center',
                formatter(value, row, index) {
                    if(value == '10'){
                        return "中征码"
                    }
                    if(value == '20'){
                        return "统一社会信用代码"
                    }
                    if(value == '30'){
                        return "组织机构"
                    }
                    return value
                }
            },{
                field: 'ENTCERTNUM',
                title: '证件号码',
                width: '20%',
                align: 'center'
            },{
                field: 'RPTDATE',
                title: '报送日期',
                width: '10%',
                align: 'center'
            },{
                field: 'ORG_CODE',
                title: '管理机构',
                width: '10%',
                align: 'center'
            },{
                field: 'SOURCESYS',
                title: '系统来源',
                width: '10%',
                align: 'center'
            },{
                field: 'action',
                title: '操作',
                width: '10%',
                align: 'center',
                formatter(value, row, index) {
                    return   '<a href="javascript:void(0)" onClick=update(\"'+row.BSSGMTSEQNO+'\")>'+"修改"+'</a>';
                }
            }
           ]]
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

function doQuery() {
    $('#filterDataTable').datagrid('load', getQueryParam());
}

function update(BSSGMTSEQNO){
    initComboboxWH("EntCertType","ENTCERTTYPE",120,400)
    $('#dialog-update').dialog('open');

    $.ajax({
        url : path + '/filterReport/getOne.html',
        type : "post",
        data : {
            BSSGMTSEQNO:BSSGMTSEQNO
        },
        async : false,
        success : function(data) {

           $('#dialog-updateForm').form('load',data);
        }
    });

}

function doCancel(){
    $('#dialog-update').dialog('close');
    $('#dialog-updateForm').form('clear');
}

function doSubmit(){
    $('#dialog-updateForm').form('submit', {
        url : path + '/filterReport/update.html',
        success : function(data) {
            data = JSON.parse(data);
            if (data.RET_CODE == 'SUCCESS') {
                $.messager.alert('成功', data.RET_MSG, 'info');
                doQuery();
                doCancel();
            } else if (data.RET_CODE == 'FAIL') {
                $.messager.alert('错误', data.RET_MSG, 'error');
            }
        }
    });
}