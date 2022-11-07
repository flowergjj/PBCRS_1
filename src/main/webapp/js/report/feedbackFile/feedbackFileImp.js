var path = getAppPath();
var filetype = '.enc';
$(function() {
	// 初始化数据列表
	initTable();
	initBtn();

});
function initTable() {

	$('#DataListTable').datagrid({
		striped : true,
		url : path + '/Receivefile/getReveiceFileList.html',
		remoteSort : false,
		idField : 'SEQNO',
		queryParams : queryParameter(),
		singleSelect : true,// 是否单选
		pagination : true,// 分页控件
		scrollbarSize : 0,
		pageSize : 20,
		pageList : [ 20 ],
		columns : [ [ 
		{
			field : 'BUSSTYPE',
			title : '报文类型',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				return getTabName(row.BUSSTYPE)
			}
			
		},
		{
			field : 'REPORTDATE',
			title : '报送日期',
			width : '6%',
			align : 'center'
		},
		{
			field : 'RECEIVEFILENAME',
			title : '反馈文件名称',
			width : '20%',
			align : 'center',
			formatter : function(value, row, index) {
				if(value != null && value !="undefined"){
					var content = value.substring(value.length-34);
					var str = '<a href="javascript:void(0)" onClick=downFile(\"' + value.replace(/\\/g,'/') + '\")>'+content+'</a>';
					return str;
				}
				return '';


			}
			
		}, {
			field : 'SUCCESSCOUNT',
			title : '成功笔数',
			width : '5%',
			align : 'center'
		}, {
			field : 'FAILCOUNT',
			title : '失败笔数',
			width : '5%',
			align : 'center'
		}, {
			field : 'IMPORTDATE',
			title : '文件导入日期',
			width : '8%',
			align : 'center'
		},
		{
			field : 'IMPORTTIME',
			title : '文件导入时间',
			width : '12%',
			align : 'center'
		},{
			field : 'RESULTERRORREASON',
			title : '解压解密错误消息',
			width : '10%',
			align : 'center'
		}, {
			field : 'VALIDATERESULT',
			title : '解压解密是否成功',
			width : '8%',
			align : 'center',
			formatter : function(value, row, index) {
				if(value=="1") return "成功";
				if(value=="2") return "失败";

			}
		}, {
			field : 'detail',
			title : '操作',
			width : '5%',
			align : 'center',
			formatter : function(value, row, index) {

				var seqno = row.SEQNO;
				var str = '<a href="javascript:void(0)" onClick=showDetail(\"' + seqno + '\")>查看详情</a>';
				return str;

			}
		},{
				field : 'RECEIVETXTFILENAME',
				title : '解析TXT文件下载',
				width : '10%',
				align : 'center',
				formatter : function(value, row, index) {
					if(value == null || value == ''){
						return ;
					}
					var content = value.substring(value.length-42);
					var str = '<a href="javascript:void(0)" onClick=downFile(\"' + value.replace(/\\/g,'/') + '\")>'+"下载"+'</a>';
					return str;

				}
			} ] ]

	});
	// 设置分页控件
	var p = $('#DataListTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
	$('#DataListTable').datagrid('clearSelections');
}


function downFile(filePath){

window.open(path + '/Receivefile/loadFile.html?'
			+ "filePath=" +String.raw `${filePath}`);
			
		
}

function showDetail(seqno) {
	$('#detialTable').datagrid({
		striped : true,
		url : path + '/Receivefile/listPageAll.html?Seqno=' + seqno,
		remoteSort : false,
		idField : 'RECEIVEFILENAME',
		// queryParams : getQueryParam(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
		columns : [ [{
			field : 'FBCODE',
			title : '错误代码',
			width : '15%',
			align : 'center'
		}, {
			field : 'FBMSG',
			title : '错误栏位',
			width : '15%',
			align : 'center'
		}, {
			field : 'MSG_INFO',
			title : '错误信息',
			width : '50%',
			align : 'center'
		}, {
			field : 'status',
			title : '操作',
			width : '15%',
			align : 'center',
			formatter : function(value, row, index) {

				var num = row.REPORTSEQNO;
				var str = '<a href="javascript:void(0)" onClick=modify(\"' + row.BUSSTYPE + '\","' + num + '",\"' + row.MSG_INFO + '\")>修改</a>';

				return str;

			}
		} ] ],
		onDblClickRow : function(index, row) {

		}
	});
	// 设置分页控件
	var p = $('#detialTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 15,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'

	});
	$('#dialog-importDetail').dialog('open');
}

function initBtn() {

	$('#btn_imp').on('click', function(e) {
		e.preventDefault;

		$('#dialog-importALL').dialog('open');
	});

	$('#btn_ok').on('click', function(e) {
		e.preventDefault;
		if (!checkImportParams()) {
			return ;
		}
		$('#dialog-importdform').form('submit', {
			url : path + '/Receivefile/uploadFile.html',
			success : function(data) {
				$('#dialog-fileupload').unmask();
				data = JSON.parse(data);
				if (data.RET_CODE == 'SUCCESS') {
					$.messager.alert('成功', data.RET_MSG, 'info');
					doQuery();
					$('#dialog-importALL').dialog('close');
					$('#dialog-importCheck').dialog('close');

				} else if (data.RET_CODE == 'FAILED') {
					$.messager.alert('错误', data.RET_MSG, 'error');
				}

			}
		});

	});

}

