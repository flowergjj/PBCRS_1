var path = getAppPath();
var enbasinfseqno=getQueryString('id');
var mode = getQueryString('mode');
//栏位
var msg = getQueryString('msg');
//报文类型
var bussType = getQueryString('bussType');
//反馈编码
var fbCode = getQueryString('fbCode');
//信息报告日期
var rptdate = getQueryString("rptdate");
var lazy = false;
$(function() {
	
	$('body').css('visibility', 'visible');
	//showDetial('d1');
	setinitCombobox();
	initBindEvent();	
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
});

function hideDetial(){
	$('#d1').hide();
	$('#d2').hide();
	$('#d3').hide();
	$('#d4').hide();
	$('#d5').hide();
	$('#d6').hide();

}

function showDetial(id){
	hideDetial();
	
	if(id=='d1'){
		var url='';
		if(mode!="2")
			url =path+"/EnSecAcctInf/getEnguaracctbssgmt.html?EnSecAcctInfSeqNo="+enbasinfseqno;
		
		if(mode=="2")
			url =path+"/EnSecAcctInf/getEnguaracctbssgmtm.html?EnSecAcctInfSeqNo="+enbasinfseqno;
		//console.log(url);
		$('#formEnGuarAcctBsSgmt').form('load',url);
		var seqNo = enbasinfseqno.replace("MIS","");
		seqNo = seqNo.replace("SLNENT","");
		seqNo = seqNo.replace("CRMS","");
		$("#codes").textbox('setValue',seqNo.substr(0,seqNo.length-8));
		
	}
	if(id=='d2'){
		var url='';
		if(mode!="2")
			url =path+"/EnSecAcctInf/getEnguaaccbsinfsgmt.html?EnSecAcctInfSeqNo="+enbasinfseqno;
		
		if(mode=="2")
			url =path+"/EnSecAcctInf/getEnguaaccbsinfsgmtm.html?EnSecAcctInfSeqNo="+enbasinfseqno;
		//console.log(url);
		$('#formEnGuaAccBsInfSgmt').form('load',url);
		//var paragraphDel = $('#paragraphDel2').val();
		
		$('#formEnGuaAccBsInfSgmt').form({onLoadSuccess:function(data){
			
			//data=JSON2.parse(data);
			if(data.paragraphDel == '1'){
				disabled(id);
			}
		}});
		
		
	}
	if(id=="d3"){
		loadEnRltRepInfSgmtTable();
		
	}
	if(id=="d4"){
		loadMotCltCtrInfSgmtTable();
		
	}
	if(id=="d5"){
		var url='';
		if(mode!="2")
			url =path+"/EnSecAcctInf/getEnacctcredsgmt.html?EnSecAcctInfSeqNo="+enbasinfseqno;
		if(mode=="2")
			url =path+"/EnSecAcctInf/getEnacctcredsgmtm.html?EnSecAcctInfSeqNo="+enbasinfseqno;
		//console.log(url);
		$('#formGuarAcctCredSgmt').form('load',url);
		
		
	}
	if(id=="d6"){
		var url='';
		if(mode!="2")
			url =path+"/EnSecAcctInf/getEnoricreinfsgmt.html?EnSecAcctInfSeqNo="+enbasinfseqno;
		if(mode=="2")
			url =path+"/EnSecAcctInf/getEnoricreinfsgmtm.html?EnSecAcctInfSeqNo="+enbasinfseqno;
		//console.log(url);
		$('#formGuaRltRepInfSgmt').form('load',url);
		$('#formGuaRltRepInfSgmt').form({onLoadSuccess:function(data){
			
			//data=JSON2.parse(data);
			if(data.paragraphDel == '1'){
				disabled(id);
			}
		}});
		
	}
	$('#'+id).show();
	
}

function disabled(id){
	 $('#'+id+' a.easyui-linkbutton').linkbutton('disable');
	/*$.each(aList,function(index,value){
		aList[index].linkbutton('disabled','true');
	});*/
}

