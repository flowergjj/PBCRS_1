var path = getAppPath();
var enbasinfseqno=getQueryString('id');
var mode = getQueryString('mode');
var msg = getQueryString('msg');
//栏位
var msg = getQueryString('msg');
//报文类型
var bussType = getQueryString('bussType');
//反馈编码
var fbCode = getQueryString('fbCode');
//信息报告日期
var rptdate = getQueryString("rptdate");
var lazy = false;
var bsSgmt;//基础段
$(function() {
	getEnAcctBsSgmt();
	$('body').css('visibility', 'visible');
	//showDetial('d1');
	initBindEvent();
	setinitCombobox();
	initDialog();
	//console.log(enbasinfseqno);
	if(mode=="3"){
		$('input,select,textarea').attr('readonly',true);
		$('input,select,textarea').attr('disabled','disabled');
		$('[iconcls=icon-save]').each(function(idx,el){

			$(el).html('');
		});
	}
	if(msg!=""&&msg!="undefined"&&msg!=null){
		$("#msg").html(msg);
	}
	getFeedBack(bussType,msg,fbCode);
	showDetial('d1')
});

function hideDetial(){
	$('#d1').hide();
	$('#d2').hide();
	$('#d3').hide();
	$('#d4').hide();
	$('#d5').hide();
	$('#d6').hide();
	$('#d7').hide();
	$('#d8').hide();
}

function getEnAcctBsSgmt(){
	var url = path+"/EnAcctInf/getEnAcctBsSgmt.html?EnAcctInfSeqNo="+enbasinfseqno;
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			bsSgmt = data;
			//console.info(bsSgmt);
			if(bsSgmt.acctCode != null || bsSgmt.acctCode != undefined){
				$("#codes").textbox('setValue',bsSgmt.acctCode.substr(14,bsSgmt.acctCode.length));
			}
			
		}		
	});
}

function ininButton(){
 	var reportFlag = $('#reportFlag').val();
	if(reportFlag == '1'){
		$('#subAcctId').linkbutton('disable')
		$('#subSgmtId').linkbutton('disable')
		$('#subEltrepId').linkbutton('disable')
		$('#subMotCltCtrInf').linkbutton('disable')
		$('#subActId').linkbutton('disable')
		$('#subSpeId').linkbutton('disable')
	}else{
		$('#updSubAcctId').linkbutton('disable')
		$('#d2EnAcctBsInfSgmt').linkbutton('disable')
		$('#updSubSgmtId').linkbutton('disable')
		$('#updSubEltrepId').linkbutton('disable')
		$('#updSubMotCltCtrInf').linkbutton('disable')
		$('#d7ActLbltyInfSgmt').linkbutton('disable')
		$('#updSubActId').linkbutton('disable')
		$('#d8EnAccSpeTrsSgmtm').linkbutton('disable')
		$('#updSubSpeId').linkbutton('disable')
	}

}

