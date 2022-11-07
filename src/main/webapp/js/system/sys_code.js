var path = getAppPath();
var Mydata = {};

function initCombobox(typeId, comoboxId) {
	$.ajax({
		url : path + '/sysCodeManage/sysCodeListByType.html',
		type : "post",
		data : {
			type : typeId
		},
		async : false,
		success : function(data) {
			Mydata[typeId] = data;
			$('#' + comoboxId).combobox({
				data : data,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 100
			});
		}
	});
	
}

function ComboxTxt(typeId,value){
   //console.info(Mydata[typeId]);
   for(var i = 0 ;i< Mydata[typeId].length;i++){
	   if(value == Mydata[typeId][i].id){
		   return Mydata[typeId][i].text;
	   }
   }
}

/*function initCombobox1(typeId, comoboxId) {
	$.ajax({
		url : path + '/sysCodeManage/sysCodeListByType.html',
		type : "post",
		data : {
			type : typeId
		},
		async : false,
		success : function(data) {
			data.unshift({id:null,text:'--无--'});
			$('#' + comoboxId).combobox({
				data : data,
				valueField : 'id',
				textField : 'text',
				panelWidth : 132,
				panelHeight : 70
			});
		}
	});
}*/
function initComboboxWH(typeId, comoboxId,width,height) {
	$.ajax({
		url : path + '/sysCodeManage/sysCodeListByType.html',
		type : "post",
		data : {
			type : typeId
		},
		async : false,
		success : function(data) {
			$('#' + comoboxId).combobox({
				data : data,
				valueField : 'id',
				textField : 'text',
				panelWidth : width,
				panelHeight : height
			});
		}
	});
	
}
