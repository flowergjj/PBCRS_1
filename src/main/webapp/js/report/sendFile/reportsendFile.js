var path = getAppPath();

var lazy = false;
$(function() {
	getRptType(null);
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
var toolbar = [
   			{
   				text:"选择下载",
   				iconCls: "icon-task",
   				"class": "tooltip-info",
   				handler:function(){
   					saveAll();
   				}
   			}

   		];

function getentTable() {

	
	$('#sendFileTable').datagrid({
		striped : true,
		url : path + '/sendFile/listPage.html',
		remoteSort : false,
		idField : 'SEQNO',
		queryParams : getQueryParam(),
		singleSelect : false,// 是否单选
		pagination : true,// 分页控件
		toolbar:toolbar,
		pageSize : 20,
		pageList : [ 20 ],
		columns : [ [
			{
				field : 'REPORTNAME',
				title : '报文交易类型',
				width : '10%',
				align : 'center'
			},{
				field : 'ENCFILENAME',
				title : '加密文件名称',
				width : '20%',
				align : 'center',
				formatter : function(value, row, index) {
					if(row.ENCFILENAME == null ){
						return '';
					}else{
						var tmp = row.FILEPATH+"/"+row.ENCFILENAME;
						var url = tmp.replace(/\\/g,
							"/");
						var str ='<a href="javascript:void(0)" onClick=save(\"'
							+encodeURI(url)+ '\")>'+row.ENCFILENAME+'</a>';
						return str;
					}




				}
			}, {
				field : 'BADFILENAME',
				title : '错误信息文件',
				width : '25%',
				align : 'center',
				formatter : function(value, row, index) {
					if(row.BADFILENAME == null){
						return '';
					}else{
						var tmp =row.FILEPATH+"\\"+ row.BADFILENAME;
						var url = tmp.replace(/\\/g,
							"/");
						var str ='<a href="javascript:void(0)" onClick=save(\"'
							+encodeURI(url)+ '\")>'+row.BADNAME+'</a>'+'<a href="javascript:void(0)" onClick=showErrInfo(\"'
							+row.SEQNO+ '\")>'+"查看错误信息"+'</a>';
						return str;
					}
				}
			},/*{
			field : 'ERR',
			title : '查看错误信息',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
				if(row.BADFILENAME == null){
					return '';
				}else{

					var str ='<a href="javascript:void(0)" onClick=showErrInfo(\"'
						+row.SEQNO+ '\")>'+"查看错误信息"+'</a>';
					return str;
				}
			}
		}*/ {
				field : 'CREATEDATE',
				title : '生成日期',
				width : '8%',
				align : 'center'
			}
			,{
				field : 'RPTDATE',
				title : '报送日期',
				width : '8%',
				align : 'center',
				formatter : function(value, row, index) {
					var rptDate = row.TXTNAME.substr(14,8);
					return rptDate.replace(/^(\d{4})(\d{2})(\d{2})$/,"$1-$2-$3");




				}
			},
			{
				field : 'CREATETIME',
				title : '生成时间',
				width : '12%',
				align : 'center'
			},
			{
				field : 'INFOCOUNT',
				title : '报文信息数量',
				width : '6%',
				align : 'center'
			},
			{
				field : 'TAB',
				title : '标记',
				width : '5%',
				align : 'center'
			},{
				field : 'TXTFILENAME',
				title : 'TXT报文下载',
				width : '6%',
				align : 'center',
				formatter : function(value, row, index) {
					var tmp = row.TXTFILENAME;
					var url = tmp.replace(/\\/g,
						"/");
					var str ='<a href="javascript:void(0)" onClick=save(\"'
						+encodeURI(url)+ '\")>'+"下载"+'</a>';
					return str;
				}
			}] ]/*,
		onDblClickRow : function(index, row) {
			var tmp = row.FILEPATH+row.ENCFILENAME;
			var url = tmp.replace(/\\/g,
					"/");
			save(encodeURI(url));
		}*/
	});
	// 设置分页控件
	var p = $('#sendFileTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,// 每页显示的记录条数，默认为10
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

function save(url){
	var queryFilepath = $('#queryFilepath').val();
	window.open(path + '/sendFile/download.html?'
			+ "filePath=" + url+"&queryFilepath="+queryFilepath);
	
}

function saveAll(){
	var list =  $('#sendFileTable').datagrid('getSelections');
	if(list.length == 0){
		$.messager.alert('提示','你还没有选择任何记录!');
		return false;
	}
	var tmp = list[0].FILEPATH+"/";
	var param ="path="+tmp.replace(/\\/g,"/");
	var num = 0;
	for (var i = 0; i < list.length; i++) {

		if(list[i].ENCFILENAME != null){
			num = num+1;
			param+="&enc"+[num]+"="+list[i].ENCFILENAME;
			
		}
	}
	param+="&num="+num;
	window.open(path + '/sendFile/downloadAll.html?'
			+ param);
	
}

//条件查询
function doQuery(){
	var queryObject = getQueryParam();
	queryObject.conditionId=null;
	$('#sendFileTable').datagrid('load',queryObject);
	$('#sendFileTable').datagrid('unselectAll');
	
}

function getQueryParam() {
	var queryObject = new Object();
	var conditionId=getQueryString('id');
	if(conditionId){
		queryObject.conditionId=conditionId;
	}
	queryObject.queryRptDate = $('#queryRptDate').datebox('getValue');
	queryObject.RptDate = $('#RptDate').datebox('getValue').replace("-","").replace("-","");
	queryObject.queryTxtFileName = $('#queryTxtFileName').val();
	queryObject.queryFilepath = $('#queryFilepath').val();
	queryObject.queryRptType =$('#queryRptType').combobox('getValue');
	queryObject.querySysType ="ENT";
	return queryObject;
}
//清除条件
function doClear(){
	$('#queryRptDate').datebox('clear');
	$('#RptDate').datebox('clear');
	$('#queryTxtFileName').textbox('clear');
	$('#queryFilepath').textbox('clear');
	$('#queryRptType').combobox('clear');
}
