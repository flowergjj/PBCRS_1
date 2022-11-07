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
<script type="text/javascript" src="${ctx}/js/etlmanager/controller_manage.js"></script>
<script type="text/javascript" src="${ctx}/js/report/reportType.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>调度处理</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout"
			data-options="region: 'center',border: false">

			<!-- <div class="easyui-panel" style=" height: 100%;width:100%;overflow: hidden;" id="ent">
				
				<div class="easyui-layout"
					data-options="region: 'center', border: true" style="height: 100%;">
					<table id="ent_etlpanel" style="height: 100%; width: 100%;"></table>
				</div>
			</div>
 -->

			<div class="easyui-panel" style=" height: 100%;"   id="ind">
				<div class="easyui-layout"
					data-options="region: 'center', border: false"
					style="height: 100%; width: 100%;">
					<table id="ind_etlpanel" style="height: 100%; width: 100%;"></table>
				</div>
			</div>

		</div>
	</div>
	
	<div id="toolbar">
		调度日期:<input class="easyui-datebox" id="etl_date" />
	</div>
	
	
</body>
</html>