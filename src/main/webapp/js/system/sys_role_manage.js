var path = getAppPath();

var lazy = false;
$(function() {
	$('#addDlg').window({
		modal : true,
		minimizable : false
	});
	$('#editDlg').window({
		modal : true,
		minimizable : false
	});
	// getRoleStatus();
	/*$.ajax({
		url : path + '/roleManage/getRoleLevel.html',
		type : "post",
		async : false,
		success : function(data) {
			/!*$('#ROLELEVEL').combobox({
				// url:path+'/orgManage/orgList.html',
				data : data,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});*!/
			$('#rolelevel').combobox({
				// url:path+'/orgManage/orgList.html',
				data : data,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});
		}

	});*/
	//initCombobox("ROLE_IDCODE_CHK","VALID_CODE_FLAG");
	//initCombobox("ROLE_IDCODE_CHK","valid_code_flag");
	getRoleDG();
	$('body').css('visibility', 'visible');
});

function getRoleDG() {

	$('#roletable').datagrid({
		striped : true,
		url : path + '/roleManage/roleList.html',
		remoteSort : false,
		idField : 'roleID',
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
			field : 'roleID',
			title : '角色ID',
			width : '160',
			align : 'center'
		}, {
			field : 'roleName',
			title : '角色名称',
			width : '160',
			align : 'center'
		}, {
			field : 'updateUser',
			title : '创建用户',
			width : '160',
			align : 'center'
		}, {
			field : 'updateDate',
			title : '创建时间',
			width : '160',
			align : 'center'
		} ] ],
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				// $("#addDlg").window("open").window('setTitle', '新增');
				openDialog();
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				delRole();
			}
		} ],
		onDblClickRow : function(index, row) {
			openUpdateDialog(row.roleID);
		}
	});
	// 设置分页控件
	var p = $('#roletable').datagrid('getPager');
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

// 数据新增提交处理
function doPostData() {
	//alert(1);
	MaskUtil.mask('正在提交，请稍候……');
	if (!$('#addForm').form('enableValidation').form('validate')) {
		MaskUtil.unmask();
		return;
	} else {
		$("#addDlg").window('close');
		//alert(2);
		var nodes = $('#add_powertree').tree('getChecked');
		var list = "";
		for ( var i = 0; i < nodes.length; i++) {
			if (list != '')
				list += ',';
			list += nodes[i].id;
		}

		if (list == "") {
			MaskUtil.unmask();
			$.messager.alert('错误', '新建的角色权限不能为空！', 'error');
			return;
		}
		//alert(list);
		var postdata = new Object();
		postdata.ROLEID = $("#ROLEID").val();
		postdata.ROLENAME = $("#ROLENAME").val();
		//postdata.ROLELEVEL = $("#ROLELEVEL").combobox('getValue');
		//postdata.VALID_CODE_FLAG = $("#VALID_CODE_FLAG").combobox('getValue');
		postdata.VALID_CODE_FLAG = 0;
		postdata.POWERLIST = list;
		postdata.UPDATETYPE = 'save';
		//alert(postdata);
		$.ajax({
			url : path + '/roleManage/saveOrUpdateRole.html',
			type : "post",
			data : postdata,
			success : function(data) {
				if ('success' == data.success) {
					MaskUtil.unmask();
					// doQuery();
					$("#roletable").datagrid('reload');
				} else {
					MaskUtil.unmask();
					$("#addDlg").window('open');
					$.messager.alert('错误', data.error, 'error');
				}
			}
		});
	}
}

