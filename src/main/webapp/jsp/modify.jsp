<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp" />
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>

    <script type="text/javascript" src="${ctx}/js/jQuery.md5.js"></script>
	<script type="text/javascript" src="${ctx}/js/system/modify.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/modify.css">
	<title>EISM</title>
  </head>
  <!-- background="${ctx}/images/Other/u0.jpg" -->
  <body>
	<form id="main" name="main" action="<c:url value="loginCheck.html"/>" method="post" class="body">
    <div id="base" class="">
    <!-- Unnamed (Image) -->
    <div id="u0" class="ax_image" style="width: 100%; height: 100%; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px; overflow: hidden;">
      <img id="u0_img" src="${ctx}/images/Other/u0.jpg" style="width: 100%; height: 100%;"  />
    </div>
    
       <div id="info">
           <font color="red">用户首次登陆, 请修改密码!</font>
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
      <div id="u99" class="ax_文本">
        <img id="u4_img" class="img " src="${ctx}/images/Other/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u5" class="text">
          <p><span>新密码:</span></p>
        </div>
      </div>
      
      <!-- Unnamed (形状) -->
      <div id="u100" class="ax_文本">
        <img id="u100_img" class="img " src="${ctx}/images/Other/transparent.gif"/>
        <div id="u100_text" class="text">
          <p><span>确认密码:</span></p>
        </div>
      </div>

      <!-- user (文本框(单行)) -->
      <div id="u6" class="ax_文本框_单行_" data-label="user">
        <input type="text" name="userId" id="userId" value="${param.user}" readonly="readonly" />
      </div>

      <!-- pwd (文本框(单行)) -->
      <div id="u7" class="ax_文本框_单行_" data-label="pwd">
       <input type="password" name="pw" id="pw" />
      </div>
      
      <!-- pwd (文本框(单行)) -->
      <div id="u101" class="ax_文本框_单行_" data-label="pwd">
       <input type="password" name="repw" id="repw" />
      </div>

      <!-- Unnamed (Image) -->
      <div id="u102" class="ax_image">
           <img id="u102_img" class="img" onclick="SavePwd()" src="${ctx}/images/Other/u102.gif"/>
      </div>

    </div>
    </form>
  </body>
</html>
