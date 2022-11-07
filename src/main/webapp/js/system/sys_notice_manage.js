var path = getAppPath();
var idIndex = 0;
var lazy = false;
$(function () {
    $('#addDlg').window({
        modal: true,
        minimizable: false
    });
    $('#editDlg').window({
        modal: true,
        minimizable: false
    });

    getNoticeList();

    $('body').css('visibility', 'visible');
});

function getNoticeList() {

    $('#noticetable').datagrid({
        striped: true,
        url: path + '/systemNoticeManage/noticeList.html',
        remoteSort: false,
        idField: 'NOTICE_ID',
        queryParams: getQueryParam(),
        singleSelect: false,// 是否单选
        pagination: true,// 分页控件
        pageSize: 15,
        pageList: [15],
        rownumbers: false,// 行号
        frozenColumns: [[{
            field: 'ck',
            checkbox: true
        }, {
            field: 'NOTICE_ID',
            title: '公告编号',
            width: '160',
            align: 'center'
        }, {
            field: 'TITLE',
            title: '标题',
            width: '160',
            align: 'center'
        }, {
            field: 'OPER_NAME',
            title: '公告创建人',
            width: '160',
            align: 'center'
        }, {
            field: 'OPER_TIME',
            title: '公告创建时间',
            width: '160',
            align: 'center'
        }]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                $("#addDlg").window("open").window('setTitle', '新增');
                openDialog();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                delNotice();
            }
        }],
        onDblClickRow: function (index, row) {
            openUpdateDialog(row);
        }
    });
    // 设置分页控件
    var p = $('#noticetable').datagrid('getPager');
    $(p).pagination({
        pageSize: 15,// 每页显示的记录条数，默认为10
        showPageList: false,
        beforePageText: '第',// 页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
        /*
         * onBeforeRefresh:function(){ $(this).pagination('loading'); alert('before
         * refresh'); $(this).pagination('loaded'); }
         */
    });
}

var filemaxsize = (10 * 1024 * 1024); // 10M
function checkFileSize() {
    var fileflag = true;
    $('[name=noticeFile]').each(function (idx, el) {
        fileflag = fileSizeCheck(filemaxsize, el);
        return fileflag;
    });
    return fileflag;
}

// 数据新增提交处理
function doPostData() {
    MaskUtil.mask('正在提交，请稍候……');
    var filesize_flag = checkFileSize();
    if (filesize_flag == false) {
        MaskUtil.unmask();
        return;
    }

    if (!$('#addForm').form('enableValidation').form('validate')) {
        MaskUtil.unmask();
        return;
    } else {
        $("#status").val('1');

        $('#addForm').form('submit', {
            url: path + '/systemNoticeManage/saveNotice.html',
            success: function (data) {
                data = JSON.parse(data);
                if (data.RET_CODE == 'SUCCESS') {
                    $.messager.alert('Title', data.RET_MSG, 'info');
                    $("#noticetable").datagrid('reload');
                    doQuery();
                    $("#addDlg").window('close');
                } else if (data.RET_CODE == 'FAILED') {
                    $.messager.alert('Title', data.RET_MSG, 'error');
                }
            }
        });
        MaskUtil.unmask();
    }
}

// 数据修改提交处理
function doUpdatePostData() {
    MaskUtil.mask('正在提交，请稍候……');

    var filesize_flag = checkFileSize();
    if (filesize_flag == false) {
        MaskUtil.unmask();
        return;
    }

    if (!$('#editDlg').form('enableValidation').form('validate')) {
        MaskUtil.unmask();
        return;
    } else {
        $("#STATUS").val('1');

        $('#editDlgForm').form('submit', {
            url: path + '/systemNoticeManage/updateNotice.html',
            success: function (data) {
                data = JSON.parse(data);
                if (data.RET_CODE == 'SUCCESS') {
                    $.messager.alert('Title', data.RET_MSG, 'info');
                    $("#noticetable").datagrid('reload');
                    doQuery();
                    $("#editDlg").window('close');
                } else if (data.RET_CODE == 'FAILED') {
                    $.messager.alert('Title', data.RET_MSG, 'error');
                }
                MaskUtil.unmask();
            }
        });
    }
}

// 公告修改页面
function openUpdateDialog(row) {
    console.log(row)
    var oper_user = row.OPER_USER;
    var userid = row.USERID;

    if (oper_user != userid) {
        $.messager.alert('Title', '请勿修改他人创建的公告', 'error');
        return;
    }

    $('#TITLE').val('');
    $('#CONTENT').val('');
    $('#editDlg').form(
        'load',
        path + '/systemNoticeManage/getNoticeByID.html?NOTICE_ID='
        + row.NOTICE_ID);
    clearUpdateAtta();
    if (row.BUSSINESS_CODE != null && row.BUSSINESS_CODE != '') {
        showUpdateFile(row.BUSSINESS_CODE);
    }
    $("#editDlg").window("open").window('setTitle', '修改');
}

