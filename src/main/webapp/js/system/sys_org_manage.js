var path = getAppPath();

$(function(){
	getOrgDG();
});

function getQueryParam() {
	var queryObject = new Object();
	queryObject.queryOrgID = $('#queryOrgID').textbox('getValue')
	queryObject.queryOrgName =  $('#queryOrgName').textbox('getValue')
	return queryObject;
}
function getOrgDG() {

	$('#orgtable').datagrid({
		striped : true,
		url : path + '/orgManage/orgList.html',
		remoteSort : false,
		idField : 'ORGID',
		queryParams : getQueryParam(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		rownumbers : false,// 行号
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'ORGID',
			title : '机构编号',
			width : '160',
			align : 'center'
		}, {
			field : 'ORGNAME',
			title : '机构名称',
			width : '160',
			align : 'center'
		}, {
			field : 'CREATEUSER',
			title : '创建用户',
			width : '160',
			align : 'center'
		}, {
			field : 'CREATEDATE',
			title : '创建时间',
			width : '160',
			align : 'center'
		} ] ],
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				openDialog();
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				delOrg();
			}
		} ],
		onDblClickRow : function(index, row) {
			openUpdateDialog(row.ORGID);

		}
	});
	// 设置分页控件
	var p = $('#orgtable').datagrid('getPager');
	$(p).pagination({
		pageSize : 15,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		/*
         * onBeforeRefresh:function(){ $(this).pagination('loading'); alert('before
         * refresh'); $(this).pagination('loaded'); }
         */
	});
}


function delOrg(){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
		if(r){
			MaskUtil.mask('正在删除，请稍候……');
			var list = $('#orgtable').datagrid('getSelections');
			var postdata = "";
			for ( var i = 0; i < list.length; i++) {
				postdata += list[i].ORGID + ',';
			}
			if (postdata == "") {
				MaskUtil.unmask();
				$.messager.alert('错误', '请选择需要删除的机构！', 'error');
				return;
			}
			$.ajax({
				url : path + '/orgManage/delOrChangeOrg.html',
				type : "post",
				data : {
					listdata : postdata,
					type : 'del'
				},
				success : function(data) {
					if ('success' == data.success) {
						$.messager.alert('成功', "删除成功", 'info');
						MaskUtil.unmask();
						doQuery();
					} else {
						MaskUtil.unmask();
						$.messager.alert('错误', data.error, 'error');
					}
				}
			});
			$('#orgtable').datagrid('load',getQueryParam());
		}});

}

function doQuery(){
	$('#orgtable').datagrid('load',getQueryParam());
}
function doClear(){
	 $('#queryOrgID').textbox('clear')
	 $('#queryOrgName').textbox('clear')
}

function clearAddFromData() {
	$('#addForm').form('clear');
}

function openDialog() {
	clearAddFromData();
	$("#addDlg").window('open');
}

function doSubmit(){
	MaskUtil.mask('正在提交，请稍候……');
	var postdata = new Object();
	postdata.ORGID = $("#addOrgId").textbox('getValue');
	postdata.ORGNAME = $("#addOrgName").textbox('getValue');
	postdata.UPDATETYPE = 'save';

	$.ajax({
		url : path + '/orgManage/saveOrUpdateOrg.html',
		type : "post",
		data : postdata,
		success : function(data) {
			if ('success' == data.success) {
				doCancel();
				$.messager.alert('成功', "添加成功", 'info');
				doQuery();
			} else {
				$.messager.alert('错误', data.error, 'error');
			}
			MaskUtil.unmask();
		}
	});
}
function doCancel(){
	$("#addDlg").window('close');
}

function doEditSubmit(){
	MaskUtil.mask('正在提交，请稍候……');
	var postdata = new Object();
	postdata.ORGID = $("#orgID").textbox('getValue');
	postdata.ORGNAME = $("#orgName").textbox('getValue');
	postdata.UPDATETYPE = 'update';

	$.ajax({
		url : path + '/orgManage/saveOrUpdateOrg.html',
		type : "post",
		data : postdata,
		success : function(data) {
			if ('success' == data.success) {
				doEditCancel();
				$.messager.alert('成功', "修改成功", 'info');
				doQuery();
			} else {
				$.messager.alert('错误', data.error, 'error');
			}
			MaskUtil.unmask();
		}

	});

}


function openUpdateDialog(id){
	var url= path+"/orgManage/getOrgInfoById.html?orgID="+id
	$('#editForm').form('load',url)
	$('#editDlg').dialog('open');
}

function doEditCancel(){
	$('#editDlg').dialog('close');
}