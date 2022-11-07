var path = getAppPath();

var lazy = false;
$(function() {
	initCombox();
	$('body').css('visibility', 'visible');
	//getOrgData();
	getentTable();
	var errmsg=getQueryString('msg');
	if(errmsg)
		$('#errormsg').html(errmsg);
});

function doMore(){
	$('#queryMore').show();
}
function initCombox(){
	initCombobox('REPORT_SOURCESYS','querySourceSys');
	initCombobox('BusiType','QueryBusiType');
}

var toolbar = [
   			{
   				text:"新增",
   				iconCls: "icon-add",
   				"class": "tooltip-info",
   				handler:function(){
   					openDialog();
   				}
   			},{
   				text:"删除",
   				iconCls: "icon-remove",
   				"class": "tooltip-info",
   				handler:function(){
   					delParam();
   					
   				}
   			}

   		];

function getentTable() {

	
	$('#ParamTable').datagrid({
		striped : true,
		url : path + '/Busilines/listPage.html',
		remoteSort : false,
		//idField : 'TABLEKEY',
		queryParams : getQueryParam(),
		toolbar : toolbar,
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'ck',
			checkbox : true
		},{
			field : 'PRODID',
			title : '借贷产品代码',
			width : '10%',
			align : 'center'
		},{
			field : 'PRODIDDESC',
			title : '借贷产品代码含义',
			width : '20%',
			align : 'center'
		}, {
			field : 'NOGUARFLAG',
			title : '是否信用免担保',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='1') return '是';
				if( value=='0' || value == null) return '否';
			}
		},{
			field : 'IS_SUBMISSION',
			title : '是否报送该借贷业务',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='0' || value == null) return '是';
				if( value=='1') return '否';

			}
		},{
			field : 'SOURCESYS',
			title : '系统',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='CRMS') return '信贷';
				if( value=='PLN') return '个贷';

			}
		}   ] ]
	});
	// 设置分页控件
	var p = $('#ParamTable').datagrid('getPager');
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

/*function modify(url,mode){
	openSubTab('../jsp/ent/modify/BalanceSheetDetial.jsp?id='+url+"&mode="+mode,'企业资产负债表信息修改详细');
}*/

//条件查询
function doQuery(){
	
	$('#ParamTable').datagrid('load',getQueryParam());
	
}

function getQueryParam() {
	var queryObject = new Object();	
	queryObject.QueryBusiType = $('#QueryBusiType').combobox('getValue');
	queryObject.QueryprodId = $('#QueryprodId').val();
	queryObject.QueryprodDesc = $('#QueryprodDesc').val();
	queryObject.querySourceSys = $('#querySourceSys').combobox('getValue');
	return queryObject;
}
//清除条件
function doClear(){
	$('#QueryBusiType').combobox('clear');
	$('#QueryprodId').textbox('clear');
	$('#QueryprodDesc').textbox('clear');
	$('#querySourceSys').combobox('clear');
	//$('#queryOrg').combobox('clear');
}

function clearAddFromData() {
	$('#addForm').form('clear');
}

function openDialog() {
	clearAddFromData();
	setinitCombobox();
	$("#addDlg").window('open');
}

//角色删除
function delParam() {
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r) {
		if (r) {
			MaskUtil.mask('正在删除，请稍候……');
			var list = $('#ParamTable').datagrid('getSelections');
			//console.info(list);
			if (list.length == 0) {
				MaskUtil.unmask();
				$.messager.alert('错误', '请选择需要删除的角色！', 'error');
				return;
			}
			$.ajax({
				url : path + '/Busilines/delParam.html',
				type : "post",
				data : {
					listdata : list,
					listSize : list.length
				},
				success : function(data) {
					if ('SUCCESS' == data.RET_CODE) {
						$.messager.alert('成功', '删除成功!', 'info');
						MaskUtil.unmask();
						doQuery();
					} else {
						MaskUtil.unmask();
						$.messager.alert('错误', data.RET_MSG, 'error');
					}
				}
			});
			$('#ParamTable').datagrid('clearSelections');
		}
	});

}

function setinitCombobox(){
/*	initCombobox('EnAcctMdfcType','AcctType');
	initCombobox('BusiType','BusiType');*/
	initCombobox('noGuar','noGuarFlag');//是否信用免担保
/*	initCombobox('reportOverRedueFlag','reportOverRedueFlag');//个人是否报逾期*/
	initCombobox('reportOverRedueFlag','isSubmission');//是否生成借贷报送数据
	initCombobox('REPORT_SOURCESYS', 'sourceSys');	
/*	$('#BusiType').combobox({
		onChange:function(n,o){
			var sourceSys = $("#sourceSys").combobox('getValue');
			if(sourceSys != null && sourceSys != ''){
				if(sourceSys == "PLN" || sourceSys == "ILN" || sourceSys == "SLNIND"){
					if(n=="CL"){
						$("#dtl").hide();
						$("#dtl1").hide();
						$("#noGuar").hide();
						$("#noShow").hide();
						initCombobox('InCreditLimType','BusiLines');
					}
					if(n=="DC"){
						$("#dtl").show();
						$("#dtl1").show();
						$("#noGuar").show();
						$("#noShow").show();
						initCombobox('AcctBusiLines','BusiLines');
						initCombobox('AcctBusiDtilLines','BusiDtlLines');
					}
				}else if(sourceSys == "MIS" || sourceSys == "CRMS" || sourceSys == "SLNENT"){
					if(n=="CL"){
						$("#dtl").hide();
						$("#dtl1").hide();
						$("#noGuar").hide();
						$("#noShow").hide();
						initCombobox('EnCreditLimType','BusiLines');
					}
					if(n=="GU"){
						$("#dtl").show();
						$("#dtl1").show();
						$("#noGuar").show();
						$("#noShow").hide();
						initCombobox('EnGuarAcctBusiLines','BusiLines');//担保大类
						initCombobox('EnGuaAccBusDtiLines','BusiDtlLines');//担保细类	
		     		}
					if(n=="DC"){
						$("#dtl").show();
						$("#dtl1").show();
						$("#noGuar").show();
						$("#noShow").show();
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
					}
				}
			}
		}
	});*/

	

}

function doSaveData(){
	MaskUtil.mask('正在提交，请稍候……');
	if (!$('#addForm').form('enableValidation').form('validate')) {
		MaskUtil.unmask();
		return;
	} else {
		$("#addDlg").window('close');
		var postdata = new Object();
		postdata.prodId = $("#prodId").val();
		postdata.prodDesc = $("#prodDesc").val();
		postdata.sourceSys = $("#sourceSys").combobox('getValue');
		postdata.noGuarFlag = $("#noGuarFlag").combobox('getValue');
		postdata.isSubmission = $("#isSubmission").combobox('getValue');
		$.ajax({
			url : path + '/Busilines/saveParam.html',
			type : "post",
			data : postdata,
			success : function(data) {
				if ('SUCCESS' == data.RET_CODE) {
					$.messager.alert('成功', '添加成功!', 'info');
					MaskUtil.unmask();
					// doQuery();
					$("#ParamTable").datagrid('reload');
				} else {
					MaskUtil.unmask();
					$("#addDlg").window('open');
					if(data.RET_MSG == null || data.RET_MSG ==undefined){
						$.messager.alert('错误', "添加失败!", 'error');
					}else{
						$.messager.alert('错误', data.RET_MSG, 'error');
					}

				}
			}
		});
	}
}
