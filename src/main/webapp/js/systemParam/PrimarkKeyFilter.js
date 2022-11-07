var path = getAppPath();

var lazy = false;
$(function() {
	initCombox();
	$('body').css('visibility', 'visible');
	//getOrgData();
	getentTable();
	var errmsg=getQueryString('msg');
	if(errmsg)
		$('#errormsg').html(errmsg);
});

function doMore(){
	$('#queryMore').show();
}
function initCombox(){
	initCombobox('REPORT_SOURCESYS','querySourceSys');
	initCombobox('BusiType','QueryBusiType');
}

var toolbar = [
   			{
   				text:"新增",
   				iconCls: "icon-add",
   				"class": "tooltip-info",
   				handler:function(){
   					openDialog();
   				}
   			},{
   				text:"启用/停用",
   				iconCls: "icon-edit",
   				"class": "tooltip-info",
   				handler:function(){
   					delCustId();
   					
   				}
   			}

   		];

function getentTable() {

	
	$('#FilterTable').datagrid({
		striped : true,
		url : path + '/speCustId/listPage.html',
		remoteSort : false,
		//idField : 'TABLEKEY',
		queryParams : getQueryParam(),
		toolbar : toolbar,
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'ck',
			checkbox : true
		},{
			field : 'SOURCECUSTID',
			title : '业务主键',
			width : '15%',
			align : 'center'
		}, {
			field : 'SPE_TYPE',
			title : '主键类型',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if(value == "1") return '客户号';
				if(value == "2") return '借贷账户标识码';
				if(value == "3") return '抵质押合同标识码';
			}
		},{
			field : 'STATE',
			title : '状态',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if(value == "1") return '启用';
				if(value == "0") return '停用';
			}
		},{
			field : 'SOURCESYS',
			title : '系统',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='CRMS') return '信贷';
				if( value=='PLN') return '个贷';
			}
		}   ] ]
	});
	// 设置分页控件
	var p = $('#FilterTable').datagrid('getPager');
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

/*function modify(url,mode){
	openSubTab('../jsp/ent/modify/BalanceSheetDetial.jsp?id='+url+"&mode="+mode,'企业资产负债表信息修改详细');
}*/

//条件查询
function doQuery(){
	
	$('#FilterTable').datagrid('load',getQueryParam());
	
}

function getQueryParam() {
	var queryObject = new Object();	
	queryObject.QuerysourceCustId = $('#QuerysourceCustId').val();
	queryObject.QueryState = $('#QueryState').combobox('getValue');
	queryObject.querySourceSys = $('#querySourceSys').combobox('getValue');
	return queryObject;
}
//清除条件
function doClear(){
	$('#QuerysourceCustId').textbox('clear');
	$('#QueryState').combobox('clear');
	$('#querySourceSys').combobox('clear');
	//$('#queryOrg').combobox('clear');
}

function clearAddFromData() {
	$('#addForm').form('clear');
}

function openDialog() {
	clearAddFromData();
	setinitCombobox();
	$("#addDlg").window('open');
}

//角色删除
function delCustId() {
	MaskUtil.mask('正在删除，请稍候……');
	var list = $('#FilterTable').datagrid('getSelections');
	//console.info(list);
	if (list.length == 0) {
		MaskUtil.unmask();
		$.messager.alert('错误', '请选择需要删除的角色！', 'error');
		return;
	}
	$.ajax({
		url : path + '/speCustId/delCustId.html',
		type : "post",
		data : {
			listdata : list,
			listSize : list.length
		},
		success : function(data) {
			if ('SUCCESS' == data.RET_CODE) {
				$.messager.alert('成功', '删除成功!', 'info');
				MaskUtil.unmask();
				doQuery();
			} else {
				MaskUtil.unmask();
				$.messager.alert('错误', data.RET_MSG, 'error');
			}
		}
	});
	$('#FilterTable').datagrid('clearSelections');
}

function setinitCombobox(){
	initComboboxWH('REPORT_SOURCESYS', 'sourceSys',150,150);
	setSpeType();
}

function doSaveData(){
	MaskUtil.mask('正在提交，请稍候……');
	if (!$('#addForm').form('enableValidation').form('validate')) {
		MaskUtil.unmask();
		return;
	} else {
		$("#addDlg").window('close');
		var postdata = new Object();
		postdata.sourceSys = $("#sourceSys").combobox('getValue');
		postdata.sourceCustId = $("#sourceCustId").val();
		postdata.speType = $("#speType").combobox('getValue');
		postdata.state = "1";//默认为有效
		$.ajax({
			url : path + '/speCustId/saveCustId.html',
			type : "post",
			data : postdata,
			success : function(data) {
				if ('SUCCESS' == data.RET_CODE) {
					$.messager.alert('成功', '添加成功!', 'info');
					MaskUtil.unmask();
					// doQuery();
					$("#FilterTable").datagrid('reload');
				} else {
					MaskUtil.unmask();
					$("#addDlg").window('open');
					$.messager.alert('错误', data.RET_MSG, 'error');
				}
			}
		});
	}
}

function setSpeType(){
	var data = [{    
	    "ID":1,    
	    "TEXT":"客户号"   
	},{    
	    "ID":2,    
	    "TEXT":"借贷账户标识码"
	},{    
	    "ID":3,    
	    "TEXT":"抵质押合同标识码"
	}/*,{
	    "ID":4,    
	    "TEXT":"证件号"   
	}*/] ;
	$('#speType').combobox({
		data : data,
		valueField : 'ID',
		textField : 'TEXT',
		panelWidth : 150,
		panelHeight : 150
	});
	
}