function checkImportParams() {

	var uploadFileTypeFlag = 'none';

	var filepath = $('#stocklistFile').val();
	if (filepath != '') {
		var point = filepath.lastIndexOf(".");
		var type = filepath.substr(point);
		type = type.toLowerCase();
		if (filetype.indexOf(type) < 0) {
			uploadFileTypeFlag = 'bad';

		} else {
			uploadFileTypeFlag = 'good';
		}
	}
	if (uploadFileTypeFlag == 'bad') {
		$.messager.alert('错误', '文件格式不正确，请上传以下类型的文件：' + filetype, 'error');
		return false;
	} else if (uploadFileTypeFlag == 'none') {
		$.messager.alert('错误', '请选择上传文件', 'error');
		return false;
	}

	return true;
}

// 条件查询
function doQuery() {
	
	$('#DataListTable').datagrid('load', queryParameter());

}
// 条件封装
function queryParameter() {
	var parameter = new Object();
	
	parameter.importDate=$('#query_imp_date').datebox('getValue');
	  
	parameter.reportFileName=$('#query_file_name').textbox('getValue');
	 
	return parameter;
}
// 清除条件
function clearQuery() {
	$('#query_imp_date').datebox('clear');
	$('#query_file_name').textbox('clear');
	
}

function getTabName(bussType) {
	if (bussType == '110')
		return '个人基本信息记录';
	if (bussType == '114')
		return '个人基本信息删除请求记录';
	if (bussType == '120')
		return '家族关系信息记录';
	if (bussType == '130')
		return '个人证件有效期信息记录';
	if (bussType == '134')
		return '个人证件有效期信息删除请求记录';
	if (bussType == '140')
		return '个人证件整合信息记录';

	if (bussType == '210')
		return '个人借贷账户记录';
	if (bussType == '211')
		return '个人借贷账户标识变更请求记录';
	if (bussType == '212')
		return '个人借贷账户按段更正请求记录';
	if (bussType == '213')
		return '个人借贷账户按段删除请求记录';
	if (bussType == '214')
		return '个人借贷账户整笔删除请求记录';
	if (bussType == '215')
		return '个人借贷账户特殊事件说明记录';
	if (bussType == '220')
		return '个人授信协议信息记录';
	if (bussType == '221')
		return '个人授信协议标识变更请求记录';
	if (bussType == '222')
		return '个人授信协议按段更正请求记录';
	if (bussType == '223')
		return '个人授信协议按段删除请求记录';
	if (bussType == '224')
		return '个人授信协议整笔删除请求记录';

	if (bussType == '230')
		return '个人担保账户信息记录';
	if (bussType == '231')
		return '个人担保账户标识变更请求记录';
	if (bussType == '232')
		return '个人担保账户更正请求记录';
	if (bussType == '233')
		return '个人担保账户按段删除请求记录';
	if (bussType == '234')
		return '个人担保账户整笔删除请求记录';

	if (bussType == '510')
		return '抵(质)押合同信息记录';
	if (bussType == '511')
		return '抵(质)押合同标识变更请求记录';
	if (bussType == '514')
		return '抵(质)押合同整笔删除请求记录';

	if (bussType == '310')
		return '企业基本信息';
	if (bussType == '314')
		return '企业基本信息删除请求记录';
	if (bussType == '340')
		return '企业身份标识整合信息记录';
	if (bussType == '350')
		return '企业间关联关系信息记录';

	if (bussType == '410')
		return '企业借贷账户记录';
	if (bussType == '411')
		return '企业借贷账户标识变更请求记录';
	if (bussType == '412')
		return '企业借贷账户更正请求记录';
	if (bussType == '413')
		return '企业借贷账户按段删除请求记录';
	if (bussType == '414')
		return '企业借贷账户整笔删除请求记录';
	if (bussType == '420')
		return '企业授信协议信息记录';
	if (bussType == '421')
		return '企业授信协议标识变更请求记录';
	if (bussType == '422')
		return '企业授信协议更正请求记录';
	if (bussType == '423')
		return '企业授信协议按段删除请求记录';
	if (bussType == '424')
		return '企业授信协议整笔删除请求记录';
	if (bussType == '430')
		return '企业最高额保证合同信息记录';
	if (bussType == '431')
		return '企业最高额保证合同标识变更请求记录';
	if (bussType == '432')
		return '企业最高额保证合同更正请求记录';
	if (bussType == '433')
		return '企业最高额保证合同按段删除请求记录';
	if (bussType == '434')
		return '企业最高额保证合同整笔删除请求记录';

	if (bussType == '440')
		return '企业担保账户信息记录';
	if (bussType == '441')
		return '企业担保账户标识变更请求记录';
	if (bussType == '442')
		return '企业担保账户更正请求记录';
	if (bussType == '443')
		return '企业担保账户按段删除请求记录';
	if (bussType == '444')
		return '企业担保账户整笔删除请求记录';

	if (bussType == '610')
		return '企业资产负债表信息记录';
	if (bussType == '620')
		return '企业利润及利润分配表信息记录';
	if (bussType == '630')
		return '企业现金流量表信息记录';
	if (bussType == '640')
		return '事业单位资产负债表信息记录';
	if (bussType == '650')
		return '事业单位收入支出表信息记录';
	if (bussType == '614')
		return '企业资产负债表整笔删除请求记录';
	if (bussType == '624')
		return '企业利润及利润分配表整笔删除请求记录';
	if (bussType == '634')
		return '企业现金流量表整笔删除请求记录';
	if (bussType == '644')
		return '事业单位资产负债表整笔删除请求记录';
	if (bussType == '654')
		return '事业单位收入支出表整笔删除请求记录';
}

