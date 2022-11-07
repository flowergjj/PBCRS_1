var enReport;
$(function() {
	
	$('body').css('visibility', 'visible');
	showDetial('d1');
	//setinitCombobox();
	//initDialog();

});

function hideDetial(){
	$('#d1').hide();
	$('#d2').hide();
}

function showDetial(id){
	hideDetial();
	$('#'+id).show();
	
	if(id=="d1"){
		var type = "'310','412','422','432','442','610','620','630','640','650','411','421','441','511'";
		getRptType(type);
		loadEnReportModTable();
		
	}
	if(id=="d2"){
		var type = "'110','212','222','510','211','221','511'";
		getInRptType(type);
		loadInReportModTable();
		
	}

}
var toolbar = [
			{
				text:"选择报送",
				iconCls: "icon-task",
				"class": "tooltip-info",
				handler:function(){
					report("EnReportModTable");
				}
			}

		];
//个人框
var toolbar1 = [
   			{
   				text:"选择报送",
   				iconCls: "icon-task",
   				"class": "tooltip-info",
   				handler:function(){
   					report("MnMmbInfTable");
   				}
   			}   			
   		];
//企业修改待上报信息
function loadEnReportModTable(){
	
	$('#EnReportModTable').datagrid({
		striped : true,
		url : path + '/reportMod/listPageUpdSub.html',
		remoteSort : false,
		//idField : 'REPORTMODID',
		queryParams : getQueryParam(),
		singleSelect : false,// 是否单选
		toolbar:toolbar,
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		//rownumbers:true,//行号
		columns : [ [ {
			field : 'REPORTMODID',
			width : '15%',
			checkbox:true
		},  {
			field : 'ENTNAME',
			title : '企业名称',
			width : '20%',
			align : 'center'
		},{
			field : 'ENTCERTTYPE',
			title : '企业身份标识类型',
			width : '15%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='10') return '中征码（原贷款卡编）';
				if( value=='20') return '统一社会信用代码 ';
				if( value=='30') return '组织机构代码 ';
				if( value=='01') return '工商注册号 ';
				if( value=='02') return '事业单位证书号 ';
				if( value=='03') return '社会团体登记号 ';
				if( value=='04') return '民办非企业登记号 ';
				if( value=='05') return '基金会登记号 ';
				if( value=='06') return '宗教证书登记号 ';
				if( value=='07') return '律师事务所执业许可证号 ';
				if( value=='08') return '司法鉴定许可证号 ';
				if( value=='41') return '纳税人识别号（国税） ';
				if( value=='42') return '纳税人识别号（地税） ';

			}
		}, {
			field : 'ENTCERTNUM',
			title : '企业身份标识号码',
			width : '15%',
			align : 'center'
		},{
			field : 'INFRECTYPE',
			title : '报文类型',
			width : '20%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='310') return '企业基本信息记录';
				if( value=='410') return '企业借贷账户记录 ';
				if( value=='420') return '企业授信协议信息记录 ';
				if( value=='440') return '企业担保账户信息记录 ';
				if( value=='610') return '企业资产负债表信息记录 ';
				if( value=='620') return '企业利润及利润分配表信息记录 ';
				if( value=='630') return '企业现金流量表信息记录 ';
				if( value=='640') return '事业单位资产负债表信息记录 ';
				if( value=='650') return '事业单位收入支出表信息记录 ';
				if( value=='422') return '企业授信协议更正请求记录 ';
				if( value=='412') return '企业借贷账户更正请求记录 ';
				if( value=='442') return '企业担保账户更正请求记录 ';
				if( value=='510') return '抵(质)押合同信息记录';
				if( value=='511') return '抵(质)押合同标识变更请求记录';
				if( value=='411') return '企业借贷账户标识变更请求记录';
				if( value=='421') return '企业授信协议标识变更请求记录';
				if( value=='441') return '企业担保账户标识变更请求记录';
			}
		}, {
			field : 'RPTDATE',
			title : '报送时间',
			width : '10%',
			align : 'center'
		}
		,{
			field : 'ssss',
			title : '校验反馈信息',
			width : '20%',
			align : 'center',
			formatter : function(value, row, index) {
				var res1="";
				$.ajax({
					url :  path + '/reportMod/checkMsg.html',
					type : "post",
					data : {
						seqno : row.REPORTMODID
					},
					async : false,
					success : function(res) {
						if(res.RET_CODE=="SUCCESS"){
							res1=res;
							/*return '<a href="javascript:void(0)" onClick=checkMsg(\"'
							+res.condtionId+'\")>'+res.badName+'</a>';*/
						}else{
							
						}
					}
				});
				if(res1!=""){
					return '<a href="javascript:void(0)" onClick=checkMsg(\"'
					+res1.condtionId+'\")>'+res1.badName+'</a>';
				}
				return ;
			}
		}
/*		, {
			field : 'REPORTSTAUT',
			title : '报送状态',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='0') return '未处理';
				if( value=='1') return '已处理 ';
				if( value=='2') return '处理完成 ';
			}
		}*/
		] ],
		onDblClickRow : function(index, row) {
			// alert(index);
			// alert(row.USERID);
			//openUpdateDialog(row.userid);
		}
	});
	enReport = EnReportModTable;
	// 设置分页控件
	var p = $('#EnReportModTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 15,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	/*
	 * onBeforeRefresh:function(){ $(this).pagination('loading'); alert('before
	 * refresh'); $(this).pagination('loaded'); }
	 */
	});
	$('#EnReportModTable').datagrid('clearChecked');
}
function report(tableName){
	var set = new Set();
	var custArr;
	custArr=getSelectRows(tableName);
	if(custArr == false){
		return;
	}
	
	var reportId="";
	var id="";
	var etl_date="";
	
	for (var int = 0; int < custArr.length; int++) {
		reportId=custArr[int].INFRECTYPE;
		if(id==""){
			id=id+"'"+custArr[int].REPORTMODID+"'";
		}else{
			id=id+",'"+custArr[int].REPORTMODID+"'";
		}
		etl_date=custArr[int].RPTDATE;
		set.add(reportId);
		set.add(etl_date);
	}
	if(set.size !=2 ){
		$.messager.alert('错误', '请选择同一种报文类型和同一报送时间!', 'error');
		return ;
	}
	var SYSID = "";
	if(reportId == "511"){		
		if(tableName == "EnReportModTable"){
			SYSID = "ENT";
		}else if(tableName == "MnMmbInfTable"){
			SYSID = "IND";
		}
	}
	var url =path+"/reportMod/report.html";
	$.ajax({
		url : url,
		type : "post",
		data : {
			reportId : reportId,
			id		 :	id,
			etl_date  :etl_date,
			type:'updSub',//修改并提交
			SYSID:SYSID
			
		},
		async : false,
		success : function(res) {
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功请等待校验，校验成功后请进入报送文件下载查看校验信息及报送文件!', 'info');
			
			}else{
			
				if(res.ERR_MSG!=null){
					$.messager.alert('错误', res.ERR_MSG, 'error');
				}else{
					$.messager.alert('错误', '提交失败!', 'error');
				}
				
			
			}
			
		}
	});
}


