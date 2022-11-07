/**
 * 数据报送完整性
 */
var flag = false;
$(function(){
	/*infoManager();
	infoBySourceManager();
	indBaseAddCheck();
	d1CheckResult();
	r4CheckResult();
	indCtrCheckResult();
	modCheckResult();
	infoJoinCheckResult();*/
});

//客户资料统一管理
function infoManager(){
	
	$('#infoManager').datagrid({
		striped : true,
		url : path + '/DataInteger/infoManager.html',
		remoteSort : false,
		//async:false,
		queryParams : getQueryParam("infoManager"),
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
			field : 'KEEP',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
				formatter : function(value, row, index) {
					return '100%';
				}
		} ] ]
	});
	
}
//客户资料分系统管理
function infoBySourceManager(){
	
	//个贷系统
	$('#PLN_infoManager').datagrid({
		striped : true,
		url : path + '/DataInteger/PLNinfoManager.html',
		remoteSort : false,
		//async:false,
		queryParams : getQueryParam("PLN_infoManager"),
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
			field : 'KEEP',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
	//网贷系统
	$('#ILN_infoManager').datagrid({
		striped : true,
		url : path + '/DataInteger/ILNinfoManager.html',
		remoteSort : false,
		//async:false,
		queryParams : getQueryParam("ILN_infoManager"),
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
			field : 'KEEP',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
	//小微系统
	$('#SLN_infoManager').datagrid({
		striped : true,
		url : path + '/DataInteger/SLNinfoManager.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("SLN_infoManager"),
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
			field : 'KEEP',
			title : '一致率（2/1）',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				return '100%';
			}
		} ] ]
	});
	
}

//个人基本信息记录增量数据完整性比对
function indBaseAddCheck(){
	
	$('#indBaseAddCheck').datagrid({
		striped : true,
		url : path + '/DataInteger/indBaseAddCheck.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("indBaseAddCheck"),
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
function d1CheckResult(){
	//D1账户第一个月
	$('#d1FirstCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/d1FirstCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("d1FirstCheckResult"),
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
	//D1账户第二个月
	$('#d1SecondCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/d1SecondCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("d1SecondCheckResult"),
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
	//D1账户第三个月
	$('#d1ThirdCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/d1ThirdCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("d1ThirdCheckResult"),
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
//循环额度下分账户（R4）业务量比对结果
function r4CheckResult(){
	//R4账户第一个月
	$('#r4FirstCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/r4FirstCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("r4FirstCheckResult"),
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
	//R4账户第二个月
	$('#r4SecondCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/r4SecondCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("r4SecondCheckResult"),
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
	//R4账户第三个月
	$('#r4ThirdCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/r4ThirdCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("r4ThirdCheckResult"),
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
//个人授信协议业务量比对结果
function indCtrCheckResult(){
	$('#indCtrCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/indCtrCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("indCtrCheckResult"),
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
//抵（质）押物信息业务量比对结果
function modCheckResult(){
	$('#modCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/modCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("modCheckResult"),
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
//信息关联性比对结果
function infoJoinCheckResult(){
	$('#baseInfoJoinCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/baseInfoJoinCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("baseInfoJoinCheckResult"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '信息类别',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCCOUNT',
			title : '个人借贷账户信息记录基础段主体标识数（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'BSCOUNT',
			title : '个人基本信息基础段主体标识数（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'KEEP',
			title : '两端一致率（2/1）',
			width : '25%',
			align : 'center'
		} ] ]
	});
	
	$('#ctrInfoJoinCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/ctrInfoJoinCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("ctrInfoJoinCheckResult"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '信息类别',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCCOUNT',
			title : '个人借贷账户信息记录授信额度信息段授信协议标识码个数（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'CLCOUNT',
			title : '个人授信协议信息记录基础段授信协议标识码个数（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'KEEP',
			title : '两端一致率（2/1）',
			width : '25%',
			align : 'center'
		} ] ]
	});
	
	$('#modInfoJoinCheckResult').datagrid({
		striped : true,
		url : path + '/DataInteger/modInfoJoinCheckResult.html',
		remoteSort : false,
		async:false,
		queryParams : getQueryParam("modInfoJoinCheckResult"),
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPENAME',
			title : '信息类别',
			width : '25%',
			align : 'center'
		}, {
			field : 'ACCCOUNT',
			title : '个人借贷账户抵质押物信息段抵质押合同标识码个数（1）',
			width : '25%',
			align : 'center'
		}, {
			field : 'DZYCOUNT',
			title : '抵质押合同信息记录基础段抵质押合同标识码个数（2）',
			width : '25%',
			align : 'center'
		}, {
			field : 'KEEP',
			title : '两端一致率（2/1）',
			width : '25%',
			align : 'center'
		} ] ]
	});
}


function getQueryParam(tableType) {
	var queryObject = new Object();
	queryObject.queryRptDate = $('#etlDate').datebox('getValue');
/*	queryObject.querySourceSys = $('#querySourceSys').combobox('getValue');
*///	queryObject.tableType = tableType;
	return queryObject;
}
//条件查询
function doQuery(){
	if(!flag){
		infoManager();
		infoBySourceManager();
		indBaseAddCheck();
		d1CheckResult();
		r4CheckResult();
		indCtrCheckResult();
		modCheckResult();
		infoJoinCheckResult();
		flag =true;
	}else{
		$('#infoManager').datagrid('load',getQueryParam("infoManager"));
		$('#PLN_infoManager').datagrid('load',getQueryParam("PLN_infoManager"));
		$('#ILN_infoManager').datagrid('load',getQueryParam("ILN_infoManager"));
		$('#SLN_infoManager').datagrid('load',getQueryParam("SLN_infoManager"));
		$('#indBaseAddCheck').datagrid('load',getQueryParam("indBaseAddCheck"));
		$('#d1FirstCheckResult').datagrid('load',getQueryParam("d1FirstCheckResult"));
		$('#d1SecondCheckResult').datagrid('load',getQueryParam("d1SecondCheckResult"));
		$('#d1ThirdCheckResult').datagrid('load',getQueryParam("d1ThirdCheckResult"));
		$('#r4FirstCheckResult').datagrid('load',getQueryParam("r4FirstCheckResult"));
		$('#r4SecondCheckResult').datagrid('load',getQueryParam("r4SecondCheckResult"));
		$('#r4ThirdCheckResult').datagrid('load',getQueryParam("r4ThirdCheckResult"));
		$('#indCtrCheckResult').datagrid('load',getQueryParam("indCtrCheckResult"));
		$('#modCheckResult').datagrid('load',getQueryParam("modCheckResult"));
		$('#baseInfoJoinCheckResult').datagrid('load',getQueryParam("baseInfoJoinCheckResult"));
		$('#ctrInfoJoinCheckResult').datagrid('load',getQueryParam("ctrInfoJoinCheckResult"));
		$('#modInfoJoinCheckResult').datagrid('load',getQueryParam("modInfoJoinCheckResult"));
	}
	
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