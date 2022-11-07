<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp" />

<html>
<head>
<title>工商客户信息查询</title>
<meta http-equiv="Content-Type" content="text/html;
	 charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/demo.css">

<script type="text/javascript" src="${ctx}/js/common_utils.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
<script type="text/javascript" src="${ctx}/js/workbench/gssjcx.js"></script>
</head>

<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="padding: 5px;"
			data-options="region: 'north'">
			<table class="queryTable" style="width: 100%">
				<tr>
					<td class="queryTitle" style="width: 90px;">&nbsp;企业全称/注册号/统一社会信用代码：</td>
					<td class="queryContent" style="width: 290px;"><input
						class="easyui-textbox" id="custmark" style="width:250px;"/></td>
						<td class="queryTitle" style="width: 90px;">&nbsp;查询类型：</td>
					<td class="queryContent"><input class="easyui-combobox"
						id="signType" /></td>
					<td class="queryTitle" style="width: 90px;">&nbsp;数据库：</td>
					<td class="queryContent"><input class="easyui-combobox"
						id="dbtype" /></td>
				</tr>
				
				<tr>
					<td class="queryTitle" style="width: 90px;"></td>
					<td class="queryContent" >
						 <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="doQuery()">查询</a> &nbsp;&nbsp;<a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel" onclick="doClear()">重置</a>
					</td>
					<td class="queryTitle" style="width: 190px;" colspan="2">双击查询项可查看明细并打印</td>
					<td></td>
				</tr>
			</table>
		</div>

		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="gssjtable" style="height: 100%; width: 100%;"></table>
		</div>
</body>
</html>
