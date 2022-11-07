<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript" src="${ctx}/js/system/sys_code.js"></script>
<script type="text/javascript" src="${ctx}/js/system/sys_role_manage.js"></script>

<title>角色管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body style="visibility: hidden;">

	<div class="easyui-layout" fit="true">
		<table id="roletable" style="height: 100%; width: 100%"></table>
	</div>

	<div id="addDlg" class="easyui-window" title="角色新增" modal="true"
		closed="true" iconCls="icon-save"
		style="width: 80%; height: 80%; text-align: center; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; padding-left: 30px;">
				<form id="addForm" method="POST"
					style="width: 600px; height: 350px; text-align: center;">
					<table border="0" class="queryTable" width="600px" height="350px"
						style="margin-bottom: 0px">
						<tr>
							<td class="queryTitle" width="80px">角色ID <span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td class="queryContent" width="120px"><input type="text"
								id="ROLEID" name="ROLEID" class="inputText easyui-validatebox"
								data-options="required:true,missingMessage:'请填写角色ID'" /></td>
							<%--<td class="queryTitle" width="80px">角色层级 <span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td class="queryContent" width="120px"><input type="text"
								id="ROLELEVEL" name="ROLELEVEL" class="easyui-combobox"
								data-options="required:true,missingMessage:'请选择角色层级'" /></td>--%>
						</tr>
						<tr>
							<td class="queryTitle">角色名称 <span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td class="queryContent"><input type="text" id="ROLENAME"
								name="ROLENAME" class="inputText easyui-validatebox"
								data-options="required:true,missingMessage:'请填写角色名称'" /></td>
							<td class="queryTitle" width="80px"><!-- 验证码登录 <span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span> -->
							</td>
							<td class="queryContent" width="120px">
							<!-- <input type="text"
								id="VALID_CODE_FLAG" name="VALID_CODE_FLAG" class="easyui-combobox"
								data-options="required:true,missingMessage:'请选择是否使用验证码登录'" /> -->
								</td>
						</tr>
						<tr>
							<td class="queryTitle" style="vertical-align: top;">权限</td>
							<td class="queryContent" colspan="3">
								<ul id="add_powertree" class="easyui-tree"></ul>
							</td>
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
	</div>

	<div id="editDlg" class="easyui-window" title="角色修改" closed="true"
		iconCls="icon-edit"
		style="width: 80%; height: 80%; text-align: center; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; padding-left: 30px;">
				<form id="editDlg" method="POST"
					style="width: 600px; height: 350px; text-align: center;">
					<table border="0" class="queryTable" width="600px" height="350px"
						style="margin-bottom: 0px; border-spacing: 2px;">
						<tr>
							<td class="queryTitle" width="15%">角色ID<span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span></td>
							<td class="queryContent" width="25%"><input type="text"
								id="roleid" name="roleid" class="inputText" readonly="readonly" /></td>
							<%--<td class="queryTitle" width="15%">角色层级 <span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td class="queryContent" width="25%"><input type="text"
								id="rolelevel" name="rolelevel" class="easyui-combobox"
								data-options="required:true,missingMessage:'请选择角色层级'" /></td>--%>
						</tr>
						<tr>
							<td class="queryTitle">角色名称<span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span></td>
							<td class="queryContent"><input type="text" id="rolename"
								name="rolename" class="easyui-validatebox"
								data-options="required:true,missingMessage:'请填写用户姓名'" readonly="readonly"  /></td>
                            <td class="queryTitle" width="80px"><!-- 登录方式 <span
                                style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span> -->
                            </td>
                            <td class="queryContent" width="120px">
<!--                             <input type="text"
                                id="valid_code_flag" name="valid_code_flag" class="easyui-combobox"
                                data-options="required:true,missingMessage:'请选择登录方式'" /> -->
                                </td>

						</tr>
						<tr>
							<td class="queryTitle" style="vertical-align: top;">权限</td>
							<!-- <td class="queryContent" colspan="3" id="userpower"></td> -->
							<td class="queryContent" colspan="3">
								<ul id="update_powertree" class="easyui-tree"></ul>
							</td>
						</tr>
						<tr>
							<td class="queryTitle" colspan="4" style="text-align: center;">
								<a class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="doUpdatePostData()">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="easyui-linkbutton" iconCls="icon-cancel"
								href="javascript:void(0)"
								onclick="javascript:$('#editDlg').dialog('close')">返回</a>
							</td>
						</tr>
					</table>
				</form>
				<!-- 				<div region="south" border="false" -->
				<!-- 					style="text-align: center; height: 30px; line-height: 30px;"> -->
				<!-- 					<a class="easyui-linkbutton" iconCls="icon-ok" -->
				<!-- 						href="javascript:void(0)" onclick="doUpdatePostData()">提交</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
				<!-- 					<a class="easyui-linkbutton" iconCls="icon-cancel" -->
				<!-- 						href="javascript:void(0)" -->
				<!-- 						onclick="javascript:$('#editDlg').dialog('close')">返回</a> -->
				<!-- 				</div> -->
			</div>
		</div>
	</div>
</body>
</html>
