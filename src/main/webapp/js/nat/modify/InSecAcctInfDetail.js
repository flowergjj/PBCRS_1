//工程地址
var path = getAppPath();
// 信息主键
var key = getQueryString("id");
var fromJson = "";
// 存放当前打开的段落id;
var nowId = "";
$(function() {
	setinitCombobox();
	infoHide();
	initKey(key);
});

// 初始化各段主键
function initKey(GuarAcctBsSgmtSeqNo) {
	// 给各段主键赋值
	$('#guarAcctBsSgmtKey').val(GuarAcctBsSgmtSeqNo);
	$('#guarAcctBsInfSgmtKey').val(GuarAcctBsSgmtSeqNo);
	$('#guaRltRepInfSgmtKey').val(GuarAcctBsSgmtSeqNo);
	$('#gsRltRepInfSgmtKey').val(GuarAcctBsSgmtSeqNo);
	$('#guaMotCltCtrSgmtKey').val(GuarAcctBsSgmtSeqNo);
}
//
function loadForm(id) {

	// 得到相应段的主键值
	if (id == "gsRltRepInfSgmt") {
		loadTable(id);
	} else if (id == "guaMotCltCtrSgmt") {
		loadguaMotCltCtrSgmt(id);
	} else {
		var infoKey = $('#' + id + 'Key').val();
		// 首字母转大写
		var url = firstToUpper(id);
		$.ajax({
			url : path + '/InSecAcctInf/get' + url + '.html',
			type : "post",
			data : {
				// 主键
				"infoKey" : infoKey
			},
			async : false,
			success : function(data) {
				// 加载id对应的列表信息信息
				$('#' + id + 'Form').form('load', data);
				fromJson = $('#' + id + 'Form').serialize();

			}
		});
	}
}
// 加载表格
function loadTable(id) {

	var infoKey = $('#' + id + 'Key').val();
	var url = firstToUpper(id);
	$('#' + id + 'Table').datagrid({
		striped : true,

		url : path + '/InSecAcctInf/get' + url + '.html',
		remoteSort : false,
		idField : 'GSRLTREPYMTINFSEQNO',
		queryParams : {
			'rltRepymtInfSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		width : '100%',
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'RLTREPYMTINFSGMTSEQNO',
			title : '主键',
			width : '20%',
			align : 'center'
		}, {
			field : 'GSRLTREPYMTINFSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		}, {
			field : 'ARLPNAME',
			title : '责任人名称',
			width : '20%',
			align : 'center'
		}, {
			field : 'ARLPCERTTYPE',
			title : '责任人身份标识类型',
			width : '20%',
			align : 'center'
		}, {
			field : 'ARLPCERTNUM',
			title : '责任人身份标识号码',
			width : '20%',
			align : 'center'
		}, {
			field : 'ARLPAMT',
			title : '还款责任金额',
			width : '20%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.RLTREPYMTINFSGMTSEQNO;
				var seqno = row.GSRLTREPYMTINFSEQNO;
				return '<a href="javascript:void(0)" onClick=openDialog("' + seqno + '","' + id + '")>修改</a>';

			}

		} ] ]

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
// 加载抵质押物表格
function loadguaMotCltCtrSgmt(id) {
	var infoKey = $('#' + id + 'Key').val();
	var url = firstToUpper(id);
	$('#' + id + 'Table').datagrid({
		striped : true,

		url : path + '/InSecAcctInf/get' + url + '.html',
		remoteSort : false,
		idField : 'MOTGACLTALCTRCTINFSEQNO',
		queryParams : {
			'GuarMotgaCltalCtrctInfSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'GUAMOTCLTCTRINFSGMTSEQNO',
			title : '主键',
			width : '20%',
			align : 'center'
		}, {
			field : 'MOTGACLTALCTRCTINFSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		}, {
			field : 'CCC',
			title : '合同标识码',
			width : '20%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.MOTGACLTALCTRCTINFSEQNO;
				return '<a href="javascript:void(0)" onClick=modify("' + id + '")>修改</a>';

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
function modify(index) {
	openSubTab(path + '/jsp/nat/modify/MotCltCtrInfDetail.jsp?id=' + index, '抵质押物信息修改详细');
}

function openDialog(seqno, id) {
	$('#dialog-gsRltRepInfSgmt').dialog('open');
	// return false;
	var url = path + "/InSecAcctInf/getGsRltRepymtInf.html?GsRltRepymtInfSeqNo=" + seqno + "&RltRepymtInfSgmtSeqNo=" + id;

	$('#gsRltRepInfSgmtForm').form('load', url);
}

// 打开选择的信息段//关闭原先段落信息
function showDetial(id) {
	if (nowId == "") {
		nowId = id;
	}
	// 加载之前先清空
	// 加载该段落数据
	loadForm(id);
	// 隐藏现有段页面
	$('#' + nowId).hide();
	// $('#' + nowId).css('display', 'none');
	// 展示选择段页面
	$('#' + id).show();
	// $('#' + id).css('display', 'block');
	// 将现在打开的重新赋值给记录当前打开的段落nowId
	nowId = id;

}

// 修改
function submitform(sgmtId,updateType) {

	var json = $('#' + sgmtId + 'Form').serialize();
	if (fromJson == json) {
		$.messager.alert('错误', '请修改后再保存!', 'error');
		return false;
	}

	$('#' + sgmtId + 'Form').form('submit', {
		url : path + "/InSecAcctInf/update" + firstToUpper(sgmtId) + ".html?INSECACCTINFSEQNO=" + key+"&updateType="+updateType,
		success : function(res) {
			res = JSON.parse(res);
			if (res.RET_CODE == "SUCCESS") {
				$.messager.alert('成功', '提交成功!', 'info');
				// key = res.INSECACCTINFSEQNO;
				initKey(key);
				/*
				 * if (sgmtId == "idSgmt") {
				 * $('#dialog-idSgmt').dialog('close'); loadTable(sgmtId); }
				 * else { showDetial(sgmtId); }
				 */
				showDetial(sgmtId);
				reflashTab("../jsp/nat/modify/InSecAcctInf.jsp", "个人担保账户信息");
				parent.$('#center_tab').tabs('select', "个人担保信息修改详细");
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
	$('#guarAcctBsSgmt').hide();
	$('#guarAcctBsInfSgmt').hide();
	$('#guaRltRepInfSgmt').hide();
	$('#gsRltRepInfSgmt').hide();
	$('#guaMotCltCtrSgmt').hide();

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
	initCombobox('InSecAccType', 'acctType');
	initCombobox('InSecAccRptDate', 'rptDateCode');
	initCombobox('InIDType', 'idtype');

	initCombobox('InSecBusiLines', 'busiLines');
	initCombobox('InSecBusiDtilLines', 'busiDtilLines');
	initCombobox('Cy', 'cy');
	initCombobox('InSecAccGuarMode', 'guarMode');
	initCombobox('InSecAccOthRepGuaWay', 'othRepyGuarWay');
	initCombobox('InSecAccStatus', 'acctStatus');
	initCombobox('FiveCate', 'fiveCate');
	initCombobox('CompAdvFlag', 'compAdvFlag');
	initCombobox('IDType', 'infoIDType');
	initCombobox('IDType', 'infoIDType');
	initCombobox('InSecAccRltRepType', 'arlpType');
	initCombobox('InIDType', 'arlpCertType');
	initCombobox('InSecAccWartySign', 'wartySign');

}