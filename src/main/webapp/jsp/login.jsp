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
	<script type="text/javascript">
		function document.onkeydown() {
			if(event.keyCode == 13) {
				chPW();
			}
		}
	</script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css">
	<title>江苏金农征信报送系统</title>
</head>
<body>
     
    <form id="main" name="main" action="<c:url value="loginCheck.html"/>" method="post" class="body">
    <div id="base" class="">
      <!-- Unnamed (Image) -->
      <div id="u0" class="ax_image" style="width: 100%; height: 100%; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px; overflow: hidden;">
        <img id="u0_img" src="${ctx}/images/Other/u0.jpg" style="width: 100%; height: 100%;"  />
      </div>
      <div id="error">
        <c:if test="${!empty error}">
            <font color="red"><c:out value="${error}" /></font>
        </c:if>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u2" class="ax_文本">
        <img id="u2_img" class="img " src="${ctx}/images/Other/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u3" class="text">
          <p><span>用户:</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u4" class="ax_文本">
        <img id="u4_img" class="img " src="${ctx}/images/Other/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u5" class="text">
          <p><span>密码:</span></p>
        </div>
      </div>

      <!-- user (文本框(单行)) -->
      <div id="u6" class="ax_文本框_单行_" data-label="user">
        <input type="text" name="userID"/>
      </div>

      <!-- pwd (文本框(单行)) -->
      <div id="u7" class="ax_文本框_单行_" data-label="pwd">
       <input type="password" name="pw" id="pw"/>
        <input name="password" type="hidden"/>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u8" class="ax_image">
        <img id="u8_img" class="img " onclick="javascript:chPW();" src="${ctx}/images/Other/u8.gif"/>
        <!-- Unnamed () -->
        <div id="u9" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u10" class="ax_image">
        <img id="u10_img" class="img " onclick="javascript:main.reset();" src="${ctx}/images/Other/u10.gif"/>
        <!-- Unnamed () -->
        <div id="u11" class="text">
          <p><span></span></p>
        </div>
      </div>
    </div>
    </form>
  </body>
</html>
