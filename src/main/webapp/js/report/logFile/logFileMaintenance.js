var path = getAppPath();
$(function() {
	//初始化数据列表
	initTable();
	var type=null;
	getRptType(type);
	
	var rptData = $('#queryRptType').combobox('getData');


});
function initTable(){

	$('#DataListTable').datagrid({
		striped : true,
		url : path + '/reportMod/getLogInfo.html',
		remoteSort : false,
		idField : 'CONDITIONID',
		queryParams : queryParameter(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize:0,
		pageSize : 21,
		pageList : [ 21 ],
		columns : [ [ {
			field : 'TXTFILENAME',
			title : '文件名称',
			width : '14%',
			align : 'center'
		}, {
			field : 'SGMTID',
			title : '报文类型',
			width : '14%',
			align : 'center',
			formatter : function(value, row, index) {
				var bussType= row.SGMTID;
				//alert(bussType);
				if(bussType=='110') return  "个人基本信息记录";
				if(bussType=='114') return  "个人基本信息删除请求记录 ";
				if(bussType=='120') return  "家族关系信息记录"; 
				if(bussType=='130') return  "个人证件有效期信息记录   ";    
				if(bussType=='134') return  "个人证件有效期信息删除请求记录";
				if(bussType=='140') return  "个人证件整合信息记录      ";   

				if(bussType=='210') return  "个人借贷账户记录";
				if(bussType=='211') return  "个人借贷账户标识变更请求记录";
				if(bussType=='212') return  "个人借贷账户按段更正请求记录";
				if(bussType=='213') return  "个人借贷账户按段删除请求记录";
				if(bussType=='214') return  "个人借贷账户整笔删除请求记录";
				if(bussType=='215') return  "个人借贷账户特殊事件说明记录";
				if(bussType=='220') return  "个人授信协议信息记录";
				if(bussType=='221') return  "个人授信协议标识变更请求记录";
				if(bussType=='222') return  "个人授信协议按段更正请求记录";
				if(bussType=='223') return  "个人授信协议按段删除请求记录";
				if(bussType=='224') return  "个人授信协议整笔删除请求记录";

				/*if(bussType=='230') return  "个人担保账户信息记录        ";
				if(bussType=='231') return  "个人担保账户标识变更请求记录";
				if(bussType=='232') return  "个人担保账户更正请求记录    ";
				if(bussType=='233') return  "个人担保账户按段删除请求记录";
				if(bussType=='234') return  "个人担保账户整笔删除请求记录";
*/
				if(bussType=='510') return  "抵(质)押合同信息记录";
				if(bussType=='511') return  "抵(质)押合同标识变更请求记录";
				if(bussType=='514') return  "抵(质)押合同整笔删除请求记录";

				if(bussType=='310') return  "企业基本信息记录";
				if(bussType=='314') return  " 企业基本信息删除请求记录";
				if(bussType=='340') return  " 企业身份标识整合信息记录";
				if(bussType=='350') return  " 企业间关联关系信息记录 ";

				if(bussType=='410') return  "企业借贷账户记录";
				if(bussType=='411') return  " 企业借贷账户标识变更请求记录      ";
				if(bussType=='412') return  " 企业借贷账户更正请求记录          ";
				if(bussType=='413') return  " 企业借贷账户按段删除请求记录      ";
				if(bussType=='414') return  " 企业借贷账户整笔删除请求记录      ";
				if(bussType=='420') return  "企业授信协议信息记录";
				if(bussType=='421') return  " 企业授信协议标识变更请求记录      ";
				if(bussType=='422') return  " 企业授信协议更正请求记录          ";
				if(bussType=='423') return  " 企业授信协议按段删除请求记录      ";
				if(bussType=='424') return  " 企业授信协议整笔删除请求记录      ";
				if(bussType=='430') return  " 企业最高额保证合同信息记录        ";
				if(bussType=='431') return  " 企业最高额保证合同标识变更请求记录";
				if(bussType=='432') return  " 企业最高额保证合同更正请求记录    ";
				if(bussType=='433') return  " 企业最高额保证合同按段删除请求记录";
				if(bussType=='434') return  " 企业最高额保证合同整笔删除请求记录";

				if(bussType=='440') return  "企业担保账户信息记录";
				if(bussType=='441') return  "企业担保账户标识变更请求记录";
				if(bussType=='442') return  "企业担保账户更正请求记录    ";
				if(bussType=='443') return  "企业担保账户按段删除请求记录";
				if(bussType=='444') return  " 企业担保账户整笔删除请求记录";

				 if(bussType=='610') return  "企业资产负债表信息记录";
				if(bussType=='620') return  "企业利润及利润分配表信息记录";
				if(bussType=='630') return  "企业现金流量表信息记录";
				if(bussType=='640') return  "事业单位资产负债表信息记录";
				if(bussType=='650') return  "事业单位收入支出表信息记录"; 
				if(bussType=='614') return  "企业资产负债表整笔删除请求记录 ";
				if(bussType=='624') return  "企业利润及利润分配表整笔删除请求记录";
				if(bussType=='634') return  " 企业现金流量表整笔删除请求记录     ";
				if(bussType=='644') return  " 事业单位资产负债表整笔删除请求记录 ";
				if(bussType=='654') return  " 事业单位收入支出表整笔删除请求记录 ";
			}
				
		}, {
			field : 'LOG_INFO',
			title : '日志信息',
			width : '14%',
			align : 'center'
		},
		{
			field : 'ETL_TX_DATE',
			title : '报送日期',
			width : '14%',
			align : 'center'
		},
		{
			field : 'START_TIME',
			title : '开始时间',
			width : '14%',
			align : 'center'
		},
		{
			field : 'END_TIME',
			title : '结束时间',
			width : '14%',
			align : 'center'
		},
		{
			field : 'BADFILENAME',
			title : '错误文件',
			width : '14%',
			align : 'center'
		}] ],
		
			onLoadSuccess : function(data){
				var remarklist=data.rows;
				for(var i=0;i<remarklist.length/3;i++){
					
					$(this).datagrid('mergeCells', {
						index : i*3,
					   field : 'SGMTID',
						rowspan : 3
					});
					$(this).datagrid('mergeCells', {
						index : i*3,
					   field : 'TXTFILENAME',
						rowspan : 3
					});
					$(this).datagrid('mergeCells', {
						index : i*3,
					   field : 'ETL_TX_DATE',
						rowspan : 3
					});
					$(this).datagrid('mergeCells', {
						index : i*3,
					   field : 'BADFILENAME',
						rowspan : 3
					});
				}
			}
		
	});
	// 设置分页控件
	var p = $('#DataListTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 21,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
	$('#DataListTable').datagrid('clearSelections');
}

//条件查询
function doQuery(){
	
	$('#DataListTable').datagrid('load',queryParameter());
	
}
//条件封装
function queryParameter(){
	var parameter=new Object();
	parameter.reportDate=$('#reportDate').datebox('getValue');
	
	parameter.reportType=$('#queryRptType').combobox('getValue');
	return parameter;
}
//清除条件
function clearQuery(){
	$('#reportDate').datebox('clear');
	/*$('#queryName').textbox('clear');
	$('#queryNum').textbox('clear');*/
	$('#queryRptType').combobox('clear');
}


