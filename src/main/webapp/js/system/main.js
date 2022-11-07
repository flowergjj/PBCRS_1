var icon = "";
var path = getAppPath();
var userId=getUserId();
$(function() {
	var arr = new Array();
	arr.push({id:1});
	arr.push({id:2});
	var data =JSON.stringify(arr)
	$.ajax({
		url : path + '/test.html',
		type : "post",
	    contentType: 'application/json',
		data:data,
		async : false,
		success : function(data) {

		}
	});


	$('#noticetable').datagrid({
		toolbar : [ {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				reloadNotice();
			}
		} ]

	});

	reloadNotice();
	checklogList();
	
	//getCheckTotal();

});

function zeroize(obj){
	if(obj<10){
		return '0'+obj;
	}else{
		return obj;
	}
}

function checklogList(){
	var date = new Date();
	var etl_date = date.getFullYear()+"-"+zeroize(date.getMonth()+1)+"-"+zeroize(date.getDate());
	var checkType = getCheckType('CHECK_REPORT_TYPE');
	$.ajax({
		url : path + '/etlManager/getCheckByGroup.html',
		type : "post",
		data : {
			etl_date:etl_date
		},
		async : false,
		success : function(data) {
			var str="";
            if(data.data!=null){
            	$.each(data.data,function(dindex,dvalue){
            		$.each(checkType,function(cindex,cvalue){
                		if(dvalue.CHECKFUNC == cvalue.id){
                			 
                			str=str+"<a href='#' onClick='checkDetail(\""+etl_date+'\",\"'+dvalue.CHECKFUNC+"\")'>"+cvalue.text+"校验错误"+dvalue.TOTAL+"笔"+"</a><br/><br/>";
                			
                		}
                	});
            		
            	});
            	$('#checkList').html(str);
            	//console.log(str);
            	
            }
		}
	});
}
function checkDetail(p_etl_date,checkfunc){
	openSubTab("../jsp/etlmanager/checkAllLog.jsp"+'?dealDt='+'2019-08-30'+"&condition="+checkfunc,"校验日志");
	//console.log(p_etl_date,checkfunc);
}

function getCheckType(typeId) {
	var checkType='';
	$.ajax({
		url : path + '/sysCodeManage/sysCodeListByType.html',
		type : "post",
		data : {
			type : typeId
		},
		async : false,
		success : function(data) {
			checkType=data;
		}
	});
	return checkType;
}

function OpenChgPwdDlg() {
	$('#chgPwddlg').dialog('open');
}

function logOut() {
	$.ajax({
		url : path + '/userManage/logOut.html',
		type : "post",
		async : false,
		success : function(data) {
         
		}
	});
	window.location.href = path;
}

function mailLogin() {
	$.ajax({
		url : path + '/MailLogin/getLoginSid.html',
		type : "post",
		async : false,
		success : function(data) {
			if (data['url']) {
				window.open(data['url'], '_blank');
			}
		}
	});
}

function SavePwd() {

	var oldpwd = $('#oldPwd').val();
	var newpwd = $('#newPwd').val();
	var cfmpwd = $('#confirmPwd').val();
	if (newpwd != cfmpwd) {

		alert('确认密码不相符！');
	} else {
		var oldpw = $.md5(oldpwd);
		var npw = $.md5(newpwd);
		$.ajax({
			url : path + '/userManage/changPwd.html',
			type : "post",
			data : {
				"oldpwd" : oldpw,
				"newpwd" : npw
			},
			success : function(data) {
				if (data.success != '') {
					alert(data.success);
					$('#chgPwddlg').dialog('close');
				} else {
					alert(data.error);
				}
			}
		});

	}
}



function reloadNotice() {
	$
			.ajax({
				url : path + '/noticeBoardController/getNotices.html',
				type : 'POST',
				success : function(data) {
					var notice_address = "../jsp/system/SystemNotice.jsp";
					var str2 = "";
					for ( var k = 0; k < data.length; k++) {
						str2 += "<li style=\"padding:3px;\" ><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-add'\" onclick=\"openTab('"
								+ notice_address
								+ '?NOTICE_ID='
								+ data[k].NOTICE_ID
								+ "', '系统公告')\">"
								+ data[k].TITLE + "</a></li>";
					}

					$('#notice_board').html(str2);
				}
			});

}
function getCheckTotal() {
	$.ajax({
		url : path + '/pledgeAndQualif/getTotal.html',
		method : 'post',
		async : false,
		data : {
			loginUserId : userId
		},
		success : function(data) {
			$('#pledgeTotal').html(data.pledgeTotal);
			$('#qualifTotal').html(data.qualifTotal);
		}
	});
}