function showDetial(id){
	hideDetial();
	$('#'+id).show();
	if(id=='d1'){
		var url='';

			url =path+"/EnAcctInf/getEnAcctBsSgmt.html?EnAcctInfSeqNo="+enbasinfseqno;

		$('#formEnAcctBsSgmt').form('load',url);
		$('#formEnAcctBsSgmt').form({
			onLoadSuccess:function(){
				/*初始化各个提交按钮*/
				ininButton();
			}
		})
		
	}
	if(id=='d2'){
		openDialogComRecInfSgmt(enbasinfseqno);
/*		var url='';
	
			url =path+"/EnAcctInf/getEnacctbsinfsgmt.html?EnAcctInfSeqNo="+enbasinfseqno;
		
	
		//console.log(url);
		$('#formEnAcctBsInfSgmt').form('load',url);*/
		
	}
	if(id=="d3"){
		loadEnRltRepInfSgmtTable();
		
	}
	if(id=="d4"){
		loadMotCltCtrInfSgmtTable();
		
	}
	if(id=="d5"){
		var url='';
		
			url =path+"/EnAcctInf/getEnacctcredsgmt.html?EnAcctInfSeqNo="+enbasinfseqno;
		
		//console.log(url);
		$('#formEnAcctCredSgmt').form('load',url);
		
		
	}
	if(id=="d6"){
		var url='';
		
			url =path+"/EnAcctInf/getEnoricreinfsgmt.html?EnAcctInfSeqNo="+enbasinfseqno;
		
		//console.log(url);
		$('#formEnOriCreInfSgmt').form('load',url);
		
		
	}
	
	
	if(id=='d7'){
		var url='';
		
			url =path+"/EnAcctInf/getActlbltyinfsgmt.html?EnAcctInfSeqNo="+enbasinfseqno;
		
		//console.log(url);
		$('#formActLbltyInfSgmt').form('load',url);
		
		$('#formActLbltyInfSgmt').form({onLoadSuccess:function(data){
			
		
			if(data.paragraphDel == '1'){
				disabled(id);
			}
		}});
		
	}
	
	if(id=='d8'){
		loadEnAccSpeTrsSgmtTable();
		
	}
}
function disabled(id){
	 $('#'+id+' a.easyui-linkbutton').linkbutton('disable');
	/*$.each(aList,function(index,value){
		aList[index].linkbutton('disabled','true');
	});*/
}



function loadEnRltRepInfSgmtTable(){
	var url ='';
	//if(mode=='1')
		url=path + '/EnAcctInf/listEnrltrepinfsgmtPage.html';
	/*if(mode=='2')
		url=path + '/EnAcctInf/listEnrltrepinfsgmtPagem.html';*/
	$('#EnRltRepInfSgmtTable').datagrid({
		striped : true,
		url : url,
		remoteSort : false,
		idField : 'RLTREPYMTINFSEQNO',
		queryParams : getQueryParamid(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'RLTREPYMTINFSGMTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'RLTREPYMTINFSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'ARLPIDTYPE',
			title : '身份类别',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='1') return '自然人';
				if( value=='2') return '组织机构';


			}
		}, {
			field : 'ARLPNAME',
			title : '责任人名称',
			width : '160',
			align : 'center'
		}, {
			field : 'ARLPCERTTYPE',
			title : '责任人身份标识类型',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.ARLPIDTYPE=="1"){
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
				}else{
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
			}
		}, {
			field : 'ARLPCERTNUM',
			title : '责任人身份标识号码',
			width : '160',
			align : 'center'
		}, {
			field : 'ARLPTYPE',
			title : '还款责任人类型',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='10') return '股东';
				if( value=='21') return '普通合伙人';
				if( value=='22') return '有限合伙人';
				if( value=='30') return '个体工商户参与经营者';

			}
		}, {
			field : 'ARLPAMT',
			title : '还款责任金额',
			width : '160',
			align : 'center'
		}, {
			field : 'WARTYSIGN',
			title : '联保标志',
			width : '160',
			align : 'center'
		}, {
			field : 'MAXGUARMCC',
			title : '保证合同编号',
			width : '160',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
			
				var seqno = row.RLTREPYMTINFSGMTSEQNO;
				var id = row.RLTREPYMTINFSEQNO;
				return '<a href="javascript:void(0)" onClick=modifyEnRltRepInfSgmt("'+seqno+'","'+
						id+ '")>修改</a>';
			}
		} ] ],
		onDblClickRow : function(index, row) {
			// alert(index);
			// alert(row.USERID);
			//openUpdateDialog(row.userid);
		}
	});
	// 设置分页控件
	var p = $('#EnRltRepInfSgmtTable').datagrid('getPager');
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


