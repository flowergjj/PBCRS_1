//工程地址
var path = getAppPath();

// 栏位
var msg = getQueryString('msg');
// 报文类型
var bussType = getQueryString('bussType');
// 反馈编码
var fbCode = getQueryString('fbCode');
//信息报告日期
var rptdate = getQueryString("rptdate");
// 信息主键
var key = getQueryString("id");
var bsSgmt;//基础段
var fromJson = "";
// 存放当前打开的段落id;
var nowId = "";
$(function() {
	// key = window.location.search.split("=", 2)[1];
	initBindEvent();
	setinitCombobox();
	infoHide();
	initKey(key);
	//设置月份这个栏位只显示年和月份，不显示日
	setDateBox('#MONTH');
	getEnAcctBsSgmt();
	getFeedBack(bussType, msg, fbCode);
	showDetial('acctBsSgmt')

});

// 初始化各段主键
function initKey(AcctBsSgmtSeqNo) {

	// 给各段主键赋值
	$('#acctBsSgmtKey').val(AcctBsSgmtSeqNo);
	$('#acctBsInfSgmtKey').val(AcctBsSgmtSeqNo);
	$('#rltRepymtInfSgmtKey').val(AcctBsSgmtSeqNo);
	$('#oriCreInfSgmtKey').val(AcctBsSgmtSeqNo);
	$('#accMthBlgInfSgmtKey').val(AcctBsSgmtSeqNo);
	$('#specPrdSgmtKey').val(AcctBsSgmtSeqNo);
	$('#acctDbtInfSgmtKey').val(AcctBsSgmtSeqNo);
	$('#accSpeTrsDspSgmtKey').val(AcctBsSgmtSeqNo);
	$('#motCltCtrInfSgmtKey').val(AcctBsSgmtSeqNo);
	$('#acctCredSgmtKey').val(AcctBsSgmtSeqNo);
}

