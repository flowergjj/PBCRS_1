var path = getAppPath();
var enbasinfseqno=getQueryString('id');
var mod = getQueryString('mod');
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
	//hideDetial();
	setinitCombobox();
	initDialog();
	if(mod=="3"){
		$('input,select,textarea').attr('readonly',true);
		$('input,select,textarea').attr('disabled','disabled');
	}
	//console.log(enbasinfseqno);
	if(msg!=""&&msg!="undefined"&&msg!=null){
		$("#msg").html(msg);
	}
	getFeedBack(bussType,msg,fbCode);
	showDetial('d2');
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

function ininButton(){
	var reportFlag = $('#REPORTFLAG').textbox('getValue');
	console.log(reportFlag)
	if(reportFlag == '1'){
		$('#subFcsinfId').linkbutton('disable')
		$('#subBaseId').linkbutton('disable')
		$('#subMnmId').linkbutton('disable')
		$('#subCotainfId').linkbutton('disable')
	}else{
		$('#updSubFcsinfId').linkbutton('disable')
		$('#updSubBaseId').linkbutton('disable')
		$('#updSubMnmId').linkbutton('disable')
		$('#updSubCotainfId').linkbutton('disable')
	}
}

function showDetial(id){
	hideDetial();
	$('#'+id).show();
	
	if(id=='d1'){
		var url =path+"/EnBasInfBsSgmt/getEnbasinffcssgmt.html?ENBASINFSEQNO="+enbasinfseqno;
		//console.log(url);

		$('#formFcsInfSgmt').form('load',url);


	}
	if(id=='d2'){

		var url =path+"/EnBasInfBsSgmt/getEnBasInfBsSgmt.html?ENBASINFSEQNO="+enbasinfseqno;
		//console.log(url);
		$('#formEnBasInfBsSgmt').form('load',url);
		$('#formEnBasInfBsSgmt').form({
			onLoadSuccess:function(){
				/*初始化各个提交按钮*/
				ininButton();
			}
		})
	}
	if(id=="d3"){
		loadidTable();
		
	}
	if(id=="d4"){
		loadMnMmbInfTable();
		
	}
	if(id=="d5"){
		loadMnShaHodInfSgmtTable();
		
	}
	if(id=="d6"){
		loadActuCotrlInfSgmtTable();
		
		
		
	}
	
	
	if(id=='d7'){

		var url =path+"/EnBasInfBsSgmt/getSpvAthInfSgmt.html?ENBASINFSEQNO="+enbasinfseqno;
		//console.log(url);
		$('#formSpvAthInfSgmt').form('load',url);
		
	}
	
	if(id=='d8'){

		var url =path+"/EnBasInfBsSgmt/getCotaInfSgmt.html?ENBASINFSEQNO="+enbasinfseqno;
		//console.log(url);
		$('#formCotaInfSgmt').form('load',url);
		
	}
}

