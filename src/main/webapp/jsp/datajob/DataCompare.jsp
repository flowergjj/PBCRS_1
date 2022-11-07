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
	src="${ctx}/js/datajob/DataCompare.js"></script> 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>企业-两端比对</title>
</head>
<body style="padding:0px">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<table class="queryTable" style="width: 100%">
<div   style="border:1px solid rgb(149, 184, 231);background-color:white;width:100%">
	<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;比对日期:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="queryRptDate" type="text" class="easyui-datebox"
						data-options="editable:false" style="width: 120px" />
				</div>
   	<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;数据来源:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="dataType" type="text" class="easyui-combobox"
						data-options="editable:false" style="width: 120px" />
				</div>
   	<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;报文类型:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="fieldType" type="text" class="easyui-combobox"
						data-options="editable:false" style="width: 120px" />
				</div>
<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;标识码:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="fieldCode" type="text" class="easyui-textbox"
						 style="width: 120px" />
				</div>
	<div style="padding: 4px; display: inline-block;_zoom:1;_display:inline">

					<div style=' display: inline-block;_zoom:1;_display:inline'>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-add" onclick="add()">生成报送层与集市层比对数据</a>
					</div>
					<div style=' display: inline-block;_zoom:1;_display:inline'>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery('data')">集市层与报送层数据比较</a>
					</div>
					<div style=' display: inline-block;_zoom:1;_display:inline'>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery('report')">报送层与集市层数据比较</a>
					</div>
					
					<div style=' display: inline-block;_zoom:1;_display:inline'>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-add" onclick="doCreateInter()">生成比对文件</a>
					</div> 
					
					<div style=' display: inline-block;_zoom:1;_display:inline'>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery()">查询</a>
					</div>
					<div style=' display: inline-block;_zoom:1;_display:inline'>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-cancel" onclick="doClear()">清空</a>
					</div>
	</div>
</div>
</table>
</div>

		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="DataCompareTable" style="height: 100%; width: 100%;"></table>
		</div>
		
		
	<div id="Detail" class="easyui-window" title="对比详情" modal="true"
		closed="true" iconCls="icon-save"
		style="width: 90%; height: 90%; text-align: center; background: #fafafa;">
		<div class="easyui-layout" fit="true">

		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<table class="queryTable" style="width: 100%">
				<tr>
					<td class="queryTitle">&nbsp;企业姓名：</td>
					<td class="queryContent"><input class="easyui-textbox" type="text"
						id="queryEntName" /></td>
					<td class="queryTitle">&nbsp;企业身份标识码：</td>
					<td class="queryContent"><input class="easyui-textbox" type="text"
						id="queryEntCertNum" /></td>
					<td class="queryBtnTd">&nbsp;&nbsp; <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="doQuery()">查询</a>
						&nbsp;&nbsp; <a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="doClear()">清空</a>
					</td>
				</tr>
				

			</table>
			
		</div>
		
		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="DataCompareDetailTable" style="height: 95%; width: 99%;"></table>
		</div>


		</div>
	</div>
</div>
</body>
</html>