<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript" src="${ctx}/js/common_utils.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
<script type="text/javascript"
	src="${ctx}/js/datajob/indScore.js"></script> 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>个人量化评分</title>
</head>
<body style="padding:0px">
<div   style="border:1px solid rgb(149, 184, 231);background-color:white;width:100%">
	<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;评分日期:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="scoreDate" type="text" class="easyui-datebox"
						data-options="editable:false" style="width: 120px" />
				</div>
	<div style="padding: 4px; display: inline-block;_zoom:1;_display:inline">

					<div style=' display: inline-block;_zoom:1;_display:inline'>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doCreate()">生成文件</a>
					</div>
	</div>
</div>

</body>
</html>