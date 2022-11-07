var path = getAppPath();
$(function() {
	
});
function doCreate() {
	 window.open(path + '/datajob/create.html?scoreDate='+$('#scoreDate').datebox('getValue'));
	
			
	/*$.ajax({
		url : path + '/datajob/create.html',
		type : "post",
		//data : param,
		async : false,
		success : function(data) {
			if(data.RET_CODE == 'FAILED'){
				$.messager.propress('close');
				$.messager.alert('错误', data.MSG, 'error');
			}else{
				$.messager.propress('close');
			}
		}		
	});*/
	/*$.messager.progress({
		title:'提示',
		msg:'文件生成中，请稍后......',
		text:'',
		interval:'500'
	});*/
}
