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

<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:"post",
		url:"http://localhost:8080/pbcrs/showYes.html",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify({"name":"张三","sex":"22"})
	})
});
</script>
</head>
<body>
	
</body>
</html>