function loadEnRltRepInfSgmtTable(){
	var url ='';
	if(mode=='1')
		url=path + '/EnSecAcctInf/listEnrltrepinfsgmtPage.html';
	if(mode=='2')
		url=path + '/EnSecAcctInf/listEnrltrepinfsgmtPage.html';
	$('#GseRltRepInfSgmtTable').datagrid({
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
			field : 'INFOIDTYPE',
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
				if(row.INFOIDTYPE=="1"){
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
				if( value=='1') return '共同债务人';
				if( value=='2') return '反担保人';
				if( value=='9') return '其他';

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
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='0') return '担任保证/多人分保';
				if( value=='2') return '联保';

			}
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
				var infoIdTyep = row.INFOIDTYPE;
				return '<a href="javascript:void(0)" onClick=modifyEnRltRepInfSgmt("'+seqno+'","'+
						id+'","'+infoIdTyep+ '")>修改</a>';
			}
		} ] ],
		onDblClickRow : function(index, row) {
			// alert(index);
			// alert(row.USERID);
			//openUpdateDialog(row.userid);
		}
	});
	// 设置分页控件
	var p = $('#GseRltRepInfSgmtTable').datagrid('getPager');
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
	if(mode=='1')
		url=path + '/EnSecAcctInf/listMotCltCtrInfSgmtPage.html';
	if(mode=='2')
		url=path + '/EnSecAcctInf/listMotCltCtrInfSgmtPagem.html';
	$('#GuarMotCltCtrInfSgmtTable').datagrid({
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
			field : 'CCCINFSEQNO',
			width : '160',
			hidden : 'true'
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
				return '<a href="javascript:void(0)" onClick=modifymot("' + seqno + '")>修改</a>';
				/*var id = row.CCCINFSEQNO;
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
	var p = $('#GuarMotCltCtrInfSgmtTable').datagrid('getPager');
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


function modifymot(index){
	openSubTab(path + '/jsp/nat/modify/MotCltCtrInfDetail.jsp?id=' + index+"&mode=1", '抵质押物信息修改详细');
	
}


function modifyMotCltCtrInfSgmt(seqno,id){
	/*$('#dialog-MotCltCtrInfSgmtmodify').dialog('open');
	var url =path+"/EnSecAcctInf/getCccinf.html?MotgaCltalCtrctInfSgmtSeqNo="+seqno+"&CccInfSeqNo="+id;
	//console.log(url);
	$('#dialog-MotCltCtrInfSgmtmodifyform').form('load',url);*/
}



function modifyEnRltRepInfSgmt(seqno,id,infoIdType){
	$('#dialog-GseRltRepInfSgmtmodify').dialog('open');
	var url =path+"/EnSecAcctInf/getEnrltrepymtinf.html?RltRepymtInfSgmtSeqNo="+seqno+"&RltRepymtInfSeqNo="+id;
	if(infoIdType == "1"){
		initCombobox('InIDType','ArlpCertType');//责任人身份标识类型
	}
	if(infoIdType == "2"){
		initCombobox('EntCertTypeNew','ArlpCertType');//责任人身份标识类型
	}
	//console.log(url);
	$('#dialog-GseRltRepInfSgmtmodifyform').form('load',url);
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

function submitformEnGuarAcctBsSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formEnGuarAcctBsSgmt').form('submit',{
		url:path+"/EnSecAcctInf/updateEnAcctBsSgmt.html",
		onSubmit:function(param){

			param.GuarAcctBsSgmtSeqNo=enbasinfseqno;
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
				reflashTab("../jsp/ent/modify/EnSecAcctInf.jsp","企业担保账户信息");
				parent.$('#center_tab').tabs('select', "企业担保账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformEnGuaAccBsInfSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formEnGuaAccBsInfSgmt').form('submit',{
		url:path+"/EnSecAcctInf/updateEnAcctBsInfSgmt.html",
		onSubmit:function(param){

			param.GuarAcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type ;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-ActuCotrlInfSgmtmodify').dialog('close');
				showDetial('d2');
				reflashTab("../jsp/ent/modify/EnSecAcctInf.jsp","企业担保账户信息");
				parent.$('#center_tab').tabs('select', "企业担保账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformGseRltRepInfSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#dialog-GseRltRepInfSgmtmodifyform').form('submit',{
		url:path+"/EnSecAcctInf/updateEnrltrepymtinf.html",
		onSubmit:function(param){

			param.GuarAcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type ;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-ActuCotrlInfSgmtmodify').dialog('close');
				showDetial('d3');
				reflashTab("../jsp/ent/modify/EnSecAcctInf.jsp","企业担保账户信息");
				parent.$('#center_tab').tabs('select', "企业担保账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformGuarAcctCredSgmt(type,tableId){
	if(!checkUpdateType(type,rptdate)){
		return false;
	} 
	if(checkData(tableId) == false){
		return;
	}
	$('#formGuarAcctCredSgmt').form('submit',{
		url:path+"/EnSecAcctInf/updateGuaracctcredsgmt.html",
		onSubmit:function(param){

			param.GuarAcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				$('#dialog-EnRltRepInfSgmtmodify').dialog('close');
				showDetial('d5');
				reflashTab("../jsp/ent/modify/EnSecAcctInf.jsp","企业担保账户信息");
				parent.$('#center_tab').tabs('select', "企业担保账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
	
}
/*
function submitformMotCltCtrInfSgmt(){
	$('#dialog-MotCltCtrInfSgmtmodifyform').form('submit',{
		url:path+"/EnSecAcctInf/updateCccinf.html",
		onSubmit:function(param){

			param.EnSecAcctInfSeqNo=enbasinfseqno;

		},
		success:function(res){
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				$('#dialog-MotCltCtrInfSgmtmodify').dialog('close');
				showDetial('d4');
				reflashTab("../jsp/ent/modify/EnSecAcctInf.jsp","企业担保账户信息");
				parent.$('#center_tab').tabs('select', "企业担保账户信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
	
}*/

function submitformGuaRltRepInfSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formGuaRltRepInfSgmt').form('submit',{
		url:path+"/EnSecAcctInf/updateGuarltrepinfsgmt.html",
		onSubmit:function(param){

			param.GuarAcctBsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-MotCltCtrInfSgmtmodify').dialog('close');
				showDetial('d6');
				reflashTab("../jsp/ent/modify/EnSecAcctInf.jsp","企业担保账户信息");
				parent.$('#center_tab').tabs('select', "企业担保账户信息修改详细");
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
	queryObject.EnSecAcctInfSeqNo=enbasinfseqno;
	//queryObject.EntCertNum=$('#queryEntCertNum').val();
	return queryObject;
}





function setinitCombobox(){
	initCombobox('EnGuarAcctType','AcctType');//账户类型
	initCombobox('EntCertTypeNew','IDType');//债务人身份标识类型
	initCombobox('EnGuarAcctBusiLines','BusiLines');//担保大类
	initCombobox('EnGuaAccBusDtiLines','BusiDtilLines');//担保细类
	initCombobox('Cy','Cy');//币种
	initCombobox('EnGuarAcctGuarMode','GuarMode');//反担保方式
	initCombobox('EnOthRepyGuarWay','OthRepyGuarWay');//其他还款保证方式
	
	
	
	initCombobox('IDType','InfoIDType');//身份类别

	$('#InfoIDType').combobox({
		onChange:function(n,o){
			if(n=="1"){
				initCombobox('InIDType','ArlpCertType');//责任人身份标识类型
			}
			if(n=="2"){
				initCombobox('EntCertTypeNew','ArlpCertType');//责任人身份标识类型
			}
		}
	});
	
	initCombobox('EnGuaAccRltRepType','ArlpType');//还款责任人类型
	initCombobox('EnAcctMdfcWartySign','WartySign');//联保标志
	//initCombobox('EnAcctMdfcOriDbtCate','OrigDbtCate');
	initCombobox('EnGuarMdfcStatus','AcctStatus');//账户状态
	//initCombobox('InitRpySts','InitRpySts');
	initCombobox('FiveCate','FiveCate');//五级分类
	//initCombobox('RpmtType','RpmtType');
	//initCombobox('CagOfTrdTypeEnAccMdf','ChanTranType');
	initCombobox('CompAdvFlag','CompAdvFlag');//代偿垫款
	
	
	
	
	
	

}

function initBindEvent(){
	$('#InfoIDType').combobox({
		onLoadSuccess: function(){
			var INFOIDTYPE= $('#InfoIDType').combobox('getValue');
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

function byParagraphDelete(sgmtId, tableName){
	
	
	
	$.messager.confirm('确认对话框', '您想要删除该段吗？', function(r){
		if (r){
			$.ajax({
				url:path+"/EnSecAcctInf/delete"+tableName+".html?EnSecAcctInfSeqNo="+enbasinfseqno+"&startDate="+$('#dd').datebox('getValue'),
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
