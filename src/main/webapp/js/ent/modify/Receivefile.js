var path = getAppPath();

var lazy = false;
$(function() {
	
	$('body').css('visibility', 'visible');
	//getOrgData();
	getentTable();
});

function doMore(){
	$('#queryMore').show();
}

function getOrgData() {
	$.ajax({
		url : path + '/userManage/getOrgList.html',
		type : "post",
		async : false,
		success : function(data) {
			// alert(data.query.length);
			$('#queryOrg').combobox({
				// url:path+'/orgManage/orgList.html',
				data : data.query,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});
		}		
	});
}

function getentTable() {

	
	$('#ReceivefileTable').datagrid({
		striped : true,
		url : path + '/Receivefile/listPageEn.html',
		remoteSort : false,
		idField : 'RECEIVEFILENAME',
		queryParams : getQueryParam(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'CREATEDATE',
			title : '创建日期',
			width : '14%',
			hidden:'true',
			align : 'center'
		}, {
			field : 'IMPORTDATE',
			title : '导入日期',
			width : '14%',
			align : 'center'
		}, {
			field : 'RECEIVEFILENAME',
			title : '反馈文件名称',
			width : '16%',
			align : 'center'
		}, {
			field : 'FBCODE',
			title : '错误代码',
			width : '14%',
			align : 'center'
		}, {
			field : 'FBMSG',
			title : '错误栏位',
			width : '14%',
			align : 'center'
		}, {
			field : 'BUSSTYPE',
			title : '报文类别',
			width : '14%',
			align : 'center',
			formatter : function(value, row, index) {
				return getTabName(row.BUSSTYPE).trim();

			}
		}/*, {
			field : 'SEQNO',
			title : '状态',
			width : '100',
			align : 'center',
			formatter : function(value, row, index) {
				if(value){
					return '已修改';
				}else{
					return '未修改';
				}

			}
		}, {
			field : 'MODDATE',
			title : '修改时间',
			width : '100',
			align : 'center'
		}*/, {
			field : 'REPORTDATE',
			title : '报送时间',
			width : '14%',
			align : 'center'
		}, {
			field : 'status',
			title : '操作',
			width : '14%',
			align : 'center',
			formatter : function(value, row, index) {
				
				var num=row.REPORTSEQNO;
				var str ='<a href="javascript:void(0)" onClick=modify(\"'
					+row.BUSSTYPE+ '\","'+num+'",\"'+row.FBMSG+'\")>修改</a>';
				
				
				return str;
			
			}
		}
		  ] ],
		onDblClickRow : function(index, row) {
		
		}
	});
	// 设置分页控件
	var p = $('#ReceivefileTable').datagrid('getPager');
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
}

//条件查询
function doQuery(){
	
	$('#ReceivefileTable').datagrid('load',getQueryParam());
	
}