// 数据修改提交处理
function doUpdatePostData() {
	// alert($('#addForm').form('enableValidation').form('validate'));
	MaskUtil.mask('正在提交，请稍候……');
	if (!$('#editDlg').form('enableValidation').form('validate')) {
		MaskUtil.unmask();
		return;
	} else {
		$("#editDlg").window('close');

		var nodes = $('#update_powertree').tree('getChecked', ['checked','indeterminate'])

		var list = "";
		for ( var i = 0; i < nodes.length; i++) {
			if (list != '')
				list += ',';
			list += nodes[i].id;
		}

		if (list == "") {
			MaskUtil.unmask();
			$.messager.alert('错误', '修改的角色权限不能为空！', 'error');
			return;
		}
		var postdata = new Object();
		postdata.ROLEID = $("#roleid").val();
		postdata.ROLENAME = $("#rolename").val();
		//postdata.VALID_CODE_FLAG = $("#valid_code_flag").combobox('getValue');
		postdata.VALID_CODE_FLAG = 0;
		postdata.POWERLIST = list;
		postdata.UPDATETYPE = 'update';
		$.ajax({
			url : path + '/roleManage/saveOrUpdateRole.html',
			type : "post",
			data : postdata,
			success : function(data) {
				if ('success' == data.success) {
					MaskUtil.unmask();
					// doQuery();
					$("#roletable").datagrid('reload');
				} else {
					MaskUtil.unmask();
					$("#editDlg").window('open');
					$.messager.alert('错误', data.error, 'error');
				}
			}
		});
	}
}

// 角色修改
function openUpdateDialog(roleid) {
	clearUpdateFromData();
	$.ajax({
		url : path + '/roleManage/getRoleInfoById.html',
		type : "post",
		data : {
			roleID : roleid
		},
		success : function(data) {
			$("#roleid").val(data.roleID);
			$("#rolename").val(data.roleName);
			$('#rolelevel').combobox('select', data.roleLevel);
			//$('#valid_code_flag').combobox('select', data.VALID_CODE_FLAG);

			$.ajax({
				url : path + '/roleManage/getPowerByRoleId.html',
				type : "post",
				data : {
					roleID : data.roleID
				},
				async : false,
				success : function(rlt) {
					$('#update_powertree').tree({
						data : rlt,
						animate : true,
						checkbox : true,
						cascadeCheck : true
					});
					$("#editDlg").window('open');
				}
			});
		}
	});
}

// 角色删除
function delRole() {
	MaskUtil.mask('正在删除，请稍候……');
	var list = $('#roletable').datagrid('getSelections');
	var postdata = "";
	for ( var i = 0; i < list.length; i++) {
		postdata += list[i].roleID + ',';
	}
	if (postdata == "") {
		MaskUtil.unmask();
		$.messager.alert('错误', '请选择需要删除的角色！', 'error');
		return;
	}
	$.ajax({
		url : path + '/roleManage/delOrChangeRoleStatus.html',
		type : "post",
		data : {
			listdata : postdata,
			type : 'del'
		},
		success : function(data) {
			if ('success' == data.success) {
				MaskUtil.unmask();
				doQuery();
			} else {
				MaskUtil.unmask();
				$.messager.alert('错误', data.error, 'error');
			}
		}
	});
	$('#roletable').datagrid('clearSelections');
}

/**
 * 获取请求信息
 */
function getQueryParam() {
	var queryObject = new Object();
	queryObject.ROLEID = $('#ROLEID').val();
	queryObject.ROLENAME = $('#ROLENAME').val();
	// queryObject.ROLELEVEL = $('#ROLELEVEL').combobox('getValue');
	return queryObject;
}

/**
 * 查询数据
 */
function doQuery() {
	$("#roletable").datagrid('unselectAll');
	$("#roletable").datagrid('load', getQueryParam());
}

function getRoleStatus() {
	$.ajax({
		url : path + '/sysCodeManage/selRoleStatusByType.html',
		type : "post",
		data : {
			type : 'ROLE-STATUS'
		},
		async : false,
		success : function(data) {
			$('#STATUS').combobox({
				data : data,
				valueField : 'ID',
				textField : 'TEXT',
				panelWidth : 132,
				panelHeight : 70
			});
			$('#status').combobox({
				data : data,
				valueField : 'ID',
				textField : 'TEXT',
				panelWidth : 132,
				panelHeight : 70
			});
		}
	});
}

function clearAddFromData() {
	$('#addForm').form('clear');
}

function openDialog() {
	clearAddFromData();
	$.ajax({
		url : path + '/roleManage/getPowerByRoleId.html',
		type : "post",
		data : {
			roleID : ''
		},
		success : function(rlt) {
			$('#add_powertree').tree({
				data : rlt,
				animate : true,
				checkbox : true,
				cascadeCheck : true
			});
			$("#addDlg").window('open');
		}
	});
}

function clearUpdateFromData() {
	$('#editDlg').form('clear');
}