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
<title>Insert title here</title>
<script type="text/javascript">
function changeImg() {
	var imgSrc = $("#vcodeImg1");
	var src = imgSrc.attr("src");
	imgSrc.attr("src", chgUrl(src));
}

function chgUrl(url) {
	var timestamp = (new Date()).valueOf();
	if ((url.indexOf("&") >= 0)) {
		url = url + "×tamp=" + timestamp;
	} else {
		url = url + "?timestamp=" + timestamp;
	}

	return url;
}
</script>
</head>
<body>
<img src="${ctx}/cbb_code/save.html" class="checkCode"
								id="vcodeImg1" onclick="changeImg()" style="cursor:hand;"
								alt="看不清，换一张">
</body>
</html>