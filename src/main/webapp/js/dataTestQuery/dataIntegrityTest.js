/**
 * 数据报送完整性
 */
$(function(){
	//initCombobox('ENT_REPORT_SYS','querySourceSys');
	$("#etlDate").datebox('setValue',getNowFormatDate());
	DcTable();
	GuTable();
	BaseTable();
	ReportTable();
	D1Table();
	D2Table();
	R4Table();
	ClTable();
	G1Table();
	G2Table();
	G3Table();
	DzyTable();
})

//借贷业务实际开展情况表
function DcTable(){
	$('#DcTable').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("DcTable"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'SYSCODEDESC',
			title : '借贷业务大类',
			width : '25%',
			align : 'center'
		}, {
			field : 'CUSTTOTAL',
			title : '总客户数',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTTOTAL',
			title : '账户笔数',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '余额',
			width : '25%',
			align : 'center'
		} ] ]
	});
}
//担保业务实际开展情况表
function GuTable(){
	$('#GuTable').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("GuTable"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'SYSCODEDESC',
			title : '担保业务大类',
			width : '25%',
			align : 'center'
		}, {
			field : 'CUSTTOTAL',
			title : '总客户数',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTTOTAL',
			title : '账户笔数',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '余额',
			width : '25%',
			align : 'center'
		} ] ]
	});
}

//企业基本信息比对结果表
function BaseTable(){
	$('#BaseTable').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("BaseTable"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}

//财务报表信息比对结果表
function ReportTable(){
	$('#ReportTable').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("ReportTable"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}
//非循环贷账户（D1）业务量比对结果表
function D1Table(){
	$('#D1Table').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("D1Table"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}
//贴现账户（D2）业务量比对结果表
function D2Table(){
	$('#D2Table').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("D2Table"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}
//循环额度下分账户（R4）业务量比对结果表
function R4Table(){
	$('#R4Table').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("R4Table"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}
//授信协议业务量比对结果表
function ClTable(){
	$('#ClTable').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("ClTable"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}
//融资担保账户（G1）业务量比对结果表
function G1Table(){
	$('#G1Table').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("G1Table"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}
//非融资担保账户（G2）业务量比对结果表
function G2Table(){
	$('#G2Table').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("G2Table"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}
//支付担保账户（G3）业务量比对结果表
function G3Table(){
	$('#G3Table').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("G3Table"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}
//抵（质）押合同业务量比对结果表
function DzyTable(){
	$('#DzyTable').datagrid({
		striped : true,
		url : path + '/DataInteger/listPage.html',
		remoteSort : false,
		queryParams : getQueryParam("DzyTable"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '数据项',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '机构实际业务量（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'TOTAL',
			title : '报文统计结果（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCTCODE',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
}

function getQueryParam(tableType) {
	var queryObject = new Object();
	queryObject.queryRptDate = $('#etlDate').datebox('getValue');
/*	queryObject.querySourceSys = $('#querySourceSys').combobox('getValue');
*/	queryObject.tableType = tableType;
	return queryObject;
}
//条件查询
function doQuery(){
	
	$('#DcTable').datagrid('load',getQueryParam("DcTable"));
	$('#GuTable').datagrid('load',getQueryParam("GuTable"));
	$('#BaseTable').datagrid('load',getQueryParam("BaseTable"));
	$('#ReportTable').datagrid('load',getQueryParam("ReportTable"));
	$('#D1Table').datagrid('load',getQueryParam("D1Table"));
	$('#D2Table').datagrid('load',getQueryParam("D2Table"));
	$('#R4Table').datagrid('load',getQueryParam("R4Table"));
	$('#ClTable').datagrid('load',getQueryParam("ClTable"));
	$('#G1Table').datagrid('load',getQueryParam("G1Table"));
	$('#G2Table').datagrid('load',getQueryParam("G2Table"));
	$('#G3Table').datagrid('load',getQueryParam("G3Table"));
	$('#DzyTable').datagrid('load',getQueryParam("DzyTable"));
	
}

//清除条件
function doClear(){
	$('#etlDate').datebox('clear');
	//$('#querySourceSys').combobox('clear');
}

//获取格式化日期 add by cc 2020-03-17
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}