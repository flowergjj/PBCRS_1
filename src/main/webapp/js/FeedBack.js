var resData;
function getFeedBack(bussType,msg,fbCode){
	var param={};
	param.reportId = bussType;
	param.fieldName = msg;
	param.fbCode = fbCode;
	$.ajax({
		url : path + '/feedBack/getFeed.html',
		type : "post",
		data :param,
		async : false,
		success : function(data) {
			resData = data;
			if(data.code == "ok"){
				showDetial(data.query.SMGTNAME);
				//var fieldName = data.query.FIELDNAME;
				var fieldName = data.query.FIELDNAME.toUpperCase();
				var fieldName1 = data.query.FIELDNAME;
				if($("#"+fieldName+"").length>0){
					 $("#"+fieldName+"").next().css("border","1px solid red");
					// $("#"+fileName+"").parent().css("width","50px");
				}else if($("#"+fieldName1+"").length>0){
					$("#"+fieldName1+"").next().css("border","1px solid red");
				}
				//document.getElementById(""+fileName+"").style.borderColor = "#ff0000";
			}else if(data.code == "msgOk"){
				for (var i = 0; i < data.query.length; i++) {
					var fieldName = data.query[i].FIELDNAME.toUpperCase();
					var fieldName1 = data.query.FIELDNAME;			
					if($("#"+fieldName+"").length>0){					
						 $("#"+fieldName+"").next().css("border","1px solid red");
						// $("#"+fileName+"").parent().css("width","50px");
					}else if($("#"+fieldName1+"").length>0){
						$("#"+fieldName1+"").next().css("border","1px solid red");
					}
				}
			}
		}		
	});
}


function dialog(){
	if(resData.code == "ok"){
		var fieldName = resData.query.FIELDNAME.toUpperCase();
		var fieldName1 = resData.query.FIELDNAME;
		if($("#"+fieldName+"").length>0){
			 $("#"+fieldName+"").next().css("border","1px solid red");
			// $("#"+fileName+"").parent().css("width","50px");
		}else if($("#"+fieldName1+"").length>0){
			$("#"+fieldName1+"").next().css("border","1px solid red");
		}
		//document.getElementById(""+fileName+"").style.borderColor = "#ff0000";
	}else if(resData.code == "msgOk"){
		for (var i = 0; i < resData.query.length; i++) {
			var fieldName = resData.query[i].FIELDNAME.toUpperCase();
			var fieldName1 = resData.query.FIELDNAME;			
			if($("#"+fieldName+"").length>0){					
				 $("#"+fieldName+"").next().css("border","1px solid red");
				// $("#"+fileName+"").parent().css("width","50px");
			}else if($("#"+fieldName1+"").length>0){
				$("#"+fieldName1+"").next().css("border","1px solid red");
			}
		}
	}
}