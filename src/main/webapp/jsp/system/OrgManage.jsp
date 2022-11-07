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
<script type="text/javascript" src="${ctx}/js/system/sys_org_manage.js"></script>

    <title>机构管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
  	<body>
	<%--<div id="addDlg" class="easyui-window" title="机构新增" modal="true"
		 closed="true" iconCls="icon-save"
		 style="width: 400px; height: 400px; text-align: center; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				 style="background: #fff; border: 1px solid #ccc; padding-left: 30px;">
				<form id="addForm" method="POST"
					  style="width: 300px; height: 350px; text-align: center;">
					<table border="0" class="queryTable" width="300px" height="350px"
						   style="margin-bottom: 0px">
						<tr>
							<td  width="80px">机构编号 <span
									style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td  width="120px"><input type="text"
																		  id="ROLEID" name="ROLEID" class="inputText easyui-validatebox"
																		  data-options="required:true,missingMessage:'请填写机构编号'" /></td>
						</tr>
						<tr>
							<td >机构名称 <span
									style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td ><input type="text" id="ROLENAME"
															name="ROLENAME" class="inputText easyui-validatebox"
															data-options="required:true,missingMessage:'请填写机构名称'" /></td>

						</tr>

						<tr>
							<td class="queryTitle" colspan="4" style="text-align: center;"><a
									class="easyui-linkbutton" iconCls="icon-ok"
									href="javascript:void(0)" onclick="doPostData()">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="easyui-linkbutton" iconCls="icon-cancel"
								   href="javascript:void(0)"
								   onclick="javascript:$('#addDlg').dialog('close')">取消</a></td>
						</tr>
					</table>
				</form>
			</div>

			<!-- 			<div region="south" border="false" -->
			<!-- 				style="text-align: center; height: 30px; line-height: 30px;"> -->
			<!-- 				<a class="easyui-linkbutton" iconCls="icon-ok" -->
			<!-- 					href="javascript:void(0)" onclick="doPostData()">确定</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
			<!-- 				<a class="easyui-linkbutton" iconCls="icon-cancel" -->
			<!-- 					href="javascript:void(0)" -->
			<!-- 					onclick="javascript:$('#addDlg').dialog('close')">取消</a> -->
			<!-- 			</div> -->
		</div>
	</div>--%>
	<div id="addDlg" class="easyui-dialog" title="机构新增"
		 data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
		 style="width: 60%; height: 60%; padding: 5px;text-align: center">
		<form id='addForm' method="POST" class="abms-form">
			<div style="padding: 10px 0 ">

				<div style="display: inline-block;margin-right:10px">&nbsp;机构编号:<input class="easyui-textbox" id="addOrgId"  /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;机构名称:<input class="easyui-textbox" id="addOrgName"  /></div>

			</div>

			<div style="padding: 10px 0 ">
				<div style="display: inline-block;margin-right:10px">
					<a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doSubmit()">确定</a>
					&nbsp;&nbsp; <a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-cancel"
									onclick="doCancel()">取消</a>
				</div>
			</div>

		</form>
	</div>

	<div id="editDlg" class="easyui-dialog" title="机构修改"
		 data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
		 style="width: 60%; height: 60%; padding: 5px;text-align: center">
		<form id='editForm' method="POST" class="abms-form">
			<div style="padding: 10px 0 ">

				<div style="display: inline-block;margin-right:10px">&nbsp;机构编号:<input class="easyui-textbox" id="orgID" disabled="disabled" name="orgID"  /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;机构名称:<input class="easyui-textbox" id="orgName"  name="orgName" /></div>

			</div>

			<div style="padding: 10px 0 ">
				<div style="display: inline-block;margin-right:10px">
					<a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doEditSubmit()">确定</a>
					&nbsp;&nbsp; <a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-cancel"
									onclick="doEditCancel()">取消</a>
				</div>
			</div>

		</form>
	</div>

	<div class="easyui-layout" fit="true">
			<div class="easyui-layout"
				 data-options="region: 'north', border: false">
				<table class="queryTable" width="1000px">
					<tr>
						<td class="queryTitle">&nbsp;机构编号：</td>
						<td class="queryContent">
							<input class="easyui-textbox" type="text" id="queryOrgID" />
						</td>
						<td class="queryTitle">&nbsp;机构名称：</td>
						<td class="queryContent">
							<input  class="easyui-textbox" type="text" id="queryOrgName" />
						</td>

						<td class="queryBtnTd">
							&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="doQuery()">查询</a>
							<a href="javascript:void(0)"
							   class="easyui-linkbutton" iconCls="icon-cancel"
							   onclick="doClear()">清空</a>
						</td>
					</tr>
				</table>
			</div>

			<div class="easyui-layout"
				 data-options="region: 'center', border: false">
				<table id="orgtable" style="height: 100%; width: 100%"></table>
			</div>

		</div>
	</body>
</html>
