var path = getAppPath();

var lazy = false;
$(function() {
	
	$('body').css('visibility', 'visible');
	//getOrgData();
	initCombox();
	getentTable();
	var errmsg=getQueryString('msg');
	if(errmsg)
		$('#errormsg').html(errmsg);
});

function doMore(){
	$('#queryMore').show();
}
function initCombox(){
	initCombobox('ENT_REPORT_SYS','querySourceSys');
}
function getOrgData() {
	$.ajax({
		url : path + '/userManage/getOrgList.html',
		type : "post",
		async : false,
		success : function(data) {
			// alert(data.query.length);
			$('#queryOrg').combobox({
				// url:path+'/orgManage/orgList.html',
				data : data.query,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 120
			});
		}		
	});
}

function getentTable() {

	
	$('#EnAcctInfTable').datagrid({
		striped : true,
		url : path + '/EnAcctInf/listPage.html',
		remoteSort : false,
		idField : 'ACCTBSSGMTSEQNO',
		queryParams : getQueryParam(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [ {
			field : 'RPTDATE',
			title : '信息报告日期',
			width : '10%',
			align : 'center'
		}, {
			field : 'NAME',
			title : '借款人名称',
			width : '15%',
			align : 'center'
		}, {
			field : 'ACCTTYPE',
			title : '账户类型',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='D1') return '非循环贷账户';
				if( value=='D2') return '贴现账户';
				if( value=='R1') return '循环贷账户';
				if( value=='R2') return '贷记卡账户 ';
				if( value=='R3') return '准贷记卡账户 ';
				if( value=='R4') return '循环额度下分账户 ';
				if( value=='C1') return '催收账户 ';
				

			}
		}, {
			field : 'ACCTCODE',
			title : '企业账户标识码',
			width : '15%',
			align : 'center'
		}, {
			field : 'CODE',
			title : '借据号',
			width : '15%',
			align : 'center',
			formatter : function(value, row, index) {
				var str;
				if(row.ACCTCODE != null || row.ACCTCODE != undefined){
					str = row.ACCTCODE.substr(14,row.ACCTCODE.length);
				}
				return str;
			}
		}, {
			field : 'IDTYPE',
			title : '借款人身份标识类型',
			width : '10%',
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
			field : 'IDNUM',
			title : '借款人身份标识号码',
			width : '10%',
			align : 'center'
		}, {
			field : 'MNGMTORGCODE',
			title : '业务管理机构代码',
			width : '10%',
			align : 'center'
		}, {
			field : 'SOURCESYS',
			title : '系统',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if( value=='MIS') return '信贷';
				if( value=='SLNENT') return '小微企业';
				if( value=='CB') return '核心';
				if( value=='CRMS') return '新一代MIS';

			}
		} ,{
			field : 'status',
			title : '操作',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				//console.log(index);

				var url = row.ACCTBSSGMTSEQNO.replace(/\\/g,
						"/");
				var str ='<a href="javascript:void(0)" onClick=del(\"'
					+encodeURI(url)+ '\")>删除</a>';
				//if(row.AFTERSEQNO){
					if(row.ISDEL == '1'){
						return '已删除';
						
					}else{
						return str;
					}
/*					
				}else{
					return str;
				}*/
				
				
			
			}
		}/*, {
			field : 'AFTERSEQNO',
			title : '已修改',
			width : '100',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.AFTERSEQNO){
					if(row.AFTERSEQNO!=''){
						var url = row.AFTERSEQNO.replace(/\\/g,
						"/");
	
						return '<a href="javascript:void(0)" onClick=modify(\"'
								+encodeURI(url)+ '\",2)>再修改</a>';
					}else{
						return '';
					}
					
				}else{
					return '';
				}
				
				
			}
		} */ ] ],
		onDblClickRow : function(index, row) {
			var url = row.ACCTBSSGMTSEQNO.replace(/\\/g,
			"/");
			del(encodeURI(url));
		}
	});
	// 设置分页控件
	var p = $('#EnAcctInfTable').datagrid('getPager');
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

function del(url){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
		if(r){
			var param = {};
			param.AcctBsSgmtSeqNo = url;
			$.ajax({
				url : path + '/EnAcctInf/updateByIsDel.html',
				type : "post",
				data:param,
				async : false,
				success : function(data) {
					if(data.RET_CODE == 'SUCCESS'){
						$.messager.alert('成功', '删除成功!', 'info');
						$('#EnAcctInfTable').datagrid('load',getQueryParam());
					}else{
						$.messager.alert('错误', '删除失败!', 'error');
					}
				}		
			});
		}
	})

	
}

//条件查询
function doQuery(){
	
	$('#EnAcctInfTable').datagrid('load',getQueryParam());
	
}

function getQueryParam() {
	var queryObject = new Object();
	queryObject.queryRptDate = $('#queryRptDate').datebox('getValue');
	var enbasinfseqno=getQueryString('id');
	if(enbasinfseqno){
		queryObject.ACCTBSSGMTSEQNO=enbasinfseqno;
	}
	queryObject.queryEntName = $('#queryEntName').val();
	queryObject.queryEntCertNum = $('#queryEntCertNum').val();
	queryObject.querySourceSys = $('#querySourceSys').combobox('getValue');
	//queryObject.queryOrg = $('#queryOrg').combobox('getValue');
	return queryObject;
}

//清除条件
function doClear(){
	$('#queryRptDate').datebox('clear');
	$('#queryEntName').textbox('clear');
	$('#queryEntCertNum').textbox('clear');
	$('#querySourceSys').combobox('clear');
	//$('#queryOrg').combobox('clear');
}
