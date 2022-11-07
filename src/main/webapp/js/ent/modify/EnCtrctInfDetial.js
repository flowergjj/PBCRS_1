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

}

function showDetial(id){
	hideDetial();
	$('#'+id).show();
	if(id=='d1'){
		var url='';
		if(mode!="2")
			url =path+"/EnCtrctInf/getEnctrctbssgmt.html?EnCtrctInfSeqNo="+enbasinfseqno;
		
		if(mode=="2")
			url =path+"/EnCtrctInf/getEnctrctbssgmtm.html?EnCtrctInfSeqNo="+enbasinfseqno;
		//console.log(url);
		$('#formEnCtrctBsSgmt').form('load',url);
		var seqNo = enbasinfseqno.replace("MIS","");
		seqNo = seqNo.replace("SLNENT","");
		seqNo = seqNo.replace("CRMS","");
		$("#codes").textbox('setValue',seqNo.substr(0,seqNo.length-8));
		
	}
	if(id=='d2'){
		loadEnCtrCerRelSgmtTable();
		
		
	}
	if(id=="d3"){
		var url='';
		if(mode!="2")
			url =path+"/EnCtrctInf/getEncreditlimsgmt.html?EnCtrctInfSeqNo="+enbasinfseqno;
		
		if(mode=="2")
			url =path+"/EnCtrctInf/getEncreditlimsgmtm.html?EnCtrctInfSeqNo="+enbasinfseqno;
		//console.log(url);
		$('#formEnCreditLimSgmt').form('load',url);
		
	}
}

function loadEnCtrCerRelSgmtTable(){
	var url ='';
	if(mode!="2")
		url=path + '/EnCtrctInf/listEnctrcerrelsgmtPage.html';
	if(mode=='2')
		url=path + '/EnCtrctInf/listEnctrcerrelsgmtPagem.html';
	$('#EnCtrCerRelSgmtTable').datagrid({
		striped : true,
		url : url,
		remoteSort : false,
		idField : 'CTRCTCERTRELSEQNO',
		queryParams : getQueryParamid(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'CTRCTCERTRELSGMTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'CTRCTCERTRELSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'BRERTYPE',
			title : '共同受信人身份类别',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='1') return '自然人';
				if( value=='2') return '组织机构';


			}
		}, {
			field : 'CERTRELNAME',
			title : '共同受信人名称',
			width : '160',
			align : 'center'
		}, {
			field : 'CERTRELIDTYPE',
			title : '共同受信人身份标识类型',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.BRERTYPE=="1"){
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
			field : 'CERTRELIDNUM',
			title : '共同受信人身份标识号码',
			width : '160',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
			
				var seqno = row.CTRCTCERTRELSGMTSEQNO;
				var id = row.CTRCTCERTRELSEQNO;
				return '<a href="javascript:void(0)" onClick=modifyEnCtrCerRelSgmt("'+seqno+'","'+
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
	var p = $('#EnCtrCerRelSgmtTable').datagrid('getPager');
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



function modifyEnCtrCerRelSgmt(seqno,id){
	$('#dialog-EnCtrCerRelSgmtmodify').dialog('open');
	var url =path+"/EnCtrctInf/getEnctrctcertrel.html?CtrctCertRelSgmtSeqNo="+seqno+"&CtrctCertRelSeqNo="+id;
	//console.log(url);
	$('#dialog-EnCtrCerRelSgmtmodifyform').form('load',url);
}


var initDialog = function() {
	$('#dialog-EnCtrCerRelSgmtmodify').dialog({
		'onBeforeClose': function() {
			$('#dialog-EnCtrCerRelSgmtmodifyform').form('clear');
		}
	});
	

	
};

function submitEnCtrctBsSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formEnCtrctBsSgmt').form('submit',{
		url:path+"/EnCtrctInf/updateEnctrctbssgmt.html",
		onSubmit:function(param){

			param.EnCtrctInfSeqNo=enbasinfseqno;
			param.type = type ;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-ActuCotrlInfSgmtmodify').dialog('close');
				showDetial('d1');
				reflashTab("../jsp/ent/modify/EnCtrctInf.jsp","企业授信协议信息");
				parent.$('#center_tab').tabs('select', "企业授信协议信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformEnCtrCerRelSgmt(type){
	if(!checkUpdateType(type,rptdate)){
		return false;
	} 
	$('#dialog-EnCtrCerRelSgmtmodifyform').form('submit',{
		url:path+"/EnCtrctInf/updateEnCtrctCertRel.html",
		onSubmit:function(param){

			param.EnCtrctInfSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				$('dialog-EnCtrCerRelSgmtmodify').dialog('close');
				showDetial('d2');
				reflashTab("../jsp/ent/modify/EnCtrctInf.jsp","企业授信协议信息");
				parent.$('#center_tab').tabs('select', "企业授信协议信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
	
}

function submitformEnCreditLimSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formEnCreditLimSgmt').form('submit',{
		url:path+"/EnCtrctInf/updateEncreditlimsgmt.html",
		onSubmit:function(param){

			param.EnCtrctInfSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				//$('#dialog-ActuCotrlInfSgmtmodify').dialog('close');
				showDetial('d3');
				reflashTab("../jsp/ent/modify/EnCtrctInf.jsp","企业授信协议信息");
				parent.$('#center_tab').tabs('select', "企业授信协议信息修改详细");
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
	queryObject.EnCtrctInfSeqNo=enbasinfseqno;
	//queryObject.EntCertNum=$('#queryEntCertNum').val();
	return queryObject;
}





function setinitCombobox(){
	initCombobox('EnCreditLimType','CreditLimType');
	initCombobox('McLimLoopFlag','LimLoopFlg');
	initCombobox('McConStatus','ConStatus');
	initCombobox('IDType','BrerType');
	
	$('#BrerType').combobox({
		onChange:function(n,o){
			if(n=="1"){
				initCombobox('InIDType','CertRelIDType');
			}
			if(n=="2"){
				initCombobox('EntCertTypeNew','CertRelIDType');
			}
		}
	});
	
	initCombobox('EntCertTypeNew','IDType');

	
	
	
	initCombobox('Cy','Cy');
	
	
	
	
	
	
	

}

//按段删除
function deleteform() {
	
	$.messager.confirm('确认','您确认想要删除此段吗？',function(r){
		if(r){
			MaskUtil.mask('正在删除，请稍候……');
	$.ajax({
		url : path + "/EnCtrctInf/deleteform.html",
		type : "post",
		data : {
			// 主键
			"key" : enbasinfseqno,
			startDate : $('#dd').datebox("getValue")
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
	 '<input  id="dd"  type="text" style="width: 100px"></input> '); 
	 $('#dd').datebox({    
		    required:false   
	 });
}

function getCount(){
	$.ajax({
		url : path + "/EnCtrctInf/getCount.html",
		type : "post",
		data : {
			// 主键
			"key" : enbasinfseqno
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
