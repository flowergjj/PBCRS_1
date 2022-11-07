<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="commDivWidth" value="300px" />
<c:set var="commLabelWidth" value="80px" />
<c:set var="commInputWidth" value="200px" />
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
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
	<script type="text/javascript" src="${ctx}/js/jQuery.md5.js"></script>
<script type="text/javascript" src="${ctx}/js/system/sys_common.js"></script>
<script type="text/javascript" src="${ctx}/js/system/sys_user_manage.js"></script>

<title>用户管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<style>
		.addDivStyle{
			width: 300px;
			display: inline-block;
			margin-right:10px;
			text-align: left;
		}
		.addLabelStyle{
			display: inline-block;
			width: 80px;
			text-align: right;
		}
		.addInputStyle{
			width: 200px;
		}
	</style>
</head>
<body style="visibility: hidden;">
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout"
			data-options="region: 'north', border: false">
			<table class="queryTable" width="1000px">
				<tr>
					<td class="queryTitle">&nbsp;用户号：</td>
					<td class="queryContent"><input class="easyui-textbox" type="text"
						id="queryUserId" /></td>
					<td class="queryTitle">&nbsp;用户姓名：</td>
					<td class="queryContent"><input class="easyui-textbox" type="text"
						id="queryUserName" /></td>
					<td class="queryTitle">&nbsp;用户状态：</td>
					<td class="queryContent"><input class="easyui-combobox"
						id="queryStatus" /></td>
					<td class="queryBtnTd" rowspan="2">&nbsp;&nbsp; <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="doQuery()">查询</a><br /> <br />
						&nbsp;&nbsp; <a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="doClear()">清空</a>
					</td>
				</tr>
				<tr>
					<td class="queryTitle">&nbsp;所属机构：</td>
					<td class="queryContent"><input class="easyui-combobox"
						id="queryOrgId" /></td>

					<td class="queryTitle">&nbsp;所属角色：</td>
					<td class="queryContent"><input class="easyui-combobox"
						id="queryRoleId" /></td>

				</tr>
			</table>
		</div>

		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="usertable" style="height: 100%; width: 100%;"></table>
		</div>
	</div>

	<div id="addDlg" class="easyui-window" title="用户新增" closed="true"
		iconCls="icon-save"
		style="width: 80%; height: 80%;  background: #fafafa;">
		<form id="addForm" method="POST"  style="width: 1000px;margin: 0 auto" >
			<div style="padding: 10px 0 ">
				<div  class="addDivStyle">
					<label class="addLabelStyle">&nbsp;用户号:</label>
					<input type="text" id="userId" class="addInputStyle easyui-textbox" data-options="required:true" /></div>
				<div class="addDivStyle">
					<label class="addLabelStyle">&nbsp;用户姓名:</label>
					<input type="text" id="userName"
						   class=" addInputStyle easyui-textbox"
						   data-options="required:true" />
				</div>
				<div class="addDivStyle">
					<label class="addLabelStyle">&nbsp;所属机构:</label>
					<input class="addInputStyle easyui-combobox"
						   id="orgId" data-options="required:true" />
				</div>

			</div>
			<div style="padding: 10px 0 ">
				<div class="addDivStyle">
					<label class="addLabelStyle">&nbsp;性别:</label>
					<input class="addInputStyle easyui-combobox"
						   id="sex" />
				</div>
				<div class="addDivStyle">
					<label class="addLabelStyle">&nbsp;邮箱:</label>
					<input type="text" id="email"
						   name="email" class="addInputStyle easyui-textbox" />
				</div>
				<div class="addDivStyle" >
					<label class="addLabelStyle">&nbsp;手机号码:</label>
					<input type="text" id="telephone"
						   name="telephone" class="addInputStyle easyui-numberbox"
						   data-options="" />
				</div>

			</div>
			<div style="padding: 10px 0 ">
				<div class="addDivStyle">
					<label class="addLabelStyle">&nbsp;登陆密码:</label>
					<input type="password"  class="addInputStyle easyui-textbox" data-options="required:true"
						   id="password" />

				</div>


			</div>
			<div style="padding: 10px 0 ">
				<div class="addDivStyle" >
					<label class="addLabelStyle">&nbsp;用户角色:</label>
					<span id="userrole">
					</span>
				</div>

			</div>
			<div style="padding: 30px 0;text-align: center ">
				<a class="easyui-linkbutton" iconCls="icon-ok"
				   href="javascript:void(0)" onclick="doPostData()">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" iconCls="icon-cancel"
				   href="javascript:void(0)" onclick="cancelPostData()">返回</a>
			</div>
		</form>

	</div>


	<div id="editDlg" class="easyui-window" title="用户修改" closed="true"
		iconCls="icon-edit"
		style="width: 80%; height: 80%;  background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; padding-left: 5px;">
				<form id="editDlg" method="POST"
					style="width: 1000px;margin: 0 auto">
					<div style="padding: 10px 0 ">
						<div  class="addDivStyle">
							<label class="addLabelStyle">&nbsp;用户号:</label>
							<input type="text" id="updateuserId" name="" class="addInputStyle easyui-textbox" data-options="required:true" /></div>
						<div class="addDivStyle">
							<label class="addLabelStyle">&nbsp;用户姓名:</label>
							<input type="text" id="updateuserName"
								   class=" addInputStyle easyui-textbox"
								   data-options="required:true" />
						</div>
						<div class="addDivStyle">
							<label class="addLabelStyle">&nbsp;所属机构:</label>
							<input class="addInputStyle easyui-combobox"
								   id="updateorgId" data-options="required:true" />
						</div>

					</div>
					<div style="padding: 10px 0 ">
						<div class="addDivStyle">
							<label class="addLabelStyle">&nbsp;性别:</label>
							<input class="addInputStyle easyui-combobox"
								   id="updatesex" />
						</div>
						<div class="addDivStyle">
							<label class="addLabelStyle">&nbsp;邮箱:</label>
							<input type="text" id="updateemail"
								   name="email" class="addInputStyle easyui-textbox" />
						</div>
						<div class="addDivStyle" >
							<label class="addLabelStyle">&nbsp;手机号码:</label>
							<input type="text" id="updatetelephone"
								   name="telephone" class="addInputStyle easyui-numberbox"
								   data-options="" />
						</div>

					</div>
					<div style="padding: 10px 0 ">
						<div class="addDivStyle" >
							<label class="addLabelStyle">&nbsp;用户角色:</label>
							<span id="updateuserrole">
					</span>
						</div>
					</div>
					<div style="padding: 30px 0;text-align: center ">
						<a class="easyui-linkbutton" iconCls="icon-ok"
						   href="javascript:void(0)" onclick="doUpdatePostData()">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-cancel"
						   href="javascript:void(0)" onclick="cancelUpdate()">返回</a>
					</div>
					<%--<table border="0" class="queryTable" width="750px" height="350px"
						style="margin-bottom: 0px">
						<tr>
							<td class="queryTitle">用户号</td>
							<td class="queryContent"><input type="text"
								id="updateuserId" class="inputText" readonly="readonly" /></td>
							<td class="queryTitle">用户姓名</td>
							<td class="queryContent"><input type="text"
								id="updateuserName" class="easyui-textbox" /></td>
							<td class="queryTitle">性别</td>
							<td class="queryContent"><input class="easyui-combobox"
								id="updatesex" /></td>
						</tr>
						<tr>

							<td class="queryTitle">所属机构</td>
							<td class="queryContent"><input class="easyui-combobox"
								id="updateorgId" /></td>
							<td class="queryTitle">授权机构</td>
							<td class="queryContent"><input class="easyui-combobox"
								id="updateAuthOrgId" /></td>
							<td class="queryTitle">所属部门</td>
							<td class="queryContent"><input type="text"
								id="updatedeptName" name="deptName" class="easyui-textbox" /></td>
						</tr>
						<tr>
							<td class="queryTitle">岗位名称</td>
							<td class="queryContent"><input type="text"
								id="updatestation" name="station" class="easyui-textbox" /></td>
							<td class="queryTitle">邮箱</td>
							<td class="queryContent"><input type="text" id="updateemail"
								name="email" class="easyui-textbox" /></td>
							<td class="queryTitle">手机号码</td>
							<td class="queryContent"><input type="text"
								id="updatetelephone" name="telephone" class="easyui-numberbox"
								data-options="required:true" /></td>
						</tr>
						<tr>
							<td class="queryTitle">地址</td>
							<td class="queryContent"><input type="text"
								id="updateaddress" name="address" class="easyui-textbox" /></td>
							<td class="queryTitle">邮编</td>
							<td class="queryContent"><input type="text"
								id="updatepostcode" name="postcode" class="easyui-textbox" /></td>
						</tr>
						<tr>
							<td class="queryTitle">用户角色</td>
							<td class="queryContent" colspan="5" id="updateuserrole"></td>
						</tr>
						<tr>
							<td colspan="4" align="center" style="color:red">(提示:一个用户只能选择一个角色)</td>
						
						</tr>
						<tr>
							<td class="queryTitle">描述</td>
							<td class="queryContent" colspan="5"><textarea
									id="updatedescription" name="description" class="inputText"
									style="width: 440px; height: 80px; resize: none;">
								</textarea></td>
						</tr>
					</table>--%>
				</form>
			</div>
			<%--<div region="south" border="false"
				style="text-align: center; height: 30px; line-height: 30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:void(0)" onclick="doUpdatePostData()">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="cancelUpdate()">返回</a>
			</div>--%>

		</div>

	</div>
</body>
</html>
