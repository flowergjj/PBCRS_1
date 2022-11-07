var path = getAppPath();

function SaveIdCode() {
	var idcode = $('#identifyingCode').val();
	if (!idcode) {
		$('#info font').html('登录验证码不能为空!');
		alert('请填写登录验证码!');
		$('#identifyingCode').focus();
		return false;
	}
	var return_flag = false;
	$.ajax({
		url : path + '/identifyingCodeManage/saveValidCode.html',
		type : "post",
		data : {
			"identifyingCode" : idcode
		},
		async : false,
		success : function(data) {
			$('#info font').html(data.RET_MSG);
			if (data.RET_CODE == 'SUCCESS') {
				return_flag = true;
				window.location.href = path + '/jsp/main.jsp';
			} else {
				alert(data.RET_MSG);
				$('#identifyingCode').focus();
			}
		}
	});
	return return_flag;
}

function obtainIdCode(val) {
	settime(val);

	$.ajax({
		url : path + '/identifyingCodeManage/obtainValidCode.html',
		type : "post",
		data : {
			"identifyingCode" : $('#identifyingCode').val()
		},
		success : function(data) {
			if (typeof data.RET_MSG != 'undefined') {
				alert(data.RET_MSG);
			} else {
				alert("后端处理异常，请稍候重试！");
			}
			$('#identifyingCode').focus();
		}
	});

}

var TIMER_COUNT = 60;
var countdown = TIMER_COUNT;
var timer_cache;
function settime(val) {
	if (countdown <= 0) {
		val.removeAttribute("disabled");
		val.value = "点击获取登录验证码";
		countdown = TIMER_COUNT;
		return;
	} else {
		val.setAttribute("disabled", true);
		val.value = "重新发送(" + countdown + "s)";
		countdown--;
	}
	timer_cache = setTimeout(function() {
		settime(val);
	}, 1000);
}