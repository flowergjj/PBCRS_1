<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
<script type="text/javascript" src="${ctx}/js/system/sys_role_group_manage.js"></script>

    <title>角色组管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body style="visibility: hidden;">
	
	<div class="easyui-layout" fit="true">
  	<table id="grouptable" style="height:100%;width:100%"></table>
	</div>

  	<div id="addDlg" class="easyui-window" title="角色组新增" modal="true" closed="true" iconCls="icon-save" style="width:650px; height:450px;text-align:center; background: #fafafa;"	>
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="background:#fff;border:1px solid #ccc;padding-left: 30px;">
				<form id="addForm"  method="POST" style="width: 600px;height:350px; text-align: center;">
					<table border="0" class="queryTable" width="600px" height="350px" style="margin-bottom: 0px" >
						<tr>
							<td class="queryTitle" width="80px">角色组名称
								<span style="color:#ff0000;font-size:7pt;">	
									&nbsp;*
								</span>
							</td>
							<td class="queryContent" width="120px">
								<input type="text" id="groupName" name="groupName" class="inputText"/>
							</td>
						</tr>
						<tr>
							<td class="queryTitle">角色组类型
								<span style="color:#ff0000;font-size:7pt;">	
									&nbsp;*
								</span>
							</td>
							<td class="queryContent">
								<input type="text" id="status" name="status" class="easyui-combobox"/>
							</td>
						</tr>
						<tr>
							<td class="queryTitle">角色</td>
							<td class="queryContent" colspan="3" id="rolelist">
							
							</td>
						</tr>
						
						
					</table>
				</form>
			</div>
			
			<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
			<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="doPostData()">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="javascript:$('#addDlg').dialog('close')">取消</a>
			</div>
		</div>
	</div>
	
	<div id="editDlg" class="easyui-window" title="角色组修改" closed="true" iconCls="icon-edit" style="width:650px; height:450px;text-align:center; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="background:#fff;border:1px solid #ccc;padding-left: 30px;">
				<form id="editDlg"  method="POST" style="width: 600px;height:350px; text-align: center;">
					<input type="hidden" id="updategroupId" name="groupId"/>
					<table border="0" class="queryTable" width="600px" height="350px" style="margin-bottom: 0px" >
						<tr>
							<td class="queryTitle" width="80px">角色组名称
								<span style="color:#ff0000;font-size:7pt;">	
									&nbsp;*
								</span>
							</td>
							<td class="queryContent" width="120px">
								<input type="text" id="updategroupName" name="groupName" class="inputText"/>
							</td>
						</tr>
						<tr>
							<td class="queryTitle">角色组类型
								<span style="color:#ff0000;font-size:7pt;">	
									&nbsp;*
								</span>
							</td>
							<td class="queryContent">
								<input type="text" id="updatestatus" name="status" class="easyui-combobox"/>
							</td>
						</tr>
						<tr>
							<td class="queryTitle">角色</td>
							<td class="queryContent" colspan="3" id="updaterolelist">
							
							</td>
						</tr>

						
					</table>
				</form>
				<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="doUpdatePostData()">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="javascript:$('#editDlg').dialog('close')">返回</a>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>