function ininButton(){
	var reportFlag = $('#reportflag').val();

	if(reportFlag == '1'){
		$('#subTable2').linkbutton('disable')
		$('#subTable1').linkbutton('disable')
		$('#subTable8').linkbutton('disable')
		$('#subTable5').linkbutton('disable')
		$('#subTable7').linkbutton('disable')
		$('#subTable9').linkbutton('disable')
	}else{
		$('#updSubTable2').linkbutton('disable')
		$('#acctBsInfSgmtTable1').linkbutton('disable')
		$('#updSubTable1').linkbutton('disable')
		$('#updSubTable8').linkbutton('disable')
		$('#accMthBlgInfSgmtTable5').linkbutton('disable')
		$('#updSubTable5').linkbutton('disable')
		$('#acctDbtInfSgmtTable7').linkbutton('disable')
		$('#updSubTable7').linkbutton('disable')
		$('#accSpeTrsDspSgmtTable9').linkbutton('disable')
		$('#updSubTable9').linkbutton('disable')
	}
}
//
function loadForm(id) {

	// 得到相应段的主键值
	if (id == "rltRepymtInfSgmt") {
		loadTableRltRepymtInfSgmt(id);
	}
	if (id == "accSpeTrsDspSgmt") {
		loadTableAccSpeTrsDspSgmt(id);
	}
	if (id == "motCltCtrInfSgmt") {
		loadTablemotCltCtrInfSgmt(id);
	} else {
		var infoKey = $('#' + id + 'Key').val();
		// 首字母转大写
		var url = firstToUpper(id);
		$.ajax({
			url : path + '/InAcctInf/get' + url + '.html',
			type : "post",
			data : {
				// 主键
				"infoKey" : infoKey
			},
			async : false,
			success : function(data) {
				// 加载id对应的列表信息信息
				if(id == 'acctBsSgmt'){
					$('#' + id + 'Form').form({
						onLoadSuccess:function(){
							/*初始化各个提交按钮*/
							ininButton();
						}
					});
				}
				$('#' + id + 'Form').form('load', data);
				fromJson = $('#' + id + 'Form').serialize();
				$('#ACCTCODE1').textbox('setValue',$('#ACCTCODE').textbox('getValue').replace($('#MNGMTORGCODE').textbox('getValue'),''));
				if(data.paragraphDel!=null && data.paragraphDel == '1'){
					disabled(id + 'Form');
				}
			}
		});
	}
}
function disabled(formId){
	 $('#'+formId+' a.easyui-linkbutton').linkbutton('disable');
	/*$.each(aList,function(index,value){
		aList[index].linkbutton('disabled','true');
	});*/
}
// 加载表格
function loadTableRltRepymtInfSgmt(id) {
	var infoKey = $('#' + id + 'Key').val();
	var url = firstToUpper(id);
	$('#' + id + 'Table').datagrid({
		striped : true,

		url : path + '/InAcctInf/get' + url + '.html',
		remoteSort : false,
		idField : 'RltRepymtInfSeqNo',
		queryParams : {
			'RltRepymtInfSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [ /*{
			field : 'RLTREPYMTINFSGMTSEQNO',
			title : '主键',
			width : '20%',
			align : 'center'
		}, {
			field : 'RLTREPYMTINFSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		},*/ {
			field : 'INFOIDTYPE',
			title : '身份类别',
			width : '7%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.INFOIDTYPE == '1') return '自然人';
				if(row.INFOIDTYPE == '2') return '组织机构';

			}
		}, {
			field : 'ARLPNAME',
			title : '责任人名称',
			width : '11%',
			align : 'center'
		}, {
			field : 'ARLPCERTTYPE',
			title : '责任人身份标识类型',
			width : '19%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.INFOIDTYPE == '1'){
					if(value=='1')	return '户口薄';
					if(value=='2')	return '护照';
					if(value=='5')	return '港澳居民来往内地通行证';
					if(value=='6')	return '台湾同胞来往内地通行证';
					if(value=='8')	return '外国人居留证';
					if(value=='9')	return '警官证';
					if(value=='A')	return '香港身份证';
					if(value=='B')	return '澳门身份证';
					if(value=='C')	return '台湾身份证';
					if(value=='X')	return '其他证件';
					if(value=='10')	return '居民身份证及其他以公民身份证号为标识的证件';
					if(value=='20')	return '军人身份证件';
				}
				if(row.INFOIDTYPE == '2'){
					if(value=='10')	return '中征码';
					if(value=='20')	return '统一社会信用代码';
					if(value=='30')	return '组织机构代码';
				}

			}
		}, {
			field : 'ARLPCERTNUM',
			title : '责任人身份标识号码',
			width : '11%',
			align : 'center'
		}, {
			field : 'ARLPTYPE',
			title : '还款责任人类型',
			width : '11%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.ARLPTYPE == '1') return '共同借款人';
				if(row.ARLPTYPE == '2') return '保证人';
				if(row.ARLPTYPE == '9') return '其他';

			}
		}, {
			field : 'ARLPAMT',
			title : '还款责任金额',
			width : '11%',
			align : 'center'
		}, {
			field : 'WARTYSIGN',
			title : '联保标志',
			width : '11%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.WARTYSIGN == '0') return '单人保证/多人分保';
				if(row.WARTYSIGN == '1') return '联保';
				

			}
		}, {
			field : 'MAXGUARMCC',
			title : '保证合同编号',
			width : '11%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '8%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.RLTREPYMTINFSGMTSEQNO;
				var seqno = row.RLTREPYMTINFSEQNO;
				return '<a href="javascript:void(0)" onClick=openDialogRltRepymtInfSgmt("' + seqno + '","' + id + '")>修改</a>';

			}

		} ] ],

	});
	// 设置分页控件
	var p = $('#' + id + 'Table').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
	$('#' + id + 'Table').datagrid('clearSelections');
}
// 加载表格
function loadTableAccSpeTrsDspSgmt(id) {
	var infoKey = $('#' + id + 'Key').val();
	var url = firstToUpper(id);
	$('#' + id + 'Table').datagrid({
		striped : true,

		url : path + '/InAcctInf/get' + url + '.html',
		remoteSort : false,
		idField : 'GSRLTREPYMTINFSEQNO',
		queryParams : {
			'AcctSpecTrstDspnSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [ /*{
			field : 'ACCTSPECTRSTDSPNSGMTSEQNO',
			title : '主键',
			width : '20%',
			align : 'center'
		}, {
			field : 'CAGOFTRDINFSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		}, */{
			field : 'CHANTRANTYPE',
			title : '交易类型',
			width : '16%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.CHANTRANTYPE == '1') return '展期';
				if(row.CHANTRANTYPE == '4') return '提前还款';
				if(row.CHANTRANTYPE == '15') return '核销';
				

			}
		}, {
			field : 'TRANDATE',
			title : '交易日期',
			width : '16%',
			align : 'center'
		}, {
			field : 'TRANAMT',
			title : '交易金额',
			width : '16%',
			align : 'center'
		}, {
			field : 'DUETRANMON',
			title : '到期日期变更月数',
			width : '16%',
			align : 'center'
		}, {
			field : 'DETINFO',
			title : '交易明细信息',
			width : '20%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '16%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.ACCTSPECTRSTDSPNSGMTSEQNO;
				var seqno = row.CAGOFTRDINFSEQNO;
				return '<a href="javascript:void(0)" onClick=openDialogAccSpeTrsDspSgmt("' + seqno + '","' + id + '")>修改</a>';

			}

		} ] ],

	});
	// 设置分页控件
	var p = $('#' + id + 'Table').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
	$('#' + id + 'Table').datagrid('clearSelections');
}
// 加载表格
function loadTablemotCltCtrInfSgmt(id) {
	var infoKey = $('#' + id + 'Key').val();
	var url = firstToUpper(id);
	$('#' + id + 'Table').datagrid({
		striped : true,
		url : path + '/InAcctInf/get' + url + '.html',
		remoteSort : false,
		idField : 'MOTGACLTALCTRCTINFSGMTSEQNO',
		queryParams : {
			'MotgaCltalCtrctInfSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [/* {
			field : 'MOTGACLTALCTRCTINFSGMTSEQNO',
			title : '主键',
			width : '20%',
			align : 'center'
		}, {
			field : 'MOTGACLTALCTRCTINFSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		}, */{
			field : 'CCC',
			title : '合同标识码',
			width : '90%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				// 抵质押主键
				var id = row.MOTGACLTALCTRCTINFSEQNO;
				// 借贷信息主键
				var loanId = row.MOTGACLTALCTRCTINFSGMTSEQNO;
				return '<a href="javascript:void(0)" onClick=modify("' + id + '","' + loanId + '")>修改</a>';

			}

		} ] ],

	});
	// 设置分页控件
	var p = $('#' + id + 'Table').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
	$('#' + id + 'Table').datagrid('clearSelections');
}
// 跳转到修改页面
function modify(id, loadId) {
	openSubTab(path + '/jsp/nat/modify/MotCltCtrInfDetail.jsp?id=' + id + '&businessKey=' + loadId + "&type=GU", '抵质押物信息修改详细');
}

