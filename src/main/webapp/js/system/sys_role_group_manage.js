var path = getAppPath();

var lazy = false;
$(function(){
    $('#addDlg').window({
        modal: true,
        minimizable : false
    });
    $('#editDlg').window({
        modal: true,
        minimizable : false
    });
	//getRoleStatus();
	$.ajax({
		url:path+'/roleGroupManage/roleGroupStatusCode.html',
		type:"post",
		async : false,
		success:function(data){
			$('#status').combobox({    
//				url:path+'/orgManage/orgList.html', 
				data:data,
				valueField:'ID',    
				textField:'TEXT',
				panelWidth: 132,
				panelHeight: 120
			});
			$('#updatestatus').combobox({    
//				url:path+'/orgManage/orgList.html', 
				data:data,
				valueField:'ID',    
				textField:'TEXT',
				panelWidth: 132,
				panelHeight: 120
			});
			getRoleDG();
		}
	
	});
	$('body').css('visibility', 'visible');
});

function getRoleDG(){
	
	$('#grouptable').datagrid({  
        striped: true,  
        url:path+'/roleGroupManage/roleGroupList.html',  
        remoteSort:false,   
        idField:'groupId',  
//        queryParams: getQueryParam(),
        singleSelect:false,//是否单选  
        pagination:true,//分页控件  
		pageSize : 15,
		pageList: [15],
        rownumbers:false,//行号  
        frozenColumns:[[  
            {field:'ck',checkbox:true},
            {field : 'groupId', title : '角色组ID',width : '160',align : 'center'},
            {field : 'groupName', title : '角色组名称',width : '160',align : 'center'},
            {field : 'groupStatus', title : '角色组类型',width : '160',align : 'center'}
        ]],  
        toolbar: [{  
            text: '添加',  
            iconCls: 'icon-add',  
            handler: function() {
//            	$("#addDlg").window("open").window('setTitle', '新增'); 
				openDialog();
            }  
        }, '-',{  
            text: '删除',  
            iconCls: 'icon-remove',  
            handler: function(){  
                delRole();  
            }  
        }],
            onDblClickRow: function(index,row){
	        	openUpdateDialog(row); 
        	} 
    });  
    //设置分页控件  
    var p = $('#grouptable').datagrid('getPager');  
    $(p).pagination({  
        pageSize : 15,//每页显示的记录条数，默认为10   
        showPageList:false,
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
        /*onBeforeRefresh:function(){ 
            $(this).pagination('loading'); 
            alert('before refresh'); 
            $(this).pagination('loaded'); 
        }*/ 
    }); 
}

//数据新增提交处理
function doPostData(){
	$('#addDlg').mask('提交中, 请稍候...');
		var groupName = $('#groupName').val();
		var status = $('#status').combobox('getValue');
		if(groupName == ""){
			$.messager.alert('错误','角色组名称不能为空！','error');
			$('#addDlg').unmask();
			return;
		}
		if(status == ""){
			$.messager.alert('错误','角色组类型为必选项！','error');
			$('#addDlg').unmask();
			return;
		}
		var power = $("input[name='POWER']");
		var list = "";
		for(var i=0;i<power.length;i++){
			if($(power[i]).is(':checked')){
				if((i+1) == power.length){
					list += $(power[i]).val();
				}else{
					list += $(power[i]).val() + ",";
				}
			}
		}
//		if(list == ""){
//			$('#addDlg').unmask();
//			$.messager.alert('错误','新建的角色组必须不能为空！','error');
//			return;
//		}
		var postdata = new Object();
		postdata.groupName = groupName;
		postdata.status = status;
		postdata.powerList = list;
		postdata.type = 'save';
		$.ajax({
			url:path+'/roleGroupManage/saveOrUpdateRoleGroup.html',
			type:"post",
			data:postdata,
			success:function(data){
				if('success' == data.success){
					$('#addDlg').unmask();
					$("#addDlg").window('close');
//					doQuery();
                    $("#grouptable").datagrid('reload');
                    
				}else{
					$('#addDlg').unmask();
//					$("#addDlg").window('close');
//					$("#addDlg").window('open');
					$.messager.alert('错误',data.error,'error');
				}
			}
		});					
}


//数据修改提交处理
function doUpdatePostData(){
	$('#editDlg').mask('提交中, 请稍候...');
//		$("#editDlg").window('close');
	var groupName = $('#updategroupName').val();
	var groupId = $('#updategroupId').val();
	var status = $('#updatestatus').combobox('getValue');
	if(groupName == ""){
		$.messager.alert('错误','角色组名称不能为空！','error');
		$('#addDlg').unmask();
		return;
	}
	if(status == ""){
		$.messager.alert('错误','角色组类型为必选项！','error');
		$('#addDlg').unmask();
		return;
	}
		var power = $("input[name='power']");
		var list = "";
		for(var i=0;i<power.length;i++){
			if($(power[i]).is(':checked')){
				if((i+1) == power.length){
					list += $(power[i]).val();
				}else{
					list += $(power[i]).val() + ",";
				}
			}
		}
		var postdata = new Object();
		postdata.groupName = groupName;
		postdata.groupId = groupId;
		postdata.status = status;
		postdata.powerList = list;
		postdata.type = 'update';
		$.ajax({
			url:path+'/roleGroupManage/saveOrUpdateRoleGroup.html',
			type:"post",
			data:postdata,
			success:function(data){
				if('success' == data.success){
					
					$('#editDlg').unmask();
					$("#editDlg").window('close');
//					doQuery();
                    $("#grouptable").datagrid('reload');
				}else{
//					MaskUtil.unmask();
					$('#editDlg').unmask();
//					$("#editDlg").window('open');
					$.messager.alert('错误',data.error,'error');
				}
			}
		});
}

