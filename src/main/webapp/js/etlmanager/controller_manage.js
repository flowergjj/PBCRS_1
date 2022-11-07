var path = getAppPath();
var enReport;
$(function() {
	$('body').css('visibility', 'visible');
	loadEtlManager()
	

});


// 调度列表
function loadEtlManager() {
	$('#ind_etlpanel').treegrid({
						url: path + '/sysConController/sysConByType.html',    
    					idField:'CON_ID', 
    					/*queryParams:{
    						
    					} ,*/  
    					toolbar:"#toolbar",
    					treeField:'CON_NAME',    
						columns : [ [						
								{
									field : 'CON_NAME',
									title : '调度名称',
									width : '20%'
									//align : 'left'
								},
								{
									field : 'TYPE',
									title : '调度类型',
									width : '30%',
									align : 'center'
								},
								{
									field : 'PROC_NAME',
									title : '存储过程名',
									width : '40%',
									align : 'center'
								},
								
								{
									field : 'INFRECTYPE',
									title : '操作',
									width : '10%',
									align : 'center',
									formatter : function(value, row, index) {
										return '<a href="javascript:void(0)" onClick=deal(\"'
												+row.CON_NAME+ '\",\"'+row.PROC_NAME+'\",\"'+row.CON_ID+'\",\"'+row.REPORT_CODE+'\",\"'+row.SOURCESYS+'\")>执行</a>';
									}
								}
						
						] ],
						onDblClickRow : function(index, row) {
							// alert(index);
							// alert(row.USERID);
							// openUpdateDialog(row.userid);
						}
					});

	// 设置分页控件
/*	var p = $('#ind_etlpanel').datagrid('getPager');
	$(p).pagination({
		pageSize : 15,// 每页显示的记录条数，默认为10
		showPageList : false,
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});*/
	//$('#ind_etlpanel').datagrid('clearChecked');
}






function deal(CON_NAME,PROC_NAME,CON_ID,REPORT_CODE,SOURCESYS){
	MaskUtil.mask('执行中，请稍候……');
	var param = {};
	param.CON_NAME = CON_NAME
	param.PROC_NAME = PROC_NAME
	param.CON_ID = CON_ID
	param.REPORT_CODE = REPORT_CODE
	param.ETL_DATE = $('#etl_date').datebox('getValue').replace("-",'').replace("-",'');
	param.SYSID = SOURCESYS;
	param.ISBATCH = "Y"
	$.ajax({
		url : path + '/sysConController/deal.html',
		type : "post",
		data:param,
		//async : false,
		success : function(data) {
			MaskUtil.unmask();
			if(data.RET_CODE == 'SUCCESS'){
				if(data.msg != null){
					$.messager.alert('成功', data.msg, 'info');
				}else{
					$.messager.alert('成功', '执行成功!', 'info');
				}

			}else{
				$.messager.alert('错误', data.msg, 'error');

			}
		},error : function(){
			MaskUtil.unmask();
		}		
	});
}