function openDialogRltRepymtInfSgmt(seqno, id) {
	$('#dialog-rltRepymtInfSgmt').dialog('open');
	// return false;
	var url = path + "/InAcctInf/getRltRepymtInf.html?RltRepymtInfSeqNo=" + seqno + "&RltRepymtInfSgmtSeqNo=" + id;
	// console.log(url);
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			initOMCombobox(data, 'rltRepymtInfSgmtForm');
			// 加载id对应的列表信息信息
			$('#rltRepymtInfSgmtForm').form('load', data);
			dialog();
		}
	});
	//$('#rltRepymtInfSgmtForm').form('load', url);
	//dialog();
}
function openDialogAccSpeTrsDspSgmt(seqno, id) {
	$('#dialog-accSpeTrsDspSgmt').dialog('open');
	// return false;
	var url = path + "/InAcctInf/getCagOfTrdInf.html?CagOfTrdInfSeqNo=" + seqno + "&AcctSpecTrstDspnSgmtSeqNo=" + id;
	// console.log(url);
	$('#accSpeTrsDspSgmtForm').form('load', url);
	
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			// 加载id对应的列表信息信息
			$('#accSpeTrsDspSgmtForm').form('load', data);
			//$('#rltRepymtInfSgmtForm').form('load', data);
			if(data.paragraphDel!=null && data.paragraphDel == '1'){
				disabled('accSpeTrsDspSgmtForm');
			}
			dialog();
		}
	});
	
}
// 打开选择的信息段//关闭原先段落信息
// function showDetial(id) {
// if (nowId == "") {
// nowId = id;
// }
// // 加载之前先清空
// // 加载该段落数据
// loadForm(id);
// // 隐藏现有段页面
// $('#' + nowId).hide();
// // $('#' + nowId).css('display', 'none');
// // 展示选择段页面
// $('#' + id).show();
// // $('#' + id).css('display', 'block');
// // 将现在打开的重新赋值给记录当前打开的段落nowId
// nowId = id;
//
// }
function showDetial(id) {
	if (nowId == "") {
		nowId = id;
	}
	loadForm(id);
	// 隐藏现有段页面
	$('#' + nowId).css('z-index', '-100');
	// $('#' + nowId).css('display', 'none');
	// 展示选择段页面
	$('#' + id).css('z-index', '100');
	// $('#' + id).css('display', 'block');
	// 将现在打开的重新赋值给记录当前打开的段落nowId
	nowId = id;

}

