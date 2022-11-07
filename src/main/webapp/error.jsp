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
		<title>PBCRS</title>
	</head>
	<body>
		<c:if test="${!empty error}">
	        <font color="red"><c:out value="${error}" /></font>
		</c:if>        
		<form action="<c:url value="loginCheck.html"/>" method="post">
			Oops... 您的登录可能已经过期，或者您已在其他地方登录，请&nbsp;<a href="javascript:void(0)" id="logout" onclick="backLogIn()">重新登录</a>&nbsp;，或联系管理员。
		</form>
		<!-- style="color:#000 " -->
	</body>
</html>
