




function getRptType(type) {
	var param = {};
	param.reportType = type;
	$.ajax({
		url : path + '/sendFile/listPageRptType.html',
		type : "post",
		data : param,
		async : false,
		success : function(data) {
			// alert(data.query.length);
			$('#queryRptType').combobox({
				// url:path+'/orgManage/orgList.html',
				data : data.query,
				valueField : 'ID',
				textField : 'TEXT',
				panelWidth : 132,
				panelHeight : 120
			});
		}		
	});
}
function getInRptType(type) {
	var param = {};
	param.reportType = type;
	$.ajax({
		url : path + '/sendFile/listPageRptType.html',
		type : "post",
		data : param,
		async : false,
		success : function(data) {
			// alert(data.query.length);
			$('#queryInRptType').combobox({
				// url:path+'/orgManage/orgList.html',
				data : data.query,
				valueField : 'ID',
				textField : 'TEXT',
				panelWidth : 132,
				panelHeight : 120
			});
		}		
	});
}

function getYLRptType(type) {
	var param = {};
	param.reportType = type;
	$.ajax({
		url : path + '/sendFile/listPageRptType.html',
		type : "post",
		data : param,
		async : false,
		success : function(data) {
			// alert(data.query.length);
			$('#YLqueryRptType').combobox({
				// url:path+'/orgManage/orgList.html',
				data : data.query,
				valueField : 'ID',
				textField : 'TEXT',
				panelWidth : 132,
				panelHeight : 120
			});
		}		
	});
}

