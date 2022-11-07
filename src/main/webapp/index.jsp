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
  <!-- background="${ctx}/images/Other/u0.jpg" -->
  <body>
 	<c:if test="${!empty error}">
        <font color="red"><c:out value="${error}" /></font>
	</c:if>   
	<form id="main" name="main" action="${ctx}/loginCheck.html" method="post" class="body">
    <div id="base" class="">
	    <!-- Unnamed (Image) -->
	    <div id="u0" class="ax_image" style="width: 100%; height: 100%; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px; overflow: hidden;">
	      <img id="u0_img" src="${ctx}/images/Other/u0.jpg" style="width: 100%; height: 100%;"  />
	    </div>


      <!-- Unnamed (形状) -->
      <div id="u2" class="ax_文本">
        <img id="u2_img" class="img " src="${ctx}/images/Other/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u3" class="text">
          <p style="margin:0px"><span>用户:</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u4" class="ax_文本">
        <img id="u4_img" class="img " src="${ctx}/images/Other/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u5" class="text">
          <p style="margin:0px"><span>密码:</span></p>
        </div>
      </div>

      <!-- user (文本框(单行)) -->
      <div id="u6" class="ax_文本框_单行_" data-label="user">
       <%--  <input type="text" name="userID" value="${param.userId}"/> --%>
        <input class="easyui-textbox" name="userID" value="${param.userId}" style="width:100%;height:25px" data-options="iconCls:'icon-man'">
      </div>

      <!-- pwd (文本框(单行)) -->
      <div id="u7" class="ax_文本框_单行_" data-label="pwd">
      
      	<input class="easyui-textbox" name="pw" id="pw"  type="password" style="width:100%;height:25px;" data-options="iconCls:'icon-lock'">
       <!-- <input type="password" name="pw" id="pw"/> -->
        <input name="password" type="hidden"/>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u8" class="ax_image">
       <%--  <img id="u8_img" class="img " onclick="javascript:chPW()" src="${ctx}/images/Other/u8.gif"/> --%>
       <a href="#" onclick="javascript:chPW()" class="easyui-linkbutton" style="width:75px" data-options=""><b>登录</b></a> 
      </div>

      <!-- Unnamed (Image) -->
      <div id="u10" class="ax_image">
       <%--  <img id="u10_img" class="img " onclick="javascript:main.reset();" src="${ctx}/images/Other/u10.gif"/> --%>
       <a href="#" onclick="javascript:main.reset()" class="easyui-linkbutton" style="width:75px" data-options=""><b>重置</b></a> 
     
      </div>

      <!-- Unnamed (Image) -->
      <div id="u12" class="ax_image">
        <img id="u12_img" class="img " onclick="javascript:form.reset();" src="${ctx}/images/Other/u12.png"/>
        <!-- Unnamed () -->
        <div id="u13" class="text">
          <p><span></span></p>
        </div>
      </div>
    </div>
    </form>
  </body>
</html>
