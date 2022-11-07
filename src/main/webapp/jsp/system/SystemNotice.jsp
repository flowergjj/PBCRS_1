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
<script type="text/javascript" src="${ctx}/js/system/systemNotice.js"></script>

<title>系统公告</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div id="queryDlg" class="easyui-panel" title="公告"
			style="width: 100%; height: 100%;">
			<form id="queryDlgForm" method="POST" enctype="multipart/form-data"
				style="text-align: center;">
				<table border="0" class="queryTable" style="width: 100%"
					style="margin-bottom: 0px">
					<input type="hidden" id="STATUS" name="STATUS" value="1" />
					<input type="hidden" id="NOTICE_ID" name="NOTICE_ID" />
					<tr>
						<td class="queryContent" align="center">
							<div id="TITLE" class="showtext"></div>
						</td>
					</tr>
					<tr>
						<td class="queryContent" align="center">
							<hr width="100%"/>
						</td>
					</tr>
					<tr>
						<td class="queryContent" align="center">
							<div id="CONTENT" class="showtext"></div>
						</td>
					</tr>
					<tr>
						<td class="queryContent">
						</td>
					</tr>
					<tr id="update_attachFileRow">
						<td class="queryContent" id="updateAttachArea" align="center"></td>
					</tr>

				</table>
			</form>
		</div>
	</div>

</body>
</html>
