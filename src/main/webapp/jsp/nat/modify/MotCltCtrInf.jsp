<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="uname" value="${user.userName}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${ctx}/js/jQuery.md5.js"></script>
<script type="text/javascript" src="${ctx}/js/system/main.js"></script>
<script type="text/javascript" src="${ctx}/js/nat/modify/MotCltCtrInf.js"></script>
<title>个人抵质押物信息维护</title>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div class="easyui-layout"
		 data-options="region: 'north', border: false">
		<div style="padding: 2px 0 ">
			<div style="display: inline-block;margin-right:10px">&nbsp;信息报告日期:<input id="queryRptDate" type="text" class="easyui-datebox" data-options="editable:false"
																					 style="width: 120px" />
			</div>
			<div style="display: inline-block;margin-right:10px">&nbsp;债务人姓名:<input id="queryName" class="easyui-textbox"  style="width: 200px;" /></div>
			<div style="display: inline-block;margin-right:10px">&nbsp;债务人身份标识号码:<input id="queryCertNum" class="easyui-textbox" style="width: 200px" /></div>
			<div style="display: none;margin-right:10px">&nbsp;系统:<input id="reportSys" class="easyui-combobox" style="width: 150px" /></div>
			<div style="display: inline-block;margin-right:10px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
				   iconCls="icon-search" onclick="doQuery()">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				   iconCls="icon-cancel" onclick="clearQuery()">清空</a>
			</div>

		</div>


	</div>

	<div class="easyui-layout"
		 data-options="region: 'center', border: false">
		<table id="DataListTable" style="height: 100%; width: 100%;"></table>
	</div>
</div>
</body>

</html>