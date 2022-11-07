//工程地址
var path = getAppPath();
//信息报告日期
var rptdate = getQueryString("rptdate");
//栏位
var msg = getQueryString('msg');
//报文类型
var bussType = getQueryString('bussType');
//反馈编码
var fbCode = getQueryString('fbCode');
// 信息主键
var key = getQueryString("id");
// 存放当前打开的段落id;
var nowId = "";
var fromJson = "";
$(function() {

	setinitCombobox();
	infoHide();
	initKey(key);
	getFeedBack(bussType,msg,fbCode);

});
// 根据主表主键初始化各段主键
function initKey(CtrctBsSgmtSeqNo) {
	// 给各段主键赋值
	$('#ctrctBsSgmtKey').val(CtrctBsSgmtSeqNo);
	$('#ctrctCertRelSgmtKey').val(CtrctBsSgmtSeqNo);
	$('#creditLimSgmtKey').val(CtrctBsSgmtSeqNo);
}
//
function loadForm(id) {
	// 得到相应段的主键值
	if (id == "ctrctCertRelSgmt") {
		loadTable(id);
	} else {
		var infoKey = $('#' + id + 'Key').val();
		// 首字母转大写
		var url = firstToUpper(id);
		$.ajax({
			url : path + '/InCtrctInf/get' + url + '.html',
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
				$('#CONTRACTCOD1E').textbox('setValue',$('#CONTRACTCODE').textbox('getValue').substr(14));
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

		url : path + '/InCtrctInf/get' + url + '.html',
		remoteSort : false,
		idField : 'CTRCTCERTRELSEQNO',
		queryParams : {
			'CtrctCertRelSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'CTRCTCERTRELSGMTSEQNO',
			title : '主键1',
			width : '25%',
			align : 'center',
			hiden : true
		}, {
			field : 'CTRCTCERTRELSEQNO',
			title : '主键2',
			width : '25%',
			align : 'center',
			hiden : true
		}, {
			field : 'BRERTYPE',
			title : '共同受信人身份类别',
			width : '25%',
			align : 'center'
		}, {
			field : 'CERTRELNAME',
			title : '共同受信人名称',
			width : '25%',
			align : 'center'
		}, {
			field : 'CERTRELIDTYPE',
			title : '共同受信人身份标识类型',
			width : '25%',
			align : 'center'
		}, {
			field : 'CERTRELIDNUM',
			title : '共同受信人身份标识号码',
			width : '25%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.CTRCTCERTRELSGMTSEQNO;
				var seqno = row.CTRCTCERTRELSEQNO;
				return '<a href="javascript:void(0)" onClick=openDialog("' + seqno + '","' + id + '")>修改</a>';

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
	if(id=="creditLimSgmt"){
		getCount();
	}
	// $('#' + id).css('display', 'block');
	// 将现在打开的重新赋值给记录当前打开的段落nowId
	nowId = id;

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
// 修改
function submitform(sgmtId,updateType,tableName) {
	if(!checkUpdateType(updateType,rptdate)) return false;
	/*var json = $('#' + sgmtId + 'Form').serialize();
	if (fromJson == json) {
		$.messager.alert('错误', '请修改后再保存!', 'error');
		return false;
	}*/
	if(checkData(tableName)==false){
		
		return ;
	};
	$('#' + sgmtId + 'Form').form('submit', {
		url : path + "/InCtrctInf/update" + firstToUpper(sgmtId) + ".html?INCTRCTINFSEQNO=" + key+"&updateType="+updateType,
		success : function(res) {
			res = JSON.parse(res);
			if (res.RET_CODE == "SUCCESS") {
				$.messager.alert('成功', '提交成功!', 'info');
				initKey(key);
				showDetial(sgmtId);
				reflashTab("../jsp/nat/modify/InCtrctInf.jsp", "个人授信协议信息");
				parent.$('#center_tab').tabs('select', "个人授信信息修改详细");
			} else {
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
}

function infoHide() {
	$('#ctrctBsSgmt').hide();
	$('#ctrctCertRelSgmt').hide();
	$('#creditLimSgmt').hide();

}

function openDialog(seqno, id) {
	$('#dialog-ctrctCertRelSgmt').dialog('open');
	var url = path + "/InCtrctInf/getCtrctCertRel.html?CtrctCertRelSeqNo=" + seqno + "&CtrctCertRelSgmtSeqNo=" + id;
	// console.log(url);
	$('#ctrctCertRelSgmtForm').form('load', url);
}
// 下拉框代码表
function setinitCombobox() {
	
	
	initCombobox('InCreditLimType', 'CREDITLIMTYPE');
	initCombobox('InCtrctRptDate', 'RPTDATECODE');
	initCombobox('InIDType', 'IDTYPE');
	initCombobox('IDType', 'BRERTYPE');
	initCombobox('InIDType', 'CERTRELIDTYPE');
	initCombobox('McLimLoopFlag', 'LIMLOOPFLG');
	initCombobox('Cy', 'CY');
	initCombobox('McConStatus', 'CONSTATUS');

}

//按段删除
function deleteform() {

	$.messager.confirm('确认','您确认想要删除此段吗？',function(r){
		
		if(r){

			MaskUtil.mask('正在删除，请稍候……');

			$.ajax({
		url : path + "/InCtrctInf/deleteform.html",
		type : "post",
		data : {
			// 主键
			"key" : key,
			"startDate":$('#dd').datebox('getValue')
		},
		async : false,
		success : function(data) {
			MaskUtil.unmask();
			if (data.RET_CODE == "SUCCESS") {
				$.messager.alert('成功', '提交成功!', 'info');
				$("#del").linkbutton({"disabled":true});
				$("#sub").linkbutton({"disabled":true});
				$("#upd").linkbutton({"disabled":true});
			} else {
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	}); 
		}
		});

	$(".messager-button").prev()
		.append('<font color="red">删除起始时间(非必选)：</font>'+
			'<input  id="dd"  type="text" class="easyui-datebox" required="false" style="width: 100px"></input> ');
	$('#dd').datebox({
		required:false
	});


}

function getCount(){
	$.ajax({
		url : path + "/InCtrctInf/getCount.html",
		type : "post",
		data : {
			// 主键
			"key" : key
		},
		async : false,
		success : function(data) {
			if(data.RET_CODE != "FAILED"){
				if(data.count == 1){
					$("#del").linkbutton({"disabled":true});
					$("#sub").linkbutton({"disabled":true});
					$("#upd").linkbutton({"disabled":true});
				}
			}

		}
	}); 
}