function loadidTable(){
	$('#idTable').datagrid({
		striped : true,
		url : path + '/EnBasInfBsSgmt/listPageid.html',
		remoteSort : false,
		idField : 'IDSgmtListSeqNo',
		queryParams : getQueryParamid(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'IDSGMTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'IDSGMTLISTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'OTHENTCERTTYPE',
			title : '企业身份标识类型',
			width : '160',
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
			field : 'OTHENTCERTNUM',
			title : '企业身份标识号码',
			width : '160',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.IDSGMTLISTSEQNO;
				var seqno = row.IDSGMTSEQNO;
				return '<a href="javascript:void(0)" onClick=modifyid("'+seqno+'","'+
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
	var p = $('#idTable').datagrid('getPager');
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


function loadMnMmbInfTable(){
	$('#MnMmbInfTable').datagrid({
		striped : true,
		url : path + '/EnBasInfBsSgmt/listPageMnMmbInfTable.html',
		remoteSort : false,
		idField : 'MNMMBINFSGMTSEQNO',
		queryParams : getQueryParamMnMmb(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'MNMMBINFSGMTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'MMBINFSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'MMBALIAS',
			title : '主要组成人员姓名',
			width : '160',
			align : 'center'
		}, {
			field : 'MMBIDTYPE',
			title : '主要组成人员证件类型',
			width : '160',
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
			field : 'MMBIDNUM',
			title : '主要组成人员证件号码',
			width : '160',
			align : 'center'
		}, {
			field : 'MMBPSTN',
			title : '主要组成人员职位',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='1') return '法定代表人/非法人组织负责人';
				if( value=='2') return '总经理/主要负责人';
				if( value=='3') return '财务负责人';
				if( value=='4') return '董事长/执行董事';
				if( value=='5') return '监事长/执行监事';
				if( value=='9') return '其他董事、监事及高级管理人员';


			}
		},{
			field : 'status',
			title : '操作',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.MNMMBINFSGMTSEQNO;
				var seqno = row.MMBINFSEQNO;
				return '<a href="javascript:void(0)" onClick=modifyMnMmbInf("'+seqno+'","'+
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
}
function modifyMnMmbInf(seqno,id){

	$('#dialog-MnMmbmodify').dialog('open');
	var url =path+"/EnBasInfBsSgmt/getMnMmbInf.html?MmbInfSeqNo="+seqno+"&MnMmbInfSgmtSeqNo="+id;
	//console.log(url);
	$('#dialog-MnMmbmodifyform').form('load',url);
	//弹框修改标记反馈字段
	dialog();
}


function loadMnShaHodInfSgmtTable(){
	$('#MnShaHodInfSgmtTable').datagrid({
		striped : true,
		url : path + '/EnBasInfBsSgmt/listPageMnShaHodInfSgmtTable.html',
		remoteSort : false,
		idField : 'SHARHODINFSEQNO',
		queryParams : getQueryParamMnShaHodInfSgmt(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'SHARHODINFSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'MNSHAHODINFSGMTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'SHARHODTYPE',
			title : '出资人类型',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='10') return '股东';
				if( value=='21') return '普通合伙人';
				if( value=='22') return '有限合伙人';
				if( value=='30') return '个体工商户参与经营者';

			}
		}, {
			field : 'SHARHODCERTTYPE',
			title : '出资人身份类别',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='1') return '自然人';
				if( value=='2') return '组织机构';


			}
		}, {
			field : 'SHARHODNAME',
			title : '出资人姓名/名称',
			width : '160',
			align : 'center'
		}, {
			field : 'SHARHODIDTYPE',
			title : '出资人身份标识类型',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.SHARHODCERTTYPE=="1"){
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
			field : 'SHARHODIDNUM',
			title : '出资人身份标识号码',
			width : '160',
			align : 'center'
		}, {
			field : 'INVRATIO',
			title : '出资比例',
			width : '160',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.SHARHODINFSEQNO;
				var seqno = row.MNSHAHODINFSGMTSEQNO;
				return '<a href="javascript:void(0)" onClick=modifyMnShaHodInfSgmt("'+seqno+'","'+
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
	var p = $('#MnShaHodInfSgmtTable').datagrid('getPager');
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
function modifyMnShaHodInfSgmt(seqno,id){
	$('#dialog-MnShaHodInfSgmtmodify').dialog('open');
	var url =path+"/EnBasInfBsSgmt/getMnShaHodInf.html?MnShaHodInfSgmtSeqNo="+seqno+"&SharHodInfSeqNo="+id;
	//console.log(url);
	$('#dialog-MnShaHodInfSgmtmodifyform').form('load',url);
	
}

function loadActuCotrlInfSgmtTable(){
	$('#ActuCotrlInfSgmtTable').datagrid({
		striped : true,
		url : path + '/EnBasInfBsSgmt/listPageloadActuCotrlInfSgmtTable.html',
		remoteSort : false,
		idField : 'ACTUCOTRLINFSEQNO',
		queryParams : getQueryParamActuCotrlInfSgmt(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'ACTUCOTRLINFSGMTSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'ACTUCOTRLINFSEQNO',
			width : '160',
			hidden : 'true'
		}, {
			field : 'ACTUCOTRLCERTTYPE',
			title : '实际控制人身份类别',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='1') return '自然人';
				if( value=='2') return '组织机构';


			}
		}, {
			field : 'ACTUCOTRLNAME',
			title : '实际控制人姓名/名称',
			width : '160',
			align : 'center'
		}, {
			field : 'ACTUCOTRLIDTYPE',
			title : '实际控制人证件类型',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.ACTUCOTRLCERTTYPE=="1"){
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
			field : 'ACTUCOTRLIDNUM',
			title : '实际控制人证件号码',
			width : '160',
			align : 'center'
		},{
			field : 'status',
			title : '操作',
			width : '160',
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.ACTUCOTRLINFSGMTSEQNO;
				var seqno = row.ACTUCOTRLINFSEQNO;
				return '<a href="javascript:void(0)" onClick=modifyActuCotrlInfSgmt("'+seqno+'","'+
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
	var p = $('#ActuCotrlInfSgmtTable').datagrid('getPager');
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


function modifyActuCotrlInfSgmt(seqno,id){
	$('#dialog-ActuCotrlInfSgmtmodify').dialog('open');
	var url =path+"/EnBasInfBsSgmt/getActuCotrlInf.html?ActuCotrlInfSeqNo="+seqno+"&ActuCotrlInfSgmtSeqNo="+id;
	//console.log(url);
	$('#dialog-ActuCotrlInfSgmtmodifyform').form({onLoadSuccess:function(data){
		var certType=$('#ActuCotrlCertType').combobox('getValue');
		var idType =$('#ActuCotrlIDType').combobox('getValue');
		
		if(certType=="1"){
			initCombobox('InIDType','ActuCotrlIDType');//两种 类型
					
		}
		else{
			initCombobox('EntCertTypeNew','ActuCotrlIDType');
		}
		$('#ActuCotrlIDType').combobox('setValue',idType);	
	}});
	$('#dialog-ActuCotrlInfSgmtmodifyform').form('load',url);
	
	
}
function modifyMnShaHodInfSgmt(seqno,id){
	$('#dialog-MnShaHodInfSgmtmodify').dialog('open');
	var url =path+"/EnBasInfBsSgmt/getMnShaHodInf.html?MnShaHodInfSgmtSeqNo="+seqno+"&SharHodInfSeqNo="+id;
	//console.log(url);
	$('#dialog-MnShaHodInfSgmtmodifyform').form({onLoadSuccess:function(data){
		var certType=$('#SharHodCertType').combobox('getValue');
		var idType =$('#SharHodIDType').combobox('getValue');
		
		if(certType=="1"){
			initCombobox('InIDType','SharHodIDType');//两种 类型
					
		}
		else{
			initCombobox('EntCertTypeNew','SharHodIDType');
		}
		$('#SharHodIDType').combobox('setValue',idType);	
	}});
	
	$('#dialog-MnShaHodInfSgmtmodifyform').form('load',url);
	
		
	
}


function modifyMnMmbInf(seqno,id){
	$('#dialog-MnMmbmodify').dialog('open');
	var url =path+"/EnBasInfBsSgmt/getMnMmbInf.html?MmbInfSeqNo="+seqno+"&MnMmbInfSgmtSeqNo="+id;
	//console.log(url);
	$('#dialog-MnMmbmodifyform').form('load',url);
}


function modifyid(seqno,id){
	$('#dialog-idmodify').dialog('open');
	var url =path+"/EnBasInfBsSgmt/getIdsSgmt.html?IDSgmtSeqNo="+seqno+"&IDSgmtListSeqNo="+id;
	//console.log(url);
	$('#dialog-idmodifyform').form('load',url);
}

var initDialog = function() {
	$('#dialog-idmodify').dialog({
		'onBeforeClose': function() {
			$('#dialog-idmodifyform').form('clear');
		}
	});
	
	$('#dialog-MnMmbmodify').dialog({
		'onBeforeClose': function() {
			$('#dialog-MnMmbmodifyform').form('clear');
		}
	});
	
	
	$('#dialog-MnShaHodInfSgmtmodify').dialog({
		'onBeforeClose': function() {
			$('#dialog-MnShaHodInfSgmtmodifyform').form('clear');
		}
	});
	
	
	$('#dialog-ActuCotrlInfSgmtmodify').dialog({
		'onBeforeClose': function() {
			$('#dialog-ActuCotrlInfSgmtmodifyform').form('clear');
		}
	});

	
	
};

function submitformActuCotrlInfSgmt(type){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	$('#dialog-ActuCotrlInfSgmtmodifyform').form('submit',{
		url:path+"/EnBasInfBsSgmt/updatectuCotrlInfSgmt.html",
		onSubmit:function(param){

			param.BsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				
				$('#dialog-ActuCotrlInfSgmtmodify').dialog('close');
				showDetial('d6');
				reflashTab("../jsp/ent/modify/EnBasInf.jsp","企业基本信息");
				parent.$('#center_tab').tabs('select', "企业基本信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
	
}

function submitformidsInfSgmt(type){
	if(!checkUpdateType(type,rptdate)){
		return false;
	} 
	$('#dialog-idmodifyform').form('submit',{
		url:path+"/EnBasInfBsSgmt/updateIdsSgmt.html",
		onSubmit:function(param){

			param.BsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				$('#dialog-idmodify').dialog('close');
				showDetial('d3');
				reflashTab("../jsp/ent/modify/EnBasInf.jsp","企业基本信息");
				parent.$('#center_tab').tabs('select', "企业基本信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
	
}

function getQueryParamActuCotrlInfSgmt(){
	var queryObject = new Object();
	queryObject.ENBASINFSEQNO=enbasinfseqno;
	queryObject.ActuCotrlName=$('#queryActuCotrlName').val();
	queryObject.ActuCotrlIDNum=$('#queryActuCotrlIDNum').val();
	return queryObject;
}

function getQueryParamid(){
	var queryObject = new Object();
	queryObject.ENBASINFSEQNO=enbasinfseqno;
	queryObject.EntCertNum=$('#queryEntCertNum').val();
	return queryObject;
}

function submitformMnMmbInfSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#dialog-MnMmbmodifyform').form('submit',{
		url:path+"/EnBasInfBsSgmt/updateMnMmbInf.html",
		onSubmit:function(param){

			param.BsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				$('#dialog-MnMmbmodify').dialog('close');
				showDetial('d4');
				reflashTab("../jsp/ent/modify/EnBasInf.jsp","企业基本信息");
				parent.$('#center_tab').tabs('select', "企业基本信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error:function(){
			MaskUtil.unmask();
		}
	});
	
}

function getQueryParamMnMmb(){
	var queryObject = new Object();
	queryObject.ENBASINFSEQNO=enbasinfseqno;
	queryObject.MmbAlias=$('#queryMmbAlias').val();
	queryObject.MmbIDNum=$('#queryMmbIDNum').val();
	return queryObject;
}

function submitformMnShaHodInfSgmt(type){
	if(!checkUpdateType(type,rptdate)){
		return false;
	} 
	$('#dialog-MnShaHodInfSgmtmodifyform').form('submit',{
		url:path+"/EnBasInfBsSgmt/updateMnShaHodInf.html",
		onSubmit:function(param){
			param.BsSgmtSeqNo=enbasinfseqno;
			param.type = type

		},
		success:function(res){
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				$('#dialog-MnShaHodInfSgmtmodify').dialog('close');
				showDetial('d5');
				reflashTab("../jsp/ent/modify/EnBasInf.jsp","企业基本信息");
				parent.$('#center_tab').tabs('select', "企业基本信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
	
}

function getQueryParamMnShaHodInfSgmt(){
	var queryObject = new Object();
	queryObject.ENBASINFSEQNO=enbasinfseqno;
	queryObject.SharHodName=$('#querySharHodName').val();
	queryObject.SharHodIDNum=$('#querySharHodIDNum').val();
	return queryObject;
}


function submitformFcsInfSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formFcsInfSgmt').form('submit',{
		url:path+"/EnBasInfBsSgmt/updateEnbasinffcssgmt.html?ENBASINFSEQNO="+enbasinfseqno,
		onSubmit:function(param){
			param.BsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
			
				showDetial('d1');
				reflashTab("../jsp/ent/modify/EnBasInf.jsp","企业基本信息");
				parent.$('#center_tab').tabs('select', "企业基本信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
}

function submitformEnBasInfBsSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formEnBasInfBsSgmt').form('submit',{
		url:path+"/EnBasInfBsSgmt/updateEnBasInfBsSgmt.html?ENBASINFSEQNO="+enbasinfseqno,
		onSubmit:function(param){
			param.BsSgmtSeqNo=enbasinfseqno;
			param.type = type;
			/*var t =$('#formEnBasInfBsSgmt').serializeArray();
			var ParamValue={};
			$.each(t,function(){
				ParamValue[this.name]=this.value;
			});
			//console.log(ParamValue.NATIONALITY);
			param.EntName=ParamValue.ENTNAME;
			param.EntCertType=ParamValue.ENTCERTTYPE;
			param.EntCertNum=ParamValue.ENTCERTNUM;
			param.InfSurcCode=ParamValue.INFSURCCODE;
			param.RptDate=ParamValue.RPTDATE;
			param.RptDateCode=ParamValue.RPTDATECODE;
			param.Cimoc=ParamValue.CIMOC;
			param.CustomerType=ParamValue.CUSTOMERTYPE;
			param.EtpSts=ParamValue.ETPSTS;
			param.OrgType=ParamValue.ORGTYPE;*/


		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				showDetial('d2');
				reflashTab("../jsp/ent/modify/EnBasInf.jsp","企业基本信息");
				parent.$('#center_tab').tabs('select', "企业基本信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
}


function submitformSpvAthInfSgmt(type){
	if(!checkUpdateType(type,rptdate)){
		return false;
	} 
	
	$('#formSpvAthInfSgmt').form('submit',{
		url:path+"/EnBasInfBsSgmt/updateSpvAthInfSgmt.html?ENBASINFSEQNO="+enbasinfseqno,
		onSubmit:function(param){
			param.BsSgmtSeqNo=enbasinfseqno;
			param.type = type;
		},
		success:function(res){
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				showDetial('d7');
				reflashTab("../jsp/ent/modify/EnBasInf.jsp","企业基本信息");
				parent.$('#center_tab').tabs('select', "企业基本信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		}
	});
}

function submitformCotaInfSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		return;
	}
	$('#formCotaInfSgmt').form('submit',{
		url:path+"/EnBasInfBsSgmt/updateCotaInfSgmt.html?ENBASINFSEQNO="+enbasinfseqno,
		onSubmit:function(param){
			param.BsSgmtSeqNo=enbasinfseqno;
			param.type = type;

		},
		success:function(res){
			MaskUtil.unmask();
			res =JSON.parse(res);
			//console.log(res);
			if(res.RET_CODE=="SUCCESS"){
				$.messager.alert('成功', '提交成功!', 'info');
				showDetial('d8');
				reflashTab("../jsp/ent/modify/EnBasInf.jsp","企业基本信息");
				parent.$('#center_tab').tabs('select', "企业基本信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
}




function setinitCombobox(){
	initCombobox('Dist','ADMDIVOFREG');//登记地址区划代码
	initCombobox('Dist','ConAddDistrictCode');//联系地址区划代码
	initCombobox('NatEcoIndCla','ECOINDUSCATE');
	initCombobox('EcoType','ECOTYPE');
	initCombobox('EntScale','ENTSCALE');
	initCombobox('EntCertTypeNew','EntCertType');
	initCombobox('EnCustomerType','CUSTOMERTYPE');
	initCombobox('EtpSts','ETPSTS');
	initCombobox('OrgType','ORGTYPE');
	initCombobox('EntCertTypeNew','OthEntCertType');
	initCombobox('SupOrgType','SUPORGTYPE');
	initCombobox('EntCertTypeNew','SUPORGCERTTYPE');
	initCombobox('Dist','CONADDDISTRICTCODE');
	
	initCombobox('InIDType','MmbIDType');
	initCombobox('MmbPstn','MmbPstn');
	initCombobox('SharHodCertType','SharHodType');
	$('#SharHodType').combobox({
		onChange:function(n,o){
			if(n=="1"){
				initCombobox('InIDType','SharHodIDType');//两种 类型
			}
			else{
				initCombobox('EntCertTypeNew','SharHodIDType');
			}
		}
		
	});
	initCombobox('SharHodCertType','SharHodCertType');
	initCombobox('InIDType','SharHodIDType');//两种 类型
	initCombobox('SharHodCertType','ActuCotrlCertType');
	$('#ActuCotrlCertType').combobox({
		onChange:function(n,o){
			if(n=="1"){
				initCombobox('InIDType','ActuCotrlIDType');//两种 类型
			}
			else{
				initCombobox('EntCertTypeNew','ActuCotrlIDType');
			}
		}
		
	});
	
	

}


