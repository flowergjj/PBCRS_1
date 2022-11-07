var path = getAppPath();
$(function() {
	initCombox();
	detail();
});
function doCreate() {
	var queryRptDate =  $('#queryRptDate').datebox('getValue');
	if(queryRptDate != null && queryRptDate != undefined && queryRptDate != ''){
		window.open(path + '/compareEnds/create.html?queryRptDate='+$('#queryRptDate').datebox('getValue'));
	}else{
		$.messager.alert('错误', '请选择截止日期', 'error');
		return;
	}
	 
}
//接口层
function doCreateInter() {
	var dataType = $('#dataType').combobox('getValue');
	var queryRptDate =  $('#queryRptDate').datebox('getValue');
	if(queryRptDate != null && queryRptDate != undefined && queryRptDate != ''&&dataType != null && dataType != undefined && dataType != ''){
		window.open(path + '/compareEnds/createInter.html?queryRptDate='+$('#queryRptDate').datebox('getValue')+'&dataType='+dataType);
	}else{
		$.messager.alert('错误', '请选择截止日期和数据来源', 'error');
		return;
	}
	

	 
}

//增加比对数据
function add() {
	var queryRptDate =  $('#queryRptDate').datebox('getValue');
	if(queryRptDate != null && queryRptDate != undefined && queryRptDate != ''){
		MaskUtil.mask('执行中，请稍候……');
		$.ajax({
			url : path + '/compareEnds/add.html',
			type : "post",
			data : {
				queryRptDate : queryRptDate
			},
			success : function(data) {
				if ('SUCCESS' == data.RET_CODE) {
					$.messager.alert('成功', '添加成功!', 'info');
					MaskUtil.unmask();
					//doQuery();
				} else {
					MaskUtil.unmask();
					$.messager.alert('错误', data.RET_MSG, 'error');
				}
			}
		});
	}else{
		$.messager.alert('错误', '请选择截止日期', 'error');
		return;
	}
		 
}

function initCombox(){
	initCombobox('data_type','dataType');
	initCombobox('field_type','fieldType');
}

function getentTable() {

	
	$('#DataCompareTable').datagrid({
		striped : true,
		url : path + '/compareEnds/listPage.html',
		remoteSort : false,
		//queryParams : getQueryParam(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'FIELD44',
			title : '数据日期',
			width : '33%',
			align : 'center'
		}, {
			field : 'FILENAME',
			title : '文件名',
			width : '33%',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '33%',
			align : 'center',
			formatter : function(value, row, index) {
				var url = row.COMPARENO.replace(/\\/g,
						"/");
				var str ='<a href="javascript:void(0)" onClick=detail(\"'
					+encodeURI(url)+ '\")>比对详情</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onClick=createGenFile(\"'
					+encodeURI(url)+ '\")>生成比对文件</a>';
				return str;
				
				
			
			}
		} ] ]
	});
	// 设置分页控件
	var p = $('#DataCompareTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 15,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
}

