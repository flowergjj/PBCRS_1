<%@page import="com.documentum.operations.impl.common.query.Query"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="uname" value="${user.userName}" />
<c:set var="sss" value="${id}"/>
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${ctx}/js/jQuery.md5.js"></script>
<script type="text/javascript" src="${ctx}/js/system/main.js"></script>
<script type="text/javascript" src="${ctx}/js/test/formTest.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="#" id="ff">
		普通文本框：<input class="easyui-textbox" name="text" style="width: 200px">
		</br>
		<div style="height: 20px"></div>
		加密文本框：<input type="password" name="password"/>
		<div style="height: 20px"></div>
		下拉框：<input id="cc" class="easyui-combobox" name="dept"/></br>
		<div style="height: 20px"></div>
		数字文本框：<input type="text" class="easyui-numberbox" name="number"
			data-options="min:0" /> </br>
		<div style="height: 20px"></div>
		日期输入框<input type="text" class="easyui-datebox" name="date"
			required="required" /> </br>
		<div style="height: 20px"></div>
		时间日期输入框<input class="easyui-datetimebox" name="birthday"
			data-options="required:true,showSeconds:false" style="width: 150px"></br>
		<div style="height: 20px"></div>
		单选框：男:<input type="radio" name="sex"  value="男"> 女:<input
			type="radio" name="sex" value="女">
	</form>
</body>
</html>