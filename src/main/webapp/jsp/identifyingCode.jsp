<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp" />
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<script type="text/javascript" src="${ctx}/js/jQuery.md5.js"></script>
<script type="text/javascript" src="${ctx}/js/system/identifyingCode.js"></script>
<script type="text/javascript">
 
        function document.onkeydown() {
            if(event.keyCode == 13) {
                var rflag = SaveIdCode();
                return rflag;
            }
        }
    </script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/identifyingCode.css">
<title>EISM</title>
</head>
<!-- background="${ctx}/images/Other/u0.jpg" -->
<body>
	<form id="main" name="main" action="<c:url value="/jsp/main.jsp"/>" method="post" class="body">
		<div id="base" class="">
			<!-- Unnamed (Image) -->
			<div id="u0" class="ax_image"
				style="width: 100%; height: 100%; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px; overflow: hidden;">
				<img id="u0_img" src="${ctx}/images/Other/u0.jpg"
					style="width: 100%; height: 100%;" />
			</div>

			<div id="info" style="width: 80%; height: 100%;">
				<font color="red">请输入正确的登录验证码</font>
			</div>

			<!-- Unnamed (形状) -->
			<div id="u2" class="ax_文本">
				<img id="u2_img" class="img "
					src="${ctx}/images/Other/transparent.gif" />
				<!-- Unnamed () -->
				<div id="idcode_desc" class="text">
					<p>
						<span>登录验证码:</span>
					</p>
				</div>
			</div>

			<!-- user (文本框(单行)) -->
			<div id="idcode_value" class="ax_文本框_单行_" data-label="user">
				<input type="text" name="identifyingCode" id="identifyingCode"
					value="" class="inputText" />
			</div>
			<div id="idcode_btn" class="ax_文本框_单行_" data-label="user">
				<input type="button" id="btn" value="点击获取登录验证码"
					onclick="javascript:obtainIdCode(this);" />
			</div>

			<!-- Unnamed (Image) -->
			<div id="u102" class="ax_image">
				<img id="u102_img" class="img" onclick="SaveIdCode()"
					src="${ctx}/images/Other/u102.gif" />
			</div>

		</div>
	</form>
</body>
</html>
