var path = getAppPath();
var dealDt = getQueryString('dealDt');
var dealEndDt = getQueryString('dealEndDt');
var condition = getQueryString('condition');
$(function() {

	setinitCombobox();
	initTable()
	

});

function initTable(){
	$('#etlLog').datagrid({
			striped : true,
			url : path + '/sysConController/getEtlLogInfo.html',
			remoteSort : false,
			idField : 'WEB_PROC_ID',
			queryParams : queryParameter(),
			singleSelect : true,// 是否单选
			pagination : true,// 分页控件
			scrollbarSize:0,
			pageSize : 21,
			pageList : [ 21 ],  
			columns : [ [ {
				field : 'ETL_DATE',
				title : '调度日期',
				width : '11%',
				align : 'center'
			}, {
				field : 'CON_NAME',
				title : '调度类型',
				width : '11%',
				align : 'center'
			},
			 {
				field : 'INSERT_USER_NO',
				title : '执行人工号',
				width : '11%',
				align : 'center'
			},
			{
				field : 'INSERT_USER_NAME',
				title : '执行人姓名',
				width : '11%',
				align : 'center' 
			},
			{
				field : 'DEAL_DT',
				title : '调度起始时间',
				width : '11%',
				align : 'center'
			},
			{
				field : 'DEALEND_DT',
				title : '调度结束时间',
				width : '11%',
				align : 'center'
			},
			{
				field : 'STATE',
				title : '调度状态',
				width : '11%',
				align : 'center'
			},
			{
				field : 'SOURCESYS',
				title : '系统',
				width : '11%',
				align : 'center'
			},
			{
				field : 'BADFILENAME',
				title : '操作',
				width : '11%',
				align : 'center',
				formatter : function(value, row, index) {
					return '<a href="javascript:void(0)" onClick=checkLog(\"'
							+row.WEB_PROC_ID+ '\")>查看日志</a>';
				}
			}]],
			onLoadSuccess : function(data){
				
			}
			
		});
		// 设置分页控件
		var p = $('#etlLog').datagrid('getPager');
		$(p).pagination({
			pageSize : 21,// 每页显示的记录条数，默认为10
			showPageList : false,
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	
		});
		$('#etlLog').datagrid('clearSelections');
}

function queryParameter(){

}

function checkLog(webProcID){
$.ajax({
		url : path + '/sysConController/findLogDetail.html',
		type : "post",
		data:{
		 webProcId:webProcID
		},
		async : false,
		success : function(data) {
			if(data.RET_CODE == 'SUCCESS'){
				var logList = data.DATA;
				var str="<strong>日志详细信息</strong><br>"
				$.each(logList,function(index,value){
				
				    if(value.LOG_TYPE == "ERROR"){
						str = str+ index+": " +"<font color='#FF0000'>"+value.PROC_NAME+"   "+value.LOG_INFO + "  "+"</font><br>";
					}else{
						str= str+ index+": " +value.PROC_NAME+"   " +value.LOG_INFO +"<br>";
					}
				});
				$('#log_detail').html(str)
				$('#log').dialog('open')
			}else{
				$.messager.alert('错误', '日志查询失败！', 'error');
			}	
		}	
	});
	
}

//条件查询
function doQuery(){
	
}




//下拉框代码表
function setinitCombobox() {
	//initComboboxWH('REPORT_SYS', 'sourceSys',132,100);	
}
function clearQuery(){
	
	
}