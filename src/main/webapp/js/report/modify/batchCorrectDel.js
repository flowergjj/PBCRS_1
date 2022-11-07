var path = getAppPath();
var filetype = '.enc';
$(function() {
	// 初始化数据列表
	initTable();
	initBtn();
	initFirstCombobox();
	// download();

});
var toolbar = [
      			{
       				text:"导入",
       				iconCls: "icon-save",
       				"class": "tooltip-info",
       				handler:function(){
       					//e.preventDefault;

       					$('#dialog-importALL').dialog('open');
       				}
       			},{
       				text:"模版下载",
       				iconCls: "icon-save",
       				"class": "tooltip-info",
       				handler:function(){
       					// e.preventDefault;

       					$('#dialog-templateDown').dialog('open');
       				}
       			}, {
       				text:"选择报送",
       				iconCls: "icon-task",
       				"class": "tooltip-info",
       				handler:function(){
       					report();
       				}
       			} , {
       				text:"批量删除并报送",
       				iconCls: "icon-task",
       				"class": "tooltip-info",
       				handler:function(){
       					showRpt();       					
       				}
       			}   			
       		];
function initTable() {

	$('#DataListTable').datagrid({
		striped : true,
		url : path + '/batchCorrect/listPageDel.html',
		remoteSort : false,
		//idField : 'SEQNO',
		queryParams : queryParameter(),
		singleSelect : true,// 是否单选
		toolbar:toolbar,
		pagination : true,// 分页控件
		//scrollbarSize : 0,
		pageSize : 15,
		pageList : [ 15 ],
		columns : [ [ {
			field : 'FILENAME',
			title : '文件名称',
			width : '40%',
			align : 'center'
		}, {
			field : 'OPERATEDATE',
			title : '文件导入时间',
			width : '30%',
			align : 'center'
		}, {
			field : 'detail',
			title : '操作',
			width : '30%',
			align : 'center',
			formatter : function(value, row, index) {

				var id = row.ID;
				var str = '<a href="javascript:void(0)" onClick=showDetail(\"' + id + '\")>查看详情</a>';
				return str;

			}
		} ] ]

	});
	// 设置分页控件
	var p = $('#DataListTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
	$('#DataListTable').datagrid('clearChecked');
	$('#DataListTable').datagrid('clearSelections');
}

function initBtn() {

/*	$('#btn_imp').on('click', function(e) {
		e.preventDefault;

		$('#dialog-importALL').dialog('open');
	});
	
	$('#btn_down').on('click', function(e) {
		e.preventDefault;

		$('#dialog-templateDown').dialog('open');
	});
*/
	$('#btn_ok').on('click', function(e) {
		e.preventDefault;
		var list = $('#batchCorrectFile').val().split('\\');
		var fileName = list[list.length - 1];
		if (checkFileName(fileName)) {
			$.messager.alert('错误', '文件名称已存在于请修改以后重新上传！', 'error');
			return;
		}
		//return false;
		$('#dialog-importdform').form('submit', {
			url : path + '/batchCorrect/uploadFileDel.html',
			success : function(data) {
				$('#dialog-fileupload').unmask();
				data = JSON.parse(data);
				if (data.RET_CODE == 'SUCCESS') {
					$.messager.alert('成功', data.RET_MSG, 'info');
					doQuery();
					$('#dialog-importALL').dialog('close');
					$('#dialog-importCheck').dialog('close');

				} else if (data.RET_CODE == 'FAILD') {
					$.messager.alert('错误', data.RET_MSG, 'error');
				}

			}
		});

	});

}

function checkFileName(fileName) {
	var flag=false;
	$.ajax({
		url : path + '/batchCorrect/checkFileNameDel.html',
		type : "post",
		data : {
			fileName : fileName
		},
		async : false,
		success : function(data) {
			flag=data;
			
		}
	});
	if(flag==true){
		return true;
	}
	return false;
}

function initFirstCombobox(fieldName) {
	$.ajax({
		url : path + '/batchCorrect/getFirstCombox.html',
		type : "post",
		data : {
			comments : fieldName
		},
		async : false,
		success : function(data) {
			$('#queryRptType').combobox({
				data : data,

				valueField : 'ID',
				textField : 'TEXT',
				panelWidth : 132,
				panelHeight : 150
			});
		}
	});

}
function initDelCombobox() {
	$.ajax({
		url : path + '/batchCorrect/getFirstCombox.html',
		type : "post",
		async : false,
		success : function(data) {
			$('#queryRptTypeDel').combobox({
				data : data,

				valueField : 'ID',
				textField : 'TEXT',
				panelWidth : 132,
				panelHeight : 150
			});
		}
	});

}

function download1() {
	var reportType = $("#queryRptType").combobox("getValue");
	if (reportType.length <= 0 ) {
		$.messager.alert('错误', '请选择以后再点击下载!', 'error');
		return false;
	}
	window.open(path + '/batchCorrect/downloadDel.html?reportType=' + encodeURI(reportType));
	$('#dialog-templateDown').dialog('close');
	$("#queryRptType").combobox("clear");
	$("#paragraphName").combobox("clear");

}


// 条件查询
function doQuery() {

	$('#DataListTable').datagrid('load', queryParameter());

}
// 条件封装
function queryParameter() {
	var parameter = new Object();

	parameter.queryRptDate = $('#queryRptDate').datebox('getValue');
 
    parameter.queryfileName=$('#queryfileName').val();

	return parameter;
}
// 清除条件
function clearQuery() {
	$('#queryRptDate').datebox('clear');
	$('#queryfileName').textbox('clear');
	//$('#queryNum').textbox('clear');
	//$('#queryCimoc').combobox('clear');
}
//获取选中的记录集合
function getSelectRows(){
	var rows = $('#DataListTable').datagrid('getChecked');
	if(rows.length == 0){
		$.messager.alert('提示','你还没有选择任何记录!');
		return false;
	}

	var rowSData = $('#DataListTable').datagrid('getSelections');

	return  rowSData;
}

function report(){
	MaskUtil.mask('执行中，请稍候……');
	var result = getSelectRows();
	//console.info(result);
	$.ajax({
		url : path + '/batchCorrect/reportDel.html',
		type : "post",
		data : {
			id : result[0].ID
		},
		async : false,
		success : function(res) {
			MaskUtil.unmask();
			//console.info(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功请等待校验，校验成功后请进入报送文件下载查看校验信息及报送文件!', 'info');
				
			}else{
				if(res.ERR_MSG!=null){
					$.messager.alert('错误', res.ERR_MSG, 'error');
				}else{
					$.messager.alert('错误', '提交失败!', 'error');
				}
				
			}
		}
	});
}

