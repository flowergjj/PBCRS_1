var path = getAppPath();

var lazy = false;
$(function() {
	getRptType(null);
	getYLRptType(null);
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
		pageSize : 15,
		pageList : [ 15 ],
		// rownumbers:true,//行号
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
			width : '15%',
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
			field : 'ISUPLOAD',
			title : '是否已上传人行征信',
			width : '10%',
			align : 'center',
			formatter : function(value, row, index) {
			   if(value == 'Y'){
				   return '已上传';
			   }
			   return '';
			}
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
	queryObject.querySysType ="IND";
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



var errToolbar = [
		{
			text:"添加到过滤列表      ",
			iconCls: "icon-add",
			handler:function(){
				addFilter();
			}
		},
		{
			text:"            从过滤列表移除",
			iconCls: "icon-cancel",
			handler:function(){
				removeFilter();
			}
		},
		{
			text:"确认保存",
			iconCls: "icon-save",
			handler:function(){
				addFilter();
			}
		}
		

	];

function showErrInfo(seqno){
	
	$("#err").dialog("open")
	$('#errTab').treegrid({    
	    url:path + '/sendFile/queryErrorInfo.html?seqNo='+seqno,    
	    idField:'seqno',    
	    treeField:'msgInfo',  
	    toolbar:errToolbar,
	    singleSelect: false,
	    checkOnSelect:false,
	    pagination:true,
	    columns:[[    
	    	{field : 'check',width : '2%',checkbox:true},
	       /* {title:'业务主键',field:'reportseqno',width:'15%'}, */   
	        {field:'name',title:'名称',width:'15%',align:'center'},    
	        {field:'idnum',title:'证件号码',width:'15%',align:'center'},    
	        {field:'fbcode',title:'反馈代码',width:'8%',align:'center'},    
	        {field:'fbmsg',title:'反馈消息',width:'8%',align:'center'},    
	        {field:'msgInfo',title:'反馈内容',width:'45%',align:'left',halign: 'center',formatter: function(value,row,index){
				return '<span title='+value+'>'+value+'</span>'
			 }}, 
	        {field:'isFilter',title:'是否已过滤',width:'8%',align:'center',formatter: function(value,row,index){
				if(value == 'Y'){
					return '是'
				}
				return '否'
			 }},    
	    ]],
		onLoadSuccess:function(row,data){
			$(".datagrid-body [node-id='null'] .datagrid-cell-check").html("")
		},
		rowStyler: function(row){
			if(row.isFilter == 'Y'){
				return 'background-color:#cccccc;';
			}
	}

	});

	
}

function addFilter(){
	var select = $('#errTab').datagrid("getSelections")
	var obj= new Array();
	var isFilterVar = 'N'
    $.each(select,function(i,v){
    	if(v.seqno != null){
    		obj[obj.length] = v
			if(v.isFilter == 'Y'){
				isFilterVar ='Y'
			}
    	}
    });
	if(isFilterVar == 'Y'){
		$.messager.alert('错误', '已选择列表存在已过滤数据!', 'error');
		return ;
	}

    if(obj.length == 0){
		$.messager.alert('错误', '未选择数据!', 'error');
		return ;
	}
    $.ajax({
		url : path + '/sendFile/addFilter.html',
		type : "post",
		data:JSON.stringify(obj),
		contentType: "application/json",
		dataType: "json",
		async : false,
		success : function(data) {
			if(data.RPT_CODE == 'SUCCESS'){
				$.messager.alert('成功', '添加成功!', 'info');
				$('#errTab').treegrid('load');
				$('#errTab').datagrid("uncheckAll")
			}else{
				$.messager.alert('错误', '添加失败!', 'error');
			}

		}		
	});
}

function removeFilter() {
	var select = $('#errTab').datagrid("getSelections")
	var obj= new Array();
	var isFilterVar = 'Y'
	$.each(select,function(i,v){
		if(v.seqno != null){
			obj[obj.length] = v
			if(v.isFilter == 'N'){
				isFilterVar = 'N'
			}
		}
	});
	if(isFilterVar == 'N'){
		$.messager.alert('错误', '已选择列表存在未过滤数据!', 'error');
		return ;
	}
	if(obj.length == 0){
		$.messager.alert('错误', '未选择数据!', 'error');
		return ;
	}
	$.ajax({
		url : path + '/sendFile/removeFilter.html',
		type : "post",
		data:JSON.stringify(obj),
		contentType: "application/json",
		dataType: "json",
		async : false,
		success : function(data) {
			if(data.RPT_CODE == 'SUCCESS'){
				$.messager.alert('成功', '移除成功!', 'info');
				$('#errTab').treegrid('load');
				$('#errTab').datagrid("uncheckAll")
			}else{
				$.messager.alert('错误', '移除失败!', 'error');
			}

		}
	});

}