function loadMotCltCtrInfSgmtTable(){
	var url ='';
	//if(mode=='1')
		url=path + '/EnAcctInf/listMotCltCtrInfSgmtPage.html';
	/*if(mode=='2')
		url=path + '/EnAcctInf/listMotCltCtrInfSgmtPagem.html';*/
	$('#MotCltCtrInfSgmtTable').datagrid({
		striped : true,
		url : url,
		remoteSort : false,
		idField : 'MOTGACLTALCTRCTINFSGMTSEQNO',
		queryParams : getQueryParamid(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'MOTGACLTALCTRCTINFSGMTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'MOTGACLTALCTRCTINFSEQNO',
			title : '主键2',
			width : '20%',
			align : 'center'
		}, {
			field : 'CCC',
			title : '抵（质）押合同标识码',
			width : '160',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				var seqno= row.MOTGACLTALCTRCTINFSGMTSEQNO;
				//抵质押主键
				var id = row.MOTGACLTALCTRCTINFSEQNO;
				return '<a href="javascript:void(0)" onClick=modifymot("'+ id+ '","' + seqno + '")>修改</a>';
			/*	var id = row.CCCINFSEQNO;
				return '<a href="javascript:void(0)" onClick=modifyMotCltCtrInfSgmt("'+seqno+'","'+
						id+ '")>修改</a>';*/
				
			}
		} ] ],
		onDblClickRow : function(index, row) {
			// alert(index);
			// alert(row.USERID);
			//openUpdateDialog(row.userid);
		}
	});
	// 设置分页控件
	var p = $('#MotCltCtrInfSgmtTable').datagrid('getPager');
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

function modifymot(id,loadId){
	openSubTab(path + '/jsp/nat/modify/MotCltCtrInfDetail.jsp?id=' + id +'&businessKey='+loadId+ "&type=MO", '抵质押物信息修改详细');
	
}


function loadEnAccSpeTrsSgmtTable(){
	var url ='';
	//if(mode=='1')
		url=path + '/EnAcctInf/listEnaccspetrssgmtPage.html';
/*	if(mode=='2')
		url=path + '/EnAcctInf/listEnaccspetrssgmtPagem.html';
	*/
	$('#EnAccSpeTrsSgmtTable').datagrid({
		striped : true,
		url : url,
		remoteSort : false,
		idField : 'ACCTSPECTRSTDSPNSGMTSEQNO',
		queryParams : getQueryParamid(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'ACCTSPECTRSTDSPNSGMTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'CAGOFTRDINFSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'CHANTRANTYPE',
			title : '交易类型',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='11') return '展期';
				if( value=='12') return '提前还款';
				if( value=='21') return '核损核销';

			}
		}, {
			field : 'TRANDATE',
			title : '交易日期',
			width : '160',
			align : 'center'
		}
		, {
			field : 'TRANAMT',
			title : '交易金额',
			width : '160',
			align : 'center'
		}
		, {
			field : 'DUETRANMON',
			title : '到期日期变更月数',
			width : '160',
			align : 'center'
		}, {
			field : 'DETINFO',
			title : '交易明细信息',
			width : '160',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				var seqno = row.ACCTSPECTRSTDSPNSGMTSEQNO;
				var  id= row.CAGOFTRDINFSEQNO;
				return '<a href="javascript:void(0)" onClick=modifyEnAccSpeTrsSgmt("'+seqno+'","'+
						id+ '")>修改</a>';
			}
		} ] ],
		onDblClickRow : function(index, row) {
			// alert(index);
			// alert(row.USERID);
			//openUpdateDialog(row.userid);
		}
	});
	// 设置分页控件
	var p = $('#EnAccSpeTrsSgmtTable').datagrid('getPager');
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

function modifyEnAccSpeTrsSgmt(seqno,id){
	$('#dialog-EnAccSpeTrsSgmtmodify').dialog('open');
	var url =path+"/EnAcctInf/getEncagoftrdinf.html?AcctSpecTrstDspnSgmtSeqNo="+seqno+"&CagOfTrdInfSeqNo="+id;
	//console.log(url);
	$('#dialog-EnAccSpeTrsSgmtmodifyform').form('load',url);
	$('#dialog-EnAccSpeTrsSgmtmodifyform').form({onLoadSuccess:function(data){
		
		//data=JSON2.parse(data);
		if(data.paragraphDel == '1'){
			
			disabled("dialog-EnAccSpeTrsSgmtmodifyform");
		}
	}});
	//弹框修改标记反馈字段
	dialog();
}

