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

	
	$('#IncAndExpStBsSgmtTable').datagrid({
		striped : true,
		url : path + '/IncAndExpSta/listPage.html',
		remoteSort : false,
		idField : 'INCOMEANDESSEQNO',
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
			field : 'SOURCECUSTID',
			title : '客户号',
			width : '15%',
			align : 'center',
			formatter : function(value, row, index) {
				var str;
				var seqNo = row.INCOMEANDESBSSGMTSEQNO;
				if(seqNo != null && seqNo!=undefined){
					var tmp = seqNo.replace(row.SOURCESYS,"");
					str = tmp.substr(0,tmp.length-15);
				}
				return str;
			}
		}, {
			field : 'ENTNAME',
			title : '企业名称',
			width : '15%',
			align : 'center'
		}, {
			field : 'ENTCERTTYPE',
			title : '企业身份标识类型',
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
			field : 'ENTCERTNUM',
			title : '企业身份标识号码',
			width : '10%',
			align : 'center'
		}, {
			field : 'SHEETYEAR',
			title : '报表年份',
			width : '10%',
			align : 'center'
		}, {
			field : 'CIMOC',
			title : '信息维护机构',
			width : '15%',
			align : 'center'
		},{
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
		} , {
			field : 'status',
			title : '操作',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				var url = row.INCOMEANDESSEQNO.replace(/\\/g,
						"/");
				var str ='<a href="javascript:void(0)" onClick=modify(\"'
					+encodeURI(url)+ '\",\"'+row.RPTDATE+'\",1)>修改</a>';
				//if(row.AFTERSEQNO){
					if(row.ISDEL =='1'){
						return '';
						
					}else{
						return str;
					}
					
/*				}else{
					return str;
				}*/
				
				
			
			}
		}, {
			field : 'AFTERSEQNO',
			title : '是否已删除',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				var url = row.INCOMEANDESSEQNO.replace(/\\/g,
				"/");
		var str ='<a href="javascript:void(0)" onClick=del(\"'
			+encodeURI(url)+ '\",1)>删除</a>';
		//if(row.AFTERSEQNO){
			if(row.ISDEL =='1'){
				return '已删除';
				
			}else{
				return str;
			}
				
				
			}
		}  ] ],
		onDblClickRow : function(index, row) {
			// alert(index);
			// alert(row.USERID);
			//openUpdateDialog(row.userid);
			var url = row.INCOMEANDESSEQNO.replace(/\\/g,
			"/");
			modify(encodeURI(url),3);
		}
	});
	// 设置分页控件
	var p = $('#IncAndExpStBsSgmtTable').datagrid('getPager');
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

function modify(url,rptdate,mode){
	openSubTab('../jsp/ent/modify/IncAndExpStaDetial.jsp?id='+url+"&mode="+mode+"&rptdate="+rptdate,'事业单位收入支出表信息修改详细');
}

//条件查询
function doQuery(){
	
	$('#IncAndExpStBsSgmtTable').datagrid('load',getQueryParam());
	
}

function getQueryParam() {
	var queryObject = new Object();
	var enbasinfseqno=getQueryString('id');
	if(enbasinfseqno){
		queryObject.INCOMEANDESSEQNO=enbasinfseqno;
	}
	queryObject.queryRptDate = $('#queryRptDate').datebox('getValue');
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


function del(url){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
		if(r){
			var param = {};
			param.IncomeAndESSeqNo = url;
			$.ajax({
				url : path + '/IncAndExpSta/updateByIsDel.html',
				type : "post",
				data:param,
				async : false,
				success : function(data) {
					if(data.RET_CODE == 'SUCCESS'){
						$.messager.alert('成功', '删除成功!', 'info');
						$('#IncAndExpStBsSgmtTable').datagrid('load',getQueryParam());
					}else{
						$.messager.alert('错误', '删除失败!', 'error');
					}
				}		
			});
		}
	})

	
}