//获取选中的记录集合
function getSelectRows(tableName){
	var rows = $('#'+tableName).datagrid('getChecked');
	if(rows.length == 0){
		$.messager.alert('提示','你还没有选择任何记录!');
		return false;
	}

/*	else{
		for(var i=0;i<rows.length;i++){
			if(rows[i].REPORTSTAUT=='1' ||rows[i].REPORTSTAUT=='2'){
				$.messager.alert('提示','请选择未处理的记录!');
				return false;
			}
		}
	}*/

	//EnReportModTable
	var rowSData = $('#'+tableName).datagrid('getSelections');

	return  rowSData;
}




//个人修改待上报 ，报文
function loadInReportModTable(){
	
	$('#MnMmbInfTable').datagrid({
		striped : true,
		url : path + '/reportMod/updSublistPage.html',
		remoteSort : false,
		idField : 'REPORTMODID',
		//queryParams : getQueryParamMnMmb(),
		toolbar:toolbar1,
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [
		             {
			field : 'REPORTMODID',
			width : '15%',
			checkbox:true
		}, {
			field : 'NAME',
			title : '姓名',
			width : '15%',
			align : 'center'
		}, {
			field : 'IDTYPE',
			title : '证件类型',
			width : '20%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='1') return '户口薄';
				if( value=='2') return '护照';
				if( value=='5') return '港澳居民来往内地通行证';
				if( value=='6') return '台湾同胞来往内地通行证';
				if( value=='8') return '外国人居留证';
				if( value=='9') return '警官证';
				if( value=='A') return '香港身份证';
				if( value=='B') return '澳门身份证';
				if( value=='C') return '台湾身份证';
				if( value=='X') return '其他证件';
				if( value=='10') return '居民身份证及其他以公民身份证号为标识的证件';
				if( value=='20') return '军人身份证件';


			}
		}, {
			field : 'IDNUM',
			title : '证件号码',
			width : '15%',
			align : 'center'
		}, {
			field : 'INFRECTYPE',
			title : '报文类型',
			width : '20%',
			align : 'center',
			formatter : function(value, row, index) {
					if( value=='110') return '个人基本信息记录';
					if( value=='212') return '个人借贷账户按段更正请求记录 ';
					if( value=='222') return '个人授信协议按段更正请求记录 ';
					if( value=='510') return '抵(质)押合同信息记录';
					if( value=='211') return '个人借贷账户标识变更请求记录';
					if( value=='221') return '个人授信协议标识变更请求记录';
					if( value=='511') return '抵(质)押合同标识变更请求记录';
					
				}
			
		},{
			field : 'RPTDATE',
			title : '报送时间',
			width : '10%',
			align : 'center'
		}
		,{
			field : 'ssss',
			title : '校验反馈信息',
			width : '20%',
			align : 'center',
			formatter : function(value, row, index) {
				var res1="";
				$.ajax({
					url :  path + '/reportMod/checkMsg.html',
					type : "post",
					data : {
						seqno : row.REPORTMODID
					},
					async : false,
					success : function(res) {
						if(res.RET_CODE=="SUCCESS"){
							res1=res;
							/*return '<a href="javascript:void(0)" onClick=checkMsg(\"'
							+res.condtionId+'\")>'+res.badName+'</a>';*/
						}else{
							
						}
					}
				});
				if(res1!=""){
					return '<a href="javascript:void(0)" onClick=checkMsg(\"'
					+res1.condtionId+'\")>'+res1.badName+'</a>';
				}
				return ;
			}
		}] ],
		onDblClickRow : function(index, row) {
			// alert(index);
			// alert(row.USERID);
			//openUpdateDialog(row.userid);
		}
	});
	// 设置分页控件
	var p = $('#MnMmbInfTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 15,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	/*
	 * onBeforeRefresh:function(){ $(this).pagination('loading'); alert('before
	 * refresh'); $(this).pagination('loaded'); }
	 */
	});
	$('#MnMmbInfTable').datagrid('clearChecked');
}

