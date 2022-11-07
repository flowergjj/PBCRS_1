var path = getAppPath();

function SavePwd()
{
	var pw = $('#pw').val();
	var repw = $('#repw').val();
	if(!pw) {
		$('#info font').html('新密码不能为空!');
		alert('新密码不能为空!');
	}
	else if( pw != repw)
	{
		$('#info font').html('二次输入的密码不相符, 请修改!');
		alert('确认密码不相符！');
	}
	else
	{
		pw = $.md5(pw);
		$.ajax({
			url:path+'/userManage/newPwd.html',
			type:"post",
			data:{
				"userId":$('#userId').val(),
				"pw":pw
			},
			success:function(data){
				if(data.RET_CODE == 'SUCCESS') {
					$.messager.alert('Title', data.RET_MSG, 'info', function() {
						window.location.href=path + '/index.jsp?userId=' + $('#userId').val();
					});
				} else {
					$.messager.alert('Title', data.RET_MSG, 'error');
				}
			}	
		});
	}
	
}