function getUrl(bussType){
	if(bussType=='110') return '../jsp/nat/modify/InBasInfoDetail.jsp';
	if(bussType=='114') return '个人基本信息删除请求记录        ';
	if(bussType=='120') return '家族关系信息记录                ';
	if(bussType=='130') return '个人证件有效期信息记录          ';
	if(bussType=='134') return '个人证件有效期信息删除请求记录  ';
	if(bussType=='140') return '个人证件整合信息记录            ';

	if(bussType=='210') return '../jsp/nat/modify/InAcctInfDetail.jsp';
	if(bussType=='211') return '个人借贷账户标识变更请求记录 ';
	if(bussType=='212') return '个人借贷账户按段更正请求记录 ';
	if(bussType=='213') return '个人借贷账户按段删除请求记录 ';
	if(bussType=='214') return '个人借贷账户整笔删除请求记录 ';
	if(bussType=='215') return '个人借贷账户特殊事件说明记录 ';
	if(bussType=='220') return '../jsp/nat/modify/InCtrctInfDetail.jsp';
	if(bussType=='221') return '个人授信协议标识变更请求记录 ';
	if(bussType=='222') return '个人授信协议按段更正请求记录 ';
	if(bussType=='223') return '个人授信协议按段删除请求记录 ';
	if(bussType=='224') return '个人授信协议整笔删除请求记录 ';

	if(bussType=='230') return '个人担保账户信息记录         ';
	if(bussType=='231') return '个人担保账户标识变更请求记录 ';
	if(bussType=='232') return '个人担保账户更正请求记录     ';
	if(bussType=='233') return '个人担保账户按段删除请求记录 ';
	if(bussType=='234') return '个人担保账户整笔删除请求记录 ';

	if(bussType=='510') return '../jsp/nat/modify/MotCltCtrInfDetail.jsp';
	if(bussType=='511') return '抵(质)押合同标识变更请求记录 ';
	if(bussType=='514') return '抵(质)押合同整笔删除请求记录 ';

	if(bussType=='310') return '../jsp/ent/modify/EnBasInfDetial.jsp';
	if(bussType=='314') return ' 企业基本信息删除请求记录';
	if(bussType=='340') return ' 企业身份标识整合信息记录';
	if(bussType=='350') return ' 企业间关联关系信息记录  ';

	if(bussType=='410') return '../jsp/ent/modify/EnAcctInfDetial.jsp';
	if(bussType=='411') return ' 企业借贷账户标识变更请求记录       ';
	if(bussType=='412') return ' 企业借贷账户更正请求记录           ';
	if(bussType=='413') return ' 企业借贷账户按段删除请求记录       ';
	if(bussType=='414') return ' 企业借贷账户整笔删除请求记录       ';
	if(bussType=='420') return '../jsp/ent/modify/EnCtrctInfDetial.jsp';
	if(bussType=='421') return ' 企业授信协议标识变更请求记录       ';
	if(bussType=='422') return ' 企业授信协议更正请求记录           ';
	if(bussType=='423') return ' 企业授信协议按段删除请求记录       ';
	if(bussType=='424') return ' 企业授信协议整笔删除请求记录       ';
	if(bussType=='430') return ' 企业最高额保证合同信息记录         ';
	if(bussType=='431') return ' 企业最高额保证合同标识变更请求记录 ';
	if(bussType=='432') return ' 企业最高额保证合同更正请求记录     ';
	if(bussType=='433') return ' 企业最高额保证合同按段删除请求记录 ';
	if(bussType=='434') return ' 企业最高额保证合同整笔删除请求记录 ';


}



function modify(bussType,id,msg){
	var url =  getUrl(bussType);
	var tabName =getTabName(bussType).trim();
	openSubTab(url+'?id='+id+'&msg='+msg+"&bussType="+bussType,tabName);
}
