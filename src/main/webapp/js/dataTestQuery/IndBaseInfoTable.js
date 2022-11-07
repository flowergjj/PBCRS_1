var path = getAppPath();
var lazy = false;
var idIndex = 0;
var filetype = '.xls,.xlsx';
var refCode = "";
$(function() {
	indBaseInfo();
});


function indBaseInfo() {
	$('#indBaseInfo').datagrid({
		striped : true,
		url : path + '/indBaseInfoTable/getList.html',
		remoteSort : false,
		idField : 'VERSION',
		 queryParams : getQueryParam(), 
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'FIELDNAME',
			title : '数据项名称',
			width : '25%',
			align : 'center'

		}, {
			field : 'CX',
			title : '出现约束',
			width : '25%',
			align : 'center'
		},
		{
			field : 'KZ',
			title : '空值约束',
			width : '25%',
			align : 'center'
		},
		
		{
			field : 'BL',
			title : '填报率',
			width : '25%',
			align : 'center'
		} ] ],
		rowStyler : function(index, row) {},
		onLoadSuccess : function(data) {}

	});

}





function getQueryParam() {
	var queryObject = new Object(); 
	queryObject.queryDate = $('#queryDate').datebox('getValue');
	return queryObject;
}

//对datagrid进行刷新
function doQuery() {
	
	$('#indBaseInfo').datagrid('load', getQueryParam());
}

//清除筛选条件
function doClear() {
	$('#queryDate').datebox('clear');
}