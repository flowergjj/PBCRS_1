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
<script type="text/javascript" src="${ctx}/js/nat/modify/InCtrctInf.js"></script>
<title>个人授信信息维护</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div id="cc" class="easyui-layout" style="padding: 0px;"
			data-options="region: 'north'">
			<div>
				<div  style="padding: 2px; display: inline-block;">
					&nbsp;信息报告日期:</div>
				<div style="padding: 2px; display: inline-block;">
					<input id="queryRptDate" type="text" class="easyui-datebox" data-options="editable:false"
						style="width: 120px" />
				</div>
				<div  style="padding: 2px; display: inline-block;">&nbsp;姓名:</div>
				<div style="padding: 2px; display: inline-block;">
					<input id="queryName" class="easyui-textbox"  style="width: 100px;" />
				</div>
				<div  style="padding: 2px; display: inline-block;">&nbsp;证件号码:</div>
				<div
					style="padding: 2px; vertical-align: middle; display: inline-block;">
					<input id="queryIDNum" class="easyui-textbox" style="width: 150px" />
				</div>
				<div  style="padding: 2px; display: inline-block;">&nbsp;系统:</div>
				<div
					style="padding: 2px; vertical-align: middle; display: inline-block;">
					<input id="reportSys" class="easyui-combobox" style="width: 150px" />
				</div>
				
				<div style="padding: 4px; display: inline-block;">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery()">查询</a>
					</div>
				</div>
				<div style="padding: 4px; display: inline-block;">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-cancel" onclick="clearQuery()">清空</a>
					</div>
				</div>
			</div>
			<!-- 更多选择框 -->
			<div style="padding-bottom:5px;display: none;">
				
					<div  style="padding: 2px; display: inline-block;">
						&nbsp;业务管理机构:</div>
					<div style="padding: 2px; display: inline-block;">
						<input id="queryMngmtOrgCode" class="easyui-combobox" style="width: 120px" />
					</div>
				
			</div>
		</div>

		<!-- 中部表格元素-->
		<div  class="easyui-layout " data-options="region:'center',border: false">
			 <table id="DataListTable" style="height: 100%; width: 100%;"></table> 
		</div>

	</div>
</body>
</html>