function modifyMotCltCtrInfSgmt(seqno,id){
	$('#dialog-MotCltCtrInfSgmtmodify').dialog('open');
	var url =path+"/EnAcctInf/getCccinf.html?MotgaCltalCtrctInfSgmtSeqNo="+seqno+"&CccInfSeqNo="+id;
	//console.log(url);
	$('#dialog-MotCltCtrInfSgmtmodifyform').form('load',url);
}



function modifyEnRltRepInfSgmt(seqno,id){
	$('#dialog-EnRltRepInfSgmtmodify').dialog('open');
	var url =path+"/EnAcctInf/getEnrltrepymtinf.html?RltRepymtInfSgmtSeqNo="+seqno+"&RltRepymtInfSeqNo="+id;
	//console.log(url);
	$('#dialog-EnRltRepInfSgmtmodifyform').form('load',url);
	
	//弹框修改标记反馈字段
	dialog();
}

var initDialog = function() {
	$('#dialog-EnAccSpeTrsSgmtmodify').dialog({
		'onBeforeClose': function() {
			$('#dialog-EnAccSpeTrsSgmtmodifyform').form('clear');
		}
	});
	
	$('#dialog-MotCltCtrInfSgmtmodify').dialog({
		'onBeforeClose': function() {
			$('#dialog-MotCltCtrInfSgmtmodifyform').form('clear');
		}
	});
	
	
	$('#dialog-MotCltCtrInfSgmtmodify').dialog({
		'onBeforeClose': function() {
			$('#dialog-MotCltCtrInfSgmtmodifyform').form('clear');
		}
	});
	
	

	
	
};

function submitformEnAcctBsSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formEnAcctBsSgmt').form('submit',{
		url:path+"/EnAcctInf/updateEnAcctBsSgmt.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-ActuCotrlInfSgmtmodify').dialog('close');
				showDetial('d1');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformEnAcctBsInfSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formEnAcctBsInfSgmt').form('submit',{
		url:path+"/EnAcctInf/updateEnAcctBsInfSgmt.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-ActuCotrlInfSgmtmodify').dialog('close');
				showDetial('d2');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

/*function submitformEnAcctBsInfSgmt(type,tableId){
	if(checkData(tableId) == false){
		return;
	}
	$('#formEnAcctBsInfSgmt').form('submit',{
		url:path+"/EnAcctInf/updateEnAcctBsInfSgmt.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-ActuCotrlInfSgmtmodify').dialog('close');
				showDetial('d2');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
	
}*/

