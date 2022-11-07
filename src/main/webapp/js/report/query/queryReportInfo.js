$(function() {
	
	//初始化table信息
	initTable();
	

});

function getQueryParam(){
	var queryObject = new Object();
	queryObject.rptDate = $('#rptDate').datebox("getValue")
	queryObject.name = $('#name').textbox("getValue")
	queryObject.idNum = $('#idNum').textbox("getValue")
	queryObject.custCode = $('#custCode').textbox("getValue")
	queryObject.encName = $('#encName').textbox("getValue")
	return queryObject;
}


function doQuery(){
	$('#DataListTable').datagrid('load',getQueryParam());
}

function doClear(){
	$('#rptDate').datebox("clear")
	$('#name').textbox("clear")
	$('#idNum').textbox("clear")
	$('#custCode').textbox("clear")
	$('#encName').textbox("clear")
}
//企业修改待上报信息
function initTable(){
	
	$('#DataListTable').datagrid({
		striped : true,
		url : path + '/sendFile/query.html',
		remoteSort : false,
		queryParams : getQueryParam(),
		singleSelect : false,// 是否单选
		//toolbar:toolbar,
		pagination : true,// 分页控件
		pageSize : 20,
		pageList : [ 20 ],
		columns : [ [ {
			field : 'RPTDATE',
			title : '报文日期',
			width : '7%',
			align : 'center'
			
		},{
			field : 'REPORTCODE',
			title : '报文类型',
			width : '10%',
			align : 'center'
			
		},
		{
			field : 'NAME',
			title : '客户名称',
			width : '14%',
			align : 'center'
			
		},{
			field : 'IDNUM',
			title : '证件号码',
			width : '15%',
			align : 'center'
			
		},{
			field : 'CUSTCODE',
			title : '借贷账户/抵(质)押合同 标识码',
			width : '25%',
			align : 'center'
			
		},{
			field : 'ENCFILENAME',
			title : '加密文件名称',
			width : '20%',
			align : 'center'
		},
		 
		 {
			field : 'CREATEDATE',
			title : '生成日期',
			width : '7%',
			align : 'center'
			
		},
		 {
			field : 'ORDERSEQNO',
			title : '文件行号',
			width : '3%',
			align : 'center'
			
		}
	
		] ],
		onDblClickRow : function(index, row) {
			
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
	$('#DataListTable').datagrid('clearChecked');
}