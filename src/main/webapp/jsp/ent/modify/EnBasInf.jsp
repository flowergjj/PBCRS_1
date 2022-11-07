<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript" src="${ctx}/js/common_utils.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
<script type="text/javascript" src="${ctx}/js/ent/modify/EnBasInf.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业基本信息记录</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<%--企业基本信息查询条件 --%>
		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<div style="padding: 2px 0 ">
				<div style="display: inline-block;margin-right:10px">&nbsp;信息报告日期:<input class="easyui-datebox" id="queryRptDate"  /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;企业名称:<input class="easyui-textbox" type="text" id="queryEntName" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;企业身份标识码:<input class="easyui-textbox" type="text" id="queryEntCertNum" /></div>
				<div style="display: none;margin-right:10px">&nbsp;系统:<input class="easyui-combobox" type="text" id="querySourceSys" /></div>
				<div style="display: inline-block;margin-right:10px">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doQuery()">查询</a>&nbsp;
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="doClear()">清空</a>
				</div>
			</div>
		</div>
		<%-- 企业基本信息列表--%>
		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="entTable" style="height: 100%; width: 100%;"></table>
		</div>
	</div>
</body>
</html>