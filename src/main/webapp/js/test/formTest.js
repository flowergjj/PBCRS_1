var path = getAppPath();
//个人用户id

$(function() {
	alert(window.location.search.split("=", 2)[1]);
	// 加载下拉框
	$('#cc').combobox({
		url : path + "/form/get/comboboxData.html",
		valueField : 'id',
		textField : 'text'
	});
	// 通过form的load方法加载数据
	 $('#ff').form('load',path+"/form/get/formData.html");
	/*var data = {
		text : '我是一个普通文本框',
		password : '123',
		dept : 1,
		dept : 2,
		number : '10000',
		date : '2019-02-01',
		birthday : '2019-3-1 20:59',
		sex : '女'
	};*/
	 //$('#ff').form('load', data);
});