function getUrl(bussType){
	if(bussType=='110') return '个人基本信息记录                ';
	if(bussType=='114') return '个人基本信息删除请求记录        ';
	if(bussType=='120') return '家族关系信息记录                ';
	if(bussType=='130') return '个人证件有效期信息记录          ';
	if(bussType=='134') return '个人证件有效期信息删除请求记录  ';
	if(bussType=='140') return '个人证件整合信息记录            ';

	if(bussType=='210') return '个人借贷账户记录             ';
	if(bussType=='211') return '个人借贷账户标识变更请求记录 ';
	if(bussType=='212') return '个人借贷账户按段更正请求记录 ';
	if(bussType=='213') return '个人借贷账户按段删除请求记录 ';
	if(bussType=='214') return '个人借贷账户整笔删除请求记录 ';
	if(bussType=='215') return '个人借贷账户特殊事件说明记录 ';
	if(bussType=='220') return '个人授信协议信息记录         ';
	if(bussType=='221') return '个人授信协议标识变更请求记录 ';
	if(bussType=='222') return '个人授信协议按段更正请求记录 ';
	if(bussType=='223') return '个人授信协议按段删除请求记录 ';
	if(bussType=='224') return '个人授信协议整笔删除请求记录 ';

	if(bussType=='230') return '个人担保账户信息记录         ';
	if(bussType=='231') return '个人担保账户标识变更请求记录 ';
	if(bussType=='232') return '个人担保账户更正请求记录     ';
	if(bussType=='233') return '个人担保账户按段删除请求记录 ';
	if(bussType=='234') return '个人担保账户整笔删除请求记录 ';

	if(bussType=='510') return '抵(质)押合同信息记录         ';
	if(bussType=='511') return '抵(质)押合同标识变更请求记录 ';
	if(bussType=='514') return '抵(质)押合同整笔删除请求记录 ';

	if(bussType=='310') return '../jsp/ent/modify/EnBasInfDetial.jsp';
	if(bussType=='314') return ' 企业基本信息删除请求记录';
	if(bussType=='340') return ' 企业身份标识整合信息记录';
	if(bussType=='350') return ' 企业间关联关系信息记录  ';

	if(bussType=='410') return '../jsp/ent/modify/EnAcctInfDetial.jsp';
	if(bussType=='411') return ' 企业借贷账户标识变更请求记录       ';
	if(bussType=='412') return ' 企业借贷账户更正请求记录           ';
	if(bussType=='413') return ' 企业借贷账户按段删除请求记录       ';
	if(bussType=='414') return ' 企业借贷账户整笔删除请求记录       ';
	if(bussType=='420') return '../jsp/ent/modify/EnCtrctInfDetial.jsp';
	if(bussType=='421') return ' 企业授信协议标识变更请求记录       ';
	if(bussType=='422') return ' 企业授信协议更正请求记录           ';
	if(bussType=='423') return ' 企业授信协议按段删除请求记录       ';
	if(bussType=='424') return ' 企业授信协议整笔删除请求记录       ';
	if(bussType=='430') return ' 企业最高额保证合同信息记录         ';
	if(bussType=='431') return ' 企业最高额保证合同标识变更请求记录 ';
	if(bussType=='432') return ' 企业最高额保证合同更正请求记录     ';
	if(bussType=='433') return ' 企业最高额保证合同按段删除请求记录 ';
	if(bussType=='434') return ' 企业最高额保证合同整笔删除请求记录 ';

	if(bussType=='440') return '../jsp/ent/modify/EnSecAcctInfDetial.jsp';
	if(bussType=='441') return '企业担保账户标识变更请求记录 ';
	if(bussType=='442') return '企业担保账户更正请求记录     ';
	if(bussType=='443') return '企业担保账户按段删除请求记录 ';
	if(bussType=='444') return ' 企业担保账户整笔删除请求记录';

	if(bussType=='610') return '../jsp/ent/modify/BalanceSheetDetial.jsp';
	if(bussType=='620') return '../jsp/ent/modify/IncomeSProfitApprDetial.jsp';
	if(bussType=='630') return '../jsp/ent/modify/CashFlowsDetial.jsp';
	if(bussType=='640') return '../jsp/ent/modify/InsBalSheDetial.jsp';
	if(bussType=='650') return '../jsp/ent/modify/IncAndExpStaDetial.jsp';
	if(bussType=='614') return ' 企业资产负债表整笔删除请求记录      ';
	if(bussType=='624') return ' 企业利润及利润分配表整笔删除请求记录';
	if(bussType=='634') return ' 企业现金流量表整笔删除请求记录      ';
	if(bussType=='644') return ' 事业单位资产负债表整笔删除请求记录  ';
	if(bussType=='654') return ' 事业单位收入支出表整笔删除请求记录  ';
}


