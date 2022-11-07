var path = getAppPath();

var lazy = false;
$(function() {
	$('#addDlg').window({
		modal : true,
		minimizable : false,
		onDestory : function() {
			$('#addForm').form('clear');
		}
	});
	$('#editDlg').window({
		modal : true,
		minimizable : false,
		onDestory : function() {
			$('#editForm').form('clear');
		}
	});
	getOrgData();
	getUserDG();
	$('body').css('visibility', 'visible');
});

function getUserDG() {

	// $.ajax({
	// url:path+'/userManage/userList.html',
	// type:"post",
	// data:"aaaaaaaaaaa",
	// success:function(data){
	// alert(data);
	// }
	// });
	//	
	$('#usertable').datagrid({
		striped : true,
		url : path + '/userManage/userList.html',
		remoteSort : false,
		idField : 'userid',
		queryParams : getQueryParam(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'userid',
			title : '用户号',
			width : '160',
			align : 'center'
		}, {
			field : 'username',
			title : '用户姓名',
			width : '160',
			align : 'center'
		}, {
			field : 'orgname',
			title : '所属机构',
			width : '160',
			align : 'center'
		}, {
			field : 'status',
			title : '用户状态',
			width : '160',
			align : 'center'
		}, {
			field : 'USER_STAT_CHG_TIME',
			title : '启用/停用时间',
			width : '160',
			align : 'center'
		} ,{
			field : 'SOURCESYS',
			title : '操作',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				var str ='<a href="javascript:void(0)" onClick=restPwd(\"'
					+row.userid+'\")>重置密码</a>';
				return str

			}
		}] ],
		toolbar : [
		{
		text: '添加',
		iconCls: 'icon-add',
		handler: function() {
		openDialog();
		}
		},
		'-',{
		text: '删除',
		iconCls: 'icon-remove',
		handler: function(){
		delUser();
		}
		},
		'-',
		{
			text : '启用/停用',
			iconCls : 'icon-edit',
			handler : function() {
				changeUserStatus();
			}
			
		} ],
		onDblClickRow : function(index, row) {
			// alert(index);
			// alert(row.USERID);
			openUpdateDialog(row.userid);
		}
	});
	// 设置分页控件
	var p = $('#usertable').datagrid('getPager');
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

/**
 * 获取请求信息
 */
function getQueryParam() {
	var queryObject = new Object();
	queryObject.queryUserName = $('#queryUserName').val();
	queryObject.queryUserId = $('#queryUserId').val();
	queryObject.queryOrgId = $('#queryOrgId').combobox('getValue');
	//queryObject.queryAuthOrgId = $('#queryAuthOrgId').combobox('getValue');
	queryObject.queryStatus = $('#queryStatus').combobox('getValue');
	queryObject.queryRoleId = $('#queryRoleId').combobox('getValue');
	return queryObject;
}
function doClear() {
	$('#queryUserName').val('');
	$('#queryUserId').val('');
	$('#queryOrgId').combobox('clear');
	$('#queryAuthOrgId').combobox('clear');
	$('#queryStatus').combobox('clear');
	$('#queryRoleId').combobox('clear');
}
/**
 * 查询数据
 */
function doQuery() {
	$("#usertable").datagrid('unselectAll');
	$("#usertable").datagrid('load', getQueryParam());
}

function getOrgData() {
	$.ajax({
		url : path + '/userManage/getOrgList.html',
		type : "post",
		async : false,
		success : function(data) {
			// alert(data.query.length);
			$('#queryOrgId').combobox({
				//url:path+'/orgManage/orgList.html',
				data : data.query,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});
			$('#orgId').combobox({
				data : data.query,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});
			$('#updateorgId').combobox({
				data : data.query,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});
			/*$('#queryAuthOrgId').combobox({
				data : data.authQuery,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});
			$('#authOrgId').combobox({
				data : data.authQuery,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});
			$('#updateAuthOrgId').combobox({
				data : data.authQuery,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});*/
		}
	});

	$.ajax({
		url : path + '/sysCodeManage/sysCodeListByType.html',
		type : "post",
		data : {
			type : 'SEX'
		},
		async : false,
		success : function(data) {
			$('#sex').combobox({
				// url:path+'/sysCodeManage/sexCodeList.html',
				data : data,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 70
			});
			$('#updatesex').combobox({
				data : data,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 70
			});
		}
	});

	$.ajax({
		url : path + '/sysCodeManage/sysCodeListByType.html',
		type : "post",
		data : {
			type : 'USER-STATUS'
		},
		async : false,
		success : function(data) {
			$('#queryStatus').combobox({
				// url:path+'/sysCodeManage/sexCodeList.html',
				data : data,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 70
			});
		}
	});

	// 添加角色查询条件
	$.ajax({
		url : path + '/userManage/getSysRoleByUserId.html',
		type : "post",
		data : {
			userId : ''
		},
		async : false,
		success : function(data) {
			$('#queryRoleId').combobox({
				data : data,
				valueField : 'roleId',
				textField : 'roleName',
				panelWidth : 132,
				panelHeight : 70
			});
		}
	});

}

