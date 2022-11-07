var path = getAppPath();
function chPW() {
	var user = $('input[name=userID]').val();
	var pw = $('#pw').val();
	// $('#pw').val('');

	if (!user) {
		alert('请填写用户名!');
		$('#userID').focus();
		return;
	}

	if (!pw) {
		alert('请填写密码!');
		$('#pw').focus();
		return;
	}

	var npw = $.md5(pw);
	$("input[name='password']").val(npw);
	document.main.submit();
}

function backLogIn() {
	window.parent.location.href = path;
}