function showDetail(id){
	$('#dialog-BatchInfo').dialog('open');
	
	$('#batchInfoTable').datagrid({
		striped : true,
		url : path + '/batchCorrect/listPageInfoDel.html',
		remoteSort : false,
		//idField : 'SEQNO',
		queryParams : queryBatchInfo(id),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		//scrollbarSize : 0,
		pageSize : 15,
		pageList : [ 15 ],
		columns : [ [ {
			field : 'REPORTTYPE',
			title : '报文类型',
			width : '30%',
			align : 'center'
		}, {
			field : 'SGMTSEQNO',
			title : '主键标识',
			width : '40%',
			align : 'center'
		},{
			field : 'ssss',
			title : '校验反馈信息',
			width : '20%',
			align : 'center',
			formatter : function(value, row, index) {
				var res1="";
				$.ajax({
					url :  path + '/reportMod/checkMsg.html',
					type : "post",
					data : {
						seqno : row.SGMTSEQNO
					},
					async : false,
					success : function(res) {
						if(res.RET_CODE=="SUCCESS"){
							res1=res;
							/*return '<a href="javascript:void(0)" onClick=checkMsg(\"'
							+res.condtionId+'\")>'+res.badName+'</a>';*/
						}else{
							
						}
					}
				});
				if(res1!=""){
					return '<a href="javascript:void(0)" onClick=checkMsg(\"'
					+res1.condtionId+'\")>'+res1.badName+'</a>';
				}
				return ;
			}
		} ] ]

	});
	// 设置分页控件
	var p = $('#batchInfoTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
}
function showRpt(){
	$('#dialog-DelRpt').dialog('open');
	initDelCombobox();
	$('#DelRptTable').datagrid({
		striped : true,
		url : path + '/batchCorrect/listPageDelRpt.html',
		remoteSort : false,
		//idField : 'SEQNO',
		//queryParams : queryBatchInfo(id),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		//scrollbarSize : 0,
		pageSize : 15,
		pageList : [ 15 ],
		columns : [ [ {
			field : 'REPORTTYPE',
			title : '报文类型',
			width : '30%',
			align : 'center'
		}, {
			field : 'RPTDATE',
			title : '数据日期',
			width : '15%',
			align : 'center'
		}, {
			field : 'OPERATEDATE',
			title : '操作日期',
			width : '15%',
			align : 'center'
		}, {
			field : 'CREATEUSERNAME',
			title : '操作人',
			width : '20%',
			align : 'center'
		} ] ]

	});
	// 设置分页控件
	var p = $('#DelRptTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
}

function delAndRpt(){
	MaskUtil.mask('执行中，请稍候……');
	var queryDate = $('#queryDate').datebox('getValue');
    var reportType = $("#queryRptTypeDel").combobox("getValue");
    if(queryDate == null || queryDate == "" || queryDate == undefined){
		$.messager.alert('提示','数据日期不能为空!');
		return false;
    }
    if(reportType == null || reportType == "" || reportType == undefined){
		$.messager.alert('提示','报文类型不能为空!');
		return false;
    }
	$.ajax({
		url :  path + '/batchCorrect/DelRpt.html',
		type : "post",
		data : {
			queryDate : queryDate,
			reportType : reportType
		},
		async : false,
		success : function(res) {
			MaskUtil.unmask();
			
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('提示','操作成功!');
			}else{
				if(res.ERR_MSG!=null){
					$.messager.alert('错误', res.ERR_MSG, 'error');
				}else{
					$.messager.alert('错误', '提交失败!', 'error');
				}
			}
			$('#DelRptTable').datagrid('load');
		}
	});
    
}

function delAndRptClear(){
	$('#queryDate').datebox('clear');
	$('#queryRptTypeDel').combobox('clear');
}

//查看反馈信息
function checkMsg(conditionId){
	//文件下载的jsp
	openSubTab(path + '/jsp/report/sendFile/SendFile.jsp?id=' +conditionId, '报送文件下载');
}

function queryBatchInfo(id){
	var parameter = new Object();
	parameter.id = id;
	return parameter;
}