//条件查询
function doQuery(){
	
	$('#EnReportModTable').datagrid('load',getQueryParam());
	
}

function getQueryParam() {
	var queryObject = new Object();
//	var enbasinfseqno=getQueryString('id');
//	if(enbasinfseqno){
//		queryObject.ENBASINFSEQNO=enbasinfseqno;
//	}
	queryObject.queryRptDate = $('#queryRptDate').datebox('getValue');
	queryObject.queryEntName = $('#queryEntName').val();
	queryObject.queryEntCertNum = $('#queryEntCertNum').val();
/*	queryObject.reportStaut = $('#reportStaut').combobox('getValue');
*///	queryObject.queryOrg = $('#queryOrg').combobox('getValue');
	queryObject.queryRptType =$('#queryRptType').combobox('getValue');

	return queryObject;
}

//清除条件
function doClear(){
	$('#queryRptDate').datebox('clear');
	$('#queryEntName').textbox('clear');
	$('#queryEntCertNum').textbox('clear');
/*	$('#reportStaut').combobox('clear');
*/	$('#queryRptType').combobox('clear');
}

//查看反馈信息
function checkMsg(conditionId){
	//文件下载的jsp
	openSubTab(path + '/jsp/report/sendFile/SendFile.jsp?id=' +conditionId, '报送文件下载');
}

function doQueryIn(){
	
	$('#MnMmbInfTable').datagrid('load',getQueryParamIn());
	
}
function getQueryParamIn() {
	var queryObject = new Object();
	queryObject.queryRptDate = $('#queryInRptDate').datebox('getValue');
	queryObject.queryIDName = $('#queryInIDName').val();
	queryObject.queryIDNum = $('#queryInIDNum').val();
	queryObject.queryRptType =$('#queryInRptType').combobox('getValue');
	return queryObject;
}

function doClearIn(){
	$('#queryInRptDate').datebox('clear');
	$('#queryInIDName').textbox('clear');
	$('#queryInIDNum').textbox('clear');
	$('#queryInRptType').combobox('clear');

}

