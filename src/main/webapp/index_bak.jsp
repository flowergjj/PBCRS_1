<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="${ctx}/js/jQuery.md5.js"></script>
		<script type="text/javascript" src="${ctx}/js/system/index.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css">
		<title>EISM</title>
	</head>
	<body >
		<c:if test="${!empty error}">
	        <font color="red"><c:out value="${error}" /></font>
		</c:if>     
		<form action="<c:url value="loginCheck.html"/>" method="post" class="body">
			用户名1：
			<input type="text" name="userID">
			<br>
			密 码：
			<input type="password" name="pw" id="pw" onblur="chPW();">
			<input name="password" type="hidden">
			<br>
			<input type="submit" value="登录" />
			<input type="reset" value="重置" />
		</form>
	</body>
</html>
