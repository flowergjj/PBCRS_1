var path = getAppPath();
$(function(){
	//初始化数据列表
	initTable();
});

function initTable(){

	$('#DataListTable').datagrid({
		striped : true,
		url : path + '/InSecAcctInf/getDataList.html',
		remoteSort : false,
		idField : 'GUARACCTBSSGMTSEQNO',
		queryParams : queryParameter(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize:0,
		pageSize : 20,
		pageList : [ 20 ],
		columns : [ [ {
			field : 'RPTDATE',
			title : '信息报告日期',
			width : '11%',
			align : 'center'
		}, {
			field : 'NAME',
			title : '债务人姓名',
			width : '11%',
			align : 'center'
		}, {
			field : 'IDTYPE',
			title : '债务人证件类型',
			width : '11%',
			align : 'center'
		}, {
			field : 'IDNUM',
			title : '债务人证件号码',
			width : '11%',
			align : 'center'
		}, {
			field : 'ACCTCREDLINE',
			title : '担保金额',
			width : '11%',
			align : 'center'
		}, 
		{
			field : 'DUEDATE',
			title : '到期日期',
			width : '11%',
			align : 'center'
		},{
			field : 'BUSILINES',
			title : '担保业务大类',
			width : '11%',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '11%',
			align : 'center',
			formatter : function(value, row, index) {
				var id =row.GUARACCTBSSGMTSEQNO;
				var str ='<a href="javascript:void(0)" onClick=modify(\"'
					+id+'\")>修改</a>';
			
					return str;
				
			}
		} ] ],
		
		onDblClickRow : function(index, row) {
			var id =row.INSECACCTINFSEQNO;
			modify(id,"2");
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
//跳转到修改页面
function modify(index){
	openSubTab(path+'/jsp/nat/modify/InSecAcctInfDetail.jsp?id='+index,'个人担保信息修改详细');
	
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
	parameter.queryDueDate=$('#queryDueDate').datebox('getValue');
	parameter.queryBusiLines=$('#queryBusiLines').combobox('getValue');
	return parameter;
}
//清除条件
function clearQuery(){
	$('#queryRptDate').datebox('clear');
	$('#queryName').textbox('clear');
	$('#queryIDNum').textbox('clear');
	$('#queryAcctCredLine').textbox('clear');
	$('#queryDueDate').datebox('clear');
	$('#queryBusiLines').combobox('clear');
}