//角色修改
function openUpdateDialog(row){
	clearUpdateFromData();
		$("#updategroupId").val(row.groupId);
		$("#updategroupName").val(row.groupName);
		$('#updatestatus').combobox('select',row.status);
		$.ajax({
				url:path+'/roleGroupManage/roleListByGroupId.html',
				type:"post",
				data:{groupId:row.groupId},
				success:function(rlt){
					var str = "";
					for(var i=0;i<rlt.length;i++){
						if((i+1)%3 == 0){
							if(rlt[i].groupId == row.groupId){
								str += '<label style="width:160px;"><input name="power" type="checkbox" checked="true" value="'+rlt[i].roleId+'"/>'+rlt[i].roleName+'</label><br/>';
							}else{
								str += '<label style="width:160px;"><input name="power" type="checkbox" value="'+rlt[i].roleId+'"/>'+rlt[i].roleName+'</label><br/>';
							}
						}else{
							if(rlt[i].groupId == row.groupId){
								str += '<label style="width:160px;"><input name="power" type="checkbox" checked="true" value="'+rlt[i].roleId+'"/>'+rlt[i].roleName+'</label>';
							}else{
								str += '<label style="width:160px;"><input name="power" type="checkbox" value="'+rlt[i].roleId+'"/>'+rlt[i].roleName+'</label>';
							}
						}
					}
					$('#updaterolelist').html(str);
					$("#editDlg").window('open');
				}
			});
}

//角色删除
function delRole(){
	MaskUtil.mask('正在删除，请稍候……');
	var list = $('#grouptable').datagrid('getSelections');
	var postdata = "";
	for(var i=0;i<list.length;i++){
		postdata += list[i].groupId + ',';
	}
	if(postdata == ""){
		MaskUtil.unmask();
		$.messager.alert('错误','请选择需要删除的角色组！','error');
		return;
	}
	$.ajax({
		url:path+'/roleGroupManage/delRoleGroup.html',
		type:"post",
		data:{listdata:postdata},
		success:function(data){
			if('success' == data.success){
				MaskUtil.unmask();
				$("#grouptable").datagrid('reload');
				$('#grouptable').datagrid('clearSelections');
			}else{
				MaskUtil.unmask();
				$.messager.alert('错误',data.error,'error');
			}
		}
	});
}  

/**
 * 获取请求信息
 */
function getQueryParam(){
	var queryObject = new Object();
	queryObject.ROLEID = $('#ROLEID').val();
	queryObject.ROLENAME = $('#ROLENAME').val();
//	queryObject.ROLELEVEL = $('#ROLELEVEL').combobox('getValue');
	return queryObject;
}

/**
 *  查询数据
 */
function doQuery(){
	$("#roletable").datagrid('unselectAll');
	$("#roletable").datagrid('load',getQueryParam());
}

function getRoleStatus(){
	$.ajax({
		url:path+'/sysCodeManage/selRoleStatusByType.html',
		type:"post",
		data:{type:'ROLE-STATUS'},
		async : false,
		success:function(data){
			$('#STATUS').combobox({ 
				data:data,
				valueField:'ID',    
				textField:'TEXT',
				panelWidth: 132,
				panelHeight: 70
			});
			$('#status').combobox({ 
				data:data,
				valueField:'ID',    
				textField:'TEXT',
				panelWidth: 132,
				panelHeight: 70
			});
		}
	});
}

function clearAddFromData(){
	$('#addForm').form('clear');
	$('#rolelist').html("");
}
function clearUpdateFromData(){
	$('#editDlg').form('clear');
	$('#updaterolelist').html("");
}

function openDialog(){
	clearAddFromData();
	$.ajax({
		url:path+'/roleGroupManage/roleList.html',
		type:"post",
		success:function(data){
			var str = "";
			for(var i=0;i<data.length;i++){
				if((i+1)%3 == 0){
					str += '<label  style="width:160px;"><input name="POWER" type="checkbox" value="'+data[i].roleId+'"/>'+data[i].roleName+'</label><br/>';
				}else{
					str += '<label  style="width:160px;"><input name="POWER" type="checkbox" value="'+data[i].roleId+'"/>'+data[i].roleName+'</label>';
				}
			}
			$('#rolelist').html(str);
			$("#addDlg").window('open');
		}
	});
	
}

