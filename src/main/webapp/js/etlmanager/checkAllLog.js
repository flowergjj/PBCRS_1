var path = getAppPath();
var dealDt = getQueryString('dealDt');
var condition = getQueryString('condition');
$(function(){
	setinitCombobox();
	if(dealDt!=null && condition!=null){
		$('#pEtlDate').datebox('setValue',dealDt);
		if(condition != 'PKG_CHECKER'){
			$('#checkReportType').combobox('select',condition);
		}
		
	}
	//初始化数据列表
	getTable();
	
	
	
	
});
function getTable(){

	$('#checkAllLog').datagrid({
		striped : true,
		url : path + '/etlManager/getCheckAllLog.html',
		remoteSort : false,
		idField : '',
		queryParams : queryParameter(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize:0,
		pageSize : 20,
		pageList : [ 20 ],
		columns : [ [ {
			field : 'RN',
			title : '序号',
			width : '11%',
			align : 'center'
		}, {
			field : 'MSG',
			title : '错误信息',
			width : '11%',
			align : 'center'
		}, {
			field : 'CHECKFUNC',
			title : '校验规则',
			width : '11%',
			align : 'center'
		},{
			field : 'f',
			title : '报文类别',
			width : '11%',
			align : 'center',
			formatter : function(value, row, index) {
				//return "test";
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_INBASINFO')>=0){
					return '个人基本信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_INACCTINF')>=0){
					return '个人借贷账户信息记录';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_INCTRCTINF')>=0){
					return '个人授信协议信息记录';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_INMOTCLTCTRINF')>=0){
					return '个人抵(质)押合同信息记录';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_MOTCLTCTRINF')>=0){
					return '抵(质)押合同信息记录';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENBASINFO')>=0){
					return '企业基本信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENCTRCTINF')>=0){
					return '企业授信协议信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENACCTINF')>=0){
					return '企业借贷账户信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENSECACCTINF')>=0){
					return '企业担保账户信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENMOTCLTCTRINF')>=0){
					return '企业抵(质)押合同信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENCASHFLOW')>=0){
					return '企业现金流量表信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENBALANCESHEET')>=0){
					return '企业资产负债表信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENINCAND')>=0){
					return '事业单位收入支出表信息';
				}
				if(row.CHECKFUNC.toUpperCase().indexOf('PKG_CHECKER_ENINSBALSHE')>=0){
					return '事业单位资产负债表信息';
				}

				

				

				

				

				//事业单位资产负债表信息 PKG_CHECKER_ENINSBALSHE
					
			}
		}, {
			field : 'TOTAL',
			title : '错误个数',
			width : '11%',
			align : 'center'
		}, {
			field : 'P_ETL_DATE',
			title : '校验日期',
			width : '11%',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '7%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.ISDEL=='1'){
					return ;
				}
				var CHECKFUNC =row.CHECKFUNC;
				var P_ETL_DATE =row.P_ETL_DATE;
				var str ='<a href="javascript:void(0)" onClick=checkPK(\"'
					+CHECKFUNC+'\",\"'+P_ETL_DATE+'\")>查看错误主键</a>';
						return str;
					
			}
		} ] ]
	});
	// 设置分页控件
	var p = $('#checkAllLog').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
	//$('#checkAllLog').datagrid('clearSelections');
}

function checkPK(CHECKFUNC,P_ETL_DATE){
	initCheckTable(CHECKFUNC,P_ETL_DATE);
	$('#dialog-checkAll').dialog('open');
}

function initCheckTable(CHECKFUNC,P_ETL_DATE) {

	$('#checkAllTable').datagrid(
			{
				striped : true,
				url : path + '/etlManager/getErrorPK.html',
				remoteSort : false,
				idField : 'MEETINGNUM',
				queryParams :{
					'CHECKFUNC' :CHECKFUNC,
					'P_ETL_DATE' :P_ETL_DATE
				},
				singleSelect : true,// 是否单选
				pagination : true,// 分页控件
				pageNumber : 1,
				showFooter:true,
				pageSize : 20,
				pageList : [ 20 ],
				// rownumbers:true,//行号
				columns : [ [ {
					field : 'RN',
					title : '序号',
					width : '90',
					align : 'center'
				}, {
					field : 'SEQNO',
					title : '主键',
					width : '60',
					align : 'center'
				},
				{
					field : 'createusername',
					title : '操作',
					width : '80',
					align : 'center'
				} ] ]
				
				
			});
	// 设置分页控件
	var p = $('#checkAllTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});

	$('#checkAllTable').datagrid('clearSelections');
}

function doQuery(){
	$('#checkAllLog').datagrid('load',queryParameter());
}

function clearQuery(){
	
	$('#pEtlDate').datebox('clear');
	$('#checkReportType').combobox('clear');
}

function queryParameter(){
	var parameter=new Object();
	parameter.pEtlDate=$('#pEtlDate').datebox('getValue');
	parameter.checkReportType=$('#checkReportType').combobox('getValue');
	//parameter.pEtlDate=
	//parameter.checkReportType='DiYaXinXi';
	return parameter;
}

//下拉框代码表
function setinitCombobox() {
	initComboboxWH('CHECK_REPORT_TYPE', 'checkReportType',200,150);
	//initComboboxWH('ETL_TYPE', 'etlType',200,150);
	
}