var path = getAppPath();
var lazy = false;
var idIndex = 0;
var filetype = '.xls,.xlsx';
var refCode = "";
$(function() {

	initFirstTable();
	initSecondTable();
	initThirdTable();
});


function initFirstTable() {
	$('#customerReport').datagrid({
		striped : true,
		url : path + '/analysistest/getCustomerReportInfo.html',
		remoteSort : false,
		idField : 'VERSION',
		 queryParams : getQueryParam(), 
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'TYPE',
			title : '数据项',
			width : '33.3%',
			align : 'center'

		}, {
			field : 'TOTAL',
			title : '报送涉及客户数',
			width : '33.3%',
			align : 'center'
		}, {
			field : 'ZB',
			title : '占报送总客户数比率',
			width : '33.3%',
			align : 'center'
		} ] ],
		rowStyler : function(index, row) {},
		onLoadSuccess : function(data) {}

	});

}

function initSecondTable() {
	$('#baseInfoAnalysis').datagrid({
		striped : true,
		url : path + '/analysistest/getBaseInfoAnalysisInfo.html',
		remoteSort : false,
		idField : 'VERSION',
		 queryParams : getQueryParam(), 
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'QYGM',
			title : '数据项',
			width : '25%',
			align : 'center'

		}, {
			field : 'SYS_CODE_DESC',
			title : '类型',
			width : '25%',
			align : 'center',
			colspan: 1
		   
		}, {
			field : 'TOTAL',
			title : '报送涉及客户数',
			width : '25%',
			align : 'center'
		}, {
			field : 'BL',
			title : '占报送总客户数比率',
			width : '25%',
			align : 'center'
		}] ],
		rowStyler : function(index, row) {},
		onLoadSuccess : function(data) {
			var startIndex=0;
			var columnCount = 0;
			var startV="";
		    var endV="";
			$.each(data.rows,function(k,v){
				columnCount=columnCount+1;
				
				startV = v.QYGM;
				if(endV!=""){
					if(startV!=endV){
						$('#baseInfoAnalysis').datagrid('mergeCells', {
							index : startIndex,
							field : 'QYGM',
							rowspan : columnCount-1
						});
						columnCount=1;
						startIndex=k;
					}
				}
				endV =startV ;
				
			});
			
		}

	});

}

function initThirdTable() {
	$('#reportFromInfo').datagrid({
		striped : true,
		url : path + '/analysistest/getReportFromInfo.html',
		remoteSort : false,
		idField : 'VERSION',
		 queryParams : getQueryParam(), 
		singleSelect : true,// 是否单选
		columns : [ [ {
			field : 'QYZCFZ',
			title : '财务报表类型',
			width : '33.3%',
			align : 'center'

		}, {
			field : 'COUNT(1)',
			title : '报送涉及客户数',
			width : '33.3%',
			align : 'center'
		}, {
			field : 'ZB',
			title : '占报送总客户数比率',
			width : '10%',
			align : 'center'
		}] ],
		rowStyler : function(index, row) {},
		onLoadSuccess : function(data) {}

	});

}




function getQueryParam() {
	var queryObject = new Object(); 
	queryObject.rptDate = $('#rptDate').datebox('getValue');
	return queryObject;
}

//对datagrid进行刷新
function doQuery() {

	$('#customerReport').datagrid('load', getQueryParam());
	$('#baseInfoAnalysis').datagrid('load', getQueryParam());
	$('#reportFromInfo').datagrid('load', getQueryParam());
}

//清除筛选条件
function doClear() {
	$('#rptDate').datebox('clear');
}