function clearAddFromData() {
	// $("#userId").val('');
	// $("#userName").val('');
	// $("#deptName").val('');
	// $("#station").val('');
	// $("#email").val('');
	// $("#telephone").val('');
	// $("#address").val('');
	// $("#postcode").val('');
	// $("#description").val('');
	// $('#sex').combobox('clear');
	// $('#orgId').combobox('clear');
	$('#addForm').form('clear');
}

function clearUpdateFromData() {
	// $("#updateuserId").val('');
	// $("#updateuserName").val('');
	// $("#updatedeptName").val('');
	// $("#updatestation").val('');
	// $("#updateemail").val('');
	// $("#updatetelephone").val('');
	// $("#updateaddress").val('');
	// $("#updatepostcode").val('');
	// $("#updatedescription").val('');
	// $('#updatesex').combobox('clear');
	// $('#updateorgId').combobox('clear');
	$('#editDlg').form('clear');
}

function openDialog() {
	clearAddFromData();
	// $("#userId").val('aaaaaaa');
	$
			.ajax({
				url : path + '/userManage/getSysRoleByUserId.html',
				type : "post",
				data : {
					userId : ''
				},
				success : function(data) {
					var str = "";
					for ( var i = 0; i < data.length; i++) {
							str += '<label style="width:150px;"><input name="role" type="radio" value="'
									+ data[i].roleId
									+ '"/>'
									+ data[i].roleName
									+ '</label>';
					}
					$('#userrole').html(str);
					$("#addDlg").window('open');
				}
			});

}

function openUpdateDialog(userid) {
	clearUpdateFromData();
	$
			.ajax({
				url : path + '/userManage/getUserInfoById.html',
				type : "post",
				data : {
					userId : userid
				},
				success : function(data) {
					// alert(data);
					$("#updateuserId").textbox('setValue',data.userid);
					$("#updateuserName").textbox('setValue', data.username);
					$("#updatedeptName").textbox('setValue', data.deptname);
					$("#updatestation").textbox('setValue', data.station);
					$("#updateemail").textbox('setValue', data.email);
					$("#updatetelephone").textbox('setValue', data.tel);
					$("#updateaddress").textbox('setValue', data.address);
					$("#updatepostcode").textbox('setValue', data.postcode);
					$("#updatedescription").val(data.description);
					$('#updatesex').combobox('setValue', data.sex);
					$('#updateorgId').combobox('setValue', data.orgid);
					$('#updateAuthOrgId').combobox('setValue', data.authorgid);
					$.ajax({
								url : path
										+ '/userManage/getSysRoleByUserId.html',
								type : "post",
								data : {
									userId : data.userid
								},
								success : function(rlt) {
									// 非总行用户不能编辑
									// true == 总行用户；false == 非总行用户
									var orglev_flag = true;
								
									var str = "";

									for ( var i = 0; i < rlt.length; i++) {

										if (rlt[i].userId == userid) {
											// if(rlt[i].notIn == "T"){
											if (orglev_flag == false) {
												str += '<label style="width:150px;"><input name="updaterole" type="radio" disabled="disabled" checked="true" value="'
														+ rlt[i].roleId
														+ '"/>'
														+ rlt[i].roleName
														+ '</label>';
											} else {
												str += '<label style="width:150px;"><input name="updaterole" type="radio" checked="true" value="'
														+ rlt[i].roleId
														+ '"/>'
														+ rlt[i].roleName
														+ '</label>';
											}
										} else {
											// if(rlt[i].notIn == "T"){
											if (orglev_flag == false) {
												str += '<label style="width:150px;"><input name="updaterole" type="radio" disabled="disabled" value="'
														+ rlt[i].roleId
														+ '"/>'
														+ rlt[i].roleName
														+ '</label>';
											} else {
												str += '<label style="width:150px;"><input name="updaterole" type="radio" value="'
														+ rlt[i].roleId
														+ '"/>'
														+ rlt[i].roleName
														+ '</label>';
											}

										}

									}

									$('#updateuserrole').html(str);

									$("#editDlg").window('open');
								}
							});

				}
			});
}