function detail(){
	$('#DataCompareTable').datagrid({
		striped : true,
		url : path + '/compareEnds/listDetailPage.html',
		remoteSort : false,
		queryParams : getQueryParam(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		//rownumbers:true,//行号
		columns : [ [ {
			field : 'DATA_TYPE',
			title : '数据来源',
			width : '5%',
			align : 'center',
			formatter : function(value, row, index) {
				var str='';
				if(row.DATA_TYPE=="report"){
					str="报送层"
				}else{
					str="集市层";
				}
				return str;
			}
		},{
			field : 'FIELD1',
			title : '总部机构代码',
			width : '8%',
			align : 'center'
		}, {
			field : 'FIELD2',
			title : '数据截止日期',
			width : '5%',
			align : 'center'
		}, {
			field : 'FIELD3',
			title : '报文类别',
			width : '5%',
			align : 'center',
			formatter : function(value, row, index) {
				var str='';
				if(row.FIELD3 == "1"){
					str="借贷账户";
				}else if(row.FIELD3 == "2"){
					str="担保账户";
				}else if(row.FIELD3 == "3"){
					str="授信协议";
				}else{
					str="抵质押合同";
				}
				return str;
			}
		}, {
			field : 'FIELD4',
			title : '标识码',
			width : '15%'
		}, {
			field : 'FIELD5',
			title : '名称',
			width : '10%'
		}, {
			field : 'FIELD6',
			title : '身份标识类型',
			width : '5%'
		}, {
			field : 'FIELD7',
			title : '身份标识号码',
			width : '10%',
			align : 'center'
		}, {
			field : 'FIELD8',
			title : '业务种类细分/额度循环标志/合同类型',
			width : '5%',
			align : 'center'
		}, {
			field : 'FIELD9',
			title : '开户日期/额度生效日期/抵质押生效日期',
			width : '8%',
			align : 'center'
		}, {
			field : 'FIELD10',
			title : '到期日期/额度到期日期/抵质押到期日期',
			width : '8%',
			align : 'center'
		}, {
			field : 'FIELD11',
			title : '借款金额/信用额度/担保金额/授信额度',
			width : '8%',
			align : 'center'
		}, {
			field : 'FIELD12',
			title : '担保方式',
			width : '5%',
			align : 'center'
		}, {
			field : 'FIELD13',
			title : '余额',
			width : '8%',
			align : 'center'
		}, {
			field : 'FIELD14',
			title : '五级分类',
			width : '5%',
			align : 'center'
		}, {
			field : 'FIELD15',
			title : '当前逾期总额',
			width : '8%',
			align : 'center'
		}, {
			field : 'FIELD16',
			title : '当前逾期天数',
			width : '5%',
			align : 'center'
		}, {
			field : 'FIELD17',
			title : '责任人个数',
			width : '5%',
			align : 'center',
			formatter : function(value, row, index) {
				var str='0';
				if(row.ARLPNAME != undefined && row.ARLPNAME != null){
					str = row.ARLPNAME.split(",").length;
				}
				return str;
			}
		},  {
			field : 'ARLPNAME',
			title : '责任人名称',
			width : '10%',
			align : 'center'
		}, {
			field : 'ARLPCERTTYPE',
			title : '责任人证件类型',
			width : '10%',
			align : 'center'
		}, {
			field : 'ARLPCERTNUM',
			title : '责任人证件号码',
			width : '10%',
			align : 'center'
		}, {
			field : 'ARLPAMT',
			title : '还款责任金额',
			width : '10%',
			align : 'center'
		} ] ]
	});
	// 设置分页控件
	var p = $('#DataCompareTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 15,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
}

function getQueryParam(type) {
	var queryObject = new Object();	
	if(type != null && type != undefined && type != ''){
		queryObject.type = type;
	}
	queryObject.queryRptDate = $('#queryRptDate').datebox('getValue');
	queryObject.dataType = $('#dataType').combobox('getValue');
	queryObject.fieldType = $('#fieldType').combobox('getValue');
	queryObject.fieldCode = $('#fieldCode').val();
	return queryObject;
}
//清除条件
function doClear(){
	$('#queryRptDate').datebox('clear');
	$('#dataType').combobox('clear');
	$('#fieldType').combobox('clear');
	$('#fieldCode').textbox('clear');
}

function doQuery(type){
	if(type != null && type != undefined && type != ''){
		var queryRptDate =  $('#queryRptDate').datebox('getValue');
		if(queryRptDate == null || queryRptDate == undefined || queryRptDate == ''){
			$.messager.alert('错误', '请选择截止日期', 'error');
			return;
		}
	}
	$('#DataCompareTable').datagrid('load',getQueryParam(type));
	
}

function createGenFile(compareNo){
	window.open(path + '/compareEnds/createByCompareNo.html?compareNo='+compareNo);
}

function upd(tableKey,fieldId,field){
	//console.info(tableKey+","+fieldId+","+field);
	var fieldValue = $("#"+fieldId+"").val();
	//console.info(fieldValue);
	$.ajax({
		url : path + '/compareEnds/upd.html',
		type : "post",
		data : {
			fieldValue : fieldValue.trim(),
			field : field,
			tableKey : tableKey
		},
		success : function(data) {
			if ('SUCCESS' == data.RET_CODE) {
				$.messager.alert('成功', '修改成功!', 'info');
				MaskUtil.unmask();
				doQuery();
			} else {
				MaskUtil.unmask();
				$.messager.alert('错误', data.RET_MSG, 'error');
			}
		}
	});
}