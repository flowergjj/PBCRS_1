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
<script type="text/javascript" src="${ctx}/js/ent/delete/CashFlowsDel.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业现金流量表信息记录批量删除</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<table class="queryTable" style="width: 100%">
				<tr>
					<td class="queryTitle">&nbsp;信息报告日期：</td>
					<td class="queryContent"><input class="easyui-datebox" id="queryRptDate" /></td>
					<td class="queryTitle">&nbsp;企业姓名：</td>
					<td class="queryContent"><input class="easyui-textbox" type="text"
						id="queryEntName" /></td>
					<td class="queryTitle">&nbsp;企业身份标识码：</td>
					<td class="queryContent"><input class="easyui-textbox" type="text"
						id="queryEntCertNum" /></td>
					<td class="queryTitle" style="width: 3%">&nbsp;系统：</td>
					<td class="queryContent"><input class="easyui-combobox" type="text"
						id="querySourceSys" /></td>
					<td class="queryBtnTd">&nbsp;&nbsp; <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="doQuery()">查询</a>
						&nbsp;&nbsp; <a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="doClear()">清空</a>
					</td>
				</tr>
				
<!-- 				<tr>
						<td class="queryTitle">&nbsp;所属机构：</td>
					<td class="queryContent"><input class="easyui-combobox" id="queryOrg" /></td>
					<td class="queryTitle"></td>
					<td class="queryContent"></td>
					<td class="queryTitle"></td>
					<td class="queryContent"></td>
					<td class="queryBtnTd" rowspan="2">
					</td>
				</tr> -->
<!-- 				<tr>
					<td class="queryTitle" colspan="8" rowspan="3"><span id="errormsg"></span></td>

				</tr> -->
				
			</table>
			
		</div>
		
		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="CashFlowsTable" style="height: 100%; width: 100%;"></table>
		</div>
	</div>
</body>
</html>