function submitformEnRltRepInf(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#dialog-EnRltRepInfSgmtmodifyform').form('submit',{
		url:path+"/EnAcctInf/updateEnrltrepymtinf.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				$('#dialog-EnRltRepInfSgmtmodify').dialog('close');
				showDetial('d3');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformMotCltCtrInfSgmt(type){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	$('#dialog-MotCltCtrInfSgmtmodifyform').form('submit',{
		url:path+"/EnAcctInf/updateCccinf.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				$('#dialog-MotCltCtrInfSgmtmodify').dialog('close');
				showDetial('d4');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformEnAcctCredSgmt(type){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	$('#formEnAcctCredSgmt').form('submit',{
		url:path+"/EnAcctInf/updateEnacctcredsgmt.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-MotCltCtrInfSgmtmodify').dialog('close');
				showDetial('d5');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformEnOriCreInfSgmt(type){
	if(!checkUpdateType(type,rptdate)){
		return false;
	} 
	$('#formEnOriCreInfSgmt').form('submit',{
		url:path+"/EnAcctInf/updateEnoricreinfsgmt.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-MotCltCtrInfSgmtmodify').dialog('close');
				showDetial('d6');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
	
}

function submitformActLbltyInfSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formActLbltyInfSgmt').form('submit',{
		url:path+"/EnAcctInf/updateActlbltyinfsgmt.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-MotCltCtrInfSgmtmodify').dialog('close');
				showDetial('d7');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitEnAccSpeTrsSgmtmodifyform(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	/*if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} */
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#dialog-EnAccSpeTrsSgmtmodifyform').form('submit',{
		url:path+"/EnAcctInf/updateEncagoftrdinf.html",
		onSubmit:function(param){

			param.AcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				$('#dialog-EnAccSpeTrsSgmtmodify').dialog('close');
				showDetial('d8');
				reflashTab("../jsp/ent/modify/EnAcctInf.jsp","企业借贷交易账户信息");
				parent.$('#center_tab').tabs('select', "企业借贷交易账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}






function getQueryParamid(){
	var queryObject = new Object();
	queryObject.EnAcctInfSeqNo=enbasinfseqno;
	//queryObject.EntCertNum=$('#queryEntCertNum').val();
	return queryObject;
}





function setinitCombobox(){
	initCombobox('EnAcctMdfcType','AcctType');
	initCombobox('EntCertTypeNew','IDType');
	initCombobox('BusiLines','BusiLines');
	$('#BusiLines').combobox({
		onChange:function(n,o){
			if(n=="10"){
				initCombobox('EnAccBusDtiLi_EntBon','BusiDtlLines');//两种 类型
			}
			if(n=="11"){
				initCombobox('EnAccBusDtiLi_Loan','BusiDtlLines');
			}
			if(n=="12"){
				initCombobox('EnAccBusDtiLi_TraFin','BusiDtlLines');
			}
			if(n=="13"){
				initCombobox('EnAccBusDtiLi_FacFin','BusiDtlLines');
			}
			if(n=="14"){
				initCombobox('EnAccBusDtiLi_FinLea','BusiDtlLines');
			}
			if(n=="15"){
				initCombobox('EnAccBusDtiLi_SecFin','BusiDtlLines');
			}
			if(n=="16"){
				initCombobox('EnAccBusDtiLi_Overdr','BusiDtlLines');
			}
			
			if(n=="21"){
				initCombobox('EnAccBusDtiLi_BilDis','BusiDtlLines');
			}
			
			if(n=="31"){
				initCombobox('EnAccBusDtiLi_GolLea','BusiDtlLines');
			}
			if(n=="41"){
				initCombobox('EnAccBusDtiLi_Advanc','BusiDtlLines');
			}
			if(n=="51"){
				initCombobox('EnAccBusDtiLi_AssDis','BusiDtlLines');
			}
		}
		
	});
	
	initCombobox('Cy','Cy');
	initCombobox('Flag','Flag');
	initCombobox('EnAcctRepayMode','RepayMode');
	initCombobox('EnAccMdfRepFreqcy','RepayFreqcy');
	initCombobox('Dist','ApplyBusiDist');
	initCombobox('EnAcctMdfcGuarMode','GuarMode');
	initCombobox('EnAccMdfOthRepGuaWay','OthRepyGuarWay');
	initCombobox('EnAccMdfLoaTimLimCat','LoanTimeLimCat');
	
	initCombobox('EnLoanForm','LoaFrm');
	initCombobox('NatEcoIndCla','ActInvest');
	initCombobox('EnAcctMdfcFundSou','FundSou');
	initCombobox('AssetTrandFlag','AssetTrandFlag');
	
	initCombobox('IDType','ArlpIDType');
	$('#ArlpIDType').combobox({
		onChange:function(n,o){
			if(n=="1"){
				initCombobox('InIDType','ArlpCertType');
			}
			if(n=="2"){
				initCombobox('EntCertTypeNew','ArlpCertType');
			}
		}
	});
	
	initCombobox('EnAcctMdfcRltRepType','ArlpType');
	initCombobox('EnAcctMdfcWartySign','WartySign');
	initCombobox('EnAcctMdfcOriDbtCate','OrigDbtCate');
	initCombobox('EnAcctMdfcStatus','AcctStatus');
	initCombobox('InitRpySts','InitRpySts');
	initCombobox('FiveCate','FiveCate');
	initCombobox('RpmtType','RpmtType');
	initCombobox('CagOfTrdTypeEnAccMdf','ChanTranType');
	
	
	
	
	

}
function initCombx(n){
	if(n=="10"){
		initCombobox('EnAccBusDtiLi_EntBon','BusiDtlLines');//两种 类型
	}
	if(n=="11"){
		initCombobox('EnAccBusDtiLi_Loan','BusiDtlLines');
	}
	if(n=="12"){
		initCombobox('EnAccBusDtiLi_TraFin','BusiDtlLines');
	}
	if(n=="13"){
		initCombobox('EnAccBusDtiLi_FacFin','BusiDtlLines');
	}
	if(n=="14"){
		initCombobox('EnAccBusDtiLi_FinLea','BusiDtlLines');
	}
	if(n=="15"){
		initCombobox('EnAccBusDtiLi_SecFin','BusiDtlLines');
	}
	if(n=="16"){
		initCombobox('EnAccBusDtiLi_Overdr','BusiDtlLines');
	}
	
	if(n=="21"){
		initCombobox('EnAccBusDtiLi_BilDis','BusiDtlLines');
	}
	
	if(n=="31"){
		initCombobox('EnAccBusDtiLi_GolLea','BusiDtlLines');
	}
	if(n=="41"){
		initCombobox('EnAccBusDtiLi_Advanc','BusiDtlLines');
	}
	if(n=="51"){
		initCombobox('EnAccBusDtiLi_AssDis','BusiDtlLines');
	}
}

function initBindEvent(){
	$('#ArlpIDType').combobox({
		onLoadSuccess: function(){
			var INFOIDTYPE= $('#ArlpIDType').combobox('getValue');
			if(INFOIDTYPE=='1'){
				initCombobox('InIDType', 'ArlpCertType');
			}else{
				initCombobox('EntCertTypeNew', 'ArlpCertType');
			}
		},
	onSelect:function(record){
			if(record.id=='1'){
				initCombobox('InIDType', 'ArlpCertType');
			}else {
				initCombobox('EntCertTypeNew', 'ArlpCertType');
			}
	}
	});

}

function openDialogComRecInfSgmt(seqno) {
	$('#dialog-comRecInfSgmt').dialog('open');
	// return false;
	var url =path+"/EnAcctInf/getEnacctbsinfsgmt.html?EnAcctInfSeqNo="+enbasinfseqno;
	
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			initCombx(data.busiLines);
			// 加载id对应的列表信息信息
			$('#formEnAcctBsInfSgmt').form('load', data);
			if(data.paragraphDel == '1'){
				disabled("d2");
			}
			dialog();
		}
	});
	
	
	// console.log(url);
	//$('#comRecInfSgmtForm').form('load', url);
	//dialog();
}

function byParagraphDelete(sgmtId, tableName){
	$.messager.confirm('确认对话框', '您想要删除该段吗？', function(r){
		if (r){
			$.ajax({
				url:path+"/EnAcctInf/delete"+tableName+".html?EnAcctInfSeqNo="+enbasinfseqno+"&startDate="+$('#dd').datebox('getValue'),
				type : "get",
				async : false,
				success : function(data) {
					if (data.RET_CODE == "SUCCESS") {
						$.messager.alert('成功', '删除成功!', 'info');
						showDetial(sgmtId);
						//reflashTab("../jsp/nat/modify/InAcctInf.jsp", "个人借贷交易账户信息");
						//parent.$('#center_tab').tabs('select', "个人借贷信息修改详细");
						
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