function doPostData() {
		var postdata = new Object();
		postdata.userId = $("#userId").val();
		postdata.userName = $("#userName").textbox('getValue');
		postdata.email = $("#email").textbox('getValue');
		postdata.telephone = $("#telephone").textbox('getValue');
		postdata.sex = $("#sex").combobox('getValue');
		postdata.orgId = $("#orgId").combobox('getValue');
		postdata.password = $.md5($("#password").textbox('getValue'));
		postdata.roleList = $('input[name="role"]:checked').val();
		postdata.updateType = 'save';
		$('#addDlg').mask('提交中, 请稍候...');
		$.ajax({
			url : path + '/userManage/saveOrUpdateUser.html',
			type : "post",
			data : postdata,
			success : function(data) {
				if ('success' == data.success) {
					$("#addDlg").window('close');
					$.messager.alert('成功', '添加成功!', 'info');
					doQuery();
				} else {
					$.messager.alert('错误', data.error, 'error');
				}
				$('#addDlg').unmask();
			}

		});

}

function doUpdatePostData() {
		var postdata = new Object();
		postdata.userId = $("#updateuserId").val();
		postdata.userName = $("#updateuserName").textbox('getValue');
		postdata.email = $("#updateemail").textbox('getValue');
		postdata.telephone =$("#updatetelephone").textbox('getValue');
		postdata.sex = $("#updatesex").combobox('getValue');
		postdata.orgId = $("#updateorgId").combobox('getValue');
		postdata.roleList = $('input[name="updaterole"]:checked').val();;
		postdata.updateType = 'update';
		$('#editDlg').mask('提交中, 请稍候...');
		$.ajax({
			url : path + '/userManage/saveOrUpdateUser.html',
			type : "post",
			data : postdata,
			success : function(data) {
				$('#editDlg').unmask();
				if ('success' == data.success) {
					$("#editDlg").window('close');
					$.messager.alert('成功', '修改成功', 'info');
					$("#usertable").datagrid('load',getQueryParam());
				} else {
					$.messager.alert('错误', data.error, 'error');
				}
			}
		});


}

function cancelUpdate() {
	$("#editDlg").window('close');
}

function cancelPostData() {
	$("#addDlg").window('close');
}

function delUser() {
	$.messager.confirm('确认','您确认想要删除用户吗？',function(r){
		if(r){
			MaskUtil.mask('正在删除，请稍候……');
			var list = $('#usertable').datagrid('getSelections');
			var postdata = "";
			for ( var i = 0; i < list.length; i++) {
				// alert(list[i].USERID);
				postdata += list[i].userid + ',';
			}

			$.ajax({
				url : path + '/userManage/delOrChangeUsersStatus.html',
				type : "post",
				data : {
					listdata : postdata,
					type : 'del'
				},
				success : function(data) {
					if ('success' == data.success) {
						doQuery();
					} else if ('PartOfSuc' == data.success) {
						doQuery();
						$.messager.alert('警告', data.error, 'warning');
					} else {
						$.messager.alert('错误', data.error, 'error');
					}
					MaskUtil.unmask();
				}
			});
		}})

}

function changeUserStatus() {
	$.messager.confirm('确认','您确认想要更新用户状态吗？',function(r){
		if(r){
			MaskUtil.mask('正在更新状态，请稍候……');
			var list = $('#usertable').datagrid('getSelections');
			var postdata = "";
			for ( var i = 0; i < list.length; i++) {
				// alert(list[i].USERID);
				postdata += list[i].userid + ',';
			}
			if (postdata == "") {
				MaskUtil.unmask();
				$.messager.alert('错误', '未选择用户，请选择后再操作！', 'error');
				return;
			}
			$.ajax({
				url : path + '/userManage/delOrChangeUsersStatus.html',
				type : "post",
				data : {
					listdata : postdata,
					type : 'chn'
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
		}})

}


function changeUserpwd() {
	MaskUtil.mask('正在更新状态，请稍候……');
	var list = $('#usertable').datagrid('getSelections');
	var postdata = "";
	for ( var i = 0; i < list.length; i++) {
		// alert(list[i].USERID);
		postdata += list[i].userid + ',';
	}
	if (postdata == "") {
		MaskUtil.unmask();
		$.messager.alert('错误', '未选择用户，请选择后再操作！', 'error');
		return;
	}
	$.ajax({
		url : path + '/userManage/delOrChangeUsersStatus.html',
		type : "post",
		data : {
			listdata : postdata,
			type : 'chn'
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
}

function restPwd(userid){
	$.messager.confirm('确认','您确认想要重置密码吗？',function(r){
		if(r){
			MaskUtil.mask('正在重置中，请稍候……');
			$.ajax({
				url : path + '/userManage/resetPwd.html',
				type : "post",
				data : {
					userId:userid
				},
				success : function(data) {
					if ('SUCCESS' == data.RET_CODE) {
						$.messager.alert('成功', data.RET_MSG, 'info');
					} else {
						$.messager.alert('错误', data.error, 'error');
					}
					MaskUtil.unmask();
				}

			});
		}})
}
