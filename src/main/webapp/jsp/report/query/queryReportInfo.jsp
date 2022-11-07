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
	src="${ctx}/js/report/query/queryReportInfo.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>报文信息查询</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="padding: 0px;"
			data-options="region: 'north',border: false">
			<div style="padding: 2px 0 ">
				<div style="display: inline-block;margin-right:10px">&nbsp;报文日期:<input class="easyui-datebox" id="rptDate" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;客户名称:<input class="easyui-textbox" id="name" style="width: 90px;" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;证件号码:<input class="easyui-textbox" id="idNum" style="width: 120px;" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;借贷账户/抵(质)押合同/ 标识码:<input class="easyui-textbox" id="custCode" style="width: 120px;" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;加密文件名称:<input class="easyui-textbox" id="encName" style="width: 120px;" /></div>
				<div style="display: inline-block;margin-right:10px">
					<a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery()">查询</a>
					&nbsp;&nbsp; <a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-cancel"
									onclick="doClear()">清空</a>
				</div>
			</div>
		</div>

		<!-- 中部表格元素-->
		<div class="easyui-layout"
			data-options="region:'center',border: false">
			<table id="DataListTable" style="height: 100%; width: 100%;"></table>
		</div>
	</div>
</body>
</html>