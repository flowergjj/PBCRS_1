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
	src="${ctx}/js/etlmanager/etlLog.js"></script> 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>调度日志</title>
</head>
<body style="padding:0px">
	<div class="easyui-layout" fit="true">


		<div class="easyui-layout"
			 data-options="region: 'north', border: false" >
			<div style="padding: 2px 0 ">
				<div style="display: inline-block;margin-right:10px">&nbsp;系统:<input class="easyui-combobox" name="dept" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;调度日期:<input class="easyui-datebox" data-options="editable:false" style="width: 120px" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;调度类型:<input id="etlType" class="easyui-combotree" name="etlType" style="width: 200px;"/></div>
				<div style="display: inline-block;margin-right:10px">
					<a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery()">查询</a> &nbsp;&nbsp; <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel" onclick="clearQuery()">清空</a>
				</div>
			</div>

		</div>
		
		<!-- 中部表格元素-->
		<div class="easyui-layout "
			data-options="region:'center',border: false">
			<table id="etlLog" style="height: 100%; width: 100%;"></table>
		</div>

	</div>
	
	<div id="log" class="easyui-dialog" title="调度日志详细信息" style="width:80%;height:80%;"   
        data-options="resizable:false,modal:true,closed:true">   
        <div id="log_detail">
        </div>    
	</div>  
</body>
</html>