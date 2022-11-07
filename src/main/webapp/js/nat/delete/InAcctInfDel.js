var path = getAppPath();
$(function(){
	//初始化数据列表
	initTable();
	setinitCombobox();
});

function initTable(){

	$('#DataListTable').datagrid({
		striped : true,
		url : path + '/InAcctInf/getDataList.html',
		remoteSort : false,
		idField : 'ACCTBSSGMTSEQNO',
		queryParams : queryParameter(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize:0,
		pageSize : 20,
		pageList : [ 20 ],
		columns : [ [ {
			field : 'RPTDATE',
			title : '信息报告日期',
			width : '8%',
			align : 'center'
		}, {
			field : 'NAME',
			title : '姓名',
			width : '8%',
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
			width : '8%',
			align : 'center'
		}, {
			field : 'ACCTCREDLINE',
			title : '信用额度',
			width : '8%',
			align : 'center'
		}, 
		{
			field : 'ACCTCODE',
			title : '借据号',
			width : '8%',
			align : 'center'
		},
		{
			field : 'DUEDATE',
			title : '到期日期',
			width : '8%',
			align : 'center'
		},{
			field : 'BUSILINES',
			title : '借贷业务大类',
			width : '8%',
			align : 'center',
			formatter: function(value,row,index){
				if(value=='1') return	'贷款'; 
				if(value=='2') return	'信用卡'; 
				if(value=='3') return	'证券类融资';  
				if(value=='4') return	'融资租赁';  
				if(value=='5') return	'资产处置'; 
				if(value=='6') return	'垫款';  
			}
		},{
			field : 'SOURCESYS',
			title : '系统',
			width : '8%',
			align : 'center',
			formatter: function(value,row,index){
				if(value=='PLN')	return '个贷';
				if(value=='ILN')	return '网贷';
				if(value=='SLNIND')	return '小微个人';
			}
		},{
			field : 'status',
			title : '操作',
			width : '8%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.ISDEL=='1'){
					return ;
				}
				var id =row.ACCTBSSGMTSEQNO;
				var str ='<a href="javascript:void(0)" onClick=deleteAll(\"'
					+id+'\")>删除</a>';
				
						return str;
					
			}
		}
		,{
			field : 'ISDEL',
			title : '是否已删除',
			width : '8%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.ISDEL=='1'){
					return "已删除";
				}else{
					return ;
				}
				
			}
		}] ]
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
	    				reflashTab("../jsp/nat/modify/InAcctInf.jsp", "个人借贷交易账户信息");
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
	parameter.queryAcctCredLine=$('#queryAcctCredLine').textbox('getValue');
	parameter.queryBusiLines=$('#queryBusiLines').combobox('getValue');
	parameter.reportSys=$('#reportSys').combobox('getValue');
	parameter.acctCode=$('#acctCode').textbox('getValue');
	return parameter;
}
//清除条件
function clearQuery(){
	$('#queryRptDate').datebox('clear');
	$('#queryName').textbox('clear');
	$('#queryIDNum').textbox('clear');
	$('#queryAcctCredLine').textbox('clear');
	$('#queryBusiLines').combobox('clear');
	$('#reportSys').combobox('clear');
	$('#acctCode').textbox('clear');
}

//下拉框代码表
function setinitCombobox() {
	initCombobox('AcctBusiLines', 'queryBusiLines');
	initCombobox('IND_REPORT_SYS', 'reportSys');	
}