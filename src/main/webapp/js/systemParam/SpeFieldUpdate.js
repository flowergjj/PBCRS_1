var path = getAppPath();

var lazy = false;
$(function() {
	initCombox();
	getentTable();
	
});
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
   				text:"删除",
   				iconCls: "icon-remove",
   				"class": "tooltip-info",
   				handler:function(){
   					delParam();
   					
   				}
   			}

   		];

function getentTable() {

	
	$('#ParamTable').datagrid({
		striped : true,
		url : path + '/spefieldupdate/listPage.html',
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
		}, {
			field : 'REPORT_TYPE',
			title : '报文类型',
			width : '10%',
			align : 'center'
		},{
			field : 'SGMT_NAME',
			title : '报文段',
			width : '10%',
			align : 'center'
		}, {
			field : 'SPE_TABLE_NAME',
			title : '表名',
			width : '15%',
			align : 'center'
			
		} , {
			field : 'SPE_CLOUMN_NAME',
			title : '修改字段',
			width : '10%',
			align : 'center'
			
		}, {
			field : 'UPDCOMMENTS',
			title : '修改字段中文含义',
			width : '10%',
			align : 'center'
			
		}, {
			field : 'SPE_CLOUMN',
			title : '修改字段值',
			width : '10%',
			align : 'center'
			
		} 
		, {
			field : 'TERM_NAME',
			title : '条件字段',
			width : '10%',
			align : 'center'
			
		} 
		, {
			field : 'CONCOMMENTS',
			title : '条件字段中文含义',
			width : '10%',
			align : 'center'
			
		} 
		, {
			field : 'TERM',
			title : '条件字段值',
			width : '13%',
			align : 'center'
			
		} ] ]
	});
	// 设置分页控件
	var p = $('#ParamTable').datagrid('getPager');
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


function openDialog() {
	$('.textbox-icon-disabled').attr('class','textbox-icon icon-search')
	clearAddFromData();
	$('#conditionName').combobox('loadData','');
	$("#addDlg").window('open');
}
function clearAddFromData() {
	$('#addForm').form('clear');
}

function openCheckDlg(){
	
	$('#fieldInfoTable').datagrid({
		striped : true,
		url : path + '/spefieldupdate/fieldinfolist.html',
		remoteSort : false,
		//idField : 'TABLEKEY',
		queryParams : {
			fieldName:$('#fieldName').textbox('getValue')
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [  {
			field : 'REPORT_TYPE',
			title : '报文类型',
			width : '25%',
			align : 'center'
		},{
			field : 'SGMT_NAME',
			title : '报文段',
			width : '25%',
			align : 'center'
		}, {
			field : 'FIELD_NAME',
			title : '字段',
			width : '25%',
			align : 'center'
			
		} , {
			field : 'COMMENTS',
			title : '字段中文含义',
			width : '25%',
			align : 'center'
			
		} ] ],
		onDblClickRow:function(index,row){
			$("#checkDlg").window('close');
			$('#updFieldName').textbox('setValue',row.COMMENTS);
			$('#reportType').textbox('setValue',row.REPORT_TYPE);
			$('#sgmtName').textbox('setValue',row.SGMT_NAME);
			$('#updField').val(row.FIELD_NAME);
			$('#tableName').val(row.TABLE_NAME);
			
			initConditionCombobox(row.TABLE_NAME);
		}
	});
	// 设置分页控件
	var p = $('#fieldInfoTable').datagrid('getPager');
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
	
	$("#checkDlg").window('open');
}

function doFieldQuery(){
	$('#fieldInfoTable').datagrid('load',{
		fieldName:$('#fieldName').textbox('getValue')
	});
}

function setinitCombobox(){
	
}
//初始化条件下拉框
function initConditionCombobox(tableName) {
	$.ajax({
		url : path + '/spefieldupdate/getConditionCombox.html',
		type : "post",
		data : {
			tableName : tableName
		},
		async : false,
		success : function(data) {
			$('#conditionName').combobox({
				data : data,

				valueField : 'ID',
				textField : 'TEXT',
				panelWidth : 150,
				panelHeight : 150
			});
		}
	});

}

function doSaveData(){
	if (!$('#addForm').form('enableValidation').form('validate')) {
		$.messager.alert('错误', '请将信息补充完整!', 'error');
		return;
	}
	$('#conditionDesc').val($('#conditionName').combobox('getText'));
	console.log($('#addForm').serialize());
	$.ajax({
		url : path + '/spefieldupdate/insertSpedeal.html?'+$('#addForm').serialize(),
		type : "post",
		success : function(data) {
			if ('SUCCESS' == data.RET_CODE) {
				$("#addDlg").window('close');
				$.messager.alert('成功', '添加成功!', 'info');
				$('#ParamTable').datagrid('load',getQueryParam());
			} else {
				$.messager.alert('错误', '添加失败!', 'error');
			}
		}
	});
}


function delParam() {
	
	var list = $('#ParamTable').datagrid('getSelections');
	console.info(list.length);
	//console.info(list.size());
	if(list.length == 0){
		$.messager.alert('错误', '请先选择需要删除的信息!', 'error');
		return ;
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){   
	    	$.ajax({
	    		url : path + '/spefieldupdate/deleteSpedeal.html',
	    		type : "post",
	    		data :{
	    			deleteList:JSON.stringify(list)
	    		},
	    		success : function(data) {
	    			if ('SUCCESS' == data.RET_CODE) {
	    				$.messager.alert('成功', '删除成功!', 'info');
	    				$('#ParamTable').datagrid('load',getQueryParam());
	    	
	    			} else {
	    				$.messager.alert('错误', '删除失败!', 'error');
	    			}
	    		}
	    	});
	    	
	        
	    }    
	}); 
	//$('#roletable').datagrid('clearSelections');
}

//条件查询
function doQuery(){
	
	$('#ParamTable').datagrid('load',getQueryParam());
	
	
}

function getQueryParam() {
	var queryObject = new Object();	
	queryObject.report_type = $('#report_type').textbox('getValue');
	queryObject.sgmt_name = $('#sgmt_name').textbox('getValue');
	queryObject.comments = $('#comments').textbox('getValue');
	queryObject.spe_cloumn = $('#spe_cloumn').textbox('getValue');
	return queryObject;
}

//清除条件
function doClear(){
	 $('#report_type').textbox('clear');
	 $('#sgmt_name').textbox('clear');
	 $('#comments').textbox('clear');
	 $('#spe_cloumn').textbox('clear');
}