function showUpdateFile(bussinessCode) {
    console.log("打开修改信息时候的附件:" + bussinessCode)
    var imglist = findImgList(bussinessCode);
    if (imglist != null && imglist.length > 0) {
        for (var listindex = 0; listindex < imglist.length; listindex++) {
            var attachId = 'authImg_' + (idIndex += 1);
            // alert(imglist[listindex].imgName + "|" +
            // imglist[listindex].imgId);
            var str = '<div id="'
                + attachId
                + '" style="margin-top: 10px;"><input type="text" style="width:281px;height:20px;" readonly="readonly" value="'
                + imglist[listindex].imgName
                + '"/><b style="width:2px;"></b>';
            str += '<input type="button" value="查看" style="width:63px;height:19.8px;" onclick="openfile(\''
                + imglist[listindex].imgId
                + '\');" /><b style="width:2px;"></b>&nbsp;&nbsp;&nbsp;&nbsp;';
            str += '<input type="button" value="删除" style="width:63px;height:19.8px;" onclick="delAttaAll(\''
                + attachId
                + '\', \''
                + imglist[listindex].imgId
                + '\');" /></div>';
            $('#updateAttachArea').append(str);
        }
    } else {
        $('#updateAttachArea').append("<span>无附件</span>");
    }
//	$('#update_attachFileRow').show();
//	$('#update_attchButtonRow').show();
}

// 公告删除
function delNotice() {
    MaskUtil.mask('正在删除，请稍候……');
    var list = $('#noticetable').datagrid('getSelections');
    var postdata = "";
    for (var i = 0; i < list.length; i++) {
        postdata += list[i].NOTICE_ID + ',';
    }
    if (postdata == "") {
        MaskUtil.unmask();
        $.messager.alert('错误', '请选择需要删除的公告！', 'error');
        return;
    }
    $.ajax({
        url: path + '/systemNoticeManage/deleteNotice.html',
        type: "post",
        data: {
            listdata: postdata
        },
        success: function (data) {
            // data = JSON.parse(data);
            if (data.RET_CODE == 'SUCCESS') {
                $.messager.alert('Title', data.RET_MSG, 'info');
                $("#noticetable").datagrid('reload');
                doQuery();
                $("#editDlg").window('close');
            } else if (data.RET_CODE == 'FAILED') {
                $.messager.alert('Title', data.RET_MSG, 'error');
            }
            MaskUtil.unmask();
        }
    });
}

/**
 * 获取请求信息
 */
function getQueryParam() {
    var queryObject = new Object();
    queryObject.NOTICE_ID = $('#queryNoticeID').val();
    queryObject.TITLE = $('#queryTitle').val();
    //alert(queryObject.NOTICE_ID);
    //alert(queryObject.TITLE);
    return queryObject;
}

/**
 * 查询数据
 */
function doQuery() {
    $("#noticetable").datagrid('unselectAll');
    $("#noticetable").datagrid('load', getQueryParam());
}

function clearAddFromData() {
    $('#addForm').form('clear');
}

function openDialog() {
    clearAddFromData();
}

function clearUpdateFromData() {
    $('#editDlg').form('clear');
}

/* --------------------------------------------图像修改方法-------------------------------------------- */
/**
 * 图像添加
 */
function addAtta(type) {
    if (type == 'add') {
        var attachId = 'authImg_' + (idIndex += 1);
        var str = '<div id="'
            + attachId
            + '" style="margin-top: 10px;"><input name="noticeFile" type="file" size="50" />&nbsp;&nbsp;&nbsp;&nbsp;'
            + '<input type="button" value="删除" style="width:63px;height:19.8px;" onclick="delAtta(\''
            + attachId + '\')" /></div>';
        $('#attachArea').append(str);
    } else if (type == 'update') {
        var attachId = 'authImg_' + (idIndex += 1);
        var str = '<div id="'
            + attachId
            + '" style="margin-top: 10px;"><input name="noticeFile" type="file" size="50" />&nbsp;&nbsp;&nbsp;&nbsp;'
            + '<input type="button" value="删除" style="width:63px;height:19.8px;" onclick="delAtta(\''
            + attachId + '\')" /></div>';
        $('#updateAttachArea').append(str);
    }
}

function clearAtta() {
    $('#attachArea').html('');
}

function clearUpdateAtta() {
    $('#updateAttachArea').html('');
}

/**
 * 图像删除(前台)
 */
function delAtta(attachId) {
    $('#' + attachId).remove();
}

/**
 * 图像删除（前后台）
 */
function delAttaAll(attachId, bussId) {
    MaskUtil.mask('影像删除中...');
    $.ajax({
        url: path + '/systemNoticeManage/deleteImg.html',
        type: "post",
        data: {
            imgId: bussId
        },
        success: function (data) {
            MaskUtil.unmask();
            if (data.RET_CODE == 'SUCCESS') {
                $('#' + attachId).remove();
                $.messager.alert('删除成功', data.RET_MSG, 'info');
            } else {
                $.messager.alert('删除失败', data.RET_MSG, 'error');
            }
        }
    });
}

function findImgList(imgId) {
    var list = null;
    $.ajax({
        url: path + '/systemNoticeManage/findImgList.html',
        type: "post",
        async: false,
        data: {
            bussCode: imgId
        },
        success: function (data) {
            list = data;
        }
    });

    return list;
}

function openfile(imgid) {
    window.parent.open(path
        + '/systemNoticeManage/fileDownload.html?acType=017002&imgId='
        + imgid);
}

function doClear() {
    $('#queryNoticeID').val("");
    $('#queryTitle').val("");
}
