//工程地址
var path = getAppPath();
// 抵质押信息信息主键
var key = getQueryString("id");
// 业务主键
var businessKey = getQueryString("businessKey");
// 业务类型GU（个人借贷）MO（企业借贷）
var type = getQueryString("type");
//信息报告日期
var rptdate = getQueryString("rptdate");
// 栏位
var msg = getQueryString('msg');
// 报文类型
var bussType = getQueryString('bussType');
// 反馈编码
var fbCode = getQueryString('fbCode');

var fromJson = "";
// 存放当前打开的段落id;
var nowId = "";
$(function() {
	console.log($('#table2 :input[id="NAME"]').attr('class'));
	setinitCombobox();
	infoHide();
	initKey(key);
	getFeedBack(bussType, msg, fbCode);
	initBindEvent();
	showDetial('motCltCtrBsSgmt')
	// getFeedBack('510','PleCertID',fbCode);
});

function initKey(MotgaCltalCtrctBsSgmtSeqNo) {
	// 给各段主键赋值
	$('#motCltCtrBsSgmtKey').val(MotgaCltalCtrctBsSgmtSeqNo);
	$('#motCltBsInfSgmtKey').val(MotgaCltalCtrctBsSgmtSeqNo);
	$('#comRecInfSgmtKey').val(MotgaCltalCtrctBsSgmtSeqNo);
	$('#motgaProptInfSgmtKey').val(MotgaCltalCtrctBsSgmtSeqNo);
	$('#cltalInfSgmtKey').val(MotgaCltalCtrctBsSgmtSeqNo);
}

function initTable(){
	var reportFlag = $('#reportflag').val();
	if(reportFlag == '1'){
		$('#subTable2').linkbutton('disable')
		$('#subTable1').linkbutton('disable')
		$('#subTable4').linkbutton('disable')
		$('#subTable5').linkbutton('disable')
	}else{
		$('#updSubTable2').linkbutton('disable')
		$('#updSubTable1').linkbutton('disable')
		$('#updSubTable4').linkbutton('disable')
		$('#updSubTable5').linkbutton('disable')
	}
}

