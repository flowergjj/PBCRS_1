//工程地址
var path = getAppPath();
// 信息主键
var key = getQueryString("id");
//信息报告日期
var rptdate = getQueryString("rptdate");
//栏位
var msg = getQueryString('msg');
//报文类型
var bussType = getQueryString('bussType');
//反馈编码
var fbCode = getQueryString('fbCode');
// 存放当前打开的段落id;
var nowId = "";
$(function() {

//	var arr= new Array();
//	var o=new Object();
//	o.sex=1;
//	arr[0]=o;
//	arr[1]=new Object();
//	console.log(arr);
	
	InitYearCombobox();
	setinitCombobox();
	infoHide();
	initKey(key);
	if(msg!=""&&msg!="undefined"&&msg!=null){
		showDetial('bsSgmt');
		$("#msg").html(msg);
	}
	getFeedBack(bussType,msg,fbCode);
	showDetial('bsSgmt');

});
// 根据主表主键初始化各段主键
function initKey(InBasInfoSeqNo) {
	// 给各段主键赋值
	$('#bsSgmtKey').val(InBasInfoSeqNo);
	$('#idSgmtKey').val(InBasInfoSeqNo);
	$('#fcsInfSgmtKey').val(InBasInfoSeqNo);
	$('#spsInfSgmtKey').val(InBasInfoSeqNo);
	$('#eduInfSgmtKey').val(InBasInfoSeqNo);
	$('#octpnInfSgmtKey').val(InBasInfoSeqNo);
	$('#redncInfSgmtKey').val(InBasInfoSeqNo);
	$('#mlgInfSgmtKey').val(InBasInfoSeqNo);
	$('#incInfSgmtKey').val(InBasInfoSeqNo);
}