// 修改
function submitform(sgmtId, updateType, tableName) {
	//检验修改类型
	/*if(!checkUpdateType(updateType,rptdate)) return false;
	/!*var json = $('#' + sgmtId + 'Form').serialize();
	if (fromJson == json) {
		$.messager.alert('错误', '请修改后再保存!', 'error');
		return false;
	}*!/*/
	if (checkData(tableName) == false) {

		return;
	};
	$('#' + sgmtId + 'Form').form('submit', {
		url : path + "/InAcctInf/update" + firstToUpper(sgmtId) + ".html?INACCTINFSEQNO=" + key + "&updateType=" + updateType,
		success : function(res) {
			res = JSON.parse(res);
			if (res.RET_CODE == "SUCCESS") {
				$.messager.alert('成功', '提交成功!', 'info');
				showDetial(sgmtId);
				reflashTab("../jsp/nat/modify/InAcctInf.jsp", "个人借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "个人借贷信息修改详细");
				if(tableName =='table2'){
					getEnAcctBsSgmt();
				}
			} else {
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
	
}

// 条件查询
function doQuery() {
	$('#idSgmtTable').datagrid('load', queryParameter());

}
// 条件封装
function queryParameter() {
	var parameter = new Object();
	parameter.idSgmtName = $('#idSgmtName').textbox('getValue');
	parameter.idSgmtIdNum = $('#idSgmtIdNum').textbox('getValue');
	// 主键
	parameter.idSgmtSeqNo = $('#idSgmtKey').val();
	return parameter;
}
// 清除条件
function clearQuery() {
	$('#idSgmtName').textbox('clear');
	$('#idSgmtIdNum').textbox('clear');
}
// 首字母转大写
function firstToUpper(str) {
	var arr = str.split("");
	arr[0] = arr[0].toUpperCase();
	return arr.join("");
}

function infoHide() {

	/* $('#acctBsSgmt').hide(); */
	// $('#acctBsInfSgmt').hide();
	/*
	 * $('#rltRepymtInfSgmt').hide(); $('#oriCreInfSgmt').hide();
	 * $('#accMthBlgInfSgmt').hide(); $('#specPrdSgmt').hide();
	 * $('#acctDbtInfSgmt').hide(); $('#accSpeTrsDspSgmt').hide();
	 * $('#motCltCtrInfSgmt').hide();
	 */

}
function checkFrom(sgmtId) {
	var json = $('#' + sgmtId + 'Form').serialize();
	if (fromJson == json) {
		$.messager.alert('错误', '请修改后再保存!', 'error');
		return false;
	}
}
// 下拉框代码表
function setinitCombobox() {
	initCombobox('CagOfTrdType', 'CHANTRANTYPE');
	initCombobox('InitRpySts', 'INITRPYSTS');
	initCombobox('OrigDbtCate', 'ORIGDBTCATE');
	initCombobox('InLoanForm', 'LOANFORM');
	initCombobox('InFundSou', 'FUNDSOU');
	initCombobox('InAssetTrandFlag', 'ASSETTRANDFLAG');
	initCombobox('InOthRepyGuarWay', 'OTHREPYGUARWAY');
	initCombobox('InGuarMode', 'GUARMODE');
	initCombobox('InRepayFreqcy', 'REPAYFREQCY');
	initCombobox('InRepayMode', 'REPAYMODE');
	initCombobox('Flag', 'FLAG');
	initCombobox('D1/R4_RpyStatus', 'RPYSTATUS');
	initCombobox('FiveCate', 'FIVECATE');
	initCombobox('D1/R4_AcMthBlgInfSta', 'ACCTSTATUS');
	initCombobox('D1/R4_AcMthBlgInfSta', 'table7 :input[id="ACCTSTATUS"]');
	initCombobox('FiveCate', 'table7 :input[id="FIVECATE"]');
	initCombobox('RltRepymtIDType', 'INFOIDTYPE');
	initCombobox('Cy', 'CY');
	initCombobox('RltRepymtType', 'ARLPTYPE');
	initCombobox('AcctBusiDtilLines', 'BUSIDTLLINES');
	initCombobox('AcctBusiLines', 'BUSILINES');
	initCombobox('InAcctMdfcType', 'ACCTTYPE');
	initCombobox('InAcctMdfcRptDate', 'RPTDATECODE');
	initCombobox('InIDType', 'IDTYPE');
	initCombobox('AcctFirstHouLoanFlag', 'FIRSTHOULOANFLAG');
	initCombobox('Dist', 'APPLYBUSIDIST');
	initCombobox('WartySign', 'WARTYSIGN');
	
	
}
function initOMCombobox(data, id) {
	if (data.infoIDType != null) {
		if (data.infoIDType == '1') {
			initCombobox('InIDType', 'ARLPCERTTYPE');
		} else {
			initCombobox('EntCertType',  'ARLPCERTTYPE');
		}
	}	
}
function initBindEvent() {

	$('#INFOIDTYPE').combobox({

		onChange : function(newValue) {
			if (newValue== '1') {
				initCombobox('InIDType', 'ARLPCERTTYPE');
			} else {
				initCombobox('EntCertType', 'ARLPCERTTYPE');
			}
		}
	});


}

function getEnAcctBsSgmt(){
	var infoKey = $('#' + 'acctBsSgmtKey').val();
	var url = path+"/InAcctInf/getAcctBsSgmt.html";
	// 首字母转大
	$.ajax({
		url : url,
		type : "post",
		data : {
			// 主键
			"infoKey" : infoKey
		},
		async : false,
		success : function(data) {
			bsSgmt = data;

		}
	});
	
	
}

function byParagraphDelete(sgmtId, tableName){
	$.messager.confirm('确认对话框', '您想要删除该段吗？', function(r){
		if (r){
			$('#' + sgmtId + 'Form').form('submit', {
				url : path + "/InAcctInf/delete" + firstToUpper(sgmtId) + ".html?INACCTINFSEQNO=" + key+"&startDate="+$('#dd').datebox('getValue'),
				success : function(res) {
					res = JSON.parse(res);
					if (res.RET_CODE == "SUCCESS") {
						$.messager.alert('成功', '删除成功!', 'info');
						showDetial(sgmtId);
						//reflashTab("../jsp/nat/modify/InAcctInf.jsp", "个人借贷交易账户信息");
						//parent.$('#center_tab').tabs('select', "个人借贷信息修改详细");
						if(tableName =='table2'){
							getEnAcctBsSgmt();
						}
					} else {
						$.messager.alert('错误', '提交失败!', 'error');
					}
				}
			});
		}
	});
	
	$(".messager-button").prev()
	 .append('<font color="red">删除起始时间(非必选)：</font>'+
	 '<input  id="dd"  type="text" style="width: 100px"></input> '); 
	 $('#dd').datebox({    
		    required:false   
	 }); 

}
 