function getTabName(bussType){
	if(bussType=='110') return '个人基本信息记录                ';
	if(bussType=='114') return '个人基本信息删除请求记录        ';
	if(bussType=='120') return '家族关系信息记录                ';
	if(bussType=='130') return '个人证件有效期信息记录          ';
	if(bussType=='134') return '个人证件有效期信息删除请求记录  ';
	if(bussType=='140') return '个人证件整合信息记录            ';

	if(bussType=='210') return '个人借贷账户记录             ';
	if(bussType=='211') return '个人借贷账户标识变更请求记录 ';
	if(bussType=='212') return '个人借贷账户按段更正请求记录 ';
	if(bussType=='213') return '个人借贷账户按段删除请求记录 ';
	if(bussType=='214') return '个人借贷账户整笔删除请求记录 ';
	if(bussType=='215') return '个人借贷账户特殊事件说明记录 ';
	if(bussType=='220') return '个人授信协议信息记录         ';
	if(bussType=='221') return '个人授信协议标识变更请求记录 ';
	if(bussType=='222') return '个人授信协议按段更正请求记录 ';
	if(bussType=='223') return '个人授信协议按段删除请求记录 ';
	if(bussType=='224') return '个人授信协议整笔删除请求记录 ';

	if(bussType=='230') return '个人担保账户信息记录         ';
	if(bussType=='231') return '个人担保账户标识变更请求记录 ';
	if(bussType=='232') return '个人担保账户更正请求记录     ';
	if(bussType=='233') return '个人担保账户按段删除请求记录 ';
	if(bussType=='234') return '个人担保账户整笔删除请求记录 ';

	if(bussType=='510') return '抵(质)押合同信息记录         ';
	if(bussType=='511') return '抵(质)押合同标识变更请求记录 ';
	if(bussType=='514') return '抵(质)押合同整笔删除请求记录 ';

	if(bussType=='310') return '企业基本信息';
	if(bussType=='314') return ' 企业基本信息删除请求记录';
	if(bussType=='340') return ' 企业身份标识整合信息记录';
	if(bussType=='350') return ' 企业间关联关系信息记录  ';

	if(bussType=='410') return ' 企业借贷账户记录                   ';
	if(bussType=='411') return ' 企业借贷账户标识变更请求记录       ';
	if(bussType=='412') return ' 企业借贷账户更正请求记录           ';
	if(bussType=='413') return ' 企业借贷账户按段删除请求记录       ';
	if(bussType=='414') return ' 企业借贷账户整笔删除请求记录       ';
	if(bussType=='420') return ' 企业授信协议信息记录               ';
	if(bussType=='421') return ' 企业授信协议标识变更请求记录       ';
	if(bussType=='422') return ' 企业授信协议更正请求记录           ';
	if(bussType=='423') return ' 企业授信协议按段删除请求记录       ';
	if(bussType=='424') return ' 企业授信协议整笔删除请求记录       ';
	if(bussType=='430') return ' 企业最高额保证合同信息记录         ';
	if(bussType=='431') return ' 企业最高额保证合同标识变更请求记录 ';
	if(bussType=='432') return ' 企业最高额保证合同更正请求记录     ';
	if(bussType=='433') return ' 企业最高额保证合同按段删除请求记录 ';
	if(bussType=='434') return ' 企业最高额保证合同整笔删除请求记录 ';

	if(bussType=='440') return '企业担保账户信息记录         ';
	if(bussType=='441') return '企业担保账户标识变更请求记录 ';
	if(bussType=='442') return '企业担保账户更正请求记录     ';
	if(bussType=='443') return '企业担保账户按段删除请求记录 ';
	if(bussType=='444') return ' 企业担保账户整笔删除请求记录';

	if(bussType=='610') return ' 企业资产负债表信息记录              ';
	if(bussType=='620') return ' 企业利润及利润分配表信息记录        ';
	if(bussType=='630') return ' 企业现金流量表信息记录              ';
	if(bussType=='640') return ' 事业单位资产负债表信息记录          ';
	if(bussType=='650') return ' 事业单位收入支出表信息记录          ';
	if(bussType=='614') return ' 企业资产负债表整笔删除请求记录      ';
	if(bussType=='624') return ' 企业利润及利润分配表整笔删除请求记录';
	if(bussType=='634') return ' 企业现金流量表整笔删除请求记录      ';
	if(bussType=='644') return ' 事业单位资产负债表整笔删除请求记录  ';
	if(bussType=='654') return ' 事业单位收入支出表整笔删除请求记录  ';
}

function modify(bussType,id,msg){
	var url =  getUrl(bussType);
	var tabName =getTabName(bussType).trim();
	openSubTab(url+'?id='+id+'&msg='+msg+"&bussType="+bussType,tabName);
}

//function getSeqNO(RECEIVEFILENAME,ERRRECID){
//	var url =path+"/Receivefile/getErrecId.html";
//	$.ajax({
//		url : url,
//		type : "post",
//		data : {
//			receiveFileName : RECEIVEFILENAME,
//			errecId:ERRRECID
//		},
//		async : false,
//		success : function(data) {
//			return "1";
//			
//		}
//	});
//}

function getQueryParam() {
	var queryObject = new Object();
	queryObject.queryreceiveFileName = $('#queryreceiveFileName').val();
	queryObject.querysendFileName = $('#querysendFileName').val();
	
	return queryObject;
}

function doClear() {
	$('#queryreceiveFileName').textbox('clear');
 $('#querysendFileName').textbox('clear');
}