var path = getAppPath();
$(function(){
	setinitCombobox();
	//初始化数据列表
	initTable();
});

function initTable(){

	$('#DataListTable').datagrid({
		striped : true,
		url : path + '/InCtrctInf/getDataList.html',
		remoteSort : false,
		idField : 'CTRCTBSSGMTSEQNO',
		queryParams : queryParameter(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize:0,
		pageSize : 20,
		pageList : [ 20 ],
		columns : [ [{
			field : 'CTRCTBSSGMTSEQNO',
			title : '合同编号',
			width : '10%',
			align : 'center',
			formatter: function(value,row,index){
				return value.substr(0,value.length-8);
			}

		}, {
			field : 'RPTDATE',
			title : '信息报告日期',
			width : '10%',
			align : 'center'
		}, {
			field : 'NAME',
			title : '姓名',
			width : '10%',
			align : 'center'
		}, {
			field : 'IDTYPE',
			title : '证件类型',
			width : '20%',
			align : 'center',
			formatter: function(value,row,index){
				if(value=='1')	return '户口薄';
				if(value=='2')	return '护照';
				if(value=='5')	return '港澳居民来往内地通行证';
				if(value=='6')	return '台湾同胞来往内地通行证';
				if(value=='8')	return '外国人居留证';
				if(value=='9')	return '警官证';
				if(value=='A')	return '香港身份证';
				if(value=='B')	return '澳门身份证';
				if(value=='C')	return '台湾身份证';
				if(value=='X')	return '其他证件';
				if(value=='10')	return '居民身份证及其他以公民身份证号为标识的证件';
				if(value=='20')	return '军人身份证件';
			}
		}, {
			field : 'IDNUM',
			title : '证件号码',
			width : '10%',
			align : 'center'
		}, {
			field : 'MNGMTORGCODE',
			title : '业务管理机构',
			width : '10%',
			align : 'center'
		},
		{
			field : 'SOURCESYS',
			title : '系统',
			width : '10%',
			align : 'center',
			formatter: function(value,row,index){
				if(value=='PLN')	return '个贷';
				if(value=='ILN')	return '网贷';
				if(value=='SLNIND')	return '小微个人';
			}
		},
		{
			field : 'status',
			title : '操作',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.ISDEL=='1'){
					return ;
				}
				var id =row.CTRCTBSSGMTSEQNO;
				var str ='<a href="javascript:void(0)" onClick=deleteAll(\"'
					+id+ '\")>删除</a>';
				
					return str;
				
			}
		},{
			field : 'ISDEL',
			title : '是否已删除',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.ISDEL=='1'){
					return "已删除";
				}else{
					return ;
				}
				
			}
		} 
		 ] ]/*,
		 onDblClickRow : function(index, row) {
				var id=row.INCTRCTINFSEQNO;
				modify(id,"2");
			}*/
		
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
//跳转到修改页面
function deleteAll(infoKey){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.ajax({
	    		url : path + '/InCtrctInf/delete.html',
	    		type : "post",
	    		data : {
	    			// 主键
	    			"infoKey" : infoKey
	    		},
	    		async : false,
	    		success : function(data) {
	    			if (data.RET_CODE == "SUCCESS") {
	    				$.messager.alert('成功', '添加删除队列成功!', 'info');
	    				reflashTab("../jsp/nat/modify/InCtrctInf.jsp", "个人授信协议信息");
	    				doQuery();
	    				
	    			} else {
	    				$.messager.alert('错误', '删除失败!', 'error');
	    			}

	    		}
	    	});
	    }    
	}); 
	
}
//条件查询
function doQuery(){
	
	$('#DataListTable').datagrid('load',queryParameter());
	
}
//条件封装
function queryParameter(){
	var parameter=new Object();
	parameter.queryRptDate=$('#queryRptDate').datebox('getValue');
	parameter.queryName=$('#queryName').textbox('getValue');
	parameter.queryIDNum=$('#queryIDNum').textbox('getValue');
	parameter.queryMngmtOrgCode=$('#queryMngmtOrgCode').combobox('getValue');
	parameter.reportSys=$('#reportSys').combobox('getValue');
	return parameter;
}
//清除条件
function clearQuery(){
	$('#queryRptDate').datebox('clear');
	$('#queryName').textbox('clear');
	$('#queryIDNum').textbox('clear');
	$('#queryMngmtOrgCode').combobox('clear');
	$('#reportSys').combobox('clear');
}

//下拉框代码表
function setinitCombobox() {
	initCombobox('IND_REPORT_SYS', 'reportSys');	
}