function ininButton(){
	var reportFlag = $('#reportflag').val();
	if(reportFlag == '1'){
		$('#subTable1').linkbutton('disable')
		$('#subTable2').linkbutton('disable')
		$('#subTable3').linkbutton('disable')
		$('#subTable4').linkbutton('disable')
		$('#subTable5').linkbutton('disable')
		$('#subTable6').linkbutton('disable')
		$('#subTable7').linkbutton('disable')
		$('#subTable8').linkbutton('disable')
	}else{
		$('#updSubTable1').linkbutton('disable')
		$('#updSubTable2').linkbutton('disable')
		$('#updSubTable3').linkbutton('disable')
		$('#updSubTable4').linkbutton('disable')
		$('#updSubTable5').linkbutton('disable')
		$('#updSubTable6').linkbutton('disable')
		$('#updSubTable7').linkbutton('disable')
		$('#updSubTable8').linkbutton('disable')
	}
}
//
function loadForm(id) {
	// 得到相应段的主键值
	if (id == "idSgmt") {
		loadTable(id);
	} else {
		var infoKey = $('#' + id + 'Key').val();
		// 首字母转大写
		var url = firstToUpper(id);
		$.ajax({
			url : path + '/InBasInfo/get' + url + 'Info.html',
			type : "post",
			data : {
				// 主键
				"infoKey" : infoKey
			},
			async : false,
			success : function(data) {
				// 加载id对应的列表信息信息
				if(id=='bsSgmt'){
					$('#' + id + 'Form').form({onLoadSuccess:function(){
						/*初始化各个提交按钮*/
						ininButton();
					}});

				}
				$('#' + id + 'Form').form('load', data);
				$('#SOURCECUSTID').textbox('setValue',$('#bsSgmtKey').val().substr(0,$('#bsSgmtKey').val().length-11));
				
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

		url : path + '/InBasInfo/get' + url + 'Info.html',
		remoteSort : false,
		idField : 'IDSGMTLISTSEQNO',
		queryParams : {
			'idSgmtSeqNo' : infoKey
		},
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'IDSGMTSEQNO',
			title : '主键1',
			width : '25%',
			align : 'center',
			hidden : 'true'
		}, {
			field : 'IDSGMTLISTSEQNO',
			title : '主键2',
			width : '25%',
			align : 'center',
			hidden : 'true'
		}, {
			field : 'ALIAS',
			title : '姓名',
			width : '25%',
			align : 'center'
		}, {
			field : 'OTHIDTYPE',
			title : '证件类型',
			width : '25%',
			align : 'center'
		}, {
			field : 'OTHIDNUM',
			title : '证件号码',
			width : '25%',
			align : 'center'
		}, {
			field : 'MODIFY',
			title : '操作',
			width : '25%',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.IDSGMTSEQNO;
				var seqno = row.IDSGMTLISTSEQNO;
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
function submitformFcsInfSgmt(sgmtId,updateType,tableName) {
	//检验修改类型
	/*if(!checkUpdateType(updateType,rptdate)) return false;*/
	if(checkData(tableName)==false){
		return ;
	};
	$('#' + sgmtId + 'Form').form('submit', {
		url : path + "/InBasInfo/update" + firstToUpper(sgmtId) + ".html?updateType="+updateType,
		success : function(res) {
			var res=$.parseJSON(res);
			//res = JSON.parse(res);
			if (res.RET_CODE == "SUCCESS") {
				$.messager.alert('成功', '提交成功!', 'info');
				showDetial(sgmtId);
				reflashTab("../jsp/nat/modify/InBasInfo.jsp", "个人基本信息");
				parent.$('#center_tab').tabs('select', "个人基本信息修改详细");
			} else {
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
}

//跟正
function correctformFcsInfSgmt(sgmtId) {
	$('#' + sgmtId + 'Form').form('submit', {
		url : path + "/InBasInfo/correct" + firstToUpper(sgmtId) + ".html",
		success : function(res) {
			res = JSON.parse(res);
			if (res.RET_CODE == "SUCCESS") {
				$.messager.alert('成功', '提交成功!', 'info');
				showDetial(sgmtId);
				reflashTab("../jsp/nat/modify/InBasInfo.jsp", "个人基本信息");
			} else {
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
}

function infoHide() {
	$('#fcsInfSgmt').hide();
	$('#bsSgmt').hide();
	$('#idSgmt').hide();
	$('#spsInfSgmt').hide();
	$('#eduInfSgmt').hide();
	$('#octpnInfSgmt').hide();
	$('#redncInfSgmt').hide();
	$('#mlgInfSgmt').hide();
	$('#incInfSgmt').hide();
}

function openDialog(seqno, id) {
	$('#dialog-idSgmt').dialog('open');
	var url = path + "/InBasInfo/getIDRec.html?IDSgmtListSeqNo=" + seqno + "&IDSgmtSeqNo=" + id;
	// console.log(url);
	$('#idSgmtForm').form('load', url);
}
// 下拉框代码表
function setinitCombobox() {
	initComboboxWH('SexCode', 'SEX',132,100);
	initCombobox('IsChinese', 'NATION');
	initComboboxWH('Dist', 'HHDIST',132,200);
	initComboboxWH('InIDType', 'IDTYPE',132,150);
	initCombobox('InBasInfoRptDate', 'RPTDATECODE');
	initComboboxWH('CustomerType', 'CUSTOMERTYPE',132,150);
	initCombobox('InIDType', 'OTHIDTYPE');
	initComboboxWH('MariStatus', 'MARISTATUS',132,100);
	initComboboxWH('InIDType', 'SPOIDTYPE',132,150);
	initComboboxWH('EduLevel', 'EDULEVEL',132,100);
	initComboboxWH('AcaDegree', 'ACADEGREE',132,100);
	initComboboxWH('EmpStatus', 'EMPSTATUS',132,100);
	initComboboxWH('CpnType', 'CPNTYPE',132,100);
	initComboboxWH('NatEcoIndCla', 'INDUSTRY',132,200);
	initComboboxWH('Dist', 'CPNDIST',132,200);
	initComboboxWH('OctpnType', 'OCCUPATION',132,200);
	initComboboxWH('Title', 'TITLE',132,100);
	initCombobox('TechTitle', 'TECHTITLE');
	initCombobox('ResiStatus', 'RESISTATUS');
	initCombobox('Dist', 'RESIDIST');
	initCombobox('Dist', 'MAILDIST');

}
/*function checkData(tableName){
	
	//提交前校验
	$('#'+tableName+' :input[checkId]').each(function(){
		alert(1);
		var val = $(this).val();
		var array=$(this).attr('checkId').split('_');
		var htmlMsg=$(this).parent().prev().html().replace(/:|&nbsp;/,'');
		console.log(array);
		if(array[1]!='AM'){
			if(val==null || val==''){
				//return true;
			}else{
				var errorMsg;
				if((errorMsg=checkRules(array[0],val,htmlMsg))!=null){
					$.messager.alert('错误', errorMsg, 'error');
					return false;
				}
			}
		}else{
			var errorMsg;
			if((errorMsg=checkRules(array[0],val,htmlMsg))!=null){
				$.messager.alert('错误', errorMsg, 'error');
				return false;
			}
		}
			
	});
	return true;
}
function checkRules(rule,value,msg){
	//ANC100
	var reg1=/(^ANC)(\d+$)/;
	//ANC..100
	var reg2=/(^ANC..)(\d+$)/;
	//N100
	var reg3=/(^N)(\d+$)/;
	//N..100
	var reg4=/(^N..)(\d+$)/;
	//AN100
	var reg5=/(^AN)(\d+$)/;
	//AN..100
	var reg6=/(^AN..)(\d+$)/;
	//uInt..5
	var reg7=/(^uInt..)(\d+$)/;
	//Int..5
	var reg8=/(^Int..)(\d+$)/;
	//Float(2,5)
	var reg9=/(^Float\()(\d+)(,)(\d+)(\)$)/;
	var r;
	if((r =rule.match(reg1))!=null){
		//console.log(r+"--"+reg1);
		if(value.length==r[2]){
			return ;
		}
		return "\""+msg+"\"栏位内容长度应该为"+r[2];
		
	}
	if((r =rule.match(reg2))!=null){
		//console.log(r+"--"+reg2);
		if(value.length<=r[2]){
			return ;
		}
		return "\""+msg+"\"栏位内容长度应该小于等于"+r[2];
	}
	if((r =rule.match(reg3))!=null){
		//console.log(r+"--"+reg3);
		var regNumber=/^[1-9]\d*$/;
		if(value.length==r[2] && value.match(regNumber)!=null){
			return ;
		}
		return "\""+msg+"\"栏位内容长度应该等于"+r[2]+",并且为数字！";
		
	}
	if((r =rule.match(reg4))!=null){
		//console.log(r+"--"+reg4);
		var regNumber=/^[1-9]\d*$/;
		if(value.length<=r[2] && value.match(regNumber)!=null){
			return ;
		}
		return "\""+msg+"\"栏位内容长度应该小于等于"+r[2]+",并且为数字！";
	}
	if((r =rule.match(reg5))!=null){
		//console.log(r+"--"+reg5);
		var regAN=/^[a-zA-Z0-9]*$/;
		if(value.length==r[2] && value.match(regAN)!=null){
			return ;
		}
		return "\""+msg+"\"栏位内容长度应该等于"+r[2]+",并且为数字和英文字母！";
	}
	if((r =rule.match(reg6))!=null){
		var regAN=/^[a-zA-Z0-9]*$/;
		if(value.length<=r[2] && value.match(regAN)!=null){
			return ;
		}
		return "\""+msg+"\"栏位内容长度应该小于等于"+r[2]+",并且为数字和英文字母！";
	}
	if((r =rule.match(reg7))!=null){
		//console.log(r+"--"+reg7);
		var regNumber=/^[1-9]\d*$/;
		if(value.match(regNumber)!=null &&value<=pow(10,r[2],1)&&value>=0){
			return ;
		}
		return   "\""+msg+"\"栏位内容取值应该在0到"+pow(10,r[2],1)+"之间";
	}
	if((r =rule.match(reg8))!=null){
		//console.log(r+"--"+reg8);
		var regNumber=/^-?[1-9]\d*$/;
		if(value.match(regNumber)!=null &&value<=pow(10,r[2],1)&&value>=pow(10,r[2],-1)){
			return ;
		}
		return  "\""+msg+"\"栏位内容取值应该在"+pow(10,r[2],-1)+"到"+pow(10,r[2],1)+"之间";
	}
	if((r =rule.match(reg9))!=null){
		var regFloat=new RegExp('^-?[1-9]\\d*\\.[0-9]{'+r[4]+'}$','g');
		if(value.match(regFloat)!=null &&value<pow(10,r[2],1)&&value>pow(10,r[2],-1)){
			return ;
		}
		return  "\""+msg+"\"栏位内容取值应该在"+pow(10,r[2],-1)+"到"+pow(10,r[2],1)+"之间,并且保留小数点后"+r[4]+"位！";
	}
}
function pow(a,b,c){
	var inta=parseInt(a);
	var intb=parseInt(b);
	var result=1;
	for(var i=0;i<intb;i++){
		result=result*inta;
	}
	return c*result;
}*/  
//id,beforeYear,afterYear
//给年份下拉框初始化，从当前年份往下推60年
function InitYearCombobox(){
	$('#WORKSTARTDATE').combobox({
		valueField:'year',
		textField:'year',
		panelHeight:'150'
	});
	//创建年度数组
	var data=[];
	var thisYear = new Date().getFullYear();
	var startYear=thisYear;
	var endYear = thisYear-60;
	for(;startYear>=endYear;startYear--){
		data.push({"year":startYear});
	}
	$('#WORKSTARTDATE').combobox("loadData",data);
}

