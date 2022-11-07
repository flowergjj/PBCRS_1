var path = getAppPath();

$(function() {
	setinitCombobox();
	// 初始化数据列表
	initTable();

});

var toolbar = [ {
	text : "添加",
	iconCls : "icon-save",
	"class" : "tooltip-info",
	handler : function() {
		$('#add').form('clear');
		$('#dialog-add').dialog('open');
	}
}, {
	text : "删除",
	iconCls : "icon-remove",
	"class" : "tooltip-info",
	handler : function() {
		remove();
	}
}/*, {
	text:"选择报送",
	iconCls: "icon-task",
	"class": "tooltip-info",
	handler:function(){
		report("DataListTable");
	}
}*/ ];

function initTable() {
	$('#DataListTable').datagrid({
		striped : true,
		url : path + '/InSpcInf/selectPageList.html',
		remoteSort : false,
		idField : 'inSpcEvtDscInfSeqNo',
		queryParams : queryParameter(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		toolbar:toolbar,
		columns : [ [{
			field : 'check',
			title : '勾选框',
			width : '15%',
			align : 'center',
		    checkbox:true
		},{
			field : 'acctCode',
			title : '账户标识码',
			width : '17%',
			align : 'center'
		}, {
			field : 'opetnType',
			title : '事件类型',
			width : '17%',
			align : 'center',
			formatter : function(value, row, index) {
				if (row.opetnType == '11') return "信用卡因调整账单日本月不出单";
				if (row.opetnType == '12') return "已注销信用卡账户重启";
				if (row.opetnType == '21') return "转出";
			}
		}, {
			field : 'flag',
			title : '生效标志',
			width : '17%',
			align : 'center',
			formatter : function(value, row, index) {
				if (row.flag == '0') return "无效";
				if (row.flag == '1') return "有效";
			}
		}, {
			field : 'month',
			title : '月份',
			width : '17%',
			align : 'center'
		}, {
			field : 'rptDate',
			title : '报送日期',
			width : '16%',
			align : 'center'
		}, {
			field : 'sourceSys',
			title : '系统号',
			width : '16%',
			align : 'center',
			formatter : function(value, row, index) {
				if (row.sourceSys == 'PLN') return "个贷";
				if (row.flag == 'ILN') return "网贷";
				if (row.flag == 'SLNIND') return "小微个人";
			}
		}/*, {
			field : 'reportFlag',
			title : '报送标志',
			width : '15%',
			align : 'center',
			formatter : function(value, row, index) {
				if (row.reportFlag == '1') {
					return "已报送";
				}

			}
		}*/
		
		] ],
		onDblClickRow : function(index,row){
			$('#dialog-update').dialog('open');
			$('#update').form('load',row);
	}
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
	$('#DataListTable').datagrid('clearSelections');
}

function submit(){
	$('#add').form('submit', {
		url: path + '/InSpcInf/insertInfo.html',
		onSubmit: function(){
			var isValid = $(this).form('validate');
			
			return isValid;	// 返回false终止表单提交
		},
		success: function(res){
			res=$.parseJSON(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '添加成功!', 'info');
				$('#DataListTable').datagrid('load',queryParameter());
				$('#dialog-add').dialog('close');
			}else{
				$.messager.alert('错误', '添加失败!', 'error');
			}
			
		}
	});

}
function submitU(){
	$('#update').form('submit', {
		url: path + '/InSpcInf/updateInfo.html',
		onSubmit: function(){
			var isValid = $(this).form('validate');
			
			return isValid;	// 返回false终止表单提交
		},
		success: function(res){
			 res=$.parseJSON(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '修改成功!', 'info');
				$('#DataListTable').datagrid('load',queryParameter());
				$('#dialog-update').dialog('close');
			}else{
				$.messager.alert('错误', '修改失败!', 'error');
			}
			
		}
	});

}

function remove(){
	var select = $('#DataListTable').datagrid("getSelections");
	if(select.length == 0){
		$.messager.alert('错误', '您没有选择要删除的记录!', 'error');
		return ;
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	
	    	var keys="";
	    	$.each(select,function (index,value){
	    		keys =keys +value.inSpcEvtDscInfSeqNo+";";
	    	});
	    	
	    	$.ajax({
	    		url : path + '/InSpcInf/deleteInfo.html',
	    		type : "post",
	    		data : {
	    			keys:keys
	    		},
	    		async : false,
	    		success : function(res) {
	    			if(res.RET_CODE=="SUCCESS"){
	    				$.messager.alert('成功', '删除成功!', 'info');
	    				$('#DataListTable').datagrid('load',queryParameter());
	    			}else{
	    				$.messager.alert('错误', '删除失败!', 'error');
	    			}
	    		}
	    	});
	    }    
	}); 
	

}


