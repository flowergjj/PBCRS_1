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
	//initDialog();
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
	
}

function showDetial(id){
	hideDetial();
	$('#'+id).show();
	if(id=='d1'){
		
		var url =path+"/IncAndExpSta/getIncandexpstbssgmt.html?IncomeAndESSeqNo="+enbasinfseqno;
		//console.log(url);
		$('#formIncAndExpStBsSgmt').form('load',url);
		
	}
	if(id=='d2'){

		var url =path+"/IncAndExpSta/getIncandexpstasgmt.html?IncomeAndESSeqNo="+enbasinfseqno;
		//console.log(url);
		$('#formIncAndExpStaSgmt').form('load',url);
		
	}
	
}

function submitformIncAndExpStBsSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formIncAndExpStBsSgmt').form('submit',{
		url:path+"/IncAndExpSta/updateIncandexpstbssgmt.html",
		onSubmit:function(param){
			param.IncomeAndESSeqNo=enbasinfseqno;
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
				showDetial('d1');
				reflashTab("../jsp/ent/modify/BalanceSheet.jsp","企业资产负债表信息");
				parent.$('#center_tab').tabs('select', "企业资产负债表信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error :function(){
			MaskUtil.unmask();
		}
	});
}

function submitformIncAndExpStaSgmt(type,tableId){
	MaskUtil.mask('执行中，请稍候……');
	if(!checkUpdateType(type,rptdate)){
		MaskUtil.unmask();
		return false;
	} 
	if(checkData(tableId) == false){
		MaskUtil.unmask();
		return;
	}
	$('#formIncAndExpStaSgmt').form('submit',{
		url:path+"/IncAndExpSta/upIncandexpstasgmt.html",
		onSubmit:function(param){
			param.IncomeAndESSeqNo=enbasinfseqno;
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
				reflashTab("../jsp/ent/modify/BalanceSheet.jsp","企业资产负债表信息");
				parent.$('#center_tab').tabs('select', "企业资产负债表信息修改详细");
			}else{
				$.messager.alert('错误', '提交失败!', 'error');
			}
		},error : function(){
			MaskUtil.unmask();
		}
	});
}


function setinitCombobox(){

	initCombobox('EntCertTypeNew','EntCertType');
	initCombobox('CorFinStaType','SheetType');
	initCombobox('CorFinStaSheTypeDiv','SheetTypeDivide');
	
	

}


