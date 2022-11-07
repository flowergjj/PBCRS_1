<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<%-- <jsp:include page="/jsp/report/sendFile/YLSendFile.jsp"></jsp:include> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript" src="${ctx}/js/common_utils.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
<script type="text/javascript" src="${ctx}/js/report/reportType.js"></script>
<script type="text/javascript" src="${ctx}/js/report/sendFile/reportsendFileInd.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>个人报送下载</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<div style="padding: 2px 0 ">
				<div style="display: inline-block;margin-right:10px">&nbsp;生成日期:<input class="easyui-datebox" id="queryRptDate" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;文件名称:<input class="easyui-textbox" type="text" id="queryTxtFileName" /></div>
				<div style="display: none;margin-right:10px">&nbsp;批量目录名:<input class="easyui-textbox" type="text" id="queryFilepath" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;报文类型:<input class="easyui-combobox" id="queryRptType" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;报送日期:<input class="easyui-datebox" id="RptDate" /></div>
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
		
		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="sendFileTable" style="height: 100%; width: 100%;"></table>
		</div>
	</div>

<div id="err" class="easyui-dialog" title="错误详细信息" style="width:90%;height:80%;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">   
		<table id="errTab" style="width:100%;height:100%"></table>
		

</div> 

</body>
</html>