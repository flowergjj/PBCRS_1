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
<script type="text/javascript" src="${ctx}/js/report/reportType.js"></script>
<script type="text/javascript"
	src="${ctx}/js/report/delete/reportDelete.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>报文整笔删除报送</title>
</head>
<body style="visibility: hidden;">
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width: 100px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)" onclick="showDetial('d1');"><span>企业</span></a></li>
				<li><a href="javascript:void(0)" onclick="showDetial('d2');"><span>个人</span></a></li>

			</ul>
		</div>
		<div class="easyui-layout"
			data-options="region: 'center',border: false">
			<div class="easyui-layout" fit="true" id="d1">
				<div class="easyui-layout"
					 data-options="region: 'north', border: false">
					<div style="padding: 2px 0 ">
						<div style="display: inline-block;margin-right:10px">&nbsp;信息报告日期:<input class="easyui-datebox" id="queryRptDate" /></div>
						<div style="display: inline-block;margin-right:10px">&nbsp;企业名称:<input class="easyui-textbox" id="queryEntName" /></div>
						<div style="display: inline-block;margin-right:10px">&nbsp;企业身份标识码:<input class="easyui-textbox"  id="queryEntCertNum" /></div>
						<div style="display: inline-block;margin-right:10px">&nbsp;报文类型:<input class="easyui-combobox" id="queryRptType" /></div>
						<div style="display: inline-block;margin-right:10px">
							<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery()">查询</a> &nbsp;&nbsp; <a
								href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-cancel" onclick="doClear()">清空</a>
						</div>
					</div>
				</div>

				<div class="easyui-layout"
					 data-options="region: 'center', border: false" >
					<table id="EnReportModTable" style="height: 100%; width: 100%;"></table>
				</div>
			</div>
			<div class="easyui-layout" fit="true" id="d2">
				<div class="easyui-layout"
					 data-options="region: 'north', border: false">
					<div style="padding: 2px 0 ">
						<div style="display: inline-block;margin-right:10px">&nbsp;信息报告日期:<input class="easyui-datebox" id="queryInRptDate" /></div>
						<div style="display: inline-block;margin-right:10px">&nbsp;姓名:<input class="easyui-textbox" id="queryInIDName" /></div>
						<div style="display: inline-block;margin-right:10px">&nbsp;证件号码:<input class="easyui-textbox"id="queryInIDNum" /></div>
						<div style="display: inline-block;margin-right:10px">&nbsp;报文类型:<input class="easyui-combobox" id="queryInRptType" /></div>
						<div style="display: inline-block;margin-right:10px">
							<a href="javascript:void(0)" class="easyui-linkbutton"
							   iconCls="icon-search" onclick="doQueryIn()">查询</a> &nbsp;&nbsp; <a
								href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-cancel" onclick="doClearIn()">清空</a>
						</div>
					</div>
				</div>

				<div class="easyui-layout"
					 data-options="region: 'center', border: false">
					<table id="MnMmbInfTable" style="height: 100%; width: 100%;"></table>
				</div>
			</div>

		</div>


	</div>
</body>
</html>