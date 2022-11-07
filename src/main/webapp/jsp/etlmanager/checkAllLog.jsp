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
	src="${ctx}/js/etlmanager/checkAllLog.js"></script> 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>校验日志</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div id="cc" class="easyui-layout" style="padding: 0px;"
			data-options="region: 'north'">
			<div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;校验日期:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="pEtlDate" type="text" class="easyui-datebox"
						data-options="editable:false" style="width: 120px" />
				</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;校验报文:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="checkReportType" class="easyui-combobox" name="msgType"
						style="width: 200px;"/>
				</div>
				
				<!-- <div style="padding: 2px; display: inline-block;">&nbsp;校验时间:</div>
				<div style="padding: 2px; display: inline-block;">
					<input id="queryRptType" class="easyui-combobox" name="dept"
						style="width: 200px;"/>
				</div> -->


				<div style="padding: 4px; display: inline-block;_zoom:1;_display:inline">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery()">查询</a>
					</div>
				</div>
				<div style="padding: 4px; display: inline-block;_zoom:1;_display:inline">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-cancel" onclick="clearQuery()">清空</a>
					</div>
				</div>
			</div>

		</div>

		<!-- 中部表格元素-->
		<div class="easyui-layout "
			data-options="region:'center',border: false">
			<table id="checkAllLog" style="height: 100%; width: 100%;"></table>
		</div>

	</div>
	
	<div id="dialog-checkAll" class="easyui-dialog" title="错误信息"
			data-options="closed:true,modal:true"
			style="width:1000px; height:800px; padding: 5px;">
			<div class="easyui-layout" fit="true">
		
				<div class="easyui-layout"
					data-options="region: 'center', border: false">
					<table id="checkAllTable" style="height: 98%; width: 90%;"></table>
				</div>
			</div>
		</div>
</body>
</html>