// 跳转到修改页面
function modify(index) {
	openSubTab(path + '/jsp/nat/modify/InAcctInfDetail.jsp?id=' + index, '个人借贷信息修改详细');
}
// 条件查询
function doQuery() {

	$('#DataListTable').datagrid('load', queryParameter());

}
// 下拉框代码表
function setinitCombobox() {
	initCombobox('OpetnType', 'opetnType');
	initCombobox('InSpcFlag', 'flag');
	initCombobox('IND_REPORT_SYS', 'sourceSys');
	initCombobox('OpetnType', 'opetnTypeU');
	initCombobox('InSpcFlag', 'flagU');
	initCombobox('IND_REPORT_SYS', 'sourceSysU');
}

// 条件封装
function queryParameter() {
	var parameter = new Object();
	parameter.queryRptDate=$('#queryRptDate').combobox('getValue');
	parameter.queryRptDate=$('#queryRptDate').combobox('getValue');
	return parameter;
}
// 清除条件
function clearQuery() {
	$('#queryRptDate').datebox('clear');
	$('#queryName').textbox('clear');
	$('#queryIDNum').textbox('clear');
	$('#queryAcctCredLine').textbox('clear');
	$('#acctCode').textbox('clear');
	$('#queryBusiLines').combobox('clear');
	$('#reportSys').combobox('clear');
}

function deleteAll(infoKey) {
	$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
		if (r) {
			$.ajax({
				url : path + '/InAcctInf/delete.html',
				type : "post",
				data : {
					// 主键
					"infoKey" : infoKey
				},
				async : false,
				success : function(data) {
					if (data.RET_CODE == "SUCCESS") {
						$.messager.alert('成功', '添加删除队列成功!', 'info');
						
						doQuery();
					} else {
						$.messager.alert('错误', '删除失败!', 'error');
					}

				}
			});
		}
	});

}

function report(tableName){
	var set = new Set();
	var custArr;
	custArr=getSelectRows(tableName);
	if(custArr == false){
		return;
	}
	
	var reportId="";
	var id="";
	var etl_date="";
	
	for (var int = 0; int < custArr.length; int++) {
		reportId=custArr[int].infRecType;
		if(id==""){
			id=id+"'"+custArr[int].inSpcEvtDscInfSeqNo+"'";
		}else{
			id=id+",'"+custArr[int].inSpcEvtDscInfSeqNo+"'";
		}
		etl_date=custArr[int].rptDate;
		set.add(reportId);
		set.add(etl_date);
	}
	if(set.size !=2 ){
		$.messager.alert('错误', '请选择同一种报文类型和同一报送时间!', 'error');
		return ;
	}
	var url =path+"/reportMod/report.html";
	$.ajax({
		url : url,
		type : "post",
		data : {
			reportId : reportId,
			id		 :	id,
			etl_date  :etl_date
			
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
			
			
		}
	});
}

function getSelectRows(tableName){
	var rows = $('#'+tableName).datagrid('getChecked');
	if(rows.length == 0){
		$.messager.alert('提示','你还没有选择任何记录!');
		return false;
	}

/*	else{
		for(var i=0;i<rows.length;i++){
			if(rows[i].REPORTSTAUT=='1' ||rows[i].REPORTSTAUT=='2'){
				$.messager.alert('提示','请选择未处理的记录!');
				return false;
			}
		}
	}*/

	//EnReportModTable
	var rowSData = $('#'+tableName).datagrid('getSelections');

	return  rowSData;
}