function loadForm(id) {

	// 得到相应段的主键值
	if (id == "comRecInfSgmt") {
		loadTableComRecInfSgmt(id);
	} else if (id == "motgaProptInfSgmt") {
		loadTableMotgaProptInfSgmt(id);
	} else if (id == "cltalInfSgmt") {
		loadTableCltalInfSgmt(id);
	} else {
		var infoKey = $('#' + id + 'Key').val();
		// 首字母转大写
		var url = firstToUpper(id);
		$.ajax({
			url : path + '/MotCltCtrInf/get' + url + '.html',
			type : "post",
			data : {
				// 主键
				"infoKey" : infoKey
			},
			async : false,
			success : function(data) {
				initOMCombobox(data, id + 'Form');
				if(id=='motCltCtrBsSgmt'){
					$('#' + id + 'Form').form({
						onLoadSuccess:function(){
							/*初始化各个提交按钮*/
							initTable()
						}
					})

				}
				// 加载id对应的列表信息信息
				$('#' + id + 'Form').form('load', data);

				fromJson = $('#' + id + 'Form').serialize();
				$('#CCCODE1').textbox('setValue',$('#CCCODE').textbox('getValue').substr(14));
			}
		});
	}
}
// 加载表格
function loadTableComRecInfSgmt(id) {
	var infoKey = $('#' + id + 'Key').val();
	var url = firstToUpper(id);
	$('#' + id + 'Table').datagrid({
		striped : true,

		url : path + '/MotCltCtrInf/get' + url + '.html',
		remoteSort : false,
		idField : 'COMRECINFSGMTSEQNO',
		queryParams : {
			'ComRecInfSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'COMRECINFSGMTSEQNO',
			title : '主键',
			width : '20%',
			align : 'center'
		}, {
			field : 'OTRRECSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		}, {
			field : 'INFOIDTYPE',
			title : '身份类别',
			width : '20%',
			align : 'center'
		}, {
			field : 'GUARNAME',
			title : '其他债务人名称',
			width : '20%',
			align : 'center'
		}, {
			field : 'GUARCERTTYPE',
			title : '其他债务人身份标识类型',
			width : '20%',
			align : 'center'
		}, {
			field : 'GUARCERTNUM',
			title : '其他债务人身份标识号码',
			width : '20%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.COMRECINFSGMTSEQNO;
				var seqno = row.OTRRECSEQNO;
				return '<a href="javascript:void(0)" onClick=openDialogComRecInfSgmt("' + seqno + '","' + id + '")>修改</a>';

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
function loadTableMotgaProptInfSgmt(id) {
	var infoKey = $('#' + id + 'Key').val();
	var url = firstToUpper(id);
	$('#' + id + 'Table').datagrid({
		striped : true,

		url : path + '/MotCltCtrInf/get' + url + '.html',
		remoteSort : false,
		idField : 'MOTGAPROPTINFSGMTSEQNO',
		queryParams : {
			'ComRecInfSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [ /*{
			field : 'MOTGAPROPTINFSGMTSEQNO',
			title : '主键',
			width : '20%',
			align : 'center'
		}, {
			field : 'PLEINFSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		}, */{
			field : 'PLETYPE',
			title : '抵押物种类',
			width : '16%',
			align : 'center',
			formatter: function(value,row,index){
				if(value=='11') return	'房产';
				if(value=='12') return	'土地使用权(包含土地附着物)';
				if(value=='13') return	'交通运输设备';
				if(value=='14') return	'机器设备';
				if(value=='99') return	'其他'; 
			}
		}, {
			field : 'PLEDGORTYPE',
			title : '抵押人身份类别',
			width : '16%',
			align : 'center',
			formatter: function(value,row,index){
				if(value=='1')	return '自然人';
				if(value=='2')	return '组织机构';
			}
		}, {
			field : 'PLEDGORNAME',
			title : '抵押人名称',
			width : '16%',
			align : 'center'
		}, {
			field : 'PLEORCERTTYPE',
			title : '抵押人身份标识类型',
			width : '20%',
			align : 'center',
			formatter: function(value,row,index){
				if(row.PLEDGORTYPE=='1'){
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
				if(row.PLEDGORTYPE=='2'){
					if(value=='10') return	'中征码(原贷款卡编)';
					if(value=='20') return	'统一社会信用代码';
					if(value=='30') return	'组织机构代码';
				}
			}
		}, {
			field : 'PLEORCERTNUM',
			title : '抵押人身份标识号码',
			width : '16%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '16%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.MOTGAPROPTINFSGMTSEQNO;
				var seqno = row.PLEINFSEQNO;
				return '<a href="javascript:void(0)" onClick=openDialogMotgaProptInfSgmt("' + seqno + '","' + id + '")>修改</a>';

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
function loadTableCltalInfSgmt(id) {
	var infoKey = $('#' + id + 'Key').val();
	var url = firstToUpper(id);
	$('#' + id + 'Table').datagrid({
		striped : true,

		url : path + '/MotCltCtrInf/get' + url + '.html',
		remoteSort : false,
		idField : 'CLTALINFSGMTSEQNO',
		queryParams : {
			'CltalInfSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [ /*{
			field : 'CLTALINFSGMTSEQNO',
			title : '主键',
			width : '20%',
			align : 'center'
		}, {
			field : 'IMPINFSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		}, */{
			field : 'IMPTYPE',
			title : '质押物种类',
			width : '16%',
			align : 'center',
			formatter: function(value,row,index){
				if(value=='11') return 	'现金及其等价物'; 
				if(value=='12') return 	'票据';
				if(value=='13') return 	'保单';
				if(value=='14') return 	'债券'; 
				if(value=='15') return 	'股权';
				if(value=='21') return 	'基金';
				if(value=='22') return 	'贵金属'; 
				if(value=='23') return 	'应收账款'; 
				if(value=='24') return 	'流动资产';
				if(value=='25') return 	'无形资产'; 
				if(value=='26') return 	'涉农质物'; 
				if(value=='90') return 	'其他';
			}
		}, {
			field : 'IPPC',
			title : '出质人身份类别',
			width : '16%',
			align : 'center',
			formatter: function(value,row,index){
				if(value=='1')	return '自然人';
				if(value=='2')	return '组织机构';
			}
		}, {
			field : 'PAWNNAME',
			title : '出质人名称',
			width : '16%',
			align : 'center'
		}, {
			field : 'PAWNCERTTYPE',
			title : '出质人身份标识类型',
			width : '20%',
			align : 'center',
			formatter: function(value,row,index){
				if(row.IPPC=='1'){
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
				if(row.IPPC=='2'){
					if(value=='10') return	'中征码(原贷款卡编)';
					if(value=='20') return	'统一社会信用代码';
					if(value=='30') return	'组织机构代码';
				}
			}
		}, {
			field : 'PAWNCERTNUM',
			title : '出质人身份标识号码',
			width : '16%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '16%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.CLTALINFSGMTSEQNO;
				var seqno = row.IMPINFSEQNO;
				return '<a href="javascript:void(0)" onClick=openDialogCltalInfSgmt("' + seqno + '","' + id + '")>修改</a>';

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

function openDialogComRecInfSgmt(seqno, id) {
	$('#dialog-comRecInfSgmt').dialog('open');
	// return false;
	var url = path + "/MotCltCtrInf/getOtrRec.html?OtrRecSeqNo=" + seqno + "&ComRecInfSgmtSeqNo=" + id;
	
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			initOMCombobox(data, 'comRecInfSgmtForm');
			// 加载id对应的列表信息信息
			$('#comRecInfSgmtForm').form('load', data);
			dialog();
		}
	});
	
	
	// console.log(url);
	//$('#comRecInfSgmtForm').form('load', url);
	//dialog();
}
function openDialogMotgaProptInfSgmt(seqno, id) {

	$('#dialog-motgaProptInfSgmt').dialog('open');
	// return false;
	var url = path + "/MotCltCtrInf/getPleInf.html?PleInfSeqNo=" + seqno + "&ComRecInfSgmtSeqNo=" + id;

	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			initOMCombobox(data, 'motgaProptInfSgmtForm');
			// 加载id对应的列表信息信息
			$('#motgaProptInfSgmtForm').form('load', data);
			dialog();
		}
	});
	
	
	
	// console.log(url);
	//$('#motgaProptInfSgmtForm').form('load', url);

	//dialog();
	// getFeedBack('510','PleCertID',fbCode);

}
function openDialogCltalInfSgmt(seqno, id) {
	$('#dialog-cltalInfSgmt').dialog('open');
	// return false;
	var url = path + "/MotCltCtrInf/getImpInf.html?ImpInfSeqNo=" + seqno + "&CltalInfSgmtSeqNo=" + id;
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			initOMCombobox(data, 'cltalInfSgmtForm');
			// 加载id对应的列表信息信息
			$('#cltalInfSgmtForm').form('load', data);
			dialog();
		}
	});
	
	// console.log(url);
	//$('#cltalInfSgmtForm').form('load', url);
	//dialog();
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
function submitform(sgmtId, updateType, tableName) {
	//检验修改类型
	/*if(!checkUpdateType(updateType,rptdate)) return false;*/
	var json = $('#' + sgmtId + 'Form').serialize();
	if (fromJson == json) {
		$.messager.alert('错误', '请修改后再保存!', 'error');
		return false;
	}
	if (checkData(tableName) == false) {

		return;
	}

	$('#' + sgmtId + 'Form').form('submit', {
		url : path + "/MotCltCtrInf/update" + firstToUpper(sgmtId) + ".html?MOTGACLTALCTRCTINFSEQNO=" + key + "&updateType=" + updateType,
		success : function(res) {
			res = JSON.parse(res);

			if (res.RET_CODE == "SUCCESS") {
				$.messager.alert('成功', '提交成功!', 'info');
				initKey(key);
				showDetial(sgmtId);
				reflashTab("../jsp/nat/modify/MotCltCtrInf.jsp?sourceSys=ILN,PLN,SLN", "抵质押物信息");
				// 当业务主键有值时候进行跳转，没有值说明不是从借贷跳转过来的
				if (businessKey != null && type == 'GU') {
					reflashTab(path + '/jsp/nat/modify/InAcctInfDetail.jsp?id=' + businessKey, '个人借贷信息修改详细');
				} else {
					if (businessKey != null && type == 'MO')
						reflashTab(path + '/jsp/nat/modify/EnAcctInfDetail.jsp?id=' + businessKey, '企业借贷信息修改详细');
				}

				// parent.$('#center_tab').tabs('select', "抵质押物信息修改详细");
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
	$('#motCltCtrBsSgmt').hide();
	$('#motCltBsInfSgmt').hide();
	$('#comRecInfSgmt').hide();
	$('#motgaProptInfSgmt').hide();
	$('#cltalInfSgmt').hide();
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

	initCombobox('Cy', 'IMPCY');
	initCombobox('IDType', 'PAWNCERTTYPE');
	initCombobox('ImpType', 'IMPTYPE');
	initCombobox('ValOrgType', 'VALORGTYPE');
	initCombobox('Cy', 'PLECY');
	initCombobox('Dist', 'PLEDISTR');
	initCombobox('IDType', 'PLEDGORTYPE');
	initCombobox('MotgaProptIDType', 'MOTGAPROPTIDTYPE');
	initCombobox('PleType', 'PLETYPE');
	initCombobox('MotCltCtrStatus', 'CCSTATUS');
	initCombobox('MaxGuar', 'MAXGUAR');
	initCombobox('Cy', 'CY');
	initCombobox('MotCltCtrType', 'GUARTYPE');
	initCombobox('InAcctMdfcType', 'acctType');
	initCombobox('IDType', 'INFOIDTYPE');
	initCombobox('IDType', 'IPPC');
	initCombobox('MotCltCtrRptDate', 'RPTDATECODE');
	
	
}
function initOMCombobox(data, id) {
	if (data.infoIDType != null && id=='motCltCtrBsSgmtForm') {
		if (data.infoIDType == '1') {
			initCombobox('InIDType', id + ' :input[id="CERTTYPE"]');
		} else {
			initCombobox('EntCertType', id + ' :input[id="CERTTYPE"]');
		}
	}
	if (data.infoIDType != null && id=='comRecInfSgmtForm') {
		if (data.infoIDType == '1') {
			initCombobox('InIDType', 'GUARCERTTYPE');
		} else {
			initCombobox('EntCertType', 'GUARCERTTYPE');
		}
	}
	if (data.pledgorType != null ) {
		if (data.pledgorType == '1') {
			initCombobox('InIDType', 'PLEORCERTTYPE');
		} else {
			initCombobox('EntCertType', 'PLEORCERTTYPE');
		}
	}
	if (data.ippc != null ) {
		if (data.ippc == '1') {
			initCombobox('InIDType', 'PAWNCERTTYPE');
		} else {
			initCombobox('EntCertType', 'PAWNCERTTYPE');
		}
	}
	
}
function initBindEvent() {

	$('#motCltCtrBsSgmtForm :input[id="INFOIDTYPE"]').combobox({

		onChange : function(newValue) {
			if (newValue== '1') {
				initCombobox('InIDType', 'CERTTYPE');
			} else {
				initCombobox('EntCertType', 'CERTTYPE');
			}
		}
	});

	$('#comRecInfSgmtForm :input[id="INFOIDTYPE"]').combobox({

		onChange : function(newValue) {
			if (newValue== '1') {
				initCombobox('InIDType', 'GUARCERTTYPE');
			} else {
				initCombobox('EntCertType', 'GUARCERTTYPE');
			}
		}
	});
	
	$('#PLEDGORTYPE').combobox({

		onChange : function(newValue) {
			if (newValue== '1') {
				initCombobox('InIDType', 'PLEORCERTTYPE');
			} else {
				initCombobox('EntCertType', 'PLEORCERTTYPE');
			}
		}
	});
	$('#IPPC').combobox({

		onChange : function(newValue) {
			if (newValue== '1') {
				initCombobox('InIDType', 'PAWNCERTTYPE');
			} else {
				initCombobox('EntCertType', 'PAWNCERTTYPE');
			}
		}
	});

}

